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

package projection3d.clustering;

import datamining.clustering.Clustering;
import java.io.IOException;
import java.util.ArrayList;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.util.HashMap;
import java.util.Map;
import matrix.AbstractMatrix;
/**
 *
 * @author Fernando Vieira Paulovich
 * @modified Jorge Poco
 */
public class HierarchicalClustering extends Clustering {

    public enum HierarchicalClusteringType {

        SLINK, CLINK, ALINK
    }

    /** Creates a new instance of HierarchicalClustering
     * @param nrclusters
     * @param type 
     */
    public HierarchicalClustering(int nrclusters, HierarchicalClusteringType type) {
        super(nrclusters);
        this.type = type;
    }

    public HierarchicalClustering(HierarchicalClusteringType type) {
        super(0);
        this.type = type;
    }

    @Override
    public ArrayList<ArrayList<Integer>> execute(AbstractDissimilarity diss, AbstractMatrix matrix) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        return this.execute(dmat);
    }

    @Override
    public ArrayList<ArrayList<Integer>> execute(DistanceMatrix dmat) throws IOException {
        this.init(dmat);

        //Inicialmente todos os pontos devem ser clusters unitários
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < this.distances.length; i++) {
            clusters.add(new ArrayList<Integer>());
            clusters.get(i).add(i);
        }

        //Forma os clusters até alcançar o número pré-determinado
        while (clusters.size() > nrclusters) {
            int[] cmin = this.joinNearestClusters(clusters);

            //Faço o update da matriz de distâncias
            this.updateDistanceMatrix(cmin);
        }

        return clusters;
    }

    public float[] getPointsHeight(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        this.init(dmat);

        //Inicialmente todos os pontos devem ser clusters unitários
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();

        float[] height = new float[this.distances.length];

        for (int i = 0; i < this.distances.length; i++) {
            clusters.add(new ArrayList<Integer>());
            clusters.get(i).add(i);
            height[i] = 0.0f;
        }

        //Forma os clusters até alcançar o número pré-determinado
        while (clusters.size() > 1) {
            int[] cmin = this.joinNearestClusters(clusters);

            //Armazeno a distância entre os clusters
            for (int i = 0; i < clusters.get(cmin[0]).size(); i++) {
                height[clusters.get(cmin[0]).get(i)] += 1;
            }

            //Faço o update da matriz de distâncias
            this.updateDistanceMatrix(cmin);
        }

        return height;
    }

    public HierarchicalClusterNode getHierarchicalTree(AbstractMatrix matrix, AbstractDissimilarity diss)
            throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        this.init(dmat);
        
        
        Map<Integer, HierarchicalClusterNode> clusterMap = new HashMap<Integer, HierarchicalClusterNode>(matrix.getRowCount());
        clusterIds = new int[matrix.getRowCount()];
        isDeleted = new boolean[matrix.getRowCount()];

        //Inicialmente todos os pontos devem ser clusters unitários
        int nextClusterId = 0;
        for (int i = 0; i < this.distances.length; i++) {
            clusterIds[i] = nextClusterId;
            isDeleted[i] = false;
            clusterMap.put(nextClusterId, new HierarchicalClusterLeafNode(nextClusterId, matrix.getRow(i).getId()));
            nextClusterId++;
        }

        //Forma os clusters até alcançar o número pré-determinado
        while (clusterMap.size() > 1) {
            int[] cmin = this.joinNearestClusters(nextClusterId, clusterMap);

            //Faço o update da matriz de distâncias
            this.updateDistanceMatrix(cmin);
            clusterIds[cmin[0]] = nextClusterId;
            nextClusterId++;
        }

        //Should return the root node.
        return clusterMap.entrySet().iterator().next().getValue();
    }

    private void init(DistanceMatrix dmat) throws IOException {
        //Cria a matriz de distâncias
        distances = new float[dmat.getElementCount()][dmat.getElementCount()];
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = 0; j < dmat.getElementCount(); j++) {
                if (i == j) {
                    this.distances[i][j] = 0.0f;
                } else {
                    float distance = dmat.getDistance(i, j);
                    this.distances[i][j] = distance;
                    this.distances[j][i] = distance;
                }
            }
        }
    }

    private int[] joinNearestClusters(ArrayList<ArrayList<Integer>> clusters) {
        float minDistance = Float.POSITIVE_INFINITY;
        int[] cmin = new int[2];
        cmin[0] = -1;
        cmin[1] = -1;

        //Procurar os clusters mais próximos c1 e c2
        for (int c1 = 0; c1 < distances.length; c1++) {
            if (!isDeleted[c1]) {
                for (int c2 = c1 + 1; c2 < distances.length; c2++) {
                    if (!isDeleted[c2]) {
                        if (minDistance > distances[c1][c2]) {
                            minDistance = distances[c1][c2];
                            cmin[0] = c1;
                            cmin[1] = c2;
                        }
                    }
                }
            }
        }


        //Agrupe os dois clusters mais próximos: c1min e c2min
        //coloco dentro de c1min o c2min
        for (int i = 0; i < clusters.get(cmin[1]).size(); i++) {
            clusters.get(cmin[0]).add(clusters.get(cmin[1]).get(i));
        }

        //apago c2min
        clusters.remove(cmin[1]);

        return cmin;
    }

    private int[] joinNearestClusters(int nextClusterId, Map<Integer, HierarchicalClusterNode> clusterMap) {
        float minDistance = Float.POSITIVE_INFINITY;
        int[] cmin = new int[2];
        cmin[0] = -1;
        cmin[1] = -1;

        //Procurar os clusters mais próximos c1 e c2
        for (int c1 = 0; c1 < distances.length; c1++) {
            if (!isDeleted[c1]) {
                for (int c2 = c1 + 1; c2 < distances.length; c2++) {
                    if (!isDeleted[c2]) {
                        if (minDistance > distances[c1][c2]) {
                            minDistance = distances[c1][c2];
                            cmin[0] = c1;
                            cmin[1] = c2;
                        }
                    }
                }
            }
        }

        HierarchicalClusterNode newNode = new HierarchicalClusterNode(nextClusterId, minDistance);
        newNode.addSubNode(clusterMap.get(clusterIds[cmin[0]]));
        newNode.addSubNode(clusterMap.get(clusterIds[cmin[1]]));

        clusterMap.remove(clusterIds[cmin[0]]);
        clusterMap.remove(clusterIds[cmin[1]]);
        clusterMap.put(nextClusterId, newNode);

        return cmin;
    }


    private void updateDistanceMatrix(int[] cmin) {
        if (this.type.equals(HierarchicalClusteringType.SLINK)) {
            //Linha: copia a menor/maior distância entre os clusters c1min e c2min para o cluster c1min
            for (int i = 0; i < distances[0].length; i++) {
                if (distances[cmin[0]][i] < distances[cmin[1]][i]) {
                    distances[cmin[0]][i] = distances[cmin[0]][i];
                } else {
                    distances[cmin[0]][i] = distances[cmin[1]][i];
                }
                if (distances[i][cmin[0]] < distances[i][cmin[1]]) {
                    distances[i][cmin[0]] = distances[i][cmin[0]];
                } else {
                    distances[i][cmin[0]] = distances[i][cmin[1]];
                }
            }
        } else if (this.type.equals(HierarchicalClusteringType.CLINK)) {
            //Linha: copia a menor/maior distância entre os clusters c1min e c2min para o cluster c1min
            for (int i = 0; i < distances[0].length; i++) {
                if (distances[cmin[0]][i] > distances[cmin[1]][i]) {
                    distances[cmin[0]][i] = distances[cmin[0]][i];
                } else {
                    distances[cmin[0]][i] = distances[cmin[1]][i];
                }

                if (distances[i][cmin[0]] > distances[i][cmin[1]]) {
                    distances[i][cmin[0]] = distances[i][cmin[0]];
                } else {
                    distances[i][cmin[0]] = distances[i][cmin[1]];
                }
            }
        } else if (this.type.equals(HierarchicalClusteringType.ALINK)) {
            //Linha: copia a menor/maior distância entre os clusters c1min e c2min para o cluster c1min
            for (int i = 0; i < distances[0].length; i++) {
                distances[cmin[0]][i] = (distances[cmin[0]][i] * 2 + distances[cmin[1]][i]) / 2;

                distances[i][cmin[0]] = (distances[i][cmin[0]] * 2 + distances[i][cmin[1]]) / 2;
            }
        }
        //Apago a linha das distâncias do cluster c2min
        isDeleted[cmin[1]] = true;
    }

    private HierarchicalClusteringType type = HierarchicalClusteringType.CLINK;
    private float distances[][];
    private boolean isDeleted[];
    private int clusterIds[];
}
