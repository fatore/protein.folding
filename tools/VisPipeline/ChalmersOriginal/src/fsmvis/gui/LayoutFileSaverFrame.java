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
 * LayoutFileSaverFrame
 * 
 * A frame to allow the user to save layouts 
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;


public class LayoutFileSaverFrame extends FileHandlerFrame
{
    private static LayoutFileSaverFrame instance;



    private LayoutFileSaverFrame(Viewer parent)
    {
	super(parent);
    
	setTitle("Save Layout");

	chooser.setDialogType(JFileChooser.SAVE_DIALOG); 
	
	filter  = new ExampleFileFilter();
	filter.addExtension("layout");
	filter.setDescription("fsmvis Layout files");
	chooser.addChoosableFileFilter(filter);
    
    }
    



    /**
     * Static public accessor for this singleton frame
     *
     */
    public static LayoutFileSaverFrame getInstance(Viewer parent)
    {
	if ( instance == null )
	    instance = new LayoutFileSaverFrame(parent);
	
	instance.setVisible(true);
	
	instance.fileText.setText("");
	
	return instance;
    }
    
    


    /**
     * Does the work of loading things.
     *
     */
    protected void handleFile()
    {
	String fileName = fileText.getText();
	
	// if fileName is empty, get default file name from properties file
	
	//otherwise, add .layout to the end of it, if it isn't there
	if ( !fileName.endsWith(".layout") )
	    fileName = fileName.concat(".layout");

	// if the file exists, do you want to replace this file?
	File file = new File(fileName);
	
	// if file is a dir, can't proceed
	if ( file.isDirectory() )
	    
	    DialogBox.errorMessage(this, "File chosen is a directory, "+
				   "cannot save.");
	
	// if the file exists ask if replace ok, or if not exist, proceed
	else if ( (file.isFile() 
		   && DialogBox.confirmDialog( this, "\""+fileName+"\" "+
		      "already exists. Do you want to replace it?")) 
		  || !file.exists() ) {
	    
	    try {
		System.out.println("file = "+fileName);
		
		FileOutputStream fos   = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(parent.getModel());
		
		oos.flush();
		oos.close();
	    }
	    catch ( FileNotFoundException fnfe) {
		
		DialogBox.errorMessage(this, "The file "+fileName+
				       " could not be found");
		System.err.println("The file "+fileName+" could not be "+
				   "found");
		fnfe.printStackTrace();
	    }
	    catch ( IOException ioe) {
		
		DialogBox.errorMessage(this, "An IO error occured writing to"+
				       " "+fileName);
		System.err.println("An IO error occurred");
		ioe.printStackTrace();
	    }
	}
    
	this.setVisible(false);
	this.dispose();
    }
}




   


