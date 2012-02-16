/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.model;

import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import projection3d.model.Projection3DModel.TypeGlyph;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;


/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Projection3D.Basics",
name = "Projection 3D Model",
description = "Create a projection 3D model to be visualized.")
public class Projection3DModelComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
         if (paramview == null) {
            paramview = new Projection3DModelParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        model = null;
    }

    @Override
    public void execute() throws IOException {
        if (projection != null) {
            model = new Projection3DModel(typeGlyph);
            
            Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
            Scalar dots = model.addScalar(ProjectionConstants.DOTS);

            int nrows = projection.getRowCount();

            for (int i = 0; i < nrows; i++) {
                AbstractVector row = projection.getRow(i);
                Projection3DInstance pi = new Projection3DInstance(model, row.getId(),
                        row.getValue(0), row.getValue(1), row.getValue(2));
                pi.setScalarValue(cdata, row.getKlass());
                pi.setScalarValue(dots, 0.0f);
                model.addInstance(pi);
            }

        } else {
            throw new IOException("A 3D projection should be provided.");
        }
    }

    public void input(@Param(name = "3D projection") AbstractMatrix projection) {
        this.projection = projection;
    }

    public Projection3DModel output() {
        return model;
    }

    public void setTypeGlyph(TypeGlyph type) {
        typeGlyph = type;
    }

    public TypeGlyph getTypeGlyph() {
        return typeGlyph;
    }
    
    public static final long serialVersionUID = 1L;
    private TypeGlyph typeGlyph = TypeGlyph.SPHERE;

    private transient Projection3DModel model;
    private transient AbstractMatrix projection;
    private transient Projection3DModelParamView paramview;
}
