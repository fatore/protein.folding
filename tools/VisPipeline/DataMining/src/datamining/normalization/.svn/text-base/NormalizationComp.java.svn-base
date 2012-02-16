/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.normalization;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import datamining.normalization.NormalizationFactory.NormalizationType;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Transformation",
name = "Normalization",
description = "Transform the data using a normalization process. " +
"It it is a Dense matrix, the original matrix will be modified.")
public class NormalizationComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        AbstractNormalization norm = NormalizationFactory.getInstance(type);
        output = norm.execute(input);
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
            paramview = new NormalizationParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        input = null;
        output = null;
    }

    /**
     * @return the type
     */
    public NormalizationType getNormalizationType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setNormalizationType(NormalizationType type) {
        this.type = type;
    }

    public static final long serialVersionUID = 1L;
    private transient NormalizationParamView paramview;
    private NormalizationType type = NormalizationType.VECTORS_UNIT_LENGTH;
    private transient AbstractMatrix input;
    private transient AbstractMatrix output;
}
