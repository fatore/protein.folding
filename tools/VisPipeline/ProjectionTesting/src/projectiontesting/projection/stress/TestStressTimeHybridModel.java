/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import fsmvis.viscomponents.HybridComp;
import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestStressTimeHybridModel extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
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
