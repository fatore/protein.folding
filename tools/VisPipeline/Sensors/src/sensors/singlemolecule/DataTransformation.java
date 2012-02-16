/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.singlemolecule;

import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author paulovich
 */
public class DataTransformation {

    public static AbstractMatrix zeroOutRanges(AbstractMatrix matrix, ArrayList<Range> ranges) {
        AbstractMatrix newmatrix = new DenseMatrix();
        newmatrix.setAttributes(matrix.getAttributes());

        ArrayList<String> attributes = matrix.getAttributes();
        ArrayList<Integer> axis = new ArrayList<Integer>();

        //defining the ranges that are used to the comparison
        float[] attrvalues = new float[attributes.size()];
        for (int i = 0; i < attributes.size(); i++) {
            attrvalues[i] = Float.parseFloat(attributes.get(i).trim());
        }

        for (int i = 0; i < ranges.size(); i++) {
            Range range = ranges.get(i);

            //find begin
            for (int j = 0; j < attrvalues.length; j++) {
                if ((attrvalues[j] - range.min) >= 0.001) {
                    axis.add(j);
                    break;
                }
            }

            //find end
            for (int j = 0; j < attrvalues.length; j++) {
                if (j == attrvalues.length - 1) {
                    axis.add(j);
                } else {
                    if ((attrvalues[j] - range.max) >= 0.001) {
                        if (Math.abs(attrvalues[j] - range.max) <= 0000.1) {
                            axis.add(j);
                        } else {
                            axis.add(j - 1);
                        }
                        break;

                    }
                }
            }
        }

        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] oldvect = matrix.getRow(i).toArray();
            float[] newvect = new float[matrix.getDimensions()];
            Arrays.fill(newvect, 0);

            for (int j = 0; j < axis.size(); j += 2) {
                for (int k = axis.get(j); k <= axis.get(j + 1); k++) {
                    newvect[k] = oldvect[k];
                }
            }

            AbstractVector row = matrix.getRow(i);
            newmatrix.addRow(new DenseVector(newvect, row.getId(), row.getKlass()), matrix.getLabel(i));
        }

        return newmatrix;
    }

    public static AbstractMatrix removeNoise(AbstractMatrix matrix, int masksize, int nriterations) {
        for (int i = 0; i < nriterations; i++) {
            movingAverageSmoothing(matrix, 1);
        }

        normalize(matrix);
        removeMean(matrix);

        return matrix;
    }

    public static AbstractMatrix fft(AbstractMatrix matrix) {
        DenseMatrix newmatrix = new DenseMatrix();

        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] vector = matrix.getRow(i).toArray();
            vector = Arrays.copyOf(vector, vector.length * 2);

            FloatFFT_1D fft = new FloatFFT_1D(vector.length / 2);
            fft.realForwardFull(vector);

            vector = Arrays.copyOfRange(vector, 0, vector.length / 20);

            AbstractVector row = matrix.getRow(i);
            newmatrix.addRow(new DenseVector(vector, row.getId(), row.getKlass()), matrix.getLabel(i));
        }

        return newmatrix;
    }

    public static void simpleExponentialSmoothing(AbstractMatrix matrix, float alpha) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            //smoothing the curve
            float[] values = matrix.getRow(i).getValues();
            for (int j = 1; j < values.length; j++) {
                values[j] = alpha * values[j] + (1 - alpha) * values[j - 1];
            }
        }
    }

    public static void movingAverageSmoothing(AbstractMatrix matrix, int masksize) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] values = matrix.getRow(i).getValues();
            float[] oldvalues = new float[values.length];
            System.arraycopy(values, 0, oldvalues, 0, values.length);

            for (int j = 0; j < values.length; j++) {
                float mean = 0;
                int count = 0;
                for (int k = j - masksize; k <= j + masksize; k++) {
                    if (k > 0 && k < oldvalues.length) {
                        mean += oldvalues[k];
                        count++;
                    }
                }
                mean /= count;

                values[j] = mean;
            }
        }
    }

    public static void median(AbstractMatrix matrix, int masksize) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] values = matrix.getRow(i).getValues();
            float[] oldvalues = new float[values.length];
            System.arraycopy(values, 0, oldvalues, 0, values.length);

            for (int j = 0; j < values.length; j++) {
                ArrayList<Float> aux = new ArrayList<Float>();
                for (int k = j - masksize; k <= j + masksize; k++) {
                    if (k >= 0 && k < oldvalues.length) {
                        aux.add(oldvalues[k]);
                    }
                }
                Collections.sort(aux);

                values[j] = aux.get(aux.size() / 2);
            }
        }
    }

    public static void removeMean(AbstractMatrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] values = matrix.getRow(i).getValues();

            float mean = 0;
            for (int j = 0; j < values.length; j++) {
                mean += values[j];
            }
            mean /= values.length;

            for (int j = 0; j < values.length; j++) {
                values[j] = values[j] - mean;
            }

        }
    }

    public static void normalize(AbstractMatrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] values = matrix.getRow(i).getValues();

            float max = Float.NEGATIVE_INFINITY;
            float min = Float.POSITIVE_INFINITY;

            for (int j = 0; j < values.length; j++) {
                if (max < values[j]) {
                    max = values[j];
                }

                if (min > values[j]) {
                    min = values[j];
                }
            }

            for (int j = 0; j < values.length; j++) {
                if (max > min) {
                    values[j] = (values[j] - min) / (max - min);
                } else {
                    values[j] = 0;
                }
            }
        }
    }

    public static void gaussianSmoothing(AbstractMatrix matrix, int masksize) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] values = matrix.getRow(i).getValues();
            float[] oldvalues = new float[values.length];
            System.arraycopy(values, 0, oldvalues, 0, values.length);

            for (int j = 0; j < values.length; j++) {
                float mean = 0;
                int count = 0;
                for (int k = j - masksize; k <= j + masksize; k++) {
                    if (k > 0 && k < oldvalues.length) {
                        mean += oldvalues[k] * ((((float) masksize + 1) - (float) Math.abs(j - k)) / ((float) masksize + 1));
                        count++;
                    }
                }
                mean /= count;
                values[j] = mean;
            }
        }
    }

    public static class Range {

        @Override
        public String toString() {
            return "Range{" + "max=" + max + ",min=" + min + '}';
        }

        public Range(float min, float max) {
            this.min = min;
            this.max = max;
        }
        public float max;
        public float min;
    }
}
