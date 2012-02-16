/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.model.AbstractInstance;


/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Tensor.Features",
name = "Fiber's Statistics",
description = "Extract statistic from a bundle of fibers.")
public class FiberStatisticComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        this.model = null;
        this.matrix = null;
    }

    @Override
    public void execute() throws IOException {
        if (model != null) {
            matrix = new DenseMatrix();

            ArrayList<String> attrs = new ArrayList<String>();
            attrs.add("length");
            attrs.add("CM_x");
            attrs.add("CM_y");
            attrs.add("CM_z");
            attrs.add("start_x");
            attrs.add("start_y");
            attrs.add("start_z");
            attrs.add("end_x");
            attrs.add("end_y");
            attrs.add("end_z");
            matrix.setAttributes(attrs);

            for(AbstractInstance in: model.getInstances()) {
                FiberInstance inst = (FiberInstance)in;
                float length = 0.f;
                for (int i=1; i<inst.numPoints; i++) {
                    float[] p1 = inst.getPoint(i-1);
                    float[] p2 = inst.getPoint(i);
                    length += Math.sqrt((p1[0]-p2[0])*(p1[0]-p2[0])+
                                        (p1[1]-p2[1])*(p1[1]-p2[1])+
                                        (p1[2]-p2[2])*(p1[2]-p2[2]));
                }

                float[] CM = new float[]{0.f, 0.f, 0.f};
                for (int i=0; i<inst.numPoints; i++) {
                    float[] p = inst.getPoint(i);
                    CM[0] += p[0];
                    CM[1] += p[1];
                    CM[2] += p[2];
                }
                CM[0] /=inst.numPoints;
                CM[1] /=inst.numPoints;
                CM[2] /=inst.numPoints;

                float[] startPoint = inst.getPoint(0);
                float[] endPoint = inst.getPoint(inst.numPoints-1);

                float []features = new float[]{length,
                                               CM[0], CM[1], CM[2],
                                               startPoint[0], startPoint[1], startPoint[2],
                                               endPoint[0], endPoint[1], endPoint[2]};
                matrix.addRow(new DenseVector(features, inst.getId(), 0));
            }
        } else {
            throw new IOException("A Fiber Model should be provided.");
        }
    }

    public void input(@Param(name = "Fiber model") FiberModel model) {
        this.model = model;
    }

    public AbstractMatrix output() {
        return matrix;
    }


    public static final long serialVersionUID = 1L;
    private transient FiberModel model;
    private transient AbstractMatrix matrix;
    private transient TRKFiberReaderParamView paramview;
}
