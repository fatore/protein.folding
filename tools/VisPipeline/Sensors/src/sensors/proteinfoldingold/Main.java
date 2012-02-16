/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors.proteinfoldingold;

import sensors.proteinfolding.InputStandardizer;
import java.io.IOException;


/**
 *
 * @author fm
 */
public class Main {

    public static void main ( String[] args) throws IOException {
        //InputStandardizer.standardizeMinimus("/home/fm/ic/data/Resultados/minimos.dat", "/home/fm/ic/data/etc/simple-min.dat");
        InputStandardizer.standardizeMaximus("/home/fm/ic/data/Resultados/maximos.dat", "/home/fm/ic/data/etc/simple-max.dat");
    }

}
