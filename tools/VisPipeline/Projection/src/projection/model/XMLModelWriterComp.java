/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.model;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Output",
name = "Projection Model  XML writer",
description = "Write a projection model to a XML file.")
public class XMLModelWriterComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            if (model != null) {
                XMLModelWriter xmw = new XMLModelWriter();
                xmw.write(model, filename);
            } else {
                throw new IOException("A projection model must be provided to write.");
            }
        } else {
            throw new IOException("A projection model file name must be provided to write.");
        }
    }

    public void input(@Param(name = "model") ProjectionModel model) {
        this.model = model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new XMLModelWriterParamView(this);
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
    private transient XMLModelWriterParamView paramview;
    private transient ProjectionModel model;
}
