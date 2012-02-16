/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix.streaming;

import matrix.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class StreamingMatrix extends AbstractMatrix {

    @Override
    public void load(String filename) throws IOException {
        this.raf = new RandomAccessFile(filename, "r");

        this.nrrows = raf.readInt();
        this.dimensions = raf.readInt();
    }

    @Override
    public int getRowCount() {
        return nrrows;
    }

    @Override
    public int getDimensions() {
        return dimensions;
    }

    @Override
    public AbstractVector getRow(int row) {
        if (raf != null) {
            try {
                int offset = (2 * FLOAT_SIZE) + (row * (dimensions + 1) * FLOAT_SIZE);
                raf.seek(offset);

                //reading the vector
                float[] vect = new float[dimensions];

                byte[] bytes = new byte[(dimensions + 1) * FLOAT_SIZE];
                raf.readFully(bytes);

                for (int start = 0, i = 0; i < dimensions; start = start + 4, i++) {
                    vect[i] = toFloat(bytes, start);
                }

                //reading the class data
                float cdata = toFloat(bytes, dimensions * FLOAT_SIZE);

                return new StreamingVector(vect, row, cdata);
            } catch (IOException ex) {
                Logger.getLogger(StreamingMatrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public void save(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addRow(AbstractVector vector) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRow(int index, AbstractVector vector) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractVector removeRow(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void normalize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float[][] toMatrix() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> getAttributes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAttributes(ArrayList<String> attributes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Integer> getIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < this.nrrows; i++) {
            ids.add(i);
        }
        return ids;
    }

    @Override
    public float[] getClassData() {
        float[] klass = new float[this.nrrows];
        for (int i = 0; i < nrrows; i++) {
            AbstractVector row = getRow(i);
            klass[i] = row.getKlass();
        }
        return klass;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private final float toFloat(byte[] b, int start) {
        int i = (((b[start] & 0xff) << 24) | ((b[start + 1] & 0xff) << 16)
                | ((b[start + 2] & 0xff) << 8) | (b[start + 3] & 0xff));
        return Float.intBitsToFloat(i);
    }

    private int nrrows;
    private RandomAccessFile raf;
    private static final int FLOAT_SIZE = 4;
}
