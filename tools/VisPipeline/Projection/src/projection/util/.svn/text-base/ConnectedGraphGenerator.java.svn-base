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

package projection.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import datamining.neighbors.Pair;
import distance.dissimilarity.AbstractDissimilarity;
import java.util.HashSet;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ConnectedGraphGenerator {

    public void execute(Pair[][] neighborhood, AbstractMatrix matrix, AbstractDissimilarity diss) {
        long start = System.currentTimeMillis();

        //completing the neighborhood
        for (int i = 0; i < neighborhood.length; i++) {
            for (int j = 0; j < neighborhood[i].length; j++) {
                boolean contain = false;

                for (int k = 0; k < neighborhood[neighborhood[i][j].index].length; k++) {
                    if (neighborhood[neighborhood[i][j].index][k].index == i) {
                        contain = true;
                        break;
                    }
                }

                if (!contain) {
                    Pair[] newneighbors = new Pair[neighborhood[neighborhood[i][j].index].length + 1];

                    //copying the previous neighbors
                    for (int k = 0; k < newneighbors.length - 1; k++) {
                        newneighbors[k] = neighborhood[neighborhood[i][j].index][k];
                    }

                    //adding the new neighbor and setting the new neighborhood
                    newneighbors[newneighbors.length - 1] = new Pair(i, neighborhood[i][j].value);
                    neighborhood[neighborhood[i][j].index] = newneighbors;
                }
            }
        }

        int nrcomponents = 0;
        HashSet<Integer> visited = new HashSet<Integer>();

        while (visited.size() < neighborhood.length) {
            //get the next seed
            int seed = 0;
            for (int i = 0; i < neighborhood.length; i++) {
                if (!visited.contains(i)) {
                    seed = i;
                    break;
                }
            }

            //if there is some visited node, create a new link from the new seed
            //and some visited node
            if (visited.size() > 0) {
                Integer node = visited.iterator().next();
                float dist = diss.calculate(matrix.getRow(node), matrix.getRow(seed));

                Pair[] newNeighbors1 = new Pair[neighborhood[node].length + 1];
                for (int j = 0; j < neighborhood[node].length; j++) {
                    newNeighbors1[j] = neighborhood[node][j];
                }
                newNeighbors1[neighborhood[node].length] = new Pair(seed, dist);
                neighborhood[node] = newNeighbors1;

                Pair[] newNeighbors2 = new Pair[neighborhood[seed].length + 1];
                for (int j = 0; j < neighborhood[seed].length; j++) {
                    newNeighbors2[j] = neighborhood[seed][j];
                }
                newNeighbors2[neighborhood[seed].length] = new Pair(node, dist);
                neighborhood[seed] = newNeighbors2;
            }

            //get the patch starting from the seed
            getComponent(neighborhood, visited, seed);
            nrcomponents++;
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Number of components on the NNG: " + nrcomponents);

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Connecting components time: " + (finish - start) / 1000.0f + "s");
    }

    private void getComponent(Pair[][] neighborhood, HashSet<Integer> visited, int seed) {
        //init
        visited.add(seed);
        HashSet<Integer> tovisit = new HashSet<Integer>();
        for (int i = 0; i < neighborhood[seed].length; i++) {
            if (!visited.contains(neighborhood[seed][i].index)) {
                tovisit.add(neighborhood[seed][i].index);
            }
        }

        while (tovisit.size() > 0) {
            Integer[] nodes = tovisit.toArray(new Integer[0]);

            for (int i = 0; i < nodes.length; i++) {
                tovisit.remove(nodes[i]);
                visited.add(nodes[i]);

                for (int j = 0; j < neighborhood[nodes[i]].length; j++) {
                    if (!visited.contains(neighborhood[nodes[i]][j].index)) {
                        tovisit.add(neighborhood[nodes[i]][j].index);
                    }
                }
            }
        }
    }

}
