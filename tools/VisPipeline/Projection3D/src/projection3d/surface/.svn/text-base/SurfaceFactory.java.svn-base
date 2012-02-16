/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.surface;

import java.util.ArrayList;
import projection3d.model.Projection3DInstance;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkSurfaceActor;


/**
 *
 * @author jpocom
 */
public class SurfaceFactory {

    public enum SurfaceType {
        
        CONVEX_HULL, DENSITY, FAST_DENSITY, SPLAT
    }

    public static vtkSurfaceActor getInstance(SurfaceType type, ArrayList<AbstractInstance> instances, String name) {
        double[][] ptrs = new double[instances.size()][3];
        for (int i = 0 ; i < instances.size(); ++i) {
            Projection3DInstance in3 = (Projection3DInstance) instances.get(i);
            ptrs[i][0] = in3.getX();
            ptrs[i][1] = in3.getY();
            ptrs[i][2] = in3.getZ();
        }
        return getInstance(type, ptrs, name);
    }

    public static vtkSurfaceActor getInstance(SurfaceType type, double[][] ptrs, String name) {

        switch(type) {
            case CONVEX_HULL:
                return new ConvexHullSurface(ptrs, name);
            case DENSITY:
                return new DensitySurface(ptrs, name);
            case FAST_DENSITY:
                return new FastDensitySurface(ptrs, name);
            case SPLAT:
                return new SplatSurface(ptrs, name);
        }
        return null;
    }
}
