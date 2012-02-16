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

package projection.technique.isomap;

import java.io.IOException;
import distance.DistanceMatrix;
import projection.technique.Projection;
import projection.util.MeshGenerator;
import projection.technique.mds.ClassicalMDSProjection;
import datamining.neighbors.KNN;
import datamining.neighbors.Pair;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.reader.MatrixReaderComp;
import projection.model.ProjectionModelComp;
import projection.util.ConnectedGraphGenerator;
import projection.util.ProjectionUtil;
import projection.view.ProjectionFrameComp;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ISOMAPProjection implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        //the new distance matrix
        DistanceMatrix new_dmat = new DistanceMatrix(matrix.getRowCount());
        new_dmat.setIds(matrix.getIds());
        new_dmat.setClassData(matrix.getClassData());

        //creating a graph with its nearest neighbors
        KNN knn = new KNN(nrneighbors);
        Pair[][] neighborhood = knn.execute(matrix, diss);

        //assuring the connectivity (????)
        ConnectedGraphGenerator cgg = new ConnectedGraphGenerator();
        cgg.execute(neighborhood, matrix, diss);

        Dijkstra d = new Dijkstra(neighborhood);

        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] dist = d.execute(i);

            for (int j = 0; j < dist.length; j++) {
                new_dmat.setDistance(i, j, dist[j]);

                if (dist[j] == Float.POSITIVE_INFINITY) {
                    System.out.println("Error.... ISOMAP");
                    return new DenseMatrix();
                }
            }
        }

        //projecting using the classical scaling
        ClassicalMDSProjection cmds = new ClassicalMDSProjection();
        return cmds.project(new_dmat);
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        //the new distance matrix
        DistanceMatrix new_dmat = new DistanceMatrix(dmat.getElementCount());
        new_dmat.setIds(dmat.getIds());
        new_dmat.setLabels(dmat.getLabels());
        new_dmat.setClassData(dmat.getClassData());

        //creating a graph with its nearest neighbors
        KNN knn = new KNN(nrneighbors);
        Pair[][] neighborhood = knn.execute(dmat);

        //assuring the connectivity (????)
        MeshGenerator meshgen = new MeshGenerator();
        neighborhood = meshgen.execute(neighborhood, dmat);

        Dijkstra d = new Dijkstra(neighborhood);

        for (int i = 0; i < dmat.getElementCount(); i++) {
            float[] dist = d.execute(i);

            for (int j = 0; j < dist.length; j++) {
                new_dmat.setDistance(i, j, dist[j]);
            }
        }

        //projecting using the classical scaling
        ClassicalMDSProjection cmds = new ClassicalMDSProjection();
        return cmds.project(new_dmat);
    }

    /**
     * @return the nrneighbors
     */
    public int getNumberNeighbors() {
        return nrneighbors;
    }

    /**
     * @param nrneighbors the nrneighbors to set
     */
    public void setNumberNeighbors(int nrneighbors) {
        this.nrneighbors = nrneighbors;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("ERROR! It should be: ISOMAPProjection filename.data");
            System.exit(1);
        }

        ProjectionUtil.log(false, false);

        System.out.println("========================");
        System.out.println("ISOMAP - processing: " + args[0]);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(args[0]);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        long start = System.currentTimeMillis();
        ISOMAPProjectionComp isomap = new ISOMAPProjectionComp();
        isomap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        if (matrix.getRowCount() < 1500) {
            isomap.setNumberNeighbors(10);
        } else if (matrix.getRowCount() < 5000) {
            isomap.setNumberNeighbors(15);
        } else {
            isomap.setNumberNeighbors(20);
        }
        isomap.input(matrix);
        isomap.execute();
        AbstractMatrix projection = isomap.output();
        long finish = System.currentTimeMillis();
        System.out.println("ISOMAP projection time: " + (finish - start) / 1000.0f + "s");

        projection.save(args[0] + "-isomap.prj");

        ProjectionModelComp model = new ProjectionModelComp();
        model.input(projection);
        model.execute();

        ProjectionFrameComp frame = new ProjectionFrameComp();
        frame.input(model.output());
        frame.execute();

        System.out.println("ISOMAP - end");
        System.out.println("========================");
    }

    private int nrneighbors = 10;
}
