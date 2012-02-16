/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import jourdanmelancon.JourdanMelanconProjectionComp;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestStressTimeJourdanMelancon extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        JourdanMelanconProjectionComp jm = new JourdanMelanconProjectionComp();
        jm.setDissimilarityType(dissstype);
        jm.input(matrix);
        jm.execute();
        AbstractMatrix projection = jm.output();
        return projection;
    }

}
