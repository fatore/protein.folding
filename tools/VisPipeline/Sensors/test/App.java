
import java.io.IOException;
import sensors.proteinfolding.GraphCreator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fm
 */


public class App {

    public static void main(String args[]) throws IOException {

        //standardizeMinimus("/home/fm/ic/data/resultados/res_baixa_hidrofobicidade/minimo.dat",
        //                   "/home/fm/ic/data/etc/res_baixa_hidrofobicidade/simple-min.dat");

        //standardizeMaximus("/home/fm/ic/data/resultados/res_baixa_hidrofobicidade/maximo.dat",
        //                   "/home/fm/ic/data/etc/res_baixa_hidrofobicidade/simple-max.dat");

        GraphCreator gc = new GraphCreator();
        gc.createJungGraph("/home/fm/ic/data/etc/res_alta_hidrofobicidade/simple-min.dat",
                           "/home/fm/ic/data/etc/res_alta_hidrofobicidade/simple-max.dat",
                           "/home/fm/ic/data/grafos/jung/res_baixa_hidrofobicidade/j-50.dat",50);

    }
}
