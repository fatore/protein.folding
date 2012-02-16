/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author jpocom
 */
public abstract class ImageReaderPreparer {

    protected boolean zipped = false;

    protected String unzip(String srcDirectory, String fileName, String destDir) {
        String zext = ".gz";
        String file = srcDirectory + File.separator + fileName;
        File imageFile = new File(file);
        if (!imageFile.exists() && (new File(file + zext).exists())) {
            zipped = true;
        }
        if (zipped) {
            new UnzipFile().gunzip(file, destDir);
            return destDir;
        } else {
            return srcDirectory;
        }
    }

    protected void unzip(File imageFile, String destDir) {
        String zext = ".gz";
        if (!imageFile.exists() && (new File(imageFile.getPath() + zext).exists())) {
            zipped = true;
        }
        if (zipped) {
            new UnzipFile().gunzip(imageFile.getPath(), destDir);
        }
    }

    protected String unzip(String directory, String fileName) throws IOException {
        String zext = ".gz";
        String file = directory + File.separator + fileName;
        File imageFile = new File(file);
        if (!imageFile.exists() && (new File(file + zext).exists())) {
            zipped = true;
        }
        if (zipped) {
            String suffix = "_" + new Random().nextInt();
            File tempDir = new File(FileUtils.getTempFolder());
            File dir = File.createTempFile("NRG", suffix, tempDir);
            if (dir.exists()) {
                dir.delete();
            }
            dir.mkdir();
            new UnzipFile().gunzip(file, dir.getPath());
            return dir.getPath();
        } else {
            return directory;
        }
    }
}
