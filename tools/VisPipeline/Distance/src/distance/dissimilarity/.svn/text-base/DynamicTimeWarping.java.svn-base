/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package distance.dissimilarity;

import matrix.AbstractVector;
import matrix.sparse.SparseVector;

/**
 *
 * @author paulovich
 */
public class DynamicTimeWarping implements AbstractDissimilarity {

    public DynamicTimeWarping() {
        this(0.25f);
    }

    public DynamicTimeWarping(float warpingWindowPerc) {
        this.warpingWindowPerc = warpingWindowPerc;
        this.warpingWindowSize = -1;
    }

    public DynamicTimeWarping(int warpingWindowSize) {
        this.warpingWindowSize = warpingWindowSize;
        this.warpingWindowPerc = -1;
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

        //creating the matrix
        if (g == null || g.length < a.length) {
            g = new float[a.length][b.length];
        }

        //initial condition
        g[0][0] = dist(a[0], b[0]);

        int r = warpingWindowSize;
        if (warpingWindowSize == -1) {
            r = (int) ((a.length - 1) * warpingWindowPerc);
        }

        //filling the diagonals
        for (int i = r + 1, j = 0; i < b.length; i++, j++) {
            g[j][i] = g[i][j] = Float.POSITIVE_INFINITY;
        }

        //calculate the first row and column
        for (int i = 1; i <= r; i++) {
            g[0][i] = g[0][i - 1] + dist(a[i], b[0]);
            g[i][0] = g[i - 1][0] + dist(a[0], b[i]);
        }

        //calculate the remaining values
        for (int i = 1; i < b.length; i++) {
            int min = Math.max(1, i - r);
            int max = Math.min(a.length - 1, i + r);

            for (int j = min; j <= max; j++) {
                g[i][j] = Math.min(Math.min(g[i - 1][j], g[i - 1][j - 1]), g[i][j - 1]) + dist(a[j], b[i]);
            }
        }

        return (float) g[a.length - 1][b.length - 1];
    }

    private float dist(float a, float b) {
        float diff = a - b;
        return (float) Math.sqrt(diff * diff);
    }
    private static float[][] g;
    private float warpingWindowPerc;
    private int warpingWindowSize;
}
