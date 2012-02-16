/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelcoordinates.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import matrix.AbstractVector;
import matrix.dense.DenseVector;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;
import visualizationbasics.color.ColorTable;
import visualizationbasics.model.AbstractModel;

/**
 *
 * @author Fernando
 */
public class ParallelCoordinatesModel extends AbstractModel {

    public enum PolylineNormalizationType {

        COLUMN_INDEPENDENT, COLUMN_ALL
    }

    public ParallelCoordinatesModel(ArrayList<String> attributes) {
        this.attributes = attributes;
        this.minklass = Float.POSITIVE_INFINITY;
        this.maxklass = Float.NEGATIVE_INFINITY;
        this.maxvalues = null;
        this.minvalues = null;
        this.norm = PolylineNormalizationType.COLUMN_INDEPENDENT;
        this.alpha = 1.0f;
        this.drawaxelables = true;
        this.drawverticalaxes = true;
        this.drawsilhouette = true;
        this.coloredinstances = true;

        this.setSize(800, 600);
        this.setColorTable(new ColorTable(ColorScaleType.PSEUDO_RAINBOW_SCALE));
    }

    public void draw(BufferedImage image, boolean highquality) {
        if (image != null) {
            //////first draw the non-selected instances
            for (int i = 0; i < instances.size(); i++) {
                PolylineInstance pi = (PolylineInstance) instances.get(i);

                if (!pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }

            ///////drawing other elements
            int width = image.getWidth() - 2 * PolylineInstance.LATERAL_INSET; //horizontal space
            int height = image.getHeight() - (PolylineInstance.TOP_INSET + PolylineInstance.BOTTOM_INSET); //vertical space
            int space = width / maxvalues.length; //spacing between axes
            Graphics2D g2 = (Graphics2D) image.getGraphics();

            for (int i = 0; i < maxvalues.length; i++) {
                int x1 = PolylineInstance.LATERAL_INSET + (i * space);
                int y1 = PolylineInstance.TOP_INSET;

                int x2 = x1;
                int y2 = PolylineInstance.TOP_INSET + height;

                /////draw labels
                if (drawaxelables) {
                    if (attributes.size() == maxvalues.length) {
                        g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
                        String label = attributes.get(i);

                        //limiting the number of chars used to represent a number
                        try {
                            double value = Float.parseFloat(label);
                            DecimalFormat df  = (DecimalFormat)DecimalFormat.getInstance(Locale.ENGLISH);
                            df.applyPattern("0.00E0");
                            label = df.format(value);
                        } catch (NumberFormatException nfe) {
                        }

                        g2.setColor(Color.BLACK);
                        g2.drawString(label,
                                x1 - PolylineInstance.LATERAL_INSET / 2,
                                y1 - PolylineInstance.LATERAL_INSET / 2);

                        g2.setColor(Color.WHITE);
                        g2.fillRect((x1 + space) - PolylineInstance.LATERAL_INSET,
                                y1 - PolylineInstance.TOP_INSET,
                                width,
                                PolylineInstance.TOP_INSET);
                    }
                }

                /////draw silhouette boxes
                if (drawsilhouette && silhouettes != null) {
                    if (silhouettes[i] > 0.0f) {
                        g2.setColor(Color.BLUE);
                    } else if (silhouettes[i] < 0.0f) {
                        g2.setColor(Color.RED);
                    } else {
                        g2.setColor(Color.WHITE);
                    }

                    g2.fillRect(x1 - PolylineInstance.LATERAL_INSET / 2,
                            y1 - PolylineInstance.LATERAL_INSET * 3,
                            (int) ((silhouettes[i] + 1) / 2 * (space - PolylineInstance.LATERAL_INSET / 2)),
                            PolylineInstance.LATERAL_INSET);

                    g2.setColor(Color.BLACK);
                    g2.drawRect(x1 - PolylineInstance.LATERAL_INSET / 2,
                            y1 - PolylineInstance.LATERAL_INSET * 3,
                            space - PolylineInstance.LATERAL_INSET / 2,
                            PolylineInstance.LATERAL_INSET);
                }

                /////draw vertical lines
                if (drawverticalaxes) {
                    g2.setColor(Color.BLACK);
                    g2.drawLine(x1, y1, x2, y2);
                }
            }

            ///////then the selected instances
            for (int i = 0; i < instances.size(); i++) {
                PolylineInstance pi = (PolylineInstance) instances.get(i);

                if (pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }
        }
    }

    public void updateKlassRange(float value) {
        if (maxklass < value) {
            maxklass = value;
        }

        if (minklass > value) {
            minklass = value;
        }
    }

    public void updateValuesRange(float[] values) {
        if (maxvalues == null) {
            maxvalues = new float[values.length];
            minvalues = new float[values.length];
            Arrays.fill(maxvalues, Float.NEGATIVE_INFINITY);
            Arrays.fill(minvalues, Float.POSITIVE_INFINITY);
        }

        if (maxvalues.length == values.length) {
            for (int i = 0; i < values.length; i++) {
                if (maxvalues[i] < values[i]) {
                    maxvalues[i] = values[i];
                }

                if (minvalues[i] > values[i]) {
                    minvalues[i] = values[i];
                }
            }
        } else {
            throw new RuntimeException("ERROR: trying to add an instance of different size!");
        }
    }

    public float[] getMaxValues() {
        return maxvalues;
    }

    public float[] getMinValues() {
        return minvalues;
    }

    public float getMaxKlass() {
        return maxklass;
    }

    public float getMinKlass() {
        return minklass;
    }

    public float getAlpha() {
        return alpha;
    }

    public Dimension getSize() {
        return size;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public boolean isColoredInstances() {
        return coloredinstances;
    }

    public void setColoredInstances(boolean value) {
        this.coloredinstances = value;
        setChanged();
    }

    public boolean isDrawVerticalAxes() {
        return drawverticalaxes;
    }

    public void setDrawVerticalAxes(boolean value) {
        this.drawverticalaxes = value;
        setChanged();
    }

    public boolean isDrawAxeLabels() {
        return drawaxelables;
    }

    public void setDrawAxeLabels(boolean value) {
        this.drawaxelables = value;
        setChanged();
    }

    public boolean isDrawSilhouetteBoxes() {
        return drawsilhouette;
    }

    public void setDrawSilhouetteBoxes(boolean value) {
        this.drawsilhouette = value;
        setChanged();
    }

    public PolylineNormalizationType getNormalization() {
        return norm;
    }

    public void setNormalization(PolylineNormalizationType norm) {
        this.norm = norm;

        //changing the screen positions
        for (int i = 0; i < instances.size(); i++) {
            ((PolylineInstance) instances.get(i)).calculateScreenPositions();
        }

        setChanged();
    }

    public void setSize(int width, int height) {
        if (size == null) {
            size = new Dimension();
        }

        size.width = width;
        size.height = height;

        //changing the screen positions
        for (int i = 0; i < instances.size(); i++) {
            ((PolylineInstance) instances.get(i)).calculateScreenPositions();
        }

        setChanged();
    }

    public void setAlpha(float value) {
        if (value >= 0.0f && value <= 1.0f) {
            this.alpha = value;
            setChanged();
        }
    }

    public ColorTable getColorTable() {
        return ctable;
    }

    public void setColorTable(ColorTable ctable) {
        this.ctable = ctable;

        //changing the instances color
        for (int i = 0; i < instances.size(); i++) {
            ((PolylineInstance) instances.get(i)).calculateColor();
        }

        setChanged();
    }

    public AbstractVector getColumn(int col) {
        float[] vect = new float[instances.size()];
        for (int i = 0; i < vect.length; i++) {
            vect[i] = ((PolylineInstance) instances.get(i)).getOriginalValues()[col];
        }

        AbstractVector vector = new DenseVector(vect, col);
        return vector;
    }

    public void calculateSilhouette() {
        silhouettes = new float[maxvalues.length];
        Arrays.fill(silhouettes, 0.0f);

        //calculating the silhouette for all dimensions
        Silhouette sil = new Silhouette();

        for (int i = 0; i < silhouettes.length; i++) {
            silhouettes[i] = sil.calculate(instances, new int[]{i});
        }

        setChanged();
    }

    public ArrayList<PolylineInstance> getInstancesByPosition(Rectangle rect) {
        int width = size.width - 2 * PolylineInstance.LATERAL_INSET; //horizontal space
        int space = width / maxvalues.length; //spacing between axes

        int iniaxe = -1;
        int finaxe = -1;

        //finding the axes inside the rectangle
        for (int i = 0; i < maxvalues.length; i++) {
            int x = PolylineInstance.LATERAL_INSET + (i * space);

            if (x >= rect.x && x <= rect.x + rect.width) {
                if (iniaxe == -1) {
                    iniaxe = i;
                    finaxe = i;
                } else {
                    finaxe = i;
                }
            }
        }

        ArrayList<PolylineInstance> selected = new ArrayList<PolylineInstance>();

        for (int i = 0; i < instances.size(); i++) {
            PolylineInstance pi = (PolylineInstance) instances.get(i);

            if (iniaxe >= 0 && pi.isInside(rect, iniaxe, finaxe)) {
                selected.add(pi);
            }
        }

        return selected;
    }

    public PolylineInstance getInstanceByPosition(Point point) {
        for (int i = 0; i < instances.size(); i++) {
            PolylineInstance pi = (PolylineInstance) instances.get(i);

            if (pi.isInside(point.x, point.y)) {
                return pi;
            }
        }

        return null;
    }
    private float[] silhouettes;
    private ArrayList<String> attributes;
    private float alpha;
    private ColorTable ctable;
    private PolylineNormalizationType norm;
    private float minklass;
    private float maxklass;
    private float[] minvalues;
    private float[] maxvalues;
    private boolean drawverticalaxes;
    private boolean drawaxelables;
    private boolean drawsilhouette;
    private Dimension size;
    private boolean coloredinstances;
}
