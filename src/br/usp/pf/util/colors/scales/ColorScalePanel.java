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

package br.usp.pf.util.colors.scales;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ColorScalePanel extends JPanel {

    public ColorScalePanel(JPanel gv) {
//        this.gv = gv;

        this.scale = new ColorScalePanel.ColorScale();

        this.maxLabel.setForeground(java.awt.Color.GRAY);
        this.maxLabel.setFont(new java.awt.Font("Verdana", Font.BOLD, 10));

        this.minLabel.setForeground(java.awt.Color.GRAY);
        this.minLabel.setFont(new java.awt.Font("Verdana", Font.BOLD, 10));

//        this.scale.setBorder(new javax.swing.border.LineBorder(java.awt.Color.GRAY, 1, true));
//        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.setToolTipText("Click to change the color scale");

        this.setLayout(new java.awt.BorderLayout(5, 5));
        this.add(this.scale, java.awt.BorderLayout.CENTER);
        this.add(this.maxLabel, java.awt.BorderLayout.EAST);
        this.add(this.minLabel, java.awt.BorderLayout.WEST);

        this.addMouseListener(new MouseClickedListener());
    }

    /**
	 * @param colorTable
	 */
    public void setColorTable(ColorTable colorTable) {
        this.colorTable = colorTable;
    }

    class MouseClickedListener extends MouseAdapter {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            super.mouseClicked(evt);
//            if (ColorScalePanel.this.gv != null) {
//                ColorScaleChange.getInstance(gv.getProjectionExplorerView(), gv).display();
//            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            ColorScalePanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            ColorScalePanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }

    public class ColorScale extends javax.swing.JPanel {

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            if (colorTable != null) {
//                minLabel.setForeground(colorTable.getColor(0.0f));
//                maxLabel.setForeground(colorTable.getColor(1.0f));

                //Getting the panel dimension - horizontal fill
                java.awt.Dimension size = this.getSize();
                int height = size.height;
                int width = size.width;

                for (int i = 0; i <= width; i++) {
                    float index = ((float) i) / ((float) width);
                    g.setColor(colorTable.getColor(index));
                    g.drawRect(i, 0, i, height);
                    g.fillRect(i, 0, i, height);
                }
            }
        }

    }

    /**
	 */
    private javax.swing.JLabel maxLabel = new javax.swing.JLabel("Max");
    /**
	 */
    private javax.swing.JLabel minLabel = new javax.swing.JLabel("Min");
    /**
	 */
    private ColorScalePanel.ColorScale scale;
    /**
	 */
    private ColorTable colorTable;
//    private Viewer gv;
}
