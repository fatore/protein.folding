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
 * SelectAlgorithmFrame
 * 
 *
 */
 
package fsmvis.gui;

import fsmvis.data.DataItemCollection;
import fsmvis.engine.LayoutModel;
import fsmvis.engine.SpringModel;
import fsmvis.engine.NeighbourAndSampleModel;
import fsmvis.engine.InterpolateSampleModel;
import fsmvis.engine.EndCriteria;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;


public class SelectAlgorithmFrame extends JFrame implements ActionListener
{
    
    protected JRadioButton       springModel;
    protected JRadioButton       neighbModel;
    protected JRadioButton       interpModel;
    protected ButtonGroup        group;
    protected JButton            ok; 
    protected JButton            cancel;
    protected JButton            back;
    protected Container          main;
    protected Viewer             parent;
    protected DataItemCollection dataItems;
    protected ButtonGroup        normGroup;
    protected JRadioButton       doNorm;
    protected JRadioButton       doNotNorm;
    protected boolean            normalise;
    protected ButtonGroup        endGroup; 
    protected JRadioButton       justIterations;
    protected JRadioButton       justVelocity;
    protected JRadioButton       justConstant;
    protected JRadioButton       all;
    protected JRadioButton       any;
    protected JTextField         vel;
    protected JTextField         its;
    protected JTextField         con;


    /**
     * constructor: takes the dataItemCollection as input so that it can hand 
     * it over to the correct layout algorithm
     *
     * @param dataItems The data item collection to use
     */
    public SelectAlgorithmFrame ( DataItemCollection dataItems, 
				  Viewer parent )
    {
	this.parent = parent;
	this.dataItems = dataItems;
	
	main = this.getContentPane();
	main.setLayout(new FlowLayout());
	
	setTitle("FSMvis");
	setSize(520, 600);
	setLocation(50, 50);

	JPanel radioPanel = new JPanel();
	radioPanel.setBorder( new TitledBorder(
				  LineBorder.createGrayLineBorder(),
		 		  "Select Algorithm"));
	 
	radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
	
	springModel = new JRadioButton("Spring Model [ O(n^2) per iteration]",
				       false);
	neighbModel = new JRadioButton("Neighbour And Sample Model [ O(n) "+
				       "per iteration]", false);
	interpModel = new JRadioButton("Hybrid Model [ O(n root n) overall ]", true);
	springModel.setActionCommand("SpringModel");
	neighbModel.setActionCommand("NeighbourAndSampleModel");
	interpModel.setActionCommand("InterpolateSampleModel");

	group = new ButtonGroup();
	group.add(springModel);
	group.add(neighbModel);
	group.add(interpModel);

	radioPanel.add(springModel);
	radioPanel.add(neighbModel);
	radioPanel.add(interpModel);

	//setup normalisation decision panel
	//   MJC: this is not used at the moment
	JPanel normPanel = new JPanel();
	normPanel.setBorder( new TitledBorder(
				  LineBorder.createGrayLineBorder(),
				  "Normalise distance matrix?"));
	normPanel.setLayout(new BoxLayout(normPanel, BoxLayout.X_AXIS));
	doNorm = new JRadioButton("Normalise", false);
	doNotNorm = new JRadioButton("No normalisation", true);	
	doNorm.setActionCommand("yes");
	doNotNorm.setActionCommand("no");
	normGroup = new ButtonGroup();
	normGroup.add(doNorm);
	normGroup.add(doNotNorm);
	normPanel.add(doNorm);
	normPanel.add(doNotNorm);

	normalise = false;


	//setup completion criteria panel  
	//   MJC: this is not used at the moment
	JPanel endCritPanel = new JPanel();
	endCritPanel.setLayout(new BoxLayout(endCritPanel, BoxLayout.Y_AXIS));
	justIterations = new JRadioButton("After multiple (x) of N iterations", false);
	justVelocity = new JRadioButton("After avg. velocity < y",false);
	justConstant = new JRadioButton("After constant number (z) of iterations",true);
	any = new JRadioButton("Any of above", false);
	all = new JRadioButton("All of above", false);
	justIterations.setActionCommand("1");
	justVelocity.setActionCommand("2");
	justConstant.setActionCommand("3");
	any.setActionCommand("4");
	all.setActionCommand("5");
	endGroup = new ButtonGroup();
	endGroup.add(justIterations);
	endGroup.add(justVelocity);
	endGroup.add(justConstant);
	endGroup.add(any);
	endGroup.add(all);
	endCritPanel.add(justIterations);
	endCritPanel.add(justVelocity);
	endCritPanel.add(justConstant);
	endCritPanel.add(any);
	endCritPanel.add(all);

	JPanel endSizePanel = new JPanel();
	JPanel eSPTop = new JPanel();
	JPanel eSPMid = new JPanel();
	JPanel eSPBottom = new JPanel();
	JPanel eSPUnder = new JPanel();
	its = new JTextField("1",3); 
	vel = new JTextField("0.03",4); 
	con = new JTextField("250",4);
	JLabel itsFixed = new JLabel(" * N iterations"); 
	JLabel velFixed = new JLabel(" minimum velocity");
	JLabel conFixed = new JLabel(" iterations");
	endSizePanel.setLayout(new BoxLayout(endSizePanel, BoxLayout.Y_AXIS));
	eSPTop.setLayout(new BoxLayout(eSPTop, BoxLayout.X_AXIS));
	eSPMid.setLayout(new BoxLayout(eSPMid, BoxLayout.X_AXIS));
	eSPBottom.setLayout(new BoxLayout(eSPBottom, BoxLayout.X_AXIS));
	eSPUnder.setLayout(new BoxLayout(eSPUnder, BoxLayout.X_AXIS));

	eSPTop.add(new JLabel("x: "));
	eSPMid.add(new JLabel("y: "));
	eSPBottom.add(new JLabel("z: "));
	eSPTop.add(its);
	eSPMid.add(vel);
	eSPBottom.add(con);
	eSPTop.add(itsFixed);
	eSPMid.add(velFixed);
	eSPBottom.add(conFixed);

	endSizePanel.add(eSPTop);
	endSizePanel.add(eSPMid);
	endSizePanel.add(eSPBottom);
	endSizePanel.add(eSPUnder);

	JPanel endPanel = new JPanel();
	endPanel.setBorder( new TitledBorder( LineBorder.createGrayLineBorder(), "Completion criteria for spring model"));
	endPanel.setLayout(new BorderLayout());
	endPanel.add(endCritPanel, BorderLayout.WEST);
	endPanel.add(endSizePanel, BorderLayout.EAST);

	// setup listener for end criteria panel
	RadioListener endRL = new RadioListener(this);
	justIterations.addActionListener(endRL);
	justVelocity.addActionListener(endRL);
	justConstant.addActionListener(endRL);
	any.addActionListener(endRL);
	all.addActionListener(endRL);
	

	//setup the control buttons
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new BorderLayout());
	buttonPanel.setPreferredSize(new Dimension(400,40));
	
	ok     = new JButton("OK");
	cancel = new JButton("Cancel");
	back   = new JButton("Back");

	ok.setActionCommand("ok");
	cancel.setActionCommand("cancel");
	back.setActionCommand("back");

	ok.addActionListener(this);
	cancel.addActionListener(this);
	back.addActionListener(this);

	JPanel lButts = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel rButts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	lButts.add(back);
	
	rButts.add(ok);
	rButts.add(cancel);
	
	buttonPanel.add(lButts, BorderLayout.WEST);
	buttonPanel.add(rButts, BorderLayout.EAST);
	
	JPanel bottomPanel = new JPanel();
	bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
	bottomPanel.add(endPanel);  // just use defaults for now
	bottomPanel.add(normPanel);
	bottomPanel.add(buttonPanel);


	main.setLayout(new BorderLayout());
	
	main.add(radioPanel, BorderLayout.CENTER);
	main.add(bottomPanel, BorderLayout.SOUTH);
	
	addWindowListener(new WindowHandler(this));
	
	this.pack();
	this.show();
	
	hideEndC("con");
    }
    
    protected void editAllEndC() {
	its.setEditable(true);
	vel.setEditable(true);
	con.setEditable(true);
    }

    protected void hideEndC(String s) {
	editAllEndC();
	if (s.equals("its")) {
	    vel.setEditable(false);
	    con.setEditable(false);
	} else if (s.equals("con")) {
	    vel.setEditable(false);
	    its.setEditable(false);	 
	}else {//s == "vel"
	    its.setEditable(false);
	    con.setEditable(false);	}
    }
    
    /**
     *
     *
     * @param alg
     */
    protected void loadModel(String alg)
    {
	this.setVisible(false);
	
	int endC = Integer.parseInt(endGroup.getSelection().getActionCommand());
	int is=0;
	double vl=0.0;
	int conIs=0;
	try {
	    is = Integer.parseInt(its.getText());
	    vl = Double.parseDouble(vel.getText());
	    conIs = Integer.parseInt(con.getText());
	} catch (NumberFormatException e) {
	    System.err.println("number format exception");
        }

	EndCriteria ec = new EndCriteria(endC, is, vl, conIs, dataItems.getSize());
	LayoutModel model;
	String end = endGroup.getSelection().getActionCommand();
	
	 if ( alg.equals("SpringModel") ) {
	    model = new SpringModel(dataItems, ec, parent);
	    if (normalise)
		((SpringModel)model).doNormalise();
	}
	else if ( alg.equals("NeighbourAndSampleModel") ) {
	    model = new NeighbourAndSampleModel(dataItems, ec, parent);
	    if (normalise)
		((NeighbourAndSampleModel)model).doNormalise();
	}
	 else {//interp
	     model = new InterpolateSampleModel(dataItems, ec, parent);
	     if (normalise)
		 ((InterpolateSampleModel)model).doNormalise();
	 }
	 parent.setModel(model, alg);

	this.dispose();
    }

    
    public void actionPerformed(ActionEvent e) 
    {
	
	if ( e.getActionCommand().equals("ok") ) {
	    
	    String alg = group.getSelection().getActionCommand();
	    String norm = normGroup.getSelection().getActionCommand();
	    if (norm.equals("no"))
		normalise=false;
	    loadModel(alg);
	    
	}
	if ( e.getActionCommand().equals("cancel") )
	    dispatchEvent(new WindowEvent(this,
					  WindowEvent.WINDOW_CLOSING));
	
	
	if ( e.getActionCommand().equals("back") ) {
	    
	    this.setVisible(false);
	    
	    FileHandlerFrame f = DataFileLoaderFrame.getInstance(parent);
	    
	    this.dispose();
	}
    }
}


class RadioListener implements ActionListener {

    SelectAlgorithmFrame saf;

    public RadioListener(SelectAlgorithmFrame sf) {
       saf=sf;
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getActionCommand().equals("1") ) {
	   saf.hideEndC("its");
	}
	else if ( e.getActionCommand().equals("2") ){
           saf.hideEndC("vel");
	}
	else if ( e.getActionCommand().equals("3") ){
	    saf.hideEndC("con");
	}
	else
           saf.editAllEndC();
    }


}
