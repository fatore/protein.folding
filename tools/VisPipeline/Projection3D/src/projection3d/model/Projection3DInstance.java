
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.model;

import java.awt.Color;
import projection.model.ProjectionInstance;
import projection.model.Scalar;
import vtk.vtkDataArray;
import vtk.vtkPoints;


/**
 *
 * @author jpocom
 */
public class Projection3DInstance extends ProjectionInstance {

    public Projection3DInstance(Projection3DModel model, int id, float x, float y, float z) {
        super(model, id, x, y);
        this.z = z;
    }

    public Projection3DInstance(Projection3DModel model, int id) {
        this(model, id, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setScalarValue(Scalar scalar, float value) {
        super.setScalarValue(scalar, value);

        if (scalar != null) {
            ((Projection3DModel) model).getColorArray(scalar.getName()).InsertNextTuple4(0,0,0,0);
        }
    }

    @Override
    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
            Projection3DModel pm = (Projection3DModel)model;
            vtkDataArray active = pm.getColorArray(pm.getSelectedScalar().getName());
            active.SetTuple4(pm.getPosById(id), color.getRed(), color.getGreen(), color.getBlue(), pm.getAlpha()*255);
        }
    }

    public void updatePoint() {
        Projection3DModel pm = (Projection3DModel)model;
        vtkPoints points = pm.getPolydata().GetPoints();
        points.SetPoint(pm.getPosById(id), x, y, z);
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    protected float z;
}
