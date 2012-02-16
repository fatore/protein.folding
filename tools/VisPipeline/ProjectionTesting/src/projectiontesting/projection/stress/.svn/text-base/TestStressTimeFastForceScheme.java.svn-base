/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import plsp.technique.FastForceScheme2DComp;

/**
 *
 * @author paulovich
 */
public class TestStressTimeFastForceScheme  extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        FastForceScheme2DComp ffs = new FastForceScheme2DComp();
        ffs.setDissimilarityType(dissstype);
        ffs.setNumberIterations(50);
        ffs.input(matrix);
        ffs.execute();
        AbstractMatrix projection = ffs.output();
        return projection;
    }

}
