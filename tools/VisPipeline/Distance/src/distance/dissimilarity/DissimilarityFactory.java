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
package distance.dissimilarity;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class DissimilarityFactory {

    public enum DissimilarityType {

        CITY_BLOCK, COSINE_BASED, EUCLIDEAN, EXTENDED_JACCARD, INFINITY_NORM,
        DYNAMIC_TIME_WARPING, MAX_MOVING_EUCLIDEAN, MIN_MOVING_EUCLIDEAN
    }

    public static AbstractDissimilarity getInstance(DissimilarityType type) {

        if (type.equals(DissimilarityType.CITY_BLOCK)) {
            return new CityBlock();
        } else if (type.equals(DissimilarityType.COSINE_BASED)) {
            return new CosineBased();
        } else if (type.equals(DissimilarityType.EUCLIDEAN)) {
            return new Euclidean();
        } else if (type.equals(DissimilarityType.EXTENDED_JACCARD)) {
            return new ExtendedJaccard();
        } else if (type.equals(DissimilarityType.INFINITY_NORM)) {
            return new InfinityNorm();
        } else if (type.equals(DissimilarityType.DYNAMIC_TIME_WARPING)) {
            return new DynamicTimeWarping();
        } else if (type.equals(DissimilarityType.MAX_MOVING_EUCLIDEAN)) {
            return new MaxMovingEuclidean();
        } else if (type.equals(DissimilarityType.MIN_MOVING_EUCLIDEAN)) {
            return new MinMovingEuclidean();
        }

        return null;
    }
}
