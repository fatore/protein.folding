/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author PC
 */
public class SplitFrequencies {

    public static void execute(AbstractMatrix matrix, float inimedium, float endmedium,
            DenseMatrix lowmatrix, DenseMatrix medmatrix, DenseMatrix highmatrix) throws IOException {

        ArrayList<String> attr = matrix.getAttributes();
        float[] freqs = new float[attr.size()];

        //checking if the attributtes are valid and converting
        for (int i = 0; i < attr.size(); i++) {
            if (isFloat(attr.get(i))) {
                freqs[i] = Float.parseFloat(attr.get(i));
            } else {
                throw new IOException("ERROR: attribute is not a valid frequency "
                        + "value: " + attr.get(i));
            }
        }

        //defining the frequencies of each range
        ArrayList<Integer> lowindex = new ArrayList<Integer>();
        ArrayList<Integer> medindex = new ArrayList<Integer>();
        ArrayList<Integer> highindex = new ArrayList<Integer>();
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] < inimedium) {
                lowindex.add(i);
            } else if (freqs[i] <= endmedium) {
                medindex.add(i);
            } else {
                highindex.add(i);
            }
        }

        //creating the low frequencies matrix
        ArrayList<String> lowattr = new ArrayList<String>();
        for (int i = 0; i < lowindex.size(); i++) {
            lowattr.add(attr.get(lowindex.get(i)));
        }

        lowmatrix.setAttributes(lowattr);
        lowmatrix.setLabels(matrix.getLabels());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);
            float[] vect = row.toArray();
            float[] newvect = new float[lowindex.size()];

            for (int j = 0; j < newvect.length; j++) {
                newvect[j] = vect[lowindex.get(j)];
            }

            lowmatrix.addRow(new DenseVector(newvect, row.getId(), row.getKlass()), matrix.getLabel(i));
        }


        //creating the medium frequencies matrix
        ArrayList<String> medattr = new ArrayList<String>();
        for (int i = 0; i < medindex.size(); i++) {
            medattr.add(attr.get(medindex.get(i)));
        }

        medmatrix.setAttributes(medattr);
        medmatrix.setLabels(matrix.getLabels());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);
            float[] vect = row.toArray();
            float[] newvect = new float[medindex.size()];

            for (int j = 0; j < newvect.length; j++) {
                newvect[j] = vect[medindex.get(j)];
            }

            medmatrix.addRow(new DenseVector(newvect, row.getId(), row.getKlass()), matrix.getLabel(i));
        }

        //creating the high frequencies matrix
        ArrayList<String> highattr = new ArrayList<String>();
        for (int i = 0; i < highindex.size(); i++) {
            highattr.add(attr.get(highindex.get(i)));
        }

        highmatrix.setAttributes(highattr);
        highmatrix.setLabels(matrix.getLabels());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);
            float[] vect = row.toArray();
            float[] newvect = new float[highindex.size()];

            for (int j = 0; j < newvect.length; j++) {
                newvect[j] = vect[highindex.get(j)];
            }

            highmatrix.addRow(new DenseVector(newvect, row.getId(), row.getKlass()), matrix.getLabel(i));
        }
    }

    private static boolean isFloat(String num) {
        try {
            Float.parseFloat(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/AM-formatado(densidade).data";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        DenseMatrix low = new DenseMatrix();
        DenseMatrix med = new DenseMatrix();
        DenseMatrix high = new DenseMatrix();

        SplitFrequencies.execute(matrix, 100, 10000, low, med, high);

        low.save(filename + "-LOW.data");
        med.save(filename + "-MED.data");
        high.save(filename + "-HIGH.data");
    }
}
