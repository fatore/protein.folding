/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.view.selection;

import visualizationbasics.view.selection.AbstractSelection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class InstanceSelection extends AbstractSelection {

    public InstanceSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() != null) {
            viewer.getModel().setSelectedInstances(selinst);
            viewer.getModel().notifyObservers();
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/AlignCenter16.gif"));
    }

    @Override
    public String toString() {
        return "Instance Selection";
    }

}
