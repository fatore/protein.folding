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
 * SubsetNeighbourAndSampleModel
 * 
 * Extends NeighbourAndSampleModel and does layouts using the neighbours sets
 * with stochastic sampling, but also only does a layout of the subset of the
 * data set.  This is useful for making initial layouts of the whole dataset
 * or for any other kind of layout algorithm which only works over a subset of 
 * the data at any one time.  This class makes it appear as if only the subset
 * of the whole data set actually exists, and only returns the size of the 
 * subset and the coordinates of points which are in the subset.  The subset is
 * initialised to a random sample of data objects, but can be changed to any
 * subset that the user requires
 *
 */
package fsmvis.engine;

import fsmvis.data.DataItemCollection;
import fsmvis.utils.Utils;
import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.gui.Viewer;

import java.util.HashSet;
import java.util.ArrayList;
import java.awt.Color;

public class SubsetNeighbourAndSampleModel extends NeighbourAndSampleModel {

    protected ArrayList subset;
    protected HashSet subsetContents;
    protected int subsetSize;
    protected static int rootNFactor = 3;
    protected final static String ROOT_N_FACTOR = "rootNFactor";
    protected boolean kmeans = false;
    protected HashSet numbers;
    /**
     * constructor: specifies the data item collection to be used
     *
     * @param dataItems The data item collection to be used with this layout
     * model
     */
    public SubsetNeighbourAndSampleModel(DataItemCollection dataItems, EndCriteria ec, Viewer v) {
        super(dataItems, ec, v);
        kmeans = false;
        //subsetSize = Math.min(100, dataItems.getSize());
        //setSubsetSize( rootNFactor * (int)Math.sqrt(dataItems.getSize()) );
        setSubsetSize((int) Math.sqrt(dataItems.getSize()));
        //System.out.println("" + rootNFactor + " " + Math.sqrt(dataItems.getSize()));

        // need to make the neighbour and sample sets of these objects
        // only be from this subset
        for (int i = 0; i < subsetSize; i++) {

            int index = ((Integer) subset.get(i)).intValue();

            //randomize the neighbours
            HashSet exclude = new HashSet();
            exclude.add(new Integer(index));
            neighbours.set(index, Utils.createRandomSample(new HashSet(subset), exclude, dataItems.getSize(), neighbourSize));
            // randomize the sample
            randomizeSample(index);

            //set the color of this dataItem to be different
            getDataItem(i).setColor(Color.red);
        }


    // initialise hashSet of ints 1..datasize
    //for (int i =0; i<dataItems.getSize(); i++)
    //   numbers.add(new Integer(i));

    }

    /**
     *  Instruction to normalise data for spring model
     */
    public void doNormalise() {
        dataItems.calcNormValues(subset);
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
                    new PropertiesHandler(this, "SubsetNeighbourAndSampleModel");

            properties.putAll(propHandler.getProperties());

        } catch (NoPropertiesException npe) {

            System.err.println("couldn't load the properties for " +
                    " SubsetNeighbourAndSampleModel");
            npe.printStackTrace();
        }

    }

    /**
     * Initialises the values for this class, over rides the method in Spring
     * model.  Makes a call to super first.
     *
     */
    public void initValues() {
        super.initValues();

        rootNFactor = Integer.parseInt(properties.getProperty(ROOT_N_FACTOR));

    }

    /**
     * Sets the subset of dataItems to be used with this layout model to be
     * the arraylist of integers subset
     *
     * @param subset The subset to be used
     */
    public void setSubset(ArrayList subset) {
        this.subset = subset;
    }

    /**
     * Method to perform one iteration of the layout algorithm for the
     * full data set
     */
    public void iterateFullSet() {
        try {
            super.doIteration();
        } catch (TooManyIterationsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to perform one iteration of the layout algorithm for this
     * layout model
     */
    public void doIteration() throws TooManyIterationsException {
        //iterate over subset sample

        for (int i = 0; i < subsetSize; i++) {
            //calculate the forces that will be exerted on this object
            calcForces(((Integer) subset.get(i)).intValue());
        }

        //	System.out.println("++++++");
        for (int i = 0; i < subsetSize; i++) {
            if (Coordinate.getActiveDimensions() == 4) {
                calcTimeForce(((Integer) subset.get(i)).intValue());
            }
            if (Coordinate.getActiveDimensions() >= 3) {
                calcGravityForce(((Integer) subset.get(i)).intValue());
            }

            // integrate the changes that have just been calculated to calc
            // this objects new velocity and force
            integrateChanges(((Integer) subset.get(i)).intValue());
        }

        numIterations++;
    }

    /**
     * Creates a new arrayList of random numbers to be used by the samples 
     * ArrayList.  This list  will contain a sampleSize random numbers, 
     * corresponding to dataItem indices, such that none of the values are the 
     * same as ones already in the sample or already in the neighbours list 
     * and are between 0 and dataItems.getSize().  THe resulting list will be 
     * stored in samples[index].  
     * 
     * Overrides the method in NeighbourAndSampleModel, only selects samples 
     * from the subset arrayList
     * 
     * 
     * @param index The index of the samples arrayList to store the result
     */
    protected void randomizeSample(int index) {

        // the neighbours list, which is not wanted in this sample
        HashSet exclude = new HashSet((ArrayList) neighbours.get(index));
        exclude.add(new Integer(index));

        ArrayList newSample = Utils.createRandomSample(new HashSet(subset),
                exclude,
                dataItems.getSize(),
                sampleSize);

        samples.set(index, newSample);
    }

    /**
     * Returns the subset size that is being used for this layout
     *
     * @return The subset size that is being used
     */
    public int getSubsetSize() {
        return subset.size();
    }

    /**
     * Sets the subsetSize to be subsetSize
     *
     *  @param subsetSize The new size of the subset
     */
    public void setSubsetSize(int subsetSize) {
        subsetSize = Math.min(subsetSize, dataItems.getSize());

        // if this is a different subset size, change it
        if (this.subsetSize != subsetSize) {


            //may need to adjust size of Neighbour and sample sets if
            // subsetSize is very small
            if (neighbourSize > subsetSize) {
                neighbourSize = subsetSize - 1;
            }
            if (sampleSize > subsetSize) {
                sampleSize = subsetSize - 1;
            }

            //big problem -- set neighb & samp to be 1/2 subsetsize
            if (neighbourSize + sampleSize > subsetSize - 1) {
                neighbourSize = subsetSize / 2;
                sampleSize = subsetSize / 2;
            }


            //System.out.println("dataitems = "+dataItems.getSize());
            //System.out.println("subset = "+subsetSize);

            if (!kmeans) {
                subset = Utils.createRandomSample(null, null,
                        dataItems.getSize(),
                        subsetSize);
            }

            subsetContents = new HashSet(subset);

            //System.out.println("subset = "+subset);

            this.subsetSize = subsetSize;
        }
    }

    /**
     * Calculates the approximate error in this layout, does this by calcing 
     * the value for a subset of the data set to get an approximation of  
     * the error without slowing down the layout too much.
     *
     * @return The approximation of the avg error
     */
    public synchronized double getApproxError() {
//	ArrayList sample = Utils.createRandomSample(new HashSet((ArrayList)subset), null,
//						    dataItems.getSize(),
//						    Math.min(50, subset.size()));  // crashes here.  By adding subset to HashSet, have to iterate through subset.  Already an iterator for subset, and get ConcurrentModificationException
//
//	double error = 0.0;
//	int numComps = 0;
//
//	for ( int i = 1 ; i < sample.size() ; i++ ) {
//
//	    int obj1 = ((Integer)sample.get(i)).intValue();
//
//	    for ( int j = 0 ; j < i ; j++ ) {
//
//		int obj2 = ((Integer)sample.get(j)).intValue();
//
//		Vect v = new Vect((Coordinate)position.get(obj1),
//				  (Coordinate)position.get(obj2));
//
//		double lowDist = v.getLength();
//		double highDist = dataItems.getDesiredDist(obj1, obj2);
//		error += (lowDist - highDist);
//		numComps++;
//	    }
//	}
//	return error/(double)numComps;
        return 0.0f;
    }

    /**
     * Returns the average error in the data set
     *
     * @return the average error
     */
    public double getAvgError() {
//	double error = 0.0;
//	int numComps = 0;
//
//	for ( int i = 1 ; i < subset.size() ; i++ ) {
//
//	    int obj1 = ((Integer)subset.get(i)).intValue();
//
//	    for ( int j = 0 ; j < i ; j++ ) {
//
//		int obj2 = ((Integer)subset.get(j)).intValue();
//
//		Vect v = new Vect((Coordinate)position.get(obj1),
//				  (Coordinate)position.get(obj2));
//
//		double lowDist = v.getLength();
//		double highDist = dataItems.getDesiredDist(obj1, obj2);
//		error += (lowDist - highDist);// * (lowDist - highDist);
//		numComps++;
//	    }
//	}
//
//	//return Math.sqrt(error);
//	return error/(double)numComps;
        return 0.0f;
    }

    /**
     * Returns an approximation of the average error in the data set
     *
     * @return An approx of the avg velocity
     */
    public synchronized double getApproxVelocity() {

//	ArrayList sample = Utils.createRandomSample(new HashSet((ArrayList)subset), null,
//						    dataItems.getSize(),
//						    Math.min(50, subset.size()));
//
//	double totalVel = 0.0;
//
//	//System.out.println("selction size "+sample.size());
//
//	for ( int i = 0 ; i < sample.size() ; i++ ) {
//
//	    int index = ((Integer)sample.get(i)).intValue();
//
//	    totalVel += ((Vect)velocity.get(index)).getLength();
//	}
//
//	return totalVel / sample.size();

        return 0.0f;
    }

    /**
     * Returns the average velocity in the data set
     *
     * @return the average velocity
     */
    public double getAvgVelocity() {
        //Coordinate.setActiveDimensions(2);

        double totalVel = 0.0;
        for (int i = 0; i < subset.size(); i++) {

            int index = ((Integer) subset.get(i)).intValue();

            totalVel += ((Vect) velocity.get(index)).getLength();

        //System.out.println("adding on "+((Vect)velocity.get(index)).getLength());
        }

        //System.out.println("total vel = "+totalVel);

        //Coordinate.setActiveDimensions(4);

        return totalVel / subset.size();
    }

}
