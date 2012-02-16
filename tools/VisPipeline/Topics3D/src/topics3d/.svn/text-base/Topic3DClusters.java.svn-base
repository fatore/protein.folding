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

package topics3d;

import datamining.clustering.BKmeans;
import datamining.clustering.Jdbscan2D;
import distance.dissimilarity.Euclidean;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.model.Scalar;
import projection3d.model.Projection3DInstance;
import projection3d.surface.SurfaceFactory;
import projection3d.surface.SurfaceFactory.SurfaceType;
import topics.util.TopicConstants;
import topics3d.TopicFactory.TopicType;
import topics3d.model.TopicProjection3DInstance;
import topics3d.model.TopicProjection3DModel;
import topics3d.util.OpenDialog;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkSurfaceActor;

/**
 *
 * @author Fernando Vieira Paulovich, Roberto Pinho
 */
public class Topic3DClusters {

    public enum ClusteringType {

        KMEANS, DBSCAN
    }

    public void execute(TopicProjection3DModel model, ClusteringType clusttype,
            TopicType topictype, Component parent) throws IOException {
        //Create the clusters
        AbstractMatrix matrix = new DenseMatrix();
        ArrayList<AbstractInstance> instances = model.getInstances();
        for (int i = 0; i < instances.size(); i++) {
            Projection3DInstance pi = (Projection3DInstance) instances.get(i);

            float[] point = new float[3];
            point[0] = pi.getX();
            point[1] = pi.getY();
            point[2] = pi.getZ();
            matrix.addRow(new DenseVector(point));
        }

        ArrayList<ArrayList<Integer>> clusters = null;

        if (clusttype == ClusteringType.KMEANS) {
            String inputValue = (String) JOptionPane.showInputDialog(null,
                    "Choose the number of clusters:", "Defining the Number of Clusters",
                    JOptionPane.QUESTION_MESSAGE, null, null,
                    (Object) Integer.toString((int) Math.sqrt(instances.size())));

            if (inputValue == null) {
                return;
            }

            int nclusters = Integer.parseInt(inputValue);
            BKmeans km = new BKmeans(nclusters);
            clusters = km.execute(new Euclidean(), matrix);
        } else {
            Jdbscan2D dbscan = new Jdbscan2D();
            clusters = dbscan.execute(new Euclidean(), matrix);
        }

        scalar = model.addScalar(TopicConstants.TOPICS_CLUSTERS);

        for (int c = 0; c < clusters.size(); c++) {
            for (int v = 0; v < clusters.get(c).size(); v++) {
                ((Projection3DInstance) instances.get(clusters.get(c).get(v))).setScalarValue(scalar, c);
            }
        }

        if (OpenDialog.checkCorpus(model, parent)) {
            AbstractTopic3DCreator topic = TopicFactory.getInstance(model, topictype);

            Object[] options = {"Convex Hull", "Density Surface"};
            int  surfaceType = JOptionPane.showOptionDialog(null, "Which method do you want?",
                        "Defining Method", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

            //for each cluster
            for (int c = 0; c < clusters.size(); c++) {
                ArrayList<TopicProjection3DInstance> aux_ins = new ArrayList<TopicProjection3DInstance>();

                for (int v = 0; v < clusters.get(c).size(); v++) {
                    aux_ins.add((TopicProjection3DInstance) instances.get(clusters.get(c).get(v)));
                }

                Topic3D topicrender = topic.createTopic(aux_ins);

                vtkSurfaceActor actor =null;
                if (surfaceType==0) {
                    actor = SurfaceFactory.getInstance(SurfaceType.CONVEX_HULL, aux_ins, scalar.getName());
                } else {
                    actor = SurfaceFactory.getInstance(SurfaceType.DENSITY, aux_ins, scalar.getName());
                }
                Color cc = model.getColorTable().getColor(aux_ins.get(0).getNormalizedScalarValue(scalar));
                actor.GetProperty().SetColor(cc.getRed()/255., cc.getGreen()/255., cc.getBlue()/255.);
                topicrender.setActor(actor);

                model.addTopic(topicrender);
            }
        }
    }

    public Scalar getScalar() {
        return scalar;
    }

    private Scalar scalar;
}
