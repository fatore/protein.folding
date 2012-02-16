/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.model.topic;

import topics.model.*;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import plsp.persistent.PersistentPLSPProjection2D;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Basics",
name = "P-LSP Topic Projection Model",
description = "Create a P-LSP topic projection model to be visualized.")
public class PLSPTopicProjectionModelComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (projection != null) {
            if (plsp != null) {
                model = new PLSPTopicProjectionModel();
                Scalar cdata = model.addScalar(ProjectionConstants.CDATA);
                Scalar dots = model.addScalar(ProjectionConstants.DOTS);

                int nrows = projection.getRowCount();

                for (int i = 0; i < nrows; i++) {
                    AbstractVector row = projection.getRow(i);
                    TopicProjectionInstance pi = new TopicProjectionInstance(model, row.getId(),
                            row.getValue(0), row.getValue(1));
                    pi.setScalarValue(cdata, row.getKlass());
                    pi.setScalarValue(dots, 0.0f);

                    if (labels != null) {
                        pi.setLabel(labels.get(i));
                    }
                }


                //persisting patches
                //int cont = 0;
                for (int i = 0; i < plsp.getClusters().size(); i++) {
                    ArrayList<AbstractInstance> patch = new ArrayList<AbstractInstance>();
                    //cont = cont + plsp.getClusters().get(i).size();
                    for (int j = 0; j < plsp.getClusters().get(i).size(); j++) {
                        int pos = plsp.getClusters().get(i).get(j);
                        patch.add(model.getInstances().get(pos));
                    }
                    ((PLSPTopicProjectionModel) model).addPatch(patch);
                }

                //persisting control points
                for (int i = 0; i < plsp.getCpoints().size(); i++) {
                    ArrayList<AbstractInstance> patchCP = new ArrayList<AbstractInstance>();
                    for (int j = 0; j < plsp.getCpoints().get(i).size(); j++) {
                        int pos = plsp.getCpoints().get(i).get(j);
                        patchCP.add(model.getInstances().get(pos));
                    }
                    ((PLSPTopicProjectionModel) model).addPatchControlPoints(patchCP);
                }

                //persisting neighborhood graphs
                for (int i = 0; i < plsp.getNeighborhoodGraphs().size(); i++) {
                    ((PLSPTopicProjectionModel) model).addNeighborhoodGraph(plsp.getNeighborhoodGraphs().get(i));
                }

                //creating relationship between instanceID and patchID
                ((PLSPTopicProjectionModel) model).generatePatchMap();
            } else {
                throw new IOException("A Persistent P-LSP should be provided.");
            }
        } else {
            throw new IOException("A 2D projection should be provided.");
        }
    }

    public void input(@Param(name = "2D projection") AbstractMatrix projection) {
        this.projection = projection;
    }

    public void input(@Param(name = "2D projection") AbstractMatrix projection,
            @Param(name = "labels") ArrayList<String> labels) {
        this.projection = projection;

        if (labels.size() == projection.getRowCount()) {
            this.labels = labels;
        }
    }

    public void input(@Param(name = "Persistent P-LSP") PersistentPLSPProjection2D plsp) {
        this.plsp = plsp;
    }

    public TopicProjectionModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        model = null;
    }
    
    public static final long serialVersionUID = 1L;
    private transient TopicProjectionModel model;
    private transient AbstractMatrix projection;
    private transient ArrayList<String> labels;
    private transient PersistentPLSPProjection2D plsp;
}
