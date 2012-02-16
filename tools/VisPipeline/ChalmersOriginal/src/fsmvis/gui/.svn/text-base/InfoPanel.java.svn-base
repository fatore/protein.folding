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
 * InfoPanel
 *
 *
 */
 
package fsmvis.gui; 

import fsmvis.engine.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InfoPanel extends JPanel implements Runnable
{
    
    protected          Viewer parent;
    
    protected          JTextField runningTime;
    protected          JTextField dataSize;
    protected          JTextField layoutSize;
    protected          JTextField velocity;
    protected          JTextField error;
    protected           JTextField stress;

    protected          Thread     me;
    protected volatile boolean    running = false;
    protected          long       delay   = 1000;
    
    private            double     avgVel  = 0.0;
    private            double     avgErr  = 0.0;
   
    private            long       startTime = 0;
    private            long       runTime   = 0;
 

    // for printing out time at certain intervals (for stress calculations)
    private int timeCount=0;
    private long exactTime =0;
    private boolean notDone = true; //don't print seconds more than once
    private int currentMultiple = 0;
    private int multiplesOf = 50;
    private int lastSize = 0;
    private int increaseStep = 100;


    /**
     * constructor:
     *
     * @param parent The parent frame for this panel
     */
    public InfoPanel(Viewer parent)
    {
	this.parent = parent;

	this.setPreferredSize(new Dimension(200, 200));
	this.setSize(200, 200);
	
	this.setBorder(new TitledBorder(LineBorder.createGrayLineBorder(), 
					"Engine Information",
					TitledBorder.DEFAULT_JUSTIFICATION, 
					TitledBorder.DEFAULT_POSITION,
					Viewer.bold14));
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel timeLabel = new JLabel("Running Time: ");
	timeLabel.setFont(Viewer.point9);
	
	runningTime =  new JTextField(9);
	runningTime.setEditable(false);
	runningTime.setFont(Viewer.point9);
	
	timePanel.add( timeLabel );
	timePanel.add( Box.createHorizontalGlue() );
	timePanel.add( runningTime );
	
	JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel dataLabel = new JLabel("Data Size: ");
	dataLabel.setFont(Viewer.point9);
	
	dataSize = new JTextField(9);
	dataSize.setEditable(false);
	dataSize.setFont(Viewer.point9);
	
	dataPanel.add(dataLabel);
	dataPanel.add( Box.createHorizontalGlue() );
	dataPanel.add(dataSize);
	
	JPanel laySizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel laySizeLabel = new JLabel("Layout Size: ");
	laySizeLabel.setFont(Viewer.point9);

	layoutSize = new JTextField(9);
	layoutSize.setEditable(false);
	layoutSize.setFont(Viewer.point9);
	
	laySizePanel.add(laySizeLabel);
	laySizePanel.add( Box.createHorizontalGlue() );
	laySizePanel.add(layoutSize);
	
	JPanel velPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel velLabel = new JLabel("Avg. Velocity: ");
	velLabel.setFont(Viewer.point9);
	
	velocity = new JTextField(9);
	velocity.setEditable(false);
	velocity.setFont(Viewer.point9);
	
	velPanel.add( velLabel );
	velPanel.add( Box.createHorizontalGlue() );
	velPanel.add( velocity );
	
	JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel errLabel = new JLabel("Avg. Error: ");
	errLabel.setFont(Viewer.point9);
	
	error = new JTextField(9);
	error.setEditable(false);
	error.setFont(Viewer.point9);
	
	errPanel.add(errLabel);
	errPanel.add( Box.createHorizontalGlue() );
	errPanel.add(error);
	
	this.add(timePanel);
	this.add(dataPanel);
	this.add(laySizePanel);
	this.add(velPanel);
	this.add(errPanel);
	
	this.add(Box.createVerticalGlue());
	
	me = new Thread(this);
	me.start();
    }
    
    
    
    /**
     * Code to be executed by this classes thread
     *
     */
    public void run()
    {
	try {
	while ( running ) {
	    
	    // update the contents of the textfields 
	    
	    int layoutSize = parent.getModel().getDataItemCollection().
		                                                    getSize();
	    if ( parent.getModel().getDisplayableItems() != null )
		layoutSize = parent.getModel().getDisplayableItems().size();
	    
	    setLayoutSize( layoutSize );
	    
	    long t = runTime;
	    if ( startTime != 0 )
		t += System.currentTimeMillis() - startTime;

	    setRunningTime( t );
	    
	
	    
// 	    if (!((parent.getModelType()).equals("InterpolateSampleModel") && ((InterpolateSampleModel)(parent).getModel()).isInterpolating()) && !((parent.getModelType()).equals("KMeansInterp") && ((InterpolateSampleModel)(parent).getModel()).isInterpolating())) {
// 
		avgVel = (avgVel * 5.0 + parent.getModel().getApproxVelocity())/6.0;

		setVelocity( avgVel );

		avgErr = (avgErr * 5.0 + parent.getModel().getApproxError()) / 6.0; 

		setError( avgErr );
// 	    }
	    try {
		    
		Thread.sleep(delay);
	    }
	    catch ( InterruptedException ie) {
		ie. printStackTrace();
		System.out.println("there was an error sleeping our thread");
	    }
	}
	} catch ( Exception ie) { System.out.println("Error in Info Panel"); ie.printStackTrace(); parent.end();}
	
    }
    
    
    
    /**
     * Sets the state of this panel to be the boolean state
     *
     * @param enable The new state of the panel
     */
    public void setEnabled(boolean enable)
    {
	if ( enable )
	    enable();
	else
	    disable();
    }


    
    /**
     * Enables this window, makes it active
     *
     */
    public void enable()
    {
	runningTime.setEnabled(true);
	dataSize.setEnabled(true);
	layoutSize.setEnabled(true);
	velocity.setEnabled(true);
	error.setEnabled(true);
	
	running = true;
	
	me = new Thread(this);
	
	me.start();
	
	
	
	setDataSize( parent.getModel().getDataItemCollection().getSize() );
    }
    
    
    /**
     * Disables this window, stops it updating
     *
     */
    public void disable()
    {
	
	running = false;
	
	try {
	    
	    me.join();
	}
	catch (InterruptedException ie) {
	    ie.printStackTrace();
	}
	
	runningTime.setEnabled(false);
	dataSize.setEnabled(false);
	layoutSize.setEnabled(false);
	velocity.setEnabled(false);
	error.setEnabled(false);
	
    }

    
    
    /**
     * Called when the layout algorithm is started, starts the timer in this
     * thread
     *
     */
    public void start()
    {
	startTime = System.currentTimeMillis();
    }



    /**
     * Called when the layout algorithm is stopped, stops the timer in this
     * thread
     *
     */
    public void stop()
    {
	runTime += System.currentTimeMillis() - startTime;
	
	startTime = 0;
    }


    
    /**
     * Resets the timers
     *
     */
    public void reset()
    {
	runTime   = 0;
	startTime = 0;
    }

    
    
    /**
     * Accessor method to set the Running time field
     *
     * @param err The new running time
     */
    public void setRunningTime( long time )
    {
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SS");
		
	runningTime.setText( formatter.format(new Date(time)) );
	timeCount = (int)(time/1000);
	exactTime=time;

    }
    
    

    /**
     * Accessor method to set the data size text field
     *
     * @param err The new data size
     */
    public void setDataSize( int size )
    {
	dataSize.setText( Integer.toString(size) );
    }
    
    

    /**
     * Accessor method to set the layout size text field
     *
     * @param err The new layout size
     */
    public void setLayoutSize( int size )
    {
	layoutSize.setText( Integer.toString(size) );
    }
    
    

    /**
     * Accessor method to set the average velocity text field
     *
     * @param err The new average velocity
     */
    public void setVelocity(double vel)
    {
	String velo = Double.toString(vel);
	
	if ( velo.length() > 6 )
	    velo = velo.substring(0, 6);
		
	velocity.setText(velo);
    }
    
    
    
    /**
     * Accessor method to set the average error text field
     *
     * @param err The new error value
     */
    public void setError(double err)
    {
	String errStr = Double.toString(err);
	if ( errStr.length() > 6 )
	    errStr = errStr.substring(0, 6);
	
	error.setText(errStr);
    }

    /**
     * get the current elapsed time in seconds
     *
     * @return current elapsed time in seconds
     */
    public int getCurrentTime() {
	return timeCount;
    }

     /**
     * get the current elapsed time
     *
     * @return current exact time
     */
    public long getExactTime() {
	return (System.currentTimeMillis() - startTime);
    }   

}
