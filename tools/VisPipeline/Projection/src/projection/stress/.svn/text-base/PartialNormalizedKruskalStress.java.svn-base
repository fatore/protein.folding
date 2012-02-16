/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.stress;

import distance.DistanceMatrix;
import distance.LightWeightDistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando
 */
public class PartialNormalizedKruskalStress extends Stress {

    @Override
    public float calculate(AbstractMatrix projection, AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, diss);
        return calculate(projection, dmat);
    }

    @Override
    public float calculate(AbstractMatrix projection, DistanceMatrix dmat) throws IOException {
        LightWeightDistanceMatrix dmatprj = new LightWeightDistanceMatrix(projection, new Euclidean());

        float perc = 0.005f;
        long nrelements = (((long)dmatprj.getElementCount() * (long)dmatprj.getElementCount())
                - (long)dmatprj.getElementCount()) / 2;

        HashSet<Long> index_aux = new HashSet<Long>();
        while (index_aux.size() < perc * nrelements) {
            long value = (int) (Math.random() * nrelements);
            index_aux.add(value);
        }

        ArrayList<Long> index = new ArrayList<Long>(index_aux);
        Collections.sort(index);

        double maxrn = Double.NEGATIVE_INFINITY;
        double maxr2 = Double.NEGATIVE_INFINITY;

        long count = 0;
        for (int i = 0, r = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                if (r < index.size() && index.get(r) == count) {
                    r++;
                    double valuern = dmat.getDistance(i, j);
                    double valuer2 = dmatprj.getDistance(i, j);

                    if (valuern > maxrn) {
                        maxrn = valuern;
                    }

                    if (valuer2 > maxr2) {
                        maxr2 = valuer2;
                    }
                }

                count++;
            }
        }

        double num = 0.0f;
        double den = 0.0f;
        count = 0;
        for (int i = 0, r = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                if (r < index.size() && index.get(r) == count) {
                    r++;
                    double distrn = dmat.getDistance(i, j) / maxrn;
                    double distr2 = dmatprj.getDistance(i, j) / maxr2;
                    num += (distrn - distr2) * (distrn - distr2);
                    den += distrn * distrn;
                }

                count++;
            }
        }

        return (float) (num / den);
    }
}
