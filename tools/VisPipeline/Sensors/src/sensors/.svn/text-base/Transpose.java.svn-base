/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 *
 * @author PC
 */
public class Transpose {

    public static void execute(AbstractMatrix matrix, String filename) throws IOException {
        ArrayList<String> attributes = matrix.getAttributes();
        ArrayList<String> labels = matrix.getLabels();
        float[][] vectors = matrix.toMatrix();

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));

            out.write("freq,");
            for (int i = 0; i < labels.size()-1; i++) {
                out.write(labels.get(i)+",");
            }
            out.write(labels.get(labels.size()-1)+"\r\n");

            for (int i = 0; i < attributes.size(); i++) {
                out.write(attributes.get(i) + ",");

                for (int j = 0; j < vectors.length-1; j++) {
                    out.write(vectors[j][i] + ",");
                }

                out.write(vectors[vectors.length-1][i] + "\r\n");
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Transpose.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        AbstractMatrix matrix = MatrixFactory.getInstance("D:/My Dropbox/artigos/Case-Prudente/single-molecule/novoArquivo02.data");

        Transpose.execute(matrix, "D:/My Dropbox/artigos/Case-Prudente/single-molecule/novoArquivo02.csv");

    }
}
