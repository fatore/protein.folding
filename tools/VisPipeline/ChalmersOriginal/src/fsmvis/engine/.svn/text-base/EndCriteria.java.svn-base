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
 * EndCriteria
 * 
 *
 */

package fsmvis.engine;

public class EndCriteria {

    protected int criteria;
    // 1: iterations > its*N, 2: velocity<vel, 3:constantIts, 4:either, 5:both
    protected int its;
    protected double vel;
    protected int conIts;
    protected int N;  // data size
    protected int actualItsToStop;
    protected boolean nonStop = false;

    public EndCriteria(int crit, int i, double v, int ci, int size) {
	criteria = crit;
	its = i;
	vel=v;
	conIts = ci;
	N = size;
	actualItsToStop = N*its;
    }

    public int getCrit() {
	return criteria;
    }

    public void setN(int n1) {
	N=n1;
	actualItsToStop = N*its;
    } 

    public boolean isThisEnd(int iterationsDone, double currentVel) {

	if (nonStop) 
	    return false;
	else {
	    if (iterationsDone >= actualItsToStop)
		if ((criteria==1) || (criteria==4) || (currentVel<vel&&iterationsDone>=conIts))
		    return true;
	    if ((currentVel<vel) && (iterationsDone>0))
		return ((criteria==2)||criteria==4);
	    if (iterationsDone>=conIts)
		return (criteria==3||criteria==4);
	    return false;
	}
    }

    /**
     * Option to disregard end criteria.
     *

     */
    public void turnOff() {
	nonStop = true;
    }


    public int getMaxIterations() {
	return its;
    }

    public double getMinVelocity() {
	return vel;
    }
 

   

}
