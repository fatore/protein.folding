/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author jpocom
 */
public class AnalyzeReader extends ImageReaderPreparer implements ImageReader {

    boolean littleEndian = false;
    private String orientation;
    String directory, fileName;
    int volumes = 1;
    int glmax, glmin;

    public AnalyzeReader(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
        orientation = null;
    }

    @Override
    public boolean isZipped() {
        return zipped;
    }

    @Override
    public void clearTempFolder() {
        if (zipped) {
            System.out.println("Now deleting " + directory);
            FileUtils.deleteFile(directory, true);
        }
    }

    @Override
    public FileInfo getFileInfo() throws IOException {
        String hdrfile = null;
        String name = "";
        if (fileName.endsWith(".img")) {
            name = fileName.substring(0, fileName.length() - 4);
        } else if (fileName.endsWith(".hdr")) {
            name = fileName.substring(0, fileName.length() - 4);
        } else {
            name = fileName;
        }
        String path = "";

        String destDir = unzip(directory, name + ".img");
        if (zipped) {
            unzip(directory, name + ".hdr", destDir);
            directory = destDir;
        }

        if (directory != null && !directory.equals("")) {
            if (!directory.endsWith("" + File.separatorChar)) {
                directory += File.separatorChar;
            }
            path += directory + name + ".hdr";
        } else {
            path += name + ".hdr";
        }


        FileInputStream filein = new FileInputStream(path);
        DataInputStream input = new DataInputStream(filein);
        FileInfo fi = new FileInfo();
        byte[] units = new byte[4];

        int i;
        short bitsallocated, datatype;
        // In order to get the sliceSpacing, ImagePlus has been altered

        // header_key
        input.readInt();                                // sizeof_hdr
        for (i = 0; i < 10; i++) {
            input.read();                               // data_type
        }
        for (i = 0; i < 18; i++) {
            input.read();                               // db_name
        }
        input.readInt();                                // extents
        input.readShort();                              // session_error
        input.readByte();                       	// regular
        input.readByte();                               // hkey_un0

        // image_dimension
        short endian = readShort(input);                // dim[0]
        if ((endian < 0) || (endian > 15)) {
            System.out.println("Byte Order:: Little Endian");
            littleEndian = true;
            fi.intelByteOrder = true;
        }
        fi.width = readShort(input);                    // dim[1]
        fi.height = readShort(input);                   // dim[2]
        fi.nImages = readShort(input);                  // dim[3]
        volumes = input.readShort();                    // dim[4]
        for (i = 0; i < 3; i++) {
            input.readShort();                          // dim[5-7]
        }
        input.read(units, 0, 4);                        // vox_units
        fi.unit = new String(units, 0, 4);
        for (i = 0; i < 8; i++) {
            input.read();                               // cal_units[8]
        }
        input.readShort();                              // unused1
        datatype = readShort(input);            	// datatype
        bitsallocated = readShort(input);		// bitpix
        input.readShort();				// dim_un0
        input.readFloat();				// pixdim[0]
        fi.pixelWidth = (double) readFloat(input);	// pixdim[1]
        fi.pixelHeight = (double) readFloat(input);     // pixdim[2]
        fi.pixelDepth = (double) readFloat(input); 	// pixdim[3]
        for (i = 0; i < 4; i++) {
            input.readFloat();                          // pixdim[4-7]
        }
        fi.offset = (int) readFloat(input);		// vox_offset
        input.readFloat();				// roi_scale
        input.readFloat();				// funused1
        input.readFloat();				// funused2
        input.readFloat();				// cal_max
        input.readFloat();				// cal_min
        input.readInt();				// compressed
        input.readInt();				// verified

        // ImageStatistics s = imp.getStatistics();
        glmax = readInt(input);	//(int) s.max		// glmax
        glmin = readInt(input);	//(int) s.min		// glmin

        // data_history
        for (i = 0; i < 80; i++) {
            input.read();                               // descrip
        }
        for (i = 0; i < 24; i++) {
            input.read();                               // aux_file
        }
        orientation = "" + input.read();        	// orient
        for (i = 0; i < 10; i++) {
            input.read();                               // originator
        }
        for (i = 0; i < 10; i++) {
            input.read();                               // generated
        }
        for (i = 0; i < 10; i++) {
            input.read();                               // scannum
        }
        for (i = 0; i < 10; i++) {
            input.read();                               // patient_id
        }
        for (i = 0; i < 10; i++) {
            input.read();                               // exp_date
        }
        for (i = 0; i < 10; i++) {
            input.read();                       	// exp_time
        }
        for (i = 0; i < 3; i++) {
            input.read();                       	// hist_un0
        }
        input.readInt();				// views
        input.readInt();				// vols_added
        input.readInt();				// start_field
        input.readInt();				// field_skip
        input.readInt();				// omax
        input.readInt();				// omin
        input.readInt();				// smax
        input.readInt();				// smin

        input.close();
        filein.close();

        switch (datatype) {
            case 2:
                fi.fileType = FileInfo.GRAY8;           // DT_UNSIGNED_CHAR
                bitsallocated = 8;
                break;
            case 4:
                fi.fileType = FileInfo.GRAY16_SIGNED; 	// DT_SIGNED_SHORT
                bitsallocated = 16;
                break;
            case 8:
                fi.fileType = FileInfo.GRAY32_INT; 	// DT_SIGNED_INT
                bitsallocated = 32;
                break;
            case 16:
                fi.fileType = FileInfo.GRAY32_FLOAT; 	// DT_FLOAT
                bitsallocated = 32;
                break;
            case 128:
                fi.fileType = FileInfo.RGB_PLANAR; 	// DT_RGB
                bitsallocated = 24;
                break;
            default:
                fi.fileType = 0;			// DT_UNKNOWN
        }
        fi.fileName = name + ".img";
        fi.directory = directory;
        fi.fileFormat = FileInfo.RAW;
        return (fi);
    }

    public int readInt(DataInputStream input) throws IOException {
        if (!littleEndian) {
            return input.readInt();
        }
        byte b1 = input.readByte();
        byte b2 = input.readByte();
        byte b3 = input.readByte();
        byte b4 = input.readByte();
        return ((((b4 & 0xff) << 24) | ((b3 & 0xff) << 16) | ((b2 & 0xff) << 8) | (b1 & 0xff)));
    }

    public short readShort(DataInputStream input) throws IOException {
        if (!littleEndian) {
            return input.readShort();
        }
        byte b1 = input.readByte();
        byte b2 = input.readByte();
        return ((short) (((b2 & 0xff) << 8) | (b1 & 0xff)));
    }

    public float readFloat(DataInputStream input) throws IOException {
        if (!littleEndian) {
            return input.readFloat();
        }
        int orig = readInt(input);
        return (Float.intBitsToFloat(orig));
    }

    /**
     * @return
     */
    @Override
    public String getOrientation() {
        return ReaderUtils.getOrientationLabel(getStandardOrientation(orientation));
    }

    /**
     * @return Returns the glmax.
     */
    public int getGlmax() {
        return glmax;
    }

    /**
     * @return Returns the glmin.
     */
    public int getGlmin() {
        return glmin;
    }

    public String getStandardOrientation(String analyzeOrientation) {
        String rtn = "";
        //System.out.println("ORI " + analyzeOrientation);
        if (analyzeOrientation.equalsIgnoreCase("0")) //TRANSVERSE
        {
            rtn = "2";
        } else if (analyzeOrientation.equalsIgnoreCase("1")) //CORONAL
        {
            rtn = "3";
        } else if (analyzeOrientation.equalsIgnoreCase("2")) //SAGITTAL
        {
            rtn = "4";
        } else if (analyzeOrientation.equalsIgnoreCase("3")) //TRANSERSE Flipped
        {
            rtn = "-2";
        } else if (analyzeOrientation.equalsIgnoreCase("4")) //CORONAL Flipped
        {
            rtn = "-3";
        } else if (analyzeOrientation.equalsIgnoreCase("5")) //SAGITTAL Flipped
        {
            rtn = "-4";
        }
        return rtn;
    }

    @Override
    public int getVolumes() {
        return volumes;
    }

    @Override
    public int getOrientationForWriter() {
        return ReaderUtils.getOrientationAsInt(getOrientation(), true);
    }

    public static void main(String[] args) {
        AnalyzeReader read = new AnalyzeReader("Y:\\data2\\WORK\\pettest\\pb1184\\ROIS", "mr_test.img");
        try {
            FileInfo fi = read.getFileInfo();
            System.out.println("Glmax " + read.getGlmax() + " GlMin " + read.glmin + " " + fi.fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
