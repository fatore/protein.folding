/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import jourdanmelancon.JourdanMelanconProjectionComp;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeJourdanMelancon extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        JourdanMelanconProjectionComp jm = new JourdanMelanconProjectionComp();
        jm.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        jm.input(matrix);
        jm.execute();
        AbstractMatrix projection = jm.output();
        return projection;
    }

}
