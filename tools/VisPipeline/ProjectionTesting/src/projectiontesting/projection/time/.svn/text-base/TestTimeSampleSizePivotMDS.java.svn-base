/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.PivotMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestTimeSampleSizePivotMDS extends TestTimeSampleSize {

    @Override
    protected void project(AbstractMatrix matrix) throws IOException {
        PivotMDSProjectionComp pivotmds = new PivotMDSProjectionComp();
        pivotmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        pivotmds.input(matrix);
        pivotmds.execute();
    }
}
