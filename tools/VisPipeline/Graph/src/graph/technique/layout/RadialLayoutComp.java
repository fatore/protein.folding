/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.technique.layout;

import graph.model.Connectivity;
import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Graph.Layout",
name = "Radial Layout",
description = "Draw to the plane using the radial approach.")
public class RadialLayoutComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (matrix != null && conn != null) {
            RadialLayout radial = new RadialLayout();
            position = radial.execute(matrix, conn);
        } else {
            throw new IOException("A points matrix and links should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix,
            @Param(name = "connectivity") Connectivity conn) {
        this.matrix = matrix;
        this.conn = conn;
    }

    public AbstractMatrix output() {
        return position;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        conn = null;
        matrix = null;
        position = null;
    }

    public static final long serialVersionUID = 1L;
    private transient Connectivity conn;
    private transient AbstractMatrix matrix;
    private transient AbstractMatrix position;
}
