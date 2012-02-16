/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.PivotMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimePivotMDS extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        PivotMDSProjectionComp pivotmds = new PivotMDSProjectionComp();
        pivotmds.setDissimilarityType(dissstype);
        pivotmds.input(matrix);
        pivotmds.execute();
        AbstractMatrix projection = pivotmds.output();
        return projection;
    }
}
