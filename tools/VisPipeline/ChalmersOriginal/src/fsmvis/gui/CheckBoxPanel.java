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
 *
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
 * CheckBoxPanel
 *
 *
 */
package fsmvis.gui; 

import fsmvis.data.DataItemCollection;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class CheckBoxPanel extends JPanel implements SelectionChangedListener,
						     Selectable,
						     ItemListener
{
    
    protected Viewer             parent;
    protected DataItemCollection dataItems;
    protected String             colName;
    protected int                column;
    protected HashMap            checkBoxes;
    protected HashMap            mapping;
    
    protected List               selected;

    /**
     * constructor:
     *
     * @param parent
     * @param dataItems
     * @param column
     */
    public CheckBoxPanel( Viewer parent, 
			  Set fieldSet,
			  DataItemCollection dataItems, 
			  int column )
    {
	this.parent    = parent;
	this.dataItems = dataItems;
	this.column    = column;
	
	colName = (String)dataItems.getFields().get(column);
	
	
	this.setLayout(new BorderLayout());
	
	this.setAlignmentY(LEFT_ALIGNMENT);

	this.setBorder( new TitledBorder(LineBorder.createGrayLineBorder(), 
					 colName,
					 TitledBorder.DEFAULT_JUSTIFICATION, 
					 TitledBorder.DEFAULT_POSITION,
					 Viewer.point10));
	
	
	// iterate through the field set and create a checkbox for each of them
	Iterator itr = fieldSet.iterator();
	int totalLen = 0;
	int c = 0;
	
	//also generate a hashmap of mappings from  each checkbox to the 
	// indices of the data items that contain these elements
	checkBoxes = new HashMap();
	mapping    = new HashMap();

	JPanel leftPan = new JPanel();
	leftPan.setLayout(new FlowLayout(FlowLayout.LEFT));

	while ( itr.hasNext() ) {
	    
	    String value    = (String)itr.next();
	    
	    if ( value != null ) {
	    
		totalLen += value.length();
		c++;
		
		JCheckBox check = new JCheckBox(value, true);
		check.setActionCommand( value );
		check.setFont(Viewer.point9);
		check.addItemListener(this);
		leftPan.add(check);
		
		checkBoxes.put(value, check);
		
		mapping.put(value, new ArrayList());
	    }
	}



	this.add(leftPan, BorderLayout.CENTER);

	//iterate over data set assigning each index to a element of the hash
	ArrayList col = dataItems.getColumn(column);
	
	for ( int i = 0 ; i < dataItems.getSize() ; i++ ) {

	    String value = (String)col.get(i);
	    
	    if ( value != null ) 
		((ArrayList)mapping.get(value)).add(new Integer(i));;
	}
	
	
	//approx of the size needed for this component
	int size = 50 + ((c*2 + totalLen) / 25) * 22;
	
	this.setMinimumSize(new Dimension(302, size));
	this.setPreferredSize(new Dimension(302, size));
	this.setMaximumSize(new Dimension(302, size));
	
	
	selectAll();

	parent.getSelectionHandler().addSelectionChangedListener(this);
    
	parent.getSelectionHandler().addSelectableObject(this);
    }


    /**
     * Returns the selection of this object
     *
     * @return The selection
     */
    public Collection getSelection()
    {
	return selected;
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
	return null;
    }



    /**
     * Sets this selectable object to select all its items
     *
     */
    public void selectAll()
    {
	selected = new ArrayList();
	for ( int i = 0 ; i < dataItems.getSize() ; i++ )
	    selected.add(new Integer(i));
    }
    

    
    /**
     * Sets this selectable item to select none of it items
     *
     */
    public void selectNone()
    {
	selected.clear();
    }
    


    /**
     * called when the state of an item has changed, required by ItemListener
     *
     */
    public void itemStateChanged(ItemEvent e) 
    {
	    
	ArrayList indices = (ArrayList)mapping.get(
				 ((JCheckBox)e.getItem()).getActionCommand());
	
	
	
	//call the selection handler with the updated selection values
	if ( e.getStateChange() ==  ItemEvent.SELECTED )
	    
	    selected.addAll( indices );
	else
	    selected.removeAll( indices );
    
	parent.getSelectionHandler().updateSelection();
    }
    

    
    /**
     * Method that will be called the selection is changed in the selection 
     * handler
     *
     * @param select The selection handler that created the selection changed
     */
    public void selectionChanged( SelectionHandler select )
    {
	
	
	// keeps track of which boxes are now selected
        LinkedList selectedBoxes = new LinkedList();
		
	
	// set all checkboxes to off
	Iterator itr = checkBoxes.keySet().iterator();
	while ( itr.hasNext() ) {
	    
	    JCheckBox c = (JCheckBox)checkBoxes.get( itr.next() );
	    
	    c.removeItemListener(this);
	    
	    c.setSelected(false);
	    
	    c.addItemListener(this);
	}
	
	// iterate over the whole data set, checking which items are 
	// selected and marking this
	for ( int i = 0 ; i < dataItems.getSize() &&
		  selectedBoxes.size() < checkBoxes.keySet().size(); i++ ) {
	    
	    // if an item is selected, find out its field value
	    if ( select.getState(i) ) {
		String value = (String)dataItems.getColumn(column).get(i);
		
		//System.out.println("value = "+value);
		if ( ! selectedBoxes.contains(value) && value != null )
		    selectedBoxes.add(value);
		
	    }
	}
	
	
	//iterate thro' the selected boxes and set them selected.
	itr = selectedBoxes.iterator();
	
	while ( itr.hasNext() ) {
	    
	    JCheckBox c = (JCheckBox)checkBoxes.get( itr.next() );
	    
	    c.removeItemListener(this);
	    
	    c.setSelected(true);
	    
	    c.addItemListener(this);
	}
	
    }

    /** 
     * Returns the name of the column that this check box panel represents
     * 
     * @return The column name 
     */
    public String toString()
    {
	return colName;
    }
    
}

