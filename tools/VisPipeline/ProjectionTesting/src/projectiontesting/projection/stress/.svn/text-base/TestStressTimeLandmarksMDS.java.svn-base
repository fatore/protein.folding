/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import mdsj.LandmarkMDSProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimeLandmarksMDS extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        LandmarkMDSProjectionComp landmarkmds = new LandmarkMDSProjectionComp();
        landmarkmds.setDissimilarityType(dissstype);
        landmarkmds.input(matrix);
        landmarkmds.execute();
        AbstractMatrix projection = landmarkmds.output();
        return projection;
    }
}
