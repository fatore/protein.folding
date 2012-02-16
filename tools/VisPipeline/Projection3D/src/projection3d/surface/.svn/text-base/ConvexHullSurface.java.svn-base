/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.surface;

import java.util.ArrayList;
import projection3d.surface.convexhull.Point3d;
import projection3d.surface.convexhull.QuickHull3D;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkCellArray;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolyDataMapper;
import vtk.vtkSurfaceActor;


/**
 *
 * @author jpocom
 */
public class ConvexHullSurface extends vtkSurfaceActor {

    public ConvexHullSurface(double[][] ptrs, String name) {
        super(name);
        
        Point3d[] pointsClusterAux = new Point3d[ptrs.length];
        for (int i = 0; i < ptrs.length; i++) {
            pointsClusterAux[i] = new Point3d(ptrs[i]);
        }

        createActor(pointsClusterAux);
    }

    public void createActor(Point3d[] pointsClusterAux) {
        QuickHull3D hull = new QuickHull3D();
        hull.build(pointsClusterAux);
        
        Point3d[] vertices = hull.getVertices();
        int[][] faceIndices = hull.getFaces();

        // create the vtkPolyData, representing the convexHull
        vtkPoints pointsCH = new vtkPoints();
        for (int i = 0; i < vertices.length; i++) {
            pointsCH.InsertPoint(i, vertices[i].x, vertices[i].y, vertices[i].z);
        }

        vtkCellArray polys = new vtkCellArray();
        for (int i = 0; i < faceIndices.length; i++) {
            polys.InsertNextCell(faceIndices[i].length);
            for (int k = 0; k < faceIndices[i].length; k++) {
                polys.InsertCellPoint(faceIndices[i][k]);
            }
        }

        // Polydata
        vtkPolyData hulli = new vtkPolyData();
        hulli.SetPoints(pointsCH);
        hulli.SetPolys(polys);

        // PolydataMapper
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInput(hulli);

        this.SetMapper(mapper);
    }
    
    @Override
    public ArrayList<AbstractInstance> getInstances() {
        return instances;
    }

    @Override
    public void setInstances(ArrayList<AbstractInstance> insts) {
        this.instances = insts;
    }

    private ArrayList<AbstractInstance> instances;
    private String name;
}
