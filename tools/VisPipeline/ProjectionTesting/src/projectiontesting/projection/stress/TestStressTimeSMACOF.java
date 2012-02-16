/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.SMACOFProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimeSMACOF extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        SMACOFProjectionComp smacof = new SMACOFProjectionComp();
        smacof.setDissimilarityType(dissstype);
        //smacof.setNumberIterations(matrix.getRowCount() / 20);
        smacof.setNumberIterations(50);
        smacof.input(matrix);
        smacof.execute();
        AbstractMatrix projection = smacof.output();
        return projection;
    }
}
