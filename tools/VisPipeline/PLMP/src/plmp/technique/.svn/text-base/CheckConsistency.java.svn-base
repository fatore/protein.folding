/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author PC
 */
public class CheckConsistency {

    public CheckConsistency() {
        this.nullcols = new ArrayList<Integer>();
    }

    public AbstractMatrix removeNullColumns(AbstractMatrix matrix) {
        DenseMatrix newmatrix = new DenseMatrix();

        float[][] vectors = matrix.toMatrix();

        //discovering the null columns
        int[] count = new int[vectors[0].length];
        Arrays.fill(count, 0);
        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].length; j++) {
                if (Math.abs(vectors[i][j]) > EPSILON) {
                    count[j]++;
                }
            }
        }

        //counting how many columns are not null, and storing the null ones
        int nrnotnull = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                nrnotnull++;
            } else {
                nullcols.add(i);
            }
        }

        //removing the null columns
        for (int i = 0; i < vectors.length; i++) {
            AbstractVector row = matrix.getRow(i);
            float[] vector = new float[nrnotnull];

            for (int j = 0, k = 0; j < vectors[i].length; j++) {
                if (count[j] != 0) {
                    vector[k++] = vectors[i][j];
                }
            }

            newmatrix.addRow(new DenseVector(vector, row.getId(), row.getKlass()));
        }

        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Number of original columns: " + matrix.getDimensions()
                + "\nNumber of null columns: " + (matrix.getDimensions() - nrnotnull));

        return newmatrix;
    }

    public float[] returnTheRemoved(float[] vector) {
        float[] newvector = new float[vector.length + nullcols.size()];
        int begin = 0, i = 0;


        for (int j = 0; j < nullcols.size(); j++) {
            int end = nullcols.get(j);

            while (begin < end) {
                newvector[begin++] = vector[i++];
            }

            newvector[++begin] = 0;
        }

        for (int j = begin; j < newvector.length; j++) {
            newvector[j] = vector[i++];
        }

        return newvector;
    }

    private ArrayList<Integer> nullcols;
    private static final float EPSILON = 0.0000001f;
}
