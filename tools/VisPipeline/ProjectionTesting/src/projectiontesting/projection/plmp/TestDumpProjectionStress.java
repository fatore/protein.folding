/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.plmp;

import distance.LightWeightDistanceMatrix;
import distance.dissimilarity.Euclidean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.reader.MatrixReaderComp;

/**
 *
 * @author Fernando
 */
public class TestDumpProjectionStress {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Error: It should be: projection-directory matrix.data");
            System.exit(0);
        }

        String projdir = args[0];//"D:\\artigos\\2010\\mmds_vis_2010\\data\\projections\\fibers";
        String matrixfilename = args[1];//"D:\\dados\\fibers250000-normcols.bin-50000.bin";

        File matrixfile = new File(matrixfilename);
        File dir = new File(projdir);
        File[] files = dir.listFiles();

        ArrayList<File> projfiles = new ArrayList<File>();

        for (File f : files) {
            String filename = f.getName();
            if (filename.startsWith(matrixfile.getName()) && filename.endsWith(".prj")) {
                projfiles.add(f);
            }
        }

        Collections.sort(projfiles);

        for (File f : projfiles) {
            System.out.println("processing: " + f.getName());
        }

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(matrixfilename);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        //creating the Rn distance matrix
        LightWeightDistanceMatrix dmat = new LightWeightDistanceMatrix(matrix, new Euclidean());

        //finding the max value
        double maxrn = Float.MIN_VALUE;

        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                float valuern = dmat.getDistance(i, j);

                if (valuern > maxrn) {
                    maxrn = valuern;
                }
            }
        }

        //creating the R2 distance matrices
        LightWeightDistanceMatrix[] dmatprj = new LightWeightDistanceMatrix[projfiles.size()];

        for (int i = 0; i < dmatprj.length; i++) {
            DenseMatrix projection = new DenseMatrix();
            projection.load(projfiles.get(i).getPath());
            dmatprj[i] = new LightWeightDistanceMatrix(projection, new Euclidean());
        }

        //finding the max value
        double[] maxr2 = new double[projfiles.size()];
        Arrays.fill(maxr2, Double.NEGATIVE_INFINITY);

        for (int k = 0; k < maxr2.length; k++) {
            for (int i = 0; i < dmat.getElementCount(); i++) {
                for (int j = i + 1; j < dmat.getElementCount(); j++) {
                    double value = dmatprj[k].getDistance(i, j);

                    if (value > maxr2[k]) {
                        maxr2[k] = value;
                    }
                }
            }
        }

        int nrvalues = 500000;
        long nrelements = (((long) dmat.getElementCount() * (long) dmat.getElementCount())
                - (long) dmat.getElementCount()) / 2;

        HashSet<Long> index_aux = new HashSet<Long>();
        while (index_aux.size() < nrvalues) {
            long value = (int) (Math.random() * nrelements);
            index_aux.add(value);
        }

        ArrayList<Long> index = new ArrayList<Long>(index_aux);
        Collections.sort(index);

        //calculating the denominator
        double den = 0.0f;
        long count = 0;
        int r = 0;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                if (r < index.size() && index.get(r) == count) {
                    r++;
                    double distrn = dmat.getDistance(i, j) / maxrn;
                    den += distrn * distrn;
                }

                count++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(matrixfile + "-stressdump.txt"));

        for (int i = 0; i < projfiles.size(); i++) {
            bw.write(projfiles.get(i).getName() + "\r\n");
        }

        double[] stress = new double[projfiles.size()];
        Arrays.fill(stress, 0.0f);

        count = 0;
        r = 0;
        for (int i = 0; i < dmat.getElementCount(); i++) {
            for (int j = i + 1; j < dmat.getElementCount(); j++) {
                if (r < index.size() && index.get(r) == count) {
                    r++;

                    double distrn = dmat.getDistance(i, j) / maxrn;

                    for (int k = 0; k < maxr2.length; k++) {
                        double distr2 = dmatprj[k].getDistance(i, j) / maxr2[k];
                        double value = ((distrn - distr2) * (distrn - distr2)) / den;

                        stress[k] += value;

                        bw.write(Double.toString(value));

                        if (k < maxr2.length - 1) {
                            bw.write(";");
                        } else {
                            bw.write("\r\n");
                        }
                    }
                }

                count++;
            }
        }

        bw.close();

        for (int i = 0; i < stress.length; i++) {
            System.out.println("------------------------------");
            System.out.println(projfiles.get(i).getName());
            System.out.println("stress: " + (float) stress[i]);
            System.out.println("------------------------------");
        }
    }

}
