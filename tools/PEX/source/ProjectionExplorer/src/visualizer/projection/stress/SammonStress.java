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
package visualizer.projection.stress;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.graph.Graph;
import visualizer.matrix.DenseMatrix;
import visualizer.matrix.DenseVector;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.projection.distance.CosineBased;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.DistanceMatrix;
import visualizer.projection.distance.Euclidean;
import visualizer.projection.distance.LightWeightDistanceMatrix;
import visualizer.projection.plsp.PLSPProjection2D;
import visualizer.util.Util;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SammonStress extends Stress {

    @Override
    public float calculate(Matrix matrix, Dissimilarity diss, Matrix projection) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return this.calculate(dmat, projection);
    }

    @Override
    public float calculate(DistanceMatrix dmat, Matrix projection) throws IOException {
        LightWeightDistanceMatrix dmatprj = new LightWeightDistanceMatrix(projection, new Euclidean());

        float num = 0.0f;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                num += ((dmat.getDistance(i, j) - dmatprj.getDistance(i, j)) *
                        (dmat.getDistance(i, j) - dmatprj.getDistance(i, j))) /
                        (dmat.getDistance(i, j) * dmat.getDistance(i, j));
            }
        }

        float den = 0.0f;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                den += dmat.getDistance(i, j) * dmat.getDistance(i, j);
            }
        }

        return num / den;
    }

    @Override
    public float calculate(Matrix matrix, Dissimilarity diss, Graph graph) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return this.calculate(dmat, graph);
    }

    @Override
    public float calculate(DistanceMatrix dmat, Graph graph) throws IOException {
        DenseMatrix projection = new DenseMatrix();

        for (int i = 0; i < graph.getVertex().size(); i++) {
            float[] vect = new float[2];
            vect[0] = graph.getVertex().get(i).getX();
            vect[1] = graph.getVertex().get(i).getY();
            projection.addRow(new DenseVector(vect));
        }

        return this.calculate(dmat, projection);
    }

    public static void main(String[] args) {
        try {
            Util.log(true, false);

            if(args.length != 3){
                System.out.println("Usage: java SammonsStress <points> <projection> <dissimilarity>");
                System.out.println("   ex: java SammonsStress points.data projection.data cosine");
            }

            String pointsfilename = args[0];
            Matrix points = MatrixFactory.getInstance(pointsfilename);

            String projfilename = args[1];
            Matrix projection = MatrixFactory.getInstance(projfilename);

            Dissimilarity diss = null;

            if (args[2].trim().toLowerCase().equals("cosine")) {
                diss = new CosineBased();
            } else {
                diss = new Euclidean();
            }

            SammonStress stress = new SammonStress();
            float value = stress.calculate(points, diss, projection);

            System.out.println("---");
            System.out.println("Points: " + pointsfilename);
            System.out.println("Projection: " + projfilename);
            System.out.println("Sammon stress: " + value);
            System.out.println("---");

        } catch (IOException ex) {
            Logger.getLogger(PLSPProjection2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
