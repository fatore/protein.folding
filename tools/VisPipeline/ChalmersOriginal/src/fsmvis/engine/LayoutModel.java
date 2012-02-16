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
 * LayoutModel
 * 
 * interface to describe how a layout model should behave.
 *
 */

package fsmvis.engine;


import fsmvis.data.DataItem;
import fsmvis.data.DataItemCollection;

import java.io.Serializable;
import java.util.Properties;
import java.util.List;



public interface LayoutModel extends Serializable
{
    
    public final static String LAYOUT_BOUNDS         = "layoutBounds";
    public final static String RANGE_HI              = "rangeHi";
    public final static String RANGE_LO              = "rangeLo";
    public final static String DAMPING_FACTOR        = "dampingFactor";
    public final static String SPRING_FORCE          = "springForce";
    public final static String GRAVITY_FORCE         = "gravityForce";
    public final static String GRAVITY_DAMPING_FORCE = "gravityDampingForce";
    public final static String TIME_FORCE            = "timeForce";
    public final static String TIME_DAMPING_FORCE    = "timeDampingForce";
    public final static String FREENESS              = "freeness";
    public final static String DELTA_TIME            = "deltaTime";
    

    /**
     * Method to perform one iteration of the layout algorithm for this
     * layout model
     */
    public void doIteration() throws TooManyIterationsException;

    

    /**
     * Returns the coordinate position of the object corresponding to the 
     * index index
     * 
     * @param index The index of the object
     * @return The coordinate of the object
     */
    public Coordinate getPosition(int index);
  
    
    /**
     * Returns the data item at index index.
     *
     * @param index The index of the data item wanted
     * @return The data item that was at this index
     */
    public DataItem getDataItem(int index);
    
    

    /**
     * Returns the dataItemCollection object that this layoutmodel is 
     * representing.
     * 
     * @return The DataItemCollection that this model is laying out
     */
    public DataItemCollection getDataItemCollection();
    
    
    
    /**
     * Returns the indices of the items which are displayable.  By default this
     * returns null.  However, if it was required to only display a portion of 
     * the data set at a time this method could be overriden.  Anyone 
     * interested in viewing the items should call this method, if it returns
     * null then they can use all available indices
     * 
     * @return The indices of the displayable data items
     */
    public List getDisplayableItems();


     /**
     * Returns the number of iterations that have been carried out by this 
     * layout model 
     *
     * @return The number of iterations that this layout model has done
     */
    public int getNumIterations();


    
    /**
     * Returns the number of milliseconds that the algorithm has been running
     * for.  
     *
     * @return The time in millis that the system has been running
     */
    public long getExecutionTime();
 
    

    
    /**
     * Calculates the approximate error in this layout, does this by calcing 
     * the value for a subset of the data set to get an approximation of  
     * the error without slowing down the layout too much.
     *
     * @return The approximation of the avg error
     */
    public double getApproxError();



    /**
     * Returns the average error in the data set
     *
     * @return the average error
     */
    public double getAvgError();
    

    
    /**
     * Returns an approximation of the average velocity in the data set
     *
     * @return An approx of the avg velocity
     */
    public double getApproxVelocity();
    


    /**
     * Returns the average velocity in the data set
     *
     * @return the average velocity
     */
    public double getAvgVelocity();
    


    /**
     * Returns the properties object used by a layout model
     * 
     * @return The properties object
     */
    public Properties getProperties();


    
    /**
     * called whenever the values for this layout model have been altered 
     * externally, so that the system knows to update their values
     *
     */
    public void updateValues();
}
