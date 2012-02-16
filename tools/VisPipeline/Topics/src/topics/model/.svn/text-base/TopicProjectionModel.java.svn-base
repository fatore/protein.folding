/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import projection.model.ProjectionModel;
import projection.model.Scalar;
import textprocessing.corpus.Corpus;
import topics.Topic;
import topics.scalar.QuerySolver;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class TopicProjectionModel extends ProjectionModel {

    public TopicProjectionModel() {
        this.topics = new ArrayList<Topic>();
        this.showtopics = false;
        this.instancesize = 4;
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        if (image != null) {
            //first draw the non-selected instances
            for (int i = 0; i < instances.size(); i++) {
                TopicProjectionInstance pi = (TopicProjectionInstance) instances.get(i);

                if (!pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }

            //then the selected instances
            for (int i = 0; i < instances.size(); i++) {
                TopicProjectionInstance pi = (TopicProjectionInstance) instances.get(i);

                if (pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }
        }
    }

    public boolean isShowTopics() {
        return showtopics;
    }

    public void setShowTopics(boolean showtopics) {
        this.showtopics = showtopics;

        for (Topic t : topics) {
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

    public void addTopic(Topic topic) {
        this.topics.add(topic);
        setChanged();
    }

    public ArrayList<Topic> getTopics() {
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

    public Topic getTopicByPosition(Point point) {
        float dist = Float.MAX_VALUE;
        Topic topic = null;

        for (Topic t : this.topics) {
            float aux = t.weightDistance(point);
            if (aux != -1 && dist > aux) {
                dist = aux;
                topic = t;
            }
        }

        return topic;
    }

    public static ArrayList<TopicProjectionInstance> convertInstances(ArrayList<AbstractInstance> aiins) {
        ArrayList<TopicProjectionInstance> conv = new ArrayList<TopicProjectionInstance>();
        for (AbstractInstance ai : aiins) {
            if (ai instanceof TopicProjectionInstance) {
                conv.add((TopicProjectionInstance) ai);
            }
        }

        return conv;
    }

    private int instancesize;
    private boolean showtopics;
    private ArrayList<Topic> topics;
    private Corpus corpus;
}
