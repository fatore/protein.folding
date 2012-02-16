/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

import java.io.IOException;

/**
 *
 * @author jpocom
 */
public interface ImageReader {

    public FileInfo getFileInfo() throws IOException;

    public String getOrientation();

    public int getOrientationForWriter();

    public int getVolumes();

    public boolean isZipped();

    public void clearTempFolder();
}
