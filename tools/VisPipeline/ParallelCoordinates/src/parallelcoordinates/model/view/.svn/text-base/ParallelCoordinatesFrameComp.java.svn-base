/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parallelcoordinates.model.view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import parallelcoordinates.model.ParallelCoordinatesModel;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.coordination.AbstractCoordinator;

/**
 *
 * @author PC
 */
@VisComponent(hierarchy = "Parallel Coordinates.View",
name = "Parallel Coordinates View Frame",
description = "Display parallel coordinates model.")
public class ParallelCoordinatesFrameComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (model != null) {
            ParallelCoordinatesFrame frame = new ParallelCoordinatesFrame();
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setTitle(title);
            frame.setModel(model);

            if (coordinators != null) {
                for (int i = 0; i < coordinators.size(); i++) {
                    frame.addCoordinator(coordinators.get(i));
                }
            }
        } else {
            throw new IOException("A parallel coordinates model should be provided.");
        }
    }

    public void input(@Param(name = "parallel coordinates model") ParallelCoordinatesModel model) {
        this.model = model;
    }

    public void attach(@Param(name = "coordinator") AbstractCoordinator coordinator) {
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
        coordinators = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle () {
        return title;
    }

    public static final long serialVersionUID = 1L;
    private String title = "";
    private transient ParallelCoordinatesModel model;
    private transient ArrayList<AbstractCoordinator> coordinators;
}
