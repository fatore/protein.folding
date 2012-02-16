/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.singlemolecule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author paulovich
 */
public class ArocaUtil {

    public static void read(String filename, int nrwavenumbers) throws IOException {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(filename));

            float[] wavenumbers = new float[nrwavenumbers];

            DenseMatrix matrix = new DenseMatrix();

            //reading one spectrum
            String line = null;
            while ((line = in.readLine()) != null) {
                float[] spectrum = new float[nrwavenumbers];
                String s1 = null, s2 = null;

                for (int i = 0; i < nrwavenumbers; i++) {
                    StringTokenizer token = new StringTokenizer(line, "\t", false);
                    s1 = token.nextToken();
                    s2 = token.nextToken();
                    wavenumbers[nrwavenumbers - i - 1] = Float.parseFloat(token.nextToken());
                    spectrum[nrwavenumbers - i - 1] = Float.parseFloat(token.nextToken());

                    if (i < nrwavenumbers - 1) {
                        line = in.readLine();
                    }
                }

                matrix.addRow(new DenseVector(spectrum), s1 + "<>" + s2);
            }

            ArrayList<String> attr = new ArrayList<String>();
            for (int i = 0; i < wavenumbers.length; i++) {
                attr.add(Float.toString(wavenumbers[i]));
            }
            matrix.setAttributes(attr);

            matrix.save(filename + ".data");

        } catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(ArocaUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filename = "/home/paulovich/Downloads/aroca.txt";
        ArocaUtil.read(filename, 575);
    }
}
