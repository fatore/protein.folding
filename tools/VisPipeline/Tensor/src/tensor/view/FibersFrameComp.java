/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import tensor.model.FiberModel;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.coordination.AbstractCoordinator;

/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Tensor.View",
name = "Fibers View Frame",
description = "Display a fibers model.")
public class FibersFrameComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        model = null;
    }

    @Override
    public void execute() throws IOException {
        if (model != null) {
            FibersFrame frame = new FibersFrame();
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setModel(model);
            frame.setVisible(true);
            
            if (coordinators != null) {
                for (int i = 0; i < coordinators.size(); i++) {
                    frame.addCoordinator(coordinators.get(i));
                }
            }
        } else {
            throw new IOException("A projection model should be provided.");
        }
    }

    public void input(@Param(name = "fiber model") FiberModel model) {
        this.model = model;
    }


    public void attach(@Param(name = "Coordinator") AbstractCoordinator coordinator) {
        if (coordinators == null) {
            coordinators = new ArrayList<AbstractCoordinator>();
        }

        if (coordinator != null) {
            coordinators.add(coordinator);
        }
    }

    public static final long serialVersionUID = 1L;
    private transient FiberModel model;
    private transient ArrayList<AbstractCoordinator> coordinators;
    
}
