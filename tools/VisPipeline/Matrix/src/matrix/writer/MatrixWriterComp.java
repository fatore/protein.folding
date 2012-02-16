/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix.writer;

import matrix.*;
import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Points.Output",
name = "Points matrix writer",
description = "Write a Points Matrix to a file.")
public class MatrixWriterComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            matrix.save(filename);
        } else {
            throw new IOException("A matrix file name must be provided to write.");
        }
    }

    public void input(@Param(name = "matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new MatrixWriterParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        matrix = null;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static final long serialVersionUID = 1L;
    private String filename = "";
    private transient MatrixWriterParamView paramview;
    private transient AbstractMatrix matrix;
}
