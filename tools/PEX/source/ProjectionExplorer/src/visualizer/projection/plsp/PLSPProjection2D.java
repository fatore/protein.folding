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
package visualizer.projection.plsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lspsolver.Solver;
import visualizer.datamining.clustering.BKmeans;
import visualizer.graph.Graph;
import visualizer.graph.Scalar;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.Projection;
import visualizer.projection.ProjectionData;
import visualizer.projection.ProjectorType;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.lsp.ControlPointsType;
import visualizer.projection.lsp.LSPProjection2D;
import visualizer.util.KNN;
import visualizer.util.PExConstants;
import visualizer.util.Pair;
import visualizer.util.Util;
import visualizer.wizard.ProjectionView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PLSPProjection2D extends Projection {

    @Override
    public float[][] project(Matrix matrix, ProjectionData pdata, ProjectionView view) {
        this.matrix = matrix;

        long start = System.currentTimeMillis();

        float[][] projection = null;

        try {
            Dissimilarity diss = DissimilarityFactory.getInstance(pdata.getDissimilarityType());

            //creating the clusters
            if (view != null) {
                view.setStatus("Calculating the B-KMEANS...", 35);
            }

            int nrclusters = (int) Math.sqrt(matrix.getRowCount());
            BKmeans bkmeans = new BKmeans(nrclusters);
            ArrayList<ArrayList<Integer>> clusters = bkmeans.execute(diss, matrix);

            if (view != null) {
                view.setStatus("Getting and projecting the control points...", 40);
            }

            //getting the control points
            this.cpoints = getControlPoints(matrix, diss, clusters);

            //projecting the control points
            ArrayList<ArrayList<float[]>> cpointsproj = projectControlPoints(matrix, pdata, cpoints, view);


            if (view != null) {
                view.setStatus("Projecting the patches...", 70);
            }

            //projecting each patch
            projection = new float[matrix.getRowCount()][];

            for (int i = 0; i < clusters.size(); i++) {
                float[][] projcluster = projectCluster(pdata, matrix,
                        clusters.get(i), cpoints.get(i), cpointsproj.get(i));

                for (int j = 0; j < clusters.get(i).size(); j++) {
                    projection[clusters.get(i).get(j)] = projcluster[j];
                }

                if (view != null) {
                    view.setStatus("Projecting the patches...",
                            (int) (70 + (20.0f / clusters.size()) * i));
                }
            }

            if (view != null) {
                view.setStatus("Finished...", 90);
            }
        } catch (IOException ex) {
            Logger.getLogger(PLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Piecewise LSP (P-LSP) time: " + (finish - start) / 1000.0f + "s");

        return projection;
    }

    @Override
    public float[][] project(DistanceMatrix dmat, ProjectionData pdata, ProjectionView view) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProjectionView getProjectionView(ProjectionData pdata) {
        return new PLSPProjection2DView(pdata);
    }

    @Override
    public void postProcessing(Graph graph) {
        Scalar s = graph.addScalar(PExConstants.PIVOTS);

        for (int i = 0; i < graph.getVertex().size(); i++) {
            graph.getVertex().get(i).setScalar(s, 0.0f);
        }

        for (int i = 0; i < this.cpoints.size(); i++) {
            for (int j = 0; j < this.cpoints.get(i).size(); j++) {
                graph.getVertex().get(this.cpoints.get(i).get(j)).setScalar(s, 1.0f);
            }
        }
    }

    private float[][] projectCluster(ProjectionData pdata, Matrix matrix,
            ArrayList<Integer> cluster, ArrayList<Integer> cpoints,
            ArrayList<float[]> cpointsproj) throws IOException {
        long start = System.currentTimeMillis();

        //getting the dissimilarity
        Dissimilarity diss = DissimilarityFactory.getInstance(pdata.getDissimilarityType());

        //creatng the matrix with all points
        Matrix projmatrix = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < cluster.size(); i++) {
            projmatrix.addRow(matrix.getRow(cluster.get(i)));
        }

        int nrneighbors = pdata.getNumberNeighborsConnection();
        nrneighbors = (nrneighbors < cluster.size()) ? nrneighbors : cluster.size() - 1;

        //creating the neighborhood graph
        KNN ann = new KNN(nrneighbors);
        Pair[][] neighbors = ann.execute(projmatrix, diss);

        ConnectedGraphGenerator congraph = new ConnectedGraphGenerator();
        neighbors = congraph.execute(neighbors, projmatrix, diss);

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

        float w = 1.0f; //weigthing the control points

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
                "Solving the system using LSPSolver time: " + (finish - start) / 1000.0f + "s");

        return projection;
    }

    private ArrayList<ArrayList<Integer>> getControlPoints(Matrix matrix,
            Dissimilarity diss, ArrayList<ArrayList<Integer>> clusters) throws IOException {
        ArrayList<ArrayList<Integer>> controlpoints = new ArrayList<ArrayList<Integer>>();

        //percentage of points of each cluster to use
        float perc = (float) Math.pow(matrix.getRowCount(), 0.75) / matrix.getRowCount();

        //for each patch
        for (int i = 0; i < clusters.size(); i++) {
            //creating the matrix with the points on the patch
            Matrix matcluster = MatrixFactory.getInstance(matrix.getClass());
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

    private ArrayList<ArrayList<float[]>> projectControlPoints(Matrix matrix,
            ProjectionData pdata, ArrayList<ArrayList<Integer>> controlpoints,
            ProjectionView view) throws IOException {

        //creatng the matrix with all points
        Matrix projmatrix = MatrixFactory.getInstance(matrix.getClass());
        for (int i = 0; i < controlpoints.size(); i++) {
            for (int j = 0; j < controlpoints.get(i).size(); j++) {
                projmatrix.addRow(matrix.getRow(controlpoints.get(i).get(j)));
            }
        }

        ProjectionData pdatalsp = new ProjectionData();
        pdatalsp.setDissimilarityType(pdata.getDissimilarityType());

        if (projmatrix.getRowCount() < 500) {
            pdatalsp.setNumberNeighborsConnection(10);
        } else if (projmatrix.getRowCount() < 5000) {
            pdatalsp.setNumberNeighborsConnection(15);
        } else if (projmatrix.getRowCount() < 10000) {
            pdatalsp.setNumberNeighborsConnection(20);
        } else {
            pdatalsp.setNumberNeighborsConnection(25);
        }

        pdatalsp.setFractionDelta(8.0f);
        pdatalsp.setNumberIterations(pdata.getNumberIterations());
        pdatalsp.setControlPointsChoice(ControlPointsType.KMEANS);
        pdatalsp.setProjectorType(ProjectorType.FASTMAP);
        pdatalsp.setNumberControlPoints(projmatrix.getRowCount() / 5);

        LSPProjection2D lsp = new LSPProjection2D();
        float[][] project = lsp.project(projmatrix, pdatalsp, view);

        //store the projection regarding the patches
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
            Util.log(true, false);

            if (args.length != 3) {
                System.out.println("Usage: java PLSPProjection2D <number neighbors> <points> <dissimilarity>");
                System.out.println("   ex: java PLSPProjection2D 10 points.data cosine");
            }

            DissimilarityType disstype = null;
            if (args[2].trim().toLowerCase().equals("cosine")) {
                disstype = DissimilarityType.COSINE_BASED;
            } else {
                disstype = DissimilarityType.EUCLIDEAN;
            }

            String filename = args[1];
            Matrix matrix = MatrixFactory.getInstance(filename);

            ProjectionData pdata = new ProjectionData();
            pdata.setDissimilarityType(disstype);
            pdata.setNumberNeighborsConnection(Integer.parseInt(args[0]));
            pdata.setFractionDelta(8.0f);
            pdata.setNumberIterations(50);
            pdata.setControlPointsChoice(ControlPointsType.KMEANS);
            pdata.setProjectorType(ProjectorType.FASTMAP);
            pdata.setSourceFile(filename);

            PLSPProjection2D plsp2D = new PLSPProjection2D();
            float[][] projection = plsp2D.project(matrix, pdata, null);

            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(filename + "-[" + args[0] + "N]PLSP.prj"));

                out.write("DY\r\n");
                out.write(projection.length + "\r\n");
                out.write("2\r\n");
                out.write("x;y\r\n");

                for (int i = 0; i < projection.length; i++) {
                    out.write(matrix.getRow(i).getId() + ";" + projection[i][0] +
                            ";" + projection[i][1] + ";" + matrix.getRow(i).getKlass() + "\r\n");
                }
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(PLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<ArrayList<Integer>> cpoints;
}
