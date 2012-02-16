/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.plmp;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import plmp.technique.PLMPProjectionComp;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.streaming.StreamingMatrix;
import projection.util.ProjectionUtil;

/**
 *
 * @author Fernando
 */
public class TestPLMPProjectionMultipleFilesTime {

    public static void main(String[] args) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-10000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-20000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-30000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-40000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-50000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-60000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-70000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-80000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-90000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-100000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-110000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-120000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-130000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-140000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-150000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-160000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-170000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-180000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-190000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-200000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-210000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-220000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-230000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-240000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-250000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-260000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-270000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-280000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-290000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-300000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-310000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-320000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-330000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-340000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-350000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-360000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-370000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-380000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-390000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-400000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-410000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-420000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-430000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-440000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-450000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-460000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-470000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-480000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-490000.bin");
//        files.add("D:/dados/viscontest2008/multifield.0099-normcols.bin-500000.bin-500000.bin");

//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-10000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-20000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-30000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-40000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-50000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-60000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-70000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-80000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-90000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-100000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-110000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-120000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-130000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-140000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-150000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-160000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-170000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-180000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-190000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-200000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-210000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-220000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-230000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-240000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-250000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-260000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-270000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-280000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-290000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-300000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-310000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-320000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-330000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-340000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-350000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-360000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-370000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-380000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-390000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-400000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-410000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-420000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-430000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-440000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-450000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-460000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-470000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-480000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-490000.bin");
//        files.add("D:/dados/mammals/mammals-500000.bin-normcols.bin-500000.bin");

//        files.add("D:/dados/fibers/fibers250000-normcols.bin-10000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-20000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-30000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-40000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-50000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-60000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-70000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-80000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-90000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-100000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-110000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-120000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-130000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-140000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-150000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-160000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-170000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-180000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-190000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-200000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-210000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-220000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-230000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-240000.bin");
//        files.add("D:/dados/fibers/fibers250000-normcols.bin-250000.bin");

        files.add("/home/paulovich/Data/multifield.0099-normalized.bin");

        ProjectionUtil.log(false, false);

        for (int i = 0; i < files.size(); i++) {
            StreamingMatrix matrix = new StreamingMatrix();
            matrix.load(files.get(i));

            for (int j = 0; j < matrix.getRowCount(); j++) {
                AbstractVector row = matrix.getRow(j);
            }

            float time = Float.POSITIVE_INFINITY;

            for (int j = 0; j < 2; j++) {
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
//                FastmapProjection2DComp fastmap = new FastmapProjection2DComp();
//                fastmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
//                fastmap.input(matrix);
//                fastmap.execute();

                long finish = System.currentTimeMillis();

                if (time > (finish - start) / 1000.0f) {
                    time = (finish - start) / 1000.0f;
                }

                projection.save(files.get(i) + "-mmds.prj");
            }

            //System.out.println(matrix.getRowCount() + ";" + (finish - start) / 1000.0f);
            System.out.println(Float.toString(time).replaceAll("\\.", ","));
        }
    }
}
