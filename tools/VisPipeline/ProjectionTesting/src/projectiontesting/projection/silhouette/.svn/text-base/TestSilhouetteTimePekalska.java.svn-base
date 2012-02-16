/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import projection.technique.pekalska.PekalskaProjectionComp;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimePekalska extends TestSilhouetteTime {

     @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        PekalskaProjectionComp pekalska = new PekalskaProjectionComp();
        pekalska.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        pekalska.input(matrix);
        pekalska.execute();
        AbstractMatrix projection = pekalska.output();
        return projection;
    }

}
