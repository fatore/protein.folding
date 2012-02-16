/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plmp.technique;

import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.util.MatrixUtils;

/**
 *
 * @author PC
 */
public class BKmeans {

    public BKmeans(int nrclusters) {
        this.nrclusters = nrclusters;
    }

    public ArrayList<ArrayList<Integer>> execute(AbstractDissimilarity diss, AbstractMatrix matrix) throws IOException {
        try {
            long start = System.currentTimeMillis();
            this.diss = diss;
            this.clusters = new ArrayList<ArrayList<Integer>>();
            this.centroids = MatrixFactory.getInstance(matrix.getClass());

            //initially the gCluster has all elements
            ArrayList<Integer> gCluster = new ArrayList<Integer>();
            int size1 = matrix.getRowCount();
            for (int i = 0; i < size1; i++) {
                gCluster.add(i);
            }

            this.clusters.add(gCluster);

            //considering just one element as the centroid
            this.centroids.addRow((AbstractVector) matrix.getRow(0).clone());

            for (int j = 0; j < this.nrclusters - 1; j++) {
                //Search the cluster with the bigger number of elements
                gCluster = this.getClusterToSplit(this.clusters);

                //split the greatest cluster into two clusters
                if (gCluster.size() > 1) {
                    this.splitCluster(matrix, diss, gCluster);
                }
            }

            //removing possible empty clusters
            for (int i = this.clusters.size() - 1; i >= 0; i--) {
                if (this.clusters.get(i).size() <= 0) {
                    this.clusters.remove(i);

                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
                            "The Bissection k-means algorithm is returning an "
                            + "empty cluster. Number of requested clusters : " + this.nrclusters);
                }
            }

            int removed = 0;

            for (int i = this.clusters.size() - 1; i >= 0; i--) {
                if (this.clusters.get(i).size() == 0) {
                    this.clusters.remove(i);
                    this.centroids.removeRow(i);
                    removed++;
                }
            }

            if (removed > 0) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
                        "The Bissection k-means algorithm is returning "
                        + "empty clusters. Removed: " + removed);
            }

            long finish = System.currentTimeMillis();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Bisecting K-Means time: " + (finish - start) / 1000.0f + "s");

            String tmp = "Clusters sizes: ";
            for (int i = 0; i < this.clusters.size(); i++) {
                tmp += this.clusters.get(i).size() + " ";
            }

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, tmp.trim());


        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BKmeans.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.clusters;
    }

    public AbstractMatrix getCentroids() {
        return this.centroids;
    }

    public int[] getMedoids(AbstractMatrix matrix) throws IOException {
        int[] m = new int[this.centroids.getRowCount()];

        for (int i = 0; i < m.length; i++) {
            int point = -1;
            float distance = Float.MAX_VALUE;

            for (int j = 0; j < this.clusters.get(i).size(); j++) {
                float distance2 = this.diss.calculate(this.centroids.getRow(i),
                        matrix.getRow(this.clusters.get(i).get(j)));

                if (distance > distance2) {
                    point = this.clusters.get(i).get(j);
                    distance = distance2;
                }
            }

            m[i] = point;
        }

        return m;
    }

    protected ArrayList<Integer> getClusterToSplit(ArrayList<ArrayList<Integer>> clusters) {
        ArrayList<Integer> gCluster = clusters.get(0);

        for (int i = 0; i < clusters.size(); i++) {
            if (clusters.get(i).size() > gCluster.size()) {
                gCluster = clusters.get(i);
            }
        }

        return gCluster;
    }

    protected void splitCluster(AbstractMatrix matrix, AbstractDissimilarity diss, ArrayList<Integer> gCluster) throws IOException {
        try {
            this.centroids.removeRow(clusters.indexOf(gCluster));
            this.clusters.remove(gCluster);

            //getting the two pivots
            int[] pivots = this.getPivots(matrix, diss, gCluster);

            //Create two new clusters
            ArrayList<Integer> cluster_1 = new ArrayList<Integer>();
            cluster_1.add(pivots[0]);
            AbstractVector centroid_1 = (AbstractVector) matrix.getRow(pivots[0]).clone();

            ArrayList<Integer> cluster_2 = new ArrayList<Integer>();
            cluster_2.add(pivots[1]);
            AbstractVector centroid_2 = (AbstractVector) matrix.getRow(pivots[1]).clone();

            int iterations = 0;

            do {
                centroid_1 = this.calculateMean(matrix, cluster_1);
                centroid_2 = this.calculateMean(matrix, cluster_2);

                cluster_1.clear();
                cluster_2.clear();

                //For each cluster
                int size = gCluster.size();
                for (int i = 0; i < size; i++) {
                    float distCentr_1 = diss.calculate(matrix.getRow(gCluster.get(i)), centroid_1);
                    float distCentr_2 = diss.calculate(matrix.getRow(gCluster.get(i)), centroid_2);

                    if (distCentr_1 < distCentr_2) {
                        cluster_1.add(gCluster.get(i));
                    } else if (distCentr_2 < distCentr_1) {
                        cluster_2.add(gCluster.get(i));
                    } else {
                        if (cluster_1.size() > cluster_2.size()) {
                            cluster_2.add(gCluster.get(i));
                        } else {
                            cluster_1.add(gCluster.get(i));
                        }
                    }
                }

                if (cluster_1.size() < 1) {
                    cluster_1.add(cluster_2.get(0));
                    cluster_2.remove(cluster_2.get(0));
                } else if (cluster_2.size() < 1) {
                    cluster_2.add(cluster_1.get(0));
                    cluster_1.remove(cluster_1.get(0));
                }

            } while (++iterations < this.nrIterations);

            //Add the two new clusters
            this.clusters.add(cluster_1);
            this.clusters.add(cluster_2);

            //add the new centroids
            this.centroids.addRow(centroid_1);
            this.centroids.addRow(centroid_2);

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BKmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected int[] getPivots(AbstractMatrix matrix, AbstractDissimilarity diss, ArrayList<Integer> gCluster) throws IOException {
        int[] pivots = new int[2];

        //choosing the first pivot
        Random random = new Random(System.currentTimeMillis());
        AbstractVector fpivot = matrix.getRow(gCluster.get(random.nextInt(gCluster.size())));

        int pivot = 0;
        float maxdist = Float.NEGATIVE_INFINITY;

        for (int i = 0; i < gCluster.size(); i++) {
            float dist = diss.calculate(fpivot, matrix.getRow(gCluster.get(i)));

            if (dist > maxdist) {
                maxdist = dist;
                pivot = gCluster.get(i);
            }
        }

        pivots[0] = pivot;

        //choosing the second pivot
        fpivot = matrix.getRow(pivot);
        pivot = 0;
        maxdist = Float.NEGATIVE_INFINITY;

        for (int i = 0; i < gCluster.size(); i++) {
            float dist = diss.calculate(fpivot, matrix.getRow(gCluster.get(i)));

            if (dist > maxdist) {
                maxdist = dist;
                pivot = gCluster.get(i);
            }
        }

        pivots[1] = pivot;

        return pivots;
    }

    protected AbstractVector calculateMean(AbstractMatrix matrix, ArrayList<Integer> cluster) throws IOException {
        AbstractMatrix mean = MatrixFactory.getInstance(matrix.getClass());

        int size = cluster.size();
        for (int i = 0; i < size; i++) {
            mean.addRow(matrix.getRow(cluster.get(i)));
        }

        return MatrixUtils.mean(mean);
    }

    public class Pivot implements Comparable {

        public Pivot(float distance, int id) {
            this.distance = distance;
            this.id = id;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Pivot) {
                if (this.distance - ((Pivot) o).distance == EPSILON) {
                    return 0;
                } else if (this.distance - ((Pivot) o).distance > EPSILON) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        public float distance;
        public int id;
    }
    protected ArrayList<ArrayList<Integer>> clusters;
    protected AbstractMatrix centroids;
    protected AbstractDissimilarity diss;
    protected static final float EPSILON = 0.00001f;
    protected int nrIterations = 15;
    protected int nrclusters;
}
