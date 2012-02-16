/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.technique.idmap.IDMAPProjectionComp;

/**
 *
 * @author PC
 */
public class TestStressTimeForceScheme extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        IDMAPProjectionComp idmap = new IDMAPProjectionComp();
        idmap.setDissimilarityType(dissstype);
        idmap.setFractionDelta(8.0f);
        idmap.setInitialization(InitializationType.FASTMAP);
        idmap.setNumberIterations(100);
        idmap.input(matrix);
        idmap.execute();
        AbstractMatrix projection = idmap.output();
        return projection;
    }
}
