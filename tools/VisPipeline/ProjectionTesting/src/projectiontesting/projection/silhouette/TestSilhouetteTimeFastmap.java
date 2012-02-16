/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.fastmap.FastmapProjection2DComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeFastmap extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        FastmapProjection2DComp fastmap = new FastmapProjection2DComp();
        fastmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        fastmap.input(matrix);
        fastmap.execute();
        AbstractMatrix projection = fastmap.output();
        return projection;
    }

}
