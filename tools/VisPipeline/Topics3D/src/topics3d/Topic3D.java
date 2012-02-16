/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topics3d;

import java.awt.Rectangle;
import java.util.ArrayList;
import topics.StringBox;
import topics3d.model.TopicProjection3DInstance;
import topics3d.model.TopicProjection3DModel;
import vtk.vtkActor;
import vtk.vtkCoordinate;
import vtk.vtkPolyDataMapper2D;
import vtk.vtkRenderer;
import vtk.vtkSurfaceActor;
import vtk.vtkTextActor;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Topic3D {

    public Topic3D(TopicProjection3DModel model, ArrayList<TopicProjection3DInstance> instances,
            ArrayList<StringBox> boxes) {
        this.model = model;
        this.boxes = boxes;
        this.show = false;
        this.instances = instances;
    }

    public void draw3DTopic(vtkRenderer renderer, java.awt.Font font) {
        if (surfaceActor != null) {
            renderer.AddActor(surfaceActor);
            String complete = "";

            if (boxes != null) {
                for (StringBox box : boxes) {
                    complete += box.getMessage() + "\n";
                }
            }
            vtkCoordinate coord = new vtkCoordinate();
            coord.SetCoordinateSystemToWorld();
            coord.SetValue(surfaceActor.GetCenter());

            text.SetInput(complete);
            text.GetTextProperty().BoldOn();
            text.GetTextProperty().SetFontFamilyToCourier();
            text.GetTextProperty().SetFontSize(18);
            text.GetTextProperty().ShadowOn();
            double[] temp = coord.GetComputedDoubleViewportValue(renderer);
            text.SetPosition(temp[0], temp[1]);

            vtkPolyDataMapper2D mapper = new vtkPolyDataMapper2D();
            text.SetMapper(mapper);

            renderer.AddActor(text);


            if (show) {
                surfaceActor.GetProperty().SetRepresentationToWireframe();
                text.VisibilityOn();
            } else {
                surfaceActor.GetProperty().SetRepresentationToSurface();
                text.VisibilityOff();
            }
        }
    }

    public boolean isShowTopic() {
        return show;
    }

    public void setShowTopic(boolean show) {
        this.show = show;
        model.setChanged();
    }

    public void setActor(vtkSurfaceActor actor) {
        this.surfaceActor = actor;
    }

    public vtkSurfaceActor getActor() {
        return surfaceActor;
    }

    @Override
    public String toString () {
        String rpt = " ";
        if (boxes!=null) {
            for (StringBox box : boxes) {
                rpt += box.getMessage()+"\n";
            }
        }
        return rpt;
    }

    private TopicProjection3DModel model;
    private ArrayList<StringBox> boxes;
    private ArrayList<TopicProjection3DInstance> instances;
    private Rectangle rectangle;
    private boolean show;
    protected vtkSurfaceActor surfaceActor = null;
    protected vtkTextActor text = new vtkTextActor();
}
