/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.util.filter;

import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author Fernando V. Paulovic
 */
public class XMLFilter extends AbstractFilter {

    @Override
    public String getDescription() {
        return "XML Projection Model (*.xml)";
    }

    @Override
    public String getProperty() {
        return "XML.DIR";
    }

    @Override
    public String getFileExtension() {
        return "xml";
    }

}
