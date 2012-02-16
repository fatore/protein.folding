/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique;

import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando
 */
public class Sampling {

    public enum SampleType {

        RANDOM, CLUSTERING, MAXMIN, SPAM
    }

    public Sampling(SampleType sampletype, int samplesize) {
        this.sampletype = sampletype;
        this.samplesize = samplesize;
    }

    public AbstractMatrix execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        AbstractMatrix sampledata = null;

        if (sampletype == SampleType.CLUSTERING) {
            BKmeans bkmeans = new BKmeans(samplesize);
            bkmeans.execute(diss, matrix);
            sampledata = bkmeans.getCentroids();
        } else if (sampletype == SampleType.MAXMIN) {
            Random random = new Random(System.currentTimeMillis());

            ArrayList<Integer> pivots = new ArrayList<Integer>();
            pivots.add(random.nextInt(matrix.getRowCount()));

            for (int i = 0; i < samplesize - 1; i++) {
                float maxdist = Float.NEGATIVE_INFINITY;
                int maxpivot = 0;

                int size = matrix.getRowCount();
                for (int j = 0; j < size; j++) {
                    float mindist = Float.POSITIVE_INFINITY;
                    int minpivot = 0;

                    for (int k = 0; k < pivots.size(); k++) {
                        float dist = diss.calculate(matrix.getRow(j), matrix.getRow(pivots.get(k)));

                        //getting the smallest distance between the points and the pivots
                        if (mindist > dist) {
                            mindist = dist;
                            minpivot = j;
                        }
                    }

                    //getting the largest distance between the smallest distances
                    if (maxdist < mindist) {
                        maxdist = mindist;
                        maxpivot = minpivot;
                    }
                }

                pivots.add(maxpivot);
            }

            //creating the sample matrix
            //sampledata_aux = MatrixFactory.getInstance(matrix.getClass());
            sampledata = new DenseMatrix();

            Iterator<Integer> it = pivots.iterator();
            while (it.hasNext()) {
                int index = it.next();
                AbstractVector row = matrix.getRow(index);
                sampledata.addRow(new DenseVector(row.getValues()));
            }
        } else if (sampletype == SampleType.SPAM) {
            float[] maxs = new float[matrix.getDimensions()];
            float[] mins = new float[matrix.getDimensions()];

            Arrays.fill(maxs, Float.NEGATIVE_INFINITY);
            Arrays.fill(mins, Float.POSITIVE_INFINITY);

            int size = matrix.getRowCount();
            for (int i = 0; i < size; i++) {
                float[] array = matrix.getRow(i).toArray();

                for (int j = 0; j < array.length; j++) {
                    if (maxs[j] < array[j]) {
                        maxs[j] = array[j];
                    }

                    if (mins[j] > array[j]) {
                        mins[j] = array[j];
                    }
                }
            }

            sampledata = new DenseMatrix();

            Random rand = new Random(System.currentTimeMillis());

            for (int i = 0; i < samplesize; i++) {
                float[] vect = new float[matrix.getDimensions()];

                for (int j = 0; j < vect.length; j++) {
                    vect[j] = rand.nextFloat() * (maxs[j] - mins[j]) + mins[j];
                }

                sampledata.addRow(new DenseVector(vect));
            }
        } else {
            //create the sample matrix data
            HashSet<Integer> sample = new HashSet<Integer>();
            Random random = new Random(System.currentTimeMillis());

            while (sample.size() < samplesize) {
                int index = (int) (random.nextFloat() * (matrix.getRowCount()));
                if (index < matrix.getRowCount()) {
                    sample.add(index);
                }
            }

            //sampledata_aux = MatrixFactory.getInstance(matrix.getClass());
            sampledata = new DenseMatrix();

            //creating the sample matrix
            Iterator<Integer> it = sample.iterator();
            while (it.hasNext()) {
                int index = it.next();
                AbstractVector row = matrix.getRow(index);
                sampledata.addRow(new DenseVector(row.toArray(), row.getId(), row.getKlass()));
            }
        }

        return sampledata;
    }

    private SampleType sampletype;
    private int samplesize;
}
