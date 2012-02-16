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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.matrix.MatrixUtils;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.util.Pair;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ConnectedGraphGenerator {

    public Pair[][] execute(Pair[][] neighbors, Matrix matrix, Dissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        //completing the neighborhood
        for (int i = 0; i < neighbors.length; i++) {
            for (int j = 0; j < neighbors[i].length; j++) {
                boolean contain = false;

                for (int k = 0; k < neighbors[neighbors[i][j].index].length; k++) {
                    if (neighbors[neighbors[i][j].index][k].index == i) {
                        contain = true;
                        break;
                    }
                }

                if (!contain) {
                    Pair[] newneighbors = new Pair[neighbors[neighbors[i][j].index].length + 1];

                    //copying the previous neighbors
                    for (int k = 0; k < newneighbors.length - 1; k++) {
                        newneighbors[k] = neighbors[neighbors[i][j].index][k];
                    }

                    //adding the new neighbor and setting the new neighborhood
                    newneighbors[newneighbors.length - 1] = new Pair(i, neighbors[i][j].value);
                    neighbors[neighbors[i][j].index] = newneighbors;
                }
            }
        }

        //getting the conncted components
        ArrayList<ArrayList<Integer>> components = getConnectedComponents(neighbors);

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Number of components on the NNG: " + components.size());

        //joining all components
        if (components.size() > 1) {
            joinComponents(matrix, diss, neighbors, components);
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Connecting components time: " + (finish - start) / 1000.0f + "s");

        return neighbors;
    }

    public Pair[][] execute(Pair[][] neighbors, DistanceMatrix dmat) throws IOException {
        return null;
    }

    /**
     * Join the components in order to reach a connected graph.
     * @param matrix The points matrix.
     * @param diss The dissimilarity.
     * @param neighbors The nearest neighbors list (it can change)
     * @param components The components which compose the graph.
     * @return A new connected graph.
     * @throws IOException Throws an exception if something goes wrong.
     */
    private void joinComponents(Matrix matrix, Dissimilarity diss, Pair[][] neighbors,
            ArrayList<ArrayList<Integer>> components) throws IOException {

        //store the centroid of each component
        Matrix centroids = MatrixFactory.getInstance(matrix.getClass());

        //calculating the centroids
        for (ArrayList<Integer> component : components) {
            Matrix aux = MatrixFactory.getInstance(matrix.getClass());

            for (Integer node : component) {
                aux.addRow(matrix.getRow(node));
            }

            centroids.addRow(MatrixUtils.mean(aux));
        }

        //joining all components
        for (int i = 0; i < components.size(); i++) {
            //finding the nearest component
            float dist = Float.POSITIVE_INFINITY;
            int nearest = -1;

            for (int j = i + 1; j < components.size(); j++) {
                float aux = diss.calculate(centroids.getRow(i), centroids.getRow(j));

                if (aux < dist) {
                    dist = aux;
                    nearest = j;
                }
            }

            if (nearest > -1) {
                int node1 = -1;
                int node2 = -1;

                //find the nearest point of component i to the centroid of nearest
                dist = Float.POSITIVE_INFINITY;
                for (int n = 0; n < components.get(i).size(); n++) {
                    float aux = diss.calculate(matrix.getRow(components.get(i).get(n)),
                            centroids.getRow(nearest));

                    if (aux < dist) {
                        dist = aux;
                        node1 = components.get(i).get(n);
                    }
                }

                //find the nearest point of component i to the centroid of nearest
                dist = Float.POSITIVE_INFINITY;
                for (int n = 0; n < components.get(nearest).size(); n++) {
                    float aux = diss.calculate(matrix.getRow(components.get(nearest).get(n)),
                            centroids.getRow(i));

                    if (aux < dist) {
                        dist = aux;
                        node2 = components.get(nearest).get(n);
                    }
                }

                //adding on the neighbors list
                Pair[] newNeighbors1 = new Pair[neighbors[node1].length + 1];
                for (int j = 0; j < neighbors[node1].length; j++) {
                    newNeighbors1[j] = neighbors[node1][j];
                }

                newNeighbors1[neighbors[node1].length] = new Pair(node2, dist);
                neighbors[node1] = newNeighbors1;

                Pair[] newNeighbors2 = new Pair[neighbors[node2].length + 1];
                for (int j = 0; j < neighbors[node2].length; j++) {
                    newNeighbors2[j] = neighbors[node2][j];
                }

                newNeighbors2[neighbors[node2].length] = new Pair(node1, dist);
                neighbors[node2] = newNeighbors2;
            }
        }
    }

    /**
     * Return the connected components of neighborhood graph.
     * @param neighbors The k-nearest neighbors of each point.
     * @return The conncted components.
     */
    private ArrayList<ArrayList<Integer>> getConnectedComponents(Pair[][] neighbors) {
        long start = System.currentTimeMillis();

        ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> component = new ArrayList<Integer>();

        //ensuring that the graph is entirely connected
        boolean[] visited = new boolean[neighbors.length];
        boolean[] notvisited = new boolean[neighbors.length];
        boolean[] tovisit = new boolean[neighbors.length];
        ArrayList<Integer> tovisitindex = new ArrayList<Integer>();

        int notvisitedcount = neighbors.length;
        int notvisitedindex = 0;

        tovisit[0] = true;
        tovisitindex.add(0);

        Arrays.fill(visited, false);
        Arrays.fill(notvisited, true);
        Arrays.fill(tovisit, false);

        while (notvisitedcount > 0) {
            if (tovisitindex.size() > 0) {
                int next = tovisitindex.remove(0);
                visited[next] = true;
                notvisited[next] = false;
                notvisitedcount--;

                for (int i = 0; i < neighbors[next].length; i++) {
                    if (!visited[neighbors[next][i].index]) {
                        if (!tovisit[neighbors[next][i].index]) {
                            tovisitindex.add(neighbors[next][i].index);
                            tovisit[neighbors[next][i].index] = true;
                        }
                    }
                }

                //adding the point to the component
                component.add(next);
            } else {
                //find the next not visited element
                while (!notvisited[notvisitedindex]) {
                    notvisitedindex++;
                }

                int next = notvisitedindex;
                notvisited[next] = false;
                notvisitedcount--;
                tovisitindex.add(next);
                tovisit[next] = true;

                //adding the component
                components.add(component);

                //creating a new component
                component = new ArrayList<Integer>();
            }
        }

        if (component.size() > 0) {
            components.add(component);
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Getting components: " + (finish - start) / 1000.0f + "s");

        return components;
    }

}
