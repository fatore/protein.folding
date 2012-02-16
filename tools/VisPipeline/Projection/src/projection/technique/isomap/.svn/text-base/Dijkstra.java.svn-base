/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx), based on the code presented 
 * in:
 * 
 * http://snippets.dzone.com/tag/dijkstra
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

import datamining.neighbors.Pair;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Dijkstra {

    public Dijkstra(Pair[][] neighborhood) {
        this.graph = new Dijkstra.Graph(neighborhood);
    }

    public float[] execute(int source) {
        if (source < graph.getNodes().length) {
            //get the graph nodes
            Node[] nodes = graph.getNodes();

            //initialize
            for (int i = 0; i < nodes.length; i++) {
                nodes[i].d = Float.POSITIVE_INFINITY;
            }
            nodes[source].d = 0;

            //create the priority queue
            Heap heap = new Heap(graph.getNodes());
            while (!heap.empty()) {
                Node u = heap.remove();

                //relax the adj. nodes to u
                for (int i = 0; i < u.edges.length; i++) {
                    Node v = nodes[u.edges[i].index];
                    float w = u.d + u.edges[i].value;

                    if (v.d > w) {
                        heap.decreaseKey(v, w);
                    }
                }
            }

            float[] s = new float[graph.getNodes().length];
            for (int i = 0; i < nodes.length; i++) {
                s[i] = nodes[i].d;
            }
            return s;
        }

        return null;
    }

    class Graph {

        public Graph(Pair[][] neighborhood) {
            complete(neighborhood);

            nodes = new Node[neighborhood.length];

            for (int i = 0; i < neighborhood.length; i++) {
                nodes[i] = new Node(i, neighborhood[i]);
            }
        }

        private void complete(Pair[][] neighbors) {
            //completing the neighborhood
            for (int i = 0; i < neighbors.length; i++) {
                for (int j = 0; j < neighbors[i].length; j++) {
                    boolean contain = false;

                    for (int k = 0; k < neighbors[neighbors[i][j].index].length; k++) {
                        if (neighbors[neighbors[i][j].index][k].index == i) {
                            contain = true;
                            break;
                        }
                    }

                    if (!contain) {
                        Pair[] newneighbors = new Pair[neighbors[neighbors[i][j].index].length + 1];

                        //copying the previous neighbors
                        for (int k = 0; k < newneighbors.length - 1; k++) {
                            newneighbors[k] = neighbors[neighbors[i][j].index][k];
                        }

                        //adding the new neighbor and setting the new neighborhood
                        newneighbors[newneighbors.length - 1] = new Pair(i, neighbors[i][j].value);
                        neighbors[neighbors[i][j].index] = newneighbors;
                    }
                }
            }
        }

        public Node getNode(int index) {
            return nodes[index];
        }

        public Node[] getNodes() {
            return nodes;
        }

        private Node nodes[];
    }

    class Heap {

        public Heap(Node nodes[]) {
            this.nodes = new Node[nodes.length];
            this.end = -1;

            for (int i = 0; i < nodes.length; i++) {
                insert(nodes[i]);
            }
        }

        public boolean empty() {
            return (end == -1);
        }

        public boolean full() {
            return (end == nodes.length - 1);
        }

        public boolean insert(Node node) {
            if (!full()) {
                end++;
                nodes[end] = node;
                nodes[end].heapindex = end;
                bubblingUp(end);
                return true;
            }

            return false;
        }

        public Node remove() {
            if (!empty()) {
                Node node = nodes[0];
                nodes[0] = nodes[end];
                nodes[0].heapindex = 0;
                end--;
                bubblingDown(0);
                return node;
            }

            return null;
        }

        public void increaseKey(Node node, float newd) {
            if (newd > node.d) {
                node.d = newd;
                bubblingDown(node.heapindex);
            }
        }

        public void decreaseKey(Node node, float newd) {
            if (newd < node.d) {
                node.d = newd;
                bubblingUp(node.heapindex);
            }
        }

        private void bubblingUp(int index) {
            int parent = (index - 1) / 2;

            while (index > 0 && nodes[index].d <= nodes[parent].d) {
                Node tmp = nodes[index];
                nodes[index] = nodes[parent];
                nodes[index].heapindex = index;
                nodes[parent] = tmp;
                nodes[parent].heapindex = parent;

                index = parent;
                parent = (parent - 1) / 2;
            }
        }

        private void bubblingDown(int index) {
            while (index < end / 2) {
                int leftchild = 2 * index + 1;
                int rightchild = 2 * index + 2;

                int largestchild;

                if (leftchild < end
                        && nodes[leftchild].d > nodes[rightchild].d) {
                    largestchild = rightchild;
                } else {
                    largestchild = leftchild;
                }

                if (nodes[index].d <= nodes[largestchild].d) {
                    break;
                }

                Node tmp = nodes[index];
                nodes[index] = nodes[largestchild];
                nodes[index].heapindex = index;
                nodes[largestchild] = tmp;
                nodes[largestchild].heapindex = largestchild;
                index = largestchild;
            }
        }

        private Node nodes[];
        private int end;
    }

    class Node {

        public Node(int id, Pair[] edges) {
            this.edges = edges;
            this.id = id;
            this.d = Float.POSITIVE_INFINITY;
            this.heapindex = -1;
        }

        public int id;
        public int heapindex;
        public float d;
        public Pair[] edges;
    }

    private Dijkstra.Graph graph;
}
