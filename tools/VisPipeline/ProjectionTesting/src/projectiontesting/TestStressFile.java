/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.reader.MatrixReaderComp;
import projection.stress.StressComp;
import projection.stress.StressFactory.StressType;

/**
 *
 * @author PC
 */
public class TestStressFile {

    public static void main(String[] args) throws IOException {
        args = new String[2];

        ArrayList<String> datafiles = new ArrayList<String>();
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/winequality-white-std.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/winequality-red-std.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/wdbc-std.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/shuttle_trn_corr-normcols.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/segmentation-normcols.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/multifield.0099-normcols.bin-30000.bin.data");
        datafiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/mammals-10000-normcols.bin.data");

        ArrayList<String> projfiles = new ArrayList<String>();
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/winequality-white-std.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/winequality-red-std.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/wdbc-std.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/shuttle_trn_corr-normcols.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/segmentation-normcols.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/multifield.0099-normcols.bin-30000.bin.data-glimmer.prj");
        projfiles.add("D:/codigo/glimmer/glimmer_gpu/Release/data/mammals-10000-normcols.bin.data-glimmer.prj");




        for (int i = 0; i < datafiles.size(); i++) {
            args[0] = datafiles.get(i);
            args[1] = projfiles.get(i);

            if (args.length != 2) {
                System.out.println("Error: TestStressFile matrixfile.data projfile.prj");
            }

//        Dump.setFilename("stress_dump.txt");
//
            String matrixfile = args[0];
            String projfile = args[1];

            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename(matrixfile);
            reader.execute();
            AbstractMatrix matrix = reader.output();

            DenseMatrix projection = new DenseMatrix();
            projection.load(projfile);

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection, matrix);
            stress.execute();

            Dump.dump("---------");
            Dump.dump("data: " + matrixfile);
            Dump.dump("projection: " + projfile);
            Dump.dump("stress: " + stress.output());
            Dump.dump("---------");
        }
    }

}
