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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
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
name = "Landmark MDS (L-MMDS)",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "V. de Silva, J.B. Tenenbaum, Sparse multidimensional scaling "
+ "using landmark points, Technical Report, Stanford University, 2004.")
public class LandmarkMDSProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            int size = matrix.getRowCount();
            int samplesize = (int) Math.sqrt(matrix.getRowCount());

            //create the sample matrix data
            HashSet<Integer> sample = new HashSet<Integer>();
            Random random = new Random(System.currentTimeMillis());

            while (sample.size() < samplesize) {
                int index = (int) (random.nextFloat() * (matrix.getRowCount()));
                if (index < matrix.getRowCount()) {
                    sample.add(index);
                }
            }

            //creating the landmaks distance matrix
            double[][] input = new double[samplesize][size];

            Iterator<Integer> iterator = sample.iterator();
            int k = 0;
            while (iterator.hasNext()) {
                int index = iterator.next();
                AbstractVector row1 = matrix.getRow(index);

                for (int j = 0; j < size; j++) {
                    AbstractVector row2 = matrix.getRow(j);
                    float dist = diss.calculate(row1, row2);
                    input[k][j] = dist;
                }

                k++;
            }

            double[][] output = new double[2][size];
            ClassicalScaling.lmds(input, output);

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

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient MassiveMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("ERROR! It should be: LandmarkMDSProjectionComp filename.data");
            System.exit(1);
        }

        ProjectionUtil.log(false, false);

        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename(args[0]);
            reader.execute();
            AbstractMatrix matrix = reader.output();

            System.out.println("============Starting Landmarks MDS ============" + args[0]);
            float stress_value = Float.POSITIVE_INFINITY;
            float time = 0;

            for (int i = 0; i < 10; i++) {
                long start = System.currentTimeMillis();
                LandmarkMDSProjectionComp landmarkmds = new LandmarkMDSProjectionComp();
                landmarkmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
                landmarkmds.input(matrix);
                landmarkmds.execute();
                AbstractMatrix projection = landmarkmds.output();
                long finish = System.currentTimeMillis();

                StressComp stress = new StressComp();
                stress.setDissimilarityType(DissimilarityType.EUCLIDEAN);
                stress.setStressType(StressType.NORMALIZED_KRUSKAL);
                stress.input(projection, matrix);
                stress.execute();

                if (stress_value > stress.output()) {
                    stress_value = stress.output();
                    time = (finish - start) / 1000.0f;
                    projection.save(args[0] + "-landmarksmds.prj");
                }

                System.out.println("Landmarks MDS  time: " + (finish - start) / 1000.0f + "s - stress: " + stress.output());
            }

            System.out.println("Landmarks MDS  projection time: " + time + "s");
            System.out.println("Landmarks MDS projection stress: " + stress_value);

        } catch (IOException ex) {
            Logger.getLogger(LandmarkMDSProjectionComp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
