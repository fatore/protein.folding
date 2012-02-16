/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */
package visualizer.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import visualizer.forcelayout.ForceData;
import visualizer.view.color.ColorTable;

/**
 * This class represents a vertex on the map.
 * 
 * @author Fernando Vieira Paulovich
 */
public class Vertex implements Comparable, java.io.Serializable {

    /**
     * A vertex constructor
     *
     * @param id The identification of the vertex
     * @param x The x-coordinate of the vertex
     * @param y The y-coordinate of the vertex
     */
    public Vertex(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * A vertex constructor
     *
     * @param id The identification of the vertex
     */
    public Vertex(long id) {
        this.id = id;
    }

    /**
     * Draw the vertex on a graphical device
     * 
     * @param g2 The graphical device
     * @param globalsel Indicates if there is at least one selected vertex on 
     * the graph this vertex belongs to
     */
    public void draw(BufferedImage image, Graphics2D g2, boolean highquality) {
        if (image != null) {
            g2 = (Graphics2D) image.getGraphics();
        }

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (Vertex.drawAsCircles) {
            if (this.valid) {
                if (this.selected) {
                    g2.setStroke(new BasicStroke(4.0f));
                    rayBase *= 1.5;

                    g2.setColor(this.color);
                    g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y) -
                            this.getRay(), this.getRay() * 2, this.getRay() * 2);

                    g2.setColor(Color.DARK_GRAY);
                    g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) -
                            this.getRay(), this.getRay() * 2, this.getRay() * 2);

                    g2.setStroke(new BasicStroke(1.0f));
                    rayBase /= 1.5;
                } else {
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, alpha));

                    g2.setColor(this.color);
                    g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y) -
                            this.getRay(), this.getRay() * 2, this.getRay() * 2);

                    g2.setColor(Color.BLACK);
                    g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) -
                            this.getRay(), this.getRay() * 2, this.getRay() * 2);

                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                }

                //show the label associated to this vertex
                if (this.showLabel) {
                    g2.setFont(Vertex.font);
                    java.awt.FontMetrics metrics = g2.getFontMetrics(g2.getFont());

                    int width = metrics.stringWidth(this.toString().trim());
                    int height = metrics.getAscent();

                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.75f));
                    g2.setPaint(java.awt.Color.WHITE);
                    g2.fill(new java.awt.Rectangle(((int) this.x) + this.getRay() + 5 - 2,
                            ((int) this.y) - 1 - height, width + 4, height + 4));
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

                    g2.setColor(java.awt.Color.DARK_GRAY);
                    g2.drawRect(((int) this.x) + this.getRay() + 5 - 2, ((int) this.y) - 1 - height,
                            width + 4, height + 4);

                    g2.drawString(this.toString().trim(), ((int) this.x) + this.getRay() + 5, ((int) this.y));
                }
            } else { //not valid
                if (Vertex.showNonValid) {
                    int SIZE = 2;
                    g2.setColor(Color.WHITE);
                    g2.fillOval(((int) this.x) - SIZE, ((int) this.y) - SIZE, SIZE * 2, SIZE * 2);

                    g2.setColor(Color.GRAY);
                    g2.drawOval(((int) this.x) - SIZE, ((int) this.y) - SIZE, SIZE * 2, SIZE * 2);
                }
            }
        } else { //draw as points
            if (this.valid) {
                if (this.selected) {
                    if (image != null) {
                        int rgbcolor = this.color.getRGB();
                        image.setRGB((int) this.x, (int) this.y, rgbcolor);

                        int rgbborder = this.color.darker().darker().getRGB();
                        image.setRGB((int) this.x - 1, (int) this.y - 1, rgbborder);
                        image.setRGB((int) this.x, (int) this.y - 1, rgbborder);
                        image.setRGB((int) this.x + 1, (int) this.y - 1, rgbborder);
                        image.setRGB((int) this.x - 1, (int) this.y, rgbborder);
                        image.setRGB((int) this.x + 1, (int) this.y, rgbborder);
                        image.setRGB((int) this.x - 1, (int) this.y + 1, rgbborder);
                        image.setRGB((int) this.x, (int) this.y + 1, rgbborder);
                        image.setRGB((int) this.x + 1, (int) this.y + 1, rgbborder);
                    } else {
                        g2.setColor(this.color.darker().darker());
                        g2.fillRect((int) this.x - 1, (int) this.y - 1, 3, 3);

                        g2.setColor(this.color);
                        g2.fillRect((int) this.x, (int) this.y, 1, 1);
                    }
                } else {
                    if (image != null) {
                        int rgb = this.color.getRGB();
                        this.simulateAlpha(image, alpha, (int) this.x - 1, (int) this.y - 1, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x, (int) this.y - 1, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x + 1, (int) this.y - 1, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x - 1, (int) this.y, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x, (int) this.y, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x + 1, (int) this.y, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x - 1, (int) this.y + 1, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x, (int) this.y + 1, rgb);
                        this.simulateAlpha(image, alpha, (int) this.x + 1, (int) this.y + 1, rgb);
                    } else {
                        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, alpha));

                        g2.setColor(this.color);
                        g2.fillRect((int) this.x - 1, (int) this.y - 1, 3, 3);

                        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
                    }
                }

                //show the label associated to this vertex
                if (this.showLabel) {
                    g2.setFont(Vertex.font);
                    java.awt.FontMetrics metrics = g2.getFontMetrics(g2.getFont());

                    int width = metrics.stringWidth(this.toString().trim());
                    int height = metrics.getAscent();

                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.75f));
                    g2.setPaint(java.awt.Color.WHITE);
                    g2.fill(new java.awt.Rectangle(((int) this.x) + this.getRay() + 5 - 2,
                            ((int) this.y) - 1 - height, width + 4, height + 4));
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

                    g2.setColor(java.awt.Color.DARK_GRAY);
                    g2.drawRect(((int) this.x) + this.getRay() + 5 - 2, ((int) this.y) - 1 - height,
                            width + 4, height + 4);

                    g2.drawString(this.toString().trim(), ((int) this.x) + this.getRay() + 5, ((int) this.y));
                }
            } else { //non valid
                if (Vertex.showNonValid) {
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawLine((int) this.x - 1, (int) this.y - 1, (int) this.x + 1, (int) this.y - 1);
                    g2.drawLine((int) this.x - 1, (int) this.y, (int) this.x + 1, (int) this.y);
                    g2.drawLine((int) this.x - 1, (int) this.y + 1, (int) this.x + 1, (int) this.y + 1);
                }
            }
        }
    }

    /**
     * Check if the point (x,y) is inside this vertex
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     * @return Return true if the ponint (x,y) is inside the vertex; false otherwise
     */
    public boolean isInside(int x, int y) {
        return (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) <= this.getRay());
    }

    /**
     * Check if the vertex inside on a rectangle
     * @param rectangle The rectangle
     * @return Return true if the vertex inside the rectangle; false otherwise
     */
    public boolean isInside(java.awt.Rectangle rectangle) {
        if (((this.x >= rectangle.x) && (this.x - rectangle.x < rectangle.width)) &&
                ((this.y >= rectangle.y) && (this.y - rectangle.y < rectangle.height))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Return the color of the vertex
     * 
     * @return The color of the vertex
     */
    public Color getColor() {
        return color;
    }

    /**
     * Changes the color of the vertex
     * 
     * @param color The new color of the vertex
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Changes the color of the vertex according to a scalar.
     * 
     * @param scalar The scalar.
     * @param colorTable The color table used to color the vertex.
     */
    public void setColor(Scalar scalar, ColorTable colorTable) {
        if (this.valid && colorTable != null) {
            if (scalar.getMin() >= 0.0f && scalar.getMax() <= 1.0f) {
                this.color = colorTable.getColor(this.getScalar(scalar));
            } else {
                this.color = colorTable.getColor(this.getNormalizedScalar(scalar));
            }
        }
    }

    /**
     * Return the x-coordinate of the vertex
     * 
     * @return The x-coordinate of the vertex
     */
    public float getX() {
        return x;
    }

    /**
     * Changes the x-coordinate of the vertex
     * 
     * @param x The new x-coordinate of the vertex
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Return the y-coordinate of the vertex
     * 
     * @return The y-coordinate of the vertex
     */
    public float getY() {
        return y;
    }

    /**
     * Changes the y-coordinate of the vertex
     * 
     * @param y The new y-coordinate of the vertex
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Return the rayBase of the vertex
     *
     * @return The rayBase of the vertex
     */
    public static int getRayBase() {
        return rayBase;
    }

    /**
     * Changes the rayBase of all vertices.
     *
     * @param aRay The new rayBase of the vertex
     */
    public static void setRayBase(int aRay) {
        rayBase = aRay;
    }

    public void setRayFactor(float ray) {
        assert (ray >= 0.0f && ray <= 1.0f) : "Out of range ray factor.";

        this.rayFactor = ray;
    }

    public float getRayFactor() {
        return this.rayFactor;
    }

    public int getRay() {
        return (int) (rayBase + (this.rayFactor * rayBase));
    }

    public void setRayFactor(Scalar scalar) {
        if (this.valid) {
            if (scalar.getMin() >= 0.0f && scalar.getMax() <= 1.0f) {
                this.rayFactor = this.getScalar(scalar);
            } else {
                this.rayFactor = this.getNormalizedScalar(scalar);
            }
        }
    }

    /**
     * Return the file name associated to the vertex
     * 
     * @return The file name associated to the vertex
     */
    public String getUrl() {
        return url;
    }

    /**
     * Changes the file name associated to the vertex
     * 
     * @param url The new file name
     */
    public void setUrl(String url) {
        this.url = XMLGraphWriter.deConvert(url);
    }

    /**
     * Returns the vertex identification
     * 
     * @return The vertex identification
     */
    public long getId() {
        return id;
    }

    /**
     * Change the vertex identification
     * 
     * @param id The identification of the vertex
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return a string representing the vertex
     * 
     * @return A String representing the vertex
     */
    @Override
    public String toString() {
        if (this.titles.size() > this.indexTitle) {
            return this.titles.get(indexTitle);
        } else {
            return Long.toString(this.id);
        }
    }

    public void changeTitle(int index) {
        this.indexTitle = index;
    }

    public void setTitle(int index, String title) {
        title = XMLGraphWriter.deConvert(title);

        if (title.length() > 100) {
            title = title.substring(0, 96) + "...";
        }

        if (this.titles.size() > index) {
            this.titles.set(index, title);
        } else {
            int size = this.titles.size();
            for (int i = 0; i < index - size; i++) {
                this.titles.add("");
            }
            this.titles.add(title);
        }
    }

    public void removeTile(int index) {
        if (this.titles.size() > index) {
            this.titles.remove(index);
        }

        if (this.indexTitle == index) {
            this.indexTitle = 0;
        }
    }

    public void setScalar(Scalar scalar, float value) {
        assert (scalar.getIndex() >= 0) : "Error scalar created outside " +
                "the method Graph.addScalar(...).";

        if (scalar != null) {
            if (this.scalars.size() > scalar.getIndex()) {
                this.scalars.set(scalar.getIndex(), value);
            } else {
                int size = this.scalars.size();
                for (int i = 0; i < scalar.getIndex() - size; i++) {
                    this.scalars.add(0.0f);
                }
                this.scalars.add(value);
            }

            if (scalar.getMin() > value) {
                scalar.setMin(value);
            }

            if (scalar.getMax() < value) {
                scalar.setMax(value);
            }
        }
    }

    public float getScalar(Scalar scalar) {
        if (scalar != null && this.scalars.size() > scalar.getIndex() && scalar.getIndex() > -1) {
            return this.scalars.get(scalar.getIndex());
        } else {
            return 0.0f;
        }
    }

    public float getNormalizedScalar(Scalar scalar) {
        if (scalar != null && this.scalars.size() > scalar.getIndex() && scalar.getIndex() > -1) {
            if (scalar.getMax() > scalar.getMin()) {
                float value = this.scalars.get(scalar.getIndex());
                return (value - scalar.getMin()) / (scalar.getMax() - scalar.getMin());
            } else {
                return 0.0f;
            }
        } else {
            return 0.0f;
        }
    }

    public void removeScalar(Scalar scalar) {
        if (scalar != null && this.scalars.size() > scalar.getIndex()) {
            this.scalars.remove(scalar.getIndex());
        }
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public static boolean isShowNonValid() {
        return showNonValid;
    }

    public static void setShowNonValid(boolean aShowNonValid) {
        showNonValid = aShowNonValid;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static boolean isDrawAsCircles() {
        return drawAsCircles;
    }

    public static void setDrawAsCircles(boolean aDrawAsCircles) {
        drawAsCircles = aDrawAsCircles;
    }

    public static Font getFont() {
        return font;
    }

    public static void setFont(Font aFont) {
        font = aFont;
    }

    public boolean isShowLabel() {
        return this.showLabel;
    }

    public void setShowLabel(boolean showTitle) {
        this.showLabel = showTitle;
    }

    /**
     * @return the alpha
     */
    public static float getAlpha() {
        return alpha;
    }

    /**
     * @param aAlpha the alpha to set
     */
    public static void setAlpha(float aAlpha) {
        alpha = aAlpha;
    }

    //C = (alpha * (A-B)) + B
    private void simulateAlpha(BufferedImage image, float alpha, int x, int y, int rgb) {
        int oldrgb = image.getRGB(x, y);
        int oldr = (oldrgb >> 16) & 0xFF;
        int oldg = (oldrgb >> 8) & 0xFF;
        int oldb = oldrgb & 0xFF;

        int newr = (int) ((alpha * (((rgb >> 16) & 0xFF) - oldr)) + oldr);
        int newg = (int) ((alpha * (((rgb >> 8) & 0xFF) - oldg)) + oldg);
        int newb = (int) ((alpha * ((rgb & 0xFF) - oldb)) + oldb);

        int newrgb = newb | (newg << 8) | (newr << 16);
        image.setRGB(x, y, newrgb);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Vertex) {
            if (Math.abs(this.x - ((Vertex) o).x) == EPSILON) {
                if (Math.abs(this.y - ((Vertex) o).y) == EPSILON) {
                    return 0;
                } else if (Math.abs(this.y - ((Vertex) o).y) > EPSILON) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (Math.abs(this.x - ((Vertex) o).x) > EPSILON) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    private static final long serialVersionUID = 1L;
    private static final float EPSILON = 0.00001f;
    private long id = 0; //The vertex identification
    private ArrayList<Float> scalars = new ArrayList<Float>();  //The scalars associated with this vertex
    private ArrayList<String> titles = new ArrayList<String>();  //The titles associated with this vertex
    private int indexTitle;
    private boolean showLabel = false;
    private String url = "";  //The item url which the vertex represents
    private static Font font = new Font("Verdana", Font.BOLD, 9);
    private Color color = Color.BLACK; //The vertex color
    private float x = 0; //The x-coodinate of the vertex
    private float y = 0;  //The y-coodinate of the vertex
    private static int rayBase = 4; //The rayFactor of the vertex
    private float rayFactor = 0;  //The size of vertex ray (it must stay between 0.0 and 1.0)
    public ForceData fdata; //Use to repositioning the points
    private boolean valid = true; //identifies if a vertex is valid
    private boolean selected = false;
    private static boolean showNonValid = true;
    private static boolean drawAsCircles = true;
    private static float alpha = 1.0f;
}
