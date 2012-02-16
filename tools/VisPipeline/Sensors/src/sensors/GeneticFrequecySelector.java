/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;

/**
 *
 * @author PC
 */
public class GeneticFrequecySelector {

    public GeneticFrequecySelector() {
        rand = new Random(System.currentTimeMillis());
    }

    public AbstractMatrix execute(AbstractMatrix origmatrix, int nrdim) {
        long start = System.currentTimeMillis();

        float[][] data = origmatrix.toMatrix();
        float[] klass = origmatrix.getClassData();

        //defining the population size
        int popsize = origmatrix.getDimensions() / nrdim;

        //generating the initial population        
        ArrayList<Cromossome> inipop = new ArrayList<Cromossome>();
        for (int i = 0, k = 0; i < popsize; i++) {
            Cromossome c = new Cromossome(data, klass);

            for (int j = 0; j < nrdim; j++) {
                c.addIndex(k++);
            }

            inipop.add(c);
        }

        for (int k = 0; k < 20; k++) {
            ArrayList<Cromossome> newpop = new ArrayList<Cromossome>();

            for (int i = 0; i < inipop.size(); i++) {
                for (int j = i + 1; j < inipop.size(); j++) {
                    //performing the cross-over
                    ArrayList<Cromossome> cross = new ArrayList<Cromossome>();
                    inipop.get(i).crossover(inipop.get(j), cross);
                    newpop.add(cross.get(0));
                    newpop.add(cross.get(1));
                }

                //performing the mutation
                if (rand.nextFloat() < 0.05) {
                    Cromossome mut = inipop.get(i).mutation();
                    newpop.add(mut);
                }
            }

            for (int i = 0; i < newpop.size(); i++) {
                if (!inipop.contains(newpop.get(i))) {
                    inipop.add(newpop.get(i));
                }
            }

            inipop = cutSmall(inipop, popsize);
        }

        //getting the index of the best set of features
        ArrayList<Integer> index = inipop.get(0).index;
        Collections.sort(index);

        for (Integer i : index) {
            System.out.println(i);
        }

        //creating the new matrix
        AbstractMatrix matrix = new DenseMatrix();

        ArrayList<String> attributes = origmatrix.getAttributes();
        //defining the new attributes
        if (attributes.size() > 0) {
            ArrayList<String> newattr = new ArrayList<String>();
            for (int i = 0; i < index.size(); i++) {
                newattr.add(attributes.get(index.get(i)));
            }
            matrix.setAttributes(newattr);
        }

        for (int i = 0; i < origmatrix.getRowCount(); i++) {
            AbstractVector row = origmatrix.getRow(i);
            float[] values = row.toArray();

            float[] vector = new float[index.size()];
            for (int j = 0; j < vector.length; j++) {
                vector[j] = values[index.get(j)];
            }

            matrix.addRow(new DenseVector(vector, row.getId(), row.getKlass()), origmatrix.getLabel(i));
        }

        long finish = System.currentTimeMillis();
        System.out.println("Final silhouette: " + inipop.get(0).silhouette
                + " - Exporting features time: " + (finish - start) / 1000f);

        return matrix;
    }

    private ArrayList<Cromossome> cutSmall(ArrayList<Cromossome> array, int size) {
        size = Math.min(array.size(), size);
        Collections.sort(array);
        ArrayList<Cromossome> newarray = new ArrayList<Cromossome>();
        for (int i = 0; i < size; i++) {
            newarray.add(array.get(i));
        }
        return newarray;
    }

    class Cromossome implements Cloneable, Comparable<Cromossome> {

        public Cromossome(float[][] data, float[] klass) {
            this.data = data;
            this.klass = klass;
            this.index = new ArrayList<Integer>();
            this.update = false;
            this.silhouette = 0;
        }

        public boolean containsIndex(int index) {
            return this.index.contains(index);
        }

        public void addIndex(int index) {
            this.index.add(index);
            this.update = true;
            Collections.sort(this.index);
        }

        public Cromossome mutation() {
            Cromossome mut = null;

            try {
                mut = (Cromossome) clone();
                int newindex = (int) (mut.data[0].length * rand.nextFloat());
                if (mut.index.size() > 0 && !mut.containsIndex(newindex)) {
                    int aux = (int) (mut.index.size() * rand.nextFloat());
                    mut.index.set(aux, newindex);
                    mut.update = true;
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(GeneticFrequecySelector.class.getName()).log(Level.SEVERE, null, ex);
            }

            return mut;
        }

        public void crossover(Cromossome cromo, ArrayList<Cromossome> gen) {
            try {
                Cromossome gen1 = (Cromossome) clone();
                Cromossome gen2 = (Cromossome) cromo.clone();

                for (int i = 0; i < gen1.index.size(); i++) {
                    if (rand.nextBoolean()) {
                        int gen1index = gen1.index.get(i);
                        int gen2index = gen2.index.get(i);

                        gen1.index.set(i, gen2index);
                        gen2.index.set(i, gen1index);
                    }
                }

                gen1.update = true;
                gen2.update = true;

                gen.add(gen1);
                gen.add(gen2);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(GeneticFrequecySelector.class.getName()).log(Level.SEVERE, null, ex);
            }


//            try {
//                Cromossome gen1 = (Cromossome) clone();
//                Cromossome gen2 = (Cromossome) cromo.clone();
//
//                int partindex = (int) (gen1.index.size() * rand.nextFloat());
//
//                for (int i = partindex; i < gen1.index.size(); i++) {
//                    int gen1index = gen1.index.get(i);
//                    int gen2index = gen2.index.get(i);
//
//                    gen1.index.set(i, gen2index);
//                    gen2.index.set(i, gen1index);
//                }
//
//                gen1.update = true;
//                gen2.update = true;
//
//                gen.add(gen1);
//                gen.add(gen2);
//            } catch (CloneNotSupportedException ex) {
//                Logger.getLogger(GeneticFrequecySelector.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        public float silhouette() {
            if (update) {
                int[] indexaux = new int[index.size()];
                for (int i = 0; i < indexaux.length; i++) {
                    indexaux[i] = index.get(i);
                }

                silhouette = Silhouette.calculate(data, klass, indexaux);
            }

            return silhouette;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Cromossome clone = new Cromossome(data, klass);
            clone.index.addAll(index);
            clone.update = update;
            clone.silhouette = silhouette;
            return clone;
        }

        public int compareTo(Cromossome o) {
            silhouette();
            o.silhouette();

            if (silhouette == o.silhouette) {
                return 0;
            } else if (silhouette > o.silhouette) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cromossome) {
                final Cromossome other = (Cromossome) obj;

                if (index.size() != other.index.size()) {
                    return false;
                }

                for (int i = 0; i < index.size(); i++) {
                    if (index.get(i) != other.index.get(i)) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + (this.index != null ? this.index.hashCode() : 0);
            return hash;
        }

        @Override
        public String toString() {
            silhouette();

            String out = silhouette + " <> ";
            for (int i = 0; i < index.size(); i++) {
                out += index.get(i) + ",";
            }
            return out;
        }
        private float silhouette;
        private boolean update;
        private ArrayList<Integer> index;
        private float[] klass;
        private float[][] data;
    }
    private Random rand;

    public static void main(String args[]) throws IOException, CloneNotSupportedException {
        AbstractMatrix matrix = MatrixFactory.getInstance("D:/My Dropbox/artigos/Chu/PexSensor/dados/PAHPVS-imag(sem-agua).data-CAPACITANCE.data");
        GeneticFrequecySelector gfs = new GeneticFrequecySelector();
        //gfs.test(matrix);
        gfs.execute(matrix, 10).save("D:/My Dropbox/artigos/Chu/PexSensor/dados/PAHPVS-imag(sem-agua).data-CAPACITANCE-SELGEN-NEW.data");
    }
}
