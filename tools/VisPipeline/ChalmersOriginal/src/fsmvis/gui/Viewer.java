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
 * Viewer
 *
 * Main gui class, holds all of the other panels in the interface.  Holds 
 * references to all of these panels and provides accessor methods to access them
 *
 */
 
package fsmvis.gui; 


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.Font;



import fsmvis.engine.LayoutModel;

public class Viewer extends JFrame
{
    
    protected LayoutModel            model;
    protected ScatterPanel           scatter;
    protected ScatterControlPanel    scatControl;
    protected Container              main;
    protected MenuPanel              menu;
    protected InfoPanel              info;
    protected EngineControlPanel     engControl;
    protected SelectionControlPanel  selectControl;
    protected SelectionHandler       selectionHandler;
    

    protected String modelType;

    //some predefined fonts for the gui to use
    public static Font point8  = new Font(null, Font.PLAIN, 8);
    public static Font point9  = new Font(null, Font.PLAIN, 9);
    public static Font point10 = new Font(null, Font.PLAIN, 10);
    public static Font point11 = new Font(null, Font.PLAIN, 11);
    public static Font bold14  = new Font(null, Font.BOLD,  14);

    /**
     * constructor: Creates a new Viewer using the specified layout model
     *
     * @param model The layout model to use 
     */
    public Viewer ( LayoutModel model)
    {
	this.model = model;

	selectionHandler = new SelectionHandler();
	
	main = this.getContentPane();
	
	setTitle("FSM Visualiser");
	setSize(900, 600);
	
	scatter       = new ScatterPanel(model, this);
	
        // Stop the scatterpanel thread. GR
        
        scatter.threadRunning(false);
 
	scatControl   = new ScatterControlPanel(this);
      	
	selectControl = new SelectionControlPanel(this);
	
	engControl    = new EngineControlPanel(this);
	
	info          = new InfoPanel(this);
	
	JPanel infoAndControl = new JPanel();
	infoAndControl.setLayout(new BoxLayout(infoAndControl, 
					       BoxLayout.X_AXIS));
	infoAndControl.add(engControl);
	infoAndControl.add(info);
	
	
	menu = new MenuPanel(this);
	
	JPanel scat = new JPanel();
	scat.setLayout(new BoxLayout(scat, BoxLayout.Y_AXIS));
	scat.add(scatter);
	scat.add(scatControl);
	//scat.add(new JSeparator());
	    
	scat.add(infoAndControl);
	
		
	main.setLayout(new BorderLayout());
	main.add(menu,          BorderLayout.NORTH);
	main.add(scat,          BorderLayout.CENTER);	
	main.add(selectControl, BorderLayout.WEST);
	
	addWindowListener(new ExitingWindowHandler(this));
	
	this.pack();
	
	this.show();
    
    
    }

 
    public void setModel ( LayoutModel model, String m)
    {
	this.model = model;

       	modelType = m;
	//refresh the data items values
	model.getDataItemCollection().refresh();
	selectionHandler = 
	    new SelectionHandler(model.getDataItemCollection().getSize());
	menu.setEnabled(true);
	scatter.setModel(model);
		
	if ( engControl == null )
	    System.out.println(" engControl == null ");
	//setup the engine controls as well
	engControl.setFreeness( Double.parseDouble(model.getProperties().
				   getProperty(LayoutModel.FREENESS)) );
	engControl.setDamping( Double.parseDouble(model.getProperties().
				   getProperty(LayoutModel.DAMPING_FACTOR)) );
	engControl.setSpring( Double.parseDouble(model.getProperties().
				   getProperty(LayoutModel.SPRING_FORCE)) );
    
	selectControl.update( model.getDataItemCollection() );
	

	info.reset();
	info.enable();
	
    }
    


    
    /**
     * Accessor method to the layout model
     *
     * @return the layout model instance
     */
    public LayoutModel getModel()
    {
	return model;
    }


    
    /**
     * Accessor method for the scatter panel
     *
     * @return the scatter panel instance
     */
    public ScatterPanel getScatterPanel()
    {
	return scatter;
    }
    
    
    
    /**
     * Accessor method for the scatter control panel
     *
     * @return the scatter control panel instance
     */
    public ScatterControlPanel getScatterControlPanel()
    {
	return scatControl;
    }
    
    
    /**
     * Accessor method for the info panel
     *
     * @return the info panel instance
     */
    public InfoPanel getInfoPanel()
    {
	return info;
    }
    
    
    /**
     * Accessor method for the selection control panel
     *
     * @return the selection control panel instance
     */
    public SelectionControlPanel getSelectionControlPanel()
    {
	return selectControl;
    }
    
    

    /**
     * Accessor method for the selection handler
     *
     * @return The selection handler instance
     */
    public SelectionHandler getSelectionHandler()
    {
	return selectionHandler;
    }
    
    

    /**
     * Accessor method for the menu panel
     *
     * @return the menu panel instance
     */
    public MenuPanel getMenuPanel()
    {
	return menu;
    }


    public String getIterations() {
	return scatControl.getIterations();
    }

    /**
     * get the name of the layout model currently being used
     *
     * @return string name of model type 
     */
    public String getModelType() {
	return modelType;
    }


   public void end() {
       this.setVisible(false);
       this.dispose();
       System.exit(0);
    }

}


