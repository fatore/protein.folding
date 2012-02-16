/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.projclus;

import java.io.IOException;
import projection.technique.idmap.IDMAPProjection.InitializationType;
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
name = "Projection by Clustering (ProjClus)",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "Paulovich, F. V.; Minghim, R. Text Map Explorer: a Tool to Create " +
"and Explore Document Maps. Information Visualization (IV'06). IEEE " +
"Computer Society Press. London, Jul., 2006.")
public class ProjClusProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        ProjClusProjection projclus = new ProjClusProjection();
        projclus.setFractionDelta(fracdelta);
        projclus.setInitialization(ini);
        projclus.setNumberIterations(nriterations);
        projclus.setClusterFactor(clustfactor);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = projclus.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            projection = projclus.project(dmat);
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
            paramview = new ProjClusProjectionParamView(this);
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
     * @return the ini
     */
    public InitializationType getInitialization() {
        return ini;
    }

    /**
     * @param ini the ini to set
     */
    public void setInitialization(InitializationType ini) {
        this.ini = ini;
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
     * @return the clustfactor
     */
    public float getClusterFactor() {
        return clustfactor;
    }

    /**
     * @param clustfactor the clustfactor to set
     */
    public void setClusterFactor(float clustfactor) {
        this.clustfactor = clustfactor;
    }

    public boolean isDistanceMatrixProvided() {
        return (dmat != null);
    }

    public static final long serialVersionUID = 1L;
    private InitializationType ini = InitializationType.FASTMAP;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private int nriterations = 50;
    private float fracdelta = 8.0f;
    private float clustfactor = 4.5f;
    private transient ProjClusProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
