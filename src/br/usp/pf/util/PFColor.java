/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.usp.pf.util;

import java.awt.Color;
import visualizationbasics.color.ColorScale;
import visualizationbasics.color.BlueToYellowScale;

/**
 *
 * @author fm
 */
public class PFColor {

    public static Color calculateColor(float curEnergy, float rootEnergy) {

        float aux = curEnergy - rootEnergy + 1;
        if (aux != 0) {
            aux = 1 / aux;
            aux = (float) Math.pow(aux, 2);
        } else {
            System.err.println("Divisao por Zero, algo de errado com energia root.");
            System.exit(1);
        }

        ColorScale by = new BlueToYellowScale();

        return by.getColor(aux);

    }

}
