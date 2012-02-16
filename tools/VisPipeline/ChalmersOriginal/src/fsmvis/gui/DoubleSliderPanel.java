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
 * DoubleSliderPanel
 *
 *
 *
 */

package fsmvis.gui;


import fsmvis.data.DataItemCollection;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;


public class DoubleSliderPanel extends JPanel 
                               implements DoubleSliderAdjustmentListener,
					  Selectable
					  
{

    protected Viewer             parent;
    protected DataItemCollection dataItems;
    protected String             colName;        
    protected int                column;
    protected MiniHistogram      hist;
    protected DoubleSlider       slider;
    
    protected List               sortedIndices;
    protected List               allIndices;
    
    protected List               selected;
    protected Set                deselected;
    
    // The previous slider values
    protected double             oldMin, oldMax;
   
    public DoubleSliderPanel( Viewer parent, 
			      DataItemCollection dataItems, int column)
    {

	this.parent    = parent;
	this.dataItems = dataItems;
	this.column    = column;
	
	initData();
	
	int typeId  = ((Integer)dataItems.getTypes().get(column)).intValue();
	colName = (String)dataItems.getFields().get(column);
	
	this.setLayout(new BorderLayout());
	
	this.setBorder( new TitledBorder(LineBorder.createGrayLineBorder(), 
					 colName,
					 TitledBorder.DEFAULT_JUSTIFICATION, 
					 TitledBorder.DEFAULT_POSITION,
					 Viewer.point10));
	
	hist = new MiniHistogram(parent, dataItems.getColumn(column), 
				 dataItems.getMinimum(column),
				 dataItems.getMaximum(column),
				 MiniHistogram.HORIZONTAL);
	
	parent.getSelectionHandler().addSelectionChangedListener(hist);
	
	parent.getSelectionHandler().addSelectableObject(this);
	
	// vars for initialising double slider, set to 0 for compiler
	double min = 0, max = 0;
	
		
	JPanel labels = new JPanel();
	labels.setLayout(new BoxLayout(labels, BoxLayout.X_AXIS));
		
	JLabel minLabel = new JLabel();
	JLabel maxLabel = new JLabel();
	

	switch (((Integer)dataItems.getTypes().get(column)).intValue()) {
	    
	case DataItemCollection.DATE:
	    SimpleDateFormat formatter = 
		new SimpleDateFormat("dd'/'MM'/'yyyy");
	    minLabel.setText(formatter.format(dataItems.getMinimum(column)));
	    maxLabel.setText(formatter.format(dataItems.getMaximum(column)));
	    
	    min = (double)((Date)dataItems.getMinimum(column)).getTime();
	    max = (double)((Date)dataItems.getMaximum(column)).getTime();
	    
	    break;
	    
	case DataItemCollection.DOUBLE:
	    
	    String minDbl = ((Double)dataItems.getMinimum(column)).toString();
	    String maxDbl = ((Double)dataItems.getMaximum(column)).toString();
	    if (minDbl.length() > 6)
		minDbl = minDbl.substring(0, 6);
	    if (maxDbl.length() > 6)
		maxDbl = maxDbl.substring(0, 6);
	    
	    minLabel.setText(minDbl);
	    maxLabel.setText(maxDbl);  
	    
	    min = ((Double)dataItems.getMinimum(column)).doubleValue();
	    max = ((Double)dataItems.getMaximum(column)).doubleValue();

	    break;
	    
	case DataItemCollection.INTEGER:
	    
	    minLabel.setText(((Integer)dataItems.
			      getMinimum(column)).toString());
	    maxLabel.setText(((Integer)dataItems.
			      getMaximum(column)).toString());
	    
	    min = (double)((Integer)dataItems.getMinimum(column)).intValue();
	    max = (double)((Integer)dataItems.getMaximum(column)).intValue();
	    
	    break;
	    
	default:
	    
	}

	slider = new DoubleSlider(DoubleSlider.HORIZONTAL, min, max);
	slider.setBorder(BorderFactory.createLoweredBevelBorder());
	slider.setTrackNotificationThrottle(25);
	slider.addAdjustmentListener( this );
	
	slider.setHiliteVisible(true);
	
	slider.setHilitedMinimum(min);
	slider.setHilitedMaximum(max);
	
	minLabel.setFont(Viewer.point9);
	maxLabel.setFont(Viewer.point9);
	
	labels.add( minLabel );
	labels.add( Box.createHorizontalGlue() );
	labels.add( maxLabel );
	
	
	JPanel sliderAndLabels = new JPanel();
	sliderAndLabels.setLayout(new BoxLayout(sliderAndLabels, 
						BoxLayout.Y_AXIS)); 
	sliderAndLabels.add(slider);
	sliderAndLabels.add(labels);

	this.add( hist, BorderLayout.CENTER );
	this.add( sliderAndLabels, BorderLayout.SOUTH);


	
    }
    
    
    
    /**
     * initialises the data structures used to optimise the selection controls
     * Creates an array of indices ordered in ascending order by their contents
     */
    protected void initData()
    {
	sortedIndices = new ArrayList();
	allIndices    = new LinkedList();
	
	for ( int i = 0 ; i < dataItems.getSize() ; i++ ) {
	 
	    //if this is not a null value add this to the sorted indices
	    if ( dataItems.getColumn(column).get(i) != null ) {
	    
		sortedIndices.add(new Integer(i));
		allIndices.add(new Integer(i));
	    }
	}
		
	Collections.sort(sortedIndices, 
			 new IndicesComparator( dataItems.getColumn(column)));
	   
	deselected = new HashSet();
    }
    
    

    
    /**
     * Called whenever a double ended slider is dragged
     *
     *
     */
    public void adjustmentValueChanged (DoubleSlider ds) 
    {
	
	ArrayList col = dataItems.getColumn(column);
	
	//reset the selection status
	deselected = new HashSet();
	
	//used for storing index in sorted index that was reached
	int min = 0 , max = 0;
	
	//remove point deselected to left of min 
	for ( int i = 0 ; i < sortedIndices.size() &&
		  ds.getSelectedMinimum() >  getColSortedIndex(col, i) ; i++) {
	
	    deselected.add(sortedIndices.get(i));
	    
	    min = i;
	}
	
	//remove points selected to the right of min
	for ( int i = sortedIndices.size() - 1 ; i >= 0 &&
		  ds.getSelectedMaximum() < getColSortedIndex(col, i) ; i-- ) {
	    
	    deselected.add(sortedIndices.get(i));
	    
	    max = i;
	}
	    
	min++;
	max--;
	
	if ( ds.getSelectedMinimum() ==  getColSortedIndex(col, 0) )
	    min = 0;
	
	if ( ds.getSelectedMaximum() ==
	                   getColSortedIndex(col, sortedIndices.size()-1) )
	    max = sortedIndices.size() - 1;
	
	ds.setHilitedMinimum(getColSortedIndex(col, min));
	ds.setHilitedMaximum(getColSortedIndex(col, max));
	
	parent.getSelectionHandler().updateSelection();
    }

    
    
    
    /**
     * Returns the value stored at the sorted index position i. Always returns
     * the result as a double so that it can be compared with a double slider
     *
     */
    protected double getColSortedIndex(ArrayList col, int i)
    {
	Object o = col.get(((Integer)sortedIndices.get(i)).intValue());
	
	if ( o instanceof Integer ) {  
	    
	    return (double)((Integer)o).intValue();
	}
	
	else if ( o instanceof Date ) {
	    
	    return (double)((Date)o).getTime();
	}
	else 
	    return ((Double)o).doubleValue();
	
    }
    

    // Selectable interface methods
    
    
    /**
     * Returns the selection of this object
     *
     * @return The selection
     *
     */
    public Collection getSelection()
    {
	return null;
    }


    
    /**
     * Returns the indices of the deselected items in this object
     * This method may return null if the getSelection method is being used
     * instead.  
     *
     * @return The deselection
     */
    public Collection getDeselection()
    {
	return deselected;
    }


    /**
     * Sets this selectable object to select all its items
     *
     */
    public void selectAll()
    {

	deselected.clear();
	
	reset();
	
	repaint();
    }
    

    
    /**
     * Sets this selectable item to select none of it items
     *
     */
    public void selectNone()
    {
    }



    /**
     * resets the sliders to their inital positions and selections
     *
     */
    public void reset()
    {
	slider.removeAdjustmentListener(this);

	slider.setSelectedMinimum(slider.getAbsoluteMinimum());
	slider.setHilitedMinimum(slider.getAbsoluteMinimum());
	slider.setHilitedMaximum(slider.getAbsoluteMaximum());
	slider.setSelectedMaximum(slider.getAbsoluteMaximum());
	slider.setHiliteVisible(true);
    
	slider.addAdjustmentListener(this);
    }

    
    /**
     * Overrides the parent method, returns the column name which this double 
     * slider panel is representing
     *
     * @return The name of this column
     */
    public String toString()
    {
	return colName;
    }
}
