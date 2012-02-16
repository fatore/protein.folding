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

package graph.technique.connection.mstproj;

import datamining.clustering.BKmeans;
import datamining.neighbors.ANN;
import datamining.neighbors.Pair;
import java.io.IOException;
import java.util.ArrayList;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import graph.model.Connectivity;
import graph.model.Edge;
import graph.technique.connection.GraphConnection;
import graph.util.GraphConstants;
import matrix.AbstractMatrix;
import projection.util.MeshGenerator;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class MSTConnection extends GraphConnection {

    @Override
    public Connectivity execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        int nrclusters = (int) Math.pow(matrix.getRowCount(), 0.75);

        //calculating the aproximated KNN
        BKmeans bkmeans = new BKmeans(nrclusters);
        ArrayList<ArrayList<Integer>> clusters = bkmeans.execute(diss, matrix);
        AbstractMatrix centroids = bkmeans.getCentroids();

        ANN appknn = new ANN(15);
        Pair[][] mesh = appknn.execute(matrix, diss, clusters, centroids);

        //generating the connected mesh
        MeshGenerator meshgen = new MeshGenerator();
        mesh = meshgen.execute(mesh, matrix, diss);

        Prim prim = new Prim(mesh);
        ArrayList<Prim.Edge> edges_aux = prim.execute();

        //generating the connectivity
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < edges_aux.size(); i++) {
            Prim.Edge edge = edges_aux.get(i);
            float dis = edge.len;
            int sour = edge.node1;
            int targ = edge.node2;
            edges.add(new Edge(sour, targ, dis));
        }

        Connectivity conn = new Connectivity(GraphConstants.MST, edges);
        return conn;
    }

    @Override
    public Connectivity execute(DistanceMatrix dmat) throws IOException {
        Prim prim = new Prim(dmat);
        ArrayList<Prim.Edge> edges_aux = prim.execute();

        //generating the connectivity
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < edges_aux.size(); i++) {
            Prim.Edge edge = edges_aux.get(i);
            float dis = edge.len;
            int sour = edge.node1;
            int targ = edge.node2;
            edges.add(new Edge(sour, targ, dis));
        }

        Connectivity conn = new Connectivity(GraphConstants.MST, edges);
        return conn;
    }

}
