/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.plmp;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import javasci.SciDoubleArray;
import javasci.Scilab;
import plmp.technique.PLMPProjection;
import plmp.technique.Sampling;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import projection.technique.idmap.IDMAPProjection;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.util.ProjectionUtil;

/**
 *
 * @author Fernando
 */
public class TestSVDConvergency {

    public void execute(String filename, int nrattempts, SampleType sampletype) throws IOException {
        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(filename);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        float stress_value = Float.POSITIVE_INFINITY;
        AbstractMatrix sampledata = null;
        AbstractMatrix sampleproj = null;

        int maxsample = (int) Math.pow(matrix.getRowCount(), 0.5f) * 3;

        AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.EUCLIDEAN);

        //finding the best sample data and projection
//        for (int i = 0; i < nrattempts; i++) {
        {
            Sampling sampling = new Sampling(sampletype, maxsample);
            AbstractMatrix sampledata_aux = sampling.execute(matrix, diss);

            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setFractionDelta(8.0f);
            idmap.setInitialization(InitializationType.FASTMAP);
            idmap.setNumberIterations(100);
            AbstractMatrix sampleproj_aux = idmap.project(sampledata_aux, diss);

            PLMPProjection mmds = new PLMPProjection();
            mmds.setFractionDelta(8.0f);
            mmds.setNumberIterations(100);
            mmds.setSampleMatrix(sampledata_aux);
            mmds.setSampleProjection(sampleproj_aux);
            AbstractMatrix projection_aux = mmds.project(matrix, diss);

//            StressComp stress = new StressComp();
//            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
//            stress.setStressType(StressType.PARTIAL_NORMALIZED_KRUSKAL);
//            stress.input(projection_aux, matrix);
//            stress.execute();
//
//            if (stress_value > stress.output()) {
//                stress_value = stress.output();
            sampledata = sampledata_aux;
            sampleproj = sampleproj_aux;
//            }
//
//            System.out.print(i + " ");
//        }
        }

        System.out.println();
        System.out.println("maxsample: " + maxsample);
        System.out.println("sample size: " + sampledata.getRowCount());

        AbstractMatrix s = null;
        AbstractMatrix u = null;
        AbstractMatrix v = null;

        //using this sample, create projections and calculate the SVD
        int minsample = Math.max((int) Math.sqrt(matrix.getRowCount()) / 20, 20);
        int step = (maxsample - minsample) / 40;

        for (int i = minsample; i < maxsample; i += step) {
            //adding the data
            AbstractMatrix sampledata_aux = MatrixFactory.getInstance(sampledata.getClass());
            AbstractMatrix sampleproj_aux = MatrixFactory.getInstance(sampleproj.getClass());

            for (int j = 0; j <= i; j++) {
                sampledata_aux.addRow(sampledata.getRow(j));
                sampleproj_aux.addRow(sampleproj.getRow(j));
            }

            //adding the base
            for (int j = maxsample; j < sampledata.getRowCount(); j++) {
                sampledata_aux.addRow(sampledata.getRow(j));
                sampleproj_aux.addRow(sampleproj.getRow(j));
            }

            PLMPSVDProjection lmp = new PLMPSVDProjection();
            lmp.setFractionDelta(8.0f);
            lmp.setNumberIterations(100);
            lmp.setSampleMatrix(sampledata_aux);
            lmp.setSampleProjection(sampleproj_aux);
            lmp.project(matrix, diss);

            Algebra alg = new Algebra();
            DoubleMatrix2D transf = alg.transpose(lmp.getTransformation());

            AbstractMatrix s_next = new DenseMatrix();
            AbstractMatrix u_next = new DenseMatrix();
            AbstractMatrix v_next = new DenseMatrix();
            calculateSVD(transf, s_next, u_next, v_next);

            //calculate the norm
            if (s != null && u != null && v != null) {
                double ns = calculateNorm1(s, s_next);
                double nu = calculateNorm1(u, u_next);
                double nv = calculateNorm1(v, v_next);
                double cos = cosine(v_next);
                double rat = ratio(s_next);
                String norms = i + ";" + nu + ";" + ns + ";" + nv + ";" + rat;
                System.out.println(norms.replaceAll("\\.", ","));
            }

            s = s_next;
            u = u_next;
            v = v_next;
        }
    }

    private double cosine(AbstractMatrix m) {
        float norm1 = m.getRow(0).norm();
        float norm2 = m.getRow(1).norm();
        return m.getRow(0).dot(m.getRow(1)) / (norm1 * norm2);
    }

    private void calculateSVD(DoubleMatrix2D transf, AbstractMatrix S, AbstractMatrix U, AbstractMatrix V) {
        double[] avect = new double[transf.rows() * transf.columns()];
        int count = 0;
        for (int i = 0; i < transf.rows(); i++) {
            for (int j = 0; j < transf.columns(); j++) {
                avect[count++] = transf.get(i, j);
            }
        }

        double[] uvect = new double[transf.rows() * transf.rows()];
        double[] svect = new double[transf.rows() * transf.columns()];
        double[] vvect = new double[transf.columns() * transf.columns()];

        SciDoubleArray a = new SciDoubleArray("A", transf.rows(), transf.columns(), avect);
        SciDoubleArray u = new SciDoubleArray("U", transf.rows(), transf.rows(), uvect);
        SciDoubleArray s = new SciDoubleArray("S", transf.rows(), transf.columns(), svect);
        SciDoubleArray v = new SciDoubleArray("V", transf.columns(), transf.columns(), vvect);

        Scilab.Exec("[U,S,V]=svd(A)");

        uvect = u.getData();
        svect = s.getData();
        vvect = v.getData();

        count = 0;
        float[][] mat = new float[transf.rows()][transf.rows()];
        for (int col = 0; col < mat[0].length; col++) {
            for (int lin = 0; lin < mat.length; lin++) {
                mat[lin][col] = (float) uvect[count++];
            }
        }
        for (int lin = 0; lin < mat.length; lin++) {
            U.addRow(new DenseVector(mat[lin]));
        }

        count = 0;
        mat = new float[transf.rows()][transf.columns()];
        for (int col = 0; col < mat[0].length; col++) {
            for (int lin = 0; lin < mat.length; lin++) {
                mat[lin][col] = (float) svect[count++];
            }
        }
        for (int lin = 0; lin < mat.length; lin++) {
            S.addRow(new DenseVector(mat[lin]));
        }

        count = 0;
        mat = new float[transf.columns()][transf.columns()];
        for (int col = 0; col < mat[0].length; col++) {
            for (int lin = 0; lin < mat.length; lin++) {
                mat[lin][col] = (float) vvect[count++];
            }
        }
        for (int lin = 0; lin < mat.length; lin++) {
            V.addRow(new DenseVector(mat[lin]));
        }

        //Scilab.Finish();
    }

    private double ratio(AbstractMatrix S) {
        float first = S.getRow(0).getValue(0);
        float second = S.getRow(1).getValue(1);
        return Math.max(first, second) / Math.min(first, second);
    }

    private double calculateNorm1(AbstractMatrix m1, AbstractMatrix m2) {
        double sum = 0;

        for (int i = 0; i < m1.getRowCount(); i++) {
            for (int j = 0; j < m1.getDimensions(); j++) {
                double diff = Math.abs(m1.getRow(i).getValue(j) - m2.getRow(i).getValue(j));
                sum += diff;
            }
        }

        return sum;
    }

    private double calculateNorm2(AbstractMatrix m1, AbstractMatrix m2) {
        double sum = 0;

        for (int i = 0; i < m1.getRowCount(); i++) {
            for (int j = 0; j < m1.getDimensions(); j++) {
                double diff = Math.abs(m1.getRow(i).getValue(j) - m2.getRow(i).getValue(j));
                sum += diff * diff;
            }
        }

        return Math.sqrt(sum);
    }

    private double calculateNormInf(AbstractMatrix m1, AbstractMatrix m2) {
        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < m1.getRowCount(); i++) {
            for (int j = 0; j < m1.getDimensions(); j++) {
                double diff = Math.abs(m1.getRow(i).getValue(j) - m2.getRow(i).getValue(j));

                if (diff > max) {
                    max = diff;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
//        String filename = "D:\\viscontest\\normcols\\multifield.0099_4_4_4-normcols.bin";
        String filename = "D:\\dados\\fibers250000-normcols.bin-200000.bin";

        ProjectionUtil.log(false, false);

        TestSVDConvergency svdconv = new TestSVDConvergency();
        svdconv.execute(filename, 1, SampleType.RANDOM);
    }

}
