/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.plmp;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import plmp.technique.PLMPProjection;
import plmp.technique.Sampling;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import projection.stress.StressComp;
import projection.stress.StressFactory.StressType;
import projection.technique.idmap.IDMAPProjection;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.util.ProjectionUtil;
import projectiontesting.Dump;

/**
 *
 * @author Fernando
 */
public class TestPLMPProjectionSampleStress {

    public static void main(String[] args) throws IOException {
        ProjectionUtil.log(false, false);

        ArrayList<String> files = new ArrayList<String>();
//        files.add("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\wdbc-std.data");
//        files.add("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\winequality-red-std.data");
//        files.add("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\segmentation-normcols.data");
        files.add("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\winequality-white-std.data");
//        files.add("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\mammals-10000-normcols.bin");

        for (int i = 0; i < files.size(); i++) {
            TestPLMPProjectionSampleStress test = new TestPLMPProjectionSampleStress();
            test.execute(files.get(i), 50, SampleType.RANDOM);
            test.execute(files.get(i), 50, SampleType.CLUSTERING);
            test.execute(files.get(i), 30, SampleType.MAXMIN);
            System.out.println("#######################################");
            System.out.println("#######################################");
            System.out.println("#######################################");
        }
    }

    public void execute(String filename, int nrattempts, SampleType sampletype) throws IOException {
        Dump.dump("========================");
        Dump.dump("file: " + filename);
        Dump.dump("sample type: " + sampletype);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(filename);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        float stress_value = Float.POSITIVE_INFINITY;
        AbstractMatrix sampledata = null;
        AbstractMatrix sampleproj = null;

        int maxsample = (int) Math.pow(matrix.getRowCount(), 0.5f);
        int minsample = Math.max((int) Math.sqrt(matrix.getRowCount()) / 20, 20);
        int step = (maxsample - minsample) / 20;

        Dump.dump("========================");

        for (int i = minsample; i <= maxsample; i += step) {
            Dump.dump(Integer.toString(i));
        }

        Dump.dump("========================");

        AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.EUCLIDEAN);

        for (int i = 0; i < nrattempts; i++) {
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

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.PARTIAL_NORMALIZED_KRUSKAL);
            stress.input(projection_aux, matrix);
            stress.execute();

            if (stress_value > stress.output()) {
                stress_value = stress.output();
                sampledata = sampledata_aux;
                sampleproj = sampleproj_aux;
            }

            System.out.print(i + " ");
        }

        Dump.dump("========================");

        for (int i = minsample; i <= maxsample; i += step) {
            float stress = getMeanStress(sampledata, sampleproj, matrix, diss, maxsample, i);
            Dump.dump(Float.toString(stress).replaceAll("\\.", ","));
        }

        Dump.dump("========================");
    }

    private float getMeanStress(AbstractMatrix sampledata, AbstractMatrix sampleproj,
            AbstractMatrix matrix, AbstractDissimilarity diss,
            int maxsample, int samplesize) throws IOException {
        float meanstress = 0.0f;
        int nrattempts = 30;

        for (int i = 0; i < nrattempts; i++) {
            //create the sub-sample matrix data
            HashSet<Integer> subsample_aux = new HashSet<Integer>();
            Random random = new Random(System.currentTimeMillis());

            while (subsample_aux.size() < samplesize) {
                int index = (int) (random.nextFloat() * maxsample);
                if (index < maxsample) {
                    subsample_aux.add(index);
                }
            }

            AbstractMatrix sampledata_aux = new DenseMatrix();
            AbstractMatrix sampleproj_aux = new DenseMatrix();

            Iterator<Integer> it = subsample_aux.iterator();
            while (it.hasNext()) {
                int index = it.next();
                sampledata_aux.addRow(new DenseVector(sampledata.getRow(index).toArray()));
                sampleproj_aux.addRow(new DenseVector(sampleproj.getRow(index).toArray()));
            }

            //adding the base
            for (int j = maxsample; j < sampledata.getRowCount(); j++) {
                sampledata_aux.addRow(sampledata.getRow(j));
                sampleproj_aux.addRow(sampleproj.getRow(j));
            }

            PLMPProjection mmds = new PLMPProjection();
            mmds.setFractionDelta(8.0f);
            mmds.setNumberIterations(100);
            mmds.setSampleMatrix(sampledata_aux);
            mmds.setSampleProjection(sampleproj_aux);
            AbstractMatrix projection_aux = mmds.project(matrix, diss);

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection_aux, matrix);
            stress.execute();

            meanstress += stress.output();
        }

        meanstress /= nrattempts;

        return meanstress;
    }

    private float getMedianStress(AbstractMatrix sampledata, AbstractMatrix sampleproj,
            AbstractMatrix matrix, AbstractDissimilarity diss,
            int maxsample, int samplesize) throws IOException {

        ArrayList<Float> medianstress = new ArrayList<Float>();
        int nrattempts = 30;

        for (int i = 0; i < nrattempts; i++) {
            //create the sub-sample matrix data
            HashSet<Integer> subsample_aux = new HashSet<Integer>();
            Random random = new Random(System.currentTimeMillis());

            while (subsample_aux.size() < samplesize) {
                int index = (int) (random.nextFloat() * maxsample);
                if (index < maxsample) {
                    subsample_aux.add(index);
                }
            }

            AbstractMatrix sampledata_aux = new DenseMatrix();
            AbstractMatrix sampleproj_aux = new DenseMatrix();

            Iterator<Integer> it = subsample_aux.iterator();
            while (it.hasNext()) {
                int index = it.next();
                sampledata_aux.addRow(new DenseVector(sampledata.getRow(index).toArray()));
                sampleproj_aux.addRow(new DenseVector(sampleproj.getRow(index).toArray()));
            }

            //adding the base
            for (int j = maxsample; j < sampledata.getRowCount(); j++) {
                sampledata_aux.addRow(sampledata.getRow(j));
                sampleproj_aux.addRow(sampleproj.getRow(j));
            }

            PLMPProjection mmds = new PLMPProjection();
            mmds.setFractionDelta(8.0f);
            mmds.setNumberIterations(100);
            mmds.setSampleMatrix(sampledata_aux);
            mmds.setSampleProjection(sampleproj_aux);
            AbstractMatrix projection_aux = mmds.project(matrix, diss);

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection_aux, matrix);
            stress.execute();

            medianstress.add(stress.output());
        }

        Collections.sort(medianstress);

        return medianstress.get(medianstress.size() / 2);
    }

}
