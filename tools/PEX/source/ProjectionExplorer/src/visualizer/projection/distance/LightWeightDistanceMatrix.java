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

package visualizer.projection.distance;

import java.io.IOException;
import java.util.ArrayList;
import visualizer.matrix.Matrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class LightWeightDistanceMatrix extends DistanceMatrix {

    public LightWeightDistanceMatrix(Matrix matrix, Dissimilarity diss) {
        this.matrix = matrix;
        this.diss = diss;

        this.ids = new ArrayList<String>();
        this.cdata = new float[matrix.getRowCount()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            this.ids.add(matrix.getRow(i).getId());
            this.cdata[i] = matrix.getRow(i).getKlass();
        }

        this.distmatrix = null;
        this.nrElements = matrix.getRowCount();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new LightWeightDistanceMatrix(matrix, diss);
    }

    @Override
    public float getDistance(int indexA, int indexB) {
        assert (indexA >= 0 && indexA < nrElements && indexB >= 0 && indexB < nrElements) :
                "ERROR: index out of bounds!";

        return this.diss.calculate(this.matrix.getRow(indexA), this.matrix.getRow(indexB));
    }

    @Override
    public float getMaxDistance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getMinDistance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void load(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setClassData(float[] cdata) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDistance(int indexA, int indexB, float value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setIds(ArrayList<String> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Matrix matrix;
    private Dissimilarity diss;
}
