/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.model;

import projection.model.Scalar;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import projection.util.ProjectionConstants;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Graph.Basics",
name = "Graph Model",
description = "Create a graph model to be visualized.")
public class GraphModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (placement != null) {
            model = new GraphModel();
            Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
            Scalar dots = model.addScalar(ProjectionConstants.DOTS);

            int nrows = placement.getRowCount();

            for (int i = 0; i < nrows; i++) {
                AbstractVector row = placement.getRow(i);
                GraphInstance pi = new GraphInstance(model, row.getId(),
                        row.getValue(0), row.getValue(1));
                pi.setScalarValue(cdata, row.getKlass());
                pi.setScalarValue(dots, 0.0f);
            }

            //adding the connectivities
            Connectivity dotsCon = new Connectivity(ProjectionConstants.DOTS, new ArrayList<Edge>());
            model.addConnectivity(dotsCon);

            for (int i = 0; i < conns.size(); i++) {
                model.addConnectivity(conns.get(i));
            }
        } else {
            throw new IOException("A 2D position should be provided.");
        }
    }

    public void input(@Param(name = "2D placement") AbstractMatrix placement) {
        this.placement = placement;
    }

    public void attach(@Param(name = "connectivity") Connectivity conn) {
        if (conns == null) {
            conns = new ArrayList<Connectivity>();
        }

        if (conn != null) {
            conns.add(conn);
        }
    }

    public GraphModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        placement = null;
        model = null;
        conns = null;
    }

    public static final long serialVersionUID = 1L;
    private transient GraphModel model;
    private transient AbstractMatrix placement;
    private transient ArrayList<Connectivity> conns;
}
