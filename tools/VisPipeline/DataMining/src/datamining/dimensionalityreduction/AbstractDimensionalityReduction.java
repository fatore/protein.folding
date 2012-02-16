/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining.dimensionalityreduction;

import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class AbstractDimensionalityReduction {

    public AbstractDimensionalityReduction(int targetDimension) {
        this.targetDimension = targetDimension;
    }

    public AbstractMatrix reduce(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        if (this.targetDimension < matrix.getDimensions()) {

            AbstractMatrix redmatrix = new DenseMatrix();

            float[][] red = this.execute(matrix, diss);

            //transforming the reduce form into a dense matrix
            ArrayList<Integer> ids = matrix.getIds();
            float[] classData = matrix.getClassData();
            ArrayList<String> labels = matrix.getLabels();

            for (int i = 0; i < matrix.getRowCount(); i++) {
                DenseVector dvector = new DenseVector(red[i], ids.get(i), classData[i]);

                if (labels.isEmpty()) {
                    redmatrix.addRow(dvector);
                } else {
                    redmatrix.addRow(dvector, labels.get(i));
                }
            }

            //setting the new attributes
            ArrayList<String> attr = new ArrayList<String>();
            for (int i = 0; i < redmatrix.getDimensions(); i++) {
                attr.add("attr");
            }
            redmatrix.setAttributes(attr);

            return redmatrix;
        } else {
            throw new IOException("The target dimension should be smaller than the "
                    + "dimension of the original points matrix.");
        }
    }

    protected abstract float[][] execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException;
    protected int targetDimension;
}
