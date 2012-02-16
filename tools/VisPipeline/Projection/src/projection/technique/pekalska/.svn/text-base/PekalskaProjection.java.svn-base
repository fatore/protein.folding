/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.technique.pekalska;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.LUDecomposition;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;
import projection.technique.sammon.SammonMappingProjection;

/**
 *
 * @author PC
 */
public class PekalskaProjection implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        int samplesize = (int) Math.sqrt(matrix.getRowCount());

        //create the sample distance matrix
        AbstractMatrix sampledata = getSampleData(matrix, samplesize);
        DistanceMatrix sampledmat = new DistanceMatrix(sampledata, diss);

        SammonMappingProjection sammon = new SammonMappingProjection();
        sammon.setNumberIterations(sampledmat.getElementCount());
        AbstractMatrix sampleproj = sammon.project(sampledmat);

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Sample projection created... sample size: " + samplesize);

        //finding V where (D_base.V = Y_base)
        int nrows = sampledmat.getElementCount();
        int ncolumns = sampledmat.getElementCount();

        //creating matrix D_base
        DoubleMatrix2D D = new DenseDoubleMatrix2D(nrows, ncolumns);

        for (int i = 0; i < sampledmat.getElementCount(); i++) {
            for (int j = 0; j < sampledmat.getElementCount(); j++) {
                D.setQuick(i, j, sampledmat.getDistance(i, j));
            }
        }

        //creating Y_base
        DoubleMatrix2D Y = new DenseDoubleMatrix2D(nrows, 2);

        for (int i = 0; i < sampledata.getRowCount(); i++) {
            float[] array = sampleproj.getRow(i).toArray();

            for (int j = 0; j < array.length; j++) {
                Y.setQuick(i, j, array[j]);
            }
        }

        //solving to find V
        LUDecomposition solver = new LUDecomposition(D);
        //QRDecomposition solver = new QRDecomposition(D);
        DoubleMatrix2D result = solver.solve(Y);

        float[][] aux_V = new float[2][];
        aux_V[0] = new float[ncolumns];
        aux_V[1] = new float[ncolumns];

        for (int i = 0; i < result.rows(); i++) {
            aux_V[0][i] = (float) result.getQuick(i, 0);
            aux_V[1][i] = (float) result.getQuick(i, 1);
        }

        DenseMatrix V = new DenseMatrix();
        V.addRow(new DenseVector(aux_V[0]));
        V.addRow(new DenseVector(aux_V[1]));

        DenseMatrix projection = new DenseMatrix();

        ArrayList<Integer> ids = matrix.getIds();
        float[] classData = matrix.getClassData();
        ArrayList<String> labels = matrix.getLabels();

        //finally, calculating the projection (Y_base = D_base.V)
        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);
            float[] dists = new float[sampledata.getRowCount()];

            for (int j = 0; j < dists.length; j++) {
                dists[j] = diss.calculate(row, sampledata.getRow(j));
            }

            DenseVector distsvect = new DenseVector(dists);

            float[] proj = new float[2];
            proj[0] = distsvect.dot(V.getRow(0));
            proj[1] = distsvect.dot(V.getRow(1));

            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(proj, ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(proj, ids.get(i), classData[i]), labels.get(i));
            }
        }

        long finish = System.currentTimeMillis();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Rapid Sammon time: {0}s", (finish - start) / 1000.0f);

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private AbstractMatrix getSampleData(AbstractMatrix matrix, int samplesize) throws IOException {

        AbstractMatrix sampledata_aux = new DenseMatrix();

        //create the sample matrix data
        HashSet<Integer> sample = new HashSet<Integer>();
        Random random = new Random(System.currentTimeMillis());

        while (sample.size() < samplesize) {
            int index = (int) (random.nextFloat() * (matrix.getRowCount()));
            if (index < matrix.getRowCount()) {
                sample.add(index);
            }
        }

        //creating the sample matrix
        Iterator<Integer> it = sample.iterator();
        while (it.hasNext()) {
            int index = it.next();
            AbstractVector row = matrix.getRow(index);
            sampledata_aux.addRow(new DenseVector(row.toArray()));
        }

        return sampledata_aux;
    }
}
