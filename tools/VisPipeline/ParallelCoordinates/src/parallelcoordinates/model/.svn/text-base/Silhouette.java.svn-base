/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parallelcoordinates.model;

import java.util.ArrayList;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Fernando V. Paulovic
 */
public class Silhouette {

    public float calculate(ArrayList<AbstractInstance> instances, int[] indexes) {
        float silhouette = 0.0f;
        int size = instances.size();

        for (int i = 0; i < size; i++) {
            float ai = 0.0f;
            float bi = Float.POSITIVE_INFINITY;
            int csize = 0;

            float[] value1 = ((PolylineInstance) instances.get(i)).getOriginalValues();
            float klass1 = ((PolylineInstance) instances.get(i)).getKlass();

            for (int j = 0; j < size; j++) {
                float value2[] = ((PolylineInstance) instances.get(j)).getOriginalValues();
                float klass2 = ((PolylineInstance) instances.get(j)).getKlass();

                if (klass1 == klass2) {
                    ai += distance(value1, value2, indexes);
                    csize++;
                } else {
                    bi = Math.min(bi, distance(value1, value2, indexes));
                }
            }

            if (csize > 1) {
                ai /= csize;
                silhouette += (bi - ai) / Math.max(ai, bi);
            }
        }

        return silhouette / size;
    }

    private float distance(float[] v1, float[] v2, int[] indexes) {
        float dist = 0;
        int size = indexes.length;

        for (int i = 0; i < size; i++) {
            int index = indexes[i];

            dist += (v1[index] - v2[index]) * (v1[index] - v2[index]);
        }

        return (float) Math.sqrt(dist);
    }

}
