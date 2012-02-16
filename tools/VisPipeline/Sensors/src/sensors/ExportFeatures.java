/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

/**
 *
 * @author Fernando V. Paulovic
 */
public class ExportFeatures {

//    public AbstractMatrix exportBestFeatures(ArrayList<AbstractInstance> instances,
//            ArrayList<String> attributes) {
//        long start = System.currentTimeMillis();
//
//        AbstractMatrix matrix = new DenseMatrix();
//
//        int size = ((PolylineInstance) instances.get(0)).getOriginalValues().length;
//        ArrayList<Integer> indexes = new ArrayList<Integer>();
//        HashSet<Integer> indexesfast = new HashSet<Integer>();
//
//        int bestnrdim = 0;
//        float bestsil = Float.NEGATIVE_INFINITY;
//
//        for (int i = 0; i < size; i++) {
//            float silhouette = Float.NEGATIVE_INFINITY;
//            int index = 0;
//
//            Silhouette sil = new Silhouette();
//
//            int indexes_aux1[] = new int[indexes.size()];
//            int indexes_aux2[] = new int[indexes.size() + 1];
//            for (int k = 0; k < indexes.size(); k++) {
//                indexes_aux1[k] = indexes_aux2[k] = indexes.get(k);
//            }
//
//            for (int j = 0; j < size; j++) {
//                if (!indexesfast.contains(j)) {
//                    indexes_aux2[indexes_aux2.length - 1] = j;
//                    float value = sil.calculate(instances, indexes_aux2);
//
//                    if (value > silhouette) {
//                        silhouette = value;
//                        index = j;
//                    }
//                }
//            }
//
//            if (silhouette > bestsil) {
//                bestsil = silhouette;
//                bestnrdim = indexes.size();
//            }
//
//            indexes.add(index);
//            indexesfast.add(index);
//        }
//
////        System.out.println(bestnrdim + " <> " + bestsil);
////
////        for (int i = 0; i < bestnrdim; i++) {
////            System.out.println("feature nr.: " + indexes.get(i));
////        }
//
//        //defining the new attributes
//        if (attributes.size() > 0) {
//            ArrayList<String> newattr = new ArrayList<String>();
//            for (int i = 0; i < bestnrdim; i++) {
//                newattr.add(attributes.get(indexes.get(i)));
//            }
//            matrix.setAttributes(newattr);
//        }
//
//        for (int i = 0; i < instances.size(); i++) {
//            PolylineInstance pi = (PolylineInstance) instances.get(i);
//            float[] values = pi.getOriginalValues();
//
//            float[] vector = new float[bestnrdim];
//            for (int j = 0; j < vector.length; j++) {
//                vector[j] = values[indexes.get(j)];
//            }
//
//            matrix.addRow(new DenseVector(vector, pi.getId(), pi.getKlass()), pi.getLabel());
//        }
//
//        long finish = System.currentTimeMillis();
//        System.out.println("Exporting features time: " + (finish - start) / 1000f);
//
//        return matrix;
//    }
//
//    class Silhouette {
//
//        public float calculate(ArrayList<AbstractInstance> instances, int[] indexes) {
//            float silhouette = 0.0f;
//            int size = instances.size();
//
//            for (int i = 0; i < size; i++) {
//                float ai = 0.0f;
//                float bi = Float.POSITIVE_INFINITY;
//                int csize = 0;
//
//                float[] value1 = ((PolylineInstance) instances.get(i)).getOriginalValues();
//                float klass1 = ((PolylineInstance) instances.get(i)).getKlass();
//
//                for (int j = 0; j < size; j++) {
//                    float value2[] = ((PolylineInstance) instances.get(j)).getOriginalValues();
//                    float klass2 = ((PolylineInstance) instances.get(j)).getKlass();
//
//                    if (klass1 == klass2) {
//                        ai += distance(value1, value2, indexes);
//                        csize++;
//                    } else {
//                        bi = Math.min(bi, distance(value1, value2, indexes));
//                    }
//                }
//
//                if (csize > 1) {
//                    ai /= csize;
//                    silhouette += (bi - ai) / Math.max(ai, bi);
//                }
//            }
//
//            return silhouette / size;
//        }
//
//        private float distance(float[] v1, float[] v2, int[] indexes) {
//            float dist = 0;
//            int size = indexes.length;
//
//            for (int i = 0; i < size; i++) {
//                int index = indexes[i];
//
//                dist += (v1[index] - v2[index]) * (v1[index] - v2[index]);
//            }
//
//            return (float) Math.sqrt(dist);
//        }
//    }
}
