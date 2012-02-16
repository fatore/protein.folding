/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection3d.reproject;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;



/**
 *
 * @author jpocom
 */
public class OrthogonalProjection implements Projection {

    public enum OrthogonalPlane {

        XY, XZ, YZ
    }

    public OrthogonalProjection(OrthogonalPlane plane) {
        this.plane = plane;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        long finish = System.currentTimeMillis();

        AbstractMatrix projection = new DenseMatrix();
        for (int r=0; r<matrix.getRowCount(); r++) {
            AbstractVector row = matrix.getRow(r);

            float[] values = row.getValues();
            float[] pValues = new float[2];

            if (plane.equals(OrthogonalPlane.XY)) {
                pValues[0] = values[0];
                pValues[1] = values[1];
            } else if (plane.equals(OrthogonalPlane.XZ)) {
                pValues[0] = values[0];
                pValues[1] = values[2];
            } else if (plane.equals(OrthogonalPlane.YZ)) {
                pValues[0] = values[1];
                pValues[1] = values[2];
            }

            projection.addRow(new DenseVector(pValues, row.getId(), row.getKlass()));
        }

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Least Square Projection (LSP) time: " + (finish - start) / 1000.0f + "s");

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private OrthogonalPlane plane;
}
