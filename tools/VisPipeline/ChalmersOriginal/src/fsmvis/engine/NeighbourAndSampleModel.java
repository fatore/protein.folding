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
 * NeighbourAndSampleModel
 * 
 * Implementation of a LayoutModel, this model should work in linear time
 * per iteration.  It does this by using caching of neighbour sets with 
 * stochastic sampling
 *
 */
package fsmvis.engine;

import fsmvis.utils.Utils;
import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.data.DataItemCollection;
import fsmvis.gui.Viewer;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class NeighbourAndSampleModel extends SpringModel {

    // each element is also an arrayList of indices
    protected ArrayList neighbours,  samples;
    public static int neighbourSize = 0;
    public static int sampleSize = 0;
    public static final String NEIGHBOUR_SIZE = "neighbourSize";
    public static final String SAMPLE_SIZE = "sampleSize";
    boolean dontReadFromFile = false;
    /**
     * constructor: takes a DataItemCollection as argument, this is the 
     * data that will be used with this layout model
     *
     * @param dataItems 
     */
    public NeighbourAndSampleModel(DataItemCollection dataItems, EndCriteria ec, Viewer v) {
        super(dataItems, ec, v);

        // init the neighbours array list to be a random sample
        neighbours = new ArrayList();
        samples = new ArrayList();

        dontReadFromFile = false;

        // System.out.println("fileset "+neighbourSize+"  "+sampleSize);

        // init every element of neighbours and samples with a random list
        for (int i = 0; i < dataItems.getSize(); i++) {


            HashSet exclude = new HashSet();
            exclude.add(new Integer(i));
            //init each neighbours set to a random list
            ArrayList neighbs = Utils.createRandomSample(null,
                    exclude,
                    dataItems.getSize(),
                    neighbourSize);


            // sort the arraylist into ascending order
            NeighbourComparator comp = new NeighbourComparator(dataItems, i);
            Collections.sort(neighbs, comp);


            neighbours.add(neighbs);

            exclude = new HashSet(neighbs);
            exclude.add(new Integer(i));

            //insert an ArrayList of samples into each samples element
            samples.add(Utils.createRandomSample(null,
                    exclude,
                    dataItems.getSize(),
                    sampleSize));

        }

        dataSizeFactor = 1.0 / (double) (neighbourSize + sampleSize);
    }

    /**
     * constructor: takes a DataItemCollection as argument, this is the 
     * data that will be used with this layout model
     *
     * @param dataItems 
     */
    public NeighbourAndSampleModel(DataItemCollection dataItems, EndCriteria ec, Viewer v, int neigh, int samp) {

        super(dataItems, ec, v);
        System.out.println("Changed V,S");
        neighbourSize = neigh;
        sampleSize = samp;
        dontReadFromFile = true;

        // init the neighbours array list to be a random sample
        neighbours = new ArrayList();
        samples = new ArrayList();

        // init every element of neighbours and samples with a random list
        for (int i = 0; i < dataItems.getSize(); i++) {


            HashSet exclude = new HashSet();
            exclude.add(new Integer(i));
            //init each neighbours set to a random list
            ArrayList neighbs = Utils.createRandomSample(null,
                    exclude,
                    dataItems.getSize(),
                    neighbourSize);


            // sort the arraylist into ascending order
            NeighbourComparator comp = new NeighbourComparator(dataItems, i);
            Collections.sort(neighbs, comp);


            neighbours.add(neighbs);

            exclude = new HashSet(neighbs);
            exclude.add(new Integer(i));

            //insert an ArrayList of samples into each samples element
            samples.add(Utils.createRandomSample(null,
                    exclude,
                    dataItems.getSize(),
                    sampleSize));

        }

        dataSizeFactor = 1.0 / (double) (neighbourSize + sampleSize);
    }

    /**
     * Initialises the properties for this object to be the properties held 
     * for this object
     *
     */
    protected void loadProperties() {
        super.loadProperties();
        // initialise the properties
        try {

            PropertiesHandler propHandler =
                    new PropertiesHandler(this, "NeighbourAndSampleModel");

            properties.putAll(propHandler.getProperties());

        } catch (NoPropertiesException npe) {

            System.err.println("couldn't load the properties for " +
                    " Neighbour and sample model");
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
        if (!dontReadFromFile) {
            neighbourSize = Integer.parseInt(properties.getProperty(NEIGHBOUR_SIZE));
            sampleSize = Integer.parseInt(properties.getProperty(SAMPLE_SIZE));
        }
    }

    /**
     *  Instruction to normalise data for spring model
     */
    public void doNormalise() {
        super.doNormalise();
    }

    /**
     * Calculates the forces that will be exerted on dataItem with index index
     * Calcs forces only by looking at neighbours and samples lists.  
     * Overrides the method in SpringModel
     *
     * @param index The index of the dataItem that forces are to be calculated 
     * on
     */
    public void calcForces(int index) {

        //first randomize the sample
// 	if ((viewParent.getModelType().equals("InterpolateSampleModel") || viewParent.getModelType().equals("SimpleInterpolateSampleModel")   || viewParent.getModelType().equals("OptimizedInterpolateSampleModel")  || viewParent.getModelType().equals("KMeansInterp"))&& ((InterpolateSampleModel)viewParent.getModel()).isAfters())
// 	    randomizeSample(index, "interp");
// 	else
        randomizeSample(index);
        ArrayList neighbs = (ArrayList) neighbours.get(index);
        // iterate thro' neighbour set, calcing force based on sim &
        // euclidean dist
        for (int i = 0; i < neighbourSize; i++) {
            addForces(index, ((Integer) neighbs.get(i)).intValue());
        }
        ArrayList sample = (ArrayList) samples.get(index);

        //iterate thro' sample , calcing force based on sim & eucldiean dist
        for (int i = 0; i < sampleSize; i++) {
            addForces(index, ((Integer) sample.get(i)).intValue());
        }

        //check the sample to see if any of them would make good neighbours
        findNewNeighbours(index);

    }

    /**
     * For the object at index point, check thro' its samples list to check if 
     * any of those objects would make better neighbours than the ones 
     * currently in the neighbours list. 
     *
     * @param index The index of the element whose samples list should be
     * examined for better neighbours
     */
    public void findNewNeighbours(int index) {
        ArrayList sample = (ArrayList) samples.get(index);

        NeighbourComparator comp = new NeighbourComparator(dataItems, index);

        for (int i = 0; i < sampleSize; i++) {

            // get the sample Object index

            int sampObj = ((Integer) sample.get(i)).intValue();

            //get the index of the largest dist neighbour member
            int neighbMax = ((Integer) ((ArrayList) neighbours.get(index)).get(0)).intValue();

            // check to see if this value would be suitable as a new neighbour
            if (dataItems.getDesiredDist(index, sampObj) <
                    dataItems.getDesiredDist(index, neighbMax)) {

                //replace that item in the neighbour set
                ((ArrayList) neighbours.get(index)).set(0,
                        new Integer(sampObj));

                //then sort the neighbour set
                Collections.sort(((ArrayList) neighbours.get(index)), comp);
            }
        }

    }

    /**
     * Creates a new arrayList of random numbers to be used by the samples 
     * ArrayList.  This list  will contain a sampleSize random numbers, 
     * corresponding to dataItem indices, such that none of the values are the 
     * same as ones already in the sample or already in the neighbours list 
     * and are between 0 and dataItems.getSize().  THe resulting list will be 
     * stored in samples[index].
     * 
     * @param index The index of the samples arrayList to store the result
     */
    protected void randomizeSample(int index) {
        // the neighbours list, which is not wanted in this sample
        HashSet exclude = new HashSet((ArrayList) neighbours.get(index));
        exclude.add(new Integer(index));
        ArrayList newSample = Utils.createRandomSample(null,
                exclude,
                dataItems.getSize(),
                sampleSize);

        samples.set(index, newSample);

    }

    /**
     * To ensure this version runs for final iterations of interp model - should 
     * run on whole set, rather than version in SubsetNeighb+Samp
     * 
     * @param index The index of the samples arrayList to store the result
     * @param s  No purpose
     */
    protected void randomizeSample(int index, String s) {
        // the neighbours list, which is not wanted in this sample
        HashSet exclude = new HashSet((ArrayList) neighbours.get(index));
        exclude.add(new Integer(index));
        ArrayList newSample = Utils.createRandomSample(null,
                exclude,
                dataItems.getSize(),
                sampleSize);

        samples.set(index, newSample);

    }

    public void setSizes(int v, int s) {
        neighbourSize = v;
        sampleSize = s;
    }

}
