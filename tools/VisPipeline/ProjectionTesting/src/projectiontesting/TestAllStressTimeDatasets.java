/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import projection.stress.StressFactory.StressType;
import projection.util.ProjectionUtil;
import projectiontesting.projection.stress.TestStressTimeChalmes;
import projectiontesting.projection.stress.TestStressTimeClassicMDS;
import projectiontesting.projection.stress.TestStressTimeFastForceScheme;
import projectiontesting.projection.stress.TestStressTimeLandmarksMDS;
import projectiontesting.projection.stress.TestStressTimeFastmap;
import projectiontesting.projection.stress.TestStressTimeForceScheme;
import projectiontesting.projection.stress.TestStressTimeHybridModel;
import projectiontesting.projection.stress.TestStressTimeJourdanMelancon;
import projectiontesting.projection.stress.TestStressTimeLLE;
import projectiontesting.projection.stress.TestStressTimeLSP;
import projectiontesting.projection.stress.TestStressTimeLandmarksISOMAP;
import projectiontesting.projection.stress.TestStressTimePLSP;
import projectiontesting.projection.stress.TestStressTimePekalska;
import projectiontesting.projection.stress.TestStressTimePivotMDS;
import projectiontesting.projection.stress.TestStressTimeRandomProjection;
import projectiontesting.projection.stress.TestStressTimeSMACOF;
import projectiontesting.projection.stress.TestStressTimeSammonMapping;

/**
 *
 * @author PC
 */
public class TestAllStressTimeDatasets {

    public static void main(String[] args) throws IOException {
        String dir = "/home/paulovich/Dropbox/dados/";

        Dump.setFilename(dir + "dump-stress_time-final.txt");

//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        testSmall(dir);
//
//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        testMedium(dir);

        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        testLarge(dir);

        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        testSparse(dir);

        Dump.setFilename(dir + "dump-silhouette-final.txt");

        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Dump.dump("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        TestSilhouetteFile.silhouetteAll(dir);
    }

    public static void testSmall(String dir) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add(dir + "wdbc-std.data");
        files.add(dir + "segmentation-normcols.data");
        files.add(dir + "winequality-red-std.data");
        files.add(dir + "winequality-white-std.data");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            Dump.dump("##########################################");
            Dump.dump(files.get(i));
            Dump.dump("##########################################");

            (new TestStressTimePLSP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "plsp", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeClassicMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "classicmds", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeChalmes()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "chalmers", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastForceScheme()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "fastforce", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastmap()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "fastmap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeForceScheme()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "forcescheme", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeHybridModel()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "hybridmodel", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeJourdanMelancon()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "jordanmelancon", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "landmarksmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksISOMAP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "l-isomap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLLE()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "lle", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLSP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "lsp", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePekalska()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "pekalska", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePivotMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "pivotmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeRandomProjection()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "radomproj", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeSammonMapping()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "sammon", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeSMACOF()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "smacof", 1, DissimilarityType.EUCLIDEAN);

            Dump.dump("##########################################");
        }
    }

    public static void testMedium(String dir) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add(dir + "mammals-10000-normcols.bin");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            Dump.dump("##########################################");
            Dump.dump(files.get(i));
            Dump.dump("##########################################");

            (new TestStressTimePLSP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "plsp", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeClassicMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "classicmds", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeChalmes()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "chalmers", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastForceScheme()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "fastforce", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastmap()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "fastmap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeForceScheme()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "forcescheme", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeHybridModel()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "hybridmodel", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeJourdanMelancon()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "jordanmelancon", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "landmarksmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksISOMAP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "l-isomap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLSP()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "lsp", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePekalska()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "pekalska", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePivotMDS()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "pivotmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeRandomProjection()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "radomproj", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeSMACOF()).execute(files.get(i), StressType.NORMALIZED_KRUSKAL, "smacof", 1, DissimilarityType.EUCLIDEAN);

            Dump.dump("##########################################");
        }
    }

    public static void testLarge(String dir) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add(dir + "multifield.0099-normcols.bin-30000.bin");
        files.add(dir + "shuttle_trn_corr-normcols.data");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            Dump.dump("##########################################");
            Dump.dump(files.get(i));
            Dump.dump("##########################################");

            (new TestStressTimePLSP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "plsp", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastForceScheme()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "fastforce", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeFastmap()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "fastmap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeHybridModel()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "hybridmodel", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeJourdanMelancon()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "jordanmelancon", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksMDS()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "landmarksmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLandmarksISOMAP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "l-isomap", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeLSP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "lsp", 1, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePekalska()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "pekalska", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimePivotMDS()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "pivotmds", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeRandomProjection()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "radomproj", 10, DissimilarityType.EUCLIDEAN);

            Dump.dump("##########################################");
        }
    }

     public static void testSparse(String dir) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add(dir + "amsouth8filter.data");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            Dump.dump("##########################################");
            Dump.dump(files.get(i));
            Dump.dump("##########################################");

            (new TestStressTimePLSP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "plsp", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimeFastForceScheme()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "fastforce", 1, DissimilarityType.COSINE_BASED);
            (new TestStressTimeFastmap()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "fastmap", 10, DissimilarityType.COSINE_BASED);
//            (new TestStressTimeHybridModel()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "hybridmodel", 10, DissimilarityType.EUCLIDEAN);
            (new TestStressTimeJourdanMelancon()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "jordanmelancon", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimeLandmarksMDS()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "landmarksmds", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimeLandmarksISOMAP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "l-isomap", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimeLSP()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "lsp", 1, DissimilarityType.COSINE_BASED);
            (new TestStressTimePekalska()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "pekalska", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimePivotMDS()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "pivotmds", 10, DissimilarityType.COSINE_BASED);
            (new TestStressTimeRandomProjection()).execute(files.get(i), StressType.PARTIAL_NORMALIZED_KRUSKAL, "radomproj", 10, DissimilarityType.EUCLIDEAN);

            Dump.dump("##########################################");
        }
    }
}
