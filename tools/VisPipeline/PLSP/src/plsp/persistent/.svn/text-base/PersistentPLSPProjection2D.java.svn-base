/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *
 * PEx is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */
package plsp.persistent;

import datamining.clustering.BKmeans;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import projection.technique.Projection;
import projection.technique.lsp.LSPProjection2D;
import java.util.Arrays;
import lspsolver.Solver;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import plsp.model.PLSPProjectionModelComp;
import plsp.technique.CreateNNG;
import projection.model.ProjectionModel;
import projection.technique.lsp.LSPProjection2D.ControlPointsType;
import projection.view.ProjectionFrameComp;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PersistentPLSPProjection2D implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        int nrclusters = (int) Math.sqrt(matrix.getRowCount());
        BKmeans bkmeans = new BKmeans(nrclusters);
        this.clusters = bkmeans.execute(diss, matrix);

        //getting the control points
        this.cpoints = getControlPoints(matrix, diss, clusters);

        //projecting the control points
        ArrayList<ArrayList<float[]>> cpointsproj = projectControlPoints(matrix, diss, cpoints);

        //projecting each patch
        float[][] aux_proj = new float[matrix.getRowCount()][];

        for (int i = 0; i < clusters.size(); i++) {
            float[][] projcluster = projectCluster(matrix, diss,
                    clusters.get(i), cpoints.get(i), cpointsproj.get(i), null, null);

            for (int j = 0; j < clusters.get(i).size(); j++) {
                aux_proj[clusters.get(i).get(j)] = projcluster[j];
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, 
                "Persistent Piecewise LSP (P-LSP) time: {0}s", (finish - start) / 1000.0f);

        AbstractMatrix projection = new DenseMatrix();
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("x");
        attributes.add("y");
        projection.setAttributes(attributes);

        ArrayList<Integer> ids = matrix.getIds();
        float[] cdata = matrix.getClassData();
        ArrayList<String> labels = matrix.getLabels();

        for (int i = 0; i < aux_proj.length; i++) {
            AbstractVector vector = new DenseVector(aux_proj[i], ids.get(i), cdata[i]);

            if (labels.isEmpty()) {
                projection.addRow(vector);
            } else {
                projection.addRow(vector, labels.get(i));
            }
        }
        return projection;
    }

    public float[][] reproject(AbstractMatrix matrix, AbstractDissimilarity diss, AbstractMatrix matrixCoord) throws IOException {
        long start = System.currentTimeMillis();

        int nrclusters = (int) Math.sqrt(matrix.getRowCount());
        BKmeans bkmeans = new BKmeans(nrclusters);
        this.clusters = bkmeans.execute(diss, matrix);

        //getting the control points
        this.cpoints = getControlPoints(matrix, diss, clusters);

        //getting the control points coordination
        ArrayList<ArrayList<float[]>> cpointsproj = new ArrayList<ArrayList<float[]>>();
        for (int i = 0; i < cpoints.size(); i++) {
            ArrayList<float[]> cpointsPatch = new ArrayList<float[]>();
            for (int j = 0; j < cpoints.get(i).size(); j++) {
                float coord[] = new float[2];
                coord[0] = matrixCoord.getRow(cpoints.get(i).get(j)).getValue(0);
                coord[1] = matrixCoord.getRow(cpoints.get(i).get(j)).getValue(1);
                cpointsPatch.add(coord);
            }
            cpointsproj.add(cpointsPatch);
        }

        //projecting each patch
        float[][] reprojection = new float[matrix.getRowCount()][];

        for (int i = 0; i < clusters.size(); i++) {
            float[][] projcluster = projectCluster(matrix, diss,
                    clusters.get(i), cpoints.get(i), cpointsproj.get(i), null, null);

            for (int j = 0; j < clusters.get(i).size(); j++) {
                reprojection[clusters.get(i).get(j)] = projcluster[j];
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Persistent Piecewise LSP (P-LSP) time: {0}s", (finish - start) / 1000.0f);

        return reprojection;
    }

    public ArrayList<ArrayList<Integer>> getClusters() {
        return clusters;
    }

    public ArrayList<ArrayList<Integer>> getCpoints() {
        return cpoints;
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

    public float[][] projectCluster(AbstractMatrix matrix, AbstractDissimilarity diss,
            ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
            ArrayList<float[]> cpointsproj, Pair[][] neighbors, float[] weights) throws IOException {
        long start = System.currentTimeMillis();

        if (weights == null) {
            weights = new float[cpoints.size()];
            Arrays.fill(weights, 1.0f);
        }

        //creating the neighborhood graph
        if (neighbors == null) {
            //creatng the matrix with all points
            AbstractMatrix projmatrix = MatrixFactory.getInstance(matrix.getClass());
            for (int i = 0; i < cluster.size(); i++) {
                projmatrix.addRow(matrix.getRow(cluster.get(i)));
            }

            int local_nrneighbors = nrneighbors;
            local_nrneighbors = (local_nrneighbors < cluster.size()) ? local_nrneighbors : cluster.size() - 1;

            KNN ann = new KNN(local_nrneighbors);
            neighbors = ann.execute(projmatrix, diss);

            CreateNNG nng = new CreateNNG();
            nng.execute(neighbors, projmatrix, diss);

            this.neighborhoodGraphs.add(neighbors);
        }

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
            solver.addToA((cluster.size() + i), indexes.get(cpoints.get(i)), weights[i]);// 1.0f);
        }

        ////////////////////////////////////////////
        //creating matrix B
        for (int i = 0; i < cpoints.size(); i++) {
            System.out.println("x:" + cpointsproj.get(i)[0] + ", y: " + cpointsproj.get(i)[1]);
            solver.addToB((cluster.size() + i), 0, cpointsproj.get(i)[0] * weights[i]);
            solver.addToB((cluster.size() + i), 1, cpointsproj.get(i)[1] * weights[i]);
        }

        ///////////////////////////////////////////
        //soling the system
        float[][] projection = new float[cluster.size()][];

        float[] result = solver.solve();
        for (int i = 0; i < result.length; i += 2) {
            projection[i / 2] = new float[2];
            projection[i / 2][0] = result[i];
            projection[i / 2][1] = result[i + 1];
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using LSPSolver time: {0}s", (finish - start) / 1000.0f);

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

        LSPProjection2D lsp = new LSPProjection2D();

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

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            //reader.setFilename("C:\\Users\\User\\Desktop\\plsp.bin");
            //reader.setFilename("D:\\gaborBrodatzBoasRuins_NotNormalized.data");
            reader.setFilename("D:\\DANILO\\DOUTORADO\\VisContest08\\TS30\\superSampleMultifield.0030.bin");
            reader.execute();

//            PersistentPLSPBinaryReaderComp reader1 = new PersistentPLSPBinaryReaderComp();
//            reader1.setFilename("C:\\Users\\User\\Desktop\\PLSP.plspbin");
//            reader1.execute();



            AbstractMatrix matrix = reader.output();

//            StreamingMatrix matrix = new StreamingMatrix();
//            matrix.load("D:\\Dados\\mammals-1000000.bin");

            /////////////////////////////////////////////////////
            /////////////////////////////////////////////////////
            /////////////////////////////////////////////////////

            System.out.println("Projecting....");
            long start = System.currentTimeMillis();

            PersistentPLSPProjection2DComp spcomp = new PersistentPLSPProjection2DComp();
            spcomp.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            spcomp.setFractionDelta(8.0f);
            spcomp.setNumberIterations(50);
            spcomp.input(matrix);
            spcomp.execute();
            AbstractMatrix proj1 = spcomp.output();

            long finish = System.currentTimeMillis();

            Logger.getLogger(PersistentPLSPProjection2DComp.class.getName()).log(Level.INFO,
                    "PersistentPLSPProjection2D time: " + (finish - start) / 1000.0f + "s");

            PLSPProjectionModelComp mcomp1 = new PLSPProjectionModelComp();
//            mcomp1.input(matrix);
//            mcomp1.input(reader1.output());
            mcomp1.input(proj1);
            mcomp1.input(spcomp.outputPLSP());
            mcomp1.execute();
            ProjectionModel model1 = mcomp1.output();

            ProjectionFrameComp fcomp1 = new ProjectionFrameComp();
            fcomp1.input(model1);
            fcomp1.execute();

//            StressComp scomp1 = new StressComp();
//            scomp1.setStressType(StressType.KRUSKAL);
//            LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, new Euclidean());
//            scomp1.input(proj1, dmat);
//            scomp1.execute();
//            System.out.println(scomp1.output());
        } catch (IOException ex) {
            Logger.getLogger(PersistentPLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setClusters(ArrayList<ArrayList<Integer>> clusters) {
        this.clusters = clusters;
    }

    public void setCpoints(ArrayList<ArrayList<Integer>> cpoints) {
        this.cpoints = cpoints;
    }

    public ArrayList<Pair[][]> getNeighborhoodGraphs() {
        return neighborhoodGraphs;
    }

    public void setNeighborhoodGraphs(ArrayList<Pair[][]> neighborhoodGraphs) {
        this.neighborhoodGraphs = neighborhoodGraphs;
    }
    private ArrayList<ArrayList<Integer>> cpoints;
    private ArrayList<ArrayList<Integer>> clusters;
    private ArrayList<Pair[][]> neighborhoodGraphs = new ArrayList<Pair[][]>();
    private float fracdelta = 8.0f;
    private int nriterations = 50;
    private int nrneighbors = 10;
}
