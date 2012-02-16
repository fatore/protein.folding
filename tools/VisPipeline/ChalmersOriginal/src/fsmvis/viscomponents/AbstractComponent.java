/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fsmvis.viscomponents;

import fsmvis.data.DataItem;
import fsmvis.data.DataItemCollection;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class AbstractComponent implements vispipelinebasics.interfaces.AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new ComponentParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
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

    /**
     * @return the freeness
     */
    public float getFreeness() {
        return freeness;
    }

    /**
     * @param freeness the freeness to set
     */
    public void setFreeness(float freeness) {
        this.freeness = freeness;
    }

    /**
     * @return the springforce
     */
    public float getSpringForce() {
        return springforce;
    }

    /**
     * @param springforce the springforce to set
     */
    public void setSpringForce(float springforce) {
        this.springforce = springforce;
    }

    /**
     * @return the dampingfactor
     */
    public float getDampingFactor() {
        return dampingfactor;
    }

    /**
     * @param dampingfactor the dampingfactor to set
     */
    public void setDampingFactor(float dampingfactor) {
        this.dampingfactor = dampingfactor;
    }

    public int getNumberInstances() {
        if (matrix != null) {
            return matrix.getRowCount();
        }

        return 0;
    }

    protected AbstractMatrix createProjection(AbstractMatrix matrix, DataItemCollection data) {
        float[] cdata = matrix.getClassData();
        ArrayList<Integer> ids = matrix.getIds();

        AbstractMatrix proj = new DenseMatrix();

        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add("x");
        attributes.add("y");
        proj.setAttributes(attributes);

        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] vect = new float[2];
            vect[0] = (float) data.getDataItem(i).getPosition().x;
            vect[1] = (float) data.getDataItem(i).getPosition().y;

            AbstractVector vector = new DenseVector(vect, ids.get(i), cdata[i]);
            proj.addRow(vector);
        }

        return proj;
    }

    protected DataItemCollection createDataCollection(AbstractMatrix matrix) {
        DataItemCollection data = new DataItemCollection();

        ArrayList fields = new ArrayList();
        ArrayList types = new ArrayList();

        for (int i = 0; i < matrix.getDimensions(); i++) {
            fields.add("f");
            types.add(new Integer(DataItemCollection.DOUBLE));
        }

        data.setFields(fields);
        data.setTypes(types);

        double[] sumOfVals = new double[fields.size()];
        double[] sumOfSquares = new double[fields.size()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            float[] array = matrix.getRow(i).toArray();

            ArrayList values = new ArrayList();
            for (int j = 0; j < array.length; j++) {
                values.add((double) array[j]);
                sumOfVals[j] += array[j];
                sumOfSquares[j] += array[j] * array[j];

            }

            DataItem item = new DataItem(values.toArray());
            data.addItem(item);
        }

        //normalize the data
        data.setNormalizeData(sumOfVals, sumOfSquares);

        return data;
    }

    public static final long serialVersionUID = 1L;
    protected int nriterations = 50;
    protected float freeness = 0.5f;
    protected float springforce = 0.6f;
    protected float dampingfactor = 0.2f;
    protected transient ComponentParamView paramview;
    protected transient AbstractMatrix projection;
    protected transient AbstractMatrix matrix;
}
