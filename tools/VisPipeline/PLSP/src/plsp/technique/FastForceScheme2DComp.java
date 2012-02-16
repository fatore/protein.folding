/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.technique;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection.technique.fastmap.FastmapProjection2D;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author paulovich
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Fast Force Scheme",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "")
public class FastForceScheme2DComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        FastForceScheme2D ffs = new FastForceScheme2D();
        ffs.setNumberIterations(nriterations);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);

            FastmapProjection2D fastmap = new FastmapProjection2D();
            AbstractMatrix project = fastmap.project(matrix, diss);
            ffs.setProjection(project);

            projection = ffs.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            projection = ffs.project(dmat);
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
//        if (paramview == null) {
//            paramview = new IDMAPProjectionParamView(this);
//        }
//
//        return paramview;
        return null;
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
    private int nriterations = 50;
//    private transient IDMAPProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;

}
