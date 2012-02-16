/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing.processing.transformation;

import java.io.IOException;
import matrix.AbstractMatrix;
import textprocessing.processing.transformation.MatrixTransformationFactory.MatrixTransformationType;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Text Processing",
name = "Matrix Transformation",
description = "Transform a \"documents x terms\" matrix weighting the terms " +
"according to some measure.",
howtocite = "Salton, G.; Buckley, C. Term-weighting approaches in automatic " +
"text retrieval.Information Processing & Management, v. 24, n. 5, p. 513�523, 1988.")
public class MatrixTransformationComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        MatrixTransformation trans = MatrixTransformationFactory.getInstance(matrixtrans);
        output = trans.tranform(input, null);
    }

    public void input(@Param(name = "\"documents x terms\" matrix") AbstractMatrix input) {
        this.input = input;
    }

    public AbstractMatrix output() {
        return output;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new MatrixTransformationParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        input = null;
        output = null;
    }

    /**
     * @return the matrixtrans
     */
    public MatrixTransformationType getMatrixTransformationType() {
        return matrixtrans;
    }

    /**
     * @param matrixtrans the matrixtrans to set
     */
    public void setMatrixTransformationType(MatrixTransformationType matrixtrans) {
        this.matrixtrans = matrixtrans;
    }

    public static final long serialVersionUID = 1L;
    private MatrixTransformationType matrixtrans = MatrixTransformationType.TF_IDF;
    private transient MatrixTransformationParamView paramview;
    private transient AbstractMatrix input;
    private transient AbstractMatrix output;
}
