/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Distance.Input",
name = "Distance Matrix reader",
description = "Read a Distance Matrix from a file.")
public class DistanceMatrixReaderComp implements AbstractComponent {

    @Override
    public void execute() {
        try {
            dmat = new DistanceMatrix(filename);
        } catch (IOException ex) {
            Logger.getLogger(DistanceMatrixReaderComp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DistanceMatrix output() {
        return dmat;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new DistanceMatrixReaderParamView(this);
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
    private transient DistanceMatrixReaderParamView paramview;
    private transient DistanceMatrix dmat;
}
