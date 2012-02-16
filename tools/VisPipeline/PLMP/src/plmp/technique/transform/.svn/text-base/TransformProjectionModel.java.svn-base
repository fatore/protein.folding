/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plmp.technique.transform;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import projection.model.ProjectionInstance;
import projection.model.ProjectionModel;

/**
 *
 * @author Fernando
 */
public class TransformProjectionModel extends ProjectionModel {

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
    
    private int instancesize = 4;
}
