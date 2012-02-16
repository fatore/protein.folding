/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics.model;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Basics",
name = "Topic Projection Model",
description = "Create a projection model to be visualized.")
public class TopicProjectionModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (projection != null) {
            model = new TopicProjectionModel();
            Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
            Scalar dots = model.addScalar(ProjectionConstants.DOTS);

            int nrows = projection.getRowCount();

            for (int i = 0; i < nrows; i++) {
                AbstractVector row = projection.getRow(i);
                TopicProjectionInstance pi = new TopicProjectionInstance(model, row.getId(),
                        row.getValue(0), row.getValue(1));
                pi.setScalarValue(cdata, row.getKlass());
                pi.setScalarValue(dots, 0.0f);

                if (labels != null) {
                    pi.setLabel(labels.get(i));
                }
            }
        } else {
            throw new IOException("A 2D projection should be provided.");
        }
    }

    public void input(@Param(name = "2D projection") AbstractMatrix projection) {
        this.projection = projection;
    }

    public void input(@Param(name = "2D projection") AbstractMatrix projection,
            @Param(name = "labels") ArrayList<String> labels) {
        this.projection = projection;

        if (labels.size() == projection.getRowCount()) {
            this.labels = labels;
        }
    }

    public TopicProjectionModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        model = null;
    }

    public static final long serialVersionUID = 1L;
    private transient TopicProjectionModel model;
    private transient AbstractMatrix projection;
    private transient ArrayList<String> labels;
}
