/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.kernel;

import distance.DistanceMatrix;
import distance.dissimilarity.Euclidean;
import matrix.AbstractMatrix;
import matrix.AbstractVector;

/**
 *
 * @author Fernando Viera Paulovich
 */
public class KernelDistance {

    public DistanceMatrix transform(AbstractMatrix matrix) {
        Euclidean diss = new Euclidean();

        float beta = 0;
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getRowCount(); j++) {
                beta += diss.calculate(matrix.getRow(i), matrix.getRow(j));
            }
        }

        beta = (matrix.getRowCount() * matrix.getRowCount() - matrix.getRowCount()) / beta;

        //considering an euclidean distance
        DistanceMatrix dmat = new DistanceMatrix(matrix.getRowCount());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < i; j++) {
                float dist = (float) Math.sqrt(kernel(beta, matrix.getRow(i), matrix.getRow(i)) -
                        2 * kernel(beta, matrix.getRow(i), matrix.getRow(j)) +
                        kernel(beta, matrix.getRow(j), matrix.getRow(j)));
                dmat.setDistance(i, j, dist);
            }
        }

        dmat.setClassData(matrix.getClassData());
        dmat.setIds(matrix.getIds());

        return dmat;
    }

    private float kernel(float beta, AbstractVector v1, AbstractVector v2) {
        Euclidean diss = new Euclidean();
        return (float) Math.exp(-beta * diss.calculate(v1, v2));
    }

}
