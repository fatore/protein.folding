/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.radomproj.RandomProjectionComp;

/**
 *
 * @author Fernando
 */
public class TestStressTimeRandomProjection extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        RandomProjectionComp rp = new RandomProjectionComp();
        rp.input(matrix);
        rp.execute();
        AbstractMatrix projection = rp.output();
        return projection;
    }

}
