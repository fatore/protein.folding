/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume.model;

import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vtk.*;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Volume.Input",
name = "Empty volume reader",
description = "Read an empty volume.")
public class EmptyVolumeReaderComp implements AbstractComponent {

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

    @Override
    public void execute() {
        imageData = new vtkStructuredPoints();
        imageData.SetDimensions(width, height, depth);
        imageData.SetExtent(0, width - 1, 0, height - 1, 0, depth - 1);
        imageData.SetSpacing(X, Y, Z);
        imageData.SetScalarType(5);
        imageData.SetNumberOfScalarComponents(1);
        imageData.SetScalarTypeToUnsignedShort();
        imageData.SetOrigin(0, 0, 0);

        int size = width * height * depth;

        vtkFloatArray fArray = new vtkFloatArray();
        fArray.SetJavaArray(new float[size]);

        imageData.GetPointData().SetScalars(fArray);
        imageData.Update();

        volume = new float[size];
    }

    @Override
    public void reset() {
        volume = null;
        imageData = null;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new EmptyVolumeReaderParamView(this);
        }
        return paramview;
    }

    public float[] outputVolume() {
        return this.volume;
    }

    public vtkImageData outputImageData() {
        return imageData;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void setZ(int Z) {
        this.Z = Z;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getZ() {
        return Z;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    private int X = 1;
    private int Y = 1;
    private int Z = 1;
    private int width = 150;
    private int height = 60;
    private int depth = 60;
    private transient float[] volume;
    private transient vtkImageData imageData;
    private transient EmptyVolumeReaderParamView paramview;
}
