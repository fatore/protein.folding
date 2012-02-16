/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.util.filter;

import java.util.ArrayList;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class DATAandBINMultipleFilter extends AbstractMultipleFilter {

    private static ArrayList<String> extensions;

    static {
        extensions = new ArrayList<String>();
        extensions.add("data");
        extensions.add("bin");
    }

    @Override
    public ArrayList<String> getFileExtensions() {
        return extensions;
    }

    @Override
    public String getProperty() {
        return "POINTS.DIR";
    }

    @Override
    public String getFileExtension() {
        return "data";
    }

    @Override
    public String getDescription() {
        return "Points File (*.data) or Binary Points File (*.bin)";
    }

}
