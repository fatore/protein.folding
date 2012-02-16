/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume.model;

import visualizationbasics.model.*;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class VolumeInstance extends AbstractInstance {

    public VolumeInstance(VolumeModel model, int id, float intensity) {
        super(model, id);
        assert (!Float.isInfinite(intensity) && !Float.isNaN(intensity));
        this.intensity = intensity;
    }

    public VolumeInstance(VolumeModel model, int id) {
        super(model, id);
        this.intensity = 0.0f;
    }

    public float getIntensity() {
        return this.intensity;
    }

    public void setIntensity(float intensity) {
        assert (!Float.isInfinite(intensity) && !Float.isNaN(intensity));
        this.intensity = intensity;
        model.setChanged();
    }

    protected float intensity;
}
