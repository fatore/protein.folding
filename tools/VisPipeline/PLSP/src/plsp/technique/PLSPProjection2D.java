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
package plsp.technique;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.CholeskyDecomposition;
import datamining.clustering.BKmeans;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lspsolver.Solver;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;
import projection.technique.fastmap.FastmapProjection2D;
import projection.util.ProjectionUtil;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PLSPProjection2D implements Projection {

    public enum ControlPointsType {

        BKMEANS, RANDOM
    }

    public enum SolverType {

        PARALLEL, SEQUENTIAL
    }

    public PLSPProjection2D() {
        this.solvertype = SolverType.PARALLEL;
        this.cptype = ControlPointsType.BKMEANS;
        this.fracdelta = 8.0f;
        this.nriterations = 50;
        this.nrneighbors = 5;
        this.threadpoolsize = 3;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        int nrclusters = (int) Math.sqrt(matrix.getRowCount());
        BKmeans bkmeans = new BKmeans(nrclusters);
        ArrayList<ArrayList<Integer>> clusters = bkmeans.execute(diss, matrix);

        //getting the control points
        this.cpoints = getControlPoints(matrix, diss, clusters, bkmeans.getMedoids(matrix));

        //projecting the control points
        ArrayList<ArrayList<float[]>> cpointsproj = projectControlPoints(matrix, diss, cpoints);

        //projecting each patch
        float[][] aux_proj = new float[matrix.getRowCount()][];

        if (solvertype == SolverType.PARALLEL) {
            ArrayList<ParallelSolver> threads = new ArrayList<ParallelSolver>();

            for (int i = 0; i < clusters.size(); i++) {
                ParallelSolver ps = new ParallelSolver(matrix, diss, clusters.get(i),
                        cpoints.get(i), cpointsproj.get(i), aux_proj);
                threads.add(ps);
            }

            try {
                ExecutorService executor = Executors.newFixedThreadPool(threadpoolsize);
                executor.invokeAll(threads);
                executor.shutdown();
            } catch (InterruptedException ex) {
                Logger.getLogger(PLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (solvertype == SolverType.SEQUENTIAL) {
            for (int i = 0; i < clusters.size(); i++) {
                float[][] projcluster = projectCluster(matrix, diss,
                        clusters.get(i), cpoints.get(i), cpointsproj.get(i));

                for (int j = 0; j < clusters.get(i).size(); j++) {
                    aux_proj[clusters.get(i).get(j)] = projcluster[j];
                }
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Piecewise Least Square Projection (P-LSP) time: {0}s", (finish - start) / 1000.0f);

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

    public void setControlPointsType(ControlPointsType type) {
        cptype = type;
    }

    public ControlPointsType getControlPointsType() {
        return cptype;
    }

    /**
     * @return the solvertype
     */
    public SolverType getSolverType() {
        return solvertype;
    }

    /**
     * @param solvertype the solvertype to set
     */
    public void setSolverType(SolverType solvertype) {
        this.solvertype = solvertype;
    }

    private float[][] projectCluster(AbstractMatrix matrix, AbstractDissimilarity diss,
            ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
            ArrayList<float[]> cpointsproj) throws IOException {
        long start = System.currentTimeMillis();

        if (cluster.size() > 3) {
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

            CreateNNG nng = new CreateNNG();
            nng.execute(neighbors, projmatrix, diss);

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

                for (int j = 0; j < neighbors[i].length; j++) {
                    solver.addToA(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
                }
            }

            float w = 20.0f; //weigthing the control points

            for (int i = 0; i < cpoints.size(); i++) {
                solver.addToA((cluster.size() + i), indexes.get(cpoints.get(i)), w);
            }

            ////////////////////////////////////////////
            //creating matrix B
            for (int i = 0; i < cpoints.size(); i++) {
                solver.addToB((cluster.size() + i), 0, cpointsproj.get(i)[0] * w);
                solver.addToB((cluster.size() + i), 1, cpointsproj.get(i)[1] * w);
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
        } else {
            float[][] projection = new float[cluster.size()][];

            for (int i = 0; i < cpoints.size(); i++) {
                projection[i] = new float[2];
                projection[i][0] = cpointsproj.get(i)[0];
                projection[i][1] = cpointsproj.get(i)[1];
            }

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "PLSP: projecting a cluster with less than 3 elements!");

            return projection;
        }
    }

    private float[][] projectClusterColt(AbstractMatrix matrix, AbstractDissimilarity diss,
            ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
            ArrayList<float[]> cpointsproj) throws IOException {
        long start = System.currentTimeMillis();

        if (cluster.size() > 3) {
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

            CreateNNG nng = new CreateNNG();
            nng.execute(neighbors, projmatrix, diss);

            //map the elements on the cluster to sequential ids
            HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>();
            for (int i = 0; i < cluster.size(); i++) {
                indexes.put(cluster.get(i), i);
            }

            int nrows = cluster.size() + cpoints.size();
            int ncolumns = cluster.size();

            ////////////////////////////////////////////
            //creating matrix A
            DoubleMatrix2D A = new DenseDoubleMatrix2D(nrows, ncolumns);

            for (int i = 0; i < cluster.size(); i++) {
                A.setQuick(i, i, 1.0f);

                for (int j = 0; j < neighbors[i].length; j++) {
                    A.setQuick(i, neighbors[i][j].index, (-(1.0f / neighbors[i].length)));
                }
            }

            float w = 20.0f; //weigthing the control points

            for (int i = 0; i < cpoints.size(); i++) {
                A.setQuick((cluster.size() + i), indexes.get(cpoints.get(i)), w);
            }

            ////////////////////////////////////////////
            //creating matrix B
            DoubleMatrix2D B = new DenseDoubleMatrix2D(nrows, 2);

            for (int i = 0; i < cpoints.size(); i++) {
                B.setQuick((cluster.size() + i), 0, cpointsproj.get(i)[0] * w);
                B.setQuick((cluster.size() + i), 1, cpointsproj.get(i)[1] * w);
            }

            ///////////////////////////////////////////
            //soling the system
            float[][] projection = new float[cluster.size()][];

            //solving to find A
            DoubleMatrix2D AtA = A.zMult(A, null, 1.0, 1.0, true, false);
            DoubleMatrix2D AtB = A.zMult(B, null, 1.0, 1.0, true, false);

            CholeskyDecomposition chol = new CholeskyDecomposition(AtA);
            DoubleMatrix2D result = chol.solve(AtB);

            for (int i = 0; i < result.rows(); i++) {
                projection[i] = new float[2];
                projection[i][0] = (float) result.getQuick(i, 0);
                projection[i][1] = (float) result.getQuick(i, 1);
            }

            long finish = System.currentTimeMillis();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Solving the system using Colt time: {0}s", (finish - start) / 1000.0f);

            return projection;
        } else {
            float[][] projection = new float[cluster.size()][];

            for (int i = 0; i < cpoints.size(); i++) {
                projection[i] = new float[2];
                projection[i][0] = cpointsproj.get(i)[0];
                projection[i][1] = cpointsproj.get(i)[1];
            }

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "PLSP: projecting a cluster with less than 3 elements!");

            return projection;
        }
    }

    private ArrayList<ArrayList<Integer>> getControlPoints(AbstractMatrix matrix,
            AbstractDissimilarity diss, ArrayList<ArrayList<Integer>> clusters, int[] medoids) throws IOException {
        ArrayList<ArrayList<Integer>> controlpoints = new ArrayList<ArrayList<Integer>>();

        //percentage of points of each cluster to use
        float perc = (float) Math.pow(matrix.getRowCount(), 0.75) / matrix.getRowCount();

        if (cptype == ControlPointsType.BKMEANS) {
            //for each patch
            for (int i = 0; i < clusters.size(); i++) {
                //defining the number of clusters to be found
                int nrcluster = (int) (clusters.get(i).size() * perc);
                nrcluster = (nrcluster > 3) ? nrcluster : (clusters.get(i).size() > 3) ? 3 : clusters.get(i).size();

                if (clusters.get(i).size() > 3) {
                    //creating the matrix with the points on the patch
                    AbstractMatrix matcluster = MatrixFactory.getInstance(matrix.getClass());
                    for (int j = 0; j < clusters.get(i).size(); j++) {
                        matcluster.addRow(matrix.getRow(clusters.get(i).get(j)));
                    }

                    BKmeans bkmeans = new BKmeans(nrcluster);
                    bkmeans.execute(diss, matcluster);
                    int[] med = bkmeans.getMedoids(matcluster);

                    ArrayList<Integer> cpoints_aux = new ArrayList<Integer>();
                    for (int j = 0; j < med.length; j++) {
                        cpoints_aux.add(clusters.get(i).get(med[j]));
                    }

                    controlpoints.add(cpoints_aux);
                } else {
                    ArrayList<Integer> cpoints_aux = new ArrayList<Integer>();
                    for (int j = 0; j < clusters.get(i).size(); j++) {
                        cpoints_aux.add(clusters.get(i).get(j));
                    }

                    controlpoints.add(cpoints_aux);
                }
            }
        } else if (cptype == ControlPointsType.RANDOM) {
            for (int i = 0; i < clusters.size(); i++) {
                //defining the number of control points on the cluster
                int samplesize = (int) (clusters.get(i).size() * perc) - 1;
                samplesize = (samplesize > 2) ? samplesize : 2;

                HashSet<Integer> sample = new HashSet<Integer>();
                Random random = new Random(System.currentTimeMillis());
                sample.add(medoids[i]); //adding the cluster medoid

                while (sample.size() < samplesize) {
                    int index = (int) (random.nextFloat() * (clusters.get(i).size()));
                    if (index < matrix.getRowCount()) {
                        sample.add(clusters.get(i).get(index));
                    }
                }

                ArrayList<Integer> cpoints_aux = new ArrayList<Integer>();
                Iterator<Integer> it = sample.iterator();
                while (it.hasNext()) {
                    int index = it.next();
                    cpoints_aux.add(index);
                }

                controlpoints.add(cpoints_aux);
            }
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

        FastmapProjection2D fastmap = new FastmapProjection2D();
        AbstractMatrix proj = fastmap.project(projmatrix, diss);
        FastForceScheme2D ffs = new FastForceScheme2D();
        ffs.setProjection(proj);
        ffs.setNumberIterations(nriterations);
        float[][] project = ffs.project(projmatrix, diss).toMatrix();

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

    class ParallelSolver implements Callable<Integer> {

        public ParallelSolver(AbstractMatrix matrix, AbstractDissimilarity diss,
                ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
                ArrayList<float[]> cpointsproj, float[][] aux_proj) {
            this.matrix = matrix;
            this.diss = diss;
            this.cluster = cluster;
            this.cpoints = cpoints;
            this.cpointsproj = cpointsproj;
            this.aux_proj = aux_proj;
        }

        @Override
        public Integer call() throws Exception {
            try {
//                System.out.println(cluster.size() + " " + cpoints.size() + " " + cpointsproj.size());

                float[][] projcluster = projectCluster(matrix, diss,
                        cluster, cpoints, cpointsproj);

                for (int j = 0; j < cluster.size(); j++) {
                    aux_proj[cluster.get(j)] = projcluster[j];
                }
            } catch (IOException ex) {
                Logger.getLogger(PLSPProjection2D.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

            return 0;
        }
        private AbstractMatrix matrix;
        private AbstractDissimilarity diss;
        private ArrayList<Integer> cluster;
        private ArrayList<Integer> cpoints;
        private ArrayList<float[]> cpointsproj;
        private float[][] aux_proj;
    }

    public static void main(String args[]) throws IOException {
        String filename = "/home/paulovich/Data/multifield.0099_4_4_4-normcols.bin";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        ProjectionUtil.log(false, false);

        long start = System.currentTimeMillis();
        PLSPProjection2D plsp = new PLSPProjection2D();
        plsp.setFractionDelta(8.0f);
        plsp.setNumberIterations(25);
        plsp.setNumberNeighbors(8);
        plsp.project(matrix, new Euclidean()).save(filename + "-plsp.prj");

        long finish = System.currentTimeMillis();
        System.out.println("PLSP time: " + (finish - start) / 1000.0f + "s");
    }
    private SolverType solvertype;
    private ControlPointsType cptype;
    private ArrayList<ArrayList<Integer>> cpoints;
    private float fracdelta;
    private int nriterations;
    private int nrneighbors;
    private int threadpoolsize;
}
