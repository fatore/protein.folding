/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.surface;

import java.util.Arrays;
import vtk.vtkContourFilter;
import vtk.vtkDoubleArray;
import vtk.vtkFloatArray;
import vtk.vtkPolyDataMapper;
import vtk.vtkStructuredPoints;
import vtk.vtkSurfaceActor;
import vtk.vtkTransform;
import vtk.vtkTransformPolyDataFilter;


/**
 *
 * @author jpocom
 */
public class FastDensitySurface extends vtkSurfaceActor {

    public FastDensitySurface(double [][] points, String name) {
        super(name);
        createActor(points);
    }

    public void createActor(double[][] ptrs) {
        float[] cube = generateGrid(ptrs);
        
        vtkFloatArray scalars = new vtkFloatArray();
        scalars.SetJavaArray(cube);
        
        vtkStructuredPoints grid = new vtkStructuredPoints();
        grid.SetDimensions(volDims);
        grid.SetScalarTypeToFloat();
        grid.SetNumberOfScalarComponents(1);
        grid.GetPointData().SetScalars(scalars);

        vtkContourFilter con = new vtkContourFilter();
        con.SetInput(grid);
        double iso = isovalue;//kernel(isovalue);
        con.GenerateValues(1, iso, iso);
 /*
        vtkTransform trans = new vtkTransform();
        trans.Translate(min);
        trans.Scale(scal);
        
        vtkTransformPolyDataFilter ftrans = new vtkTransformPolyDataFilter();
        ftrans.SetInput(con.GetOutput());
        ftrans.SetTransform(trans);
*/
        vtkTransform scaleTranform = new vtkTransform();
        scaleTranform.Scale(scal);
        vtkTransformPolyDataFilter fscal = new vtkTransformPolyDataFilter();
        fscal.SetInput(con.GetOutput());
        fscal.SetTransform(scaleTranform);

        vtkTransform translationTransform = new vtkTransform();
        translationTransform.Translate(min);
        vtkTransformPolyDataFilter ftrans = new vtkTransformPolyDataFilter();
        ftrans.SetInput(fscal.GetOutput());
        ftrans.SetTransform(translationTransform);
  
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInput(ftrans.GetOutput());
        mapper.ScalarVisibilityOff();

        this.SetMapper(mapper);
    }

    private float[] generateGrid(double[][] ptrs) {
        findLimits(ptrs);

        double m = Math.max(max[2] - min[2], Math.max(max[0] - min[0], max[1] - min[1]));
        for (int i = 0; i < 3; i++) {
            volDims[i] = (int) ((max[i]-min[i]) / m * 100.);
        }
        System.out.println(volDims[0] + " " + volDims[1] + " " + volDims[1] + " ");

        for (int  i = 0; i < 3; i++) {
            min[i] -= (radius+2) * (max[i]-min[i]) / volDims[i];
            max[i] += (radius+2) * (max[i]-min[i]) / volDims[i];
            volDims[i] += 2*(radius + 2);
        }
        System.out.println("min: " + min[0] + " " + min[1] + " " + min[1]);
        System.out.println("max: " + max[0] + " " + max[1] + " " + max[1]);
        System.out.println("dims: " + volDims[0] + " " + volDims[1] + " " + volDims[1] + " ");

        for (int i = 0; i < 3; i++) {
            scal[i] = (max[i] - min[i]) / (volDims[i]);
        }

        float[] vol = new float[volDims[0]*volDims[1]*volDims[2]];
        Arrays.fill(vol, 0.f);

        //find point on grid belong support of region
        double[] c = new double[3];//center
        double rg = radius;
        int[] g_min = new int[3];
        int[] g_max = new int[3];

        int ix, iy, iz;
        for (int p = 0; p < ptrs.length; p++) {
            for (int q = 0; q < 3; q++) {
                c[q] = (ptrs[p][q] - min[q]) / (max[q]-min[q])*volDims[q];
            }
            g_max[0] = (int) (rg);
            for (int x = -g_max[0]; x <= g_max[0]; x++) {
                g_max[1] = (int) (Math.sqrt(rg * rg - x * x));
                for (int y = -g_max[1]; y <= g_max[1]; y++) {
                    g_max[2] = (int) (Math.sqrt(rg * rg - x * x - y * y));
                    for (int z = -g_max[2]; z <= g_max[2]; z++) {
                        ix = (int) (x + c[0]);
                        iy = (int) (y + c[1]);
                        iz = (int) (z + c[2]);
                        if ((ix >= 0 && ix < volDims[0]) && (iy >= 0 && iy < volDims[1]) && (iz >= 0 && iz < volDims[2])) {
                            vol[iz*volDims[0]*volDims[1] + iy*volDims[0] + ix] += Math.pow(1.0 - (x * x + y * y + z * z) / (rg * rg), 2);
                        }
                    }
                }
            }

        }
        return vol;
    }

     private double kernel(double x) {
        double re;
        if (x < -1.0) {
            re = 0.0;
        } else if (x < 1.0) {
            re = Math.pow(1.0 - x * x, 2);
        } else {
            re = 0.0;
        }
        return re;
    }

    private void findLimits(double[][] ptrs) {
        // obtenemos los boundary
        for (int i =0; i < 3; i++) {
            min[i] = ptrs[0][i];
            max[i] = ptrs[0][i];
        }

        for(double[] p: ptrs) {
            if(min[0] > p[0]) min[0] = p[0];
            if(min[1] > p[1]) min[1] = p[1];
            if(min[2] > p[2]) min[2] = p[2];
            if(max[0] < p[0]) max[0] = p[0];
            if(max[1] < p[1]) max[1] = p[1];
            if(max[2] < p[2]) max[2] = p[2];
        }
    }

    private double[] min = new double[3];
    private double[] max = new double[3];
    private double[] scal = new double[3];
    private int[] volDims = new int[3];

    public static double isovalue = 0.5;
    public static double radius = 5;
}
