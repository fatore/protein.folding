/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.model;

import java.awt.Color;
import java.util.ArrayList;
import projection.model.ProjectionInstance;
import projection.model.Scalar;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkDataArray;

/**
 *
 * @author jpocom
 */
public class FiberInstance extends ProjectionInstance {

    public FiberInstance(FiberModel model, int id, int numPoints, int offset, Color col) {
        super(model, id);
        this.numPoints = numPoints;
        this.offset = offset;
        this.data = new float[numPoints * 3];
        this.scalars = new ArrayList<Float>();
        this.color = col;
    }

    public FiberInstance clone(FiberModel model) {
        FiberInstance inst = new FiberInstance(model, this.id, this.numPoints, this.offset, this.color);

        inst.data = new float[inst.data.length];
        System.arraycopy(inst.data, 0, this.data, 0, this.data.length);

        for (Float f : this.scalars) {
            inst.scalars.add(f);
        }

        return inst;
    }
    
    public float[] getPoint(int pos) {
        int step = pos * 3;
        return new float[]{data[step + 0], data[step + 1], data[step + 2]};
    }

    public double[] getPointDouble(int pos) {
        int step = pos * 3;
        return new double[]{data[step + 0], data[step + 1], data[step + 2]};
    }

    @Override
    public void setScalarValue(Scalar scalar, float value) {
        if (scalar != null) {
            int index = ((FiberModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index) {
                scalars.set(index, value);
            } else {
                int size = scalars.size();
                for (int i = 0; i < index - size; i++) {
                    scalars.add(0.0f);
                }
                scalars.add(value);
            }

            ((FiberModel)model).getColorArray(scalar.getName()).InsertNextTuple4(0,0,0,0);

            scalar.store(value);
        }
    }

    @Override
    public float getScalarValue(Scalar scalar) {
        if (scalar != null) {
            int index = ((FiberModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index && index > -1) {
                return scalars.get(index);
            }
        }

        return 0.0f;
    }

    @Override
    public float getNormalizedScalarValue(Scalar scalar) {
        if (scalar != null) {
            int index = ((FiberModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index && index > -1) {
                float value = scalars.get(index);
                return (value - scalar.getMin()) / (scalar.getMax() - scalar.getMin());
            }
        }

        return 0.0f;
    }

    @Override
    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
            FiberModel fm = (FiberModel)model;
            Scalar s = fm.getSelectedScalar();
            if (s==null) return;
            vtkDataArray active = fm.getColorArray(s.getName());
            active.SetTuple4(getId(), color.getRed(), color.getGreen(), color.getBlue(), fm.getAlphaSelected()*254);
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    public int getNumberOfPoints() {
        return numPoints;
    }
    
    protected int numPoints;
    protected int offset;
    protected float[] data;
    //protected ArrayList<Float> scalars;
    //protected Color color;
}
