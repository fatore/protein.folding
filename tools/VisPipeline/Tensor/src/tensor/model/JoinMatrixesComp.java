/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;


/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Tensor.Features",
name = "Join Matrixes",
description = "Join two matrixes.")
public class JoinMatrixesComp implements AbstractComponent {

    public enum TypeAppend {

        NEXT, UNDER
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new JoinMatrixesParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        this.matrixA = null;
        this.matrixB = null;
        this.matrixOutput = null;
    }

    @Override
    public void execute() throws IOException {
        if (matrixA != null && matrixB != null) {

            if (type.equals(TypeAppend.NEXT)) {
                if (matrixA.getRowCount() == matrixB.getRowCount()) {
                    matrixOutput = new DenseMatrix();
                    ArrayList<String> attrs = new ArrayList<String>();
                    attrs.addAll(matrixA.getAttributes());
                    attrs.addAll(matrixB.getAttributes());
                    matrixOutput.setAttributes(attrs);

                    for (int i=0; i<matrixA.getRowCount(); i++) {
                        float[] a = matrixA.getRow(i).getValues();
                        float[] b = matrixB.getRow(i).getValues();
                        float[] ab = new float[a.length+b.length];

                        System.arraycopy(a, 0, ab, 0, a.length);
                        System.arraycopy(b, 0, ab, a.length, b.length);

                        AbstractVector vec = matrixA.getRow(i);
                        matrixOutput.addRow(new DenseVector(ab, vec.getId(), vec.getKlass()));
                    }
                } else {
                    throw new IOException("Both matrixes should have the same number of rows.");
                }
            } else {
                if (matrixA.getDimensions() == matrixB.getDimensions()) {
                    try {
                        matrixOutput = (AbstractMatrix) matrixA.clone();
                        for (int i=0; i<matrixB.getRowCount(); i++) {
                            matrixOutput.addRow((AbstractVector)matrixB.getRow(i).clone());
                        }

                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(JoinMatrixesComp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    throw new IOException("Both matrixes should have the same number of columns (dimension).");
                }
            }          
        } else {
            throw new IOException("Two Matrixes should be provided as input.");
        }
    }

    public void input(@Param(name = "Matrix A") AbstractMatrix matrixA, @Param(name = "Matrix B") AbstractMatrix matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }

    public AbstractMatrix output() {
        return matrixOutput;
    }

        /**
     * @return the type
     */
    public TypeAppend getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeAppend type) {
        this.type = type;
    }

    public static final long serialVersionUID = 1L;
    private transient AbstractMatrix matrixA;
    private transient AbstractMatrix matrixB;
    private transient AbstractMatrix matrixOutput;
    private transient JoinMatrixesParamView paramview;
    private TypeAppend type = TypeAppend.NEXT;
}
