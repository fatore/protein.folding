/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.plmp;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import plmp.technique.PLMPProjectionComp;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.streaming.StreamingMatrix;
import projection.technique.fastmap.FastmapProjection2DComp;

/**
 *
 * @author paulovich
 */
public class TestStreammingTime {

    public static void main(String[] args) throws IOException {
        String matrixfilename = "/home/paulovich/Data/multifield.0099-normalized.bin";
        StreamingMatrix matrix = new StreamingMatrix();
        matrix.load(matrixfilename);

        for (int j = 0; j < matrix.getRowCount(); j++) {
            AbstractVector row = matrix.getRow(j);
        }

        System.out.println("Processing: " + matrixfilename);

        for (int j = 0; j < 5; j++) {
            //creating the projection
            long start = System.currentTimeMillis();
            PLMPProjectionComp spcomp = new PLMPProjectionComp();
            spcomp.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            spcomp.setFractionDelta(8.0f);
            spcomp.setNumberIterations(100);
            spcomp.setSampleType(SampleType.RANDOM);
            spcomp.input(matrix);
            spcomp.execute();
            AbstractMatrix projection = spcomp.output();

            long finish = System.currentTimeMillis();

            System.out.println("LMMDS time: " + (finish - start) / 1000.0f + "s");

            projection.save(matrixfilename + "-plmp-" + j + "-.prj");
        }

        for (int j = 0; j < 2; j++) {
            long start = System.currentTimeMillis();
            //creating the projection
            FastmapProjection2DComp fastmap = new FastmapProjection2DComp();
            fastmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            fastmap.input(matrix);
            fastmap.execute();
            AbstractMatrix projection = fastmap.output();
            long finish = System.currentTimeMillis();

            System.out.println("Fastmap time: " + (finish - start) / 1000.0f + "s");

            projection.save(matrixfilename + "-fastmap-" + j + "-.prj");
        }
    }
}
