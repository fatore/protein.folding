/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.technique.connection.mstproj;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import graph.model.Connectivity;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Graph.Connection.Technique",
name = "Minimum Spanning Tree Connection",
description = "Create links from a multidimensional space.")
public class MSTConnectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        MSTConnection mstproj = new MSTConnection();
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            conn = mstproj.execute(matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            conn = mstproj.execute(dmat);
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
            paramview = new MSTConnectionParamView(this);
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
    private transient MSTConnectionParamView paramview;
    private transient Connectivity conn;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
