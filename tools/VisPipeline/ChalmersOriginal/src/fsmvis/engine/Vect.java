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
 * Vect
 * 
 * Simple class to represent a vector object
 * makes use of the Coordinate object to make a vector
 *
 */

package fsmvis.engine;


public class Vect extends Coordinate
{

    
    /**
     * constructor: empty
     */
    public Vect()
    {
	super();
    }



    /**
     * constructor: takes four values to use as vector values
     *
     * @param x the x component of vector
     * @param y the y component of vector
     * @param z the z component of vector
     * @param w the w component of vector
     */
    public Vect(double x, double y, double z, double w)
    {
	super(x,y,z,w);
    }
	


    /**
     * constructor: Takes two Coordinate objects as parameters and constructs
     * a vector from this
     *
     * @param c1 The first coordinate in the vector
     * @param c2 The second coordinate in the vector
     */
    public Vect( Coordinate c1, Coordinate c2)
    {
	
	super(c2.getX() - c1.getX(), c2.getY() - c1.getY(), 
	      c2.getZ() - c1.getZ(), c2.getW() - c1.getW());
    }


    
    /**
     * constructor: instantiates the class with an existing coordinate, treats
     * The values of the coordinate as if they were vectors
     *
     * @param c The coord to use for instantiating
     */
    public Vect(Coordinate c)
    {
	super(c);
    }
    
    
    
    /**
     * Returns the length of this vector, makes use of the Coordinate class to 
     * see how many dimensions are currently being used, then only uses these 
     * dimensions to calc the length
     *
     * @return The length
     */
    public double getLength()
    {
	double len = 0.0;
	
	for (int i = 0 ; i < Coordinate.getActiveDimensions() ; i++ ) {
	    
	    len += get(i) * get(i);
	}
	
	return Math.sqrt(len);
    }

    

    /**
     * Makes a new normalized version of this vector.  That is a unit vector 
     * with length 1. does this by dividing each coord by 1/getLength()
     *
     * @return The normalized vector 
     */
    public Vect normalizeVector()
    {
	double len = getLength();
	Vect v = new Vect();
	
	
	if (len == 0.0)
	    return v;
	
	for (int i = 0 ; i < Coordinate.getActiveDimensions() ; i++ ) {
	    
	    v.set(i, get(i)/len);
	}
	return v;
    }
    


    /**
     * calculates the dot product between this vector and the vector passed 
     * as an arg
     *
     * @param v The vector to be dot prodded with this vector
     * @return The result of the dot product
     */
    public double dotProduct(Vect v)
    {
	double total = 0.0;

	for (int i = 0 ; i < Coordinate.getActiveDimensions() ; i++ ) {
	    
	    total += get(i) * v.get(i);
	}
	
	return total;
    }
    

    
    /**
     * Scales this vector by multiplying by a factor
     *
     * @param factor The factor to multiply by
     */
    public void scale(double factor)
    {
	setX(getX() * factor);
	setY(getY() * factor);
	setZ(getZ() * factor);
	setW(getW() * factor);
    }
}
