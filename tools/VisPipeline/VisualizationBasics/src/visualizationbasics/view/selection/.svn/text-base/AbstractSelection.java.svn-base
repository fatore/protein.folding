/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.view.selection;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class AbstractSelection {

    public AbstractSelection(ModelViewer viewer) {
        this.viewer = viewer;
    }

    public abstract void selected(ArrayList<AbstractInstance> selinst);

    public abstract ImageIcon getIcon();

    @Override
    public abstract String toString();

    protected ModelViewer viewer;
}
