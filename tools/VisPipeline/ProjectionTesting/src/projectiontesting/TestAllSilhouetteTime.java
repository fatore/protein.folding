/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting;

import java.io.IOException;
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
public class TestAllSilhouetteTime {

    public static void main(String[] args) throws IOException {
        ProjectionUtil.log(false, false);

        (new TestSilhouetteTimeClassicMDS()).execute(args[0], "classicmds", 1);
        (new TestSilhouetteTimeFastmap()).execute(args[0], "fastmap", 30);
        (new TestSilhouetteTimeForceScheme()).execute(args[0], "forcescheme", 1);
        (new TestSilhouetteTimeJourdanMelancon()).execute(args[0], "jourdanmelancon", 30);
        (new TestSilhouetteTimePLMPClustering()).execute(args[0], "plmp-clustering", 30);
        (new TestSilhouetteTimePLMPMaxMin()).execute(args[0], "plmp-maxmin", 30);
        (new TestSilhouetteTimePLMPRandom()).execute(args[0], "plmp-random", 30);
        (new TestSilhouetteTimePLMPSpam()).execute(args[0], "plmp-spam", 30);
        (new TestSilhouetteTimeLSP()).execute(args[0], "lsp", 1);
        (new TestSilhouetteTimePLSP()).execute(args[0], "plsp", 1);
        (new TestSilhouetteTimePekalska()).execute(args[0], "pekalska", 30);
        (new TestSilhouetteTimeSammonMapping()).execute(args[0], "sammon", 1);
        (new TestSilhouetteTimeChalmers()).execute(args[0], "chalmers", 30);
        (new TestSilhouetteTimeHybridModel()).execute(args[0], "hybrid", 30);
        (new TestSilhouetteTimeLandmarksMDS()).execute(args[0], "landmarks", 30);
        (new TestSilhouetteTimePivotMDS()).execute(args[0], "pivotmds", 30);
        (new TestSilhouetteTimeSMACOF()).execute(args[0], "smacof", 1);

    }

}

