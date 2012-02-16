/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.model;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Projection.Input",
name = "Projection model XML reader",
description = "Read a projection model from an XML file.")
public class XMLModelReaderComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            XMLModelReader xmr = new XMLModelReader();

            model = new ProjectionModel();
            xmr.read(model, filename);
        } else {
            throw new IOException("A projection model file name must be provided to write.");
        }
    }

    public ProjectionModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new XMLModelReaderParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        model = null;
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
    private transient ProjectionModel model;
    private transient XMLModelReaderParamView paramview;
}
