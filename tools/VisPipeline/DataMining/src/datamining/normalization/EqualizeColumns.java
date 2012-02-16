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

package datamining.normalization;

import java.io.IOException;
import java.util.Arrays;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.sparse.SparseMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class EqualizeColumns extends AbstractNormalization {

    @Override
    public AbstractMatrix execute(AbstractMatrix matrix) throws IOException {
        assert (matrix.getRowCount() > 0) : "More than zero vectors must be used!";

        float[][] points = null;

        if (matrix instanceof DenseMatrix) {
            points = new float[matrix.getRowCount()][];

            for (int i = 0; i < points.length; i++) {
                points[i] = matrix.getRow(i).getValues();
                matrix.getRow(i).shouldUpdateNorm();
            }
        } else {
            points = matrix.toMatrix();
        }

        //for each column
        for (int j = 0; j < matrix.getDimensions(); j++) {
            float[] hist = new float[EqualizeColumns.nrbins];
            Arrays.fill(hist, 0.0f);

            //find the maximum and minimum values            
            float min = Float.POSITIVE_INFINITY;
            float max = Float.NEGATIVE_INFINITY;

            for (int i = 0; i < points.length; i++) {
                if (points[i][j] > max) {
                    max = points[i][j];
                }

                if (points[i][j] < min) {
                    min = points[i][j];
                }
            }

            //creating the histogram
            for (int i = 0; i < points.length; i++) {
                if (max > min) {
                    int index = (int) (((points[i][j] - min) / (max - min)) * (EqualizeColumns.nrbins - 1));
                    hist[index] = hist[index] + 1;
                } else {
                    hist[0] = hist[0] + 1;
                }
            }

            //compute the cumulative histogram
            hist[0] = hist[0] / points.length;
            for (int i = 1; i < hist.length; i++) {
                hist[i] = hist[i - 1] + hist[i] / points.length;
            }

            //transformnig the values based on the new histogram
            for (int i = 0; i < points.length; i++) {
                if (max > min) {
                    int index = (int) (((points[i][j] - min) / (max - min)) * (EqualizeColumns.nrbins - 1));
                    points[i][j] = (hist[index] * (max - min)) + min;
                } else {
                    points[i][j] = 0.0f;
                }
            }
        }

        if (matrix instanceof SparseMatrix) {
            AbstractMatrix eqmatrix = new DenseMatrix();
            eqmatrix.setAttributes(matrix.getAttributes());

            for (int i = 0; i < matrix.getRowCount(); i++) {
                AbstractVector oldv = matrix.getRow(i);
                eqmatrix.addRow(new DenseVector(points[i], oldv.getId(), oldv.getKlass()));
            }

            return eqmatrix;
        } else {
            return matrix;
        }
    }

    private static final int nrbins = 1000;
}
