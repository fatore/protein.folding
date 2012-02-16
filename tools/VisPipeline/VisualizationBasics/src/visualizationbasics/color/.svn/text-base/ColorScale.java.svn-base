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

package visualizationbasics.color;

import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class ColorScale {

    public Color getColor(float value) {
        if (reverse) {
            int minindex = (int) ((colors.length - 1) * (1 - min));
            int maxindex = (int) ((colors.length - 1) * (1 - max));

            int index = (int) (Math.abs(minindex - maxindex) * (1 - value)) + maxindex;
            return colors[index];
        } else {
            int minindex = (int) ((colors.length - 1) * min);
            int maxindex = (int) ((colors.length - 1) * max);
            int index = (int) (Math.round(Math.abs(maxindex - minindex) * value)) + minindex;
            return colors[index];
        }
    }

    public int getNumberColors() {
        return colors.length;
    }

    public float getMin() {
        return min;
    }

    public void setMinMax(float min, float max) throws IOException {
        if (max >= min) {
            this.max = max;
            this.min = min;
        } else {
            throw new IOException("The min value of color should be smaller " +
                    "than max value");
        }
    }

    public float getMax() {
        return max;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    private float min = 0.0f;
    private float max = 1.0f;
    private boolean reverse = false;
    protected Color[] colors;
}
