/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando
 */
public class Transformation {

    public AbstractMatrix capacitance(AbstractMatrix real, AbstractMatrix imaginary) throws IOException {
        if (real.getDimensions() != imaginary.getDimensions()) {
            throw new IOException("ERROR: imaginary and real matrices of "
                    + "different dimensions.");
        }

        if (real.getRowCount() != imaginary.getRowCount()) {
            throw new IOException("ERROR: imaginary and real matrices of "
                    + "different sizes.");
        }

        ArrayList<String> attreal = real.getAttributes();
        ArrayList<String> attimag = real.getAttributes();
        float[] freqs = new float[attreal.size()];

        //checking if the attributtes are valid and converting 
        for (int i = 0; i < attreal.size(); i++) {
            if (isFloat(attreal.get(i))) {
                freqs[i] = Float.parseFloat(attreal.get(i));

                if (!isFloat(attimag.get(i))
                        && freqs[i] != Float.parseFloat(attimag.get(i))) {
                    throw new IOException("ERROR: different frequency values between "
                            + "real and imaginary components!");
                }
            } else {
                throw new IOException("ERROR: attribute is not a valid frequency "
                        + "value: " + attreal.get(i));
            }
        }

        //checking if the labels are valid
        ArrayList<String> imaglabels = imaginary.getLabels();
        ArrayList<String> reallabels = real.getLabels();

        if (imaglabels.size() == reallabels.size()) {
            for (int i = 0; i < imaglabels.size(); i++) {
                if (!imaglabels.get(i).equals(reallabels.get(i))) {
                    throw new IOException("ERROR: different labels values between "
                            + "real and imaginary components!");
                }
            }
        } else {
            throw new IOException("ERROR: different numbers of elements between "
                    + "real and imaginary components!");
        }

        //converting the matrices
        AbstractMatrix matrix = new DenseMatrix();
        matrix.setAttributes(attreal);
        matrix.setLabels(imaglabels);

        for (int i = 0; i < real.getRowCount(); i++) {
            float[] vect = new float[real.getDimensions()];
            float[] realvalues = real.getRow(i).toArray();
            float[] imaginaryvalues = imaginary.getRow(i).toArray();

            for (int j = 0; j < vect.length; j++) {
                vect[j] = capacitanceParallel(freqs[j], imaginaryvalues[j], realvalues[j]);
            }

            matrix.addRow(new DenseVector(vect, real.getRow(i).getId(), real.getRow(i).getKlass()));
        }

        return matrix;
    }

    public AbstractMatrix resistence(AbstractMatrix real, AbstractMatrix imaginary) throws IOException {
        if (real.getDimensions() != imaginary.getDimensions()) {
            throw new IOException("ERROR: imaginary and real matrices of "
                    + "different dimensions.");
        }

        if (real.getRowCount() != imaginary.getRowCount()) {
            throw new IOException("ERROR: imaginary and real matrices of "
                    + "different sizes.");
        }

        ArrayList<String> attreal = real.getAttributes();
        ArrayList<String> attimag = real.getAttributes();
        float[] freqs = new float[attreal.size()];

        //checking if the attributtes are valid and converting
        for (int i = 0; i < attreal.size(); i++) {
            if (isFloat(attreal.get(i))) {
                freqs[i] = Float.parseFloat(attreal.get(i));

                if (!isFloat(attimag.get(i))
                        && freqs[i] != Float.parseFloat(attimag.get(i))) {
                    throw new IOException("ERROR: different frequency values between "
                            + "real and imaginary components!");
                }
            } else {
                throw new IOException("ERROR: attribute is not a valid frequency "
                        + "value: " + attreal.get(i));
            }
        }

        //checking if the labels are valid
        ArrayList<String> imaglabels = imaginary.getLabels();
        ArrayList<String> reallabels = real.getLabels();

        if (imaglabels.size() == reallabels.size()) {
            for (int i = 0; i < imaglabels.size(); i++) {
                if (!imaglabels.get(i).equals(reallabels.get(i))) {
                    throw new IOException("ERROR: different labels values between "
                            + "real and imaginary components!");
                }
            }
        } else {
            throw new IOException("ERROR: different numbers of elements between "
                    + "real and imaginary components!");
        }

        //converting the matrices
        AbstractMatrix matrix = new DenseMatrix();
        matrix.setAttributes(attreal);
        matrix.setLabels(imaglabels);

        for (int i = 0; i < real.getRowCount(); i++) {
            float[] vect = new float[real.getDimensions()];
            float[] realvalues = real.getRow(i).toArray();
            float[] imaginaryvalues = imaginary.getRow(i).toArray();

            for (int j = 0; j < vect.length; j++) {
                vect[j] = resistenceParallel(freqs[j], imaginaryvalues[j], realvalues[j]);
            }

            matrix.addRow(new DenseVector(vect, real.getRow(i).getId(), real.getRow(i).getKlass()));
        }

        return matrix;
    }

    private float angularFrequency(float frequency) {
        return 2.0f * (float) Math.PI * frequency;
    }

    private float capacitanceSerie(float frequency, float imaginary) {
        return -1.0f / (angularFrequency(frequency) * imaginary);
    }

    private float tau(float frequency, float imaginary, float real) {
        return angularFrequency(frequency) * capacitanceSerie(frequency, imaginary) * real;
    }

    private float capacitanceParallel(float frequency, float imaginary, float real) {
        return capacitanceSerie(frequency, imaginary) / (1.0f + (float) Math.pow(tau(frequency, imaginary, real), 2));
    }

    private float resistenceParallel(float frequency, float imaginary, float real) {
        return (1.0f + tau(frequency, imaginary, real)) / (capacitanceSerie(frequency, imaginary) * tau(frequency, imaginary, real));
    }

    private float lossParallel(float frequency, float imaginary, float real) {
        return 1.0f / resistenceParallel(frequency, imaginary, real);
    }

    private boolean isFloat(String num) {
        try {
            Float.parseFloat(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String realfile = "D:/My Dropbox/artigos/Chu/PexSensor/dados/PAHPVS-real(sem-agua).data";
        String imagfile = "D:/My Dropbox/artigos/Chu/PexSensor/dados/PAHPVS-imag(sem-agua).data";

        AbstractMatrix imaginary = MatrixFactory.getInstance(imagfile);
        AbstractMatrix real = MatrixFactory.getInstance(realfile);

        System.out.println("Imaginary size: " + imaginary.getRowCount() + " columns: " + imaginary.getDimensions());
        System.out.println("Real size: " + real.getRowCount() + " columns: " + real.getDimensions());

        Transformation t = new Transformation();
        t.capacitance(real, imaginary).save(imagfile + "-CAPACITANCE.data");
        t.resistence(real, imaginary).save(imagfile + "-RESISTENCE.data");
    }

}
