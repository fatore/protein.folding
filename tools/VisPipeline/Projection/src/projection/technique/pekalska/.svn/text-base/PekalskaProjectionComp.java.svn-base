/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.technique.pekalska;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author PC
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Rapid Sammon",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.")
public class PekalskaProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);

            PekalskaProjection rapidsammon = new PekalskaProjection();
            rapidsammon.project(matrix, diss);
            projection = rapidsammon.project(matrix, diss);
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

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
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

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient MassiveMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
}
