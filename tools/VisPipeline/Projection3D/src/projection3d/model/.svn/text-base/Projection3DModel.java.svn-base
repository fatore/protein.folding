/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.model;

import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import projection.model.ProjectionModel;
import projection.model.Scalar;
import vtk.vtkActor;
import vtk.vtkCell;
import vtk.vtkDataArray;
import vtk.vtkGlyph3D;
import vtk.vtkPointSource;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolyDataAlgorithm;
import vtk.vtkPolyDataMapper;
import vtk.vtkRenderer;
import vtk.vtkSphereSource;
import vtk.vtkSurfaceActor;
import vtk.vtkUnsignedCharArray;

/**
 *
 * @author jpocom
 */
public class Projection3DModel extends ProjectionModel {

    public enum TypeGlyph {

        SPHERE, POINT
    }

    public Projection3DModel(TypeGlyph type) {
        super();
        this.sphereRadius = 0.005f;
        this.alphaNS = 0.1f;
        this.name2colorArray = new HashMap<String, vtkUnsignedCharArray>();
        this.id2pos = new HashMap<Integer, Integer>();

        createVtkModel(type);
    }

    public void setRenderer(vtkRenderer renderer) {
        this.renderer = renderer;
    }
    
    private void createVtkModel(TypeGlyph type) {
        this.typeGlyph = type;
        if (type == TypeGlyph.SPHERE) {
            glyph = new vtkSphereSource();
            ((vtkSphereSource) glyph).SetPhiResolution(5);
            ((vtkSphereSource) glyph).SetThetaResolution(5);
            ((vtkSphereSource) glyph).SetRadius(sphereRadius);
        } else {
            glyph = new vtkPointSource();
            ((vtkPointSource) glyph).SetRadius(0);
            ((vtkPointSource) glyph).SetNumberOfPoints(1);
        }

        points = new vtkPoints();

        polydata = new vtkPolyData();
        polydata.SetPoints(points);

        glyphs = new vtkGlyph3D();
        glyphs.SetSource(glyph.GetOutput());
        glyphs.SetInput(polydata);
        glyphs.SetColorModeToColorByScalar();
        glyphs.SetScaleModeToDataScalingOff();
        glyphs.GeneratePointIdsOn();

        mapper = new vtkPolyDataMapper();
        mapper.SetInput(glyphs.GetOutput());

        actor = new vtkActor();
        actor.SetMapper(mapper);
    }

    // this fucntion is in charge of paint each glyph with an opcity given
    public void draw(vtkRenderer ren) {
        drawSurface(ren, surfacesRoot);
        
        boolean globalsel = selinstances.size() > 0 ? false : true;

        // set colors and opacities
        vtkDataArray activeColors = getColorArray(selscalar.getName());
        if (activeColors != null) {
            for (int i = 0; i < instances.size(); i++) {
                float opacity = instances.get(i).isSelected() || globalsel ? alpha * 254 : alphaNS * 254;
                activeColors.SetComponent(i, 3, opacity);
            }
        }
        polydata.GetPointData().SetScalars(getColorArray(selscalar.getName()));
        polydata.GetPointData().Modified();
    }

    public void drawSurface(vtkRenderer ren, DefaultMutableTreeNode currentNode) {
        if (currentNode.isLeaf() && !currentNode.isRoot()) {
            ren.AddActor((vtkSurfaceActor)currentNode.getUserObject());
        }

        Enumeration<DefaultMutableTreeNode> e = currentNode.children();
        while (e.hasMoreElements()) {
            drawSurface(ren, e.nextElement());
        }
    }

    public void addInstance(Projection3DInstance instance) {
        id2pos.put(instance.getId(), points.GetNumberOfPoints());
        points.InsertNextPoint(instance.getX(), instance.getY(), instance.getZ());
    }

    @Override
    public Scalar addScalar(String name) {
        Scalar scalar = new Scalar(name);

        if (!scalars.contains(scalar)) {
            scalars.add(scalar);

            vtkUnsignedCharArray colors = new vtkUnsignedCharArray();
            colors.SetNumberOfComponents(4);

            name2colorArray.put(name, colors);

            return scalar;
        } else {
            return scalars.get(scalars.indexOf(scalar));
        }
    }

    public vtkPolyData getPolydata() {
        return polydata;
    }

    public vtkActor getActor() {
        return actor;
    }

    public vtkUnsignedCharArray getColorArray(String name) {
        return name2colorArray.get(name);
    }

    public void setSphereRadius(float radius) {
        sphereRadius = radius;
        if (glyph instanceof vtkSphereSource) {
            ((vtkSphereSource) glyph).SetRadius(radius);
        }
        setChanged();
    }

    public double getSphereRadius() {
        return sphereRadius;
    }

    public TypeGlyph getTypeGlyph() {
        return typeGlyph;
    }

    @Override
    public void setSelectedScalar(Scalar scalar) {
        vtkUnsignedCharArray colors = getColorArray(scalar.getName());
        if (colors == null) {
            colors = new vtkUnsignedCharArray();
            colors.SetNumberOfComponents(4);
            colors.SetNumberOfTuples(instances.size());
            name2colorArray.put(scalar.getName(), colors);
        }

        super.setSelectedScalar(scalar);
    }

    public Projection3DInstance getInstancesById(int cellId) {
        vtkDataArray inputIds = glyphs.GetOutput().GetPointData().GetArray("InputPointIds");
        vtkCell pickedCell = glyphs.GetOutput().GetCell(cellId);
        int pnt = pickedCell.GetPointId(0);
        double inputId = inputIds.GetTuple1(pnt);
        return (Projection3DInstance) instances.get((int) inputId);
    }

    public Integer getPosById(Integer id) {
        return id2pos.get(id);
    }

    public void setAlphaNS(float alpha) {
        this.alphaNS = alpha;
        setChanged();
    }

    public float getAlphaNS() {
        return alphaNS;
    }

    public void removeSurface(DefaultMutableTreeNode currentNode) {
        Enumeration<DefaultMutableTreeNode> e = currentNode.breadthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (!node.isRoot())
                renderer.RemoveActor((vtkSurfaceActor)node.getUserObject());
        }
        /*
        System.err.println("hijos antes: "+surfacesRoot.getChildCount());
        if (currentNode.isRoot())
            currentNode.removeAllChildren();
        else {
            currentNode.removeFromParent();
        }
        
        System.err.println("hijos despues: "+surfacesRoot.getChildCount());
         * 
         */
        setChanged();
    }

    public void hideAllSurfaces() {
        Enumeration<DefaultMutableTreeNode> e = surfacesRoot.depthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (!node.isRoot())
                ((vtkSurfaceActor)node.getUserObject()).SetVisibility(0);
        }
        setChanged();
    }

    
    public DefaultMutableTreeNode getRoot() {
        return surfacesRoot;
    }

    private float alphaNS;
    private float sphereRadius;
    private TypeGlyph typeGlyph;
    private vtkPolyDataAlgorithm glyph;
    private vtkPoints points;
    private vtkPolyData polydata;
    private vtkGlyph3D glyphs;
    private vtkPolyDataMapper mapper;
    private vtkActor actor;
    private vtkRenderer renderer;
    private HashMap<String, vtkUnsignedCharArray> name2colorArray;
    private HashMap<Integer, Integer> id2pos;

    private DefaultMutableTreeNode surfacesRoot = new DefaultMutableTreeNode("Root");
}
