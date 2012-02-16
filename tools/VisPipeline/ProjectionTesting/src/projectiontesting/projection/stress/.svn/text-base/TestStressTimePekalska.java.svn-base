/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import projection.technique.pekalska.PekalskaProjectionComp;
import matrix.AbstractMatrix;

/**
 *
 * @author PC
 */
public class TestStressTimePekalska extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        PekalskaProjectionComp pekalska = new PekalskaProjectionComp();
        pekalska.setDissimilarityType(dissstype);
        pekalska.input(matrix);
        pekalska.execute();
        AbstractMatrix projection = pekalska.output();
        return projection;
    }
}
