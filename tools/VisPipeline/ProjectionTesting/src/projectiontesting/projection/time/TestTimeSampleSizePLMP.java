/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import plmp.technique.PLMPProjectionComp;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestTimeSampleSizePLMP extends TestTimeSampleSize {

    @Override
    protected void project(AbstractMatrix matrix) throws IOException {
        PLMPProjectionComp spcomp = new PLMPProjectionComp();
        spcomp.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        spcomp.setFractionDelta(8.0f);
        spcomp.setNumberIterations(100);
        spcomp.setSampleType(SampleType.RANDOM);
        spcomp.input(matrix);
        spcomp.execute();
    }
}
