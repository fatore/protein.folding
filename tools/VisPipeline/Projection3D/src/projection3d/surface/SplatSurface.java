/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection3d.surface;

import vtk.vtkContourFilter;
import vtk.vtkGaussianSplatter;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolyDataMapper;
import vtk.vtkProgrammableSource;
import vtk.vtkReverseSense;
import vtk.vtkSurfaceActor;

/**
 *
 * @author jpocom
 */
public class SplatSurface extends vtkSurfaceActor {

    public SplatSurface(double [][] points, String name) {
        super(name);
        this.points = points;

        pointSource = new vtkProgrammableSource();
        pointSource.SetExecuteMethod(this, "convert");

        vtkGaussianSplatter splatter = new vtkGaussianSplatter();
        splatter.SetInput(pointSource.GetPolyDataOutput());
        splatter.SetSampleDimensions(100, 100, 100);
        splatter.SetRadius(1);
        splatter.Update();

   
        vtkContourFilter cf = new vtkContourFilter();
        cf.SetInput(splatter.GetOutput());
        cf.SetValue(0, 0.995);
        //cf.GenerateValues(1, 10, 10);

        vtkReverseSense reverse = new vtkReverseSense();
        reverse.SetInput(cf.GetOutput());
        reverse.ReverseCellsOn();
        reverse.ReverseNormalsOn();

        vtkPolyDataMapper map = new vtkPolyDataMapper();
        map.SetInput(reverse.GetOutput());

        this.SetMapper(map);
    }

    private void convert() {
        output = pointSource.GetPolyDataOutput();

        vtkPoints ps = new vtkPoints();
        output.SetPoints(ps);

        ps.SetNumberOfPoints(points.length);
        for (int i = 0; i < points.length; i++) {
            ps.SetPoint(i, points[i]);
        }

    }

    private double[][] points;
    private vtkPolyData output;
    private vtkProgrammableSource pointSource;
}
