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

package projection.stress;

import distance.DistanceMatrix;
import distance.LightWeightDistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class KruskalStress extends Stress {

    @Override
    public float calculate(AbstractMatrix projection, AbstractMatrix matrix,
            AbstractDissimilarity diss) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return calculate(projection, dmat);
    }

    @Override
    public float calculate(AbstractMatrix projection, DistanceMatrix dmat) throws IOException {
        LightWeightDistanceMatrix dmatprj = new LightWeightDistanceMatrix(projection, new Euclidean());

        double num = 0.0f;
        double den = 0.0f;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                double distrn = dmat.getDistance(i, j);
                double distr2 = dmatprj.getDistance(i, j);
                num += (distrn - distr2) * (distrn - distr2);
                den += distrn * distrn;
            }
        }

        return (float)(num / den);
    }

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader1 = new MatrixReaderComp();
            reader1.setFilename("D:\\Dados\\sampling10d2e5.0099-normalized.data");
//            reader.setFilename("D:\\Dados\\sampling10d2e5.0099-std.data");
//            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Dados\\ssurface.data");
//            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Dados\\swissroll.data");
//            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Dados\\mammals-1000000.bin");
            reader1.execute();
            AbstractMatrix matrix = reader1.output();


            MatrixReaderComp reader2 = new MatrixReaderComp();
            reader2.setFilename("D:\\projection.data");
            reader2.execute();
            AbstractMatrix proj = reader2.output();

            KruskalStress stress = new KruskalStress();
            float value = stress.calculate(proj, matrix, new Euclidean());

            System.out.println("Stress: " + value);


        } catch (IOException ex) {
            Logger.getLogger(KruskalStress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
