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
import projection.stress.StressComp;
import projection.stress.StressFactory.StressType;
import projection.util.ProjectionUtil;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 * http://www.inf.uni-konstanz.de/algo/software/mdsj/
 * @author Fernando
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "SMACOF",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "See de Leeuw, J. (1977), Applications of convex analysis to "
+ "multidimensional scaling, in Barra; Brodeau, F. & Romie, G. et al., "
+ "Recent developments in statistics, pp. 133-145. ")
public class SMACOFProjectionComp implements AbstractComponent {

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

            Data.randomize(output);
            ClassicalScaling.lmds(input, output);
            StressMinimization sm = new StressMinimization(input, output);
            sm.iterate(nriterations);

            //double[][] output = MDSJ.stressMinimization(input);

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
//        if (paramview == null) {
//            paramview = new MassiveMDSProjectionParamView(this);
//        }
//
//        return paramview;
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

    /**
     * @return the nriterations
     */
    public int getNumberIterations() {
        return nriterations;
    }

    /**
     * @param nriterations the nriterations to set
     */
    public void setNumberIterations(int nriterations) {
        this.nriterations = nriterations;
    }

    public static final long serialVersionUID = 1L;
    private int nriterations = 10;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient MassiveMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("ERROR! It should be: PivotMDSProjectionComp filename.data");
            System.exit(1);
        }

        ProjectionUtil.log(false, false);

        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename(args[0]);
            reader.execute();
            AbstractMatrix matrix = reader.output();

            System.out.println("============Starting SMACOF ============" + args[0]);

            long start = System.currentTimeMillis();
            SMACOFProjectionComp smacof = new SMACOFProjectionComp();
            smacof.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            //smacof.setNumberIterations(matrix.getRowCount() / 20);
            smacof.setNumberIterations(50);
            smacof.input(matrix);
            smacof.execute();
            AbstractMatrix projection = smacof.output();
            long finish = System.currentTimeMillis();

            StressComp stress = new StressComp();
            stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection, matrix);
            stress.execute();
            System.out.println("SMACOF stress: " + stress.output() + " time: " + (finish - start) / 1000.0f + "s");

            projection.save(args[0] + "-smacof.prj");

        } catch (IOException ex) {
            Logger.getLogger(SMACOFProjectionComp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
