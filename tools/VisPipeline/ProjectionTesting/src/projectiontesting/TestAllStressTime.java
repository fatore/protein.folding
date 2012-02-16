/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import projection.stress.StressFactory.StressType;
import projection.util.ProjectionUtil;
import projectiontesting.projection.stress.TestStressTimeChalmes;
import projectiontesting.projection.stress.TestStressTimeClassicMDS;
import projectiontesting.projection.stress.TestStressTimeFastmap;
import projectiontesting.projection.stress.TestStressTimeForceScheme;
import projectiontesting.projection.stress.TestStressTimeHybridModel;
import projectiontesting.projection.stress.TestStressTimeISOMAP;
import projectiontesting.projection.stress.TestStressTimeLLE;
import projectiontesting.projection.stress.TestStressTimePLMPRandom;
import projectiontesting.projection.stress.TestStressTimeLSP;
import projectiontesting.projection.stress.TestStressTimeLandmarksMDS;
import projectiontesting.projection.stress.TestStressTimePLSP;
import projectiontesting.projection.stress.TestStressTimePivotMDS;
import projectiontesting.projection.stress.TestStressTimeRandomProjection;
import projectiontesting.projection.stress.TestStressTimeSMACOF;
import projectiontesting.projection.stress.TestStressTimeSammonMapping;

/**
 *
 * @author PC
 */
public class TestAllStressTime {

    public static void main(String[] args) throws IOException {
        ProjectionUtil.log(false, false);

        (new TestStressTimePLMPRandom()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "plmp", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeForceScheme()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "forcescheme", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeHybridModel()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "hybridmodel", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeChalmes()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "chalmers", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeLSP()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "lsp", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeSMACOF()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "smacof", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimePivotMDS()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "pivotmds", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeLandmarksMDS()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "landmarksmds", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeClassicMDS()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "classicmds", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeISOMAP()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "isomap", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeLLE()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "lle", 1, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeSammonMapping()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "sammon", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeFastmap()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "fastmap", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimePLSP()).execute(args[0], StressType.NORMALIZED_KRUSKAL, "plsp", 10, DissimilarityType.EUCLIDEAN);
        (new TestStressTimeRandomProjection()).execute(args[0], StressType.PARTIAL_NORMALIZED_KRUSKAL, "random-projection", 10, DissimilarityType.EUCLIDEAN);
    }
}
