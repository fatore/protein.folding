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
 * NeighbourComparator
 * 
 * Implementation of the comparator model, used for sorting a neighbour
 * set based on the high dimensional distance of the elements
 *
 */

package fsmvis.engine;

import fsmvis.data.DataItemCollection;
import java.util.Comparator;


public class NeighbourComparator implements Comparator
{
    
    private DataItemCollection dataItems;
    private int index;
    

    /**
     * constructor: takes the dataItemCollection that contain the 
     * desired distances, and the index that this is the neighbour list of
     * 
     * @param dataItems the data item collection
     * @param index The index of the owner of this neighbour set
     */
    public NeighbourComparator(DataItemCollection dataItems, int index)
    {
	this.dataItems = dataItems;
	this.index     = index;
    
    }
    
    

    /**
     * Compares its two arguments for order.
     *
     * @param o1 The first object to be compared
     * @param o2 The other object to be compared
     * @return The result of the comparison
     */
    public int compare(Object o1, Object o2) 
    {
	int obj1 = ((Integer)o1).intValue();
	int obj2 = ((Integer)o2).intValue();

	if ( dataItems.getDesiredDist(index, obj1) == 
	     dataItems.getDesiredDist(index, obj2) ) {
	    
	    return 0;
	}
	else if ( dataItems.getDesiredDist(index, obj1) <
		  dataItems.getDesiredDist(index, obj2) ) {
	    
	    return 1;
	}
	else {
	    return -1;
	}
    }


    
    /**
     * Define whether or not this class is equivalent to some other object, 
     * never used in this implementation
     */
    public boolean equals(Object obj) 
    {
	return false;
    }
}
