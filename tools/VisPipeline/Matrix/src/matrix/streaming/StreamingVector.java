/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matrix.streaming;

import matrix.*;
import matrix.sparse.SparseVector;
import matrix.dense.DenseVector;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class StreamingVector extends AbstractVector {

    public StreamingVector(float[] vector) {
        this.create(vector, 0, 0.0f);
    }

    public StreamingVector(float[] vector, int id) {
        this.create(vector, id, 0.0f);
    }

    public StreamingVector(float[] vector, float klass) {
        this.create(vector, 0, klass);
    }

    public StreamingVector(float[] vector, int id, float klass) {
        this.create(vector, id, klass);
    }

    @Override
    public void normalize() {
        assert (this.norm() != 0.0f) : "ERROR: it is not possible to normalize a null vector!";

        if (this.norm() > DELTA) {
            int length = this.values.length;
            for (int i = 0; i < length; i++) {
                this.values[i] = this.values[i] / this.norm();
            }
            this.norm = 1.0f;
        } else {
            this.norm = 0.0f;
        }
    }

    @Override
    public float dot(AbstractVector vector) {
        assert (this.size == vector.size()) : "ERROR: vectors of different sizes!";

        float dot = 0.0f;

        if (vector instanceof DenseVector || vector instanceof StreamingVector) {
            int length = this.values.length;
            for (int i = 0; i < length; i++) {
                dot += this.values[i] * vector.getValues()[i];
            }
        } else {
            int length = ((SparseVector) vector).getIndex().length;
            for (int i = 0; i < length; i++) {
                dot += this.values[((SparseVector) vector).getIndex()[i]] * vector.getValues()[i];
            }
        }

        return dot;
    }

    @Override
    public float[] toArray() {
        float[] array = new float[this.values.length];
        System.arraycopy(this.values, 0, array, 0, this.values.length);

        return array;
    }

    @Override
    public float getValue(int index) {
        assert (index <= this.size) : "ERROR: vector can not be null!";

        return this.values[index];
    }

    @Override
    public void setValue(int index, float value) {
        assert (index <= this.size) : "ERROR: vector can not be null!";

        this.updateNorm = true;
        this.values[index] = value;
    }

    @Override
    public void write(BufferedWriter out) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void create(float[] vector, int id, float klass) {
        assert (vector != null) : "ERROR: vector can not be null!";

        this.values = vector;
        this.size = vector.length;
        this.id = id;
        this.klass = klass;

        this.updateNorm = true;
    }

    @Override
    protected void updateNorm() {
        this.norm = 0.0f;

        int length = this.values.length;
        for (int i = 0; i < length; i++) {
            this.norm += this.values[i] * this.values[i];
        }

        this.norm = (float) Math.sqrt(this.norm);
        this.updateNorm = false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if (values != null) {
            return new DenseVector(values, id, klass);
        } else {
            return new DenseVector(new float[]{0});
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DenseVector) {
            DenseVector dv = ((DenseVector) obj);

            if (this.size != dv.size()) {
                return false;
            }

            for (int i = 0; i < this.values.length; i++) {
                if (Math.abs(this.values[i] - dv.getValues()[i]) > DELTA) {
                    return false;
                }
            }

            return (Math.abs(dv.norm() - this.norm) <= DELTA);

        } else if (obj instanceof SparseVector) {
            SparseVector sv = ((SparseVector) obj);

            if (this.size != sv.size()) {
                return false;
            }

            float[] values_aux = sv.getValues();

            for (int i = 0; i < this.values.length; i++) {
                if (Math.abs(this.values[i] - values_aux[i]) > DELTA) {
                    return false;
                }
            }

            return (Math.abs(sv.norm() - this.norm) <= DELTA);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 5 + (int) this.norm;
        result += 7 * size;
        result += 7 * (int) this.klass;
        result += 3 * this.id;
        return result;
    }

}
