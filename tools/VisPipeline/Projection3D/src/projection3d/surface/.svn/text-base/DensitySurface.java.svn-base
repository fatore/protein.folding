/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.surface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import vtk.vtkContourFilter;
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
public class DensitySurface extends vtkSurfaceActor {

    public DensitySurface(double[][] points, String name) {
        super(name);
        createActor(points);
    }

    public void createActor(double[][] ptrs) {
        int[] dims = new int[3];
        double[] origin = new double[3];
        double[] scale = new double[3];
        float[] cubo = generateGrid(ptrs, dims, origin, scale);

        vtkFloatArray scalars = new vtkFloatArray();
        scalars.SetJavaArray(cubo);

        vtkStructuredPoints grid = new vtkStructuredPoints();
        grid.SetDimensions(dims);
        grid.SetScalarTypeToFloat();
        grid.SetNumberOfScalarComponents(1);
        grid.GetPointData().SetScalars(scalars);

        vtkContourFilter con = new vtkContourFilter();
        con.SetInput(grid);
        double iso = kernel(P);
        con.GenerateValues(1, iso, iso);

        vtkTransform scal = new vtkTransform();
        scal.Scale(scale);
        vtkTransformPolyDataFilter fscal = new vtkTransformPolyDataFilter();
        fscal.SetInput(con.GetOutput());
        fscal.SetTransform(scal);

        vtkTransform trans = new vtkTransform();
        trans.Translate(origin);
        vtkTransformPolyDataFilter ftrans = new vtkTransformPolyDataFilter();
        ftrans.SetInput(fscal.GetOutput());
        ftrans.SetTransform(trans);

        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInput(ftrans.GetOutput());
        mapper.ScalarVisibilityOff();

        this.SetMapper(mapper);
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

    private void findBoundingBox(double[][] ptrs, double[] min, double[] max) {
        for (int i = 0; i < 3; i++) {
            min[i] = ptrs[0][i];
            max[i] = ptrs[0][i];
        }

        for (double[] p : ptrs) {
            if (min[0] > p[0]) min[0] = p[0];
            if (min[1] > p[1]) min[1] = p[1];
            if (min[2] > p[2]) min[2] = p[2];
            if (max[0] < p[0]) max[0] = p[0];
            if (max[1] < p[1]) max[1] = p[1];
            if (max[2] < p[2]) max[2] = p[2];
        }
    }

    private float[] generateGrid(double[][] ptrs, int[] dims, double[] origin, double[] scale) {
        double radius = longestEdgesMST(ptrs);
        scale[0] = scale[1] = scale[2] = A * radius;
        return scalarFunction(ptrs, radius, dims, origin, scale);
    }

    private float[] scalarFunction(
            double[][] points,
            double radius,
            int[] dims,
            double[] origin,
            double scale[]) {
        //compute minimum radius radius such that \cup i=1^numberOfPoints B(x_i,radius) connected
        double[] lower = new double[3];
        double[] upper = new double[3];
        findBoundingBox(points, lower, upper);
        origin[0] = lower[0] - radius;
        origin[1] = lower[1] - radius;
        origin[2] = lower[2] - radius;

        int nx = dims[0] = (int) ((upper[0] - lower[0] + 2*radius) / scale[0]) + 2;
        int ny = dims[1] = (int) ((upper[1] - lower[1] + 2*radius) / scale[1]) + 2;
        int nz = dims[2] = (int) ((upper[2] - lower[2] + 2*radius) / scale[2]) + 2;
        System.err.println(nx + "  " + ny + " " + nz);

        float[] f = new float[nx * ny * nz];
        
        //find point on grid belong support of region
        double[] c = new double[3];//center
        double rg = radius / scale[0];
        int[] g_min = new int[3];
        int[] g_max = new int[3];

        int ix, iy, iz;
        //for (int p = 0; p < numberOfPoints; p++) {
        for (double[] point: points) {
            for (int q = 0; q < 3; q++) {
                c[q] = (point[q] - lower[q] + radius) / scale[q];
            }
            g_max[0] = (int) (rg);
            for (int x = -g_max[0]; x < g_max[0]; x++) {
                g_max[1] = (int) (Math.sqrt(rg * rg - x * x));
                for (int y = -g_max[1]; y < g_max[1]; y++) {
                    g_max[2] = (int) (Math.sqrt(rg * rg - x * x - y * y));
                    for (int z = -g_max[2]; z < g_max[2]; z++) {
                        ix = (int) (x + c[0]);
                        iy = (int) (y + c[1]);
                        iz = (int) (z + c[2]);
                        if ((ix >= 0 && ix < nx) && (iy >= 0 && iy < ny) && (iz >= 0 && iz < nz)) {
                            f[iz * nx * ny + iy * nx + ix] += Math.pow(1.0 - (x * x + y * y + z * z) / (rg * rg), 3);
                        }
                    }
                }
            }

        }

        return f;
    }

    private double length(double[] a, double[] b) {
        int m = a.length;
        double s = 0.0;
        for (int i = 0; i < m; i++) {
            s += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(s);
    }

    private double longestEdgesMST(double[][] points) {
        double r;
        ArrayList<WE> g = new ArrayList<WE>();

        Set<Integer> mst = new HashSet<Integer>();

        int n = points.length;
        if (n == 1) {
            r = 0.0;
        } else if (n == 2) {
            r = length(points[0], points[1]);
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    WE e = new WE();
                    e.first = i;
                    e.second = j;
                    e.weight = length(points[i], points[j]);
                    g.add(e);
                }
            }
            Collections.sort(g);
            for (int i = 0; i < n - 1; i++) {
                mst.add(g.get(i).first);
                mst.add(g.get(i).second);
            }
            int k = n - 2;
            while (mst.size() != n) {
                k += 1;
                mst.add(g.get(k).first);
                mst.add(g.get(k).second);
            }
            r = g.get(k).weight;
        }
        mst.clear();
        g.clear();
        return r;
    }
    private double A = 0.1;
    private double P = 0.5;
}

class WE implements Comparable {

    public double weight;
    public int first;
    public int second;

    @Override
    public int compareTo(Object o) {
        if (this.weight == ((WE) o).weight) {
            return 0;
        } else if (this.weight > ((WE) o).weight) {
            return 1;
        } else {
            return -1;
        }
    }
}
