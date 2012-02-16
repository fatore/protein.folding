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
name = "Volume BMP reader",
description = "Read a volume from a series of BMP files.")
public class BMPVolumeReaderComp implements AbstractComponent {

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
        System.out.println("BMP Comp execute");
        vtkBMPReader reader = new vtkBMPReader();
        reader.SetDataScalarType(5); //Unsigned SHORT
        reader.SetNumberOfScalarComponents(1);
        reader.SetDataByteOrderToLittleEndian();
        reader.SetDataScalarTypeToUnsignedShort();
        reader.SetDataExtent(X1, X2, Y1, Y2, Z1, Z2);
        //Xinicial Xfinal  Yinicial Yfinal    ZInicial ZFinal
        //    LARGURA          ALTURA         NUMERO DE FATIAS
        reader.SetDataSpacing(X, Y, Z); //espaçamento da célula em X, Y e Z
        reader.SetFilePattern(directory + "/" + filepattern);
        //"%s.%02d.bmp"); //padrão do nome do arquivo, no caso 2 digitos
        reader.SetDataOrigin(0, 0, 0);
        //reader.Allow8BitBMPOn();
        reader.Update();
        imageData = reader.GetOutput();
        int size = (X2-X1+1) * (Y2-Y1+1) * (Z2-Z1+1);
        volume = new float[size];
        System.out.println(size);
        System.out.println(volume.length);

        //desnecessario o resto
        double rB[] = reader.GetOutput().GetScalarRange();
        System.out.println("Range[0]: " + rB[0]);
        System.out.println("Range[1]: " + rB[1]);
        System.out.println("Byte Order: " + reader.GetDataByteOrderAsString());
        System.out.println("Scalar Type: " + reader.GetDataScalarType());
        System.out.println("Scalar Components: " + reader.GetNumberOfScalarComponents());

        System.out.println("Numero de Celulas: " + reader.GetOutput().GetNumberOfCells());
        System.out.println("Numero de Pontos: " + reader.GetOutput().GetNumberOfPoints());
        System.out.println("Ponto: " + reader.GetOutput().FindPoint(40, 20, 20));
        vtkPointData pd = reader.GetOutput().GetPointData();
        vtkDataArray da = pd.GetScalars();
        System.out.println("Num Tuplas: " + da.GetNumberOfTuples());
        System.out.println("Num Comps: " + da.GetNumberOfComponents());

        System.out.println("AFTER COMPONENT SELECTION");

        vtkImageExtractComponents iEC = new vtkImageExtractComponents();
        iEC.SetInput(reader.GetOutput());
        iEC.SetComponents(1);
        iEC.Update();
        System.out.println("Numero de Celulas: " + iEC.GetOutput().GetNumberOfCells());
        System.out.println("Numero de Pontos: " + iEC.GetOutput().GetNumberOfPoints());
        System.out.println("Ponto: " + iEC.GetOutput().FindPoint(40, 20, 20));
        pd = iEC.GetOutput().GetPointData();
        da = pd.GetScalars();
        System.out.println("Num Tuplas: " + da.GetNumberOfTuples());
        System.out.println("Num Comps: " + da.GetNumberOfComponents());
        System.out.println("Ponto: " + da.GetComponent(470, 1));
    }

    @Override
    public void reset() {
        volume = null;
        imageData = null;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new BMPVolumeReaderParamView(this);
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

    public void setX1(int X1) {
        this.X1 = X1;
    }

    public void setX2(int X2) {
        this.X2 = X2;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void setY1(int Y1) {
        this.Y1 = Y1;
    }

    public void setZ(int Z) {
        this.Z = Z;
    }

    public void setZ1(int Z1) {
        this.Z1 = Z1;
    }

    public void setZ2(int Z2) {
        this.Z2 = Z2;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setFilepattern(String filepattern) {
        this.filepattern = filepattern;
    }

    public void setY2(int y2) {
        this.Y2 = y2;
    }

    public int getX() {
        return X;
    }

    public int getX1() {
        return X1;
    }

    public int getX2() {
        return X2;
    }

    public int getY() {
        return Y;
    }

    public int getY1() {
        return Y1;
    }

    public int getZ() {
        return Z;
    }

    public int getZ1() {
        return Z1;
    }

    public int getZ2() {
        return Z2;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFilepattern() {
        return filepattern;
    }

    public int getY2() {
        return Y2;
    }
    
    private String directory;
    private String filepattern;
    private int X;
    private int Y;
    private int Z;
    private int X1;
    private int X2;
    private int Y1;
    private int Y2;
    private int Z1;
    private int Z2;
    private transient float[] volume;
    private transient vtkImageData imageData;
    private transient BMPVolumeReaderParamView paramview;
}
