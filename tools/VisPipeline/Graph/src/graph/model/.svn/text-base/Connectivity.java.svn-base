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
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package graph.model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import visualizationbasics.model.AbstractInstance;

/**
 * This class represents the graph connectivity.
 * 
 * @author Fernando Vieira Paulovich
 */
public class Connectivity implements Serializable {

    /** Creates a new instance of Connectivity
     * 
     * @param name The connectivity's name
     */
    public Connectivity(String name, ArrayList<Edge> edges) {
        this.name = name;
        this.edges = compress(edges);
        this.showweight = false;
    }

    public void draw(GraphModel model, BufferedImage image, boolean highquality) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        if (highquality) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        ArrayList<AbstractInstance> instances = model.getInstances();

        int size = edges.size();
        for (int i = 0; i < size; i++) {
            GraphInstance source = (GraphInstance) instances.get(edges.get(i).getSource());
            GraphInstance target = (GraphInstance) instances.get(edges.get(i).getTarget());

            //Combines the color of the two vertex to paint the edge
            if (!source.isValid() && !target.isValid()) {
                g2.setColor(Color.BLACK);
            } else {
                float alpha = model.getAlpha();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.setColor(new Color((source.getColor().getRed() + target.getColor().getRed()) / 2,
                        (source.getColor().getGreen() + target.getColor().getGreen()) / 2,
                        (source.getColor().getBlue() + target.getColor().getBlue()) / 2));
            }

            g2.setStroke(new BasicStroke(1.3f));
            g2.drawLine(((int) source.getX()), ((int) source.getY()),
                    ((int) target.getX()), ((int) target.getY()));
            g2.setStroke(new BasicStroke(1.0f));

            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

            if (this.showweight) {
                String label = Float.toString(edges.get(i).getWeight());

                float x = 5 + (float) Math.abs(source.getX() - target.getX()) / 2 +
                        Math.min(source.getX(), target.getX());
                float y = (float) Math.abs(source.getY() - target.getY()) / 2 +
                        Math.min(source.getY(), target.getY());

                //Getting the font information
                FontMetrics metrics = g2.getFontMetrics(g2.getFont());

                //Getting the label size
                int width = metrics.stringWidth(label);
                int height = metrics.getAscent();

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                g2.setPaint(Color.WHITE);
                g2.fill(new Rectangle((int) x - 2, (int) y - height, width + 4, height + 4));
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

                g2.setColor(Color.BLACK);
                g2.drawRect((int) x - 2, (int) y - height, width + 4, height + 4);

                g2.drawString(label, x, y);
            }
        }
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public String getName() {
        return name;
    }

    private ArrayList<Edge> compress(ArrayList<Edge> edges) {
        if (edges.size() > 0) {
            Collections.sort(edges);
            ArrayList<Edge> edges_aux = edges;
            edges = new ArrayList<Edge>();

            int n = 0;
            edges.add(edges_aux.get(0));
            for (int i = 1; i < edges_aux.size(); i++) {
                if (!edges_aux.get(n).equals(edges_aux.get(i))) {
                    edges.add(edges_aux.get(i));
                    n = i;
                }
            }
        }

        return edges;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Connectivity) {
            return this.name.equals(((Connectivity) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3 + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isShowWeight() {
        return showweight;
    }

    public void setShowWeight(boolean showweight) {
        this.showweight = showweight;
    }

    public static final long serialVersionUID = 1L;
    private String name; //The connectivity name
    private ArrayList<Edge> edges; //The edges which composes the connectivity
    private boolean showweight; //to indicate if the weight is shown
}
