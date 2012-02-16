/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting.projection.plmp;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.CholeskyDecomposition;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import plmp.technique.Sampling;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.technique.idmap.IDMAPProjection;
import projection.technique.idmap.IDMAPProjection.InitializationType;

/**
 *
 * @author Fernando
 */
public class PLMPSVDProjection {

    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        //project the sample using IDMAP
        if (sampledata == null) {
            //define the sample
            if (samplesize == 0) {
                samplesize = (int) Math.sqrt(matrix.getRowCount());
            }

            samplesize = (samplesize > 2 * matrix.getDimensions()) ? samplesize : 2 * matrix.getDimensions();

            //create the sample matrix data
            sampledata = getSampleData(matrix, diss, samplesize);

            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setFractionDelta(fracdelta);
            idmap.setInitialization(InitializationType.FASTMAP);
            idmap.setNumberIterations(nriteractions);
            sampleproj = idmap.project(sampledata, diss);

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Getting the sample and creating the sample projection... "
                    + "sample size: " + samplesize);
        } else if (sampleproj == null) {
            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setFractionDelta(fracdelta);
            idmap.setInitialization(InitializationType.FASTMAP);
            idmap.setNumberIterations(nriteractions);
            sampleproj = idmap.project(sampledata, diss);

            samplesize = sampleproj.getRowCount();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Creating the sample projection... sample size: " + samplesize);
        } else {
            samplesize = sampleproj.getRowCount();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Using the given sample projection... sample size: " + samplesize);
        }

        DenseMatrix projection = new DenseMatrix();
        transf = solveUsingColt(matrix, projection);

        return projection;
    }

    /**
     * @return the fracdelta
     */
    public float getFractionDelta() {
        return fracdelta;
    }

    /**
     * @param fracdelta the fracdelta to set
     */
    public void setFractionDelta(float fracdelta) {
        this.fracdelta = fracdelta;
    }

    /**
     * @return the nriteractions
     */
    public int getNumberIteractions() {
        return nriteractions;
    }

    /**
     * @param nriteractions the nriteractions to set
     */
    public void setNumberIterations(int nriteractions) {
        this.nriteractions = nriteractions;
    }

    /**
     * @return the sampletype
     */
    public SampleType getSampleType() {
        return sampletype;
    }

    /**
     * @param sampletype the sampletype to set
     */
    public void setSampleType(SampleType sampletype) {
        this.sampletype = sampletype;
    }

    public int getSampleSize() {
        return samplesize;
    }

    public void setSampleSize(int samplesize) {
        this.samplesize = samplesize;
    }

    public void setSampleProjection(AbstractMatrix sampleproj) {
        this.sampleproj = sampleproj;
    }

    public void setSampleMatrix(AbstractMatrix samplematrix) {
        this.sampledata = samplematrix;
    }

    public DoubleMatrix2D getTransformation() {
        return transf;
    }

    private DoubleMatrix2D solveUsingColt(AbstractMatrix matrix, AbstractMatrix projection) {
        //finding A where (D'.A = P')
        int nrows = sampledata.getRowCount();
        int ncolumns = sampledata.getDimensions();

        //creating matrix D'
        DoubleMatrix2D D = new DenseDoubleMatrix2D(nrows, ncolumns);

        for (int i = 0; i < sampledata.getRowCount(); i++) {
            float[] array = sampledata.getRow(i).toArray();

            for (int j = 0; j < array.length; j++) {
                D.setQuick(i, j, array[j]);
            }
        }

        //creating P'
        DoubleMatrix2D P = new DenseDoubleMatrix2D(nrows, 2);

        for (int i = 0; i < sampledata.getRowCount(); i++) {
            float[] array = sampleproj.getRow(i).toArray();

            for (int j = 0; j < array.length; j++) {
                P.setQuick(i, j, array[j]);
            }
        }

        //solving to find A
        DoubleMatrix2D DtD = D.zMult(D, null, 1.0, 1.0, true, false);
        DoubleMatrix2D DtP = D.zMult(P, null, 1.0, 1.0, true, false);

        CholeskyDecomposition chol = new CholeskyDecomposition(DtD);
        DoubleMatrix2D result = chol.solve(DtP);

        float[][] aux_A = new float[2][];
        aux_A[0] = new float[ncolumns];
        aux_A[1] = new float[ncolumns];

        for (int i = 0; i < result.rows(); i++) {
            aux_A[0][i] = (float) result.getQuick(i, 0);
            aux_A[1][i] = (float) result.getQuick(i, 1);
        }

        DenseMatrix A = new DenseMatrix();
        A.addRow(new DenseVector(aux_A[0]));
        A.addRow(new DenseVector(aux_A[1]));

        //finally, calculating the projection (P = D.A)
        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);

            float[] proj = new float[2];
            proj[0] = row.dot(A.getRow(0));
            proj[1] = row.dot(A.getRow(1));

            projection.addRow(new DenseVector(proj, row.getId(), row.getKlass()));
        }

        return result;
    }

    private AbstractMatrix getSampleData(AbstractMatrix matrix,
            AbstractDissimilarity diss, int samplesize) throws IOException {
        //getting the sample
        Sampling sampling = new Sampling(sampletype, samplesize);
        AbstractMatrix sampledata_aux = sampling.execute(matrix, diss);

        return sampledata_aux;
    }

    private DoubleMatrix2D transf = null;
    private AbstractMatrix sampledata = null;
    private AbstractMatrix sampleproj = null;
    private float fracdelta = 8.0f;
    private int nriteractions = 50;
    private int samplesize = 0;
    private SampleType sampletype = SampleType.CLUSTERING;
}
