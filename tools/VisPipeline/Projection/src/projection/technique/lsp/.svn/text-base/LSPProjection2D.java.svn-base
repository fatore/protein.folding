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
package projection.technique.lsp;

import projection.util.MeshGenerator;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.colt.matrix.linalg.CholeskyDecomposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lspsolver.Solver;
import distance.DistanceMatrix;
import projection.technique.Projection;
import projection.technique.idmap.IDMAPProjection;
import datamining.neighbors.ANN;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import datamining.clustering.BKmeans;
import datamining.clustering.Kmedoids;
import distance.dissimilarity.AbstractDissimilarity;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class LSPProjection2D implements Projection {

    public enum ControlPointsType {

        RANDOM, KMEDOIDS, KMEANS
    }

    public LSPProjection2D() {
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
                for (int c = 0; c < clusters.size()
                        && medoids_aux.size() < nrcontrolpoints; c++) {
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

        //getting the nearest neighbors
        ANN appknn = new ANN(nrneighbors);
        Pair[][] mesh = appknn.execute(matrix, diss, clusters, centroids);

        AbstractMatrix matrix_cp = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < this.controlPoints.length; i++) {
            matrix_cp.addRow(matrix.getRow(this.controlPoints[i]));
        }

        DistanceMatrix dmat_cp = new DistanceMatrix(matrix_cp, diss);

        IDMAPProjection idmap = new IDMAPProjection();
        idmap.setFractionDelta(fracdelta);
        idmap.setNumberIterations(nriterations);
        projection_cp = idmap.project(dmat_cp);

        //creating the mesh
        MeshGenerator meshgen = new MeshGenerator();
        mesh = meshgen.execute(mesh, matrix, diss);

        //creating the final projection
        projection = createFinalProjection(mesh, matrix);

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Least Square Projection (LSP) time: {0}s", (finish - start) / 1000.0f);

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
        IDMAPProjection idmap = new IDMAPProjection();
        idmap.setFractionDelta(fracdelta);
        idmap.setNumberIterations(nriterations);
        projection_cp = idmap.project(dmat_cp);

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
        ArrayList<String> labels = matrix.getLabels();

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector vector = new DenseVector(new float[]{0, 0}, ids.get(i), cdata[i]);

            if (labels.isEmpty()) {
                projection.addRow(vector);
            } else {
                projection.addRow(vector, labels.get(i));
            }
        }

//        if (System.getProperty("os.name").toLowerCase().equals("windows xp") ||
//            System.getProperty("os.name").toLowerCase().equals("windows vista") ||
//            System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
        projectUsingProgram(neighbors, projection);
//        } else {
//            projectUsingColt(neighbors, projection);
//        }

        Runtime.getRuntime().gc();

        return projection;
    }

    ////////////////////////////////////////////////////////////////////////////////
    //USING DISTANCE MATRIX
    private AbstractMatrix createFinalProjection(Pair[][] neighbors, DistanceMatrix dmat) {
        AbstractMatrix projection = new DenseMatrix();

        ArrayList<Integer> ids = dmat.getIds();
        float[] classData = dmat.getClassData();
        ArrayList<String> labels = dmat.getLabels();

        for (int i = 0; i < dmat.getElementCount(); i++) {
            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(new float[]{0, 0}, ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(new float[]{0, 0}, ids.get(i), classData[i]), labels.get(i));
            }
        }

        if (System.getProperty("os.name").toLowerCase().equals("windows xp")
                || System.getProperty("os.name").toLowerCase().equals("windows vista")
                || System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
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

        SparseDoubleMatrix2D B = new SparseDoubleMatrix2D(nRows, 2);
        for (int i = 0; i < projection_cp.getRowCount(); i++) {
            B.setQuick((neighbors.length + i), 0, projection_cp.getRow(i).getValue(0));
            B.setQuick((neighbors.length + i), 1, projection_cp.getRow(i).getValue(1));
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
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using Colt time: {0}s", (finish - start) / 1000.0f);
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
            }

            float[] result = solver.solve();
            for (int i = 0; i < result.length; i += 2) {
                AbstractVector row = projection.getRow(i / 2);
                row.setValue(0, result[i]);
                row.setValue(1, result[i + 1]);
            }
        } catch (IOException ex) {
            Logger.getLogger(LSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Solving the system using LSPSolver time: {0}s", (finish - start) / 1000.0f);
    }
    private AbstractMatrix projection_cp;
    private int[] controlPoints;
    private ControlPointsType type;
    private int nrcontrolpoints;
    private int nrneighbors;
    private float fracdelta;
    private int nriterations;
}
