/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.similaritymatrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;
import visualizationbasics.color.ColorTable;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.model.AbstractModel;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SimilarityMatrixModel extends AbstractModel {

    public SimilarityMatrixModel() {
        try {
            this.max = 0.0f;
            this.min = 0.0f;
            this.zoomfactor = 1.0f;
            this.colortable = new ColorTable(ColorScaleType.GRAY_SCALE);
            this.selcolortable = new ColorTable(ColorScaleType.HEATED_OBJECTS);
            this.colortable.getColorScale().setMinMax(0.25f, 1.0f);
            this.colortable.getColorScale().setReverse(true);
        } catch (IOException ex) {
            Logger.getLogger(SimilarityMatrixModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void draw(Graphics2D g2) {
        if (origimage == null) {
            int size = instances.size();

            origimage = new BufferedImage(border + size, border + size, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < instances.size(); i++) {
                if (!instances.get(i).isSelected()) {
                    ((SimilarityMatrixInstance)instances.get(i)).draw(origimage, border);
                }
            }

            for (int i = 0; i < selinstances.size(); i++) {
                ((SimilarityMatrixInstance)selinstances.get(i)).draw(origimage, border);
            }

            //draw the class color legend
            float mincdata = Float.POSITIVE_INFINITY;
            float maxcdata = Float.NEGATIVE_INFINITY;

            for (int i = 0; i < instances.size(); i++) {
                SimilarityMatrixInstance smi = (SimilarityMatrixInstance)instances.get(i);

                if (mincdata > smi.getClassData()) {
                    mincdata = smi.getClassData();
                }

                if (maxcdata < smi.getClassData()) {
                    maxcdata = smi.getClassData();
                }
            }

            ColorTable legcolortable = new ColorTable(ColorScaleType.PSEUDO_RAINBOW_SCALE);
            Graphics2D g2Buffer = (Graphics2D) origimage.getGraphics();

            for (int i = 0; i < instances.size(); i++) {
                SimilarityMatrixInstance smi = (SimilarityMatrixInstance)instances.get(i);

                float colorvalue = (smi.getClassData() - mincdata) / (maxcdata - mincdata);
                g2Buffer.setColor(legcolortable.getColor(colorvalue));

                g2Buffer.fillRect(0, i, border, i + 1);
                g2Buffer.fillRect(i + border, size, i + 1 + border, size + border);
            }

            g2Buffer.setColor(Color.WHITE);
            g2Buffer.fillRect(0, size, border, size + border);
            g2Buffer.dispose();
        }

        int size = (int) (origimage.getHeight() * zoomfactor);
        Image redimage = origimage.getScaledInstance(size, size, 0);
        g2.drawImage(redimage, 0, 0, null);
    }

    public ColorTable getColorTable() {
        return colortable;
    }

    public ColorTable getSelectionColorTable() {
        return selcolortable;
    }

    public float getMaxDistance() {
        return max;
    }

    public void setMaxDistance(float max) {
        this.max = max;
    }

    public float getMinDistance() {
        return min;
    }

    public void setMinDistance(float min) {
        this.min = min;
    }

    public void setZoomFactor(float zoomfactor) {
        this.zoomfactor = zoomfactor;
        setChanged();
    }

    public float getZoomFactor() {
        return zoomfactor;
    }

    public void changeColorScaleType(ColorScaleType scaletype) {
        colortable.setColorScaleType(scaletype);
        origimage = null;
        setChanged();
    }

    public Dimension getSize() {
        if (origimage != null) {
            int size = (int) (origimage.getHeight() * zoomfactor);
            return new Dimension(size, size);
        } else {
            return new Dimension(instances.size() + border, instances.size() + border);
        }
    }

    public ArrayList<SimilarityMatrixInstance> getInstancesByPosition(Rectangle rect) {
        ArrayList<SimilarityMatrixInstance> selected = new ArrayList<SimilarityMatrixInstance>();

        rect.x = (int) (rect.x / zoomfactor - border);
        rect.y = (int) (rect.y / zoomfactor);
        rect.width = (int) (rect.width / zoomfactor);
        rect.height = (int) (rect.height / zoomfactor);

        for (int i = 0; i < instances.size(); i++) {
            SimilarityMatrixInstance smi = (SimilarityMatrixInstance)instances.get(i);

            if ((smi.getPosition() >= rect.x && smi.getPosition() <= rect.x + rect.width) &&
                    (smi.getPosition() >= rect.y && smi.getPosition() <= rect.y + rect.height)) {
                selected.add(smi);
            }
        }

        return selected;
    }

    public ArrayList<SimilarityMatrixInstance> getInstancesByPosition(Point point) {
        ArrayList<SimilarityMatrixInstance> selected = new ArrayList<SimilarityMatrixInstance>();

        for (int i = 0; i < instances.size(); i++) {
            SimilarityMatrixInstance smi = (SimilarityMatrixInstance)instances.get(i);

            int x = (int) (point.x / zoomfactor - border);
            int y = (int) (point.y / zoomfactor);
            int width = (int) (instances.size() / zoomfactor) - border;
            int height = (int) (instances.size() / zoomfactor);

            if ((smi.getPosition() == x && y <= height) ||
                    (smi.getPosition() == y && x <= width)) {
                selected.add(smi);
            }
        }

        return selected;
    }

    @Override
    public void cleanSelectedInstances() {
        super.cleanSelectedInstances();
        origimage = null;
    }

    @Override
    public void removeInstances(ArrayList<AbstractInstance> reminst) {
        super.removeInstances(reminst);
        origimage = null;
    }

    @Override
    public void removeSelectedInstances() {
        super.removeSelectedInstances();
        origimage = null;
    }

    @Override
    public void setSelectedInstance(AbstractInstance selinst) {
        super.setSelectedInstance(selinst);
        origimage = null;
    }

    @Override
    public void setSelectedInstances(ArrayList<AbstractInstance> selinst) {
        super.setSelectedInstances(selinst);
        origimage = null;
    }

    private float max;
    private float min;
    private float zoomfactor;
    private ColorTable colortable;
    private ColorTable selcolortable;
    private BufferedImage origimage;
    private static final int border = 10;
}
