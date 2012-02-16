/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.model;

import projection.model.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class GroupedProjectionInstance extends ProjectionInstance {

    public GroupedProjectionInstance(ProjectionModel model, int id) {
        super(model, id);
        this.groupedInstances = new ArrayList<ProjectionInstance>();
        cont++;
    }

    public GroupedProjectionInstance(ProjectionModel model, int id, float x, float y) {
        super(model, id, x, y);
        this.groupedInstances = new ArrayList<ProjectionInstance>();
    }

    public ArrayList<ProjectionInstance> getGroupedInstances() {
        return groupedInstances;
    }

    public void setGroupedInstances(ArrayList<ProjectionInstance> groupedInstances) {
        this.groupedInstances = groupedInstances;
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        //super.draw(image, highquality);
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (selected) {
            g2.setColor(color.RED);
            //g2.setColor(this.color);
            g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(),
                    this.getRay() * 2, this.getRay() * 2);

            g2.setColor(Color.GRAY);
            g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(),
                    this.getRay() * 2, this.getRay() * 2);
        } else {
            g2.setColor(color.PINK);
            //g2.setColor(this.color);
            g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(),
                    this.getRay() * 2, this.getRay() * 2);

            g2.setColor(Color.BLACK);
            g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(),
                    this.getRay() * 2, this.getRay() * 2);
        }

        //show the label associated to this instance
        if (showlabel) {
            java.awt.FontMetrics metrics = g2.getFontMetrics(g2.getFont());

            int width = metrics.stringWidth(toString().trim());
            int height = metrics.getAscent();

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.75f));
            g2.setPaint(java.awt.Color.WHITE);
            g2.fillRect(((int) x) + 3, ((int) y) - 1 - height, width + 4, height + 4);

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(java.awt.Color.DARK_GRAY);
            g2.drawRect(((int) x) + 3, ((int) y) - 1 - height, width + 4, height + 4);

            g2.drawString(toString().trim(), ((int) x) + 3, ((int) y));
        }
    }

    public void splitGroup() {
//        for (AbstractInstance pi : groupedInstances) {
//            this.getModel().addInstance(pi);
//        }
    }

    @Override
    public boolean isInside(int x, int y) {
        return (Math.abs((this.x - x)) <= 3 && Math.abs((this.y - y)) <= 3);
    }

    public int getRay() {
        return ray = 5;
    }

    public void setRay(int ray) {
        this.ray = ray;
    }
    public static int cont;
    private ArrayList<ProjectionInstance> groupedInstances;
    private int ray;
}
