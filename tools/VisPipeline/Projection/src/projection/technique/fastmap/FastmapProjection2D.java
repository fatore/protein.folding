/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.technique.fastmap;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;

/**
 *
 * @author Fernando
 */
public class FastmapProjection2D implements Projection {

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        float[][] projection_aux = new float[matrix.getRowCount()][2];

        if (matrix.getRowCount() > 4) {
            doTheFastmap(matrix, diss, projection_aux);
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        DenseMatrix projection = new DenseMatrix();
        ArrayList<Integer> ids = matrix.getIds();
        float[] classData = matrix.getClassData();
        ArrayList<String> labels = matrix.getLabels();

        for (int i = 0; i < projection_aux.length; i++) {
            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(projection_aux[i], ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(projection_aux[i], ids.get(i), classData[i]), labels.get(i));
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Fastmap Projection time: {0}s", (finish - start) / 1000.0f);

        return projection;
    }

    private void doTheFastmap(AbstractMatrix matrix, AbstractDissimilarity diss, float[][] projection) {
        //projecting the first dimension
        project(matrix, diss, projection, 0);

        //projecting the second dimension
        project(matrix, diss, projection, 1);
    }

    private void project(AbstractMatrix matrix, AbstractDissimilarity diss, float[][] projection, int dimension) {
        //choosen pivots for this recursion
        int[] lvchoosen = chooseDistantObjects(matrix, diss, projection, dimension);
        float lvdistance = distance(matrix.getRow(lvchoosen[0]), matrix.getRow(lvchoosen[1]),
                projection[lvchoosen[0]], projection[lvchoosen[1]], diss, dimension);

        //if the distance between the pivots is 0, then set 0 for each instance
        //for this dimension
        if (lvdistance == 0) {
            //for each instance in the table
            for (int lvi = 0; lvi < projection.length; lvi++) {
                projection[lvi][dimension] = 0.0f;
            }
        } else { //if the distance is not equal to 0, then
            //instances iterator
            for (int lvi = 0; lvi < projection.length; lvi++) {
                float dist_lvchoosen0_lvi = distance(matrix.getRow(lvchoosen[0]), matrix.getRow(lvi),
                        projection[lvchoosen[0]], projection[lvi], diss, dimension);

                float dist_lvchoosen0_lvchoosen1 = distance(matrix.getRow(lvchoosen[0]), matrix.getRow(lvchoosen[1]),
                        projection[lvchoosen[0]], projection[lvchoosen[1]], diss, dimension);

                float dist_lvchoosen1_lvi = distance(matrix.getRow(lvchoosen[1]), matrix.getRow(lvi),
                        projection[lvchoosen[1]], projection[lvi], diss, dimension);

                float lvxi = (float) ((Math.pow(dist_lvchoosen0_lvi, 2)
                        + Math.pow(dist_lvchoosen0_lvchoosen1, 2)
                        - Math.pow(dist_lvchoosen1_lvi, 2))
                        / (2 * dist_lvchoosen0_lvchoosen1));

                projection[lvi][dimension] = lvxi;
            }
        }
    }

    private float distance(AbstractVector v1, AbstractVector v2, float[] p1, float[] p2,
            AbstractDissimilarity diss, int dimension) {
        float dist = diss.calculate(v1, v2);

        for (int i = 0; i < dimension; i++) {
            float coord1 = p1[dimension - 1];
            float coord2 = p2[dimension - 1];
            return (float) (Math.sqrt(Math.abs(Math.pow(diss.calculate(v1, v2), 2) - Math.pow((coord1 - coord2), 2))));
        }

        return dist;
    }

    private int[] chooseDistantObjects(AbstractMatrix matrix, AbstractDissimilarity diss, float[][] projection, int dimension) {
        int[] choosen = new int[2];
        int pivot1 = 0, pivot2 = 0;

        int size = matrix.getRowCount();
        float max = Float.NEGATIVE_INFINITY;

        for (int k = 0; k < 5; k++) {
            int initialpivot = (int) (Math.random() * (matrix.getRowCount() - 1));
            float max_aux = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < size; i++) {
                float dist = distance(matrix.getRow(initialpivot), matrix.getRow(i),
                        projection[initialpivot], projection[i], diss, dimension);
                if (dist > max_aux) {
                    max_aux = dist;
                    pivot1 = i;
                }
            }

            max_aux = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < size; i++) {
                float dist = distance(matrix.getRow(pivot1), matrix.getRow(i),
                        projection[pivot1], projection[i], diss, dimension);
                if (dist > max_aux) {
                    max_aux = dist;
                    pivot2 = i;
                }
            }

            if (max_aux > max) {
                choosen[0] = pivot1;
                choosen[1] = pivot2;
            }
        }

        return choosen;
    }
}
