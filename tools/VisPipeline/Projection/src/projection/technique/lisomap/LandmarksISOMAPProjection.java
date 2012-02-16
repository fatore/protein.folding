/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.technique.lisomap;

import datamining.neighbors.ANN;
import datamining.neighbors.Pair;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import mdsj.ClassicalScaling;
import projection.model.ProjectionModelComp;
import projection.technique.Projection;
import projection.technique.isomap.Dijkstra;
import projection.util.ConnectedGraphGenerator;
import projection.view.ProjectionFrameComp;

/**
 *
 * @author PC
 */
public class LandmarksISOMAPProjection implements Projection {

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        int size = matrix.getRowCount();

        //creating a graph with its nearest neighbors
        ANN ann = new ANN(nrneighbors);
        Pair[][] neighborhood = ann.execute(matrix, diss);

        //assuring the connectivity (????)
        ConnectedGraphGenerator congraph = new ConnectedGraphGenerator();
        congraph.execute(neighborhood, matrix, diss);

        //getting the landmarks randomly
        int nrlandmarks = (int) Math.sqrt(size);
        ArrayList<Integer> landmarks = getLandmarks(matrix, nrlandmarks);

        //creating the landmaks distance matrix
        double[][] input = new double[nrlandmarks][size];

        //calculating the shortest paths
        Dijkstra dijkstra = new Dijkstra(neighborhood);
        for (int i = 0; i < landmarks.size(); i++) {
            float[] distances = dijkstra.execute(landmarks.get(i));

            for (int j = 0; j < distances.length; j++) {
                input[i][j] = distances[j];
                
                if (Double.isNaN(input[i][j]) || Double.isInfinite(input[i][j])) {
                    System.out.println("Error.... L-ISOMAP");
                }
            }
        }

        //executing the landmaks mds
        double[][] output = new double[2][size];
        ClassicalScaling.lmds(input, output);

        //creating the final projection
        DenseMatrix projection = new DenseMatrix();
        ArrayList<Integer> ids = matrix.getIds();
        float[] classData = matrix.getClassData();
        ArrayList<String> labels = matrix.getLabels();

        for (int i = 0; i < size; i++) {
            float[] vect = new float[2];
            vect[0] = (float) output[0][i];
            vect[1] = (float) output[1][i];

            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]), labels.get(i));
            }
        }

        long finish = System.currentTimeMillis();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Executing the Landmarks ISOMAP algorithm time: {0}s", (finish - start) / 1000.0f);

        return projection;
    }

    private ArrayList<Integer> getLandmarks(AbstractMatrix matrix, int size) throws IOException {
        ArrayList<Integer> landmarks = new ArrayList<Integer>();

        HashSet<Integer> sample = new HashSet<Integer>();
        Random random = new Random(System.currentTimeMillis());

        while (sample.size() < size) {
            int index = (int) (random.nextFloat() * (matrix.getRowCount()));
            if (index < matrix.getRowCount()) {
                sample.add(index);
            }
        }

        Iterator<Integer> iterator = sample.iterator();
        while (iterator.hasNext()) {
            int index = iterator.next();
            landmarks.add(index);
        }

        return landmarks;
    }

    /**
     * @return the nrneighbors
     */
    public int getNumberNeighbors() {
        return nrneighbors;
    }

    /**
     * @param nrneighbors the nrneighbors to set
     */
    public void setNumberNeighbors(int nrneighbors) {
        this.nrneighbors = nrneighbors;
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/dados/multifield.0099-normcols.bin-30000.bin";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        LandmarksISOMAPProjection lisomap = new LandmarksISOMAPProjection();
        AbstractMatrix projection = lisomap.project(matrix, new Euclidean());

        ProjectionModelComp model = new ProjectionModelComp();
        model.input(projection);
        model.execute();

        ProjectionFrameComp frame = new ProjectionFrameComp();
        frame.setTitle(filename);
        frame.input(model.output());
        frame.execute();
    }
    private int nrneighbors = 8;
}
