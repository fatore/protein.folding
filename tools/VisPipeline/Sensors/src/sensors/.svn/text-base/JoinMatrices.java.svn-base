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
 * @author Fernando V. Paulovic
 */
public class JoinMatrices {

    public AbstractMatrix execute(ArrayList<AbstractMatrix> matrices) throws IOException {
        if (matrices.size() > 0) {
            ArrayList<String> labels = matrices.get(0).getLabels();
            ArrayList<Integer> ids = matrices.get(0).getIds();

            //checking the labels
            for (int i = 0; i < labels.size(); i++) {
                String label = labels.get(i);

                for (int j = 0; j < matrices.size(); j++) {
                    if (!label.toLowerCase().equals(matrices.get(j).getLabel(i).toLowerCase())) {
                        throw new IOException("ERROR: labels do not match! "
                                + label + " != " + matrices.get(j).getLabel(i));
                    }
                }
            }

            //checking the ids
            for (int i = 0; i < ids.size(); i++) {
                int id = ids.get(i);

                for (int j = 0; j < matrices.size(); j++) {
                    if (id != matrices.get(j).getRow(i).getId()) {
                        throw new IOException("ERROR: ids do not match!");
                    }
                }
            }

            AbstractMatrix matrix = new DenseMatrix();

            //creating the attributes list and defining the final dimensionality
            ArrayList<String> attr = new ArrayList<String>();
            int nrdim = 0;
            for (int i = 0; i < matrices.size(); i++) {
                attr.addAll(matrices.get(i).getAttributes());
                nrdim += matrices.get(i).getDimensions();
            }

            matrix.setAttributes(attr);

            int nrrows = matrices.get(0).getRowCount();
            for (int i = 0; i < nrrows; i++) {
                float[] finalvect = new float[nrdim];

                for (int j = 0, pos = 0; j < matrices.size(); j++) {
                    float[] vect = matrices.get(j).getRow(i).toArray();

                    for (int k = 0; k < vect.length; k++) {
                        finalvect[pos++] = vect[k];
                    }
                }

                AbstractVector originalrow = matrices.get(0).getRow(i);

                if (labels.size() > 0) {
                    matrix.addRow(new DenseVector(finalvect, originalrow.getId(),
                            originalrow.getKlass()), labels.get(i));
                } else {
                    matrix.addRow(new DenseVector(finalvect, originalrow.getId(),
                            originalrow.getKlass()));
                }
            }

            return matrix;
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        String dir = "D:/My Dropbox/artigos/Chu/PexSensor/dados/";
        ArrayList<AbstractMatrix> matrices = new ArrayList<AbstractMatrix>();
//        matrices.add(MatrixFactory.getInstance(dir + "EletrodoPuro-img.data-CAPACITANCE.data"));
//        matrices.add(MatrixFactory.getInstance(dir + "PamamProtoCruzi-img.data-CAPACITANCE.data"));
//        matrices.add(MatrixFactory.getInstance(dir + "PamamProtoLeish-img.data-CAPACITANCE.data"));
//        matrices.add(MatrixFactory.getInstance(dir + "PamamPVS-img.data-CAPACITANCE.data"));


        matrices.add(MatrixFactory.getInstance(dir + "EletrodoPuro-imag(sem-agua).data-CAPACITANCE.data"));
        matrices.add(MatrixFactory.getInstance(dir + "PAHFitase-imag(sem-agua).data-CAPACITANCE.data"));
        matrices.add(MatrixFactory.getInstance(dir + "PAHPVS-imag(sem-agua).data-CAPACITANCE.data"));

        JoinMatrices jm = new JoinMatrices();
        jm.execute(matrices).save(dir + "allsensors(sem-agua).data");
    }

}
