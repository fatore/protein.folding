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
 * class to contain the collection of data items which thiss
 * package is visualising.
 * 
 */
package fsmvis.data;

import fsmvis.engine.Vect;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class DataItemCollection implements Serializable {

    protected ArrayList types;
    protected ArrayList fields;
    protected ArrayList dataItems;
    protected ArrayList columns;
    protected ArrayList maximums;
    protected ArrayList minimums;
    protected int size;
    protected boolean isNormalized;
    protected boolean normalising = false;
    protected boolean useSamples = true;
    // layout bounds, used for normalizing desired dists
    protected double layoutBounds;
    protected double mean;
    protected double sig;
    // a 2d array of doubles for the desired distances between objects
    protected double[][] desiredDist;

    // used for standard deviation normalization
    protected double[] sumOfVals;
    protected double[] sumOfSquares;
    protected double[] average;
    protected double[] sigma;
    protected double sumHDVals;
    protected double sumHDSquares;
    protected double maxDist;
    protected double minDist;
    protected double unrelatedDist;
    // used to scale ordinal values which don't match in sim metric
    protected final double ORD_FACTOR = 1.2;
    // the number of standard deviations to use when normalizing data
    protected final double STANDARD_DEVS = 2.0;
    public final static int STRING = 0;
    public final static int DATE = 1;
    public final static int INTEGER = 2;
    public final static int DOUBLE = 3;
    protected ArrayList colWeights;
    /**
     * Constructor
     */
    public DataItemCollection() {
        dataItems = new ArrayList();
        types = new ArrayList();
        fields = new ArrayList();
        maximums = new ArrayList();
        minimums = new ArrayList();
        columns = new ArrayList();
        colWeights = new ArrayList();
    }

    /**
     * adds a DataItem to this collection of dataItems
     * @param data The data item to be added
     */
    public void addItem(DataItem data) {
        dataItems.add(data);

        size++;

        Object[] vals = data.getValues();

        for (int i = 0; i < types.size(); i++) {

            //add the data to columns
            ((ArrayList) columns.get(i)).add(vals[i]);

            // also need to do some work in here to find max and mins

            if (vals[i] != null) {

                switch (((Integer) types.get(i)).intValue()) {

                    case STRING: // STRING
                        break;
                    case DATE: // DATE
                        if (((Date) vals[i]).after((Date) maximums.get(i))) {
                            maximums.set(i, vals[i]);
                        }
                        if (((Date) vals[i]).before((Date) minimums.get(i))) {
                            minimums.set(i, vals[i]);
                        }
                        break;
                    case DOUBLE: // DOUBLE
                        if (((Double) vals[i]).compareTo(
                                (Double) maximums.get(i)) > 0) {
                            maximums.set(i, vals[i]);
                        }
                        if (((Double) vals[i]).compareTo(
                                (Double) minimums.get(i)) < 0) {
                            minimums.set(i, vals[i]);
                        }
                        break;
                    case INTEGER: // INTEGER
                        if (((Integer) vals[i]).compareTo(
                                (Integer) maximums.get(i)) > 0) {
                            maximums.set(i, vals[i]);
                        }
                        if (((Integer) vals[i]).compareTo(
                                (Integer) minimums.get(i)) < 0) {
                            minimums.set(i, vals[i]);
                        }
                        break;
                    default:

                }
            }
        }
    }

    /**
     * Sets the types that this collection represents to be types
     * 
     * @param types The collection of types to be used
     */
    public void setTypes(ArrayList types) {
        this.types = types;

        //init maxs and mins
        for (int i = 0; i < types.size(); i++) {

            //init the columns too
            columns.add(new ArrayList());

            switch (((Integer) types.get(i)).intValue()) {

                case STRING: // STRING
                    maximums.add(null);
                    minimums.add(null);
                    break;
                case DATE: // DATE
                    maximums.add(new Date(0));
                    minimums.add(new Date(Long.MAX_VALUE));
                    break;
                case DOUBLE: // DOUBLE
                    maximums.add(new Double(Double.MIN_VALUE));
                    minimums.add(new Double(Double.MAX_VALUE));
                    break;
                case INTEGER: // INTEGER
                    maximums.add(new Integer(Integer.MIN_VALUE));
                    minimums.add(new Integer(Integer.MAX_VALUE));
                    break;
                default:

            }
            colWeights.add(new Double(1.0));
        }
    }

    /**
     * Returns the arrayList of types that this class contains
     *
     * @return The arrayList of types that this class contains
     */
    public ArrayList getTypes() {
        return types;
    }

    /**
     * Sets the fields that this collection represents to be fields
     *
     * @param fields The collection of fields to be used
     */
    public void setFields(ArrayList fields) {
        this.fields = fields;
    }

    /**
     * Returns the names of the fields that this collection contains
     *
     * @return The arrayList of fields
     */
    public ArrayList getFields() {
        return fields;
    }

    /**
     * Sets the values used for data normalization, the arrays contain values 
     * for each column in the data set, which contains numeric data.  the sum 
     * of all values and sum of squares of values respectively.
     *
     * @param sumOfVals The sum of all values in each column
     * @param sumOfSquares The sum of squares of all values in each column
     */
    public void setNormalizeData(double[] sumOfVals, double[] sumOfSquares) {
        this.sumOfVals = sumOfVals;
        this.sumOfSquares = sumOfSquares;

        average = new double[sumOfVals.length];
        sigma = new double[sumOfVals.length];

        //	ArrayList tempArrayList = CSVLoader.getIDF();
        for (int i = 0; i < sumOfVals.length; i++) {

            average[i] = sumOfVals[i] / getSize();

            sigma[i] = Math.sqrt((sumOfSquares[i] - ((double) getSize() * average[i] * average[i])) / (double) getSize());

        }

        DataItem.setNormalizeData(sigma, average, STANDARD_DEVS);

    }

    /**
     * Normalizes the desired distance data to be within layoutBounds, 
     * does this by normalizing by STANDARD_DEVS standard deviations.  This 
     * will make most values be between 0 and 1.  Then multiply by range size
     * and finally add the rangeLo values, which will ensure (most) values
     * are between rangeLo and rangeHi
     * 
     * 
     * @param layoutBounds
     */
    public void normalizeDesiredDists(double layoutBounds) {
        // if the data has NOT been normalized, or layoutBounds are diff,
        // proceed
        if (!isNormalized || this.layoutBounds != layoutBounds) {
            double size = ((((double) getSize() * (double) getSize()) + (double) getSize()) / 2.0) - (double) getSize();
            double mean = sumHDVals / size;
            double sigma = Math.sqrt((sumHDSquares / size) - ((sumHDVals * sumHDVals) /
                    (size * size)));
            double total = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            for (int obj1 = 1; obj1 < dataItems.size(); obj1++) {
                for (int obj2 = 0; obj2 < obj1; obj2++) {

                    double dist = getDesiredDist(obj1, obj2);

                    // normalize data if it needs it
                    if (!isNormalized) {

                        // normalize by standard deviations
                        dist = dist / (STANDARD_DEVS * sigma);

                        dist *= layoutBounds;

                    } // else, adjust the layoutBounds to be within new values
                    else if (this.layoutBounds != layoutBounds) {

                        dist = dist / this.layoutBounds;

                        dist *= layoutBounds;
                    }


                    if (dist < min) {
                        min = dist;
                    }
                    if (dist > max) {
                        max = dist;
                    }

                    total += dist;

                    desiredDist[obj1 - 1][obj2] = dist;
                }
            }

            double avg = total / size;

            unrelatedDist = 2.0 * avg;
            isNormalized = true;
            this.layoutBounds = layoutBounds;
        }
    }

    /**
     * Calculates the desired distances array by using a similarity metric
     * on every object with every other object.  uses normalization on every 
     * value as it performs this.  Creates a new desired distances object to 
     * encapsulate this data
     */
    public void calcDesiredDistances() {

        desiredDist = new double[getSize() - 1][];

        sumHDVals = 0.0;
        sumHDSquares = 0.0;


        // for every object in the list only calculate the distance to the
        // objects which come before it in the list, so when finding
        // dist(i, j) must look up data[j][i]
        for (int i = 1; i < getSize(); i++) {
            desiredDist[i - 1] = new double[i];

            // iterate over all objects that are before item1 in the list
            for (int j = 0; j < i; j++) {

                // store the distance that was reached
                //newDists.add(new Double(sumDiff));
                double sumDiff = getDataItem(i).getDistanceTo(getDataItem(j), colWeights);
                desiredDist[i - 1][j] = sumDiff;

                if (sumDiff > maxDist) {
                    maxDist = sumDiff;
                }
                if (sumDiff < minDist) {
                    minDist = sumDiff;
                }

                sumHDVals += sumDiff;
                sumHDSquares += (sumDiff * sumDiff);

            }
        }


        double size = ((((double) getSize() * (double) getSize()) + (double) getSize()) / 2.0) - (double) getSize();
        double avg = sumHDVals / size;
        double sigma = Math.sqrt((sumHDSquares / size) - ((sumHDVals * sumHDVals) /
                (size * size)));
        unrelatedDist = 2.0 * avg;
    }

    /**
     * Called when deserialized, refreshes any data item values which were 
     * not stored with serialization.  This is anything which is static, 
     * transient or was too large to be stored and can be precomputed.  
     * At moment only refreshes the normalization data
     *
     */
    public void refresh() {
        DataItem.setNormalizeData(sigma, average, STANDARD_DEVS);
    }

    /**
     * Returns the desired distance between these two objects.  Normalisation version
     *
     * @param item1 The first object in the distance relation
     * @param item2 The second object
     * @return The desired distance
     */
    public double getDesiredDist(int item1, int item2) {
        double ans;
        if (item1 > item2) {

            ans = getDataItem(item1).getDistanceTo(getDataItem(item2), colWeights);
            if (ans < 0.0) {
                ans *= -1.0;
            }
            if (!normalising) {
                return ans;
            }
            // normalise by standard deviations
            ans = ((ans - mean) / (STANDARD_DEVS * sig)) + 0.5; // +0.5 to slide along
            if (ans <= 0.0) {
                return 0.0;
            }
            if (ans >= 1.0) {
                return 1.0;
            }
            return ans;

        } else if (item1 < item2) {
            return getDesiredDist(item2, item1);
        } else {
            System.out.println("calculating distance between same obj :" + item1);
            return 0.0;
        }
    }

    /**
     * Calculates approximations of sigma and mean needed to normalise 
     * distance data by taking a sample of distances from the full data set
     *
     */
    public void calcNormValues() {
        useSamples = false;
        calcNormValues(dataItems);
    }

    /**
     * Calculates approximations of sigma and mean needed to normalise 
     * distance data by taking a sample of distances from the subset
     * @param subset The subset of values being initially laid out 
     */
    public void calcNormValues(ArrayList sample) {
        // use the sample already taken to calculate approximate max, min values
        double thisD, sum = 0.0, sqSum = 0.0, thisBig = Double.MIN_VALUE, thisSmall = Double.MAX_VALUE;

        int sampleSize = sample.size();

        int a = 1, b = 1;
        boolean equal;
        for (int q = 0; q < sampleSize; q++) {
            equal = true;
            while (equal) {
                a = (int) ((Math.random() - 0.0001) * sampleSize);
                b = (int) ((Math.random() - 0.0001) * sampleSize);
                if (a != b) {
                    equal = false;
                }
            }

            if (useSamples) {
                thisD = getDesiredDist(((Integer) sample.get(a)).intValue(), ((Integer) sample.get(b)).intValue());
            } else {
                thisD = getDesiredDist(a, b);
            }
            sum += thisD;
            sqSum += thisD * thisD;
            if (thisD > thisBig) //biggest
            {
                thisBig = thisD;
            }
            if (thisD < thisSmall) //smallest
            {
                thisSmall = thisD;
            }
        }
        // calculate approx of means, sigma
        mean = sum / sampleSize;
        sig = Math.sqrt((sqSum / sampleSize) - ((sum * sum) / (sampleSize * sampleSize)));
        normalising = true;
    }

    /**
     * Returns the average error in the system, this is the average
     * distance that a dataItem is from its desired distance
     *
     * @return The average error
     */
    public double getAverageError() {
        double error = 0.0;
        int numComps = 0;

        for (int i = 1; i < getSize(); i++) {

            for (int j = 0; j < i; j++) {

                Vect v = new Vect(getDataItem(i).getPosition(),
                        getDataItem(j).getPosition());

                double lowDist = v.getLength();
                double highDist = getDesiredDist(i, j);
                error += (lowDist - highDist);
                numComps++;
            }
        }

        return error / (double) numComps;

    }

    /**
     * Returns the average velocity of dataItems in the collection
     *
     * @return the average velocity
     */
    public double getAvgVelocity() {
        double totalVel = 0.0;

        for (int i = 0; i < getSize(); i++) {
            totalVel += getDataItem(i).getVelocity().getLength();
        }

        return totalVel / getSize();
    }

    /**
     * Accessor method for the main collection held within this class
     *
     * @return The collection that this class encapsulates
     */
    public ArrayList getDataItems() {
        return dataItems;
    }

    /**
     * Returns the data item stored at location with index int index
     *
     * @param index The index of the dataItem that is required
     * @return the data item that was stored at this location
     */
    public DataItem getDataItem(int index) {
        return (DataItem) dataItems.get(index);
    }

    /**
     * Returns the data from a specified "column".  This allows the data  
     * to be accessed from a different direction, instead of just in rows
     * by getting data items
     *
     * @param colNum The required column number
     * @return The ArrayList of data from that column
     */
    public ArrayList getColumn(int colNum) {
        return (ArrayList) columns.get(colNum);
    }

    /**
     * Returns the arraylist of the maximum values for each column in the
     * data set.  Columns with String values just contain empty objects
     *
     * @return The arraylist of maximums
     */
    public ArrayList getMaximums() {
        return maximums;
    }

    /** 
     * Returns the maximum value for a particular column, this will either
     * be of type Integer, Double or Date. It may also be an empty object if
     * the type was String.
     *
     * @param col The column number of the maximum required
     * @return The maximum value for the specified column
     */
    public Object getMaximum(int col) {
        return maximums.get(col);
    }

    /**
     * Returns the arraylist of the minimum values for each column in the
     * data set.  Columns with String values just contain empty objects
     *
     * @return The arraylist of minimums
     */
    public ArrayList getMinimums() {
        return minimums;
    }

    /** 
     * Returns the minimum value for a particular column, this will either
     * be of type Integer, Double or Date. It may also be an empty object if
     * the type was String.
     *
     * @param col The column number of the minimum required
     * @return The minimum value for the specified column
     */
    public Object getMinimum(int col) {
        return minimums.get(col);
    }

    /** 
     * Returns the size of this data item collection
     *
     * @return The size of the data collection 
     */
    public int getSize() {
        return dataItems.size();
    }

    /** 
     * Returns the number of fields in each of the records under analysis.
     *
     * @return The number of fields.
     */
    public int getNumFields() {
        return fields.size();
    }

    /**
     * Returns the distance after which two objects are considered to be 
     * unrelated.
     *
     * @return The unrelated distance
     */
    public double getUnrelatedDist() {
        return unrelatedDist;
    }

    /**
     * Reset weights of each column
     *
     * morrisaj 16.12.02 
     */
    public void updateWeights(ArrayList weights) {
        colWeights = weights;
    }

}
