/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.view.selection.coordination;

import visualizationbasics.view.selection.coordination.IdentityCoordinationSelection;
import projection.coordination.ColorIdentityCoordinator;
import visualizationbasics.view.selection.AbstractSelection;
import visualizationbasics.coordination.AbstractCoordinator;
import visualizationbasics.coordination.IdentityCoordinator;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class CoordinationSelectionFactory {

    public static AbstractSelection getInstance(AbstractCoordinator coordinator, ModelViewer viewer) {
        if (coordinator instanceof IdentityCoordinator) {
            return new IdentityCoordinationSelection(viewer);
        }
        else if(coordinator instanceof ColorIdentityCoordinator){
            return new ColorIdentityCoordinationSelection(viewer);
        }
        return null;
    }

}
