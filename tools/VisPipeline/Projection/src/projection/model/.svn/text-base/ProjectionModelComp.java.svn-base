/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.model;

import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
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
name = "Projection Model",
description = "Create a projection model to be visualized.")
public class ProjectionModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (projection != null) {
            model = new ProjectionModel();
            Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
            Scalar dots = model.addScalar(ProjectionConstants.DOTS);
            Scalar dColor = model.addScalar(ProjectionConstants.DYNAMIC_COLOR_SCALAR);

            int nrows = projection.getRowCount();

            for (int i = 0; i < nrows; i++) {
                AbstractVector row = projection.getRow(i);
                ProjectionInstance pi = new ProjectionInstance(model, row.getId(),
                        row.getValue(0), row.getValue(1));
                pi.setScalarValue(cdata, row.getKlass());
                pi.setScalarValue(dots, 0.0f);
                pi.setScalarValue(dColor, 0.0f);
            }
        } else {
            throw new IOException("A 2D projection should be provided.");
        }
    }

    public void input(@Param(name = "2D projection") AbstractMatrix projection) {
        this.projection = projection;
    }

    public ProjectionModel output() {
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
    private transient ProjectionModel model;
    private transient AbstractMatrix projection;
}
