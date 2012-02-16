/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.idmap;

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
name = "Interactive Document Map (IDMAP)",
description = "Project points from a multidimensional space to the plane " +
"preserving the distance relations.",
howtocite = "Minghim, R.; Paulovich, F. V.; Lopes, A. A. Content-based text " +
"mapping using multidimensional projections for exploration of document " +
"collections. IS&T/SPIE Symposium on Electronic Imaging - Visualization " +
"and Data Analysis, San Jose, CA, USA, Jan., 2006.")
public class IDMAPProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        IDMAPProjection idmap = new IDMAPProjection();
        idmap.setFractionDelta(fracdelta);
        idmap.setInitialization(ini);
        idmap.setNumberIterations(nriterations);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            projection = idmap.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            projection = idmap.project(dmat);
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
            paramview = new IDMAPProjectionParamView(this);
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
     * @return the projtype
     */
    public InitializationType getInitialization() {
        return ini;
    }

    /**
     * @param projtype the projtype to set
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

    public boolean isDistanceMatrixProvided() {
        return (dmat != null);
    }

    public static final long serialVersionUID = 1L;
    private InitializationType ini = InitializationType.FASTMAP;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private int nriterations = 50;
    private float fracdelta = 8.0f;
    private transient IDMAPProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
