/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import graph.forcelayout.ForceData;
import java.awt.BasicStroke;
import projection.model.ProjectionInstance;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class GraphInstance extends ProjectionInstance {

    public GraphInstance(GraphModel model, int id, float x, float y) {
        super(model, id, x, y);
        sizefactor = 0;
    }

    public GraphInstance(GraphModel model, int id) {
        this(model, id, 0.0f, 0.0f);
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        GraphModel gmodel = (GraphModel) model;

        if (drawAsCircles) {
            if (selected) {
                int inssize = gmodel.getInstanceSize();

                g2.setStroke(new BasicStroke(4.0f));
                inssize *= 1.5;

                g2.setColor(color);
                g2.fillOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setColor(Color.DARK_GRAY);
                g2.drawOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setStroke(new BasicStroke(1.0f));
            } else {
                int inssize = gmodel.getInstanceSize();

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, gmodel.getAlpha()));

                g2.setColor(color);
                g2.fillOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setColor(Color.BLACK);
                g2.drawOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            }
        } else {
            if (selected) {
                int rgbcolor = color.getRGB();
                image.setRGB((int) x - 1, (int) y - 1, rgbcolor);
                image.setRGB((int) x, (int) y - 1, rgbcolor);
                image.setRGB((int) x + 1, (int) y - 1, rgbcolor);
                image.setRGB((int) x - 1, (int) y, rgbcolor);
                image.setRGB((int) x, (int) y, rgbcolor);
                image.setRGB((int) x + 1, (int) y, rgbcolor);
                image.setRGB((int) x - 1, (int) y + 1, rgbcolor);
                image.setRGB((int) x, (int) y + 1, rgbcolor);
                image.setRGB((int) x + 1, (int) y + 1, rgbcolor);

                g2.setColor(Color.GRAY);
                g2.drawRect((int) x - 2, (int) y - 2, 4, 4);
            } else {
                int rgb = color.getRGB();
                float alpha = ((GraphModel) model).getAlpha();
                simulateAlpha(image, alpha, (int) x - 1, (int) y - 1, rgb);
                simulateAlpha(image, alpha, (int) x, (int) y - 1, rgb);
                simulateAlpha(image, alpha, (int) x + 1, (int) y - 1, rgb);
                simulateAlpha(image, alpha, (int) x - 1, (int) y, rgb);
                simulateAlpha(image, alpha, (int) x, (int) y, rgb);
                simulateAlpha(image, alpha, (int) x + 1, (int) y, rgb);
                simulateAlpha(image, alpha, (int) x - 1, (int) y + 1, rgb);
                simulateAlpha(image, alpha, (int) x, (int) y + 1, rgb);
                simulateAlpha(image, alpha, (int) x + 1, (int) y + 1, rgb);
            }
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

    @Override
    public boolean isInside(int x, int y) {
        return (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) <= getSize());
    }

    /**
     * @return the sizefactor
     */
    public float getSizeFactor() {
        return sizefactor;
    }

    /**
     * @param sizefactor the sizefactor to set
     */
    public void setSizeFactor(float sizefactor) {
        this.sizefactor = sizefactor;
        model.setChanged();
    }

    public int getSize() {
        int inssize = ((GraphModel) model).getInstanceSize();
        return (int) (inssize + (sizefactor * inssize));
    }
    
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public ForceData fdata; //Use to repositioning the points
    private float sizefactor;
    private boolean drawAsCircles = true;
    private boolean valid = true; //identifies if a vertex is valid
}
