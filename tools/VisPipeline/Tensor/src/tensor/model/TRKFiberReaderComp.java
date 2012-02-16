/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Tensor.Input",
name = "Fiber tracks reader",
description = "Read a fiber tracks from an TRK file.")
public class TRKFiberReaderComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new TRKFiberReaderParamView(this);
        }
        return paramview;
    }

    public FiberModel output() {
        return model;
    }

    @Override
    public void reset() {
        model = null;
    }

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            TRKModelReader tr = new TRKModelReader();

            model = new FiberModel();
            tr.read(model, filename);
        } else {
            throw new IOException("A fibers model file name must be provided to write.");
        }
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
    private transient FiberModel model;
    private transient TRKFiberReaderParamView paramview;
}