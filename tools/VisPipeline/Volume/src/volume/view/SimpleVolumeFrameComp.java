/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume.view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.coordination.AbstractCoordinator;
import volume.model.VolumeModel;
import vtk.vtkImageData;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Volume.View",
name = "Volume View Frame",
description = "Display a volume model.")
public class SimpleVolumeFrameComp implements AbstractComponent {

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
    public void execute() throws IOException {
        if (model != null) {
            if (imageData != null) {
                SimpleVolumeFrame frame = new SimpleVolumeFrame();
                frame.setSize(800, 800);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.setModel(model);
                frame.setImageData(imageData);
                // frame.setCoordinator(coordinator);
                if (coordinators != null) {
                    for (int i = 0; i < coordinators.size(); i++) {
                        frame.addCoordinator(coordinators.get(i));
                    }
                }
            } else {
                throw new IOException("An image data should be provided.");
            }
        } else {
            throw new IOException("A projection model should be provided.");
        }
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        model = null;
        imageData = null;
    }

    public void input(@Param(name = "volume model") VolumeModel model,
            @Param(name = "image data") vtkImageData imageData) {
        this.model = model;
        this.imageData = imageData;
    }

    public void attach(@Param(name = "Coordinator") AbstractCoordinator coordinator) {
        if (coordinators == null) {
            coordinators = new ArrayList<AbstractCoordinator>();
        }

        if (coordinator != null) {
            coordinators.add(coordinator);
        }
    }

    private transient vtkImageData imageData;
    private transient VolumeModel model;
    private transient ArrayList<AbstractCoordinator> coordinators;
}
