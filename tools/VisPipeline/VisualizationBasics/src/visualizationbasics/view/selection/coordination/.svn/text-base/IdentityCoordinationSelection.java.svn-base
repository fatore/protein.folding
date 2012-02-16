/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.view.selection.coordination;

import visualizationbasics.view.selection.AbstractSelection;
import visualizationbasics.coordination.AbstractCoordinator;
import visualizationbasics.model.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import visualizationbasics.coordination.IdentityCoordinator;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class IdentityCoordinationSelection extends AbstractSelection {

    public IdentityCoordinationSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() != null) {
            for (AbstractCoordinator ac : viewer.getCoordinators()) {
                if (ac instanceof IdentityCoordinator) {
                    ac.coordinate(new ArrayList<AbstractInstance>(selinst), null);
                }
            }

            viewer.getModel().setSelectedInstances(selinst);
            viewer.getModel().notifyObservers();
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Refresh16.gif"));
    }

    @Override
    public String toString() {
        return "Identity Coordination Selection";
    }

}
