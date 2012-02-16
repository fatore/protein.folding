/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique.streaming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando
 */
public class GenerateSample {

    public GenerateSample(String datadescription) throws IOException {
        this.parseDescription(datadescription);
        this.interset = new ArrayList<Interval[]>();
    }

    public int getNumberDimensions() {
        return attributes.size();
    }

    public void clearIntervals() {
        this.interset = new ArrayList<Interval[]>();
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void addIntervals(Interval[] intervals) throws IOException {
        if (intervals.length == attributes.size()) {
            interset.add(intervals);
        } else {
            throw new IOException("Invalid Interval size!");
        }
    }

    public AbstractMatrix generateSample(int samplesize) {
        AbstractMatrix samplematrix = new DenseMatrix();

        Random rand = new Random(1);

        int nrsamplesperint = samplesize / interset.size();

        for (int i = 0; i < interset.size(); i++) {
            Interval[] intervals = interset.get(i);

            for (int j = 0; j < nrsamplesperint; j++) {
                float[] vect = new float[intervals.length];

                for (int k = 0; k < intervals.length; k++) {
                    float max = intervals[k].max * (datainterval[k].max - datainterval[k].min) + datainterval[k].min;
                    float min = intervals[k].min * (datainterval[k].max - datainterval[k].min) + datainterval[k].min;

                    float value = rand.nextFloat();
                    vect[k] = value * (max - min) + min;
                }

                samplematrix.addRow(new DenseVector(vect, (float) i));
            }
        }

        return samplematrix;
    }

    public AbstractMatrix generateSpamBase(AbstractMatrix samplematrix, int spamsize) {
        if (samplematrix == null) {
            samplematrix = new DenseMatrix();

            for (int j = 0; j < spamsize / 10; j++) {
                float[] vect = new float[datainterval.length];
                Arrays.fill(vect, 0.0f);
                samplematrix.addRow(new DenseVector(vect, 0));
            }
        }

        Random rand = new Random(1);

        for (int j = 0; j < spamsize; j++) {
            float[] vect = new float[datainterval.length];

            for (int k = 0; k < datainterval.length; k++) {
                float value = rand.nextFloat();
                vect[k] = (value * (datainterval[k].max - datainterval[k].min) + datainterval[k].min);
            }

            samplematrix.addRow(new DenseVector(vect, 0));
        }

        return samplematrix;
    }

    private void parseDescription(String filename) throws IOException {
        attributes = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String attrs = br.readLine();
        String mins = br.readLine();
        String maxs = br.readLine();
        br.close();

        StringTokenizer stattrs = new StringTokenizer(attrs, ";");
        while (stattrs.hasMoreTokens()) {
            attributes.add(stattrs.nextToken().trim());
        }

        datainterval = new Interval[attributes.size()];

        StringTokenizer stmins = new StringTokenizer(mins, ";");
        for (int i = 0; stmins.hasMoreTokens(); i++) {
            datainterval[i] = new Interval();
            datainterval[i].min = Float.parseFloat(stmins.nextToken().trim());
        }

        StringTokenizer stmaxs = new StringTokenizer(maxs, ";");
        for (int i = 0; stmaxs.hasMoreTokens(); i++) {
            datainterval[i].max = Float.parseFloat(stmaxs.nextToken().trim());
        }
    }

    public static class Interval {

        public Interval() {
            this(0, 0);
        }

        public Interval(float min, float max) {
            if (min < max) {
                this.min = min;
                this.max = max;
            } else {
                this.min = max;
                this.max = min;
            }
        }

        @Override
        public String toString() {
            return min + " <> " + max;
        }

        public void setMinMax(float min, float max) throws IOException {
            if (min > 1.0f || min < 0.0f) {
                throw new IOException("Value out of bounds (0 >= x <= 1)!: " + min);
            }

            if (max > 1.0f || max < 0.0f) {
                throw new IOException("Value out of bounds (0 >= x <= 1)!: " + max);
            }

            if (min < max) {
                this.min = min;
                this.max = max;
            } else {
                this.min = max;
                this.max = min;
            }
        }

        public float max;
        public float min;
    }

    public static void main(String[] args) throws IOException {
        String desc = "D:\\viscontest\\multifieldintervals.txt";
        GenerateSample gs = new GenerateSample(desc);



        Interval[] inter = new Interval[gs.getNumberDimensions()];
        for (int i = 0; i < inter.length; i++) {
            inter[i] = new GenerateSample.Interval((float) Math.random(), (float) Math.random());
            System.out.println(inter[i]);
        }


        gs.addIntervals(inter);
        AbstractMatrix sample = gs.generateSample(5);

        for (int i = 0; i < sample.getRowCount(); i++) {
            float[] array = sample.getRow(i).toArray();

            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j] + " ");
            }

            System.out.println();
        }


    }

    private Interval[] datainterval;
    private ArrayList<String> attributes;
    private ArrayList<Interval[]> interset;
}
