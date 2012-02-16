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

package datamining.clustering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.util.MatrixUtils;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Kmeans extends Clustering {

    public Kmeans(int nrclusters) {
        super(nrclusters);
    }

    @Override
    public ArrayList<ArrayList<Integer>> execute(AbstractDissimilarity diss,
            AbstractMatrix matrix) throws IOException {
        try {
            //choose some points as the initial centroids
            centroids = MatrixFactory.getInstance(matrix.getClass());
            int slop = (matrix.getRowCount() - 1) / nrclusters;
            for (int i = 0; i < nrclusters; i++) {
                centroids.addRow((AbstractVector) matrix.getRow(i * slop).clone());
            }

            for (int it = 0; it < nriterations; it++) {
                clusters.clear();

                for (int i = 0; i < nrclusters; i++) {
                    clusters.add(new ArrayList<Integer>());
                }

                //assign each point to its nearest cluster
                for (int point = 0; point < matrix.getRowCount(); point++) {
                    int nearest = 0;
                    float mindist = Float.MAX_VALUE;

                    //find the nearest cluster
                    for (int cluster = 0; cluster < nrclusters; cluster++) {
                        float dist = diss.calculate(matrix.getRow(point), centroids.getRow(cluster));

                        if (mindist > dist) {
                            nearest = cluster;
                            mindist = dist;
                        }
                    }

                    //assign the point
                    clusters.get(nearest).add(point);
                }

                //check if any cluster is empty
                for (int i = 0; i < clusters.size(); i++) {
                    //if a cluster is empty, randomly assign a point
                    if (clusters.get(i).size() == 0) {
                        int index = (int) (Math.random() * (matrix.getRowCount() - 1));
                        centroids.setRow(i, (AbstractVector) matrix.getRow(index).clone());
                        clusters.get(i).add(index);
                    }
                }

                //update the centroids
                updateCentroids(matrix);
            }

            //eliminate empty clusters        
            for (int i = clusters.size() - 1; i >= 0; i--) {
                if (clusters.get(i).size() == 0) {
                    clusters.remove(i);
                    centroids.removeRow(i);
                }
            }

            return clusters;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> execute(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AbstractMatrix getCentroids() {
        return centroids;
    }

    public int[] getMedoids(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        int[] m = new int[this.centroids.getRowCount()];

        for (int i = 0; i < m.length; i++) {
            int point = -1;
            float distance = Float.MAX_VALUE;

            for (int j = 0; j < clusters.get(i).size(); j++) {
                float distance2 = diss.calculate(centroids.getRow(i), matrix.getRow(clusters.get(i).get(j)));

                if (distance > distance2) {
                    point = this.clusters.get(i).get(j);
                    distance = distance2;
                }
            }

            m[i] = point;
        }

        return m;
    }

    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    private void updateCentroids(AbstractMatrix matrix) throws IOException {
        //clean the centroids
        centroids = MatrixFactory.getInstance(matrix.getClass());

        //for each cluster
        for (int cluster = 0; cluster < nrclusters; cluster++) {
            AbstractMatrix vectors = MatrixFactory.getInstance(matrix.getClass());

            //for each point inside a cluster
            for (int el = 0; el < clusters.get(cluster).size(); el++) {
                vectors.addRow(matrix.getRow(clusters.get(cluster).get(el)));
            }

            AbstractVector centroid = MatrixUtils.mean(vectors);
            centroids.addRow(centroid);
        }
    }

    private ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
    private AbstractMatrix centroids;
    private int nriterations = 10;//control the number of iterations
}
