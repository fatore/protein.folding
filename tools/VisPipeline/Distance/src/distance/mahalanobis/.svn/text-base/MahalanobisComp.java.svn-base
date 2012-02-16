/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.mahalanobis;

import distance.DistanceMatrix;
import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Distance.Technique",
name = "Mahalanobis Distance",
description = "Create a distance matrix calculated using the kernel approach.")
public class MahalanobisComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (matrix != null) {
            Mahalanobis m = new Mahalanobis();
            dmat = m.getDistanceMatrix(matrix);
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public DistanceMatrix output() {
        return dmat;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        matrix = null;
        dmat = null;
    }

    public static final long serialVersionUID = 1L;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
