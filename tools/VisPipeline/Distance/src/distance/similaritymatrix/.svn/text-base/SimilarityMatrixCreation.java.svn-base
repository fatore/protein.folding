/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance.similaritymatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SimilarityMatrixCreation {

    public SimilarityMatrixModel execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);
        return execute(dmat);
    }

    public SimilarityMatrixModel execute(DistanceMatrix dmat) throws IOException {
        SimilarityMatrixModel model = new SimilarityMatrixModel();

        int[] index = createIndex(dmat);

        for (int i = 0; i < dmat.getElementCount(); i++) {
            SimilarityMatrixInstance smi = new SimilarityMatrixInstance(model, dmat.getLabels().get(index[i]),
                    dmat.getIds().get(index[i]), i, dmat.getClassData()[index[i]]);

            float[] distances = new float[dmat.getElementCount()];

            for (int j = 0; j < distances.length; j++) {
                distances[j] = dmat.getDistance(index[i], index[j]);
            }

            smi.setDistancesToInstances(distances);
        }

        return model;
    }

    private int[] createIndex(DistanceMatrix dmat) {
        int[] index_aux = new int[dmat.getElementCount()];

        //getting the classes
        float[] cdata = dmat.getClassData();
        ArrayList<Float> classes = new ArrayList<Float>();

        for (int i = 0; i < cdata.length; i++) {
            if (!classes.contains(cdata[i])) {
                classes.add(cdata[i]);
            }
        }

        Collections.sort(classes);

        int n = 0;
        int ini = 0;

        for (int i = 0; i < classes.size(); i++) {
            float klass = classes.get(i);

            for (int j = 0; j < cdata.length; j++) {
                if (cdata[j] == klass) {
                    index_aux[n] = j;
                    n++;
                }
            }

            Pair[] indexes = new Pair[n - ini];
            int pivot = 0;
            float max = Float.NEGATIVE_INFINITY;

            for (int j = 0; j < indexes.length; j++) {
                for (int k = indexes.length - 1; k >= 0; k--) {
                    float distance = dmat.getDistance(index_aux[j + ini],
                            index_aux[k + ini]);

                    if (max < distance) {
                        pivot = j + ini;
                        max = distance;
                    }
                }
            }

            for (int j = 0; j < indexes.length; j++) {
                indexes[j] = new Pair(index_aux[j + ini],
                        dmat.getDistance(index_aux[pivot], index_aux[j + ini]));
            }

            Arrays.sort(indexes);

            for (int j = 0; j < indexes.length; j++) {
                index_aux[j + ini] = indexes[j].index;
            }

            ini = n;
        }

        //oder the classes to put on the right sequence
        ArrayList<Order> order_aux = new ArrayList<Order>();

        Order ord1 = new Order();
        ord1.begin = 0;
        ord1.end = 0;
        ord1.value = 0.0f;
        order_aux.add(ord1);

        for (int j = ord1.begin + 1; j < index_aux.length; j++) {
            if (cdata[index_aux[ord1.begin]] != cdata[index_aux[j]]) {
                ord1.end = j - 1;
                break;
            }
        }

        Order prev_aux = ord1;

        while (prev_aux.end < index_aux.length - 1) {
            Order new_ord = new Order();
            new_ord.begin = prev_aux.end + 1;
            new_ord.end = prev_aux.end + 1;
            new_ord.value = 0.0f;
            order_aux.add(new_ord);

            for (int j = new_ord.begin + 1; j < index_aux.length; j++) {
                if (cdata[index_aux[new_ord.begin]] != cdata[index_aux[j]]
                        || j == index_aux.length - 1) {
                    new_ord.end = j - 1;

                    if (j == index_aux.length - 1) {
                        new_ord.end = j;
                    }

                    new_ord.value = dmat.getDistance(index_aux[ord1.end],
                            index_aux[new_ord.begin]);

                    break;
                }
            }

            prev_aux = new_ord;
        }

        Collections.sort(order_aux);

        int[] index = new int[index_aux.length];
        int k = 0;

        for (int i = 0; i < order_aux.size(); i++) {
            Order ord2 = order_aux.get(i);

            for (int j = ord2.begin; j <= ord2.end; j++, k++) {
                index[k] = index_aux[j];
            }
        }

        return index;
    }

    class Order implements Comparable {

        @Override
        public int compareTo(Object o) {
            if (o instanceof Order) {
                if (Math.abs(this.value - ((Order) o).value) == EPSILON) {
                    return 0;
                } else if (this.value - ((Order) o).value > EPSILON) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public static final float EPSILON = 0.00001f;
        public int begin;
        public int end;
        public float value;
    }

    class Pair implements Comparable {

        public Pair(int index, float value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Pair) {
                if (this.value - ((Pair) o).value == EPSILON) {
                    return 0;
                } else if (this.value - ((Pair) o).value > EPSILON) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public static final float EPSILON = 0.00001f;
        public int index;
        public float value;
    }

}
