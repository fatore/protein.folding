/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelcoordinates.model;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author PC
 */
@VisComponent(hierarchy = "Projection.Basics",
name = "Projection Model",
description = "Create a projection model to be visualized.")
public class ParallelCoordinatesModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (data != null) {
            ArrayList<String> attributes = data.getAttributes();
            ArrayList<String> labels = data.getLabels();

            model = new ParallelCoordinatesModel(attributes);
            for (int i = 0; i < data.getRowCount(); i++) {
                if (labels.size() > 0) {
                    PolylineInstance pi = new PolylineInstance((ParallelCoordinatesModel) model, data.getRow(i), labels.get(i));
                } else {
                    PolylineInstance pi = new PolylineInstance((ParallelCoordinatesModel) model, data.getRow(i));
                }
            }
        } else {
            throw new IOException("A multidimensional data matrix should be provided.");
        }
    }

    public void input(@Param(name = "multidimensional data") AbstractMatrix data) {
        this.data = data;
    }

    public ParallelCoordinatesModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        data = null;
        model = null;
    }
    
    public static final long serialVersionUID = 1L;
    private transient ParallelCoordinatesModel model;
    private transient AbstractMatrix data;
}
