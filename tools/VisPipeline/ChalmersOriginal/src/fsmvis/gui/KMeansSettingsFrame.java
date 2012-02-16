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
 * KMeansSettingsFrame
 *
 * Allows the user to configure the setup of the K-means algorithm:
 * Fractionation, Buckshot, number of iterations.
 *
 */
 

package fsmvis.gui;

import fsmvis.data.DataItemCollection;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultButtonModel;

public class KMeansSettingsFrame extends JFrame
{
    private static KMeansSettingsFrame instance;
    private static int numEpochs = 20;
    private        static JTextField     text;
    
    private        Viewer         parent;
    private        Container      main;

    private	   JPanel 	  textPanel;
    private	   JPanel 	  radioPanel;
    private	   JPanel	  centroidPanel;
    private 	   JRadioButton	  fractionation;
    private 	   JRadioButton	  buckshot;
    private 	   JRadioButton   average;
    private 	   static JRadioButton   nearest;
    private	   static ButtonGroup    buttonGroup;
    private 	   static ButtonGroup	  cenGroup;
    private        JLabel	  iterations;

    /**
     * constructor:
     *
     * @param parent The parent of this frame
     */
    private KMeansSettingsFrame( Viewer parent )
    {
	this.parent = parent;
	
	main = this.getContentPane();

	setTitle("FSMvis K-means settings");
	
	// Add the controls for the K-means settings.	
	
	radioPanel = new JPanel();
	radioPanel.setBorder(new TitledBorder("Centroid strategy"));
	
	buttonGroup = new ButtonGroup();
	buckshot = new JRadioButton("Buckshot");
	buckshot.setSelected(true);
	buttonGroup.add(buckshot);
	
	fractionation = new JRadioButton("Fractionation");
	buttonGroup.add(fractionation);

	radioPanel.add(buckshot);
	radioPanel.add(fractionation);
		
	main.add(radioPanel, BorderLayout.NORTH);

	centroidPanel = new JPanel();
	centroidPanel.setBorder(new TitledBorder("Centroid type"));
	cenGroup = new ButtonGroup();
	average = new JRadioButton("Average");
	nearest = new JRadioButton("Nearest");
	average.setSelected(true);
	average.setActionCommand("average");
	nearest.setActionCommand("nearest");
	cenGroup.add(average);
	cenGroup.add(nearest);
	centroidPanel.add(average);
	centroidPanel.add(nearest);
	main.add(centroidPanel, BorderLayout.CENTER);

	textPanel = new JPanel();
	iterations = new JLabel("Max No. of iterations:");
	textPanel.add(iterations);
	text = new JTextField("20");
	text.setActionCommand("text");	
	text.addKeyListener(new KeyListens(this));
	text.setPreferredSize(new Dimension(100, 20));
	textPanel.add(text);

	main.add(textPanel, BorderLayout.SOUTH);

	this.setSize(new Dimension(300, 180));

	addWindowListener(new WindowHandler(this));

    }
        
    public String getIterations()
    {
	return text.getText();
    }

    public void setIterations(String i)
    {
	text.setText(i);
    }

    /**
     * Called whenever a component wants a view of the k_means settings, this ensures 
     * that it is singleton
     *
     * @param parent the parent frame
     */
    public static KMeansSettingsFrame getInstance( Viewer parent )
    {
	if ( KMeansSettingsFrame.instance == null || instance.parent != parent )
	{
		instance = new KMeansSettingsFrame(parent);
	}

	return instance;
    }  

    public static int getNumEpochs()
    {
	return (new Integer(text.getText())).intValue();
    }

    public static String getCenroidType()
    {
	return cenGroup.getSelection().getActionCommand();
    }

    public static void setNearestCentroidType()
    {
	nearest.setSelected(true);
    }

    public void update()
    {
	this.setVisible(true);
    }
}
