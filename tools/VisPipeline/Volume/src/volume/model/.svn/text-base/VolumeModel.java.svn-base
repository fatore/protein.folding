/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume.model;

import visualizationbasics.model.*;
import vtk.vtkImageData;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class VolumeModel extends AbstractModel {

    static {
        System.loadLibrary("vtkCommon");
        System.loadLibrary("vtkFiltering");
        System.loadLibrary("vtkGraphics");
        System.loadLibrary("vtkImaging");
        System.loadLibrary("vtksys");
        System.loadLibrary("vtkexpat");
        System.loadLibrary("vtkfreetype");
        System.loadLibrary("vtkftgl");
        System.loadLibrary("vtkjpeg");
        System.loadLibrary("vtkzlib");
        System.loadLibrary("vtktiff");
        System.loadLibrary("vtkpng");
        System.loadLibrary("vtkDICOMParser");
        System.loadLibrary("vtkIO");
        System.loadLibrary("vtkRendering");

        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public VolumeModel() {
    }

    public void draw(vtkImageData imData) {
        int ids[] = new int[instances.size()];
        double values[] = new double[instances.size()];
        int tl = 0; //logical size

        for (AbstractInstance vi : instances) {
            ids[tl] = vi.getId();
            if (vi.isSelected()) {
                values[tl] = 255.0;
            } else {
                values[tl] = 0.0;
            }
            tl++;
        }

        for (int x = 0; x < tl; x++) {
            imData.GetPointData().GetScalars().SetComponent(ids[x], 0, values[x]);
        }

        System.out.println("End of Update in VolumeModel.java");
    }

}
