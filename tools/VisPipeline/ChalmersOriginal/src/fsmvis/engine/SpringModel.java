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
 * SpringModel
 * 
 * Implementation of a LayoutModel, works in O(n^2) compares every object 
 * with every other object.
 *
 */
package fsmvis.engine;

import fsmvis.data.DataItem;
import fsmvis.data.DataItemCollection;
import fsmvis.utils.Utils;
import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.gui.Viewer;


import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

public class SpringModel implements LayoutModel {

    //locally held values (testing if it is faster)
    protected ArrayList position;
    protected ArrayList velocity;
    protected ArrayList force;
    protected DataItemCollection dataItems;
    protected int numIterations;
    protected double unrelatedDist;
    protected EndCriteria endC;
    protected Properties properties;
    //set these to default values
    protected static double layoutBounds = 1.0;
    protected static double rangeLo = -0.5;
    protected static double rangeHi = 0.5;
    protected static double dampingFactor = 0.6;
    protected static double springForce = 0.7;
    protected static double gravityForce = 0.7;
    protected static double gravityDampingForce = 0.2;
    protected static double timeForce = 0.7;
    protected static double timeDampingForce = 0.2;
    protected static double freeness = 0.8;
    protected static double deltaTime = 0.01;
    // compensates the size of the data size, the accumulated forces for 1000
    // items will be much larger than for 100 items.
    protected double dataSizeFactor;
    protected long startTime;
    protected long runningTime;
    protected Viewer viewParent;
    /**
     * constructor:
     *
     * @param dataItems The data set to use for this layout
     */
    public SpringModel(DataItemCollection dataItems, EndCriteria ec, Viewer v) {
        this.dataItems = dataItems;

        properties = new Properties();

        loadProperties();

        initValues();

        // set the number of dimensions that are being used
        Coordinate.setActiveDimensions(4);

        numIterations = 0;

        init();

        randomizePositions();

        unrelatedDist = dataItems.getUnrelatedDist();

        //	System.out.println("unrelatedDist = "+unrelatedDist);

        dataSizeFactor = 1.0 / (double) (dataItems.getSize() - 1);

        endC = ec;
        viewParent = v;
    }

    /**
     * Initalises the data structures needed for the spring model
     *     */
    protected final void init() {
        position = new ArrayList();
        velocity = new ArrayList();
        force = new ArrayList();

        //alias all of the position, vel & force vals from the dataItems
        // so that they can be accessed locally - makes it a wee bitty faster
        for (int i = 0; i < dataItems.getSize(); i++) {

            position.add(dataItems.getDataItem(i).getPosition());
            velocity.add(dataItems.getDataItem(i).getVelocity());
            force.add(dataItems.getDataItem(i).getForce());
        }
    }

    /**
     * Initialises the properties for this object to be the properties held 
     * for this object
     *
     */
    protected void loadProperties() {
        try {
            PropertiesHandler propHandler = new PropertiesHandler(this, "SpringModel");
            properties.putAll(propHandler.getProperties());
        } catch (NoPropertiesException npe) {
            System.err.println("couldn't load the properties for SpringModel");
            npe.printStackTrace();
        }
    }

    /**
     * intitialises the values held in the properties object
     * 
     *
     */
    public void initValues() {
        layoutBounds = Double.parseDouble(properties.getProperty(LAYOUT_BOUNDS));
        rangeHi = Double.parseDouble(properties.getProperty(RANGE_HI));
        rangeLo = Double.parseDouble(properties.getProperty(RANGE_LO));
        dampingFactor = Double.parseDouble(properties.getProperty(DAMPING_FACTOR));
        springForce = Double.parseDouble(properties.getProperty(SPRING_FORCE));
        gravityForce = Double.parseDouble(properties.getProperty(GRAVITY_FORCE));
        gravityDampingForce = Double.parseDouble(properties.getProperty(GRAVITY_DAMPING_FORCE));
        timeForce = Double.parseDouble(properties.getProperty(TIME_FORCE));
        timeDampingForce = Double.parseDouble(properties.getProperty(TIME_DAMPING_FORCE));
        freeness = Double.parseDouble(properties.getProperty(FREENESS));
        deltaTime = Double.parseDouble(properties.getProperty(DELTA_TIME));

    }

    /**
     *  Instruction to normalise data for spring model
     */
    public void doNormalise() {
        dataItems.calcNormValues();
    }

    /** 
     * randomizes the starting locations of the data set.  to be called once
     * at startup preferably
     */
    public void randomizePositions() {
        for (int i = 0; i < dataItems.getSize(); i++) {

            Coordinate p = (Coordinate) position.get(i);

            p.set(Math.random() * layoutBounds + rangeLo,
                    Math.random() * layoutBounds + rangeLo,
                    Math.random() * layoutBounds + rangeLo,
                    Math.random() * layoutBounds + rangeLo);
        }
    }

    private boolean isThisEnd() {
        double avgVel = 1000;
        if (numIterations % 40 == 0) {
            avgVel = getAvgVelocity();
        }
        return endC.isThisEnd(numIterations, avgVel);
    }

    /**
     * Method to perform one iteration of the layout algorithm for this
     * layout model
     */
    public void doIteration() throws TooManyIterationsException {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        // will need to put this back in later (probably)
//        if (viewParent != null &&
//                !viewParent.getModel().equals("InterpolateSampleModel") &&
//                !viewParent.getModel().equals("KMeansInterp")) //iterate over whole data set
//        {
            for (int i = 0; i < dataItems.getSize(); i++) {
                calcForces(i);
            }
//        }


        for (int i = 0; i < dataItems.getSize(); i++) {

            if (Coordinate.getActiveDimensions() == 4) {
                calcTimeForce(i);
            }
            if (Coordinate.getActiveDimensions() >= 3) {
                calcGravityForce(i);
            }

            // integrate the changes that have just been calculated to calc
            // this objects new velocity and force
            integrateChanges(i);

        }

        numIterations++;

        long end = System.currentTimeMillis();
    }

    /**
     * Calculates the forces that will be exerted on dataItem with index index
     * Calcs forces by comparing every object with every other object with 
     * indices < index.
     *
     * @param index The index of the dataItem that forces are to be calculated 
     * on
     */
    public void calcForces(int index) {

        for (int i = 0; i < index; i++) {

            addForces(index, i);


        }
    }

    /**
     * Calculates the force that will be acting between obj1 and obj2
     * This is based on the difference between their actual distance and
     * their high dimensional distance.
     * 
     * @param obj1
     * @param obj2
     */
    protected final void addForces(int obj1, int obj2) {
        Coordinate p1 = (Coordinate) position.get(obj1);
        Coordinate p2 = (Coordinate) position.get(obj2);
        Vect v = new Vect(p1, p2);

        double realDist = v.getLength();
        double desiredDist = dataItems.getDesiredDist(obj1, obj2);

        // spring force to attain ideal seperation
        double spring = springForce * (realDist - desiredDist);

        // get the velocity vector between these two points
        // this is used to calc a damping factor, to stop everything
        // getting too fast
        Vect relativeVel = new Vect((Vect) velocity.get(obj1),
                (Vect) velocity.get(obj2));

        Vect unitVect = v.normalizeVector();

        // rate of change of separation
        double separationSpeed = relativeVel.dotProduct(unitVect);

        // force due to damping of separation
        double damping = dampingFactor * separationSpeed;

        // add on the force component to each dimension
        unitVect.scale(spring + damping);


        // add this vector onto the force of obj1
        ((Vect) force.get(obj1)).add(unitVect);

        // and subtract from the force of obj2
        ((Vect) force.get(obj2)).sub(unitVect);

    }

    /**
     * Method to simulate gravity acting on the system, does this by 
     * dividing the z component of the force
     *
     * @param index The index of the object to calc Gravity force for
     */
    protected void calcGravityForce(int index) {
        double height = ((Coordinate) position.get(index)).getZ();

        Vect f = (Vect) force.get(index);
        Vect v = (Vect) velocity.get(index);

        f.setZ(-(height * gravityForce) -
                (v.getZ() * gravityDampingForce));
    }

    /**
     * Method to apply a similar effect on the fourth dimension, which I have 
     * called time, to flatten everything out to 2D
     *
     * @param index  The index of the object to calc Time force for
     */
    protected void calcTimeForce(int index) {
        double time = ((Coordinate) position.get(index)).getW();
        Vect f = (Vect) force.get(index);
        Vect v = (Vect) velocity.get(index);

        f.setW(-(time * timeForce) - (v.getW() * timeDampingForce));
    }

    /**
     * Integrates the changes that have already been calculated.  Uses the 
     * force and velocity calculations, to move the position based on the
     * current velocity and then to alter the current velocity based on the 
     * forces acting on this object.
     *
     * @param index The index of the object to integrate changes for
     */
    protected final void integrateChanges(int index) {
        // adjust the force calculation to be the average force, this
        // involves scaling by the number of calcs done
        Vect f = (Vect) force.get(index);
        f.scale(dataSizeFactor);

        //scale velocity by force and freeness
        Vect vel = (Vect) velocity.get(index);
        Vect scaleForce = new Vect(f);
        scaleForce.scale(deltaTime);
        vel.add(scaleForce);
        vel.scale(freeness);

        //add velocity onto position
        Vect scaleVel = new Vect(vel);
        scaleVel.scale(deltaTime);
        ((Coordinate) position.get(index)).add(scaleVel);

    }

    /**
     * Returns the coordinate position of the object corresponding to the 
     * index index
     * 
     * @param index The index of the object
     * @return The coordinate of the object
     */
    public Coordinate getPosition(int index) {
        return (Coordinate) position.get(index);
    }

    /**
     * Returns the data item at index index.
     *
     * @param index The index of the data item wanted
     * @return The data item that was at this index
     */
    public DataItem getDataItem(int index) {
        return dataItems.getDataItem(index);
    }

    /**
     * Returns the dataItemCollection object that this layoutmodel is 
     * representing.
     * 
     * @return The DataItemCollection that this model is laying out
     */
    public DataItemCollection getDataItemCollection() {
        return dataItems;
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
        return null;
    }

    /**
     * Accessor method for the dataItems object
     *
     * @param dataItems THe dataItemCollection to be used with this layout 
     * model
     */
    public void setDataItemCollection(DataItemCollection dataItems) {
        this.dataItems = dataItems;
    }

    /**
     * Returns the number of iterations that have been carried out by this 
     * layout model 
     *
     * @return The number of iterations that this layout model has done
     */
    public int getNumIterations() {
        return numIterations;
    }

    /**
     * Returns the number of milliseconds that the algorithm has been running
     * for.  
     *
     * @return The time in millis that the system has been running
     */
    public long getExecutionTime() {
        return 0;
    }

    /**
     * Calculates the approximate error in this layout, does this by calcing 
     * the value for a subset of the data set to get an approximation of  
     * the error without slowing down the layout too much.
     *
     * @return The approximation of the avg error
     */
    public double getApproxError() {
        ArrayList sample = Utils.createRandomSample(null, null,
                dataItems.getSize(),
                Math.min(50, dataItems.getSize()));

        double error = 0.0;
        int numComps = 0;

        for (int i = 1; i < sample.size(); i++) {

            int obj1 = ((Integer) sample.get(i)).intValue();

            for (int j = 0; j < i; j++) {

                int obj2 = ((Integer) sample.get(j)).intValue();

                Vect v = new Vect((Coordinate) position.get(obj1),
                        (Coordinate) position.get(obj2));

                double lowDist = v.getLength();
                double highDist = dataItems.getDesiredDist(obj1, obj2);
                error += (lowDist - highDist);
                numComps++;
            }
        }
        return error / (double) numComps;
    }

    /**
     * Returns the average error in the data set
     *
     * @return the average error
     */
    public double getAvgError() {
        double error = 0.0;
        int numComps = 0;

        for (int i = 1; i < dataItems.getSize(); i++) {

            for (int j = 0; j < i; j++) {

                Vect v = new Vect((Coordinate) position.get(i),
                        (Coordinate) position.get(j));

                double lowDist = v.getLength();
                double highDist = dataItems.getDesiredDist(i, j);
                error += (lowDist - highDist);
                numComps++;
            }
        }

        return error / (double) numComps;
    }

    /**
     * Returns an approximation of the average error in the data set
     *
     * @return An approx of the avg velocity
     */
    public double getApproxVelocity() {

        ArrayList sample = Utils.createRandomSample(null, null,
                dataItems.getSize(),
                Math.min(50, dataItems.getSize()));

        double totalVel = 0.0;

        for (int i = 0; i < sample.size(); i++) {

            int index = ((Integer) sample.get(i)).intValue();

            totalVel += ((Vect) velocity.get(index)).getLength();
        }

        return totalVel / sample.size();
    }

    /**
     * Returns the average velocity in the data set
     *
     * @return the average velocity
     */
    public double getAvgVelocity() {
        double totalVel = 0.0;

        for (int i = 0; i < dataItems.getSize(); i++) {
            totalVel += ((Vect) velocity.get(i)).getLength();
        }

        return totalVel / dataItems.getSize();
    }

    /**
     * called whenever the values for this layout model have been altered 
     * externally, so that the system knows to update their values
     *
     */
    public void updateValues() {
        initValues();
    }

    /**
     * Returns the properties object used by a layout model
     * 
     * @return The properties object
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Calculates stress
     *
     * @return stress
     *    
     */
    public double getStress() {
        double totalDiffSq = 0, totalLowDistSq = 0, totalHighDistSq = 0, realDist, desiredDist;
        Vect v;
        for (int i = 0; i < dataItems.getSize(); i++) {
            for (int j = 0; j < i; j++) {
                v = new Vect((Coordinate) position.get(i), (Coordinate) position.get(j));
                realDist = v.getLength();
                desiredDist = dataItems.getDesiredDist(i, j);
                totalDiffSq += ((realDist - desiredDist) * (realDist - desiredDist));
                totalLowDistSq += (realDist * realDist);
            }
        }

        return totalDiffSq / totalLowDistSq;
    }

    /**
     * reset iterations
     *
     */
    public void resetIterations() {
        numIterations = 0;
    }

}
