/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipelinebasics.interfaces;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public interface AbstractComponent extends Serializable {

    public AbstractParametersView getParametersEditor();

    public void reset();

    public void execute() throws IOException;

}
