/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.persistent;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Persistent Piecewise Least Square Projection (P-LSP)",
description = "Project points from a multidimensional space to the plane " +
"preserving the neighborhood relations, persisting Control Points and Patches.")
public class PersistentPLSPProjection2DComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        plsp = new PersistentPLSPProjection2D();
        plsp.setFractionDelta(fracdelta);
        plsp.setNumberIterations(nriterations);
        plsp.setNumberNeighbors(nrneighbors);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = plsp.project(matrix, diss);
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public AbstractMatrix output() {
        return projection;
    }

    public PersistentPLSPProjection2D outputPLSP(){
        return plsp;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new PersistentPLSPProjection2DParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
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
    public float getFractionDelta() {
        return fracdelta;
    }

    /**
     * @param fracdelta the fracdelta to set
     */
    public void setFractionDelta(float fracdelta) {
        this.fracdelta = fracdelta;
    }

    /**
     * @return the nrneighbors
     */
    public int getNumberNeighbors() {
        return nrneighbors;
    }

    /**
     * @param nrneighbors the nrneighbors to set
     */
    public void setNumberNeighbors(int nrneighbors) {
        this.nrneighbors = nrneighbors;
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

    public int getNumberInstances() {
        if (matrix != null) {
            return matrix.getRowCount();
        }

        return 0;
    }

    public static final long serialVersionUID = 1L;
    private int nriterations = 50;
    private float fracdelta = 8.0f;
    private int nrneighbors = 10;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private transient PersistentPLSPProjection2DParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient PersistentPLSPProjection2D plsp;
}
