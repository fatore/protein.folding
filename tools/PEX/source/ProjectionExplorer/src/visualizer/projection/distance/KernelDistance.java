/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.projection.distance;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.matrix.Vector;

/**
 *
 * @author Fernando Viera Paulovich
 */
public class KernelDistance {

    public DistanceMatrix transform(Matrix matrix) {
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

    private float kernel(float beta, Vector v1, Vector v2) {
        Euclidean diss = new Euclidean();
        return (float) Math.exp(-beta * diss.calculate(v1, v2));
    }

    public static void main(String[] args) {
        try {
            String filename = "D:\\My Documents\\FERNANDO\\artigos\\2009\\sibgrapi-features\\cooGabor_newFeatureSpace_ori1_1_ori2_0_ori3_1_ori4_0_withoutEnerg.data";
            Matrix matrix = MatrixFactory.getInstance(filename);

            KernelDistance kd = new KernelDistance();
            DistanceMatrix dmat = kd.transform(matrix);
            dmat.setClassData(matrix.getClassData());
            dmat.setIds(matrix.getIds());

            dmat.save(filename + "_kernel.dmat");

        } catch (IOException ex) {
            Logger.getLogger(Mahalanobis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
