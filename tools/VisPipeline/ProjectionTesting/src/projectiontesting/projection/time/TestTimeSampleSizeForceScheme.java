/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.technique.idmap.IDMAPProjectionComp;

/**
 *
 * @author PC
 */
public class TestTimeSampleSizeForceScheme extends TestTimeSampleSize {

    @Override
    protected void project(AbstractMatrix matrix) throws IOException {
        IDMAPProjectionComp idmap = new IDMAPProjectionComp();
        idmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        idmap.setFractionDelta(8.0f);
        idmap.setInitialization(InitializationType.FASTMAP);
        idmap.setNumberIterations(100);
        idmap.input(matrix);
        idmap.execute();
    }
}
