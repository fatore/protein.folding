/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.sammon.SammonMappingProjectionComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeSammonMapping extends TestSilhouetteTime {

     @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        SammonMappingProjectionComp sammon = new SammonMappingProjectionComp();
        sammon.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        sammon.setNumberIterations(matrix.getRowCount());
        sammon.input(matrix);
        sammon.execute();
        AbstractMatrix projection = sammon.output();
        return projection;
    }

}
