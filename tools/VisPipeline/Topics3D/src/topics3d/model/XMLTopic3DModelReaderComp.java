/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.model;

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
name = "Topics Projection model 3D XML reader",
description = "Read a topic projection model 3D from an XML file.")
public class XMLTopic3DModelReaderComp implements  AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (filename.trim().length() > 0) {
            XMLTopic3DModelReader xmr = new XMLTopic3DModelReader();

            model = new TopicProjection3DModel(typeGlyph);
            xmr.read(model, filename);
        } else {
            throw new IOException("A projection model 3D file name must be provided to write.");
        }
    }

    public TopicProjection3DModel output() {
        return model;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new XMLTopic3DModelReaderParamView(this);
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
    private transient TopicProjection3DModel model;
    private transient XMLTopic3DModelReaderParamView paramview;
}
