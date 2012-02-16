/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;
import visualizationbasics.color.ColorTable;
import visualizationbasics.model.AbstractModel;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ProjectionModel extends AbstractModel {

    public ProjectionModel() {
        this.scalars = new ArrayList<Scalar>();
        this.alpha = 1.0f;
        this.colortable = new ColorTable();
        this.selscalar = null;
    }

    public void draw(BufferedImage image, boolean highquality) {
        if (image != null) {
            //first draw the non-selected instances
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (!pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }

            //then the selected instances
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (pi.isSelected()) {
                    pi.draw(image, highquality);
                }
            }
        }
    }

    public ProjectionInstance getInstanceByPosition(Point point) {
        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (pi.isInside(point.x, point.y)) {
                return pi;
            }
        }

        return null;
    }

    public ArrayList<ProjectionInstance> getInstancesByPosition(Rectangle rect) {
        ArrayList<ProjectionInstance> selected = new ArrayList<ProjectionInstance>();

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (pi.isInside(rect)) {
                selected.add(pi);
            }
        }

        return selected;
    }

    public ArrayList<ProjectionInstance> getInstancesByPosition(Polygon polygon) {
        ArrayList<ProjectionInstance> selected = new ArrayList<ProjectionInstance>();

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (polygon.contains(pi.getX(), pi.getY())) {
                selected.add(pi);
            }
        }

        return selected;
    }

    public void fitToSize(Dimension size) {
        float maxx = Float.NEGATIVE_INFINITY;
        float minx = Float.POSITIVE_INFINITY;
        float maxy = Float.NEGATIVE_INFINITY;
        float miny = Float.POSITIVE_INFINITY;

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxx < pi.getX()) {
                maxx = pi.getX();
            }

            if (minx > pi.getX()) {
                minx = pi.getX();
            }

            if (maxy < pi.getY()) {
                maxy = pi.getY();
            }

            if (miny > pi.getY()) {
                miny = pi.getY();
            }
        }

        float begin = 30.0f;
        float endy = 0.0f;
        float endx = 0.0f;

        if (maxy > maxx) {
            endy = Math.min(size.width, size.height) - begin;

            if (maxy != miny) {
                endx = ((maxx - minx) * endy) / (maxy - miny);
            } else {
                endx = ((maxx - minx) * endy);
            }
        } else {
            endx = Math.min(size.width, size.height) - begin;

            if (maxx != minx) {
                endy = ((maxy - miny) * endx) / (maxx - minx);
            } else {
                endy = ((maxy - miny) * endx);
            }
        }

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxx != minx) {
                pi.setX((((pi.getX() - minx) / (maxx - minx)) *
                        (endx - begin)) + begin);
            } else {
                pi.setX(begin);
            }

            if (maxy != miny) {
                pi.setY(((((pi.getY() - miny) / (maxy - miny)) *
                        (endy - begin)) + begin));
            } else {
                pi.setY(begin);
            }
        }

        setChanged();
    }

    public Dimension getSize() {
        if (instances.size() > 0) {
            float maxx = Float.MIN_VALUE;
            float maxy = Float.MIN_VALUE;

            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (maxx < pi.getX()) {
                    maxx = pi.getX();
                }

                if (maxy < pi.getY()) {
                    maxy = pi.getY();
                }
            }

            int w = (int) (maxx) + 30;
            int h = (int) (maxy) + 30;

            return new Dimension(w, h);
        } else {
            return new Dimension(0, 0);
        }
    }

    public Scalar addScalar(String name) {
        Scalar scalar = new Scalar(name);

        if (!scalars.contains(scalar)) {
            scalars.add(scalar);
            return scalar;
        } else {
            return scalars.get(scalars.indexOf(scalar));
        }
    }

    public ArrayList<Scalar> getScalars() {
        return scalars;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        setChanged();
    }

    public void changeColorScaleType(ColorScaleType scaletype) {
        colortable.setColorScaleType(scaletype);
        setSelectedScalar(selscalar);
        setChanged();
    }

    public ColorTable getColorTable() {
        return colortable;
    }

    public Scalar getSelectedScalar() {
        return selscalar;
    }

    public void setSelectedScalar(Scalar scalar) {
        if (scalars.contains(scalar)) {
            selscalar = scalar;

            //change the color of each instance
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (scalar.getMin() >= 0.0f && scalar.getMax() <= 1.0f) {
                    pi.setColor(colortable.getColor(pi.getScalarValue(scalar)));
                } else {
                    pi.setColor(colortable.getColor(pi.getNormalizedScalarValue(scalar)));
                }
            }
        } else {
            selscalar = null;

            //change the color of each instance
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);
                pi.setColor(Color.BLACK);
            }
        }

        setChanged();
    }

    public void zoom(float rate) {
        float maxX = Float.NEGATIVE_INFINITY;
        float minX = Float.POSITIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxX < pi.getX()) {
                maxX = pi.getX();
            }

            if (minX > pi.getX()) {
                minX = pi.getX();
            }

            if (maxY < pi.getY()) {
                maxY = pi.getY();
            }

            if (minY > pi.getY()) {
                minY = pi.getY();
            }

        }

        float endX = maxX * rate;
        float endY = maxY * rate;

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxX != minX) {
                pi.setX((((pi.getX() - minX) / (maxX - minX)) * (endX - minX)) + minX);
            } else {
                pi.setX(minX);
            }

            if (maxY != minY) {
                pi.setY(((((pi.getY() - minY) / (maxY - minY)) * (endY - minY)) + minY));
            } else {
                pi.setY(minY);
            }
        }

        setChanged();
    }

    public void normalizeVertex(float begin, float end) {
        float maxX = Float.NEGATIVE_INFINITY;
        float minX = Float.POSITIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxX < pi.getX()) {
                maxX = pi.getX();
            }

            if (minX > pi.getX()) {
                minX = pi.getX();
            }

            if (maxY < pi.getY()) {
                maxY = pi.getY();
            }

            if (minY > pi.getY()) {
                minY = pi.getY();
            }
        }

        float endX = ((maxX - minX) * end);
        if (maxY != minY) {
            endX = ((maxX - minX) * end) / (maxY - minY);
        }

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxX != minX) {
                pi.setX((((pi.getX() - minX) / (maxX - minX)) *
                        (endX - begin)) + begin);
            } else {
                pi.setX(begin);
            }

            if (maxY != minY) {
                pi.setY(((((pi.getY() - minY) / (maxY - minY)) *
                        (end - begin)) + begin));
            } else {
                pi.setY(begin);
            }
        }
    }
    
    protected ArrayList<Scalar> scalars;
    protected float alpha;
    protected ColorTable colortable;
    protected Scalar selscalar;
}
