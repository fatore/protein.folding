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
 * TableViewFrame
 *
 * provides a spreadsheet like table view of the data for closer inspection
 *
 */
 

package fsmvis.gui;

import fsmvis.data.DataItemCollection;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import java.awt.Container;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.ArrayList;


public class TableViewFrame extends JFrame
{
    private static TableViewFrame instance;
    
    private        Viewer         parent;
    private        DataTableModel model;
    private        Container      main;
    private        JTable         table;

    

    /**
     * constructor:
     *
     * @param parent The parent of this frame
     */
    private TableViewFrame( Viewer parent )
    {
	this.parent = parent;
	
	main = this.getContentPane();
	
	setTitle("FSMvis Selected Data");
	
	table = new JTable();

	JScrollPane scroll = new JScrollPane(table);
	
	main.add(scroll);

	this.setSize(new Dimension(700, 500));

	addWindowListener(new WindowHandler(this));
    }
    
    
    
    /**
     * Called whenever a component wants a table view frame, this ensures 
     * that it is singleton
     *
     * @param parent the parent frame
     */
    public static TableViewFrame getInstance( Viewer parent )
    {
	if ( TableViewFrame.instance == null || instance.parent != parent )
	    instance = new TableViewFrame(parent);

	return instance;
    }

    
    /**
     * Called when the selection has changed and a component wants to view 
     * this data
     *
     *
     */
    public void update()
    {
	model = new DataTableModel(parent.getModel().getDataItemCollection(), 
				 new ArrayList(parent.getSelectionHandler().getSelectedIndices()));
	
	table.setModel(model);
	JScrollPane scroll = new JScrollPane(table);
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	this.main.add(scroll);
	System.out.println(table.getRowCount()+"  "+table.getColumnCount());
	
	

	this.setVisible(true);
    }

    
    
    private class DataTableModel extends AbstractTableModel
    {
	private ArrayList          indices;
	
	private DataItemCollection dataItems;

	
	
	/**
	 * constructor:
	 *
	 *
	 */
	private DataTableModel( DataItemCollection dataItems, 
				ArrayList indices )
	{
	    this.dataItems = dataItems;
	    this.indices = indices;
	}
	

	
	/**
	 * Returns the number of rows in this table
	 *
	 * @return The number of rows
	 */
	public int getRowCount()
	{
	    return indices.size();
	}



	/**
	 * Returns the number of columns in this table
	 * 
	 * @return The number of columns
	 */
	public int getColumnCount()
	{
	    return dataItems.getTypes().size();
	}
	


	/**
	 * Returns the object at row and column in the data set
	 *
	 * @param row the required row
	 * @param column the required column
	 * @return The value at this location
	 */
	public Object getValueAt(int row, int column)
	{
	    int index = ((Integer)indices.get(row)).intValue();

	    return dataItems.getDataItem(index).getValues()[column];
	}
	
	

	/**
	 * Returns the name of a column
	 *
	 * @param column the index of the column
	 * @return The name of this column
	 */
	public String getColumnName(int column)
	{
	    return (String)dataItems.getFields().get(column);
	}
 
    }
    
}
