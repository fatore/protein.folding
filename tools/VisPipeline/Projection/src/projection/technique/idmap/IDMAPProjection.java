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
package projection.technique.idmap;

import java.io.IOException;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;
import projection.technique.fastmap.FastmapProjection;
import projection.technique.force.ForceSchemeProjection2D;
import projection.technique.nnp.NearestNeighborProjection;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class IDMAPProjection implements Projection {

    public enum InitializationType {

        FASTMAP, NNP, RANDOM
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        return project(dmat);
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        AbstractMatrix projection = null;

        if (ini == InitializationType.FASTMAP) {
            FastmapProjection fastmap = new FastmapProjection();
            projection = fastmap.project(dmat);
        } else if (ini == InitializationType.RANDOM) {
            int pivot1 = (int) ((dmat.getElementCount() - 1) * Math.random());
            int pivot2 = (int) ((dmat.getElementCount() - 1) * Math.random());

            projection = new DenseMatrix();
            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("x");
            attributes.add("y");
            projection.setAttributes(attributes);

            ArrayList<Integer> ids = dmat.getIds();
            ArrayList<String> labels = dmat.getLabels();
            float[] cdata = dmat.getClassData();

            for (int i = 0; i < dmat.getElementCount(); i++) {
                float[] point = new float[2];
                point[0] = dmat.getDistance(i, pivot1);
                point[1] = dmat.getDistance(i, pivot2);
                AbstractVector vector = new DenseVector(point, ids.get(i), cdata[i]);

                if (labels.isEmpty()) {
                    projection.addRow(vector);
                } else {
                    projection.addRow(vector, labels.get(i));
                }
            }
        } else {
            NearestNeighborProjection nnp = new NearestNeighborProjection();
            projection = nnp.project(dmat);
        }

        ForceSchemeProjection2D force = new ForceSchemeProjection2D();
        force.setFractionDelta(fracdelta);
        force.setNumberIterations(nriterations);
        force.setProjection(projection);
        projection = force.project(dmat);

        return projection;
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
    private InitializationType ini = InitializationType.FASTMAP;
    private float fracdelta = 8.0f;
    private int nriterations = 50;
}
