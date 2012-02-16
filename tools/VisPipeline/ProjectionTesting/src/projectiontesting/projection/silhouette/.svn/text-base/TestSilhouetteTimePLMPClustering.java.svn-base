/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import plmp.technique.PLMPProjectionComp;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimePLMPClustering extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        PLMPProjectionComp mmds = new PLMPProjectionComp();
        mmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        mmds.setFractionDelta(8.0f);
        mmds.setNumberIterations(100);
        mmds.setSampleType(SampleType.CLUSTERING);
        mmds.input(matrix);
        mmds.execute();
        AbstractMatrix projection = mmds.output();
        return projection;
    }

}
