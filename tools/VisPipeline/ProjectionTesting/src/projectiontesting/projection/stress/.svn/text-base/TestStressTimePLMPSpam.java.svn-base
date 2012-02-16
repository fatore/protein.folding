/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import plmp.technique.PLMPProjectionComp;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando
 */
public class TestStressTimePLMPSpam extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        PLMPProjectionComp mmds = new PLMPProjectionComp();
        mmds.setDissimilarityType(dissstype);
        mmds.setFractionDelta(8.0f);
        mmds.setNumberIterations(100);
        mmds.setSampleType(SampleType.SPAM);
        mmds.setSampleSize((int) Math.sqrt(matrix.getRowCount()) * 2);
        mmds.input(matrix);
        mmds.execute();
        AbstractMatrix projection = mmds.output();
        return projection;
    }

}
