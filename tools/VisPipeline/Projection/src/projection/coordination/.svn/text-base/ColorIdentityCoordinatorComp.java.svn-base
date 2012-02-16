/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.coordination;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import visualizationbasics.coordination.AbstractCoordinator;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Coordination",
name = "Color Identity Coordination",
description = "Create an color identity coordination object.")
public class ColorIdentityCoordinatorComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        coord = new ColorIdentityCoordinator();
    }

    public AbstractCoordinator output() {
        return coord;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        coord = null;
    }

    public static final long serialVersionUID = 1L;
    private transient ColorIdentityCoordinator coord;
}
