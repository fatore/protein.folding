/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.persistent;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Danilo Medeiros Eler
 */
@VisComponent(hierarchy = "Projection.Output",
name = "Persistent P-LSP binary file writer",
description = "Write the P-LSP information to a binary file.")
public class PersistentPLSPBinaryWriterComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            PersistentPLSPBinaryWriter bmw = new PersistentPLSPBinaryWriter();
            System.out.println("\n\n WRITING \n");
            bmw.write(plsp, filename);
        } else {
            throw new IOException("A matrix file name must be provided to write.");
        }
    }

    public void input(@Param(name = "persistent P-LSP") PersistentPLSPProjection2D plsp) {
        this.plsp = plsp;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new PersistentPLSPBinaryWriterParamView(this);
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
    private transient PersistentPLSPBinaryWriterParamView paramview;
    private transient PersistentPLSPProjection2D plsp;
}
