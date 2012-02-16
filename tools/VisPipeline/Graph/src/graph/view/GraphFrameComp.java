/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.view;

import graph.model.GraphModel;
import projection.model.ProjectionModel;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import visualizationbasics.coordination.AbstractCoordinator;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Graph.View",
name = "Graph View Frame",
description = "Display a graph model.")
public class GraphFrameComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (model != null) {
            GraphFrame frame = new GraphFrame();
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
            throw new IOException("A projection model should be provided.");
        }
    }

    public void input(@Param(name = "graph model") GraphModel model) {
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
          if (paramview == null) {
            paramview = new GraphFrameParamView(this);
        }

        return paramview;
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
    private transient GraphModel model;
    private transient GraphFrameParamView paramview;
    private transient ArrayList<AbstractCoordinator> coordinators;
}
