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
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author paulovich
 */
@VisComponent(hierarchy = "Projection.Technique.L-MMDS",
name = "Random Sampling",
description = "Get a random sampling of a given matrix.")
public class SamplingComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (matrix != null) {
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            Sampling sampling = new Sampling(sampletype, samplesize);
            samplematrix = sampling.execute(matrix, diss);
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public AbstractMatrix output() {
        return samplematrix;
    }

    @Override
    public void reset() {
        matrix = null;
        samplematrix = null;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new SamplingParamView(this);
        }

        return paramview;
    }

    public void setSampleSize(int samplesize) {
        this.samplesize = samplesize;
    }

    public int getSampleSize() {
        return this.samplesize;
    }

    public SampleType getSampleType() {
        return sampletype;
    }

    public void setSampleType(SampleType sampletype) {
        this.sampletype = sampletype;
    }

    public DissimilarityType getDissimilarityType() {
        return disstype;
    }

    public void setDissimilarityType(DissimilarityType diss) {
        this.disstype = diss;
    }
    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private int samplesize = 0;
    private SampleType sampletype = SampleType.RANDOM;
    private transient AbstractMatrix matrix;
    private transient AbstractMatrix samplematrix;
    private transient SamplingParamView paramview;
}
