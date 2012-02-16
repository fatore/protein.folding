/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TRKFilter.java
 *
 * Created on Oct 26, 2009, 9:22:36 PM
 */
package tensor.util.filter;

import visualizationbasics.util.filter.AbstractFilter;

/**
 *
 * @author jpocom
 */
public class VTKFilter extends AbstractFilter {

    @Override
    public String getDescription() {
        return "VTK file (*.vtk)";
    }

    @Override
    public String getProperty() {
        return "VTK.DIR";
    }

    @Override
    public String getFileExtension() {
        return "vtk";
    }

}
