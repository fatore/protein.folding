/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.technique;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.Projection;

/**
 *
 * @author paulovich
 */
public class FastForceScheme2D implements Projection {

    public FastForceScheme2D() {
        this.nriterations = 50;
    }

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
         //Create the indexes and shuffle them
        ArrayList<Integer> index_aux = new ArrayList<Integer>();
        for (int i = 0; i < matrix.getRowCount(); i++) {
            index_aux.add(i);
        }

        this.index = new int[matrix.getRowCount()];
        for (int ind = 0, j = 0; j < this.index.length; ind += index_aux.size() / 10, j++) {
            if (ind >= index_aux.size()) {
                ind = 0;
            }

            this.index[j] = index_aux.get(ind);
            index_aux.remove(ind);
        }

        //if an input aux_proj is not provided, create one
        if (projection == null) {
            projection = new DenseMatrix();
            for (int i = 0; i < matrix.getRowCount(); i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random();
                point[1] = (float) Math.random();

                if (matrix.getLabels().isEmpty()) {
                    projection.addRow(new DenseVector(point, matrix.getIds().get(i),
                            matrix.getClassData()[i]));
                } else {
                    projection.addRow(new DenseVector(point, matrix.getIds().get(i),
                            matrix.getClassData()[i]), matrix.getLabels().get(i));
                }
            }
        }

        float[][] aux_proj = projection.toMatrix();

        int compsize = (int) Math.sqrt(matrix.getRowCount());
        float decfactor = 1.0f;

        for (int i = 0; i < nriterations; i++) {
            if (i < nriterations / 2) {
                decfactor = (float) (Math.pow((1 + (i * 2 + 1)), (1.0f / (i * 2 + 1))) - 1);
            } else {
                decfactor = (float) (Math.pow((1 + ((i - nriterations / 2) * 2 + 1)), (1.0f / ((i - nriterations / 2) * 2 + 1))) - 1);
            }

            iteration(matrix, diss, aux_proj, compsize, decfactor);
        }

        for (int i = 0; i < aux_proj.length; i++) {
            projection.getRow(i).setValue(0, aux_proj[i][0]);
            projection.getRow(i).setValue(1, aux_proj[i][1]);
        }

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        //Create the indexes and shuffle them
        ArrayList<Integer> index_aux = new ArrayList<Integer>();
        for (int i = 0; i < dmat.getElementCount(); i++) {
            index_aux.add(i);
        }

        this.index = new int[dmat.getElementCount()];
        for (int ind = 0, j = 0; j < this.index.length; ind += index_aux.size() / 10, j++) {
            if (ind >= index_aux.size()) {
                ind = 0;
            }

            this.index[j] = index_aux.get(ind);
            index_aux.remove(ind);
        }

        //if an input aux_proj is not provided, create one
       if (projection == null) {
            projection = new DenseMatrix();

            ArrayList<Integer> ids = dmat.getIds();
            float[] classData = dmat.getClassData();
            ArrayList<String> labels = dmat.getLabels();


            for (int i = 0; i < dmat.getElementCount(); i++) {
                float[] point = new float[2];
                point[0] = (float) Math.random();
                point[1] = (float) Math.random();

                if (labels.isEmpty()) {
                    projection.addRow(new DenseVector(point, ids.get(i), classData[i]));
                } else {
                    projection.addRow(new DenseVector(point, ids.get(i), classData[i]), labels.get(i));
                }
            }
        }

        float[][] aux_proj = projection.toMatrix();

        int compsize = (int) Math.sqrt(dmat.getElementCount());
        float decfactor = 1.0f;

        for (int i = 0; i < nriterations; i++) {
            if (i < nriterations / 2) {
                decfactor = (float) (Math.pow((1 + (i * 2 + 1)), (1.0f / (i * 2 + 1))) - 1);
            } else {
                decfactor = (float) (Math.pow((1 + ((i - nriterations / 2) * 2 + 1)), (1.0f / ((i - nriterations / 2) * 2 + 1))) - 1);
            }

            iteration(dmat, aux_proj, compsize, decfactor);
        }

        for (int i = 0; i < aux_proj.length; i++) {
            projection.getRow(i).setValue(0, aux_proj[i][0]);
            projection.getRow(i).setValue(1, aux_proj[i][1]);
        }

        return projection;
    }

    private void iteration(DistanceMatrix dmat, float[][] aux_proj, int compsize, float decfactor) {
        //for each instance
        for (int ins1 = 0; ins1 < dmat.getElementCount(); ins1++) {
            int instance = index[ins1];

            //for each other instance
            for (int ins2 = 0; ins2 < compsize; ins2++) {
                int instance2 = (int) (dmat.getElementCount() * Math.random());

                if (instance == instance2) {
                    continue;
                }

                //distance between projected instances
                double x1x2 = (aux_proj[instance2][0] - aux_proj[instance][0]);
                double y1y2 = (aux_proj[instance2][1] - aux_proj[instance][1]);
                double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);

                if (dr2 < EPSILON) {
                    dr2 = EPSILON;
                }

                float drn = dmat.getDistance(instance, instance2);

                //Calculating the (fraction of) delta
                double delta = Math.sqrt(drn) - Math.sqrt(dr2);
                delta *= Math.abs(delta);
                delta *= decfactor;

                //moving ins2 -> ins1
                aux_proj[instance2][0] += delta * (x1x2 / dr2);
                aux_proj[instance2][1] += delta * (y1y2 / dr2);

                if (aux_proj[instance2][0] != aux_proj[instance2][0]) {
                    System.out.println("Error: Force Scheme >> delta" + delta + ", x1x2=" + x1x2 + ", dr2=" + dr2 + ", drn=" + drn);
                }
            }
        }
    }

     private void iteration(AbstractMatrix matrix, AbstractDissimilarity diss, float[][] aux_proj, int compsize, float decfactor) {
        //for each instance
        for (int ins1 = 0; ins1 < matrix.getRowCount(); ins1++) {
            int instance = index[ins1];

            //for each other instance
            for (int ins2 = 0; ins2 < compsize; ins2++) {
                int instance2 = (int) (matrix.getRowCount() * Math.random());

                if (instance == instance2) {
                    continue;
                }

                //distance between projected instances
                double x1x2 = (aux_proj[instance2][0] - aux_proj[instance][0]);
                double y1y2 = (aux_proj[instance2][1] - aux_proj[instance][1]);
                double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);

                if (dr2 < EPSILON) {
                    dr2 = EPSILON;
                }

                float drn = diss.calculate(matrix.getRow(instance), matrix.getRow(instance2));

                //Calculating the (fraction of) delta
                double delta = Math.sqrt(drn) - Math.sqrt(dr2);
                delta *= Math.abs(delta);
                delta *= decfactor;

                //moving ins2 -> ins1
                aux_proj[instance2][0] += delta * (x1x2 / dr2);
                aux_proj[instance2][1] += delta * (y1y2 / dr2);

                if (aux_proj[instance2][0] != aux_proj[instance2][0]) {
                    System.out.println("Error: Force Scheme >> delta" + delta + ", x1x2=" + x1x2 + ", dr2=" + dr2 + ", drn=" + drn);
                }
            }
        }
    }

    /**
     * @param nriterations the nriterations to set
     */
    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    /**
     * @param aux_proj the aux_proj to set
     */
    public void setProjection(AbstractMatrix projection) {
        this.projection = projection;
    }

    private int[] index;
    private int nriterations;
    private AbstractMatrix projection;
    private static final float EPSILON = 0.0000001f;
}
