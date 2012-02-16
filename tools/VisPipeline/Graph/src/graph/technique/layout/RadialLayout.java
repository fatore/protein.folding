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

package graph.technique.layout;

import graph.model.Connectivity;
import graph.model.Edge;
import java.util.ArrayList;
import java.util.HashSet;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class RadialLayout implements GraphLayout {

    public AbstractMatrix execute(AbstractMatrix matrix, Connectivity conn) {
        //initializing the tree
        init(conn);

        //creating the layout
        postorderTraversal(root);

        root.x = 0.0f;
        root.y = 0.0f;
        root.w = (float) (2 * Math.PI);
        root.t = 0;

        preorderTraversal(root);

        //creating the placement
        DenseMatrix position = new DenseMatrix();

        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            float[] vect = new float[2];
            vect[0] = nodes.get(i).x;
            vect[1] = nodes.get(i).y;

            System.out.println(vect[0] + " - " + vect[1]);

            if (i < matrix.getRowCount()) {
                AbstractVector row = matrix.getRow(i);
                DenseVector vector = new DenseVector(vect, row.getId(), row.getKlass());
                position.addRow(vector);
            } else {
                DenseVector vector = new DenseVector(vect, -1, -1);
                position.addRow(vector);
            }
        }

        return position;
    }

    private void init(Connectivity conn) {
        //discoverying the number of instances
        HashSet<Integer> aux_ins = new HashSet<Integer>();
        ArrayList<Edge> edges = conn.getEdges();

        int size = edges.size();
        for (int i = 0; i < size; i++) {
            Edge edge = edges.get(i);
            aux_ins.add(edge.getSource());
            aux_ins.add(edge.getTarget());
        }

        int nrinstances = aux_ins.size();

        //creating the nodes
        nodes = new ArrayList<RadialLayout.Node>();
        for (int i = 0; i < nrinstances; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }

        //setting the first node as the root
        root = nodes.get(0);

        for (int i = 0; i < size; i++) {
            Edge edge = edges.get(i);

            RadialLayout.Node parent = nodes.get(edge.getSource());
            RadialLayout.Node ch = nodes.get(edge.getTarget());
            ch.parent = parent;
            ch.dist = edge.getWeight();
            parent.children.add(ch);
        }
    }

    private void postorderTraversal(RadialLayout.Node v) {
        if (v.children.size() == 0) {
            v.l = 1;
        } else {
            v.l = 0;

            for (RadialLayout.Node w : v.children) {
                postorderTraversal(w);
                v.l = v.l + w.l;
            }
        }
    }

    private void preorderTraversal(RadialLayout.Node v) {
        if (v != root) {
            Node u = v.parent;
            v.x = u.x + v.dist * (float) Math.cos(v.t + v.w / 2);
            v.y = u.y + v.dist * (float) Math.sin(v.t + v.w / 2);
        }

        float n = v.t;

        for (RadialLayout.Node w : v.children) {
            w.w = w.l / root.l * (float) (2 * Math.PI);
            w.t = n;
            n = n + w.w;
            preorderTraversal(w);
        }
    }

    public class Node {

        public Node(int id) {
            this.id = id;
        }

        public int id;
        public float x;
        public float y;
        public float w;
        public float t;
        public float l;
        public Node parent;
        public float dist; //distance to parent
        public ArrayList<Node> children = new ArrayList<Node>();
    }

    private ArrayList<RadialLayout.Node> nodes;
    private RadialLayout.Node root;
}
