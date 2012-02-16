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
 * CheckBoxPanel
 *
 *
 */



package fsmvis.gui;

import fsmvis.data.DataItemCollection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class SearchBoxPanel extends JPanel implements ActionListener,
						      Selectable
{

    protected Viewer             parent;
    protected DataItemCollection dataItems;
    protected int                column;
    protected String             colName;
    protected List               col;
    protected JButton            select;
    protected JButton            deSelect;
    protected JComboBox          query;

    private   static Collection         selected;
    private   static Collection         deselected;

    
    /**
     * constructor:
     *
     * @param parent
     * @param dataItems
     * @param column
     */
    public SearchBoxPanel( Viewer parent, 
			   DataItemCollection dataItems, int column)
    {
	this.parent = parent;
	this.dataItems = dataItems;
	this.column    = column;

	col = dataItems.getColumn(column);
	
	colName = (String)dataItems.getFields().get(column);

	this.setBorder( new TitledBorder(LineBorder.createGrayLineBorder(), 
					 colName,
					 TitledBorder.DEFAULT_JUSTIFICATION, 
					 TitledBorder.DEFAULT_POSITION,
					 Viewer.point10));
	HashSet choices = new HashSet(col);
	choices.remove(null);
	
	query = new JComboBox( choices.toArray() );
	query.setEditable(true);
	query.setFont(Viewer.point9);
	query.setPreferredSize(new Dimension(60, 30));
	
	select = new JButton("Select");
	select.setActionCommand("select");
	select.setFont(Viewer.point8);
	select.addActionListener(this);
	//select.setPreferredSize(new Dimension(40, 50));

	deSelect = new JButton("Deselect");
	deSelect.setActionCommand("deselect");
	deSelect.setFont(Viewer.point8);
	deSelect.addActionListener(this);
	//deSelect.setPreferredSize(new Dimension(40, 50));
	
	JPanel leftPan = new JPanel();
	leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.X_AXIS));
    
	leftPan.add(query);
	leftPan.add(Box.createRigidArea(new Dimension(5, 1)));
	leftPan.add(select);
	leftPan.add(Box.createRigidArea(new Dimension(5, 1)));
	leftPan.add(deSelect);
	
	this.add(leftPan, BorderLayout.CENTER);

	//register as a selectable object
	parent.getSelectionHandler().addSelectableObject(this);
    }

    
    /**
     * called whenever an action is performed
     *
     */
    public void actionPerformed( ActionEvent ae ) 
    {
	String val = (String)query.getSelectedItem(); 
	
	selected = null;
	deselected = null;

	if ( ae.getActionCommand().equals("select") ) {
	    
	    selected = performQuery(col, val);
	}
	else if ( ae.getActionCommand().equals("deselect") ) {

	    deselected = performQuery(col, val);
	}
	
	parent.getSelectionHandler().updateSelection();
    }

    
    
    
    /**
     * Peforms the query by searching for the val string in the arraylist col
     *
     *
     */
    protected List performQuery( List col, String val )
    {
	
	List result = new LinkedList();
	
	val = val.toLowerCase();
	
	for ( int i = 0 ; i < col.size() ; i++ ) {
	    
	    if ( col.get(i) != null ) {
		
		String s = ((String)col.get(i)).toLowerCase();
		
		if ( val.equals("*") || val.equals("") ) {
		    
		    result = null;
		}
		
		// wild card search for any substring 
		else if ( val.startsWith("*") && val.endsWith("*") 
			  && !val.equals("*")) {
		    
		    if ( s.indexOf(val.substring(1,val.length()-1 )) != -1)
			result.add(new Integer(i));
		}
		
		//wild card search for end of string
		else if ( val.startsWith("*") && !val.equals("*") ) {
		    
		    if ( s.endsWith( val.substring(1) ) )
			result.add( new Integer(i) );
		}
		
		//wild card search for start of string
		else if ( val.endsWith("*") && !val.equals("*") ){
		    
		    if ( s.startsWith( val.substring( 0, val.length()-1)) )
			result.add(new Integer(i));
		    
		} 
		else  {
		    
		    if ( s.equals( val ) )
			result.add(new Integer(i));
		}
	    }
	}
	
	
	return result;
    }

    

    // Methods required by the selectable interface

    /**
     * Returns the indices of the selected items in this object
     * This method may return null if the getDeselection method is being used
     * instead.
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
	return deselected;
	
    }


    
    /**
     * Sets this selectable object to select all its items
     *
     */
    public void selectAll()
    {
	deselected = null;
	selected = null;
    }
 
 
    
    /**
     * Sets this selectable item to select none of it items
     *
     */
    public void selectNone()
    {
	// nothing to do
    }


    /**
     * Returns the string that repesents the name of the column that this data
     * represents
     *
     * @return the name of this column
     */
    public String toString()
    {
	return colName;
    }
}
