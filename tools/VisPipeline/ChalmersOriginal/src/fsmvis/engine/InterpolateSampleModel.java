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
 * InterpolateSampleModel
 * 
 * Lays out a sample of data objects, then interpolates the other points in 
 * between these by putting them near to points that they are most similar to
 * This should form a good initial layout of points
 *
 */
package fsmvis.engine;

import fsmvis.data.DataItem;
import fsmvis.data.DataItemCollection;
import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.gui.Viewer;
import fsmvis.utils.Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class InterpolateSampleModel extends SubsetNeighbourAndSampleModel {

    protected ArrayList origSubset;
    protected ArrayList interpSubset;
    protected int interpSubsetSize;
    protected HashSet interpSubsetContents;
    protected ArrayList interpSubsetNums;
    protected int dataSampleSize = 20;
    protected int circumfSamples = 10;
    protected int vectorIterations = 20;
    protected int vectorSamples = 10;
    protected double lowError = 0.001;
    protected EndCriteria endC;
    protected long time = 0;
    protected boolean bStable = false;
    protected boolean notAfters = true;
    protected boolean isInterpolating = false;
    protected ArrayList subsetToInterp;
    protected int currentInterp = -1;

    // these constants define the interpolation of data objects
    protected final static String DATA_SAMPLE_SIZE = "dataSampleSize";
    protected final static String CIRCUMF_SAMPLES = "circumfSamples";
    protected final static String VECTOR_ITERATIONS = "vectorIterations";
    protected final static String VECTOR_SAMPLES = "vectorSamples";
    protected final static String MAX_ITERATIONS = "maxIterations";
    protected final static String LOW_VELOCITY = "lowVelocity";
    protected final static String LOW_ERROR = "lowError";
    protected boolean subOfSub;//= true;
    /**
     * constructor:
     *
     * @param dataItems The dataset to be used with this collection
     */
    public InterpolateSampleModel(DataItemCollection dataItems, EndCriteria ec, Viewer v) {
        super(dataItems, ec, v);

        //make a copy of the original subset, for interpolate calcs
        origSubset = new ArrayList(subset);
        interpSubsetSize = (int) Math.sqrt(subsetSize);
        //System.out.println("SubsetSize:  " + subsetSize);
        if (interpSubsetSize < 10 && subsetSize > 10) //have minimum interpSubset size = 10 (unless this is bigger than origSubset size)
        {
            interpSubsetSize = 10;
        }
        //System.out.println("IntSubSize:  " + interpSubsetSize);
        interpSubsetContents = new HashSet(interpSubsetSize);
        interpSubset = new ArrayList(interpSubsetSize);

        interpSubsetNums = Utils.createRandomSample(null, new HashSet(subset), subset.size(), interpSubsetSize);
        //System.out.print("subset of subset:  ");
        for (int i = 0; i < interpSubsetSize; i++) {
            interpSubset.add(subset.get(((Integer) interpSubsetNums.get(i)).intValue()));
            //System.out.print(((Integer) subset.get(((Integer) interpSubsetNums.get(i)).intValue())).intValue() + "  ");
        }
//        if (subOfSub) {
//            System.out.print("  (using)");
//        }
//        System.out.println();

        subsetToInterp = Utils.createRandomSample(null, new HashSet(subset), dataItems.getSize(), (dataItems.getSize() - subsetSize));

        endC = ec;
        endC.setN(subset.size());
    }

    /**
     * Initialises the properties for this object to be the properties held 
     * for this object
     *
     */
    public void loadProperties() {
        super.loadProperties();

        // initialise the properties
        try {

            PropertiesHandler propHandler =
                    new PropertiesHandler(this, "InterpolateSampleModel");

            properties.putAll(propHandler.getProperties());

        } catch (NoPropertiesException npe) {

            System.err.println("couldn't load the properties for " +
                    " InterpolateSampleModel");
            npe.printStackTrace();
        }

    }

    /**
     *  Instruction to normalise data for spring model
     */
    public void doNormalise() {
        super.doNormalise();
    }

    /**
     * Initialises the values for this class, over rides the method in Spring
     * model.  Makes a call to super first.
     *
     */
    public void initValues() {
        super.initValues();


        dataSampleSize = Integer.parseInt(properties.getProperty(DATA_SAMPLE_SIZE));
        circumfSamples = Integer.parseInt(properties.getProperty(CIRCUMF_SAMPLES));
        //vectorIterations = Integer.parseInt(properties.
        //				    getProperty( VECTOR_ITERATIONS));
        vectorSamples = Integer.parseInt(properties.getProperty(VECTOR_SAMPLES));
        //maxIterations    = Integer.parseInt(properties.
        //			    getProperty( MAX_ITERATIONS ));

        lowError = Double.parseDouble(properties.getProperty(LOW_ERROR));
    }

    /**
     * Method to perform one iteration of the layout algorithm for this
     * layout model
     */
    @Override
    public void doIteration() throws TooManyIterationsException {
        if (numIterations < (endC.getMaxIterations() - 1)) {
//            System.out.println("projecting the subset:" + numIterations);
            super.doIteration();
        } else {
//           System.out.println("interpolating");

            loopInterpolateDataItems();

            for (int i = 0; i < 5; i++) {
//                System.out.println("projecting full set:" + i);
                super.iterateFullSet();
            }

//            System.out.println("FINISHED interpolating");
        }

//        double avg;
//        //if not done enough iterations
//        double avgVel = 1000.0;
//        if (numIterations % 10 == 0 && !isInterpolating) {
//            avgVel = getAvgVelocity();
//        }
//        // INITIAL ITERATIONS
//        if ((notAfters && (!isInterpolating)) && !endC.isThisEnd(numIterations, avgVel)) {
//            System.out.println(numIterations);
//
//
//            super.doIteration();
//        } // INTERPOLATION
//        else if (subset.size() < dataItems.getSize()) {
//            if (!isInterpolating) {
//                System.out.println("Interp start");
//                isInterpolating = true;
//            }
//            loopInterpolateDataItems();
//        } //  AFTERS
//        else if (isInterpolating || !endC.isThisEnd(numIterations, avgVel)) {
//            if (isInterpolating) {
//                isInterpolating = false;
//                notAfters = false;
//                System.out.println("afters");
//                resetIterations();
//            }
//
//            for (int i = 0; i < 20; i++) {
//                System.out.print(i);
//                super.iterateFullSet();
//            }
//
//            System.out.println("  ****   End   **** ");
//
//            if (viewParent != null) {
//                viewParent.getScatterPanel().threadRunning(false);
//                viewParent.getScatterPanel().pause();
//                viewParent.getInfoPanel().stop();
//            }
//
//            throw new TooManyIterationsException();
//        }
    }

    /**
     * Method to interpolate all of the points not in the subset, does
     * this by finding the closest object in the subset and placing
     * it somewhere near it.  Does in a loop
     *
     * Optimised for speed
     *
     */
    protected void loopInterpolateDataItems() {
        long t1, t2;
        Coordinate.setActiveDimensions(2);
        int fullSize = dataItems.getSize();
        int numTimes = fullSize - subset.size();
        for (int q = 0; q < numTimes; q++) {

            int i = ((Integer) subsetToInterp.get(q)).intValue();

            //init the minimum distance to be dist to the first object
            int minIndex = ((Integer) origSubset.get(0)).intValue();
            if (subOfSub) {
                minIndex = ((Integer) interpSubset.get(0)).intValue();
            }

            double minDist = dataItems.getDesiredDist(i, minIndex);
            if (subOfSub) {
                minDist = dataItems.getDesiredDist(i, minIndex);
            }


            // find a suitable position

            if (subOfSub) {
                for (int j = 0; j < interpSubset.size(); j++) {
                    // build up an arraylist of
                    // the dist to obj subset[j].  < minDist, use it!
                    int samp = ((Integer) interpSubset.get(j)).intValue();
                    if (samp != i) {
                        if (dataItems.getDesiredDist(i, samp) < minDist) {

                            minDist = dataItems.getDesiredDist(i, samp);
                            minIndex = samp;
                        }
                    }
                }
            } else {

                // parent-finding
                for (int j = 0; j < origSubset.size(); j++) {
                    int samp = ((Integer) origSubset.get(j)).intValue();
                    if (samp != i) {
                        if (dataItems.getDesiredDist(i, samp) < minDist) {

                            minDist = dataItems.getDesiredDist(i, samp);
                            minIndex = samp;
                        }
                    }
                }
            }

            //cache dists to sample for i
            double[] sampCache = new double[interpSubset.size()];

            for (int p = 0; p < interpSubset.size(); p++) {
                int ind = ((Integer) interpSubset.get(p)).intValue();
                sampCache[p] = dataItems.getDesiredDist(i, ind);
            }

            placeNearToNearestNeighbour(i, minIndex, interpSubset, sampCache); // not orig subset
            //add this point to the subset

            subset.add(new Integer(i));
            subsetContents.add(new Integer(i));
        }
        Coordinate.setActiveDimensions(4);

    }

    /**
     * Returns the sum of the difference between the desired distance and 
     * the actual distance from object index, to all of the points held in 
     * the arrayList sample
     *
     * @param index  The index of the object which all dists are measured from
     * @param p      The new coordinate that is being evaluated for index
     * @param sample The arrayList of indices to measure dists to
     * @return The sum of the desired distances
     */
    protected double sumDistToSample(int index, Coordinate p, ArrayList sample, double[] sampCache) {
        double total = 0.0;

        for (int i = 0; i < sample.size(); i++) {
            if (((Integer) sample.get(i)).intValue() != index) {
                int samp = ((Integer) sample.get(i)).intValue();
                Vect v = new Vect(p, (Coordinate) position.get(samp));
                double realD = v.getLength();

                double desD;
                if (sampCache == null) {
                    desD = dataItems.getDesiredDist(index, samp);
                } else // cache;
                {
                    desD = sampCache[i];
                }

                double dif = (realD - desD);
                if (dif < 0.0d) {
                    dif *= -1.0d;
                }
                total += dif;
            }
        }


        return total;
    }

    /**
     * Calculates the sum of the forces from the object index to all of the 
     * points in the sample
     *
     * @param index
     * @param sample
     * @return The resultant force from the object to the sample
     */
    protected Vect sumForcesToSample(int index, ArrayList sample) {
        Vect f = new Vect();

        for (int i = 0; i < sample.size(); i++) {

            int samp = ((Integer) sample.get(i)).intValue();

            //get the unit vector from index pt to sample pt

            Coordinate p1 = (Coordinate) position.get(index);
            Coordinate p2 = (Coordinate) position.get(samp);
            Vect v = new Vect(p1, p2);
            Vect unitVect = v.normalizeVector();

            //scale it by spring force

            double realD = v.getLength();
            double desD = dataItems.getDesiredDist(index, samp);

            double dist = (realD - desD);
            //if (dist < 0 )
            //dist *= -1;

            double force = (springForce * dist);// * deltaTime;
            unitVect.scale(force);
            f.add(unitVect);
        }

        //scale this force, relative to how many samples it was summed to

        f.scale(1.0 / ((double) sample.size()));

        return f;
    }

    /**
     * Places the point close to its nearest neighbour.
     *
     * @param index Index of the item being placed.
     * @param parent Index of the (probable) nearest neighbour.
     * @param sample Sample on which to perform calculations
     * @param sampCache cache of neighbour distances
     *
     */
    protected void placeNearToNearestNeighbour(int index, int parent, ArrayList sample, double[] sampCache) {

        Coordinate pos = (Coordinate) position.get(parent);
        double radius = dataItems.getDesiredDist(index, parent);
        double sumDist = Double.MAX_VALUE;

        double dist0 = 0.0;
        double dist180 = 0.0;
        double dist90 = 0.0;
        double dist270 = 0.0;

        // Variables for use in binary search of circle quadrant.

        int lowBound = 0;
        int highBound = 0;

        // Get distance to points at 0 degrees on radius.

        dist0 = sumDistToSample(index, cPoint(0.0d, radius, pos), sample, sampCache);
        dist180 = sumDistToSample(index, cPoint(180.0d, radius, pos), sample, sampCache);
        dist90 = sumDistToSample(index, cPoint(90.0d, radius, pos), sample, sampCache);
        dist270 = sumDistToSample(index, cPoint(270.0d, radius, pos), sample, sampCache);

        // Determine the closest quadrant.
        if (dist0 == dist180) {
            if (dist90 > dist270) {
                lowBound = highBound = 270;
            } else {
                lowBound = highBound = 90;
            }

        } else if (dist90 == dist270) {
            if (dist0 > dist180) {
                lowBound = highBound = 180;
            } else {
                lowBound = highBound = 0;
            }
        } else if (dist0 > dist180) {
            if (dist90 > dist270) {
                lowBound = 180;
                highBound = 270;
            } else {
                lowBound = 90;
                highBound = 180;
            }
        } else {
            if (dist90 > dist270) {
                lowBound = 270;
                highBound = 360;
            } else {
                lowBound = 0;
                highBound = 90;
            }
        }

        // Now carry out a binary search within the quadrant of interest.

        int a = binSearch((double) lowBound, (double) highBound, pos,
                radius, index, sample, sampCache);

        Coordinate newCirclePt = cPoint((double) a, radius, pos);
        ((Coordinate) position.get(index)).set(newCirclePt);

        // Use force calculations to add a vector to the so-far derived
        // position, to hopefully move it closer to the ideal position.
        //System.out.print(vectorIterations);
        for (int i = 0; i < vectorIterations; i++) {
            // Get vector from circle pt to sample of points

            Vect v = sumForcesToSample(index, sample);

            // Get the current position of item index.

            Coordinate newVectPt =
                    new Coordinate((Coordinate) position.get(index));

            // Add the new force vector to this position.

            Vect newVect = new Vect(v);

            //double scaleFactor =
            //bestForcePos(index, newCirclePt, v, sample);
            //newVect.scale(scaleFactor);

            newVectPt.add(newVect);

            // Make this new position the position of item index.

            ((Coordinate) position.get(index)).set(newVectPt);
        }
    }

    /**
     *
     * Returns a coordinate of a point on the circumference of a circle.
     *
     * @param angleIn The angle to the circle's centre.
     * @param radi The radius of the circle.
     * @param posIn the position of the centre of the circle.
     * 
     */
    protected Coordinate cPoint(double angleIn, double radi, Coordinate posIn) {
        Coordinate pt = new Coordinate(
                posIn.getX() + Math.cos(Math.toRadians(angleIn)) * radi,
                posIn.getY() + Math.sin(Math.toRadians(angleIn)) * radi);
        return pt;
    }

    protected int binSearch(double lB, double hB, Coordinate pos, double radi, int index, ArrayList sample, double[] sampCache) {
        double lBound = lB;
        double hBound = hB;
        while ((int) lBound <= (int) hBound) {
            int mid = (int) Math.round((lBound + hBound) / 2);

            if ((mid == (int) lBound) || (mid == (int) hBound)) {

                // If the mid point in the circle quadrant is equal to
                // either the upper or lower bound then there is no more
                // search space and either the upper or lower bound is the
                // optimal position on the circle.

                if ((sumDistToSample(index, cPoint(lBound, radi, pos), sample, sampCache)) >=
                        (sumDistToSample(index, cPoint(hBound, radi, pos), sample, sampCache))) {
                    return (int) hBound;
                } else {
                    return (int) lBound;
                }
            } else {
                double distMidLeft = sumDistToSample(index, cPoint((double) (mid + 1), radi, pos), sample, sampCache);
                double distMidRight = sumDistToSample(index, cPoint((double) (mid - 1), radi, pos), sample, sampCache);
                double distMid = sumDistToSample(index, cPoint((double) mid, radi, pos), sample, sampCache);

                // Determine which half of the space contains the closest point to
                // the samples.

                if (distMid > distMidLeft) {
                    lBound = (double) (mid + 1);
                } else if (distMid > distMidRight) {
                    hBound = (double) (mid - 1);
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the indices of the items which are displayable.  By default this
     * returns null.  However, if it was required to only display a portion of 
     * the data set at a time this method could be overriden.  Anyone 
     * interested in viewing the items should call this method, if it returns
     * null then they can use all available indices
     * 
     * @return The indices of the displayable data items
     */
    public List getDisplayableItems() {
        return subset;
    }

    /**
     * Returns the data item at index index.
     *
     * Overrides the method in SpringModel, returns the data item with index
     * of the subset item with index.
     *
     * @param index The index of the data item wanted
     * @return The data item that was at this index
     */
    public DataItem getDataItem(int index) {
        // the real index which corresponds to index
        int i = ((Integer) subset.get(index)).intValue();

        return dataItems.getDataItem(i);
    }

    /**
     * Is the execution currently interpolating?
     *
     *
     * @return whether or not currently interpolating
     */
    public boolean isInterpolating() {
        return isInterpolating;
    }

    /**
     * Has the execution finished interpolation?
     *
     *
     * @return whether or not afters
     */
    public boolean isAfters() {
        return !notAfters;
    }

    public void setSizes(int v, int s) {
        neighbourSize = v;
        sampleSize = s;
    }

}


