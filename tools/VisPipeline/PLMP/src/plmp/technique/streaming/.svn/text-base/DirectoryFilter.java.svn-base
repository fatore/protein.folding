/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique.streaming;

import java.io.File;
import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author Fernando
 */
public class DirectoryFilter extends AbstractFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory();
    }

    public String getDescription() {
        return "Directory selection";
    }

    @Override
    public String getProperty() {
        return "DIRECTORY.DIR";
    }

    @Override
    public String getFileExtension() {
        return "*.*";
    }

}
