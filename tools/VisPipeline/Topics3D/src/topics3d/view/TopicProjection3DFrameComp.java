/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import topics3d.model.TopicProjection3DModel;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import visualizationbasics.coordination.AbstractCoordinator;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection3D.View",
name = "Topic Projection 3D View Frame",
description = "Display a projection model.")
public class TopicProjection3DFrameComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (model != null) {
            TopicProjection3DFrame frame = new TopicProjection3DFrame();
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setModel(model);

            if (coordinators != null) {
                for (int i = 0; i < coordinators.size(); i++) {
                    frame.addCoordinator(coordinators.get(i));
                }
            }
        } else {
            throw new IOException("A projection model should be provided.");
        }
    }

    public void input(@Param(name = "projection model") TopicProjection3DModel model) {
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

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        model = null;
    }

    public static final long serialVersionUID = 1L;
    private transient TopicProjection3DModel model;
    private transient ArrayList<AbstractCoordinator> coordinators;
}
