/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 *
 * @author paulovich
 */
public class FrequencySelector {

    private static final int COMBSIZE = 15;

    public AbstractMatrix execute(AbstractMatrix origmatrix) throws CloneNotSupportedException {
        float[][] data = origmatrix.toMatrix();
        float[] klass = origmatrix.getClassData();

        //genarating the initial combinations
        ArrayList<Combination> inicombs = new ArrayList<Combination>();
        for (int i = 0; i < origmatrix.getDimensions(); i++) {
            Combination c = new Combination(data, klass);
            c.addIndex(i);
            inicombs.add(c);
        }
        inicombs = cutSmall(inicombs, COMBSIZE);

        //generating the combinations
        int maxsize = 30;//origmatrix.getDimensions();
        ArrayList<Combination> kcombs = new ArrayList<Combination>();
        kcombs.addAll(inicombs);

        for (int k = 0; k < maxsize; k++) {
            ArrayList<Combination> newcombs = new ArrayList<Combination>();

            int size = kcombs.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < origmatrix.getDimensions(); j++) {
                    if (!kcombs.get(i).containsIndex(j)) {
                        Combination comb = (Combination) kcombs.get(i).clone();
                        comb.addIndex(j);

                        if (!newcombs.contains(comb)) {
                            newcombs.add(comb);
                        }
                    }
                }
            }

            kcombs = cutSmall(newcombs, COMBSIZE);
            inicombs.addAll(kcombs);
        }

        inicombs = cutSmall(inicombs, COMBSIZE);

        for (int i = 0; i < inicombs.size(); i++) {
            System.out.println(inicombs.get(i));
        }
        System.out.println("---------");




        return null;
    }

    private ArrayList<Combination> cutSmall(ArrayList<Combination> array, int size) {
        size = Math.min(array.size(), size);
        Collections.sort(array);
        ArrayList<Combination> newarray = new ArrayList<Combination>();
        for (int i = 0; i < size; i++) {
            newarray.add(array.get(i));
        }
        return newarray;
    }

    class Combination implements Cloneable, Comparable<Combination> {

        public Combination(float[][] data, float[] klass) {
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
            Collections.sort(this.index);
            this.update = true;
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
            Combination clone = new Combination(data, klass);
            clone.index.addAll(index);
            clone.update = update;
            clone.silhouette = silhouette;
            return clone;
        }

        public int compareTo(Combination o) {
            silhouette();

            if (silhouette == o.silhouette) {
                return 0;
            } else if ((silhouette) > o.silhouette) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Combination) {
                final Combination other = (Combination) obj;

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

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        AbstractMatrix matrix = MatrixFactory.getInstance("/home/paulovich/Dropbox/artigos/Chu/PexSensor"
                + "/dados/allsensors(sem-agua).data");

        FrequencySelector fs = new FrequencySelector();
        fs.execute(matrix);
    }
}
