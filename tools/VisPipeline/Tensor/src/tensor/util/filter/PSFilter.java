/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.util.filter;

import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author jpocom
 */
public class PSFilter extends AbstractFilter {

    @Override
    public String getDescription() {
        return "PS file (*.ps)";
    }

    @Override
    public String getProperty() {
        return "PS.DIR";
    }

    @Override
    public String getFileExtension() {
        return "ps";
    }

}
