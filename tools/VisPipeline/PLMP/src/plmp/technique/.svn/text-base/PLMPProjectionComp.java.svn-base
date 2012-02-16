/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plmp.technique;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Technique.PLMP",
name = "Part-Linear Multidimensional Projection (PLMP)",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.")
public class PLMPProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        PLMPProjection sproj = new PLMPProjection();
        sproj.setFractionDelta(fracdelta);
        sproj.setNumberIterations(nriterations);
        sproj.setSampleType(sampletype);
        sproj.setSampleSize(samplesize);
        sproj.setSampleMatrix(samplematrix);
        sproj.setSampleProjection(sampleprojection);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = sproj.project(matrix, diss);
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix,
            @Param(name = "sample matrix") AbstractMatrix samplematrix,
            @Param(name = "sample projection") AbstractMatrix sampleprojection) {
        this.matrix = matrix;
        this.samplematrix = samplematrix;
        this.sampleprojection = sampleprojection;
    }

    public AbstractMatrix output() {
        return projection;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new PLMPProjectionParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
        samplematrix = null;
        sampleprojection = null;
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

    /**
     * @return the sampletype
     */
    public SampleType getSampleType() {
        return sampletype;
    }

    /**
     * @param sampletype the sampletype to set
     */
    public void setSampleType(SampleType sampletype) {
        this.sampletype = sampletype;
    }

    public int getSampleSize() {
        return samplesize;
    }

    public void setSampleSize(int samplesize) {
        this.samplesize = samplesize;
    }
    
    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private SampleType sampletype = SampleType.RANDOM;
    private int nriterations = 50;
    private float fracdelta = 8.0f;
    private int samplesize = 0;
    private transient PLMPProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient AbstractMatrix samplematrix;
    private transient AbstractMatrix sampleprojection;
}
