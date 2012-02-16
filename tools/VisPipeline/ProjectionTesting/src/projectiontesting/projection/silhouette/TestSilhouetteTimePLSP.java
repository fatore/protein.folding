/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import plsp.technique.PLSPProjection2DComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimePLSP extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        PLSPProjection2DComp plsp = new PLSPProjection2DComp();
        plsp.setFractionDelta(8.0f);
        plsp.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        plsp.setNumberIterations(25);

//        int nrobjects = matrix.getRowCount();
//        if (nrobjects < 800) {
        plsp.setNumberNeighbors(8);
//        } else if (nrobjects < 1500) {
//            plsp.setNumberNeighbors(10);
//        } else if (nrobjects < 15000) {
//            plsp.setNumberNeighbors(15);
//        } else if (nrobjects < 100000) {
//            plsp.setNumberNeighbors(20);
//        } else {
//            plsp.setNumberNeighbors(25);
//        }

        plsp.input(matrix);
        plsp.execute();
        AbstractMatrix projection = plsp.output();
        return projection;
    }

}
