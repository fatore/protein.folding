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
 * SelectionHandler
 *
 * Holds a list of boolean values, one for every item in a data set to 
 * indicate their selection status.  Sets the initial state of all elements to
 * true.  The collection behind this is thread safe.
 *
 */

package fsmvis.gui;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;



public class SelectionHandler
{
    protected int        size;
    protected List       selected;
    protected Set        selectedIndices;
    protected List       all;
    
    
    protected LinkedList selectables;
    
    protected LinkedList listeners;
 
    
    /**
     * constructor: Creates a new emepty Selection handler with an initial 
     * size of 0
     *
     */
    public SelectionHandler()
    {
	selected = Collections.synchronizedList(new ArrayList());
	all = new ArrayList();
	
	selectedIndices = new HashSet();
	
	listeners = new LinkedList();
	selectables = new LinkedList();
    }
    
    
    
    /**
     * constructor: Creates a new selection handler that can initially handle 
     * up to size elements
     *
     * @param size The initial size of the selection
     */ 
    public SelectionHandler(int size)
    {
	this();
	
	this.size = size;
	
	for (int i = 0 ; i < size ; i++ ) {
	    selected.add(new Boolean(true));
	    all.add(new Integer(i));
	    selectedIndices.add(new Integer(i));
	}
    }
    
    
    
    /**
     * returns the arrayList of selected states
     *
     * @return The selected states
     */
    public List getSelected()
    {
	return selected;
    }
    
    

    /**
     * returns the selected indices, uses the hashset class because it 
     * guarantees constant time, add, remove and importantly contains.
     *
     * @return The selected indices
     */
    public HashSet getSelectedIndices()
    {
	return (HashSet)selectedIndices;
    }


    
    /**
     * Returns the status of the data item located at index index.  If the 
     * specified index does not exist, then return false.
     *
     * @param index The location in the selected array
     * @return The status of this index
     */
    public boolean getState( int index )
    {
	if ( index < selected.size() ) 
	    
	    return ((Boolean)selected.get(index)).booleanValue();
	
	else 
	    
	    return false;
	
    }
    
    
    
    /**
     * method to be called by a selectable object when the selection has 
     * changed.
     *
     */ 
    public void updateSelection()
    {
	Iterator itr = selectables.iterator();

	selectedIndices = new HashSet(all);
	HashSet deselection = new HashSet();
		
	Selectable obj;
	
	while ( itr.hasNext() ) {
	    
	    obj = (Selectable)itr.next();
	    
	    Collection c = obj.getSelection();
	    Collection d = obj.getDeselection();

	    if ( c != null && c.size() < size )
		selectedIndices.retainAll(c);
	    
	    if ( d != null )
		deselection.addAll(d);
	}
	
	selectedIndices.removeAll(deselection);
	
	for ( int i = 0 ; i < size ; i++ )
	    selected.set(i , new Boolean(selectedIndices.contains(
							new Integer(i))) );
		
	notifyListeners();
    }
    

 
    
    /**
     * Adds all of the indices in the newSelect array to the current selection
     *
     * @param newSelect The arraylist of indices to be added
     */
    public void addToSelected( List newSelect )
    {
	setStates(newSelect, true);
    
	selectedIndices.addAll(newSelect);
	
	notifyListeners();
    }
    
    
    
    /**
     * Removes the indices specified in newDeselect from the currently selected
     * indices.
     *
     * @param newDeselect The items to be deselected
     */
    public void removeFromSelected( List newDeselect )
    {
	setStates(newDeselect, false);
    
	selectedIndices.removeAll(newDeselect);
	
	notifyListeners();
    }

    

    /**
     * Selects all of the items in the selection.
     *
     */
    public void selectAll()
    {
	//tell all the selectables to reset
	Iterator itr = selectables.iterator();
	
	while ( itr.hasNext() )
	    
	    ((Selectable) itr.next() ).selectAll();
    	

	setAllStates(true);
	notifyListeners();
    }
    
    

    /**
     * Selects none of the items, deselects all
     *
     */
    public void selectNone()
    {
	//tell all the selectables to reset
	Iterator itr = selectables.iterator();
	
	while ( itr.hasNext() )
	    
	    ((Selectable) itr.next() ).selectNone();
	
	
	setAllStates(false);
	
	notifyListeners();
    }
    

    /**
     * Sets the states of the indices stored in indices to be state, internal
     * method used by removefrom selected and add to selected.  Also changes 
     * the size of the selected elements to reflect the highest index that 
     * is passed to it.
     *
     * @param indices the indices that require their state set
     * @param state The state to set them to
     */
    protected void setStates( List indices, boolean state )
    {	
	for ( int i = 0 ; i < indices.size() ; i++ ) {
	    
	    int index = ((Integer)indices.get(i)).intValue();
	    
	    
	    if ( index >= selected.size() ) {
		
		while ( index < selected.size() ) 
		    selected.add(new Boolean(false));
		
	    }
	    
	    selected.set(index, new Boolean(state));
	
	}
	
	
    }
    
    
    
    /**
     * sets all of the states to the state indicated by the parameter,
     * this can be used to select all items or to deselect all items
     *
     * @param state The new desired state for all 
     */
    protected void setAllStates( boolean state )
    {
	Collections.fill(selected, new Boolean(state));
	
	selectedIndices = new HashSet();
	    
	for ( int i = 0 ; i < size ; i++ )
	    selectedIndices.add(new Integer(i));
    }
    
    

    public void addSelectableObject( Selectable s )
    {
	selectables.add( s );
    }
    
    

    public void removeSelectableObject( Selectable s )
    {
	selectables.remove( s );
    }
    
    
    /**
     * Adds a selection changed listener
     *
     * @param s
     */
    public void addSelectionChangedListener(SelectionChangedListener s)
    {
	listeners.add(s);
    }

    
    

    /**
     * Removes a selection changed listener
     *
     * @param s
     */
    public void removeSelectionChangedListener(SelectionChangedListener s)
    {
	listeners.remove(s);
    }
    
    

    /**
     * Method to be called whenever a change occurs in the selection, notifies
     * all the listeners of this.
     * 
     */
    protected void notifyListeners()
    {
	Iterator itr = listeners.iterator();
	
	while ( itr.hasNext() ) {
	    
	    ((SelectionChangedListener)itr.next()).selectionChanged(this);
	}
    }
}
