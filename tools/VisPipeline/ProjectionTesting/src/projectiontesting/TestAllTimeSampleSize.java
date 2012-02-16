/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting;

import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import projection.util.ProjectionUtil;
import projectiontesting.projection.time.TestTimeSampleSizeHybridModel;
import projectiontesting.projection.time.TestTimeSampleSizeLandmarksMDS;
import projectiontesting.projection.time.TestTimeSampleSizePivotMDS;
import projectiontesting.projection.time.TestTimeSampleSizeFastmap;
import projectiontesting.projection.time.TestTimeSampleSizePLMP;
import projectiontesting.projection.time.TestTimeSampleSizePLSP;
import projectiontesting.projection.time.TestTimeSampleSizePekaslka;

/**
 *
 * @author PC
 */
public class TestAllTimeSampleSize {

    public static void main(String[] args) throws IOException {
        Dump.setFilename("D:/My Dropbox/time_sample_size.txt");

        ProjectionUtil.log(false, false);

        int initial = 10000;
        int step = 10000;

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(args[0]);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        Dump.dump("=================================================");
        for (int i = initial; i <= matrix.getRowCount(); i += step) {
            Dump.dump(Integer.toString(i));
        }
        Dump.dump("=================================================");

//        (new TestTimeSampleSizeFastmap()).execute(args[0], "fastmap", initial, step);
////        (new TestTimeSampleSizeForceScheme()).execute(args[0], "forcescheme", initial, step);
//        (new TestTimeSampleSizeLMMDS()).execute(args[0], "plmp", initial, step);
////        (new TestTimeSampleSizeLSP()).execute(args[0], "lsp", initial, step);
////        (new TestTimeSampleSizeHybridModel()).execute(args[0], "hybridmodel", initial, step);
//        (new TestTimeSampleSizeLandmarksMDS()).execute(args[0], "landmarksmds", initial, step);
////        (new TestTimeSampleSizePLSP()).execute(args[0], "plsp", initial, step);
        (new TestTimeSampleSizePekaslka()).execute(args[0], "pekalska", initial, step);
//        (new TestTimeSampleSizePivotMDS()).execute(args[0], "pivotsmds", initial, step);

    }
}
