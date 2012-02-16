/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import projection3d.model.Projection3DInstance;

/**
 *
 * @author Jorge Poco
 */
public class TopicProjection3DInstance extends Projection3DInstance {

    public TopicProjection3DInstance(TopicProjection3DModel model, int id) {
        super(model, id);
    }

    public TopicProjection3DInstance(TopicProjection3DModel model, int id, float x, float y, float z) {
        super(model, id, x, y, z);

        sizefactor = 0;
        label = "id: " + id;
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        TopicProjection3DModel tmodel = (TopicProjection3DModel) model;

        if (drawAsCircles) {
            if (selected) {
                int inssize = tmodel.getInstanceSize();

                g2.setStroke(new BasicStroke(4.0f));
                inssize *= 1.5;

                g2.setColor(color);
                g2.fillOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setColor(Color.DARK_GRAY);
                g2.drawOval(((int) x) - inssize, ((int) y) - inssize, inssize * 2, inssize * 2);

                g2.setStroke(new BasicStroke(1.0f));
            } else {
                int inssize = tmodel.getInstanceSize();

                g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, tmodel.getAlpha()));

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
                float alpha = ((TopicProjection3DModel) model).getAlpha();
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
        int inssize = ((TopicProjection3DModel) model).getInstanceSize();
        return (int) (inssize + (sizefactor * inssize));
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    private boolean drawAsCircles = true;
    private float sizefactor;
    private String label;
}
