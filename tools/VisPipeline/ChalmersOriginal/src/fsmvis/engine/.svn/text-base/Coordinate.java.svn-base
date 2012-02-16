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
 * Coordinate
 * 
 * Simple class to represent a coordinate object
 * values are public to make manipulation as simple as possible
 * can support up to 4 dimensions, which is what the layout algorithm
 * uses intially
 *
 */

package fsmvis.engine;

import java.util.ArrayList;
import java.io.Serializable;


public class Coordinate implements Serializable
{
    
    public double    x;
    public double    y;
    public double    z;
    public double    w;
    
    public static int       activeDimensions = 4;
    public static final int INITIAL_DIMENSIONS = 4;
    
    
    /**
     * constructor: Takes no params and sets the initial values to be 0.0
     */
    public Coordinate()
    {
	this(0.0, 0.0, 0.0, 0.0);
	
    }

    
    /**
     * constructor: takes two double values to represent the coords
     * @param x The x value of coord
     * @param y The y value
     */
    public Coordinate(double x, double y)
    {
	this(x, y, 0.0);
    }
    
    /**
     * constructor: takes two double values to represent the coords
     * @param x The x value of coord
     * @param y The y value
     * @param z THe z value
     */
    public Coordinate(double x, double y, double z)
    {
	this(x, y, z, 0.0);
    }

    
    
    /**
     * constructor: takes two double values to represent the coords
     *     
     * @param x The x value of coord
     * @param y The y value
     * @param z The z value#
     * @param w The w value
     */
    public Coordinate(double x, double y, double z, double w)
    {
	this.x = x ; this.y = y; this.z = z; this.w = w;
    }

    

    /**
     * constructor: instantiate the coordinate with a new copy of an existing
     * coordinate
     * 
     * @param c Coordinate values to be used for instantiating
     */
    public Coordinate(Coordinate c)
    {
	this(c.getX(), c.getY(), c.getZ(), c.getW());
    }
    
    
    
    public double get(int index)
    {
	switch(index) {
	case 0:
	    return getX();
	case 1:
	    return getY();
	case 2:
	    return getZ();
	case 3:
	    return getW();
	default:
	    System.err.println("unknown index");
	    return 0.0;
	}
    }
    

    /**
     * Returns the x value
     * 
     * @return The x value
     */
    public double getX()
    {
	return x;
    }
    


    /**
     * Returns the y value
     * 
     * @return The y value
     */
    public double getY()
    {
	return y;
    }


    
    /**
     * Returns the z value
     *
     * @return The z value
     */
    public double getZ()
    {
	return z;
    }

    

    /**
     * Returns the w value
     *
     * @return The w value
     */
    public double getW()
    {
	return w;
    }


    
    /**
     * Sets the values of the coordinates
     *
     * @param x The x value
     * @param y The y value
     * @param z The z value
     * @param w The w value
     */
    public void set(double x, double y, double z, double w)
    {
	setX(x);    setY(y);
	setZ(z);    setW(w);
    }
    
    
    
    /**
     *
     *
     * @param c
     */
    public void set(Coordinate c)
    {
	setX(c.getX()); 
	setY(c.getY());
	setZ(c.getZ());
	setW(c.getW());
    }
    
    
    
    /**
     *
     * 
     * @param index
     * @param val
     */
    public void set(int index, double val)
    {
	switch(index) {
	case 0:
	    setX(val);
	    break;
	case 1:
	    setY(val);
	    break;
	case 2:
	    setZ(val);
	    break;
	case 3:
	    setW(val);
	    break;
	default:
	    System.err.println("unknown index");
	}
    }
    
    
    /**
     * Sets the x value
     * 
     * @return The x value
     */
    public void setX(double x)
    {
	this.x = x;
    }
    


    /**
     * Sets the y value
     * 
     * @return The y value
     */
    public void setY(double y)
    {
	this.y = y;
    }


    
    /**
     * Sets the z value
     *
     * @return The z value
     */
    public void setZ(double z)
    {
	this.z = z;
    }
    

    
    /**
     * Sets the w value
     *
     * @return The w value
     */
    public void setW(double w)
    {
	this.w = w;
    }
    

    
    /**
     * Adds the components of the vector v onto this coordinate
     * 
     * @param v The vector to be added onto this coordinate
     */
    public void add(Vect v)
    {
	for (int i = 0 ; i < Coordinate.getActiveDimensions() ; i++)
	    set(i, (get(i) + v.get(i)) );
    }
    

    
    /**
     * subtracts the components of the vector v from this coordinate
     * 
     * @param v The vector to be added onto this coordinate
     */
    public void sub(Vect v)
    {
	for (int i = 0 ; i < Coordinate.getActiveDimensions() ; i++)
	    set(i, (get(i) - v.get(i)) );
    }
    


    /**
     * A static method to get the number of dimensions which are currently 
     * active or in use
     *
     * @return the number of active dimensions
     */
    public static int getActiveDimensions()
    {
	return activeDimensions;
    }
    
     

    /**
     * A static method to set the number of active dimensions, must be
     * between 0 and INITIAL_DIMENSIONS
     *
     * @param activeD The number of dimensions that are currently in use
     */
    public static void setActiveDimensions(int activeD)
    {
	if (activeD > INITIAL_DIMENSIONS)
	    activeD  = INITIAL_DIMENSIONS;
	
	activeDimensions = activeD;
    }
    
    
    
    /**
     * Defines whether or not two coordinates are equal
     *
     * @param c the coordinate to compare with
     * @return the result of the equality
     */
    public boolean equals(Coordinate c)
    {
	return (this.getX() == c.getX() &&
		this.getY() == c.getY() &&
		this.getZ() == c.getZ() &&
		this.getW() == c.getW());
    }
    
    
    
    /**
     * Turns the object into a String representation
     *
     * @return The string representation
     */
    public String toString()
    {
	return "x = "+getX()+", y = "+getY()+", z = "+getZ()+", w = "+getW();
    }
}
    
