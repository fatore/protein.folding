/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

import java.io.File;

/**
 *
 * @author jpocom
 */
public class FileUtils {

    public static String getTempFolder() {
        String rtn = System.getProperty("vispip.tmp");
        if (rtn == null) {
            rtn = System.getProperty("user.home");
        }
        return rtn;
    }

    private static boolean recursiveDelete(File file) {
        if (file.isDirectory()) {
            //recursive delete
            File fileList[] = file.listFiles();
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    recursiveDelete(fileList[i]);
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteFile(File f, boolean safeDelete) {
        if (safeDelete) {
            String temp = getTempFolder();
            File tempFolder = new File(temp);
            if (f.getPath().startsWith(tempFolder.getPath())) {
                return recursiveDelete(f);
            } else {
                return false;
            }
        } else {
            return recursiveDelete(f);
        }
    }

    public static boolean deleteFile(String path, boolean safeDelete) {
        return deleteFile(new File(path), safeDelete);
    }
}
