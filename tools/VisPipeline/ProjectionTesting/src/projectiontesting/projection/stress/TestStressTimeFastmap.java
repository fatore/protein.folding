/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.fastmap.FastmapProjection2DComp;

/**
 *
 * @author PC
 */
public class TestStressTimeFastmap extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        FastmapProjection2DComp fastmap = new FastmapProjection2DComp();
        fastmap.setDissimilarityType(dissstype);
        fastmap.input(matrix);
        fastmap.execute();
        AbstractMatrix projection = fastmap.output();
        return projection;
    }
}
