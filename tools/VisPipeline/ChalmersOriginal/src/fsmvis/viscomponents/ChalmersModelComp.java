/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fsmvis.viscomponents;

import fsmvis.data.DataItemCollection;
import fsmvis.engine.EndCriteria;
import fsmvis.engine.LayoutModel;
import fsmvis.engine.NeighbourAndSampleModel;
import fsmvis.engine.TooManyIterationsException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Chalmers'96",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "Chalmers, M. A linear iteration time layout algorithm for " +
"visualising high-dimensional data. In: Proceedings of the IEEE Visualization " +
"1996 (VIS'96), Los Alamitos, CA, USA: IEEE Computer Society Press, 1996, p. 127-ff.")
public class ChalmersModelComp extends AbstractComponent implements vispipelinebasics.interfaces.AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            try {
                //using a matrix
                DataItemCollection data = createDataCollection(matrix);
                EndCriteria ec = new EndCriteria(1, nriterations, 0, 0, data.getSize());

                LayoutModel model = new NeighbourAndSampleModel(data, ec, null);

                model.getProperties().setProperty(LayoutModel.FREENESS, Float.toString(freeness));
                model.getProperties().setProperty(LayoutModel.DAMPING_FACTOR, Float.toString(dampingfactor));
                model.getProperties().setProperty(LayoutModel.SPRING_FORCE, Float.toString(springforce));
                model.updateValues();

                for (int i = 0; i < nriterations; i++) {
                    model.doIteration();
                }

                projection = createProjection(matrix, data);
            } catch (TooManyIterationsException ex) {
                Logger.getLogger(SpringComp.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public static final long serialVersionUID = 1L;
}
