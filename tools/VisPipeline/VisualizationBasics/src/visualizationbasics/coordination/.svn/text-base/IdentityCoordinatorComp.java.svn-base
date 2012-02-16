/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.coordination;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Coordination",
name = "Identity Coordination",
description = "Create an identity coordination object.")
public class IdentityCoordinatorComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        coord = new IdentityCoordinator();
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
    private transient IdentityCoordinator coord;
}
