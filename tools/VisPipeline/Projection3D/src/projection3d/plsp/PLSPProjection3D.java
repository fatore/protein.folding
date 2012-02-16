package projection3d.plsp;

import datamining.clustering.BKmeans;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lspsolver.Solver;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;
import projection.util.ConnectedGraphGenerator;
import projection3d.lsp.LSPProjection3D;
import projection3d.lsp.LSPProjection3D.ControlPointsType;


/**
 *
 * @author jpocom
 */
public class PLSPProjection3D implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        int nrclusters = (int) Math.sqrt(matrix.getRowCount());
        BKmeans bkmeans = new BKmeans(nrclusters);
        ArrayList<ArrayList<Integer>> clusters = bkmeans.execute(diss, matrix);

        //getting the control points
        this.cpoints = getControlPoints(matrix, diss, clusters);

        //projecting the control points
        ArrayList<ArrayList<float[]>> cpointsproj = projectControlPoints(matrix, diss, cpoints);

        //projecting each patch
        float[][] aux_proj = new float[matrix.getRowCount()][];

        for (int i = 0; i < clusters.size(); i++) {
            float[][] projcluster = projectCluster(matrix, diss,
                    clusters.get(i), cpoints.get(i), cpointsproj.get(i));

            for (int j = 0; j < clusters.get(i).size(); j++) {
                aux_proj[clusters.get(i).get(j)] = projcluster[j];
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Piecewise LSP 3D (P-LSP) time: " + (finish - start) / 1000.0f + "s");

        AbstractMatrix projection = new DenseMatrix();
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("x");
        attributes.add("y");
        attributes.add("z");
        projection.setAttributes(attributes);

        ArrayList<Integer> ids = matrix.getIds();
        float[] cdata = matrix.getClassData();

        for (int i = 0; i < aux_proj.length; i++) {
            AbstractVector vector = new DenseVector(aux_proj[i], ids.get(i), cdata[i]);
            projection.addRow(vector);
        }

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the fracdelta
     */
    public float getFracdelta() {
        return fracdelta;
    }

    /**
     * @param fracdelta the fracdelta to set
     */
    public void setFractionDelta(float fracdelta) {
        this.fracdelta = fracdelta;
    }

    /**
     * @return the nriterations
     */
    public int getNumberIterations() {
        return nriterations;
    }

    /**
     * @param nriterations the nriterations to set
     */
    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    /**
     * @return the nrneighbors
     */
    public int getNumberNeighbors() {
        return nrneighbors;
    }

    /**
     * @param nrneighbors the nrneighbors to set
     */
    public void setNumberNeighbors(int nrneighbors) {
        this.nrneighbors = nrneighbors;
    }

    private float[][] projectCluster(AbstractMatrix matrix, AbstractDissimilarity diss,
            ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
            ArrayList<float[]> cpointsproj) throws IOException {
        long start = System.currentTimeMillis();

        //creatng the matrix with all points
        AbstractMatrix projmatrix = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < cluster.size(); i++) {
            projmatrix.addRow(matrix.getRow(cluster.get(i)));
        }

        int local_nrneighbors = nrneighbors;
        local_nrneighbors = (local_nrneighbors < cluster.size()) ? local_nrneighbors : cluster.size() - 1;

        //creating the neighborhood graph
        KNN ann = new KNN(local_nrneighbors);
        Pair[][] neighbors = ann.execute(projmatrix, diss);

        ConnectedGraphGenerator congraph = new ConnectedGraphGenerator();
        congraph.execute(neighbors, projmatrix, diss);

        //map the elements on the cluster to sequential ids
        HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>();
        for (int i = 0; i < cluster.size(); i++) {
            indexes.put(cluster.get(i), i);
        }

        int nRows = cluster.size() + cpoints.size();
        int nColumns = cluster.size();

        Solver solver = new Solver(nRows, nColumns);

        ////////////////////////////////////////////
        //creating matrix A
        for (int i = 0; i < cluster.size(); i++) {
            //new approach to increase the neighborhood precision
            solver.addToA(i, i, 1.0f);

//            for (int j = 0; j < neighbors[i].length; j++) {
//                solver.addToA(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
//            }

            float max = Float.NEGATIVE_INFINITY;
            float min = Float.POSITIVE_INFINITY;

            for (int j = 0; j < neighbors[i].length; j++) {
                if (max < neighbors[i][j].value) {
                    max = neighbors[i][j].value;
                }

                if (min > neighbors[i][j].value) {
                    min = neighbors[i][j].value;
                }
            }

            float sum = 0;
            for (int j = 0; j < neighbors[i].length; j++) {
                if (max > min) {
                    float dist = (((neighbors[i][j].value - min) / (max - min)) * (0.9f)) + 0.1f;
                    sum += (1 / dist);
                }
            }

            for (int j = 0; j < neighbors[i].length; j++) {
                if (max > min) {
                    float dist = (((neighbors[i][j].value - min) / (max - min)) * (0.9f)) + 0.1f;
                    solver.addToA(i, neighbors[i][j].index, (-((1 / dist) / sum)));
                } else {
                    solver.addToA(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
                }
            }
        }

        for (int i = 0; i < cpoints.size(); i++) {
            solver.addToA((cluster.size() + i), indexes.get(cpoints.get(i)), 1.0f);
        }

        ////////////////////////////////////////////
        //creating matrix B
        for (int i = 0; i < cpoints.size(); i++) {
            solver.addToB((cluster.size() + i), 0, cpointsproj.get(i)[0]);
            solver.addToB((cluster.size() + i), 1, cpointsproj.get(i)[1]);
            solver.addToB((cluster.size() + i), 2, cpointsproj.get(i)[2]);
        }

        ///////////////////////////////////////////
        //soling the system
        float[][] projection = new float[cluster.size()][];

        float[] result = solver.solve();
        for (int i = 0; i < result.length; i += 3) {
            projection[i / 3] = new float[3];
            projection[i / 3][0] = result[i];
            projection[i / 3][1] = result[i + 1];
            projection[i / 3][2] = result[i + 2];
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using LSPSolver time: " + (finish - start) / 1000.0f + "s");

        return projection;
    }

    private ArrayList<ArrayList<Integer>> getControlPoints(AbstractMatrix matrix,
            AbstractDissimilarity diss, ArrayList<ArrayList<Integer>> clusters) throws IOException {
        ArrayList<ArrayList<Integer>> controlpoints = new ArrayList<ArrayList<Integer>>();

        //percentage of points of each cluster to use
        float perc = (float) Math.pow(matrix.getRowCount(), 0.75) / matrix.getRowCount();

        //for each patch
        for (int i = 0; i < clusters.size(); i++) {
            //creating the matrix with the points on the patch
            AbstractMatrix matcluster = MatrixFactory.getInstance(matrix.getClass());
            for (int j = 0; j < clusters.get(i).size(); j++) {
                matcluster.addRow(matrix.getRow(clusters.get(i).get(j)));
            }

            //defining the number of clusters to be found
            int nrcluster = (int) (matcluster.getRowCount() * perc);
            nrcluster = (nrcluster > 3) ? nrcluster : 3;

            BKmeans bkmeans = new BKmeans(nrcluster);
            bkmeans.execute(diss, matcluster);
            int[] medoids = bkmeans.getMedoids(matcluster);

            ArrayList<Integer> cpoints_aux = new ArrayList<Integer>();
            for (int j = 0; j < medoids.length; j++) {
                cpoints_aux.add(clusters.get(i).get(medoids[j]));
            }

            controlpoints.add(cpoints_aux);
        }

        return controlpoints;
    }

    private ArrayList<ArrayList<float[]>> projectControlPoints(AbstractMatrix matrix,
            AbstractDissimilarity diss, ArrayList<ArrayList<Integer>> controlpoints) throws IOException {

        //creatng the matrix with all points
        AbstractMatrix projmatrix = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < controlpoints.size(); i++) {
            for (int j = 0; j < controlpoints.get(i).size(); j++) {
                projmatrix.addRow(matrix.getRow(controlpoints.get(i).get(j)));
            }
        }

        LSPProjection3D lsp = new LSPProjection3D();

        if (projmatrix.getRowCount() < 500) {
            lsp.setNumberNeighbors(10);
        } else if (projmatrix.getRowCount() < 5000) {
            lsp.setNumberNeighbors(15);
        } else if (projmatrix.getRowCount() < 10000) {
            lsp.setNumberNeighbors(20);
        } else {
            lsp.setNumberNeighbors(25);
        }

        lsp.setFractionDelta(fracdelta);
        lsp.setNumberIterations(nriterations);
        lsp.setControlPointsChoice(ControlPointsType.KMEANS);
        lsp.setNumberControlPoints(projmatrix.getRowCount() / 5);

        float[][] project = lsp.project(projmatrix, diss).toMatrix();

        //store the aux_proj regarding the patches
        ArrayList<ArrayList<float[]>> projection = new ArrayList<ArrayList<float[]>>();
        int count = 0;
        for (int i = 0; i < controlpoints.size(); i++) {
            ArrayList<float[]> patchproj = new ArrayList<float[]>();
            for (int j = 0; j < controlpoints.get(i).size(); j++) {
                patchproj.add(project[count]);
                count++;
            }
            projection.add(patchproj);
        }

        return projection;
    }

    private ArrayList<ArrayList<Integer>> cpoints;
    private float fracdelta = 8.0f;
    private int nriterations = 50;
    private int nrneighbors = 10;
}
