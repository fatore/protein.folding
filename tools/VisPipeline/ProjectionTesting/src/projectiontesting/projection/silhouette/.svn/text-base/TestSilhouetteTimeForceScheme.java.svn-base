/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.technique.idmap.IDMAPProjectionComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeForceScheme extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        IDMAPProjectionComp idmap = new IDMAPProjectionComp();
        idmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        idmap.setFractionDelta(8.0f);
        idmap.setInitialization(InitializationType.FASTMAP);
        idmap.setNumberIterations(100);
        idmap.input(matrix);
        idmap.execute();
        AbstractMatrix projection = idmap.output();
        return projection;
    }

}
