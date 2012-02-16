/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.LandmarkMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeLandmarksMDS extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        LandmarkMDSProjectionComp landmarkmds = new LandmarkMDSProjectionComp();
        landmarkmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        landmarkmds.input(matrix);
        landmarkmds.execute();
        AbstractMatrix projection = landmarkmds.output();
        return projection;
    }

}
