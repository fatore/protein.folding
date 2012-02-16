/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.awt.Color;
import java.util.ArrayList;
import projection.model.ProjectionModel;
import projection.model.Scalar;
import tensor.view.selectedview.SelectedFiberRepresentation;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;
import visualizationbasics.color.ColorTable;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkActor;
import vtk.vtkDataArray;
import vtk.vtkPolyData;
import vtk.vtkPolyDataMapper;
import vtk.vtkRenderer;
import vtk.vtkSurfaceActor;
import vtk.vtkUnsignedCharArray;

/**
 *
 * @author jpocom
 */
//public class FiberModel extends AbstractModel {
public class FiberModel extends ProjectionModel {

    public FiberModel() {
        polydata = new vtkPolyData();

        mapper = new vtkPolyDataMapper();
        mapper.SetInput(polydata);

        actor = new vtkActor();
        actor.SetMapper(mapper);
        mapper.SetScalarVisibility(1);
    }

    public void draw(vtkRenderer ren) {

        if (surfaces != null) {
            for (vtkActor act: surfaces) {
                ren.AddActor(act);
            }
        }


        boolean globalsel = selinstances.size() > 0 ? false : true;
        vtkDataArray activeColors = getColorArray(selscalar.getName());
        int pos = 0;
        for (AbstractInstance inst: instances) {
            FiberInstance fiber = (FiberInstance)inst;
            float opacity = fiber.isSelected() || globalsel ? alphaS*254 : alphaNS*254;
            activeColors.SetComponent(pos++, 3, opacity);
        }
        polydata.GetCellData().SetActiveScalars(selscalar.getName());
    }

    public vtkUnsignedCharArray getColorArray(String name) {
        return (vtkUnsignedCharArray)polydata.GetCellData().GetScalars(name);
    }

    @Override
    public Scalar addScalar(String name) {
        Scalar scalar = new Scalar(name);
        if (!scalars.contains(scalar)) {
            scalars.add(scalar);

            vtkUnsignedCharArray colors = new vtkUnsignedCharArray();
            colors.SetName(name);
            colors.SetNumberOfComponents(4);
            polydata.GetCellData().AddArray(colors);
            
            return scalar;
        } else {
            return scalars.get(scalars.indexOf(scalar));
        }
    }

    @Override
    public ArrayList<Scalar> getScalars() {
        return scalars;
    }

    @Override
    public Scalar getSelectedScalar() {
        return selscalar;
    }

    @Override
    public void setSelectedScalar(Scalar scalar) {
        if (scalars.contains(scalar)) {
            selscalar = scalar;

            //change the color of each instance
            for (int i = 0; i < instances.size(); i++) {
                FiberInstance pi = (FiberInstance)instances.get(i);
                if (scalar.getMin() >= 0.0f && scalar.getMax() <= 1.0f) {
                    pi.setColor(colortable.getColor(pi.getScalarValue(scalar)));
                } else {
                    pi.setColor(colortable.getColor(pi.getNormalizedScalarValue(scalar)));
                }
            }
        } else {
            selscalar = null;

            //change the color of each instance
            for (int i = 0; i < instances.size(); i++) {
                FiberInstance pi = (FiberInstance)instances.get(i);
                pi.setColor(Color.YELLOW);
            }
        }
        setChanged();
    }

    public vtkPolyData getPolydata() {
        return polydata;
    }

    public float getAlphaSelected() {
        return alphaS;
    }

    public void setAlphaSelected(float alpha) {
        this.alphaS = alpha;
        setChanged();
    }

    public float getAlphaNotSelected() {
        return alphaNS;
    }

    public void setAlphaNotSelected(float alpha) {
        this.alphaNS = alpha;
        setChanged();
    }

    public void changeColorScaleType(ColorScaleType scaletype) {
        colortable.setColorScaleType(scaletype);
        setSelectedScalar(selscalar);
        setChanged();
    }

    public ColorTable getColorTable() {
        return colortable;
    }

    public vtkActor getActor() {
        return actor;
    }

    public FiberInstance getInstancesById(int cellId) {
        return (FiberInstance)instances.get(cellId);
    }

    public TRKHeader getHeader() {
        return header;
    }

    public void setHeader(TRKHeader header) {
        this.header = header;
    }

    public ArrayList<vtkSurfaceActor> getSurfaces() {
        return surfaces;
    }

    public void addSurface(vtkSurfaceActor act) {
        if (act != null) {
            surfaces.add(act);
        }
        setChanged();
    }

    public void removeAllSurfaces(vtkRenderer ren) {
        for (vtkSurfaceActor act: surfaces) {
            ren.RemoveActor(act);
        }
        surfaces.clear();
        setChanged();
    }

    public void removeSurfaces(vtkRenderer ren, ArrayList<vtkSurfaceActor> acts) {
        for (vtkSurfaceActor act: acts) {
            ren.RemoveActor(act);
            surfaces.remove(act);
        }
        setChanged();
    }

    public void changeColorSurfaces(ArrayList<vtkSurfaceActor> acts, Color color) {
        for (vtkSurfaceActor act: acts) {
            act.GetProperty().SetColor(color.getRed()/256., color.getGreen()/256., color.getBlue()/256.);
        }
        setChanged();
    }

    @Override
    public void setSelectedInstances(ArrayList<AbstractInstance> selinst) {
        super.setSelectedInstances(selinst);

        if (selectedView != null) {
            ArrayList<FiberInstance> insts = new ArrayList<FiberInstance>();
            for (AbstractInstance i: selinst) {
                insts.add((FiberInstance)i);
            }
            selectedView.selected(insts);
        }
    }

    public void setSelectedView(SelectedFiberRepresentation setSelectedView) {
        this.selectedView = setSelectedView;
    }

    private TRKHeader header = new TRKHeader();
    private vtkPolyData polydata;
    private vtkPolyDataMapper mapper;
    private vtkActor actor;

    private float alphaS = 1.0f;
    private float alphaNS = 0.1f;
    //private ArrayList<Scalar> scalars = new ArrayList<Scalar>();
    //private ColorTable colortable = new ColorTable();
    //private Scalar selscalar = null;
    private ArrayList<vtkSurfaceActor> surfaces = new ArrayList<vtkSurfaceActor>();
    private SelectedFiberRepresentation selectedView = null;
}
