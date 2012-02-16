/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import fsmvis.viscomponents.HybridComp;
import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeHybridModel extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        HybridComp hybrid = new HybridComp();
        hybrid.setDampingFactor(0.3f);
        hybrid.setFreeness(0.3f);
        hybrid.setSpringForce(0.7f);
        hybrid.setNumberIterations((int) Math.sqrt(matrix.getRowCount()));
        hybrid.input(matrix);
        hybrid.execute();
        AbstractMatrix projection = hybrid.output();
        return projection;
    }

}
