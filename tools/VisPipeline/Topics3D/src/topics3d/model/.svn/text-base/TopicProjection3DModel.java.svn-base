/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import projection.model.Scalar;
import projection3d.model.Projection3DModel;
import textprocessing.corpus.Corpus;
import topics3d.Topic3D;
import topics3d.scalar.QuerySolver;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkDataArray;
import vtk.vtkRenderer;

/**
 *
 * @author Jorge Poco
 */
public class TopicProjection3DModel extends Projection3DModel {

    public TopicProjection3DModel(TypeGlyph type) {
        super(type);
        this.topics = new ArrayList<Topic3D>();
        this.showtopics = false;
        this.instancesize = 4;
    }

    // this fucntion is in charge of paint each glyph with an opcity given
    @Override
    public void draw(vtkRenderer ren) {
        drawTopics();

        boolean globalsel = selinstances.size() > 0 ? false : true;

        // set colors and opacities
        vtkDataArray activeColors = getColorArray(selscalar.getName());
        if (activeColors != null) {
            for (int i = 0; i < instances.size(); i++) {
                float opacity = instances.get(i).isSelected() || globalsel ? alpha * 254 : alphaNS * 254;
                activeColors.SetComponent(i, 3, opacity);
            }
        }
        polydata.GetPointData().SetScalars(getColorArray(selscalar.getName()));
        polydata.GetPointData().Modified();
    }

        public void drawTopics() {
        Enumeration<DefaultMutableTreeNode> e = surfacesRoot.depthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (!node.isRoot())
                renderer.AddActor(((Topic3D)node.getUserObject()).getActor());
        }
        setChanged();
    }

    @Override
    public void hideAllSurfaces() {
    Enumeration<DefaultMutableTreeNode> e = surfacesRoot.depthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (!node.isRoot())
                ((Topic3D)node.getUserObject()).getActor().SetVisibility(0);
        }
        setChanged();
    }

    public boolean isShowTopics() {
        return showtopics;
    }

    public void setShowTopics(boolean showtopics) {
        this.showtopics = showtopics;

        for (Topic3D t : topics) {
            t.setShowTopic(showtopics);
        }

        setChanged();
    }

    public Corpus getCorpus() {
        return corpus;
    }

    public void setCorpus(Corpus corpus) {
        this.corpus = corpus;
    }

    public void addTopic(Topic3D topic) {
        this.topics.add(topic);
        setChanged();
    }

    public ArrayList<Topic3D> getTopics() {
        return topics;
    }

    /**
     * @return the sizebase
     */
    public int getInstanceSize() {
        return instancesize;
    }

    /**
     * @param aSizebase the sizebase to set
     */
    public void setInstanceSize(int instancesize) {
        this.instancesize = instancesize;
        setChanged();
    }

    public Scalar createQueryScalar(String word) throws IOException {
        if (corpus == null) {
            throw new IOException("The corpus must be loaded!");
        }

        //Adding a new scalar
        String scalarName = "'" + word + "'";
        Scalar scalar = this.addScalar(scalarName);

        QuerySolver qS = new QuerySolver(corpus, instances);
        qS.createCdata(word, scalar);

        return scalar;
    }

    public static ArrayList<TopicProjection3DInstance> convertInstances(ArrayList<AbstractInstance> aiins) {
        ArrayList<TopicProjection3DInstance> conv = new ArrayList<TopicProjection3DInstance>();
        for (AbstractInstance ai : aiins) {
            if (ai instanceof TopicProjection3DInstance) {
                conv.add((TopicProjection3DInstance) ai);
            }
        }

        return conv;
    }

    private int instancesize;
    private boolean showtopics;
    private ArrayList<Topic3D> topics;
    private Corpus corpus;
}
