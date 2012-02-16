/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.sammon.SammonMappingProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimeSammonMapping extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        SammonMappingProjectionComp sammon = new SammonMappingProjectionComp();
        sammon.setDissimilarityType(dissstype);
        sammon.setNumberIterations(matrix.getRowCount());
        sammon.input(matrix);
        sammon.execute();
        AbstractMatrix projection = sammon.output();
        return projection;
    }
}
