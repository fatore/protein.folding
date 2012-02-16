/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelcoordinates.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import matrix.AbstractVector;
import parallelcoordinates.model.ParallelCoordinatesModel.PolylineNormalizationType;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando
 */
public class PolylineInstance extends AbstractInstance {

    public PolylineInstance(ParallelCoordinatesModel model, AbstractVector instance, String label) {
        super(model, instance.getId());

        this.klass = instance.getKlass();
        model.updateKlassRange(this.klass);
        this.originalvalues = instance.toArray();
        model.updateValuesRange(this.originalvalues);
        this.label = label;
        this.screenpositions = null;
        this.color = null;
    }

    public PolylineInstance(ParallelCoordinatesModel model, AbstractVector instance) {
        this(model, instance, Integer.toString(instance.getId()));
    }

    public void draw(BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (screenpositions == null) {
            calculateScreenPositions();
        }

        if (color == null) {
            calculateColor();
        }

        ParallelCoordinatesModel pmodel = (ParallelCoordinatesModel) model;

        for (int i = 0; i < screenpositions.length - 1; i++) {
            Point p1 = screenpositions[i];
            Point p2 = screenpositions[i + 1];

            if (selected) {
                g2.setColor(color);
                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            } else {
                if (pmodel.isColoredInstances()) {
                    g2.setColor(color);
                } else {
                    g2.setColor(Color.LIGHT_GRAY);
                }

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, pmodel.getAlpha()));
            }

            //draw the segment from (x1,y1) to (x2,y2)
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public boolean isInside(int x, int y) {
        if (screenpositions != null) {
            for (int i = 0; i < screenpositions.length - 1; i++) {
                Point p1 = screenpositions[i];
                Point p2 = screenpositions[i + 1];

                double dist = Line2D.ptSegDist(p1.x, p1.y, p2.x, p2.y, x, y);

                if (dist < 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isInside(Rectangle rect, int initialaxe, int finalaxe) {
        if (screenpositions != null) {
            //calculating if this instance is inside the rectangle
            for (int i = initialaxe; i <= finalaxe; i++) {
                Point p = screenpositions[i];

                if (p.y >= rect.y && p.y <= (rect.y + rect.height)) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getLabel() {
        return label;
    }

    public float[] getOriginalValues() {
        return originalvalues;
    }

    public float getKlass() {
        return klass;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public void calculateScreenPositions() {
        screenpositions = new Point[originalvalues.length];

        ParallelCoordinatesModel pmodel = (ParallelCoordinatesModel) model;

        int width = pmodel.getSize().width - 2 * LATERAL_INSET; //horizontal space
        int height = pmodel.getSize().height - (TOP_INSET + BOTTOM_INSET); //vertical space
        int space = width / originalvalues.length; //spacing between axes

        float[] maxvalues = pmodel.getMaxValues();
        float[] minvalues = pmodel.getMinValues();

        if (pmodel.getNormalization() == PolylineNormalizationType.COLUMN_INDEPENDENT) {
            for (int i = 0; i < originalvalues.length; i++) {
                //calculating position
                screenpositions[i] = new Point();
                screenpositions[i].x = LATERAL_INSET + (i * space);
                screenpositions[i].y = TOP_INSET + height;

                if (maxvalues[i] > minvalues[i]) {
                    screenpositions[i].y = TOP_INSET
                            + (int) (height * ((originalvalues[i] - minvalues[i]) / (maxvalues[i] - minvalues[i])));
                }
            }
        } else if (pmodel.getNormalization() == PolylineNormalizationType.COLUMN_ALL) {
            float max = Float.NEGATIVE_INFINITY;
            float min = Float.POSITIVE_INFINITY;

            for (int i = 0; i < maxvalues.length; i++) {
                if (max < maxvalues[i]) {
                    max = maxvalues[i];
                }

                if (min > minvalues[i]) {
                    min = minvalues[i];
                }
            }

            for (int i = 0; i < originalvalues.length; i++) {
                //calculating position
                screenpositions[i] = new Point();
                screenpositions[i].x = LATERAL_INSET + (i * space);
                screenpositions[i].y = TOP_INSET + height;

                if (max > min) {
                    screenpositions[i].y = TOP_INSET
                            + (int) (height * ((originalvalues[i] - min) / (max - min)));
                }
            }
        }
    }

    public void calculateColor() {
        ParallelCoordinatesModel pmodel = (ParallelCoordinatesModel) model;

        float maxklass = pmodel.getMaxKlass();
        float minklass = pmodel.getMinKlass();

        //defining the color
        float colorindex = 0;
        if (maxklass > minklass) {
            colorindex = (klass - minklass) / (maxklass - minklass);
        }

        color = pmodel.getColorTable().getColor(colorindex);
    }
    public static final int LATERAL_INSET = 10;
    public static final int BOTTOM_INSET = 10;
    public static final int TOP_INSET = 50;
    private float klass;
    private String label;
    private float[] originalvalues;
    private Point[] screenpositions;
    private Color color;
}
