/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.lsp.LSPProjection2DComp;

/**
 *
 * @author PC
 */
public class TestSilhouetteTimeLSP extends TestSilhouetteTime {

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix) throws IOException {
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
