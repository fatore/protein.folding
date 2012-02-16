
package vispipeline.util;

import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PIPFilter extends AbstractFilter {

    @Override
    public String getDescription() {
        return "Pipeline File (*.pip)";
    }

    @Override
    public String getProperty() {
        return "PIP.DIR";
    }

    @Override
    public String getFileExtension() {
        return "pip";
    }

}
