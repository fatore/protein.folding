/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is FSMvis .
 *
 * The Initial Developer of the Original Code is
 * Alistair Morrison, Greg Ross.
 * Portions created by the Initial Developer are Copyright (C) 2000-2002
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s): Matthew Chalmers <matthew@dcs.gla.ac.uk>
 *                 Alistair Morrison <morrisaj@dcs.gla.ac.uk>
 *                 Greg Ross <gr@dcs.gla.ac.uk>
 *                 Andrew Didsbury
 *	
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

/**
 * FSM visualiser
 * 
 * DataItem
 * abstract class to represent a single data item in the visualiser,
 * also represents the position, force and velocity of that data item
 * 
 */
package fsmvis.data;

import fsmvis.engine.Coordinate;
import fsmvis.engine.Vect;
import java.util.Date;
import java.util.ArrayList;
import java.awt.Color;
import java.io.Serializable;

public class DataItem implements Serializable {

    protected Object[] values;
    protected Coordinate position;
    protected Vect force;
    protected Vect velocity;
    protected Color color;
    protected static double[] sigma;
    protected static double[] average;
    protected static double stdDevs;
    protected static ArrayList idfValues; // GR
    public static final Color DEFAULT_COLOR = Color.white;
    protected final double ORD_FACTOR = 0.75;
    
    /**
     * constructor: takes an array of values
     *
     * @param values The array of object values
     */
    public DataItem(Object[] values) {
        this.values = values;
        position = new Coordinate();
        force = new Vect();
        velocity = new Vect();
        color = DEFAULT_COLOR;
    //	idfValues = CSVLoader.getIDF();
    }

    public static double[] sigma() {
        return sigma;
    }

    public static double[] average() {
        return average;
    }

    public static double stdDevs() {
        return stdDevs;
    }

    /**
     * Returns the collection of values that this DataItem contains
     *
     * @return The collection of values in this DataItem
     */
    public Object[] getValues() {
        return values;
    }

    /**
     * Returns the current position coordinate of this data item
     *
     * @return The current position of this data item
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Sets the layout position for this object to be the point
     *
     * @param position The coordinate for the position of this object
     */
    public void setPosition(Coordinate position) {
        this.position = position;
    }

    /**
     * Returns the vector force object associated with this object
     *
     * @return The current forces acting on this object
     */
    public Vect getForce() {
        return force;
    }

    /**
     * Sets the forces associated with this object to be force
     * 
     * @param force The new force value to associate with this object
     */
    public void setForce(Vect force) {
        this.force = force;
    }

    /**
     * Returns the current velocity of this object 
     *
     * @return The velocity of this data item
     */
    public Vect getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of this object to be the new value velocity
     * 
     * @param velocity The new velocity of this object
     */
    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    /**
     * Returns the color of this data item
     *
     * @return The color of this data item
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this data item
     *
     * @param color the new color for this data item
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Calculates the distance metric to another data item
     *
     * @param other
     * @return the distance to this other data item
     */
    public double getDistanceTo(DataItem other, ArrayList colWeights) {
        if (other == null) {
            System.out.println("other = null!!!");
        }
        
        double val1, val2 = 0.0;
        double sumDiff = 0.0;
        double ordDiff = 1.0;

        int cols = 0;

        val1 = 0.0;
        val2 = 0.0;

        Object[] otherVals = other.getValues();

        //iterate through values while calcing distance between vars
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Date && otherVals[i] instanceof Date) {
                val1 = (double) ((Date) values[i]).getTime();
                val2 = (double) ((Date) otherVals[i]).getTime();

                if (sigma[i] != 0) {
                    val1 = (val1 - average[i]) / (stdDevs * sigma[i]);
                    val2 = (val2 - average[i]) / (stdDevs * sigma[i]);
                }
                sumDiff += (((val1 - val2) * (val1 - val2)) * ((Double) colWeights.get(i)).doubleValue());

                cols++;

            } else if (values[i] instanceof String && otherVals[i] instanceof String) {
                if (((String) values[i]).compareTo((String) otherVals[i]) == 0) { //if strings are identical
                    double thisColWeight = ((Double) colWeights.get(i)).doubleValue();
                    double multiplier = ORD_FACTOR;
                    if (thisColWeight > 1.0) {
                        multiplier *= (1.0 / thisColWeight);
                    } else if (thisColWeight < 1.0 && thisColWeight > 0.0) {
                        multiplier += (0.25 * (1 - thisColWeight));
                    }
                    ordDiff *= multiplier;
                }
                cols++;

            } else if (values[i] instanceof Integer && otherVals[i] instanceof Integer) {
                val1 = (double) ((Integer) values[i]).intValue();
                val2 = (double) ((Integer) otherVals[i]).intValue();

                if (sigma[i] != 0) {
                    val1 = (val1 - average[i]) / (stdDevs * sigma[i]);
                    val2 = (val2 - average[i]) / (stdDevs * sigma[i]);
                }
                sumDiff += (((val1 - val2) * (val1 - val2)) * ((Double) colWeights.get(i)).doubleValue());

                cols++;
            } else if (values[i] instanceof Double && otherVals[i] instanceof Double) {
                val1 = ((Double) values[i]).doubleValue();
                val2 = ((Double) otherVals[i]).doubleValue();

                if (sigma[i] != 0) {
                    val1 = (val1 - average[i]) / (stdDevs * sigma[i]);
                    val2 = (val2 - average[i]) / (stdDevs * sigma[i]);
                }
                // sum of square of diffs
                sumDiff += (((val1 - val2) * (val1 - val2)) * ((Double) colWeights.get(i)).doubleValue());

                cols++;
            }
        }

        sumDiff = Math.sqrt(sumDiff);
        sumDiff *= ordDiff;

        //scale by the number of valid column values that existed.
        sumDiff *= (double) values.length / (double) cols;

        return sumDiff;
    }

    /**
     * sets up the normalization data that all data item will use
     *
     * @param sigma
     * @param average
     * @param stdDevs
     */
    public static void setNormalizeData(double[] sigma,
            double[] average, double stdDevs) {
        DataItem.sigma = sigma;
        DataItem.average = average;
        DataItem.stdDevs = stdDevs;
    }

}
