/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.radomproj;

import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando
 */
public class RandomProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        RandomProjection rp = new RandomProjection();

        if (matrix != null) {
            projection = rp.project(matrix, null);
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public AbstractMatrix output() {
        return projection;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
    }

    public static final long serialVersionUID = 1L;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
}
