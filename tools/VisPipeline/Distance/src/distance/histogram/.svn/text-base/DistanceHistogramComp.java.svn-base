/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.histogram;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Distance.View",
name = "Distance Histogram",
description = "Create a distance histogram.")
public class DistanceHistogramComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        DistanceHistogram dh = new DistanceHistogram();

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            dh.display(new DistanceMatrix(matrix, diss));
        } else if (dmat != null) { //using a distance matrix
            dh.display(dmat);
        } else {
            throw new IOException("A distance matrix or a points matrix should " +
                    "be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public void input(@Param(name = "distance matrix") DistanceMatrix dmat) {
        this.dmat = dmat;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new DistanceHistogramParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        matrix = null;
        dmat = null;
    }

    /**
     * @return the disstype
     */
    public DissimilarityType getDissimilarityType() {
        return disstype;
    }

    /**
     * @param disstype the disstype to set
     */
    public void setDissimilarityType(DissimilarityType diss) {
        this.disstype = diss;
    }

    public boolean isDistanceMatrixProvided() {
        return (dmat != null);
    }

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private transient DistanceHistogramParamView paramview;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
