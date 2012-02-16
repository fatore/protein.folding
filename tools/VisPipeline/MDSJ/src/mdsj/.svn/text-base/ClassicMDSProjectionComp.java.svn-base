/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdsj;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import projection.model.ProjectionModelComp;
import projection.stress.StressComp;
import projection.stress.StressFactory.StressType;
import projection.view.ProjectionFrameComp;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 * http://www.inf.uni-konstanz.de/algo/software/mdsj/
 * @author Fernando
 */
@VisComponent(hierarchy = "Projection.Technique.MDSJ",
name = "Classic Scaling",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "???")
public class ClassicMDSProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            int size = matrix.getRowCount();

            //creating the squared distance matrix
            double[][] input = new double[size][size];

            for (int i = 0; i < size; i++) {
                AbstractVector row1 = matrix.getRow(i);
                for (int j = 0; j < size; j++) {
                    AbstractVector row2 = matrix.getRow(j);
                    float dist = diss.calculate(row1, row2);
                    input[i][j] = input[j][i] = dist;
                }
            }

            double[][] output = new double[2][size];
            ClassicalScaling.fullmds(input, output);

            //creating the final projection
            projection = new DenseMatrix();
            ArrayList<Integer> ids = matrix.getIds();
            float[] classData = matrix.getClassData();

            for (int i = 0; i < size; i++) {
                float[] vect = new float[2];
                vect[0] = (float) output[0][i];
                vect[1] = (float) output[1][i];
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]));
            }
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public AbstractMatrix output() {
        return projection;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
    }

    /**
     * @return the disstype
     */
    public DissimilarityType getDissimilarityType() {
        return disstype;
    }

    /**
     * @param disstype the disstype to set
     */
    public void setDissimilarityType(DissimilarityType diss) {
        this.disstype = diss;
    }

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient MassiveMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\mammals-10000-normcols.bin");
            reader.execute();
            AbstractMatrix matrix = reader.output();

            System.out.println("===>>>> ");
            long start = System.currentTimeMillis();
            ClassicMDSProjectionComp mds = new ClassicMDSProjectionComp();
            mds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            mds.input(matrix);
            mds.execute();
            AbstractMatrix projection = mds.output();
            long finish = System.currentTimeMillis();

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection, matrix);
            stress.execute();
            System.out.println("stress: " + stress.output() + " time: " + (finish - start) / 1000.0f + "s");

            ProjectionModelComp model = new ProjectionModelComp();
            model.input(projection);
            model.execute();

            ProjectionFrameComp frame = new ProjectionFrameComp();
            frame.setTitle("Complete Projection");
            frame.input(model.output());
            frame.execute();
        } catch (IOException ex) {
            Logger.getLogger(SMACOFProjectionComp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

