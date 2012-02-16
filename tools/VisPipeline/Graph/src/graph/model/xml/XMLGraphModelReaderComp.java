/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.model.xml;

import graph.model.*;
import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Projection.Input",
name = "Graph model XML reader",
description = "Read a projection model from an XML file.")
public class XMLGraphModelReaderComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            XMLGraphModelReader xmr = new XMLGraphModelReader();

            model = new GraphModel();
            xmr.read(model, filename);
        } else {
            throw new IOException("A projection model file name must be provided to write.");
        }
    }

    public GraphModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new XMLGraphModelReaderParamView(this);
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
    private transient GraphModel model;
    private transient XMLGraphModelReaderParamView paramview;
}
