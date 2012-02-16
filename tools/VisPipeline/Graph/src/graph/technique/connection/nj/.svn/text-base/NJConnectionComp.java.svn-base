/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.technique.connection.nj;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import graph.model.Connectivity;
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
@VisComponent(hierarchy = "Graph.Connection.Technique",
name = "Neighbor-Joinning connection",
description = "Project points from a multidimensional space to the plane " +
"preserving the neighborhood relations.")
public class NJConnectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        NJConnection nj = new NJConnection();
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            conn = nj.execute(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            conn = nj.execute(dmat);
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

    public Connectivity output() {
        return conn;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new NJConnectionParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        conn = null;
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
    private transient NJConnectionParamView paramview;
    private transient Connectivity conn;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
