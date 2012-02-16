/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.selection;

import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.selection.AbstractSelection;
import projection.model.ProjectionInstance;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import plsp.model.GroupedProjectionInstance;
import projection.model.ProjectionModel;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class GroupSelection extends AbstractSelection {

    public GroupSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (selinst.size() > 1) {
//            groupInstances(selinst);
        }
    }

    public void groupInstances(ArrayList<ProjectionInstance> selinst){
            GroupedProjectionInstance gi = new GroupedProjectionInstance((ProjectionModel) this.viewer.getModel(),
                                           (new String("Group"+GroupedProjectionInstance.cont).hashCode()));
            float x = 0;
            float y = 0;            
            for (ProjectionInstance pi : selinst) {
                x = x + pi.getX();
                y = y + pi.getY();
            }
            x = x / selinst.size();
            y = y / selinst.size();
            gi.setX(x);
            gi.setY(y);
            gi.setGroupedInstances(selinst);
//            this.viewer.getModel().removeInstances(selinst);
//            this.viewer.getModel().addInstance(gi);
//            this.viewer.getModel().notifyObservers();
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/development/WarAdd16.gif"));
    }

    @Override
    public String toString() {
        return "Group Selection";
    }
}
