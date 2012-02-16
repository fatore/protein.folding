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
package projection.technique.mds;

import java.io.IOException;
import java.util.ArrayList;
import distance.DistanceMatrix;
import distance.LightWeightDistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import mdsj.ClassicalScaling;
import projection.model.ProjectionModelComp;
import projection.technique.Projection;
import projection.util.ProjectionUtil;
import projection.view.ProjectionFrameComp;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ClassicalMDSProjection implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return project(dmat);
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        int size = dmat.getElementCount();

        //creating the squared distance matrix
        double[][] input = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                input[i][j] = input[j][i] = dmat.getDistance(i, j);
            }
        }

        double[][] output = new double[2][size];
        ClassicalScaling.fullmds(input, output);

        input = null;
        System.gc();

        //creating the final projection
        AbstractMatrix projection = new DenseMatrix();
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("x");
        attributes.add("y");
        projection.setAttributes(attributes);

        ArrayList<Integer> ids = dmat.getIds();
        ArrayList<String> labels = dmat.getLabels();
        float[] classData = dmat.getClassData();

        for (int i = 0; i < size; i++) {
            float[] vect = new float[2];
            vect[0] = (float) output[0][i];
            vect[1] = (float) output[1][i];

            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]), labels.get(i));
            }
        }

        return projection;

//        //1. Compute the matrix of squared dissimilarities D^2.
//        DoubleMatrix2D D = new DenseDoubleMatrix2D(dmat.getElementCount(), dmat.getElementCount());
//
//        for (int i = 0; i < dmat.getElementCount() - 1; i++) {
//            for (int k = i + 1; k < dmat.getElementCount(); k++) {
//                D.setQuick(i, k, dmat.getDistance(i, k) * dmat.getDistance(i, k));
//                D.setQuick(k, i, dmat.getDistance(i, k) * dmat.getDistance(i, k));
//            }
//        }
//
//        //2. Apply double centering to this matrix: B = -1/2 * J * D^2 * J
//        //      J = I - n^-1 * 1 * 1T
//        DoubleMatrix2D J = DoubleFactory2D.dense.identity(dmat.getElementCount());
//        double value = 1.0 / dmat.getElementCount();
//
//        for (int i = 0; i < dmat.getElementCount(); i++) {
//            for (int k = 0; k < dmat.getElementCount(); k++) {
//                J.setQuick(i, k, J.getQuick(i, k) - value);
//            }
//        }
//
//        DoubleMatrix2D b = J.zMult(D, null, -0.5, 1.0, false, false).zMult(J, null, 1.0, 1.0, false, false);
//
//        //3. Compute the eigendecomposition of B = Q+A+.
//        EigenvalueDecomposition dec = new EigenvalueDecomposition(b);
//        DoubleMatrix2D eigenvectors = dec.getV();
//        DoubleMatrix1D eigenvalues = dec.getRealEigenvalues();
//
//        //4. Let the matrix of the first m eigenvalues greater than zero be A+
//        //and Q+ the first m columns of Q. Then, the coordinate matrix of
//        //classical scaling is given by X = Q+A^1/2+ .
//        DenseDoubleMatrix2D Q = new DenseDoubleMatrix2D(dmat.getElementCount(), 2);
//        DenseDoubleMatrix2D A = new DenseDoubleMatrix2D(2, 2);
//
//        for (int i = eigenvalues.size() - 1, k = 0; i >= 0 && k < 2; i--) {
//            if (eigenvalues.get(i) > EPSILON) {
//                for (int n = 0; n < Q.rows(); n++) {
//                    Q.setQuick(n, k, eigenvectors.getQuick(n, i));
//                }
//
//                A.setQuick(k, k, Math.sqrt(eigenvalues.getQuick(i)));
//                k++;
//            }
//        }
//
//        DoubleMatrix2D result = Q.zMult(A, null, 1.0, 1.0, false, false);
//
//        float[][] projection = new float[result.rows()][];
//
//        for (int i = 0; i < projection.length; i++) {
//            projection[i] = new float[2];
//
//            projection[i][0] = (float) result.getQuick(i, 0);
//            projection[i][1] = (float) result.getQuick(i, 1);
//        }
//
//        AbstractMatrix proj = new DenseMatrix();
//        ArrayList<String> attributes = new ArrayList<String>();
//        attributes.add("x");
//        attributes.add("y");
//        proj.setAttributes(attributes);
//
//        ArrayList<Integer> ids = dmat.getIds();
//        float[] cdata = dmat.getClassData();
//
//        for (int i = 0; i < projection.length; i++) {
//            AbstractVector vector = new DenseVector(projection[i], ids.get(i), cdata[i]);
//            proj.addRow(vector);
//        }
//
//        return proj;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("ERROR! It should be: ClassicalMDSProjection filename.data");
            System.exit(1);
        }

        ProjectionUtil.log(false, false);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(args[0]);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        ClassicalMDSProjectionComp cmds = new ClassicalMDSProjectionComp();
        cmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        cmds.input(matrix);
        cmds.execute();
        AbstractMatrix projection = cmds.output();

        ProjectionModelComp model = new ProjectionModelComp();
        model.input(projection);
        model.execute();

        ProjectionFrameComp frame = new ProjectionFrameComp();
        frame.input(model.output());
        frame.execute();
    }
//    private static final float EPSILON = Float.MIN_VALUE;
}
