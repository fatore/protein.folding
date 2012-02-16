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
 * of the original code is Ana Maria Cuadros Valdivia,
 * Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package graph.technique.connection.nj;

import java.io.IOException;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import graph.model.Connectivity;
import graph.model.Edge;
import graph.technique.connection.GraphConnection;
import graph.util.GraphConstants;
import java.util.ArrayList;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class NJConnection extends GraphConnection {

    public Connectivity execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        return execute(dmat);
    }

    public Connectivity execute(DistanceMatrix dmat) throws IOException {
        int numberPoints = dmat.getElementCount();

        this.neighbor = new NeighborJoining(numberPoints);
        this.neighbor.doNeighbor(dmat);
        
        //generating the connectivity
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < this.neighbor.get_size(); i++) {
            float dis = this.neighbor.get_dis_are(i);
            int sour = this.neighbor.get_source_are(i);
            int targ = this.neighbor.get_target_are(i);
            edges.add(new Edge(sour, targ, dis));
        }

        Connectivity conn = new Connectivity(GraphConstants.NJ, edges);
        return conn;
    }

    private NeighborJoining neighbor;
}
