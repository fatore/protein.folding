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
 * SelectionControlPanel
 *
 *
 *
 */

package fsmvis.gui;

import fsmvis.data.DataItemCollection;
import fsmvis.engine.LayoutModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.InputEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JTextField;



public class SelectionControlPanel extends JPanel 
                                   implements ActionListener
{
    
    protected Viewer parent;
    
    
    protected JScrollPane  scroll;
    protected JButton      all;
    //protected JButton      none;
    protected JButton      weights;
    protected JButton      viewSel;
    
    protected JPanel       scrollPanel;
        
    protected List         sliders;
    protected List         checkBs;
    protected List         searches;
    
    protected List         allIndices;

    private   MouseHandler mouseHandler;
    
    private   Color        highlight = new Color(169, 195, 252);
    
    protected static final int MAX_CHECKBOXES = 14;
    
    protected ArrayList colWeightsSlide, fixedWeightsSlide, colWeightsCheck, fixedWeightsCheck, colWeightsSearch, fixedWeightsSearch;
    protected int sliderCount=0, checkCount=0, searchCount=0;
    protected ArrayList typeStrings = new ArrayList();

    /**
     * constructor:
     * 
     * @param parent The parent frame of this panel
     */
    public SelectionControlPanel( Viewer parent )
    {
	this.parent = parent;
	
	this.setPreferredSize(new Dimension(400, 600));
	this.setSize(400, 600);
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	this.setBorder( new TitledBorder( LineBorder.createGrayLineBorder(), 
					  "Selection Controls"));
		
	colWeightsSlide = new ArrayList();
	fixedWeightsSlide = new ArrayList();
	colWeightsSearch = new ArrayList();
	fixedWeightsSearch = new ArrayList();
	colWeightsCheck = new ArrayList();
	fixedWeightsCheck = new ArrayList();
	JPanel buttons = new JPanel();
	buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
	buttons.setMinimumSize(new Dimension(300, 70));
	buttons.setAlignmentX( LEFT_ALIGNMENT );
	
	all = new JButton("Select All");
	all.setFont(Viewer.point9);
	all.setEnabled(false);
	all.setActionCommand("all");
	all.addActionListener(this);
	
// 	none = new JButton("Select None");
// 	none.setFont(Viewer.point9);
// 	none.setEnabled(false);
// 	none.setActionCommand("none");
// 	none.addActionListener(this);
	
	viewSel = new JButton("View Selected Data");
	viewSel.setFont(Viewer.point9);
	viewSel.setEnabled(false);
	viewSel.setActionCommand("view");
	viewSel.addActionListener(this);

	weights = new JButton("Confirm weights");
	weights.setFont(Viewer.point9);
	weights.setEnabled(false);
	weights.setActionCommand("weights");
	weights.addActionListener(this);

	buttons.add(all);
 	buttons.add(viewSel);
	buttons.add(weights);
	
	this.add(buttons);
	
	scrollPanel = new JPanel();
	scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
	scrollPanel.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
	scroll = new JScrollPane(scrollPanel);
	this.add(scroll);
    }

    
    
    /**
     * Reloads the JPanel and figures out what sliders and stuff to put on it
     * based on the contents of the data item collection
     *
     */
    public void update( DataItemCollection dataItems )
    {

	allIndices = new ArrayList();
	for (int i = 0 ; i < dataItems.getSize() ; i++ )
	    allIndices.add(new Integer(i));
	
	//enable the buttons
	all.setEnabled(true);
	viewSel.setEnabled(true);
	weights.setEnabled(true);

	mouseHandler = new MouseHandler(parent);
		
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	this.remove(scroll);
		
	scrollPanel = new JPanel();
	scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
	
	scrollPanel.setMaximumSize(new Dimension(320, Integer.MAX_VALUE));
	
	scroll = new JScrollPane(scrollPanel);
	this.add(scroll);
		
	ArrayList types  = dataItems.getTypes();
	ArrayList fields = dataItems.getFields(); 
	
	sliders  = new LinkedList();
	checkBs  = new LinkedList();
	searches = new LinkedList();
	
	//iterate thro' all the types creating a selection widget for each
	for ( int i = 0 ; i < types.size() ; i++ ) {

	    
	    // A Non String (Integer, Date, Double) 
	    // create a SLIDER
	    if ( ((Integer)types.get(i)).intValue() != 
		                         DataItemCollection.STRING ) {
		 
		DoubleSliderPanel slider = 
		    new DoubleSliderPanel(parent, dataItems, i);
		
		slider.setPreferredSize(new Dimension(300, 70));
		slider.setAlignmentX( LEFT_ALIGNMENT );
		
		slider.addMouseListener(mouseHandler);
		
		sliders.add(slider);
		sliderCount++;
		typeStrings.add(new String("slider"));
		
	    }
	    
	    // else a string, 
	    // create JCheckBoxes
	    else {
		//create checkboxes if there are not too many unique ordinal 
		// values
		HashSet set = new HashSet( dataItems.getColumn(i) );
		set.remove(null);
		
		if ( set.size() <= MAX_CHECKBOXES ) {
		    
		    CheckBoxPanel checkb = new CheckBoxPanel(parent, set,
							     dataItems, i);
		    checkb.setAlignmentX( LEFT_ALIGNMENT );
		    
		    checkb.addMouseListener(mouseHandler);

		    checkBs.add(checkb);
		    typeStrings.add(new String("check"));
		    checkCount++;
		}
		
		// too many ordinal values
		// create a SEARCH Box
		else {
		    
		    SearchBoxPanel search = new SearchBoxPanel(parent, 
							       dataItems, i);
		    search.setAlignmentX( LEFT_ALIGNMENT );
		    
		    search.addMouseListener(mouseHandler);
		    
		    searches.add(search);
		    typeStrings.add(new String("search"));
		    searchCount++;
		}
	    }
	}
	
	
	// set the first non empty widget to be the selected item.
	JPanel selPanel = new JPanel();
	if ( sliders.size() != 0 )
	    selPanel = (JPanel)sliders.get(0);
	else if ( checkBs.size() != 0 )
	    selPanel = (JPanel)checkBs.get(0);
	else if ( searches.size() != 0 )
	    selPanel = (JPanel)searches.get(0);
	    
	mouseHandler.setSelected( selPanel );
	parent.getScatterPanel().setSelectedColumn( selPanel.toString() );

	Iterator sliderIterator = sliders.iterator();
		
	while ( sliderIterator.hasNext() ) {
	    JPanel weightPanel = new JPanel();
	    weightPanel.setLayout(new BoxLayout(weightPanel, BoxLayout.X_AXIS));
	    weightPanel.add((JPanel)sliderIterator.next());
	    JPanel jp = addWeightControls("slider");
	    weightPanel.add(jp);
	    scrollPanel.add(weightPanel);
	    scrollPanel.add(Box.createRigidArea(new Dimension(1, 6)));
	}
	Iterator checkIterator = checkBs.iterator();
	while ( checkIterator.hasNext() ) {
	    JPanel weightPanel = new JPanel();
	    weightPanel.setLayout(new BoxLayout(weightPanel, BoxLayout.X_AXIS));
	    weightPanel.add((JPanel)checkIterator.next());
	    JPanel jp = addWeightControls("check");
	    weightPanel.add(jp);
	    scrollPanel.add(weightPanel);
	    scrollPanel.add(Box.createRigidArea(new Dimension(1, 6)));
	}
	
	Iterator searchIterator = searches.iterator();
	while ( searchIterator.hasNext() ) {
	    JPanel weightPanel = new JPanel();
	    weightPanel.setLayout(new BoxLayout(weightPanel, BoxLayout.X_AXIS));
	    weightPanel.add((JPanel)searchIterator.next());
	    JPanel jp = addWeightControls("search");
	    weightPanel.add(jp);
	    scrollPanel.add(weightPanel);
	    scrollPanel.add(Box.createRigidArea(new Dimension(1, 6)));
	} 
	  
	
	repaint();
	validate();
    }
    
    /**
     * Add panel for weight controls for this dimension
     * 
     */
    private JPanel addWeightControls(String s){
	JPanel jp = new JPanel();
	JTextField colWeight = new JTextField("1.0");
	colWeight.setEditable(true);
	JLabel fixedWeight = new JLabel("1.0"); 
	jp.add(colWeight, BorderLayout.CENTER);
	jp.add(fixedWeight, BorderLayout.SOUTH);
	if (s.equals("slider")){
	    colWeightsSlide.add(colWeight);
	    fixedWeightsSlide.add(fixedWeight);
	}else if(s.equals("check")){
	    colWeightsCheck.add(colWeight);
	    fixedWeightsCheck.add(fixedWeight);
	}else{
	    colWeightsSearch.add(colWeight);
	    fixedWeightsSearch.add(fixedWeight);
	}
	return jp;
    }


    /**
     * Read the values entered in text boxes for the weights
     * each dimension should be given in the metric
     *
     * morrisaj 13.13.02
     */
    private void updateWeights() {

	ArrayList weights = new ArrayList();    
	double weight;
	int slideC=0, checkC=0, searchC=0;
	for ( int i = 0 ; i < typeStrings.size() ; i++ ) {
	    // A Non String (Integer, Date, Double) 
	    // create a SLIDER
	    String current = (String)typeStrings.get(i);
	    if ( current.equals("slider")) {
		weight = (getWeight(slideC,current));
		weights.add(new Double(weight));
		slideC++;
	    }else if (current.equals("check")){
		weight = (getWeight(checkC,current));
		weights.add(new Double(weight));
		checkC++;
	    }else{
		weight = (getWeight(searchC,current));
		weights.add(new Double(weight));
		searchC++;
	    } 
	}
	((DataItemCollection)((LayoutModel)parent.getModel()).getDataItemCollection()).updateWeights(weights);

    }


    /**
     * Returns the value within the text field specifying the desired
     * weight for this dimension
     *
     */
    public double getWeight(int i, String s) {
	if (s.equals("slider")){
	    ((JLabel)fixedWeightsSlide.get(i)).setText(((JTextField)colWeightsSlide.get(i)).getText());
	    return Double.parseDouble(((JTextField)colWeightsSlide.get(i)).getText());
	}else if(s.equals("check")){
	    ((JLabel)fixedWeightsCheck.get(i)).setText(((JTextField)colWeightsCheck.get(i)).getText());
	    return Double.parseDouble(((JTextField)colWeightsCheck.get(i)).getText());
	}else{
	    ((JLabel)fixedWeightsSearch.get(i)).setText(((JTextField)colWeightsSearch.get(i)).getText());
	    return Double.parseDouble(((JTextField)colWeightsSearch.get(i)).getText());
	}
    }

    /**
     * called whenever one of the buttons on the screen is called
     *
     *
     */
    public void actionPerformed( ActionEvent ae ) 
    {
	String cmd = ae.getActionCommand();
	
	if ( cmd.equals("all") ) {
	 
	    parent.getSelectionHandler().selectAll();
		
	}
	
	else if ( cmd.equals("none") ) {
	    
	    parent.getSelectionHandler().selectNone();
	}
	
	else if ( cmd.equals("view") ) {

	    TableViewFrame.getInstance(parent).update();
	}
	else if (cmd.equals("weights")){
	    updateWeights();
	}
	
		
    }

    
    private class MouseHandler extends MouseAdapter
    {
	
	private Viewer parent;
	
	private JPanel selected;
	

	/**
	 * cosntructor:
	 *
	 * @param parent The parent of this mouse handler
	 */
	public MouseHandler( Viewer parent )
	{
	    this.parent = parent;
	}

	
	/**
	 * sets the currently selected JPanel, which is highlighted and its ref
	 * is stored
	 * @param selected The selected JPanel
	 *
	 */
	public void setSelected( JPanel selected )
	{
	    this.selected = selected;
	    selected.setBackground( highlight );
	}
	

	
	/**
	 * Called whenver a mouse click occurs
	 *
	 * @param me The mouse event which caused this call
	 */ 
	public void mouseClicked(MouseEvent me)
	{
	    if ( (me.getModifiers() & InputEvent.BUTTON1_MASK)
		 == InputEvent.BUTTON1_MASK ) {
	    
		//reset the previously selected
		selected.setBackground(((JPanel)me.getSource()).getBackground());
		
		//set the newly selected
		selected = ((JPanel)me.getSource());
		selected.setBackground( highlight );
		
		//tell the scatter panel about this selection
		parent.getScatterPanel().setSelectedColumn( selected.toString() );
	    }
	}
    }    
}
