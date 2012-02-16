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
 * ScatterControlPanel
 * 
 * This class extends JPanel.  It provides various controls for manipulating 
 * the scatter panel.  These include being able to control the appearance of 
 * the scatter panel, turn trails on & off, start and stop the layout model
 *
 */
 
package fsmvis.gui; 


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Hashtable;



public class ScatterControlPanel extends JPanel implements ActionListener,
							   ChangeListener
{
    protected JButton      start;
    protected JButton      stop;
    protected JTextField   iterations;
    protected Viewer       parent;
    protected JSlider      speed;
    
    
    /**
     * constructor: creates a new ScatterControlPanel
     *
     * @param parent The scatter control panel parent
     */
    public ScatterControlPanel(Viewer parent)
    {
	this.parent = parent;
	
	this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	
	start = new JButton("start");
	start.setEnabled(false);
	start.setActionCommand("start");
	start.addActionListener(this);
	start.setFont( Viewer.point10 );
	
	stop = new JButton("stop");
	stop.setEnabled(false);
	stop.setActionCommand("stop");
	stop.addActionListener(this);
	stop.setFont( Viewer.point10 );
	
	iterations  = new JTextField(5);
	iterations.setEditable(false);
	iterations.setFont( Viewer.point10 );
	iterations.setMaximumSize(new Dimension(60, 20));
	JLabel iters = new JLabel("Iterations: ");
	iters.setFont( Viewer.point10 );
	
	
	speed = new JSlider(JSlider.HORIZONTAL, 0, 4, 4);
	speed.setSnapToTicks(true);
	speed.setMajorTickSpacing(1);
	speed.setPaintTicks(true);

	//Create the label table
	Hashtable labelTable = new Hashtable();
	
	JLabel slow = new JLabel("Slow");
	JLabel fast = new JLabel("fast");
	slow.setFont( Viewer.point9 );
	fast.setFont( Viewer.point9 );
	labelTable.put( new Integer( 0 ), slow );
	labelTable.put( new Integer( 4 ), fast );
	speed.setLabelTable( labelTable );
	speed.setPaintLabels(true);
	speed.addChangeListener(this);
	speed.setEnabled(false);
	
	add(start);
	add(Box.createRigidArea(new Dimension(5, 1)));
	add(stop);
	add(Box.createRigidArea(new Dimension(5, 1)));
	add(iters);
	add(iterations);
	add(Box.createRigidArea(new Dimension(5, 1)));
	add(speed);

	this.setBorder( new TitledBorder(
			    LineBorder.createGrayLineBorder(), 
			    "Controls",
			    TitledBorder.DEFAULT_JUSTIFICATION, 
			    TitledBorder.DEFAULT_POSITION,
			    Viewer.bold14));
			    

   }
    
    

    /**
     * Sets the number of iterations that have been performed and updates 
     * the text field
     *
     * @param numIterations The number of iterations performed
     */    
    public void setIterations(int numIterations)
    {
	iterations.setText(Integer.toString(numIterations));
    }

    

    public void enable()
    {
	start.setEnabled(true);
	speed.setEnabled(true);
	setIterations(0);
    }


    public void disable()
    {
	start.setEnabled(false);
	stop.setEnabled(false);
	speed.setEnabled(false);
    }
    

    /**
     * called whenever an action is performed, required by ActionListener
     *
     * @param ae The action event that triggered this
     */
    public void actionPerformed(ActionEvent ae)
    {
	if ( ae.getActionCommand().equals("start") ) {
	    
            parent.getScatterPanel().newThread(); // GR   

	    parent.getScatterPanel().start();
	    
	    parent.getInfoPanel().start();
	    
	    System.out.println(" Start");

	    start.setEnabled(false);
	    stop.setEnabled(true);
	}
	
	else if ( ae.getActionCommand().equals("stop")) {
	    
            parent.getScatterPanel().threadRunning(false); // GR    

	    parent.getScatterPanel().pause();
	    
	    parent.getInfoPanel().stop();

	    start.setEnabled(true);
	    stop.setEnabled(false);
	}
    }

    
    
    /**
     * Called whenever the state of the slider is changed, required by 
     * change listener.
     *
     */
    public void stateChanged(ChangeEvent e)
    {
	JSlider source = (JSlider)e.getSource();
        
	if ( source == speed) {
	
	    if (!source.getValueIsAdjusting()) {
		
		int val = 4 - (int)source.getValue();
		

		parent.getScatterPanel().setDelay( 
		   (int)((double)parent.getScatterPanel().MAX_DELAY * 
					   ((double)val / 4.0)) );
	    }
        }
	
    }
    
    /**
     * called whenever the state of an item is changed, required by 
     * ItemStateListener
     *
     * @param e The Item changed event
 
    public void itemStateChanged(ItemEvent e) 
    { 
         
        Object source = e.getItemSelectable();
	
	if  ( source == drawTrails ) 
	     
	     scat.setDrawTrails( e.getStateChange() == ItemEvent.SELECTED );
    } 
    */	    

    public String getIterations() {
	return iterations.getText();
    }

}
