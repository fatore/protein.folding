/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.silhouette;

import distance.dissimilarity.Euclidean;
import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import projectiontesting.Dump;

/**
 *
 * @author PC
 */
public abstract class TestSilhouetteTime {

    protected abstract AbstractMatrix project(AbstractMatrix matrix) throws IOException;


    public void execute(String filename, String technique, int nrattempts) throws IOException {
        Dump.dump("=================================================");
        Dump.dump("Technique: " + technique + " - processing: " + filename);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(filename);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        float silhouette_value = Float.NEGATIVE_INFINITY;
        float time = 0;
        AbstractMatrix projection = null;

        for (int i = 0; i < nrattempts; i++) {
            long start = System.currentTimeMillis();
            AbstractMatrix projection_aux = project(matrix);
            long finish = System.currentTimeMillis();

            //calculating silhouette
            SilhouetteCoefficient sc = new SilhouetteCoefficient();
            float silhouette_aux = sc.average(sc.execute(projection_aux, new Euclidean()));

            if (silhouette_value < silhouette_aux) {
                silhouette_value =  silhouette_aux;
                time = (finish - start) / 1000.0f;
                projection = projection_aux;
                projection.save(filename + "-" + technique + ".prj");
            }
        }

        Dump.dump("=================================================");
        Dump.dump("Technique: " + technique + " - processing: " + filename);
        Dump.dump(technique + " projection time: " + time + "s");
        Dump.dump(technique + " projection silhouette: " + silhouette_value);
        Dump.dump("=================================================");
    }

}
