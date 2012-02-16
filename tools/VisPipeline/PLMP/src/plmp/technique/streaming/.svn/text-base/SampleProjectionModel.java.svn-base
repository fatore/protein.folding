/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique.streaming;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.model.ProjectionInstance;
import projection.model.ProjectionModel;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando
 */
public class SampleProjectionModel extends ProjectionModel {

    public SampleProjectionModel(AbstractMatrix samplematrix) {
        this.samplematrix = samplematrix;
    }

    public AbstractMatrix getSampleMatrix() {
        return samplematrix;
    }

    public AbstractMatrix getSampleProjection() {
        DenseMatrix projection = new DenseMatrix();

        for (int i = 0; i < instances.size(); i++) {
            AbstractInstance ins = instances.get(i);
            float[] vect = new float[2];
            vect[0] = ((ProjectionInstance) ins).getX();
            vect[1] = ((ProjectionInstance) ins).getY();
            projection.addRow(new DenseVector(vect));
        }

        return projection;
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        if (image != null) {
            //first draw the non-selected instances
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (!pi.isSelected()) {
                    drawInstance(image, pi);
                }
            }

            //then the selected instances
            for (int i = 0; i < instances.size(); i++) {
                ProjectionInstance pi = (ProjectionInstance) instances.get(i);

                if (pi.isSelected()) {
                    drawInstance(image, pi);
                }
            }
        }
    }

    @Override
    public ProjectionInstance getInstanceByPosition(Point point) {
        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (isInsideInstance(pi, point.x, point.y)) {
                return pi;
            }
        }

        return null;
    }

    private boolean isInsideInstance(ProjectionInstance pi, int x, int y) {
        return (Math.sqrt(Math.pow(x - pi.getX(), 2) + Math.pow(y - pi.getY(), 2)) <= instancesize);
    }

    private void drawInstance(BufferedImage image, ProjectionInstance pi) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (pi.isSelected()) {
            int inssize = instancesize;

            g2.setStroke(new BasicStroke(4.0f));
            inssize *= 1.5;

            g2.setColor(pi.getColor());
            g2.fillOval(((int) pi.getX()) - inssize, ((int) pi.getY()) - inssize, inssize * 2, inssize * 2);

            g2.setColor(Color.DARK_GRAY);
            g2.drawOval(((int) pi.getX()) - inssize, ((int) pi.getY()) - inssize, inssize * 2, inssize * 2);

            g2.setStroke(new BasicStroke(1.0f));
        } else {
            int inssize = instancesize;

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, getAlpha()));

            g2.setColor(pi.getColor());
            g2.fillOval(((int) pi.getX()) - inssize, ((int) pi.getY()) - inssize, inssize * 2, inssize * 2);

            g2.setColor(Color.BLACK);
            g2.drawOval(((int) pi.getX()) - inssize, ((int) pi.getY()) - inssize, inssize * 2, inssize * 2);

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    @Override
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

        float begin = 200.0f;

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance pi = (ProjectionInstance) instances.get(i);

            if (maxx != minx) {
                pi.setX((((pi.getX() - minx) / (maxx - minx)) *
                        (size.width - begin)) + begin);
            } else {
                pi.setX(begin);
            }

            if (maxy != miny) {
                pi.setY(((((pi.getY() - miny) / (maxy - miny)) *
                        (size.width - begin)) + begin));
            } else {
                pi.setY(begin);
            }
        }

        setChanged();
    }

    private int instancesize = 4;
    private AbstractMatrix samplematrix;
}
