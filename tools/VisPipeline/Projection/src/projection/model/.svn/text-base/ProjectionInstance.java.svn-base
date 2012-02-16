/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.model;

import visualizationbasics.model.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ProjectionInstance extends AbstractInstance {

    public ProjectionInstance(ProjectionModel model, int id, float x, float y) {
        super(model, id);

        this.x = x;
        this.y = y;
        this.scalars = new ArrayList<Float>();
        this.showlabel = false;
        this.color = Color.BLACK;
    }

    public ProjectionInstance(ProjectionModel model, int id) {
        this(model, id, 0.0f, 0.0f);
    }

    public void draw(BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        int xaux = (((int) this.x) <= 0) ? 1 : (((int) this.x) < image.getWidth()) ? (int) this.x : image.getWidth() - 1;
        int yaux = (((int) this.y) <= 0) ? 1 : (((int) this.y) < image.getHeight()) ? (int) this.y : image.getHeight() - 1;

        if (selected) {
            int rgbcolor = color.getRGB();
            image.setRGB(xaux - 1, yaux - 1, rgbcolor);
            image.setRGB(xaux, yaux - 1, rgbcolor);
            image.setRGB(xaux + 1, yaux - 1, rgbcolor);
            image.setRGB(xaux - 1, yaux, rgbcolor);
            image.setRGB(xaux, yaux, rgbcolor);
            image.setRGB(xaux + 1, yaux, rgbcolor);
            image.setRGB(xaux - 1, yaux + 1, rgbcolor);
            image.setRGB(xaux, yaux + 1, rgbcolor);
            image.setRGB(xaux + 1, yaux + 1, rgbcolor);

            g2.setColor(Color.GRAY);
            g2.drawRect(xaux - 2, yaux - 2, 4, 4);
        } else {
            int rgb = color.getRGB();
            float alpha = ((ProjectionModel) model).getAlpha();
            simulateAlpha(image, alpha, xaux - 1, yaux - 1, rgb);
            simulateAlpha(image, alpha, xaux, yaux - 1, rgb);
            simulateAlpha(image, alpha, xaux + 1, yaux - 1, rgb);
            simulateAlpha(image, alpha, xaux - 1, yaux, rgb);
            simulateAlpha(image, alpha, xaux, yaux, rgb);
            simulateAlpha(image, alpha, xaux + 1, yaux, rgb);
            simulateAlpha(image, alpha, xaux - 1, yaux + 1, rgb);
            simulateAlpha(image, alpha, xaux, yaux + 1, rgb);
            simulateAlpha(image, alpha, xaux + 1, yaux + 1, rgb);
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

    public boolean isInside(int x, int y) {
        return (Math.abs((this.x - x)) <= 1 && Math.abs((this.y - y)) <= 1);
    }

    public boolean isInside(Rectangle rect) {
        return ((x >= rect.x) && (x - rect.x < rect.width))
                && ((y >= rect.y) && (y - rect.y < rect.height));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        model.setChanged();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        model.setChanged();
    }

    public boolean isShowLabel() {
        return showlabel;
    }

    public void setShowLabel(boolean showlabel) {
        this.showlabel = showlabel;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setScalarValue(Scalar scalar, float value) {
        if (scalar != null) {
            int index = ((ProjectionModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index) {
                scalars.set(index, value);
            } else {
                int size = scalars.size();
                for (int i = 0; i < index - size; i++) {
                    scalars.add(0.0f);
                }
                scalars.add(value);
            }

            scalar.store(value);
        }
    }

    public float getScalarValue(Scalar scalar) {
        if (scalar != null) {
            int index = ((ProjectionModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index && index > -1) {
                return scalars.get(index);
            }
        }

        return 0.0f;
    }

    public float getNormalizedScalarValue(Scalar scalar) {
        if (scalar != null) {
            int index = ((ProjectionModel) model).getScalars().indexOf(scalar);

            if (scalars.size() > index && index > -1) {
                float value = scalars.get(index);
                return (value - scalar.getMin()) / (scalar.getMax() - scalar.getMin());
            }
        }

        return 0.0f;
    }

    protected void simulateAlpha(BufferedImage image, float alpha, int x, int y, int rgb) {
        try {
            //C = (alpha * (A-B)) + B
            int oldrgb = image.getRGB(x, y);
            int oldr = (oldrgb >> 16) & 0xFF;
            int oldg = (oldrgb >> 8) & 0xFF;
            int oldb = oldrgb & 0xFF;

            int newr = (int) ((alpha * (((rgb >> 16) & 0xFF) - oldr)) + oldr);
            int newg = (int) ((alpha * (((rgb >> 8) & 0xFF) - oldg)) + oldg);
            int newb = (int) ((alpha * ((rgb & 0xFF) - oldb)) + oldb);

            int newrgb = newb | (newg << 8) | (newr << 16);
            image.setRGB(x, y, newrgb);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(x + "," + y);
        }
    }

    protected ArrayList<Float> scalars;
    protected float x;
    protected float y;
    protected boolean showlabel;
    protected Color color;
}
