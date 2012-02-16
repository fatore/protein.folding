/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.lisomap;

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
 * @author Fernando
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Landmarks Isometric Feature Mapping (Landmarks ISOMAP)",
description = "Project points from a multidimensional space to the plane "
+ "preserving the geodesic distance relations.",
howtocite = "????")
public class LandmarksISOMAPProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        LandmarksISOMAPProjection lisomap = new LandmarksISOMAPProjection();
        lisomap.setNumberNeighbors(nrneighbors);

        if (matrix != null) {
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = lisomap.project(matrix, diss);
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
        if (paramview == null) {
            paramview = new LandmarksISOMAPProjectionParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
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

    public static final long serialVersionUID = 1L;
    private int nrneighbors = 10;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private transient LandmarksISOMAPProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
}
