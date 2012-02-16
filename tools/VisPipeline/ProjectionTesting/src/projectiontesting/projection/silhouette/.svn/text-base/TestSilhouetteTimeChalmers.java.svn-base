/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import fsmvis.viscomponents.ChalmersModelComp;
import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeChalmers extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        ChalmersModelComp chalmers = new ChalmersModelComp();
        chalmers.setDampingFactor(0.3f);
        chalmers.setFreeness(0.3f);
        chalmers.setSpringForce(0.7f);
        chalmers.setNumberIterations(matrix.getRowCount());
        chalmers.input(matrix);
        chalmers.execute();
        AbstractMatrix projection = chalmers.output();
        return projection;
    }

}
