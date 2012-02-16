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
public class LineSelectedFiber extends SelectedFiberRepresentation {

    public LineSelectedFiber(FibersFrame viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<FiberInstance> selinst) {
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/tensor/util/line.png"));
    }

    @Override
    public String toString() {
        return "Selected fibers represented as lines";
    }


}
