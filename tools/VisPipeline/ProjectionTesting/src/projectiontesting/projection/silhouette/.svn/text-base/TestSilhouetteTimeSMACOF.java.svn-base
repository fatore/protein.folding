/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.SMACOFProjectionComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeSMACOF extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
        SMACOFProjectionComp smacof = new SMACOFProjectionComp();
        smacof.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        //smacof.setNumberIterations(matrix.getRowCount() / 20);
        smacof.setNumberIterations(50);
        smacof.input(matrix);
        smacof.execute();
        AbstractMatrix projection = smacof.output();
        return projection;
    }

}
