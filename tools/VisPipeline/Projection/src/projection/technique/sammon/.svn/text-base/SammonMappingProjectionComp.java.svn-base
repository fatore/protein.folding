/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.sammon;

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
@VisComponent(hierarchy = "Projection.Technique",
name = "Sammon's Mapping",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "Sammon, J. W. A nonlinear mapping for data structure " +
"analysis. IEEE Transactions on Computers, v. 18, n. 5, p. 401-409, 1969.")
public class SammonMappingProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        SammonMappingProjection sammon = new SammonMappingProjection();
        sammon.setMagicFactor(mfactor);
        sammon.setNumberIterations(nriterations);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = sammon.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            projection = sammon.project(dmat);
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

    public AbstractMatrix output() {
        return projection;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new SammonMappingProjectionParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
        dmat = null;
    }

    /**
     * @return the nriterations
     */
    public int getNumberIterations() {
        return nriterations;
    }

    /**
     * @param nriterations the nriterations to set
     */
    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    /**
     * @return the fracdelta
     */
    public float getMagicFactor() {
        return mfactor;
    }

    /**
     * @param fracdelta the fracdelta to set
     */
    public void setMagicFactor(float mfactor) {
        this.mfactor = mfactor;
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

    public int getNumberInstances() {
        if (matrix != null) {
            return matrix.getRowCount();
        } else if (dmat != null) {
            return dmat.getElementCount();
        }

        return 0;
    }

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private int nriterations = 50;
    private float mfactor = 0.3f;
    private transient SammonMappingProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
