/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting;

import datamining.clustering.SilhouetteCoefficient;
import distance.dissimilarity.Euclidean;
import java.io.File;
import java.io.IOException;
import matrix.dense.DenseMatrix;

/**
 *
 * @author paulovich
 */
public class TestSilhouetteFile {

    public static void silhouette(String filename) throws IOException {
        Dump.dump("=================================================");
        Dump.dump("Projection file: " + filename);

        DenseMatrix projection = new DenseMatrix();
        projection.load(filename);

        //calculating silhouette
        SilhouetteCoefficient sc = new SilhouetteCoefficient();

        if (sc.canCompute(projection)) {
            float silhouette_value = sc.average(sc.execute(projection, new Euclidean()));
            Dump.dump("Projection silhouette: " + silhouette_value);
            Dump.dump("=================================================");
        } else {
            Dump.dump("Silhouette NOT computed! ");
            Dump.dump("=================================================");
        }
    }

    public static void silhouetteAll(String dir) throws IOException {
        File file = new File(dir);

        File[] list = file.listFiles();

        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile() && list[i].getName().endsWith(".prj")) {
                TestSilhouetteFile.silhouette(list[i].getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String projdir = "/home/paulovich/Dropbox/dados/";
        TestSilhouetteFile.silhouetteAll(projdir);
    }
}
