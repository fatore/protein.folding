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
 * MiniHistogram
 * 
 * This class draws small histograms of a data set.  These are designed to 
 * be used with a double ended slider of some kind to display a small summary 
 * of the data set that the user is working with.  It would typically sit to 
 * the side of a slider (horizontally or vertically).  It is possible to 
 * highlight selected regions on the histogram. 
 * 
 *
 */

package fsmvis.gui;

import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;



public class MiniHistogram extends JPanel implements SelectionChangedListener
{
    
    
    
    protected Viewer    parent;
    protected ArrayList data;
    protected ArrayList bins;
    protected ArrayList binStates;
    protected ArrayList binContentsCache;
    protected Object    min, max;
    protected int       minInt,  maxInt,  binSizeInt;
    protected double    minDbl,  maxDbl,  binSizeDbl;
    protected long      minDate, maxDate, binSizeDate;

    protected double    binSize;
    
    protected int       maxBinHeight   = 0;
    protected int       numBins        = 0;
    protected int       orientation    = 0;
    
    // orientations
    public static final int HORIZONTAL = 0, VERTICAL = 1;
    
    private final static int OFFSET          = 16;
    private final static int BIN_PIXEL_WIDTH = 4;



    
    /**
     * constructor: takes an arrayList of data which must contain Integer 
     * objects.  min and max are the minimum and maximum values in data
     * numBins is the number of bins that this data is to be split into
     *
     * @param data The data set provided
     * @param min minium value, 
     * @param max maximum value, same type as min
     */
    public MiniHistogram(Viewer parent, ArrayList data, Object min, Object max)
    {
	this(parent, data, min, max, HORIZONTAL);
    }


    
    /**
     * constructor: takes an arrayList of data which must contain Integer 
     * objects.  min and max are the minimum and maximum values in data
     * numBins is the number of bins that this data is to be split into
     *
     * @param data The data set provided
     * @param min minium value, 
     * @param max maximum value, same type as min
     * @param orientation The orientation of the histogram
     */
    public MiniHistogram(Viewer parent, 
			 ArrayList data, 
			 Object min, Object max,
			 int orientation)
    {
	this.parent      = parent;
	this.data        = data;
	this.orientation = orientation;
	this.min         = min;
	this.max         = max;
	
	initBins();
    }
    
    

    /**
     * Called whenever the number of bins changes, or to init at start.  Fills
     * all of the bins with the height value corresponding the number of values
     * in that range.
     *
     */
    protected void fillBins()
    {
	initBins();
	
	// iterate over whole data set
	for ( int i = 0 ; i < data.size() ; i++ ) {
	    
	    //find a bin to put this value  in
	    for ( int k = 0 ; k < numBins ; k++ ) {
		
		if ( inBin(i, k) ) {
		    
		    //increment the bin height
		    bins.set( k, new Integer(
				  ((Integer)bins.get(k)).intValue() + 1));
		    
		    //record the highest bin so far
		    if ( ((Integer)bins.get(k)).intValue() > maxBinHeight )
			maxBinHeight = ((Integer)bins.get(k)).intValue();
		    

		    // set bin selected, if the item is selected, but bin isnt
		    if ( parent.getSelectionHandler().getState(i) &&
			 !((Boolean)binStates.get(k)).booleanValue() )
			binStates.set(k, new Boolean(true));
		    
		    
		    // add this value to this bins cache
		    ((LinkedList)binContentsCache.get(k)).add(new Integer(i));

		    break;
		}
		
	    }
	    
	}
	
    }
    
    
    
    
    /**
     * Initialises all the bins (empties them)
     * 
     */
    protected void initBins()
    {
	bins = new ArrayList();
	
	binContentsCache = new ArrayList();
	
	binStates = new ArrayList();

	for ( int i = 0 ; i < numBins ; i++ ) {
	    bins.add(new Integer(0));
	    
	    binContentsCache.add( new LinkedList() );
	    
	    binStates.add(new Boolean(false));
	}
	
	//find the first non null value to determine the type
	int index = 0;
	while ( data.get(index) == null && index < data.size() )
	    index++;

	if ( data.get(index) instanceof Integer ) {
	    
	    minInt     = ((Integer)min).intValue();
	    maxInt     = ((Integer)max).intValue();
	    
	    binSize    = ((double)maxInt - (double)minInt) / (double)numBins;
	    
	}
	else if ( data.get(index) instanceof Double ) {
	    
	    minDbl     = ((Double)min).doubleValue();
	    maxDbl     = ((Double)max).doubleValue();
	    binSize    = (maxDbl - minDbl) / (double)numBins; 
	
	}
	else if ( data.get(index) instanceof Date ) {
	    
	    // may want to change this to long millis
	    minDate     = ((Date)min).getTime();
	    maxDate     = ((Date)max).getTime();
	    binSize     = ((double)maxDate - (double)minDate) / (double)numBins;
	    
	}
	else {
	    System.err.println("Invalid data types passed to minHistogram");
	}
	
    }
    
    
    
    /**
     * Returns whether or not the value at index in the data arraylist belongs
     * in bin binNo
     *
     *
     */
    protected boolean inBin( int index,  int binNo ) 
    {
	
	if ( data.get(index) instanceof Integer ) {
	    
	    int val = ((Integer)data.get(index)).intValue();
	    
	    return ( (double)val >= ((double)binNo * binSize + (double)minInt) &&
		     (double)val <= ((double)(binNo+1) * binSize + (double)minInt) );
			    
	}
	else if ( data.get(index) instanceof Double ) {
	    
	    double val = ((Double)data.get(index)).doubleValue();
	    
	    return ( val >= (double)binNo * binSize + minDbl &&
		     val <= (double)(binNo+1) * binSize + minDbl );
	    
	    
	}
	else if ( data.get(index) instanceof Date ) {
	    
	    long val = ((Date)data.get(index)).getTime();
	    
	    return ( (double)val >= (double)binNo * binSize + (double)minDate &&
		     (double)val <= (double)(binNo+1) * binSize + (double)minDate );
	    
	}
	else if ( data.get(index) == null ) {
	    
	    return false;
	
	}
	else {

	    System.err.println("Invalid data types passed to MiniHistogram");
	    return false;
	}
    }
    
    
    
    /**
     * Overrides the paint method of JComponent 
     *
     */
    public void paint(Graphics g)
    {	
	int width  = getWidth() - 2*OFFSET;
	int height = getHeight() - 1;
	
	
	if (width / BIN_PIXEL_WIDTH != numBins) {
	    numBins = width / BIN_PIXEL_WIDTH;
	    fillBins();
	}	

	double horizScale    = (double)height / (double)maxBinHeight;
	double vertScale = (double)width / (double)maxBinHeight; 
	
	g.setColor(Color.white);
	g.fillRect( 0, 0, getWidth(), getHeight() );
	
	
	
	for ( int i = 0 ; i < bins.size() ; i++ ) {
	    
	    switch(orientation) {
		
	    case HORIZONTAL:
		
		
		int binHeight = (int) ((double)((Integer)bins.
					 get(i)).intValue() * horizScale);
	
		//make sure rounding doesn't hide v. small bins
		if ( binHeight == 0 && ((Integer)bins.get(i)).intValue() > 0 )
		    binHeight = 1;
		
		//make the smallest displayable bin 2 high
		if (binHeight == 1) 
		    binHeight = 2;
		
		if ( ((Boolean)binStates.get(i)).booleanValue() )
		    g.setColor(Color.red);
		else
		    g.setColor(Color.gray);

		g.fillRect( OFFSET + i * (width / numBins), 
			    height - binHeight, 
			    width / numBins,
			    binHeight);
		
		//put axes along the bottom and sides
		g.setColor(Color.black);
		g.drawLine( OFFSET - 1, 0, OFFSET - 1, height);
		g.drawLine( OFFSET - 1, height, OFFSET+width+1, height );
		g.drawLine( OFFSET+width+1, 0, OFFSET+width+1, height );
		
				
		break;
		
	    case VERTICAL:
		
		binHeight = (int) ((double)((Integer)bins.
					    get(i)).intValue() * vertScale) ;
		
		g.setColor(Color.red);
		
		g.setColor(Color.black);
		
		break;
	    }
	    
	}
	
    }
    
    
    
    /**
     * Method that will be called the selection is changed in the selection 
     * handler
     *
     * @param select The selection handler that created the selection changed
     */
    public void selectionChanged( SelectionHandler select )
    {
	//empty all bins first
	Collections.fill(binStates, new Boolean(false));
	
	HashSet selected = select.getSelectedIndices();


	for ( int i = 0 ; i < numBins ; i++ ) {
	    
	    Iterator itr = ((LinkedList)binContentsCache.get(i)).iterator();

	    while ( itr.hasNext() ) {
		
		if ( selected.contains( itr.next() ) ) {
		    
		    binStates.set(i, new Boolean(true));
		    
		    break;
		}
	    }
	    
	    
	}
	
	revalidate();

	repaint();
    }
}
