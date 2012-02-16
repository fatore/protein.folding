/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.mds.ClassicalMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimeClassicMDS extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        ClassicalMDSProjectionComp mds = new ClassicalMDSProjectionComp();
        mds.setDissimilarityType(dissstype);
        mds.input(matrix);
        mds.execute();
        AbstractMatrix projection = mds.output();
        return projection;
    }
}
