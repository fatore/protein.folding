/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting;

import java.io.IOException;
import java.util.ArrayList;
import projection.util.ProjectionUtil;
import projectiontesting.projection.silhouette.TestSilhouetteTimeClassicMDS;
import projectiontesting.projection.silhouette.TestSilhouetteTimeFastmap;
import projectiontesting.projection.silhouette.TestSilhouetteTimeForceScheme;
import projectiontesting.projection.silhouette.TestSilhouetteTimeJourdanMelancon;
import projectiontesting.projection.silhouette.TestSilhouetteTimePLMPClustering;
import projectiontesting.projection.silhouette.TestSilhouetteTimePLMPMaxMin;
import projectiontesting.projection.silhouette.TestSilhouetteTimePLMPRandom;
import projectiontesting.projection.silhouette.TestSilhouetteTimePLMPSpam;
import projectiontesting.projection.silhouette.TestSilhouetteTimeLSP;
import projectiontesting.projection.silhouette.TestSilhouetteTimePLSP;
import projectiontesting.projection.silhouette.TestSilhouetteTimePekalska;
import projectiontesting.projection.silhouette.TestSilhouetteTimeSammonMapping;
import projectiontesting.projection.silhouette.TestSilhouetteTimeChalmers;
import projectiontesting.projection.silhouette.TestSilhouetteTimeHybridModel;
import projectiontesting.projection.silhouette.TestSilhouetteTimeLandmarksMDS;
import projectiontesting.projection.silhouette.TestSilhouetteTimePivotMDS;
import projectiontesting.projection.silhouette.TestSilhouetteTimeSMACOF;

/**
 *
 * @author PC
 */
public class TestAllSilhouetteTimeDatasets {

    public static void main(String[] args) throws IOException {
        test1();
    }

    public static void test1() throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add("D:/dados/wdbc-std.data");
        files.add("D:/dados/segmentation-normcols.data");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            Dump.dump("##########################################");
            Dump.dump("##########################################");
            Dump.dump(files.get(i));
            Dump.dump("##########################################");

            (new TestSilhouetteTimeClassicMDS()).execute(files.get(i), "classicmds", 1);
            (new TestSilhouetteTimeFastmap()).execute(files.get(i), "fastmap", 30);
            (new TestSilhouetteTimeForceScheme()).execute(files.get(i), "forcescheme", 1);
            (new TestSilhouetteTimeJourdanMelancon()).execute(files.get(i), "jourdanmelancon", 30);
            (new TestSilhouetteTimePLMPClustering()).execute(files.get(i), "plmp-clustering", 30);
            (new TestSilhouetteTimePLMPMaxMin()).execute(files.get(i), "plmp-maxmin", 30);
            (new TestSilhouetteTimePLMPRandom()).execute(files.get(i), "plmp-random", 30);
            (new TestSilhouetteTimePLMPSpam()).execute(files.get(i), "plmp-spam", 30);
            (new TestSilhouetteTimeLSP()).execute(files.get(i), "lsp", 1);
            (new TestSilhouetteTimePLSP()).execute(files.get(i), "plsp", 1);
            (new TestSilhouetteTimePekalska()).execute(files.get(i), "pekalska", 30);
            (new TestSilhouetteTimeSammonMapping()).execute(files.get(i), "sammon", 1);
            (new TestSilhouetteTimeChalmers()).execute(files.get(i), "chalmers", 30);
            (new TestSilhouetteTimeHybridModel()).execute(files.get(i), "hybrid", 30);
            (new TestSilhouetteTimeLandmarksMDS()).execute(files.get(i), "landmarks", 30);
            (new TestSilhouetteTimePivotMDS()).execute(files.get(i), "pivotmds", 30);
            (new TestSilhouetteTimeSMACOF()).execute(files.get(i), "smacof", 1);

            Dump.dump("##########################################");
            Dump.dump("##########################################");
        }
    }

}
