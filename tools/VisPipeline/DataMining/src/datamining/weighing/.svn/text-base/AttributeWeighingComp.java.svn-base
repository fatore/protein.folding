/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining.weighing;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Transformation",
name = "Attribute Weighing",
description = "Transform the data using a weighing process.")
public class AttributeWeighingComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        // AbstractNormalization norm = NormalizationFactory.getInstance(type);
        //output = norm.execute(input);
        float[][] points = null;
        
        if (this.input instanceof DenseMatrix) {
            points = new float[this.input.getRowCount()][];

            for (int i = 0; i < points.length; i++) {
                points[i] = this.input.getRow(i).getValues();
            }
        } else {
            points = this.input.toMatrix();
        }

        float[][] wPoints = new float[points.length][this.input.getDimensions()];
        for (int col=0; col<this.input.getDimensions(); col++){
            float weight = this.weights.get(col);
            for (int row=0; row<this.input.getRowCount(); row++){
                wPoints[row][col] = weight *  points[row][col];
            }
        }


        this.output = new DenseMatrix();
        for (int i=0; i<this.input.getRowCount(); i++){
            this.output.addRow( new DenseVector(wPoints[i], this.input.getRow(i).getId(), this.input.getRow(i).getKlass()));
        }
        this.output.setAttributes(this.input.getAttributes());
    }

    public void input(@Param(name = "points matrix") AbstractMatrix input) {
        this.input = input;
    }

    public AbstractMatrix output() {
        return output;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new AttributeWeighingParamView(this);
        }
        return paramview;
    }

    @Override
    public void reset() {
        input = null;
        output = null;
    }

    public AbstractMatrix getInput() {
        return input;
    }

    public void setWeights(ArrayList<Float> weights) {
        this.weights = weights;
    }
    
    public static final long serialVersionUID = 1L;
    private transient ArrayList<Float> weights;
    private transient AttributeWeighingParamView paramview;
    private transient AbstractMatrix input;
    private transient AbstractMatrix output;
}
