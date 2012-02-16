/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.util;

import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author Fernando
 */
public class TXTFilter extends AbstractFilter {

    public String getDescription() {
        return "Text file selection (*.txt)";
    }

    @Override
    public String getProperty() {
        return "TXT.DIR";
    }

    @Override
    public String getFileExtension() {
        return "txt";
    }

}
