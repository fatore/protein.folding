/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix.reader;

import matrix.*;
import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Points.Input",
name = "Points matrix reader",
description = "Read a Points Matrix from a file.")
public class MatrixReaderComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        matrix = MatrixFactory.getInstance(filename);
    }

    public AbstractMatrix output() {
        return matrix;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new MatrixReaderParamView(this);
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
    private transient AbstractMatrix matrix;
    private transient MatrixReaderParamView paramview;
}
