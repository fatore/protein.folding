/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.view.selectedview;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import tensor.model.FiberInstance;
import tensor.view.FibersFrame;

/**
 *
 * @author jpocom
 */
public abstract class SelectedFiberRepresentation {

    public SelectedFiberRepresentation(FibersFrame viewer) {
        this.viewer = viewer;
    }

    public abstract void selected(ArrayList<FiberInstance> selinst);

    public abstract ImageIcon getIcon();

    @Override
    public abstract String toString();

    protected FibersFrame viewer;
}
