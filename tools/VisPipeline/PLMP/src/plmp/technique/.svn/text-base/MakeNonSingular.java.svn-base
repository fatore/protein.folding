/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique;

import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando
 */
public class MakeNonSingular {

    public static AbstractMatrix execute(AbstractMatrix origmatrix) throws IOException {
        return execute(origmatrix, 0.0f);
    }

    public static AbstractMatrix execute(AbstractMatrix origmatrix, float cdata) throws IOException {
//        float[] vectmax = new float[origmatrix.getDimensions()];
//        Arrays.fill(vectmax, Float.NEGATIVE_INFINITY);
//
//        float[] vectmin = new float[origmatrix.getDimensions()];
//        Arrays.fill(vectmin, Float.POSITIVE_INFINITY);
//
//        for (int i = 0; i < origmatrix.getRowCount(); i++) {
//            float[] array = origmatrix.getRow(i).getValues();
//
//            for (int j = 0; j < array.length; j++) {
//                if (vectmax[j] < array[j]) {
//                    vectmax[j] = array[j];
//                }
//
//                if (vectmin[j] > array[j]) {
//                    vectmin[j] = array[j];
//                }
//            }
//        }
//
//        for (int i = 0; i < origmatrix.getDimensions(); i++) {
//            float[] vect = new float[origmatrix.getDimensions()];
//            Arrays.fill(vect, 0.0f);
//            vect[i] = (((vectmax[i] - vectmin[i]) / 2) + vectmin[i]);
//            vect[i] = (Math.abs(vect[i]) > 0.001f) ? vect[i] : 0.001f;
//            origmatrix.addRow(new DenseVector(vect, cdata));
//        }
//
//        return origmatrix;

        for (int i = 0; i < origmatrix.getDimensions(); i++) {
            origmatrix.getRow(i).setValue(i, 1.0f);
        }

        return origmatrix;
    }

}
