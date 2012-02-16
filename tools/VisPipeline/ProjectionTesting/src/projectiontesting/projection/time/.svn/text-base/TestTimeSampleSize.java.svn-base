/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.time;

import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import matrix.reader.MatrixReaderComp;
import projectiontesting.Dump;

/**
 *
 * @author PC
 */
public abstract class TestTimeSampleSize {

    protected abstract void project(AbstractMatrix matrix) throws IOException;

    public void execute(String filename, String technique, int initial, int step) throws IOException {
        Dump.dump("=================================================");
        Dump.dump("Technique: " + technique + " - processing: " + filename);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(filename);
        reader.execute();
        AbstractMatrix complete_matrix = reader.output();

        for (int i = initial; i <= complete_matrix.getRowCount(); i += step) {
            //creating the partial matrix
            AbstractMatrix matrix = MatrixFactory.getInstance(complete_matrix.getClass());
            for (int j = 0; j < i; j++) {
                matrix.addRow(complete_matrix.getRow(j));
            }

            float time = Float.POSITIVE_INFINITY;

            for (int j = 0; j < 2; j++) {
                //creating the projection
                long start = System.currentTimeMillis();
                project(matrix);
                long finish = System.currentTimeMillis();

                if (time > (finish - start) / 1000.0f) {
                    time = (finish - start) / 1000.0f;
                }
            }

            //System.out.println(matrix.getRowCount() + ";" + time);
            Dump.dump(Float.toString(time).replaceAll("\\.", ","));
        }

        Dump.dump("=================================================");
    }
}

