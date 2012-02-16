/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 *
 * @author Fernando
 */
public class LimitDistance {

    public DistanceMatrix execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        DistanceMatrix dmat = new DistanceMatrix(matrix, diss);

        float average = 0;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = 0; j < dmat.getElementCount(); j++) {
                average += dmat.getDistance(i, j);
            }
        }
        average /= dmat.getElementCount() * dmat.getElementCount();

        System.out.println("average: " + average);
        System.out.println("max: " + dmat.getMaxDistance());
        System.out.println("min: " + dmat.getMinDistance());

        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = 0; j < dmat.getElementCount(); j++) {
                if (dmat.getDistance(i, j) > average) {
                    dmat.setDistance(i, j, Math.max(average, dmat.getDistance(i, j)/3));
                }
            }
        }

        return dmat;
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/My Dropbox/artigos/Chu/PexSensor/dados/eletrodopuro-imag.data-CAPACITANCE.data";

        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        LimitDistance limit = new LimitDistance();
        limit.execute(matrix, new Euclidean()).save(filename + ".dmat");
    }

}
