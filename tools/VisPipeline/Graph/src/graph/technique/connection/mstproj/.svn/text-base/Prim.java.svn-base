/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx), based on the code presented 
 * in:
 * 
 * http://snippets.dzone.com/user/scvalex/tag/prim
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

package graph.technique.connection.mstproj;

import datamining.neighbors.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import distance.DistanceMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Prim {

    public Prim(DistanceMatrix dmat) {
        this.nrnodes = dmat.getElementCount();
        this.nredges = dmat.getElementCount() * dmat.getElementCount() - dmat.getElementCount();

        this.firstEdge = new int[nrnodes];
        this.edgeTab = new Edge[2 * nredges];

        // Initially, all adjacency lists are empty
        for (int i = 0; i < 2 * nredges; i++) {
            edgeTab[i] = new Edge(-1, -1, 0.0f);
        }

        // read edges, each with a len
        for (int i = 0; i < nrnodes; i++) {
            firstEdge[i] = (-1);
        }

        int workingEdges = 0;

        for (int i = 0; i < this.nrnodes; i++) {
            for (int j = 0; j < this.nrnodes; j++) {
                if (i == j) {
                    continue;
                }
                edgeTab[workingEdges].node1 = i;
                edgeTab[workingEdges].node2 = j;
                edgeTab[workingEdges].len = dmat.getDistance(i, j);
                edgeTab[workingEdges].next = firstEdge[i];
                firstEdge[i] = workingEdges;
                workingEdges++;

                // Save inverse of input Edge and insert in adjacency list
                edgeTab[workingEdges].node1 = j;
                edgeTab[workingEdges].node2 = i;
                edgeTab[workingEdges].len = dmat.getDistance(i, j);
                edgeTab[workingEdges].next = firstEdge[j];
                firstEdge[j] = workingEdges;
                workingEdges++;
            }
        }
    }

    public Prim(Pair[][] neighbors) {
        this.nrnodes = neighbors.length;
        this.nredges = 0;

        for (int i = 0; i < neighbors.length; i++) {
            this.nredges += neighbors[i].length;
        }

        this.firstEdge = new int[nrnodes];
        this.edgeTab = new Edge[2 * nredges];

        // Initially, all adjacency lists are empty
        for (int i = 0; i < 2 * nredges; i++) {
            edgeTab[i] = new Edge(-1, -1, 0.0f);
        }

        // read edges, each with a len
        for (int i = 0; i < nrnodes; i++) {
            firstEdge[i] = (-1);
        }

        int workingEdges = 0;

        for (int i = 0; i < neighbors.length; i++) {
            for (int j = 0; j < neighbors[i].length; j++) {
                edgeTab[workingEdges].node1 = i;
                edgeTab[workingEdges].node2 = neighbors[i][j].index;
                edgeTab[workingEdges].len = neighbors[i][j].value;
                edgeTab[workingEdges].next = firstEdge[i];
                firstEdge[i] = workingEdges;
                workingEdges++;

                // Save inverse of input Edge and insert in adjacency list
                edgeTab[workingEdges].node1 = neighbors[i][j].index;
                edgeTab[workingEdges].node2 = i;
                edgeTab[workingEdges].len = neighbors[i][j].value;
                edgeTab[workingEdges].next = firstEdge[neighbors[i][j].index];
                firstEdge[neighbors[i][j].index] = workingEdges;
                workingEdges++;
            }
        }
    }

    protected Prim() {
    }

    public ArrayList<Prim.Edge> execute() throws IOException {
        long start = System.currentTimeMillis();

        ArrayList<Prim.Edge> newEdgeMap = new ArrayList<Prim.Edge>();

        st[] flag = new st[nrnodes];
        MinHeap.HeapEntry smallest; // reference is returned from MinHeap
        MinHeap.HeapEntry changing = new MinHeap.HeapEntry();  // gets passed to MinHeap
        float[] priority = new float[nrnodes];
        int[] bestSneighbor = new int[nrnodes];

        // Vertex 0 starts in S.  Other vertices start in T.
        flag[0] = st.s;
        // Some T vertices might not have an Edge from vertex 0.
        for (int i = 1; i < nrnodes; i++) {
            flag[i] = st.t;
        }

        priority[0] = 0;  // Dummy entry for vertex 0
        // Initialize T-heap with edges from vertex 0.
        for (int i = 1; i < nrnodes; i++) {
            priority[i] = Integer.MAX_VALUE;
        }

        // Constructor does BuildMinHeap
        for (int i = firstEdge[0]; i != (-1); i = edgeTab[i].next) {
            priority[edgeTab[i].node2] = edgeTab[i].len;
            bestSneighbor[edgeTab[i].node2] = 0;
        }

        MinHeap heap = new MinHeap(nrnodes, priority, nrnodes);
        priority = null;  // No longer needed

        // Remove dummy entry from heap
        smallest = heap.extractMin();
        if (smallest.id != 0) {
            // Each iteration finds another MST Edge
            throw new IOException("Heap problem");
        }

        for (int MSTedgeCount = 0; MSTedgeCount < nrnodes - 1; MSTedgeCount++) {
            // Get minimum len Edge from S to T.
            smallest = heap.extractMin();

            // no edges remain that bridge S & T
            if (smallest.priority == Integer.MAX_VALUE) {
                break;
            }

            newEdgeMap.add(new Prim.Edge(bestSneighbor[smallest.id],
                    smallest.id, smallest.priority));

            flag[smallest.id] = st.s;

            // Scan adjacency list looking for improvements
            for (int j = firstEdge[smallest.id]; j != (-1); j = edgeTab[j].next) {
                if (flag[edgeTab[j].node2] == st.t && edgeTab[j].len < heap.getPriority(edgeTab[j].node2)) {
                    changing.id = edgeTab[j].node2;
                    changing.priority = edgeTab[j].len;
                    heap.changePriority(changing);
                    bestSneighbor[edgeTab[j].node2] = smallest.id;
                }
            }
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Prim (minimum Spanning Tree) time: " + (finish - start) / 1000.0f + "s");

        return newEdgeMap;
    }

    public static class Edge {

        public Edge(int node1, int node2, float len) {
            this.node1 = node1;
            this.node2 = node2;
            this.len = len;
        }

        public int node1;
        public int node2;
        public float len;
        public int next;  // subscript of next Edge with same node1 
    }

    private enum st {

        s, t
    };

    protected int nrnodes;
    protected int nredges;
    protected Edge[] edgeTab;
    protected int[] firstEdge;  // Table indicating first Edge for a node1
}
