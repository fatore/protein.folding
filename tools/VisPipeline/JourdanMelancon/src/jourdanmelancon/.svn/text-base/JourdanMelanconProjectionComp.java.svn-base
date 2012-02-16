/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jourdanmelancon;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import layout.JourdanMelanconSampling;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;
import structure.DataSet;
import util.SimilarityEngine;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Joudan Melançon Hybrid MDS",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "???")
public class JourdanMelanconProjectionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            long start = System.currentTimeMillis();

            //Create the dataset with the collection and the way to compute similarity
            //measure. It is achieved by creating a new Dataset class which implements
            //the getSimilarity method
            Similarity sim = new Similarity(DissimilarityFactory.getInstance(disstype));
            DataSet dataset = new DataSet(matrix.getRows(), sim);

            //Creation of a ChalmersLinear Object
            //ChalmersLinear mds = new ChalmersLinear(dataset, width / 2, height / 2);
            //ChalmersSampling mds = new ChalmersSampling(dataset, 1000, 1000);
            JourdanMelanconSampling mds = new JourdanMelanconSampling(dataset, 1000, 1000);

            //Call the computation method
            mds.computePositionsWithoutScaling();

            //creating the final projection
            projection = new DenseMatrix();
            ArrayList<Integer> ids = matrix.getIds();
            float[] classData = matrix.getClassData();

            int size = matrix.getRowCount();
            Iterator iterator = dataset.getCollection().iterator();
            for (int i = 0; i < size; i++) {
                Object element = iterator.next();
                float[] vect = new float[2];
                vect[0] = (float) mds.getX(element);
                vect[1] = (float) mds.getY(element);
                projection.addRow(new DenseVector(vect, ids.get(i), classData[i]));
            }

            long finish = System.currentTimeMillis();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Joudan Melançon Hybrid MDS time: " + (finish - start) / 1000.0f + "s");
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

    public class Similarity extends SimilarityEngine {

        public Similarity(AbstractDissimilarity diss) {
            this.diss = diss;
        }

        public double getSimilarity(Object a, Object b) {
            return diss.calculate((AbstractVector) a, (AbstractVector) b);
        }

        private AbstractDissimilarity diss;
    }

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient MassiveMDSProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Dados\\mammals-200000-normcols.bin");
            reader.execute();
            AbstractMatrix matrix = reader.output();

            JourdanMelanconProjectionComp jmmds = new JourdanMelanconProjectionComp();
            jmmds.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            jmmds.input(matrix);
            jmmds.execute();
            AbstractMatrix projection = jmmds.output();

            ProjectionModelComp model = new ProjectionModelComp();
            model.input(projection);
            model.execute();

            ProjectionFrameComp frame = new ProjectionFrameComp();
            frame.setTitle("Complete Projection");
            frame.input(model.output());
            frame.execute();
        } catch (IOException ex) {
            Logger.getLogger(JourdanMelanconProjectionComp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
