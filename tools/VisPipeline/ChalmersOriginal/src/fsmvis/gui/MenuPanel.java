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
 * MenuPanel
 *
 *
 */
 
package fsmvis.gui;   


import fsmvis.data.DataItemCollection;
import fsmvis.ColourScales.ColourScales;

import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent; 
import java.awt.FlowLayout;

import java.util.Iterator;
import java.util.ArrayList;



public class MenuPanel extends JPanel implements ActionListener,
						 ItemListener
{
    
    protected Viewer            parent;
    
    protected JMenuBar          menuBar;
    
    protected JMenu             file;
    protected JMenuItem         newLayout;
    protected JMenuItem         load;
    protected JMenuItem         save;
    protected JMenuItem         export;
    protected JMenuItem         quit;
    
    protected JMenu             display;
    protected JCheckBoxMenuItem animate;
    protected JCheckBoxMenuItem drawTrails;
    protected JCheckBoxMenuItem information;
    
    protected JMenu             view;
    protected JMenu             schemeSubmenu;
    protected JMenu             overSubmenu;
    protected JMenuItem         info;
    protected JMenuItem         reset;
    
    protected JMenu             help;
    protected JMenuItem         contents;
    
    protected String[]          colours = { "HeatedObject", "BTC", "LOCS", 
					    "Rainbow", "BTY", "Magenta", "OCS"};
    
    

    public MenuPanel( Viewer parent )
    {
	this.parent = parent;
	
	setLayout(new FlowLayout(FlowLayout.LEFT));
	
	//Create the menu bar.
	menuBar = new JMenuBar();
	parent.setJMenuBar(menuBar);
    
	
	//  Build the file menu.
	
	file = new JMenu("File");
	file.getAccessibleContext().setAccessibleDescription(
		      "Create new layouts, load & save layouts");
	menuBar.add(file);
	
	newLayout = new JMenuItem("New Layout");
	newLayout.getAccessibleContext().setAccessibleDescription(
						 "Creates a New Layout");
	newLayout.setActionCommand("new");
	newLayout.addActionListener(this);
	file.add(newLayout);
	
	
	load = new JMenuItem("Load");
	load.getAccessibleContext().setAccessibleDescription(
						 "Load a Layout");
	load.setActionCommand("load");
	load.addActionListener(this);
	file.add(load);
	
	
	save = new JMenuItem("Save");
	save.getAccessibleContext().setAccessibleDescription(
						 "Saves a Layout");
	save.setActionCommand("save");
	save.addActionListener(this);
	save.setEnabled(false);
	file.add(save);
	
    export = new JMenuItem("Export");
    export.getAccessibleContext().setAccessibleDescription(
                         "Export a Layout");
    export.setActionCommand("export");
    export.addActionListener(this);
    export.setEnabled(false);
    file.add(export);

	file.addSeparator();

	quit = new JMenuItem("Quit");
	quit.getAccessibleContext().setAccessibleDescription(
						 "Quits the program");
	quit.setActionCommand("quit");
	quit.addActionListener(this);
	file.add(quit);


	// Build the Display menu
	
	display = new JMenu("Display");
	menuBar.add(display);
	
	information = new JCheckBoxMenuItem("Information", true);
	information.addItemListener(this);
	display.add(information);
	
	animate = new JCheckBoxMenuItem("Animate", true);
	animate.addItemListener(this);
	display.add(animate);
	
	drawTrails = new JCheckBoxMenuItem("Draw Trails", true);
	drawTrails.addItemListener(this);
	display.add(drawTrails);
	
	

	
	// Build the View menu
	
	view = new JMenu("View");
	menuBar.add(view);
	
	schemeSubmenu = new JMenu("Colour Scheme");
	
	JRadioButtonMenuItem radio;
	ButtonGroup group = new ButtonGroup();
	
	for ( int i = 0 ; i < colours.length ; i++ ) {

	    String name = colours[i];
	    radio = new JRadioButtonMenuItem( name );
	    radio.setActionCommand("Color"+name);
	    radio.addActionListener(this);
	    radio.setSelected(true);
	    group.add(radio);
	    schemeSubmenu.add(radio);
	}
	
	//set the scatter panel to use this
	parent.getScatterPanel().setColourScheme(colours[0]);


	view.add(schemeSubmenu);
	
	overSubmenu = new JMenu("Colour Over");
	
	view.add(overSubmenu);
	
	reset = new JMenuItem("Reset");
	reset.setActionCommand("reset");
	reset.addActionListener(this);
	view.add(reset);

	
	menuBar.add( Box.createHorizontalGlue() );

	// Build the help menu
	
	help = new JMenu("Help");
	menuBar.add(help);

	contents = new JMenuItem("Contents");
	contents.setActionCommand("contents");
	contents.addActionListener(this);
	help.add(contents);
    }
    
    
    
    /**
     * called when the menu panel is to be disabled over enabled, modifies
     * how some menu item are displayed, causes the save item to be enabled
     * and the overSubmenu to be generated.
     *
     * @param enable The new state of the menu
     */
    public void setEnabled(boolean enable)
    {
	if ( enable )
	    enable();
	else
	    disable();
    }
    


    /**
     * Enables this menu panel
     *
     */
    public void enable()
    {
	save.setEnabled(true);
    export.setEnabled(true);
	
	overSubmenu.removeAll();
	
	ButtonGroup group = new ButtonGroup();
	JRadioButtonMenuItem radio;
	
	// get the names of the fields in the data item collection
	ArrayList fields = parent.getModel().getDataItemCollection().getFields();
	ArrayList types = parent.getModel().getDataItemCollection().getTypes();
	
	boolean first = true;
	
	for ( int i = 0 ; i < fields.size() ; i++ ) {
	    
	    int typeId = ((Integer)types.get(i)).intValue();
	    
	    // only add fields of type date, integer or double
	    if ( typeId == DataItemCollection.DATE 
		 || typeId == DataItemCollection.INTEGER 
		 || typeId == DataItemCollection.DOUBLE ) {
		
		String field = (String)fields.get(i);
		radio = new JRadioButtonMenuItem( field, first);
		radio.setActionCommand("Over"+field);
		radio.addActionListener(this);
		group.add(radio);
		overSubmenu.add(radio);
				
		//set this to the field to colour over
		if ( first )
		    parent.getScatterPanel().setColourField(field);
	    
		first = false;
	    }
	}	
    }

    
    
    /**
     * Accessor method to set the state of the animate checkbox item
     *
     * @param state The desired state of the checkbox item
     */
    public void setAnimateState(boolean state)
    {
	animate.setState(state);
    }
    


    /**
     * Disables the menu panel and turns off a lot of features
     *
     */
    public void disable()
    {
	save.setEnabled(false);
    export.setEnabled(false);
	
	overSubmenu.removeAll();
    }

    
    
    /**
     * Required by an ActionListener, is called whenever a button is pressed
     *
     * @param e The action event that caused this to be called
     */
    public void actionPerformed(ActionEvent e)
    {
	if ( e.getActionCommand().equals("new") ) {
	    
	    parent.getInfoPanel().disable();
	    
	    parent.getScatterPanel().pause();
	    
	    FileHandlerFrame fileLoader = 
		DataFileLoaderFrame.getInstance(parent); 

	}
	else if ( e.getActionCommand().equals("load") ) {
	    
	    
	    FileHandlerFrame fileLoader = 
		LayoutFileLoaderFrame.getInstance(parent); 
	}
	else if ( e.getActionCommand().equals("save") ) {
	    
	    
	    FileHandlerFrame saveFile  = 
		LayoutFileSaverFrame.getInstance(parent);
	}
	else if ( e.getActionCommand().equals("export") ) {


	    FileHandlerFrame saveFile  =
		LayoutFileExporterFrame.getInstance(parent);
	}
	else if ( e.getActionCommand().equals("quit") ) {
	    
	    if ( DialogBox.confirmDialog(this, 
					 "Are you sure you want to exit"+
	    				 " FSM Visualiser ?") )
	    parent.dispatchEvent(new WindowEvent(parent,
						 WindowEvent.WINDOW_CLOSING));
	}
	
	else if ( e.getActionCommand().equals("reset") ) {
	    
	    parent.getScatterPanel().reset();
	}
	// changed colour scheme
	else if ( e.getActionCommand().startsWith("Color") ) {
	    
	    String colour = e.getActionCommand().substring(5);
	    
	    parent.getScatterPanel().setColourScheme(colour);
	}
	// changed axis to colour over
	else if ( e.getActionCommand().startsWith("Over") ) {
	    String field = e.getActionCommand().substring(4);
	    
	    parent.getScatterPanel().setColourField(field);
	    
	}
	
    }
    
    
    
    /**
     * called when the state of an item has changed, required by ItemListener
     *
     */
    public void itemStateChanged(ItemEvent e) 
    {
        
        Object source = e.getItemSelectable();
	
	if ( source == drawTrails ) 
	    
	    parent.getScatterPanel().setDrawTrails( 
				e.getStateChange() == ItemEvent.SELECTED );
	
	else if ( source == animate )
	    
	    parent.getScatterPanel().setAnimate( 
				e.getStateChange() == ItemEvent.SELECTED );
	
	else if ( source == information )
	    
	    parent.getInfoPanel().setEnabled(
				e.getStateChange() == ItemEvent.SELECTED );
    }
}
