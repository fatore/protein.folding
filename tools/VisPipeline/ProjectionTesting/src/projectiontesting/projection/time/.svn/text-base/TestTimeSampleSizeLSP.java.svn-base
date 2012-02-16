/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.lsp.LSPProjection2DComp;

/**
 *
 * @author PC
 */
public class TestTimeSampleSizeLSP extends TestTimeSampleSize {

    @Override
    protected void project(AbstractMatrix matrix) throws IOException {
        LSPProjection2DComp lsp = new LSPProjection2DComp();
        lsp.setFractionDelta(8.0f);
        lsp.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        lsp.setNumberControlPoints(matrix.getRowCount() / 10);
        lsp.setNumberIterations(100);

        int nrobjects = matrix.getRowCount();
        if (nrobjects < 800) {
            lsp.setNumberNeighbors(8);
        } else if (nrobjects < 1500) {
            lsp.setNumberNeighbors(10);
        } else if (nrobjects < 15000) {
            lsp.setNumberNeighbors(15);
        } else if (nrobjects < 200000) {
            lsp.setNumberNeighbors(20);
        } else {
            lsp.setNumberNeighbors(25);
        }

        lsp.input(matrix);
        lsp.execute();
    }
}
