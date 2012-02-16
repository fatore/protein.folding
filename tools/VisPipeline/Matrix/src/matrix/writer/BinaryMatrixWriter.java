/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix.writer;

import matrix.*;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class BinaryMatrixWriter {

    public void write(AbstractMatrix matrix, String filename) throws IOException {
        BufferedOutputStream bos = null;
        DataOutputStream out = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(filename));
            out = new DataOutputStream(bos);

            out.writeInt(matrix.getRowCount());
            out.writeInt(matrix.getDimensions());

            for (int i = 0; i < matrix.getRowCount(); i++) {
                AbstractVector row = matrix.getRow(i);
                float[] array = row.toArray();

                for (int j = 0; j < array.length; j++) {
                    out.writeFloat(array[j]);
                }

                out.writeFloat(row.getKlass());
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }

                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(BinaryMatrixWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
