/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import fsmvis.viscomponents.HybridComp;
import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestTimeSampleSizeHybridModel extends TestTimeSampleSize {

    @Override
    protected void project(AbstractMatrix matrix) throws IOException {
        HybridComp hybrid = new HybridComp();
        hybrid.setDampingFactor(0.3f);
        hybrid.setFreeness(0.3f);
        hybrid.setSpringForce(0.7f);
        hybrid.setNumberIterations((int) Math.sqrt(matrix.getRowCount()));
        hybrid.input(matrix);
        hybrid.execute();
    }
}
