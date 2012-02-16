/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume.model;

import java.io.IOException;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Volume.Basics",
name = "Volume Model",
description = "Create a volume model to be visualized.")
public class VolumeModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (volume != null) {
            model = new VolumeModel();
            System.out.println("# Volume Instances: " + volume.length);
            for (int i = 0; i < volume.length; i++) {
                VolumeInstance vi = new VolumeInstance(model, i, volume[i]);
            }
        } else {
            throw new IOException("A volume should be provided.");
        }
    }

    public void input(@Param(name = "Volume intensities") float[] volume) {
        this.volume = volume;
    }

    public VolumeModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        model = null;
    }

    private transient VolumeModel model;
    private transient float[] volume;
}
