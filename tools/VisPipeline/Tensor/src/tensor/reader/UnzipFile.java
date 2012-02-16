/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author jpocom
 */
public class UnzipFile {

    public void copyInputStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }

    public void unzip(String srcPath, String destPath) {
        Enumeration entries;
        ZipFile zipFile;

        System.out.println("UnzipFile unzipping " + srcPath + " to " + destPath);
        try {
            zipFile = new ZipFile(srcPath);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    // Assume directories are stored parents first then children.
                    System.err.println("Extracting directory: " + entry.getName());
                    // This is not robust, just for demonstration purposes.
                    (new File(entry.getName())).mkdir();
                    continue;
                }

                System.err.println("Extracting file: " + entry.getName());
                copyInputStream(zipFile.getInputStream(entry),
                        new BufferedOutputStream(new FileOutputStream(destPath + File.separator + entry.getName())));
            }

            zipFile.close();
        } catch (IOException ioe) {
            System.err.println("Unhandled exception:");
            ioe.printStackTrace();
            return;
        }
    }

    public void gunzip(String srcPath, String destPath) {
        int sChunk = 8192;
        // create input stream
        String zipname, source;
        if (srcPath.endsWith(".gz")) {
            zipname = srcPath;
            source = srcPath.substring(0, srcPath.length() - 3);
        } else {
            zipname = srcPath + ".gz";
            source = srcPath;
        }
        File inFile = new File(source);
        File outFile = new File(destPath + File.separator + inFile.getName());
        GZIPInputStream zipin;
        try {
            FileInputStream in = new FileInputStream(zipname);
            zipin = new GZIPInputStream(in);
        } catch (IOException e) {
            System.out.println("Couldn't open " + zipname + ".");
            return;
        }
        byte[] buffer = new byte[sChunk];
        // decompress the file
        try {
            FileOutputStream out = new FileOutputStream(outFile.getAbsolutePath());
            int length;
            while ((length = zipin.read(buffer, 0, sChunk)) != -1) {
                out.write(buffer, 0, length);
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Couldn't decompress " + srcPath + ".");
        }
        try {
            zipin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
