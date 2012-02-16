/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.model;

import java.io.EOFException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tensor.io.BinaryFile;
import tensor.io.FormatException;

/**
 *
 * @author jpocom
 */
public class TRKHeader {

    public void read(BinaryFile file) throws IOException {
        try {
            file.read(idString);
            for (int i = 0; i < 3; i++) {
                dim[i] = file.readShort();
            }
            file.readFloatArray(voxelSize, 0, voxelSize.length);
            file.readFloatArray(origin, 0, origin.length);
            nScalars = file.readShort();
            if (nScalars != 0) {
                throw new IOException("Not supported TRK with nScalar different from 0");
            }
            
            for (int i = 0; i < 10; i++) {
                file.read(scalarName[i]);
            }
            nProperties = file.readShort();
            for (int i = 0; i < 10; i++) {
                file.read(propertyName[i]);
            }
            file.read(reserved);
            file.read(voxelOrder);
            file.read(pad2);
            file.readFloatArray(imageOrientationPatient, 0, imageOrientationPatient.length);
            file.read(pad1);
            invertX = (byte) file.read();
            invertY = (byte) file.read();
            invertZ = (byte) file.read();
            swapXY = (byte) file.read();
            swapYZ = (byte) file.read();
            swapZX = (byte) file.read();
            nCount = file.readInteger();
            version = file.readInteger();
            hdrSize = file.readInteger();
        } catch (EOFException ex) {
            Logger.getLogger(TRKModelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FormatException ex) {
            Logger.getLogger(TRKModelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object clone() {
        TRKHeader output = new TRKHeader();
        
        System.arraycopy(idString, 0, output.idString, 0,6);
        System.arraycopy(dim, 0, output.dim, 0,3);
        System.arraycopy(voxelSize, 0, output.voxelSize, 0,3);
        System.arraycopy(origin, 0, output.origin, 0,3);
        output.nScalars = nScalars;
        for (int i=0; i< 10; i++)
            System.arraycopy(scalarName[i], 0, output.scalarName[i], 0,20);
        output.nProperties = nProperties;
        for (int i=0; i< 10; i++)
            System.arraycopy(propertyName[i], 0, output.propertyName[i], 0,20);
        System.arraycopy(reserved, 0, output.reserved, 0,508);
        System.arraycopy(voxelOrder, 0, output.voxelOrder, 0,4);
        System.arraycopy(pad2, 0, output.pad2, 0,4);
        System.arraycopy(imageOrientationPatient, 0, output.imageOrientationPatient, 0,6);
        System.arraycopy(pad1, 0, output.pad1, 0,2);
        output.invertX = invertX;
        output.invertY = invertY;
        output.invertZ = invertZ;
        output.swapXY = swapXY;
        output.swapYZ = swapYZ;
        output.swapZX = swapZX;
        output.nCount = nCount;
        output.version = version;
        output.hdrSize = hdrSize;

        return output;
    }

    public int getNCount() {
        return nCount;
    }

    private byte[] idString = new byte[6];
    private short[] dim = new short[3];
    private float[] voxelSize = new float[3];
    private float[] origin = new float[3];
    private short nScalars;
    private byte[][] scalarName = new byte[10][20];
    private short nProperties;
    private byte[][] propertyName = new byte[10][20];
    private byte[] reserved = new byte[508];
    private byte[] voxelOrder = new byte[4];
    private byte[] pad2 = new byte[4];
    private float[] imageOrientationPatient = new float[6];
    private byte[] pad1 = new byte[2];
    private byte invertX;
    private byte invertY;
    private byte invertZ;
    private byte swapXY;
    private byte swapYZ;
    private byte swapZX;
    private int nCount;
    private int version;
    private int hdrSize;
}
