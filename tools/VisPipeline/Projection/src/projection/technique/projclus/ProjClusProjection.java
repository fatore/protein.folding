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
package projection.technique.projclus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import distance.DistanceMatrix;
import projection.technique.Projection;
import datamining.clustering.BKmeans;
import datamining.clustering.Kmedoids;
import distance.dissimilarity.AbstractDissimilarity;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.idmap.IDMAPProjection;
import projection.technique.idmap.IDMAPProjection.InitializationType;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ProjClusProjection implements Projection {

    public ProjClusProjection() {
        this.clustfactor = 4.5f;
        this.nriterations = 50;
        this.fracdelta = 8.0f;
        this.ini = InitializationType.FASTMAP;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        ArrayList<AbstractMatrix> projections = new ArrayList<AbstractMatrix>();

        BKmeans bkmeans = new BKmeans((int) Math.sqrt(matrix.getRowCount()));
        this.clusters = bkmeans.execute(diss, matrix);
        AbstractMatrix centroids = bkmeans.getCentroids();

        AbstractMatrix pivotsProjection = this.createPivotsProjection(centroids, diss);

        for (int cluster = 0; cluster < clusters.size(); cluster++) {
            //Create a distance matrix
            DistanceMatrix dmat_aux = this.createDistanceMatrix(matrix, clusters.get(cluster), diss);

            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setInitialization(ini);
            idmap.setFractionDelta(fracdelta);
            idmap.setNumberIterations(nriterations);
            AbstractMatrix projection_aux = idmap.project(dmat_aux);

            //Add the new projection to the list of projections
            projections.add(projection_aux);
        }

        //Armazena as maiores distancias ate o centroid
        float[] centroidMaxDistances = new float[clusters.size()];
        float overallMaxDistance = Float.MIN_VALUE;
        Arrays.fill(centroidMaxDistances, Float.MIN_VALUE);

        //Encontar as maiores distancias
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).size(); j++) {
                float distance = diss.calculate(centroids.getRow(i),
                        matrix.getRow(clusters.get(i).get(j)));
                if (distance > centroidMaxDistances[i]) {
                    centroidMaxDistances[i] = distance;
                }
                //Store the overall max distance
                if (distance > overallMaxDistance) {
                    overallMaxDistance = distance;
                }
            }
        }

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

        for (int i = 0; i < clusters.size(); i++) {
            AbstractMatrix p = projections.get(i);
            normalize(p, 0, centroidMaxDistances[i] / overallMaxDistance);

            //////////////////////////////////////////////////////////////////////
            //  1.2. somar a essas projecoes normalizadas as coordenadas X e Y
            //       dos centroides dos seus agrupamentos
            for (int j = 0; j < p.getRowCount(); j++) {
                float x = p.getRow(j).getValue(0) + (pivotsProjection.getRow(i).getValue(0) * clustfactor);
                float y = p.getRow(j).getValue(1) + (pivotsProjection.getRow(i).getValue(1) * clustfactor);

                p.getRow(j).setValue(0, x);
                p.getRow(j).setValue(1, y);
            }

            //////////////////////////////////////////////////////////////////////
            //2. remontar a projecao final
            for (int j = 0; j < p.getRowCount(); j++) {
                AbstractVector row = projection.getRow(clusters.get(i).get(j));
                row.setValue(0, p.getRow(j).getValue(0));
                row.setValue(1, p.getRow(j).getValue(1));
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Time to project using ProjClus: {0}s", (finish - start) / 1000.0f);

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        //The clusters projections
        ArrayList<AbstractMatrix> projections = new ArrayList<AbstractMatrix>();

        Kmedoids km = new Kmedoids((int) Math.sqrt(dmat.getElementCount()));
        this.clusters = km.execute(dmat);
        int[] medoids = km.getMedoids();

        //////////////////////////////////////////////////////////////////////////////////
        ArrayList<Integer> medoids_aux = new ArrayList<Integer>();
        for (int i = 0; i < medoids.length; i++) {
            medoids_aux.add(medoids[i]);
        }

        //Desfazer os agrupamentos pequenos, juntando-os ao seu agrupamento mais proximo
        for (int cluster = 0; cluster < clusters.size(); cluster++) {
            //se um cluster tiver menos de 4 elementos
            if (clusters.get(cluster).size() < 4) {
                //encontrar o medoid mais proximo do mesmo e unir os dois clusters
                int nearestMedoid = 0;
                float distance = dmat.getDistance(medoids_aux.get(cluster), medoids_aux.get(nearestMedoid));
                for (int m = 1; m < medoids_aux.size(); m++) {
                    if (cluster != m) {
                        float distance2 = dmat.getDistance(medoids_aux.get(cluster), medoids_aux.get(m));
                        if (distance2 < distance) {
                            distance = distance2;
                            nearestMedoid = m;
                        }
                    }
                }

                //unir os dois medoids
                for (int i = 0; i < clusters.get(cluster).size(); i++) {
                    clusters.get(nearestMedoid).add(clusters.get(cluster).get(i));
                }

                //remover o cluster com poucos elementos
                clusters.remove(cluster);
                medoids_aux.remove(cluster);
            }
        }
        medoids = new int[medoids_aux.size()];
        for (int i = 0; i < medoids_aux.size(); i++) {
            medoids[i] = medoids_aux.get(i);
        }

        //Positioning the pivots (centroids)
        AbstractMatrix pivotsProjection = createPivotsProjection(dmat, medoids);

        for (int cluster = 0; cluster < clusters.size(); cluster++) {
            //Create a distance matrix
            DistanceMatrix dmat_c = this.createDistanceMatrix(dmat, clusters.get(cluster));

            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setInitialization(ini);
            idmap.setFractionDelta(fracdelta);
            idmap.setNumberIterations(nriterations);
            AbstractMatrix projection_aux = idmap.project(dmat_c);

            //Add the new projection to the list of projections
            projections.add(projection_aux);
        }

        //Armazena as maiores distancias ate o medoid
        float[] medoidMaxDistances = new float[clusters.size()];
        float overallMaxDistance = Float.MIN_VALUE;
        Arrays.fill(medoidMaxDistances, Float.MIN_VALUE);

        //Encontar as maiores distancias
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).size(); j++) {
                float distance = dmat.getDistance(medoids[i], clusters.get(i).get(j));

                if (distance > medoidMaxDistances[i]) {
                    medoidMaxDistances[i] = distance;
                }
                //Store the overall max distance
                if (distance > overallMaxDistance) {
                    overallMaxDistance = distance;
                }
            }
        }

        AbstractMatrix projection = new DenseMatrix();
        for (int i = 0; i < dmat.getElementCount(); i++) {
            projection.addRow(new DenseVector(new float[]{0, 0}, dmat.getIds().get(i), dmat.getClassData()[i]), dmat.getLabels().get(i));
        }

        for (int i = 0; i < clusters.size(); i++) {
            AbstractMatrix p = projections.get(i);
            normalize(p, 0, medoidMaxDistances[i] / overallMaxDistance);

            //////////////////////////////////////////////////////////////////////
            //  1.2. somar a essas projecoes normalizadas as coordenadas X e Y
            //       dos centroides dos seus agrupamentos
            for (int j = 0; j < p.getRowCount(); j++) {
                float x = p.getRow(j).getValue(0) + (pivotsProjection.getRow(i).getValue(0) * clustfactor);
                float y = p.getRow(j).getValue(1) + (pivotsProjection.getRow(i).getValue(1) * clustfactor);

                p.getRow(j).setValue(0, x);
                p.getRow(j).setValue(1, y);
            }

            //////////////////////////////////////////////////////////////////////
            //2. remontar a projecao final
            for (int j = 0; j < p.getRowCount(); j++) {
                AbstractVector row = projection.getRow(clusters.get(i).get(j));
                row.setValue(0, p.getRow(j).getValue(0));
                row.setValue(1, p.getRow(j).getValue(1));
            }
        }

        return projection;
    }

    /**
     * @return the clustfactor
     */
    public float getClusterFactor() {
        return clustfactor;
    }

    /**
     * @param clustfactor the clustfactor to set
     */
    public void setClusterFactor(float clustfactor) {
        this.clustfactor = clustfactor;
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
     * @return the ini
     */
    public InitializationType getInitialization() {
        return ini;
    }

    /**
     * @param ini the ini to set
     */
    public void setInitialization(InitializationType ini) {
        this.ini = ini;
    }

    private void normalize(AbstractMatrix projection, float begin, float end) {
        float maxX = projection.getRow(0).getValue(0);
        float minX = projection.getRow(0).getValue(0);
        float maxY = projection.getRow(0).getValue(0);
        float minY = projection.getRow(0).getValue(1);

        for (int i = 1; i < projection.getRowCount(); i++) {
            if (minX > projection.getRow(i).getValue(0)) {
                minX = projection.getRow(i).getValue(0);
            } else if (maxX < projection.getRow(i).getValue(0)) {
                maxX = projection.getRow(i).getValue(0);
            }

            if (minY > projection.getRow(i).getValue(1)) {
                minY = projection.getRow(i).getValue(1);
            } else if (maxY < projection.getRow(i).getValue(1)) {
                maxY = projection.getRow(i).getValue(1);
            }
        }

        float endY = (maxY - minY) / (maxX - minX);
        //float endY = end;

        //for each position in the ArrayList ... normalize!
        for (int i = 0; i < projection.getRowCount(); i++) {
            if (maxX - minX > 0.0) {
                float x = (projection.getRow(i).getValue(0) - minX) / (maxX - minX);
                projection.getRow(i).setValue(0, x);
            } else {
                projection.getRow(i).setValue(0, 0);
            }

            if (maxY - minY > 0.0) {
                float y = (projection.getRow(i).getValue(1) - minY) / ((maxY - minY) * endY);
                projection.getRow(i).setValue(1, y);
            } else {
                projection.getRow(i).setValue(1, 0);
            }
        }
    }

    private AbstractMatrix createPivotsProjection(AbstractMatrix pivots, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(pivots, diss);

        IDMAPProjection idmap = new IDMAPProjection();
        idmap.setInitialization(ini);
        idmap.setFractionDelta(fracdelta);
        idmap.setNumberIterations(nriterations);
        AbstractMatrix projection = idmap.project(dmat);

        normalize(projection, 0, 1);

        return projection;
    }

    private DistanceMatrix createDistanceMatrix(AbstractMatrix matrix,
            ArrayList<Integer> cluster, AbstractDissimilarity diss) throws IOException {
        //Create the new points matrix
        AbstractMatrix nMatrix = MatrixFactory.getInstance(matrix.getClass());

        for (int i = 0; i < cluster.size(); i++) {
            nMatrix.addRow(matrix.getRow(cluster.get(i)));
        }

        return new DistanceMatrix(nMatrix, diss);
    }

    private DistanceMatrix createDistanceMatrix(DistanceMatrix dmat, ArrayList<Integer> cluster) {
        DistanceMatrix dmat_c = null;

        //Creating the pivots distance matrix
        dmat_c = new DistanceMatrix(cluster.size());

        for (int i = 0; i < cluster.size() - 1; i++) {
            for (int j = cluster.size() - 1; j > i; j--) {
                dmat_c.setDistance(i, j, dmat.getDistance(cluster.get(i), cluster.get(j)));
            }
        }

        return dmat_c;
    }

    private AbstractMatrix createPivotsProjection(DistanceMatrix dmat, int[] medoids) throws IOException {
        //Creating the pivots distance matrix
        DistanceMatrix dmat_p = new DistanceMatrix(medoids.length);

        for (int i = 0; i < medoids.length; i++) {
            for (int j = medoids.length - 1; j > i; j--) {
                dmat_p.setDistance(i, j, dmat.getDistance(medoids[i], medoids[j]));
            }
        }

        IDMAPProjection idmap = new IDMAPProjection();
        idmap.setInitialization(ini);
        idmap.setFractionDelta(fracdelta);
        idmap.setNumberIterations(nriterations);
        AbstractMatrix projection = idmap.project(dmat_p);

        normalize(projection, 0, 1);

        return projection;
    }
    
    private float clustfactor;
    private int nriterations;
    private float fracdelta;
    private InitializationType ini;
    private ArrayList<ArrayList<Integer>> clusters;
}
