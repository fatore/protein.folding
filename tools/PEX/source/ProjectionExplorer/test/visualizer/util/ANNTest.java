/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizer.util;

import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;
import visualizer.datamining.clustering.BKmeans;
import visualizer.matrix.DenseMatrix;
import visualizer.matrix.DenseVector;
import visualizer.matrix.Matrix;
import visualizer.matrix.MatrixFactory;
import visualizer.matrix.SparseMatrix;
import visualizer.matrix.SparseVector;
import visualizer.projection.distance.CosineBased;
import visualizer.projection.distance.Dissimilarity;
import visualizer.projection.distance.Euclidean;

/**
 *
 * @author Fernando Paulovich
 */
public class ANNTest extends TestCase {

    public ANNTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of execute method, of class KNN.
     */
    public void testExecute1() throws Exception {
        System.out.println("execute1: sparse matrix");

        for (int t = 0; t < 15; t++) {
            Matrix matrix = new SparseMatrix();

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = 0.0f;
                point[1] = 0.0f;
                matrix.addRow(new SparseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random() + 10;
                point[1] = (float) Math.random();
                matrix.addRow(new SparseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random();
                point[1] = (float) Math.random() + 10;
                matrix.addRow(new SparseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random() + 10;
                point[1] = (float) Math.random() + 10;
                matrix.addRow(new SparseVector(point));
            }

            int nrNeighbors = (int) (23 * Math.random()) + 1;

            Dissimilarity diss = new Euclidean();
            if (t % 2 == 0) {
                diss = new CosineBased();
            }

            ANN knn = new ANN(nrNeighbors);
            Pair[][] result = knn.execute(matrix, diss);

            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index > 25));
                }
            }

            for (int i = 25; i < 50; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 25 && result[i][j].index > 50));
                }
            }

            for (int i = 50; i < 75; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 50 && result[i][j].index > 75));
                }
            }

            for (int i = 75; i < 100; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 75));
                }
            }
        }
    }

    public void testExecute2() throws Exception {
        System.out.println("execute2 : dense matrix");

        for (int t = 0; t < 15; t++) {
            Matrix matrix = new DenseMatrix();

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = 0.0f;
                point[1] = 0.0f;
                matrix.addRow(new DenseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random() + 10;
                point[1] = (float) Math.random();
                matrix.addRow(new DenseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random();
                point[1] = (float) Math.random() + 10;
                matrix.addRow(new DenseVector(point));
            }

            for (int i = 0; i < 25; i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random() + 10;
                point[1] = (float) Math.random() + 10;
                matrix.addRow(new DenseVector(point));
            }

            int nrNeighbors = (int) (23 * Math.random()) + 1;

            Dissimilarity diss = new Euclidean();
            if (t % 2 == 0) {
                diss = new CosineBased();
            }

            ANN knn = new ANN(nrNeighbors);
            Pair[][] result = knn.execute(matrix, diss);

            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index > 25));
                }
            }

            for (int i = 25; i < 50; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 25 && result[i][j].index > 50));
                }
            }

            for (int i = 50; i < 75; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 50 && result[i][j].index > 75));
                }
            }

            for (int i = 75; i < 100; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    assertFalse("neighborhood wil defined.",
                            (result[i][j].index < 75));
                }
            }
        }
    }

    public void testExecute3() throws Exception {
        System.out.println("execute3 : null vectors");

        for (int t = 0; t < 15; t++) {
            Matrix matrix = new SparseMatrix();

            int DIM = 1000;

            for (int i = 0; i < 50; i++) {
                float[] point = new float[DIM];
                Arrays.fill(point, 0.0f);
                matrix.addRow(new SparseVector(point));
            }

            for (int i = 0; i < 450; i++) {
                float[] point = new float[DIM];
                Arrays.fill(point, 0.0f);

                for (int j = 0; j < point.length; j++) {
                    float esp = (float) Math.random();
                    if (esp > 0.75f) {
                        point[j] = (float) Math.random();
                    }
                }

                matrix.addRow(new SparseVector(point));
            }

            int nrNeighbors = (int) (48 * Math.random()) + 1;

            Dissimilarity diss = new Euclidean();
            if (t % 2 == 0) {
                diss = new CosineBased();
            }

            ANN knn = new ANN(nrNeighbors);
            Pair[][] result = knn.execute(matrix, diss);

            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (result[i][j].index > 50) {
                        System.out.println("Number neighbors: " + nrNeighbors);
                        System.out.println("diss: " + diss.toString());
                        System.out.println("distance: " + diss.calculate(matrix.getRow(i),
                                matrix.getRow(result[i][j].index)));
                        assertFalse("neighborhood wil defined.", true);
                    }
                }
            }
        }
    }

    public void testExecute4() throws Exception {
        System.out.println("execute4 : percentage");

        for (int t = 0; t < 15; t++) {
            Matrix matrix = new SparseMatrix();

            int DIM = 1000;

            for (int i = 0; i < 10; i++) {
                float[] point = new float[DIM];
                Arrays.fill(point, 0.0f);
                matrix.addRow(new SparseVector(point));
            }

            for (int i = 0; i < 490; i++) {
                float[] point = new float[DIM];
                Arrays.fill(point, 0.0f);

                for (int j = 0; j < point.length; j++) {
                    float esp = (float) Math.random();
                    if (esp > 0.75f) {
                        point[j] = (float) Math.random();
                    }
                }

                matrix.addRow(new SparseVector(point));
            }

            int nrNeighbors = (int) (48 * Math.random()) + 1;

            Dissimilarity diss = new Euclidean();
            if (t % 2 == 0) {
                diss = new CosineBased();
            }

            ANN appknn = new ANN(nrNeighbors);
            Pair[][] appResult = appknn.execute(matrix, diss);

            KNN knn = new KNN(nrNeighbors);
            Pair[][] result = knn.execute(matrix, diss);

            float total = 0.0f;

            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    boolean contain = false;
                    for (int k = 0; k < appResult[i].length; k++) {
                        if (result[i][j].index == appResult[i][k].index) {
                            contain = true;
                            break;
                        }
                    }

                    if (contain) {
                        total++;
                    }
                }
            }

            System.out.println("Metric: " + diss.toString());
            System.out.println("Percentage: " + total / (result.length * nrNeighbors));
        }
    }

    public void testExecute5() throws Exception {
        System.out.println("execute5 : percentage [cbr-ilp-ir.data]");

        String matrixfilename = ".\\test\\data\\cbr-ilp-ir.data";
        Matrix matrix = MatrixFactory.getInstance(matrixfilename);

        Dissimilarity diss = new CosineBased();

        int nrNeighbors = 15;

        ANN appknn = new ANN(nrNeighbors);
        Pair[][] appResult = appknn.execute(matrix, diss);

        KNN knn = new KNN(nrNeighbors);
        Pair[][] result = knn.execute(matrix, diss);

        float total = 0.0f;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                boolean contain = false;
                for (int k = 0; k < appResult[i].length; k++) {
                    if (result[i][j].index == appResult[i][k].index) {
                        contain = true;
                        break;
                    }
                }

                if (contain) {
                    total++;
                }
            }
        }

        System.out.println("Metric: " + diss.toString());
        System.out.println("Percentage: " + total / (result.length * nrNeighbors));
    }

    public void testExecute6() throws Exception {
        System.out.println("execute1 : percentage increasing the number of clusters [cbr-ilp-ir.data]");

        String matrixfilename = ".\\test\\data\\cbr-ilp-ir.data";
        Matrix matrix = MatrixFactory.getInstance(matrixfilename);

        Dissimilarity diss = new CosineBased();

        int nrNeighbors = 15;

        BKmeans bkmeans1 = new BKmeans((int) Math.pow(matrix.getRowCount(), 0.5f));
        ArrayList<ArrayList<Integer>> clusters1 = bkmeans1.execute(diss, matrix);
        Matrix centroids1 = bkmeans1.getCentroids();

        ANN ann1 = new ANN(nrNeighbors);
        Pair[][] appResult1 = ann1.execute(matrix, diss, clusters1, centroids1);

        BKmeans bkmeans2 = new BKmeans((int) Math.pow(matrix.getRowCount(), 0.75f));
        ArrayList<ArrayList<Integer>> clusters2 = bkmeans2.execute(diss, matrix);
        Matrix centroids2 = bkmeans2.getCentroids();

        ANN ann2 = new ANN(nrNeighbors);
        Pair[][] appResult2 = ann2.execute(matrix, diss, clusters2, centroids2);

        float total = 0.0f;

        for (int i = 0; i < appResult2.length; i++) {
            for (int j = 0; j < appResult2[i].length; j++) {
                boolean contain = false;

                for (int k = 0; k < appResult1[i].length; k++) {
                    if (appResult2[i][j].index == appResult1[i][k].index) {
                        contain = true;
                        break;
                    }
                }

                if (contain) {
                    total++;
                }
            }
        }

        System.out.println("---------------------------");
        System.out.println("Metric: " + diss.toString());
        System.out.println("Percentage: " + total / (appResult1.length * nrNeighbors));
    }

}
