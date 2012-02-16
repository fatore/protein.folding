/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.similaritymatrix;

import java.awt.image.BufferedImage;
import visualizationbasics.color.ColorTable;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SimilarityMatrixInstance extends AbstractInstance {

    public SimilarityMatrixInstance(SimilarityMatrixModel model, String label, int id, int position, float cdata) {
        super(model, id);
        this.label = label;
        this.position = position;
        this.cdata = cdata;
    }

    public void draw(BufferedImage image, int border) {
        SimilarityMatrixModel smmodel = (SimilarityMatrixModel) model;

        for (int i = 0; i < distances.length; i++) {
            float colorvalue = 0.0f;

            if (smmodel.getMaxDistance() > smmodel.getMinDistance()) {
                colorvalue = (distances[i] - smmodel.getMinDistance()) /
                        (smmodel.getMaxDistance() - smmodel.getMinDistance());
            }

            ColorTable colortable = smmodel.getColorTable();
            ColorTable selcolortable = smmodel.getSelectionColorTable();

            if (selected) {
                image.setRGB(border + position, i, selcolortable.getColor(colorvalue).getRGB());
                image.setRGB(border + i, position, selcolortable.getColor(colorvalue).getRGB());
            } else {
                image.setRGB(border + position, i, colortable.getColor(1.0f - colorvalue).getRGB());
                image.setRGB(border + i, position, colortable.getColor(1.0f - colorvalue).getRGB());
            }
        }
    }

    public void setDistancesToInstances(float[] distances) {
        this.distances = distances;

        SimilarityMatrixModel smmodel = (SimilarityMatrixModel) model;
        float max = smmodel.getMaxDistance();
        float min = smmodel.getMinDistance();

        for (int i = 0; i < distances.length; i++) {
            if (max < distances[i]) {
                max = distances[i];
            }

            if (min > distances[i]) {
                min = distances[i];
            }
        }

        smmodel.setMaxDistance(max);
        smmodel.setMinDistance(min);
    }

    public float getClassData() {
        return cdata;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return label;
    }

    private String label;
    private float[] distances;
    private int position;
    private float cdata;
}
