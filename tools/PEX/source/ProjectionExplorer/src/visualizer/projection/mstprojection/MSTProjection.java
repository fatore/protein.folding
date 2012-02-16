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

package visualizer.projection.mstprojection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.datamining.clustering.BKmeans;
import visualizer.graph.Connectivity;
import visualizer.graph.Edge;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.matrix.Matrix;
import visualizer.projection.Projection;
import visualizer.projection.ProjectionData;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DissimilarityFactory;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.lsp.MeshGenerator;
import visualizer.util.ANN;
import visualizer.util.PExConstants;
import visualizer.util.Pair;
import visualizer.util.Prim;
import visualizer.wizard.ProjectionView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class MSTProjection extends Projection {

    @Override
    public float[][] project(Matrix matrix, ProjectionData pdata, ProjectionView view) {
        this.matrix = matrix;

        try {
            int nrclusters = (int) Math.pow(matrix.getRowCount(), 0.75);
            Dissimilarity diss = DissimilarityFactory.getInstance(pdata.getDissimilarityType());

            if (view != null) {
                view.setStatus("Creating the clusters...", 10);
            }

            //calculating the aproximated KNN
            BKmeans bkmeans = new BKmeans(nrclusters);
            ArrayList<ArrayList<Integer>> clusters = bkmeans.execute(diss, matrix);
            Matrix centroids = bkmeans.getCentroids();

            if (view != null) {
                view.setStatus("Calculating the Aproximatted KNN...", 15);
            }

            ANN appknn = new ANN(15);
            Pair[][] mesh = appknn.execute(matrix, diss, clusters, centroids);

            if (view != null) {
                view.setStatus("Defining  connected graph...", 35);
            }

            //generating the connected mesh
            MeshGenerator meshgen = new MeshGenerator();
            mesh = meshgen.execute(mesh, matrix, diss);

            if (view != null) {
                view.setStatus("Calculating the minimum spanning tree...", 40);
            }

            Prim prim = new Prim(mesh);
            ArrayList<Prim.Edge> edges = prim.execute();

            //creating the neighborhood
            ArrayList<ArrayList<Pair>> neigh_aux = new ArrayList<ArrayList<Pair>>();

            for (int i = 0; i < matrix.getRowCount(); i++) {
                neigh_aux.add(new ArrayList<Pair>());
            }

            for (Prim.Edge e : edges) {
                neigh_aux.get(e.node1).add(new Pair(e.node2, e.len));
            }

            neighborhood = new Pair[matrix.getRowCount()][];

            for (int i = 0; i < neigh_aux.size(); i++) {
                neighborhood[i] = new Pair[neigh_aux.get(i).size()];

                for (int j = 0; j < neigh_aux.get(i).size(); j++) {
                    neighborhood[i][j] = neigh_aux.get(i).get(j);
                }
            }

            if (view != null) {
                view.setStatus("Defining the final layout...", 80);
            }

            RadialLayout layout = new RadialLayout();
            ArrayList<RadialLayout.Node> nodes = layout.execute(neighborhood);

            float[][] projection = new float[nodes.size()][];
            for (int i = 0; i < nodes.size(); i++) {
                projection[i] = new float[2];
                projection[i][0] = nodes.get(i).x;
                projection[i][1] = nodes.get(i).y;
            }

            return projection;
        } catch (IOException ex) {
            Logger.getLogger(MSTProjection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public float[][] project(DistanceMatrix dmat, ProjectionData pdata, ProjectionView view) {
        this.dmat = dmat;

        try {
            if (view != null) {
                view.setStatus("Calculating the minimum spanning tree...", 40);
            }

            Prim prim = new Prim(dmat);
            ArrayList<Prim.Edge> edges = prim.execute();

            //creating the neighborhood
            ArrayList<ArrayList<Pair>> neigh_aux = new ArrayList<ArrayList<Pair>>();

            for (int i = 0; i < dmat.getElementCount(); i++) {
                neigh_aux.add(new ArrayList<Pair>());
            }

            for (Prim.Edge e : edges) {
                neigh_aux.get(e.node1).add(new Pair(e.node2, e.len));
            }

            neighborhood = new Pair[dmat.getElementCount()][];

            for (int i = 0; i < neigh_aux.size(); i++) {
                neighborhood[i] = new Pair[neigh_aux.get(i).size()];

                for (int j = 0; j < neigh_aux.get(i).size(); j++) {
                    neighborhood[i][j] = neigh_aux.get(i).get(j);

                    System.out.print(neighborhood[i][j].value + " ");
                }
                System.out.println();
            }

            if (view != null) {
                view.setStatus("Defining the final layout...", 80);
            }

            RadialLayout layout = new RadialLayout();
            ArrayList<RadialLayout.Node> nodes = layout.execute(neighborhood);

            float[][] projection = new float[nodes.size()][];
            for (int i = 0; i < nodes.size(); i++) {
                projection[i] = new float[2];
                projection[i][0] = nodes.get(i).x;
                projection[i][1] = nodes.get(i).y;
            }

            return projection;
        } catch (IOException ex) {
            Logger.getLogger(MSTProjection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void createConnectivities(Graph graph, ProjectionData pdata, ProjectionView view) {
        super.createConnectivities(graph, pdata, view);

        if (neighborhood != null) {
            if (view != null) {
                view.setStatus("Creating MST connectivity...", 90);
            }

            Connectivity mstcon = new Connectivity(PExConstants.MST);
            ArrayList<Edge> edges = new ArrayList<Edge>();

            HashMap<Long, Vertex> index = new HashMap<Long, Vertex>();
            ArrayList<Vertex> vertex = graph.getVertex();
            for (Vertex v : vertex) {
                index.put(v.getId(), v);
            }

            for (int i = 0; i < neighborhood.length; i++) {
                for (int j = 0; j < neighborhood[i].length; j++) {
                    edges.add(new Edge(neighborhood[i][j].value,
                            index.get((long) i), index.get((long) neighborhood[i][j].index)));
                }
            }

            edges = Connectivity.compress(edges);
            mstcon.setEdges(edges);
            graph.addConnectivity(mstcon);
        }
    }

    @Override
    public ProjectionView getProjectionView(ProjectionData pdata) {
        return new MSTProjectionView(pdata);
    }

    public static void main(String[] args) {
        try {
            String filename = "D:\\My Documents\\FERNANDO\\Tese\\datasets\\iris-std.dmat";
            DistanceMatrix dmat = new DistanceMatrix(filename);

            MSTProjection mstproj = new MSTProjection();
            float[][] project = mstproj.project(dmat, null, null);

            for (int i = 0; i < project.length; i++) {
                System.out.println(project[i][0] + " " + project[i][1]);
            }

        } catch (IOException ex) {
            Logger.getLogger(MSTProjection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Pair[][] neighborhood;
}
