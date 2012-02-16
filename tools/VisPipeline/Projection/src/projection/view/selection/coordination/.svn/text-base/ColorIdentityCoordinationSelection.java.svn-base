/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.view.selection.coordination;

import visualizationbasics.view.selection.AbstractSelection;
import projection.view.selection.*;
import visualizationbasics.coordination.AbstractCoordinator;
import visualizationbasics.model.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import projection.coordination.ColorIdentityCoordinator;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ColorIdentityCoordinationSelection extends AbstractSelection {

    public ColorIdentityCoordinationSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() != null && selinst.size() > 0) {
            DynamicColorSelection selection = new DynamicColorSelection(viewer);
            selection.selected(selinst);
            if (selection.getColor() != null) {
                for (AbstractCoordinator ac : viewer.getCoordinators()) {
                    if (ac instanceof ColorIdentityCoordinator) {
                        ac.coordinate(new ArrayList<AbstractInstance>(selinst), selection.getColor());
                    }
                }
                viewer.getModel().setSelectedInstances(selinst);
                viewer.getModel().notifyObservers();
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/development/WebComponentAdd16.gif"));
    }

    @Override
    public String toString() {
        return "Color Identity Coordination Selection";
    }

}
