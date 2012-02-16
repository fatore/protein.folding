/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.Euclidean;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 *
 * @author Fernando V. Paulovic
 */
public class Silhouette {

    public static float calculate(AbstractMatrix matrix, AbstractDissimilarity diss) {
        float silhouette = 0.0f;
        int size = matrix.getRowCount();

        for (int i = 0; i < size; i++) {
            float ai = 0.0f;
            float bi = Float.POSITIVE_INFINITY;
            int csize = 0;
            float klass1 = matrix.getRow(i).getKlass();

            for (int j = 0; j < size; j++) {
                float klass2 = matrix.getRow(j).getKlass();

                if (klass1 == klass2) {
                    ai += diss.calculate(matrix.getRow(i), matrix.getRow(j));
                    csize++;
                } else {
                    bi = Math.min(bi, diss.calculate(matrix.getRow(i), matrix.getRow(j)));
                }
            }

            if (csize > 1) {
                ai /= csize;
                silhouette += (bi - ai) / Math.max(ai, bi);
            }
        }

        return silhouette / size;
    }

    public static float calculate(float[][] data, float[] klass, int[] index) {
        float silhouette = 0.0f;
        int size = data.length;

        for (int i = 0; i < size; i++) {
            float ai = 0.0f;
            float bi = Float.POSITIVE_INFINITY;
            int csize = 0;

            float[] value1 = data[i];
            float klass1 = klass[i];

            for (int j = 0; j < size; j++) {
                float value2[] = data[j];
                float klass2 = klass[j];

                if (klass1 == klass2) {
                    ai += distance(value1, value2, index);
                    csize++;
                } else {
                    bi = Math.min(bi, distance(value1, value2, index));
                }
            }

            if (csize > 1) {
                ai /= csize;
                silhouette += (bi - ai) / Math.max(ai, bi);
            }
        }

        return silhouette / size;
    }

    private static float distance(float[] v1, float[] v2, int[] index) {
        float dist = 0;
        int size = index.length;

        for (int i = 0; i < size; i++) {
            int indexaux = index[i];
            dist += (v1[indexaux] - v2[indexaux]) * (v1[indexaux] - v2[indexaux]);
        }

        return (float) Math.sqrt(dist);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> filenames = new ArrayList<String>();
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/sacarose-formatado(densidade).data-LOW.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/sacarose-formatado(densidade).data-MED.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/sacarose-formatado(densidade).data-HIGH.data");

        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/Cu2+(densidade).data-LOW.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/Cu2+(densidade).data-MED.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/Cu2+(densidade).data-HIGH.data");

        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/AM-formatado(densidade).data-LOW.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/AM-formatado(densidade).data-MED.data");
        filenames.add("D:/My Dropbox/Projetos/IC-Victor_2010/Dados_Processados/AM+Cu2+sacarose/AM-formatado(densidade).data-HIGH.data");

        for (int i = 0; i < filenames.size(); i++) {
            AbstractMatrix matrix = MatrixFactory.getInstance(filenames.get(i));
            float sil = Silhouette.calculate(matrix, new Euclidean());
            System.out.println(filenames.get(i) + " - " + sil);
        }
    }
}
