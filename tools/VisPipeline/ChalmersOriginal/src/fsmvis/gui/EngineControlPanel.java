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
 * EngineControlPanel
 *
 * This class is a JPanel that provides methods for controlling the 
 * behaviour of the layout engine, this includes being able to dynamically
 * change certain parameters that affect the way a layout works, such as the 
 * freeness of damping factor
 *
 *
 */

package fsmvis.gui;


import fsmvis.engine.LayoutModel;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.Hashtable;
import java.awt.Font;



public class EngineControlPanel extends JPanel implements ChangeListener
{
    
    protected Viewer  parent;
    protected JSlider freeness;
    protected JSlider spring;
    protected JSlider damping;
    
    protected int SLIDER_MAX = 20;


    public EngineControlPanel( Viewer parent )
    {
	this.parent = parent;
	
	this.setPreferredSize(new Dimension(280, 200));
	this.setSize(280, 200);
		
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	this.setBorder(new TitledBorder(LineBorder.createGrayLineBorder(), 
					"Engine Controls",
					TitledBorder.DEFAULT_JUSTIFICATION, 
					TitledBorder.DEFAULT_POSITION,
					Viewer.bold14));
	
	//setup labels
	Hashtable labelTable = new Hashtable();
	JLabel nought = new JLabel("0.0");
	JLabel quarter = new JLabel("0.25");
	JLabel half = new JLabel("0.5");
	JLabel threeQuarters = new JLabel("0.75");
	JLabel one = new JLabel("1.0");

	nought.setFont( parent.point8 );
	quarter.setFont( Viewer.point8 );
	half.setFont( Viewer.point8 );
	threeQuarters.setFont( Viewer.point8 );
	one.setFont( Viewer.point8 );
	
	labelTable.put( new Integer( 0 ),              nought );
	labelTable.put( new Integer( SLIDER_MAX / 4 ), quarter );
	labelTable.put( new Integer( SLIDER_MAX / 2 ), half );
	labelTable.put( new Integer( (3 * SLIDER_MAX) / 4 ), threeQuarters );
	labelTable.put( new Integer( SLIDER_MAX ),     one );


	// setup the FREENESS slider
	freeness = new JSlider(JSlider.HORIZONTAL, 0, SLIDER_MAX, 0);
	freeness.setMajorTickSpacing( 2 );
	freeness.setMinorTickSpacing( 1 );
	freeness.setPaintTicks(true);
	freeness.addChangeListener(this);
	freeness.setLabelTable( labelTable );
	freeness.setPaintLabels(true);
	freeness.setBorder( new TitledBorder(new EmptyBorder(1,1,1,1),
					     "Freeness",
					     TitledBorder.DEFAULT_JUSTIFICATION, 
					     TitledBorder.DEFAULT_POSITION,
					     Viewer.point10));
	freeness.setEnabled(false);


	// setup the SPRING FORCE slider
	spring = new JSlider(JSlider.HORIZONTAL, 0, SLIDER_MAX, 0);
	spring.setMajorTickSpacing( 2 );
	spring.setMinorTickSpacing( 1 );
	spring.setPaintTicks(true);
	spring.setLabelTable(labelTable);
	spring.setPaintLabels(true);
	spring.setBorder( new TitledBorder(new EmptyBorder(1,1,1,1), 
					   "Spring Force",
					   TitledBorder.DEFAULT_JUSTIFICATION, 
					   TitledBorder.DEFAULT_POSITION,
					   Viewer.point10));
	spring.addChangeListener(this);
	spring.setEnabled(false);
	

	//setup the DAMPING FORCE slider
	damping = new JSlider(JSlider.HORIZONTAL, 0, SLIDER_MAX, 0);
	damping.setMajorTickSpacing( 2 );
	damping.setMinorTickSpacing( 1 );
	damping.setPaintTicks(true);
	damping.setLabelTable(labelTable);
	damping.setPaintLabels(true);
	damping.setBorder( new TitledBorder(new EmptyBorder(1,1,1,1),  
					    "Damping Force",
					    TitledBorder.DEFAULT_JUSTIFICATION, 
					    TitledBorder.DEFAULT_POSITION,
					    Viewer.point10));
	damping.addChangeListener(this);
	damping.setEnabled(false);
	
	add(freeness);
	add(spring);
	add(damping);
    }

    
    public void setFreeness(double freeVal)
    {
	freeness.setValue( (int)(freeVal * (double)SLIDER_MAX));
	
	freeness.setEnabled(true);
    }
    
    
    public void setSpring(double springVal)
    {
	spring.setValue( (int)(springVal * (double)SLIDER_MAX));
	
	spring.setEnabled(true);
    }

    
    public void setDamping(double dampVal)
    {
	damping.setValue( (int)(dampVal * (double)SLIDER_MAX));
	
	damping.setEnabled(true);
    }
    
    
    /**
     * Called whenever the state of the slider is changed, required by 
     * change listener.
     *
     *
     */
    public void stateChanged(ChangeEvent e)
    {
	JSlider source = (JSlider)e.getSource();
        
	if ( !source.getValueIsAdjusting()) {
	    
	    double val = (double)source.getValue() / (double)SLIDER_MAX;
	    
	    if ( source == freeness ) {
		
		parent.getModel().getProperties().
		    setProperty(LayoutModel.FREENESS, String.valueOf(val));
		
		parent.getModel().updateValues();
	    }
	    else if ( source == spring ) { 
		
		parent.getModel().getProperties().
		    setProperty(LayoutModel.SPRING_FORCE, String.valueOf(val));
		
		parent.getModel().updateValues();
	    }
	    else if ( source == damping ) {
		
		parent.getModel().getProperties().
		    setProperty(LayoutModel.DAMPING_FACTOR, 
				String.valueOf(val));
		
		parent.getModel().updateValues();
	    }
	}
    }
}
