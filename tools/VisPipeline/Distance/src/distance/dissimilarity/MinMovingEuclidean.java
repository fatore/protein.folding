/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.dissimilarity;

import matrix.AbstractVector;
import matrix.sparse.SparseVector;

/**
 *
 * @author PC
 */
public class MinMovingEuclidean implements AbstractDissimilarity {

    public MinMovingEuclidean() {
        this(3);
    }

    public MinMovingEuclidean(int windowsize) {
        this.windowsize = windowsize;
    }

    @Override
    public float calculate(AbstractVector v1, AbstractVector v2) {
        assert (v1.size() == v2.size()) : "ERROR: vectors of different sizes!";

        float[] a = v1.getValues();
        float[] b = v2.getValues();

        if (v1 instanceof SparseVector) {
            a = v1.toArray();
        }

        if (v2 instanceof SparseVector) {
            b = v2.toArray();
        }

        float distance = 0;
        int prev = 0;

        for (int i = 0; i < a.length; i++) {
            float min = Float.POSITIVE_INFINITY;
            int begin = Math.max(prev, i - windowsize);
            int end = Math.min(b.length - 1, i + windowsize);

            for (int j = begin; j <= end; j++) {
                float value = dist(a[i], b[j]);

                if (min > value) {
                    min = value;
                    prev = j;
                }
            }

            distance += min;
        }

        return (float) Math.sqrt(distance);
    }

    private float dist(float a, float b) {
        float diff = a - b;
        return diff * diff;
    }

    private int windowsize;

}
