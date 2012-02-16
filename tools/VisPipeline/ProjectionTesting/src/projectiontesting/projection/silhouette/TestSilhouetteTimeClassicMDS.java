/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.mds.ClassicalMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeClassicMDS extends TestSilhouetteTime {

     @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        ClassicalMDSProjectionComp mds = new ClassicalMDSProjectionComp();
        mds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        mds.input(matrix);
        mds.execute();
        AbstractMatrix projection = mds.output();
        return projection;
    }

}
