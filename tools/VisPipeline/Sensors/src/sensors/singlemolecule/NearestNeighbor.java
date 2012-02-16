/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.singlemolecule;

import datamining.neighbors.Pair;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;

/**
 *
 * @author PC
 */
public class NearestNeighbor {

    //return the most similar spectra given a set of reference spectra, considering the
    //minimal distances to all reference spectra
    public static ArrayList<Integer> nn(AbstractMatrix matrix,
            AbstractDissimilarity diss, AbstractMatrix reference) throws IOException {
        ArrayList<Integer> index = new ArrayList<Integer>();

        Pair[] count = new Pair[matrix.getRowCount()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            count[i] = new Pair(i, Float.POSITIVE_INFINITY);

            AbstractVector row = matrix.getRow(i);
            for (int j = 0; j < reference.getRowCount(); j++) {
                float dist = diss.calculate(row, reference.getRow(j));
                count[i].value = Math.min(count[i].value, dist);
            }
        }

        Arrays.sort(count);

        for (int i = 0; i < count.length; i++) {
            index.add(count[i].index);
        }

        return index;
    }

    public static ArrayList<Float> nndistances(AbstractMatrix matrix, AbstractDissimilarity diss,
            AbstractMatrix reference) throws IOException {
        ArrayList<Float> dists = new ArrayList<Float>();

        Pair[] count = new Pair[matrix.getRowCount()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            count[i] = new Pair(i, Float.POSITIVE_INFINITY);

            AbstractVector row = matrix.getRow(i);
            for (int j = 0; j < reference.getRowCount(); j++) {
                float dist = diss.calculate(row, reference.getRow(j));
                count[i].value = Math.min(count[i].value, dist);
            }
        }

        float max = Float.NEGATIVE_INFINITY;

        for (int i = 0; i < reference.getRowCount(); i++) {
            float[] vector = reference.getRow(i).toArray();
            float value = 0.0f;

            for (int j = 0; j < vector.length; j++) {
                value += 4 * vector[j] * vector[j];
            }

            if (max < value) {
                max = value;
            }
        }

        for (int i = 0; i < count.length; i++) {
            count[i].value = Math.min(count[i].value / max, 1.0f);
        }

        for (int i = 0; i < count.length; i++) {
            dists.add(count[i].value);
        }

        return dists;
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/My Dropbox/artigos/Case-Prudente/single-molecule/data/DPPG-AM/single_molecule_novo_corrigido-clean.data";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        String reffilename = "D:/My Dropbox/artigos/Case-Prudente/single-molecule/data/DPPG-AM/reference-clean.data";
        AbstractMatrix refmatrix = MatrixFactory.getInstance(reffilename);

//        ArrayList<Integer> index = new ArrayList<Integer>();
//        index.add(0);
//        index.add(1);
//        index.add(2);
//        index.add(3);
//        NearestNeighbor.execute(matrix, new DynamicTimeWarping(), index, 15);

//        NearestNeighbor.nn(matrix, new DynamicTimeWarping(), refmatrix);
    }
}
