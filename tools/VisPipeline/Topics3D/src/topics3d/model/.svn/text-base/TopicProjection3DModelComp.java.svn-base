/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.model;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import projection3d.model.Projection3DModel.TypeGlyph;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection3D.Basics",
name = "Topic Projection 3D Model",
description = "Create a projection 3D model to be visualized.")
public class TopicProjection3DModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (projection != null) {
            model = new TopicProjection3DModel(TypeGlyph.SPHERE);
            Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
            Scalar dots = model.addScalar(ProjectionConstants.DOTS);

            int nrows = projection.getRowCount();

            for (int i = 0; i < nrows; i++) {
                AbstractVector row = projection.getRow(i);
                TopicProjection3DInstance pi = new TopicProjection3DInstance(model, row.getId(),
                        row.getValue(0), row.getValue(1), row.getValue(2));
                pi.setScalarValue(cdata, row.getKlass());
                pi.setScalarValue(dots, 0.0f);
                model.addInstance(pi);

                if (labels != null) {
                    pi.setLabel(labels.get(i));
                }
            }
        } else {
            throw new IOException("A 2D projection should be provided.");
        }
    }

    public void input(@Param(name = "3D projection") AbstractMatrix projection) {
        this.projection = projection;
    }

    public void input(@Param(name = "3D projection") AbstractMatrix projection,
            @Param(name = "labels") ArrayList<String> labels) {
        this.projection = projection;

        if (labels.size() == projection.getRowCount()) {
            this.labels = labels;
        }
    }

    public TopicProjection3DModel output() {
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
    private transient TopicProjection3DModel model;
    private transient AbstractMatrix projection;
    private transient ArrayList<String> labels;
}
