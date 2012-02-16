/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection3d.model;

import java.io.IOException;
import projection3d.model.Projection3DModel.TypeGlyph;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Projection3D.Input",
name = "Projection model 3D XML reader",
description = "Read a projection model 3D from an XML file.")
public class XMLModel3DReaderComp implements  AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            XMLModelReader xmr = new XMLModelReader();

            model = new Projection3DModel(typeGlyph);
            xmr.read(model, filename);
        } else {
            throw new IOException("A projection model 3D file name must be provided to write.");
        }
    }

    public Projection3DModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new XMLModel3DReaderParamView(this);
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

    public void setTypeGlyph(TypeGlyph type) {
        typeGlyph = type;
    }

    public TypeGlyph getTypeGlyph() {
        return typeGlyph;
    }

    public static final long serialVersionUID = 1L;
    private TypeGlyph typeGlyph = TypeGlyph.SPHERE;
    private String filename = "";
    private transient Projection3DModel model;
    private transient XMLModel3DReaderParamView paramview;
}
