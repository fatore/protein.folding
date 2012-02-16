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
package projection.technique.force;

import java.io.IOException;
import java.util.ArrayList;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ForceSchemeProjection2D implements Projection {

    public ForceSchemeProjection2D() {
        this.nriterations = 50;
        this.fractionDelta = 8.0f;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        return project(dmat);
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        normfactor = 0;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = 0; j < i; j++) {
                normfactor += dmat.getDistance(i, j) * dmat.getDistance(i, j);
            }
        }

        //Create the indexes and shuffle them
        ArrayList<Integer> index_aux = new ArrayList<Integer>();
        for (int i = 0; i < dmat.getElementCount(); i++) {
            index_aux.add(i);
        }

        this.index = new int[dmat.getElementCount()];
        for (int ind = 0, j = 0; j < this.index.length; ind += index_aux.size() / 10, j++) {
            if (ind >= index_aux.size()) {
                ind = 0;
            }

            this.index[j] = index_aux.get(ind);
            index_aux.remove(ind);
        }

        //if an input aux_proj is not provided, create one
        if (projection == null) {
            projection = new DenseMatrix();

            ArrayList<Integer> ids = dmat.getIds();
            float[] classData = dmat.getClassData();
            ArrayList<String> labels = dmat.getLabels();


            for (int i = 0; i < dmat.getElementCount(); i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random();
                point[1] = (float) Math.random();

                if (labels.isEmpty()) {
                    projection.addRow(new DenseVector(point, ids.get(i), classData[i]));
                } else {
                    projection.addRow(new DenseVector(point, ids.get(i), classData[i]), labels.get(i));
                }
            }
        }

        float[][] aux_proj = projection.toMatrix();

        for (int i = 0; i < nriterations; i++) {
            iteration(dmat, aux_proj);
        }

        for (int i = 0; i < aux_proj.length; i++) {
            projection.getRow(i).setValue(0, aux_proj[i][0]);
            projection.getRow(i).setValue(1, aux_proj[i][1]);
        }

        return projection;
    }

    private void iteration(DistanceMatrix dmat, float[][] aux_proj) {
        //for each instance
        for (int ins1 = 0; ins1 < aux_proj.length; ins1++) {
            int instance = this.index[ins1];

            //for each other instance
            for (int ins2 = 0; ins2 < aux_proj.length; ins2++) {
                int instance2 = this.index[ins2];

                if (instance == instance2) {
                    continue;
                }

                //distance between projected instances
                double x1x2 = (aux_proj[instance2][0] - aux_proj[instance][0]);
                double y1y2 = (aux_proj[instance2][1] - aux_proj[instance][1]);
                double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);

                if (dr2 < EPSILON) {
                    dr2 = EPSILON;
                }

                float drn = dmat.getDistance(instance, instance2);
                //float normdrn = drn;//(drn - dmat.getMinDistance()) /
                //(dmat.getMaxDistance() - dmat.getMinDistance());

                //Calculating the (fraction of) delta
//                double delta = Math.sqrt(normdrn) - Math.sqrt(dr2);
//                delta *= Math.abs(delta);
//                delta /= this.fractionDelta;

                //Calculating the (fraction of) delta
                double delta = drn - dr2;
                //delta *= Math.abs(delta);
                //delta /= normfactor;
                //delta = (delta > 0) ? Math.sqrt(delta) : -Math.sqrt(Math.abs(delta));
                delta /= this.fractionDelta;

                //moving ins2 -> ins1
                aux_proj[instance2][0] += delta * (x1x2 / dr2);
                aux_proj[instance2][1] += delta * (y1y2 / dr2);

                if (aux_proj[instance2][0] != aux_proj[instance2][0]) {
                    System.out.println("Error: Force Scheme >> delta" + delta + ", x1x2=" + x1x2 + ", dr2=" + dr2 + ", drn=" + drn);
                }
            }
        }
    }

    /**
     * @param fractionDelta the fractionDelta to set
     */
    public void setFractionDelta(float fractionDelta) {
        this.fractionDelta = fractionDelta;
    }

    /**
     * @param nriterations the nriterations to set
     */
    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    /**
     * @param aux_proj the aux_proj to set
     */
    public void setProjection(AbstractMatrix projection) {
        this.projection = projection;
    }
    private double normfactor;
    private float fractionDelta;
    private int[] index;
    private int nriterations;
    private AbstractMatrix projection;
    private static final float EPSILON = 0.0000001f;
}
