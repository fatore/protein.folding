/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import tensor.fft.FFT;
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
name = "Fiber's Curvature",
description = "Extract curvature features for each fiber.")
public class FiberCurvatureComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new FiberCurvatureParamView(this);
        }

        return paramview;
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

            ArrayList<String> attrs = new ArrayList<String>(Arrays.asList(new String[3*(min+max)]));
            for (int i=0; i<min; i++) {
                attrs.set(i, "CurvMin"+i+"_x");
                attrs.set(i+min+max, "CurvMin"+i+"_y");
                attrs.set(i+2*(min+max), "CurvMin"+i+"_z");
            }
            for (int i=0; i<max; i++) {
                attrs.set(i+min, "CurvMax"+i+"_x");
                attrs.set(i+min+min+max, "CurvMax"+i+"_y");
                attrs.set(i+min+2*(min+max), "CurvMax"+i+"_z");
            }
            matrix.setAttributes(attrs);

            for(AbstractInstance in: model.getInstances()) {
                FiberInstance inst = (FiberInstance)in;
                
                int n = inst.numPoints;
                double []fftX = new double[2*n];
                double []fftY = new double[2*n];
                double []fftZ = new double[2*n];
                
                for (int i=0; i<n; i++) {
                    float[] p2 = inst.getPoint(i);
                    fftX[i*2] = p2[0];
                    fftY[i*2] = p2[1];
                    fftZ[i*2] = p2[2];
                }

                FFT fft = new FFT(n);
                fft.transform(fftX);
                fft.transform(fftY);
                fft.transform(fftZ);


                float []features = new float[3*(getMin()+getMax())];
                for (int i=0; i<getMin(); i++) {
                    features[i] = (float)fftX[i*2];
                    features[i+getMin()+getMax()] = (float)fftY[i*2];
                    features[i+2*(getMin()+getMax())] = (float)fftZ[i*2];
                }
                for (int i=0; i<getMax(); i++) {
                    features[i+getMin()] = (float)fftX[2*n - (2*getMax()-i*2)];
                    features[i+getMin()+getMax()+getMin()] = (float)fftY[2*n - (2*getMax()-i*2)];
                    features[i+getMin()+2*(getMax()+getMin())] = (float)fftZ[2*n - (2*getMax()-i*2)];
                }

                matrix.addRow(new DenseVector(features, inst.getId()));
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

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }


    public static final long serialVersionUID = 1L;
    private transient FiberModel model;
    private transient AbstractMatrix matrix;
    private transient FiberCurvatureParamView paramview;
    private int min = 5;
    private int max = 5;
}
