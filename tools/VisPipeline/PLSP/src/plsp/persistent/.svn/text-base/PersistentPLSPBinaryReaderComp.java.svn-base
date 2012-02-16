/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.persistent;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Projection.Input",
name = "Persistent P-LSP binary file reader",
description = "Read the P-LSP information from a binary file.")
public class PersistentPLSPBinaryReaderComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        System.out.println("\n\n READING \n");
        plsp = PersistentPLSPBinaryReader.read(filename);
    }

    public PersistentPLSPProjection2D output() {
        return plsp;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new PersistentPLSPBinaryReaderParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        plsp = null;
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
    private transient PersistentPLSPProjection2D plsp;
    private transient PersistentPLSPBinaryReaderParamView paramview;
}
