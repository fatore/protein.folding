/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.lsp;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import projection3d.lsp.LSPProjection3D.ControlPointsType;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;


/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Projection3D.Technique",
name = "Least Square Projection 3D (LSP)",
description = "Project points from a multidimensional space to the plane "
+ "preserving the neighborhood relations.",
howtocite = "Paulovich, F. V.; Nonato, L. G.; Minghim, R.; Levkowitz, H. Least "
+ "Square Projection: a fast high precision multidimensional projection "
+ "technique and its application to document mapping. IEEE Transactions "
+ "on Visualization and Computer Graphics, v. 14, p. 564-575, 2008. ")
public class LSPProjection3DComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        LSPProjection3D lsp = new LSPProjection3D();
        lsp.setFractionDelta(fracdelta);
        lsp.setNumberIterations(nriterations);
        lsp.setNumberNeighbors(nrneighbors);
        lsp.setNumberControlPoints(nrcontrolpoints);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            lsp.setControlPointsChoice(ControlPointsType.KMEANS);
            projection = lsp.project(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            lsp.setControlPointsChoice(ControlPointsType.KMEDOIDS);
            projection = lsp.project(dmat);
        } else {
            throw new IOException("A distance matrix or a points matrix should "
                    + "be provided.");
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
            paramview = new LSPProjection3DParamView(this);
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
     * @return the nrcontrolpoints
     */
    public int getNumberControlPoints() {
        return nrcontrolpoints;
    }

    /**
     * @param nrcontrolpoints the nrcontrolpoints to set
     */
    public void setNumberControlPoints(int nrcontrolpoints) {
        this.nrcontrolpoints = nrcontrolpoints;
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
    private int nriterations = 50;
    private float fracdelta = 8.0f;
    private int nrneighbors = 10;
    private int nrcontrolpoints = 10;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private transient LSPProjection3DParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
