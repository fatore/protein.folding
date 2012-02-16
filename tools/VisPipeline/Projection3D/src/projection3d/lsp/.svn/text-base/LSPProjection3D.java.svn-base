
package projection3d.lsp;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.colt.matrix.linalg.CholeskyDecomposition;
import datamining.clustering.BKmeans;
import datamining.clustering.Kmedoids;
import datamining.neighbors.ANN;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lspsolver.Solver;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;
import projection.technique.fastmap.FastmapProjection;
import projection.util.MeshGenerator;


/**
 *
 * @author jpocom
 */
public class LSPProjection3D implements Projection {

    public enum ControlPointsType {

        RANDOM, KMEDOIDS, KMEANS
    }

    public LSPProjection3D() {
        this.type = ControlPointsType.RANDOM;
        this.nrcontrolpoints = 100;
        this.nrneighbors = 10;
        this.fracdelta = 8.0f;
        this.nriterations = 50;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        AbstractMatrix projection = null;
        ArrayList<ArrayList<Integer>> clusters = null;
        AbstractMatrix centroids = null;

        if (type == ControlPointsType.KMEANS) {
            //clustering the points
            BKmeans bkmeans = new BKmeans(nrcontrolpoints);
            clusters = bkmeans.execute(diss, matrix);
            centroids = bkmeans.getCentroids();
            controlPoints = bkmeans.getMedoids(matrix);

            //if less medoids are returned than the expected (due to the
            //clustering method employed), choose on the clusters other
            //medoids
            ArrayList<Integer> medoids_aux = new ArrayList<Integer>();
            for (int i = 0; i < controlPoints.length; i++) {
                medoids_aux.add(controlPoints[i]);
            }

            while (medoids_aux.size() < nrcontrolpoints) {
                for (int c = 0; c < clusters.size() &&
                        medoids_aux.size() < nrcontrolpoints; c++) {
                    if (clusters.get(c).size() > matrix.getRowCount() / nrcontrolpoints) {
                        for (int i = 0; i < clusters.get(c).size(); i++) {
                            if (!medoids_aux.contains(clusters.get(c).get(i))) {
                                medoids_aux.add(clusters.get(c).get(i));
                                break;
                            }
                        }
                    }
                }
            }

            controlPoints = new int[medoids_aux.size()];
            for (int i = 0; i < controlPoints.length; i++) {
                controlPoints[i] = medoids_aux.get(i);
            }

            nrcontrolpoints = controlPoints.length;

        } else if (type == ControlPointsType.RANDOM) {
            //Random choice
            controlPoints = new int[nrcontrolpoints];
            for (int i = 0; i < controlPoints.length; i++) {
                controlPoints[i] = (int) (Math.random() * matrix.getRowCount());
            }

            BKmeans bkmeans = new BKmeans(nrcontrolpoints);
            clusters = bkmeans.execute(diss, matrix);
            centroids = bkmeans.getCentroids();
        }

        AbstractMatrix matrix_cp = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < this.controlPoints.length; i++) {
            matrix_cp.addRow(matrix.getRow(this.controlPoints[i]));
        }

        DistanceMatrix dmat_cp = new DistanceMatrix(matrix_cp, diss);
        FastmapProjection fastmap = new FastmapProjection(3);
        projection_cp = fastmap.project(dmat_cp);
        ForceScheme force = new ForceScheme(fracdelta, projection_cp.getRowCount());
        for (int i = 0; i < nriterations; i++) {
            force.iteration(dmat_cp, projection_cp);
        }

        //getting the nearest neighbors
        ANN appknn = new ANN(nrneighbors);
        Pair[][] mesh = appknn.execute(matrix, diss, clusters, centroids);

        //creating the mesh
        MeshGenerator meshgen = new MeshGenerator();
        mesh = meshgen.execute(mesh, matrix, diss);

        //creating the final projection
        projection = createFinalProjection(mesh, matrix);

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Least Square Projection (LSP) time: " + (finish - start) / 1000.0f + "s");

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        //Choosen the control points
        if (type == ControlPointsType.KMEDOIDS) {
            Kmedoids kmedois = new Kmedoids(nrcontrolpoints);
            kmedois.execute(dmat);
            controlPoints = kmedois.getMedoids();
        } else if (type == ControlPointsType.RANDOM) {
            //Random choice
            controlPoints = new int[nrcontrolpoints];
            for (int i = 0; i < controlPoints.length; i++) {
                controlPoints[i] = (int) (Math.random() * dmat.getElementCount());
            }
        }

        //Creating the control points distance matrix
        DistanceMatrix dmat_cp = new DistanceMatrix(nrcontrolpoints);

        for (int i = 0; i < nrcontrolpoints; i++) {
            for (int j = 0; j < nrcontrolpoints; j++) {
                if (i != j) {
                    dmat_cp.setDistance(i, j, dmat.getDistance(controlPoints[i], controlPoints[j]));
                }
            }
        }

        //Projecting the control points
        FastmapProjection fastmap = new FastmapProjection(3);
        projection_cp = fastmap.project(dmat_cp);

        
        ForceScheme force = new ForceScheme(fracdelta, projection_cp.getRowCount());
        for (int i = 0; i < nriterations; i++) {
            force.iteration(dmat_cp, projection_cp);
        }

        //creating the KNN mesh
        KNN knnmesh = new KNN(nrneighbors);
        Pair[][] mesh = knnmesh.execute(dmat);

        MeshGenerator meshgen = new MeshGenerator();
        mesh = meshgen.execute(mesh, dmat);

        //creating the final projection
        return createFinalProjection(mesh, dmat);
    }

    /**
     * @return the type
     */
    public ControlPointsType getControlPointsChoice() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setControlPointsChoice(ControlPointsType type) {
        this.type = type;
    }

    /**
     * @return the nrcontrolpoints
     */
    public int getNumberControlPoints() {
        return nrcontrolpoints;
    }

    /**
     * @param nrcontrolpoints the nrcontrolpoints to set
     */
    public void setNumberControlPoints(int nrcontrolpoints) {
        this.nrcontrolpoints = nrcontrolpoints;
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

    /**
     * @return the fracdelta
     */
    public float getFractionDelta() {
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

    ////////////////////////////////////////////////////////////////////////////////
    //USING POINTS
    private AbstractMatrix createFinalProjection(Pair[][] neighbors, AbstractMatrix matrix) throws IOException {
        AbstractMatrix projection = new DenseMatrix();

        ArrayList<Integer> ids = matrix.getIds();
        float[] cdata = matrix.getClassData();

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector vector = new DenseVector(new float[]{0, 0, 0}, ids.get(i), cdata[i]);
            projection.addRow(vector);
        }

        if (System.getProperty("os.name").toLowerCase().equals("windows xp") ||
            System.getProperty("os.name").toLowerCase().equals("windows vista") ||
            //System.getProperty("os.name").toLowerCase().equals("mac os x") ||
            System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
            projectUsingProgram(neighbors, projection);
        } else {
            projectUsingColt(neighbors, projection);
        }

        Runtime.getRuntime().gc();

        return projection;
    }

    ////////////////////////////////////////////////////////////////////////////////
    //USING DISTANCE MATRIX
    private AbstractMatrix createFinalProjection(Pair[][] neighbors, DistanceMatrix dmat) {
        AbstractMatrix projection = new DenseMatrix();
        for (int i = 0; i < dmat.getElementCount(); i++) {
            projection.addRow(new DenseVector(new float[]{0, 0, 0}, dmat.getIds().get(i), dmat.getClassData()[i]));
        }

        if (System.getProperty("os.name").toLowerCase().equals("windows xp") ||
                System.getProperty("os.name").toLowerCase().equals("windows vista") ||
                System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
            this.projectUsingProgram(neighbors, projection);
        } else {
            this.projectUsingColt(neighbors, projection);
        }

        Runtime.getRuntime().gc();

        return projection;
    }

    private void projectUsingColt(Pair[][] neighbors, AbstractMatrix projection) {
        long start = System.currentTimeMillis();

        int nRows = neighbors.length + nrcontrolpoints;
        int nColumns = neighbors.length;
        SparseDoubleMatrix2D A = new SparseDoubleMatrix2D(nRows, nColumns);

        for (int i = 0; i < neighbors.length; i++) {
            //new approach to increase the neighborhood precision
            A.setQuick(i, i, 1.0f);

//            for (int j = 0; j < neighbors[i].length; j++) {
//                A.setQuick(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
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
                    A.setQuick(i, neighbors[i][j].index, (-((1 / dist) / sum)));
                } else {
                    A.setQuick(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
                }
            }
        }

        //Creating the Fij
        for (int i = 0; i < nrcontrolpoints; i++) {
            A.setQuick((projection.getRowCount() + i), this.controlPoints[i], 1.0f);
        }

        SparseDoubleMatrix2D B = new SparseDoubleMatrix2D(nRows, 3);
        for (int i = 0; i < projection_cp.getRowCount(); i++) {
            B.setQuick((neighbors.length + i), 0, projection_cp.getRow(i).getValue(0));
            B.setQuick((neighbors.length + i), 1, projection_cp.getRow(i).getValue(1));
            B.setQuick((neighbors.length + i), 2, projection_cp.getRow(i).getValue(2));
        }

        //Solving
        DoubleMatrix2D AtA = A.zMult(A, null, 1.0, 1.0, true, false);
        DoubleMatrix2D AtB = A.zMult(B, null, 1.0, 1.0, true, false);

        start = System.currentTimeMillis();
        CholeskyDecomposition chol = new CholeskyDecomposition(AtA);
        DoubleMatrix2D X = chol.solve(AtB);

        for (int i = 0; i < X.rows(); i++) {
            AbstractVector row = projection.getRow(i);
            row.setValue(0, (float) X.getQuick(i, 0));
            row.setValue(1, (float) X.getQuick(i, 1));
            row.setValue(2, (float) X.getQuick(i, 2));
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using Colt time: " + (finish - start) / 1000.0f + "s");
    }

    private void projectUsingProgram(Pair[][] neighbors, AbstractMatrix projection) {
        long start = System.currentTimeMillis();

        int nRows = neighbors.length + nrcontrolpoints;
        int nColumns = neighbors.length;

        Solver solver = new Solver(nRows, nColumns);

        try {
            ////////////////////////////////////////////
            //creating matrix A
            for (int i = 0; i < neighbors.length; i++) {
                //new approach to increase the neighborhood precision
                solver.addToA(i, i, 1.0f);

//                for (int j = 0; j < neighbors[i].length; j++) {
//                    solver.addToA(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
//                }

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

            for (int i = 0; i < nrcontrolpoints; i++) {
                solver.addToA((projection.getRowCount() + i), controlPoints[i], 1.0f);
            }

            ////////////////////////////////////////////
            //creating matrix B
            for (int i = 0; i < projection_cp.getRowCount(); i++) {
                solver.addToB((neighbors.length + i), 0, projection_cp.getRow(i).getValue(0));
                solver.addToB((neighbors.length + i), 1, projection_cp.getRow(i).getValue(1));
                solver.addToB((neighbors.length + i), 2, projection_cp.getRow(i).getValue(2));
            }

            float[] result = solver.solve();
            for (int i = 0; i < result.length; i += 3) {
                AbstractVector row = projection.getRow(i / 3);
                row.setValue(0, result[i]);
                row.setValue(1, result[i + 1]);
                row.setValue(2, result[i + 2]);
            }
        } catch (IOException ex) {
            Logger.getLogger(LSPProjection3D.class.getName()).log(Level.SEVERE, null, ex);
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using LSPSolver time: " + (finish - start) / 1000.0f + "s");
    }

    private AbstractMatrix projection_cp;
    private int[] controlPoints;
    private ControlPointsType type;
    private int nrcontrolpoints;
    private int nrneighbors;
    private float fracdelta;
    private int nriterations;
}
