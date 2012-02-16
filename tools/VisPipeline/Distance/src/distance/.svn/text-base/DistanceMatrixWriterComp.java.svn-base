/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Distance.Output",
name = "Distance Matrix writer",
description = "Write a Distance Matrix to a file.")
public class DistanceMatrixWriterComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            dmat.save(filename);
        } else {
            throw new IOException("A distance matrix file name must be provided to write.");
        }
    }

    public void input(@Param(name = "distance matrix") DistanceMatrix dmat) {
        this.dmat = dmat;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new DistanceMatrixWriterParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        dmat = null;
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
    private transient DistanceMatrixWriterParamView paramview;
    private transient DistanceMatrix dmat;
}
