/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.mds;

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
name = "Classical Multidimensional Scaling (MDS)",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "Cox, T. F.; Cox, M. A. A. Multidimensional scaling. " +
"Second ed. Chapman & Hall/CRC,2000.")
public class ClassicalMDSProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        ClassicalMDSProjection mds = new ClassicalMDSProjection();

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = mds.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            projection = mds.project(dmat);
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
            paramview = new ClassicalMDSProjectionParamView(this);
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
    private transient ClassicalMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
