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

package topics.covariance;

import topics.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import matrix.AbstractMatrix;
import textprocessing.processing.Ngram;
import textprocessing.processing.Preprocessor;
import textprocessing.processing.stemmer.StemmerFactory.StemmerType;
import topics.model.TopicProjectionInstance;
import topics.model.TopicProjectionModel;
import topics.util.TopicConstants;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class CovarianceTopic extends AbstractTopicCreator {

    public CovarianceTopic(TopicProjectionModel model) {
        super(model);
        this.percterms = 0.5f;
        this.perctopics = 0.75f;
    }

    @Override
    public Topic createTopic(ArrayList<TopicProjectionInstance> instances) throws IOException {
        int lowercut = 2, uppercut = -1, ngrams = 1;

        if (instances.size() > 50 && instances.size() < 100) {
            lowercut = 10;
        } else if (instances.size() > 100 && instances.size() < 300) {
            lowercut = 15;
        } else if (instances.size() > 300) {
            lowercut = 20;
        }

        Preprocessor pp = new Preprocessor(model.getCorpus());
        AbstractMatrix matrix = pp.getMatrixSelected(lowercut, uppercut, ngrams,
                StemmerType.NONE, new ArrayList<AbstractInstance>(instances));
        ArrayList<Ngram> cpNgrams = pp.getNgrams();

        //creating the string boxes
        ArrayList<StringBox> boxes = new ArrayList<StringBox>();

        if (matrix.getRowCount() > 0 && matrix.getDimensions() > 0) {
            ArrayList<String> attributes = new ArrayList<String>();
            float[][] points = cutDimensions(matrix, cpNgrams, attributes);
            createTopic(points, attributes, instances, boxes);
        }

        return new Topic(model, instances, boxes);
    }

    /**
     * @return the percterms
     */
    public float getPercentageTerms() {
        return percterms;
    }

    /**
     * @param percterms the percterms to set
     */
    public void setPercentageTerms(float percterms) {
        this.percterms = percterms;
    }

    /**
     * @return the perctopics
     */
    public float getPercentageTopics() {
        return perctopics;
    }

    /**
     * @param perctopics the perctopics to set
     */
    public void setPercentageTopics(float perctopics) {
        this.perctopics = perctopics;
    }

    private void createTopic(float[][] points, ArrayList<String> attributes,
            ArrayList<TopicProjectionInstance> instances,
            ArrayList<StringBox> boxes) throws IOException {
        //Extracting the mean of the columns
        float[] mean = new float[points[0].length];
        Arrays.fill(mean, 0.0f);

        for (int i = 0; i < points.length; i++) {
            //calculating
            for (int j = 0; j < points[i].length; j++) {
                mean[j] += points[i][j];
            }
        }

        for (int i = 0; i < mean.length; i++) {
            mean[i] /= points.length;
        }

        //extracting the mean
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j] -= mean[j];
            }
        }

        firstTopic = true;
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        StringBox box = createStringBoxes(points, attributes, indexes);

        if (box != null) {
            boxes.add(box);

            for (int i = 0; i < 10; i++) {
                StringBox box_aux = createStringBoxes(points, attributes, indexes);

                if (box_aux != null) {
                    boxes.add(box_aux);
                } else {
                    break;
                }
            }

            //returning the mean
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[i].length; j++) {
                    points[i][j] += mean[j];
                }
            }

            colorVertex(points, instances, indexes);
        }
    }

    private StringBox createStringBoxes(float[][] points,
            ArrayList<String> attributes, ArrayList<Integer> indexes) {

        //Get the two attributes with largest covariance
        float gcov1 = Float.MIN_VALUE;
        int icov = 0;
        int jcov = 0;
        for (int i = 0; i < points[0].length - 1; i++) {
            for (int j = points[0].length - 1; j > i; j--) {
                if (!indexes.contains(i) && !indexes.contains(j)) {
                    float aux = covariance(points, i, j);
                    if (gcov1 < aux) {
                        gcov1 = aux;
                        icov = i;
                        jcov = j;
                    }
                }
            }
        }

        indexes.add(icov);
        indexes.add(jcov);

        if (attributes.size() > 0) {
            String msg = "(" + attributes.get(icov).replaceAll("<>", "") + "," +
                    attributes.get(jcov).replaceAll("<>", "") + ",";

            for (int i = 0; i < points[0].length - 1; i++) {
                if (!indexes.contains(i)) {
                    float aux = (covariance(points, icov, i) +
                            covariance(points, jcov, i)) / 2;

                    if (aux / gcov1 > percterms) {
                        msg += attributes.get(i).replaceAll("<>", "") + ",";
                        indexes.add(i);
                    }
                }
            }

            msg = msg.substring(0, msg.length() - 1) + ")[";

            NumberFormat form = NumberFormat.getInstance();
            form.setMaximumFractionDigits(2);
            form.setMinimumFractionDigits(2);

            msg += form.format(gcov1).replaceAll(",", ".") + "]";

            if (firstTopic) {
                maxcov = gcov1;
                firstTopic = false;
                return new StringBox(msg);
            } else if (gcov1 > maxcov * perctopics) {
                return new StringBox(msg);
            }
        }

        return null;
    }

    private float[][] cutDimensions(AbstractMatrix matrix, ArrayList<Ngram> cpNgrams,
            ArrayList<String> indexGrams) {
        //keep on the new points matrix no more than 200 dimensions
        float[][] newpoints = new float[matrix.getRowCount()][];

        for (int i = 0; i < newpoints.length; i++) {
            newpoints[i] = new float[(matrix.getDimensions() < 200) ? matrix.getDimensions() : 200];
            float[] point = matrix.getRow(i).toArray();
            for (int j = 0; j < newpoints[i].length; j++) {
                newpoints[i][j] = point[j];
            }
        }

        indexGrams.clear();
        for (int i = 0; i < newpoints[0].length; i++) {
            indexGrams.add(cpNgrams.get(i).ngram);
        }

        return newpoints;
    }

    //calculate the covariance between columns a and b
    private float covariance(float[][] points, int a, int b) {
        float covariance = 0.0f;
        for (int i = 0; i < points.length; i++) {
            covariance += points[i][a] * points[i][b];
        }

        covariance /= points.length;
        return covariance;
    }

    private void colorVertex(float[][] points, ArrayList<TopicProjectionInstance> instances,
            ArrayList<Integer> indexes) throws IOException {

        scalar = model.addScalar(TopicConstants.TOPICS);

        for (int i = 0; i < instances.size(); i++) {
            float value = 1.0f;

            for (int j = 0; j < indexes.size(); j++) {
                value *= (points[i][indexes.get(j)] > 0.0f) ? 1.0f : 0.0f;
            }

            instances.get(i).setScalarValue(scalar, value);
        }
    }

    private boolean firstTopic;
    private float maxcov;
    private float percterms;
    private float perctopics;
}

