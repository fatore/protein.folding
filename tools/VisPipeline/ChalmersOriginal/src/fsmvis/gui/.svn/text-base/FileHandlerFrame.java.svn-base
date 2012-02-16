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
 * FileHandlerFrame
 * 
 * A panel to give the user a file chooser for handling file choosing
 * This class can be extended for a variety of purposes
 *
 */
 
package fsmvis.gui;

import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.utils.MonitorableTask;
import fsmvis.data.DataLoader;
import fsmvis.data.CSVLoader;
import fsmvis.engine.LayoutModel;
import fsmvis.engine.NeighbourAndSampleModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;





public abstract class FileHandlerFrame extends JFrame implements ActionListener
{
    
    protected Viewer            parent;
    protected JButton           browse;
    protected JButton           ok;
    protected JButton           cancel;
    protected JTextField        fileText;
    protected JFileChooser      chooser;
    protected ExampleFileFilter filter;


    /**
     * constructor: Takes the parent of this object as a parameter, uses this 
     * to pass the results of the loading back, if required
     *
     * @param parent The parent of this window
     */
    public FileHandlerFrame(Viewer parent)
    {
	this.parent = parent;
	
	//setup the file chooser window
	chooser = new JFileChooser(".");
	
	
	setTitle("FSMvis");
	setSize(450, 130); 
	setLocation(50, 50);

	
	fileText = new JTextField(25);
	
	browse = new JButton("Browse");
	ok     = new JButton("OK");
	cancel = new JButton("Cancel");
	
	browse.setActionCommand("browse");
	ok.setActionCommand("ok");
	cancel.setActionCommand("cancel");
	
	browse.addActionListener(this);
	ok.addActionListener(this);
	cancel.addActionListener(this);
	
	JPanel filePanel = new JPanel();
	
	filePanel.setBorder( new TitledBorder(
				LineBorder.createGrayLineBorder(),
				"File Name"));
	filePanel.add(fileText);
	filePanel.add(browse);
	
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	buttonPanel.setBorder(LineBorder.createGrayLineBorder());
	
	buttonPanel.add(ok);
	buttonPanel.add(cancel);
	
	JPanel main = new JPanel(new BorderLayout());
	main.setPreferredSize(new Dimension(450, 130));
	
	main.add(filePanel, BorderLayout.CENTER);
	main.add(buttonPanel, BorderLayout.SOUTH);
	
	getContentPane().add(main);
	addWindowListener(new WindowHandler(this));


	
	this.pack();
	this.show();
    }
    

    
    /**
     * Does the work of handling files.  This will either be a load or a save 
     * file
     *
     */
    protected abstract void handleFile();
    
    

    /**
     * Required by an ActionListener, is called whenever a button is pressed
     * 
     * @param e The action event that caused this to be called
     */
    public void actionPerformed(ActionEvent e) 
    {	
	// cancel, close the window
	if ( e.getActionCommand().equals("cancel") ) {
	    dispatchEvent(new WindowEvent(this,
					  WindowEvent.WINDOW_CLOSING));
	}

	//browse, launch a file chooser and get the file from it
	if ( e.getActionCommand().equals("browse") ) {
	    
	    int returnVal;
	    if ( chooser.getDialogType() == JFileChooser.OPEN_DIALOG )
		returnVal = chooser.showOpenDialog(this);
	    else
		returnVal = chooser.showSaveDialog(this);
		    
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		
		File file = chooser.getSelectedFile();
		
		fileText.setText(file.getAbsolutePath());
		
	    }
	}
	
	if ( e.getActionCommand().equals("ok") ) {
	    
	    File file = new File(fileText.getText());
	    
	    // no file specified
	    if ( fileText.getText().length() == 0 ) {
		
		//use default file?
		if ( DialogBox.confirmDialog(this, 
					     "No file specified. Do you want"+
					     " to use the default file?") )
		    handleFile();
		
	    }
	    
	    //cannot read from the file or its not a file (dir)

	    else
		
		handleFile();
	    
	    
	}
    }
}




   


