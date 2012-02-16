/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.util;

import vtk.vtkActor;
import vtk.vtkArrowSource;
import vtk.vtkCaptionActor2D;
import vtk.vtkCubeSource;
import vtk.vtkPolyDataMapper;
import vtk.vtkPropAssembly;
import vtk.vtkRenderer;
import vtk.vtkTextActor;
import vtk.vtkVectorText;

/**
 *
 * @author jpocom
 */
public class AxesActor extends vtkPropAssembly {

    public AxesActor(vtkRenderer _ren) {
        super();
        ren = _ren;
        createAxes();
        createCube();
    }

    public void createAxes() {
        vtkArrowSource xAxis = new vtkArrowSource();
        vtkPolyDataMapper xAxisMapper = new vtkPolyDataMapper();
        xAxisMapper.SetInput(xAxis.GetOutput());
        vtkActor xAxisActor = new vtkActor();
        xAxisActor.SetMapper(xAxisMapper);
        xAxisActor.GetProperty().SetColor(1, 0, 0);

        vtkArrowSource yAxis = new vtkArrowSource();
        vtkPolyDataMapper yAxisMapper = new vtkPolyDataMapper();
        yAxisMapper.SetInput(yAxis.GetOutput());
        vtkActor yAxisActor = new vtkActor();
        yAxisActor.SetMapper(yAxisMapper);
        yAxisActor.RotateZ(90);
        yAxisActor.GetProperty().SetColor(1, 1, 0);

        vtkArrowSource zAxis = new vtkArrowSource();
        vtkPolyDataMapper zAxisMapper = new vtkPolyDataMapper();
        zAxisMapper.SetInput(zAxis.GetOutput());
        vtkActor zAxisActor = new vtkActor();
        zAxisActor.SetMapper(zAxisMapper);
        zAxisActor.RotateY(-90);
        zAxisActor.GetProperty().SetColor(0, 1, 0);

        vtkCaptionActor2D xLabel = new vtkCaptionActor2D();
        xLabel.SetCaption("X");
        xLabel.SetAttachmentPoint(1, 0, 0);
        xLabel.LeaderOff();
        xLabel.BorderOff();
        xLabel.SetPosition(0, 0);

        vtkCaptionActor2D yLabel = new vtkCaptionActor2D();
        yLabel.SetCaption("Y");
        yLabel.SetAttachmentPoint(0, 1, 0);
        yLabel.LeaderOff();
        yLabel.BorderOff();
        yLabel.SetPosition(0, 0);

        vtkCaptionActor2D zLabel = new vtkCaptionActor2D();
        zLabel.SetCaption("Z");
        zLabel.SetAttachmentPoint(0, 0, 1);
        zLabel.LeaderOff();
        zLabel.BorderOff();
        zLabel.SetPosition(0, 0);

        this.AddPart(xAxisActor);
        this.AddPart(yAxisActor);
        this.AddPart(zAxisActor);
        this.AddPart(xLabel);
        this.AddPart(yLabel);
        this.AddPart(zLabel);



        ren.AddActor(this);
    }


    public void createCube() {
        vtkCubeSource cube = new vtkCubeSource();
        vtkPolyDataMapper cubeMapper = new vtkPolyDataMapper();
        cubeMapper.SetInput(cube.GetOutput());
        vtkActor cubeActor = new vtkActor();
        cubeActor.SetMapper(cubeMapper);
        cubeActor.GetProperty().SetRepresentationToWireframe();
        cubeActor.GetProperty().SetDiffuse(0);
        cubeActor.GetProperty().SetAmbient(1);

        vtkVectorText anteText = new vtkVectorText();
        anteText.SetText("A");
        vtkPolyDataMapper anteMapper = new vtkPolyDataMapper();
        anteMapper.SetInput(anteText.GetOutput());
        vtkActor anteActor = new vtkActor();
        anteActor.SetMapper(anteMapper);
        anteActor.SetScale(0.5, 0.5, 0.5);
        anteActor.SetPosition(0.5, -0.30, -0.25);
        anteActor.SetOrientation(90, 0, 90);
        anteActor.GetProperty().SetColor(1, 1, 1);
        anteActor.GetProperty().BackfaceCullingOn();

        vtkVectorText postText = new vtkVectorText();
        postText.SetText("P");
        vtkPolyDataMapper postMapper = new vtkPolyDataMapper();
        postMapper.SetInput(postText.GetOutput());
        vtkActor postActor = new vtkActor();
        postActor.SetMapper(postMapper);
        postActor.SetScale(0.5, 0.5, 0.5);
        postActor.SetPosition(-0.5, 0.25, -0.25);
        postActor.SetOrientation(90, 0, -90);
        postActor.GetProperty().SetColor(1, 1, 1);
        postActor.GetProperty().BackfaceCullingOn();

        vtkVectorText leftText = new vtkVectorText();
        leftText.SetText("L");
        vtkPolyDataMapper leftMapper = new vtkPolyDataMapper();
        leftMapper.SetInput(leftText.GetOutput());
        vtkActor leftActor = new vtkActor();
        leftActor.SetMapper(leftMapper);
        leftActor.SetScale(0.5, 0.5, 0.5);
        leftActor.SetPosition(0.25, 0.5, -0.25);
        leftActor.SetOrientation(90, 0, 180);
        leftActor.GetProperty().SetColor(1, 1, 1);
        leftActor.GetProperty().BackfaceCullingOn();

        vtkVectorText rightText = new vtkVectorText();
        rightText.SetText("R");
        vtkPolyDataMapper rightMapper = new vtkPolyDataMapper();
        rightMapper.SetInput(rightText.GetOutput());
        vtkActor rightActor = new vtkActor();
        rightActor.SetMapper(rightMapper);
        rightActor.SetScale(0.5, 0.5, 0.5);
        rightActor.SetPosition(-0.25, -0.5, -0.25);
        rightActor.SetOrientation(90, 0, 0);
        rightActor.GetProperty().SetColor(1, 1, 1);
        rightActor.GetProperty().BackfaceCullingOn();

        vtkVectorText superiorText = new vtkVectorText();
        superiorText.SetText("S");
        vtkPolyDataMapper superiorMapper = new vtkPolyDataMapper();
        superiorMapper.SetInput(superiorText.GetOutput());
        vtkActor superiorActor = new vtkActor();
        superiorActor.SetMapper(superiorMapper);
        superiorActor.SetScale(0.5, 0.5, 0.5);
        superiorActor.SetPosition(-0.25, 0.25, 0.5);
        superiorActor.SetOrientation(0, 0, -90);
        superiorActor.GetProperty().SetColor(1, 1, 1);
        superiorActor.GetProperty().BackfaceCullingOn();


        vtkVectorText inferiorText = new vtkVectorText();
        inferiorText.SetText("I");
        vtkPolyDataMapper inferiorMapper = new vtkPolyDataMapper();
        inferiorMapper.SetInput(inferiorText.GetOutput());
        vtkActor inferiorActor = new vtkActor();
        inferiorActor.SetMapper(inferiorMapper);
        inferiorActor.SetScale(0.5, 0.5, 0.5);
        inferiorActor.SetPosition(-0.25, -0.25, -0.5);
        inferiorActor.SetOrientation(180, 0, 90);
        inferiorActor.GetProperty().SetColor(1, 1, 1);
        inferiorActor.GetProperty().BackfaceCullingOn();

        this.AddPart(cubeActor);
        this.AddPart(anteActor);
        this.AddPart(postActor);
        this.AddPart(leftActor);
        this.AddPart(rightActor);
        this.AddPart(superiorActor);
        this.AddPart(inferiorActor);

                ren.AddActor(this);
    }

    public void setAxesVisibility(boolean ison) {
        this.SetVisibility(ison ? 1 : 0);
        xactor.SetVisibility(ison ? 1 : 0);
        yactor.SetVisibility(ison ? 1 : 0);
        zactor.SetVisibility(ison ? 1 : 0);
    }
    
    private vtkRenderer ren;
    private double axisLength = 0.8;
    private double axisTextLength = 1.2;
    private vtkTextActor xactor, yactor, zactor;
}
