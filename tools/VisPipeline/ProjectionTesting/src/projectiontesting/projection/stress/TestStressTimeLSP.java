/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.lsp.LSPProjection2DComp;

/**
 *
 * @author PC
 */
public class TestStressTimeLSP extends TestStressTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        LSPProjection2DComp lsp = new LSPProjection2DComp();
        lsp.setFractionDelta(8.0f);
        lsp.setDissimilarityType(dissstype);
        lsp.setNumberControlPoints(matrix.getRowCount() / 10);
        lsp.setNumberIterations(100);

        int nrobjects = matrix.getRowCount();
        if (nrobjects < 800) {
            lsp.setNumberNeighbors(8);
        } else if (nrobjects < 1500) {
            lsp.setNumberNeighbors(10);
        } else if (nrobjects < 15000) {
            lsp.setNumberNeighbors(15);
        } else if (nrobjects < 100000) {
            lsp.setNumberNeighbors(20);
        } else {
            lsp.setNumberNeighbors(25);
        }

        lsp.input(matrix);
        lsp.execute();
        AbstractMatrix projection = lsp.output();
        return projection;
    }
}
