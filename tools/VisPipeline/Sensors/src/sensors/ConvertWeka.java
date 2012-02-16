/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author PC
 */
public class ConvertWeka {

    public static void execute(AbstractMatrix matrix, String filename) throws IOException {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(filename));

            out.write("% converted from VisPipeline file (http://infoserver.lcad.icmc.usp.br/)\r\n\r\n");

            out.write("@RELATION \"" + filename + "\"\r\n\r\n");

            ArrayList<String> attributes = matrix.getAttributes();
            for (int i = 0; i < attributes.size(); i++) {
                //out.write("@ATTRIBUTE " + attributes.get(i) + " NUMERIC\r\n");
                out.write("@ATTRIBUTE " + i + " NUMERIC\r\n");
            }

            float[] cdata = matrix.getClassData();
            HashSet<Float> classes = new HashSet<Float>();
            for (int i = 0; i < cdata.length; i++) {
                if (!classes.contains(cdata[i])) {
                    classes.add(cdata[i]);
                }
            }

            out.write("@ATTRIBUTE class {");
            Iterator<Float> iterator = classes.iterator();
            for (int i = 0; i < classes.size() - 1; i++) {
                out.write(iterator.next() + ",");
            }
            out.write(iterator.next() + "}\r\n\r\n");

            out.write("@DATA\r\n");

            for (int i = 0; i < matrix.getRowCount(); i++) {
                float[] vector = matrix.getRow(i).toArray();

                for (int j = 0; j < vector.length; j++) {
                    out.write(vector[j] + ",");
                }
                out.write(matrix.getRow(i).getKlass() + "\r\n");
            }

        } catch (IOException ex) {
            throw new IOException("Problems written \"" + filename + "\"!");
        } finally {
            //close the file
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ConvertWeka.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static AbstractMatrix getSelected(AbstractMatrix matrix, String attr) {
        ArrayList<Integer> index = new ArrayList<Integer>();
        StringTokenizer token = new StringTokenizer(attr, ",");
        while (token.hasMoreTokens()) {
            index.add(Integer.parseInt(token.nextToken()));
        }

        ArrayList<String> attributes = matrix.getAttributes();
        AbstractMatrix newmatrix = new DenseMatrix();

        //defining the new attributes
        ArrayList<String> newattr = new ArrayList<String>();
        for (int i = 0; i < index.size(); i++) {
            newattr.add(attributes.get(index.get(i)));
        }
        newmatrix.setAttributes(newattr);

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);
            float[] values = row.toArray();

            float[] vector = new float[index.size()];
            for (int j = 0; j < vector.length; j++) {
                vector[j] = values[index.get(j)];
            }

            newmatrix.addRow(new DenseVector(vector, row.getId(), row.getKlass()), matrix.getLabel(i));
        }

        return newmatrix;
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/My Dropbox/artigos/Chu/PexSensor/dados/allsensors(sem-agua).data";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        //ConvertWeka.execute(matrix, filename + ".arff");
        ConvertWeka.getSelected(matrix, 
                "1,32,70,72,75,76,77,78,81,82,85,86,87,88,89,90,91,93,95,96,98,110,121,122,142,146,157,160,182,185")
                .save(filename + "-SEL_WEKA.data");
    }
}
