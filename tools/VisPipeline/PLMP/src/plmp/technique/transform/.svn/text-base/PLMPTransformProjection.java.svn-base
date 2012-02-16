/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique.transform;

import plmp.technique.*;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.CholeskyDecomposition;
import java.io.IOException;
import projection.technique.Projection;
import projection.technique.idmap.IDMAPProjection;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import plmp.technique.Sampling.SampleType;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PLMPTransformProjection implements Projection {

    @Override
    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        long start = System.currentTimeMillis();

        CheckConsistency cc = new CheckConsistency();

        //project the sample using IDMAP
        if (sampledata == null) {
            //define the sample
            if (samplesize == 0) {
                samplesize = (int) Math.sqrt(matrix.getRowCount());
            }

            samplesize = (samplesize > 2 * matrix.getDimensions()) ? samplesize : 2 * matrix.getDimensions();

            //create the sample matrix data
            sampledata = getSampleData(matrix, diss, samplesize);
            sampledata = cc.removeNullColumns(sampledata);
            sampledata = MakeNonSingular.execute(sampledata);

            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setFractionDelta(fracdelta);
            idmap.setInitialization(InitializationType.FASTMAP);
            idmap.setNumberIterations(nriteractions);
            sampleproj = idmap.project(sampledata, diss);

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Getting the sample and creating the sample projection... "
                    + "sample size: " + "{0}", samplesize);
        } else if (sampleproj == null) {
            IDMAPProjection idmap = new IDMAPProjection();
            idmap.setFractionDelta(fracdelta);
            idmap.setInitialization(InitializationType.FASTMAP);
            idmap.setNumberIterations(nriteractions);
            sampleproj = idmap.project(sampledata, diss);

            samplesize = sampleproj.getRowCount();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Creating the sample projection... sample size: {0}", samplesize);
        } else {
            samplesize = sampleproj.getRowCount();

            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Using the given sample projection... sample size: {0}", samplesize);
        }

        transform = createTransformation();
        AbstractMatrix projection = solveUsingColt(matrix, cc, transform);

        long finish = System.currentTimeMillis();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Part-Linear Multidimensional Projection (PLMP) time: {0}s", (finish - start) / 1000.0f);

        return projection;
    }


    private DoubleMatrix2D createTransformation() {
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
        return chol.solve(DtP);
    }

    private AbstractMatrix solveUsingColt(AbstractMatrix matrix, CheckConsistency cc, DoubleMatrix2D transform) {
        int ncolumns = sampledata.getDimensions();

        float[] aux_Ax = new float[ncolumns];
        float[] aux_Ay = new float[ncolumns];

        for (int i = 0; i < transform.rows(); i++) {
            aux_Ax[i] = (float) transform.getQuick(i, 0);
            aux_Ay[i] = (float) transform.getQuick(i, 1);
        }

        aux_Ax = cc.returnTheRemoved(aux_Ax);
        aux_Ay = cc.returnTheRemoved(aux_Ay);

        DenseMatrix A = new DenseMatrix();
        A.addRow(new DenseVector(aux_Ax));
        A.addRow(new DenseVector(aux_Ay));

        DenseMatrix projection = new DenseMatrix();

        //finally, calculating the projection (P = D.A)
        ArrayList<Integer> ids = matrix.getIds();
        float[] classData = matrix.getClassData();
        ArrayList<String> labels = matrix.getLabels();

        for (int i = 0; i < matrix.getRowCount(); i++) {
            AbstractVector row = matrix.getRow(i);

            float[] proj = new float[2];
            proj[0] = row.dot(A.getRow(0));
            proj[1] = row.dot(A.getRow(1));

            if (labels.isEmpty()) {
                projection.addRow(new DenseVector(proj, ids.get(i), classData[i]));
            } else {
                projection.addRow(new DenseVector(proj, ids.get(i), classData[i]), labels.get(i));
            }
        }

        return projection;
    }

    @Override
    public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DoubleMatrix2D getTransformation() {
        return transform;
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

    private AbstractMatrix getSampleData(AbstractMatrix matrix,
            AbstractDissimilarity diss, int samplesize) throws IOException {
        //getting the sample
        Sampling sampling = new Sampling(sampletype, samplesize);
        AbstractMatrix sampledata_aux = sampling.execute(matrix, diss);

        return sampledata_aux;
    }

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename("D:/Meus documentos/FERNANDO/Dados/viscontest/multifield.0099_4_4_4-normcols.bin");
            reader.execute();
            AbstractMatrix matrix = reader.output();

            PLMPProjectionComp mmds = new PLMPProjectionComp();
            mmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            mmds.setFractionDelta(8.0f);
            mmds.setNumberIterations(100);
            mmds.setSampleType(SampleType.RANDOM);
            //mmds.setSampleSize((int)Math.pow(matrix.getRowCount(), 0.75f));
            mmds.input(matrix);
            mmds.execute();
            AbstractMatrix projection = mmds.output();

            ProjectionModelComp model = new ProjectionModelComp();
            model.input(projection);
            model.execute();

            ProjectionFrameComp frame = new ProjectionFrameComp();
            frame.setTitle("Complete Projection");
            frame.input(model.output());
            frame.execute();
        } catch (IOException ex) {
            Logger.getLogger(PLMPTransformProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DoubleMatrix2D transform;
    private AbstractMatrix sampledata = null;
    private AbstractMatrix sampleproj = null;
    private float fracdelta = 8.0f;
    private int nriteractions = 50;
    private int samplesize = 0;
    private SampleType sampletype = SampleType.CLUSTERING;
}


