/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author PC
 */
public class LabeledProjectionInstance extends ProjectionInstance {

    public LabeledProjectionInstance(ProjectionModel model, String label, int id, float x, float y) {
        super(model, id, x, y);
        this.label = label;
        this.size = 4;
    }

    public LabeledProjectionInstance(ProjectionModel model, String label, int id) {
        this(model, label, id, 0, 0);
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        int xaux = (((int) this.x) <= 0) ? 1 : (((int) this.x) < image.getWidth()) ? (int) this.x : image.getWidth() - 1;
        int yaux = (((int) this.y) <= 0) ? 1 : (((int) this.y) < image.getHeight()) ? (int) this.y : image.getHeight() - 1;

        if (selected) {
            int rgbcolor = color.getRGB();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    image.setRGB(xaux + i, yaux + j, rgbcolor);
                }
            }

            g2.setColor(Color.GRAY);
            g2.drawRect(xaux - 2, yaux - 2, size + 3, size + 3);
            g2.drawRect(xaux - 1, yaux - 1, size + 1, size + 1);

        } else {
            int rgb = color.getRGB();
            float alpha = ((ProjectionModel) model).getAlpha();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    simulateAlpha(image, alpha, xaux + i, yaux + j, rgb);
                }
            }
        }

        //show the label associated to this instance
        if (showlabel) {
            java.awt.FontMetrics metrics = g2.getFontMetrics(g2.getFont());

            int width = metrics.stringWidth(toString().trim());
            int height = metrics.getAscent();

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.75f));
            g2.setPaint(java.awt.Color.WHITE);
            g2.fillRect(xaux + 3, yaux - 1 - height, width + 4, height + 4);

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(java.awt.Color.DARK_GRAY);
            g2.drawRect(xaux + 3, yaux - 1 - height, width + 4, height + 4);

            g2.drawString(toString().trim(), xaux + 3, yaux);
        }
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean isInside(int x, int y) {
        return (Math.abs(x - this.x) <= size && Math.abs(y - this.y) <= size);
    }
    
    private int size;
    private String label;
}
