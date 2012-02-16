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
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>, 
 * Roberto Pinho <robertopinho@yahoo.com.br>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package topics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class StringBox {

    /** Creates a new instance of StringBox
     * @param msg 
     */
    public StringBox(String message) {
        this.message = message;
    }

    public Rectangle draw(Graphics g, Point position, Font font) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());

        //Getting the label size
        int width = metrics.stringWidth(message);
        int height = metrics.getAscent();

        //Creating the rectangle to be drawn
        rectangle = new Rectangle(position.x - 2, position.y - 2, width + 4, height + 4);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
        //g2.setPaint(java.awt.Color.YELLOW);
        g2.setColor(new Color(255, 255, 204));

        g2.drawRect(rectangle.x - 1, rectangle.y - 1, rectangle.width + 2, rectangle.height + 2);
        g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        g2.setColor(Color.BLACK);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.drawString(message, position.x, position.y + height - 2);

        return rectangle;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    private String message = "";
    private Rectangle rectangle;
}
