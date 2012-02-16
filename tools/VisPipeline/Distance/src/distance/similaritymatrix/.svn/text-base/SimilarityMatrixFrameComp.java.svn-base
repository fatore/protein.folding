/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.similaritymatrix;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import visualizationbasics.coordination.IdentityCoordinator;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Distance.View",
name = "Similarity Matrix Frame",
description = "Display a similarity matrix.")
public class SimilarityMatrixFrameComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (model != null) {
            SimilarityMatrixFrame smf = new SimilarityMatrixFrame();
            smf.display(model);
            smf.setCoordinator(coordinator);
        }
    }

    public void input(@Param(name = "similarity matrix model") SimilarityMatrixModel model) {
        this.model = model;
    }

     public void attach(@Param(name = "identity coordinator") IdentityCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        model = null;
    }

    public static final long serialVersionUID = 1L;
    private transient SimilarityMatrixModel model;
    private transient IdentityCoordinator coordinator;
}
