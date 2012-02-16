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
 * DataFileLoaderFrame
 * 
 * Frame to allow the user to load data frames, allows them to open csv files
 * to create new layouts
 *
 *
 */
 
package fsmvis.gui;

import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.utils.MonitorableTask;
import fsmvis.data.DataLoader;
import fsmvis.data.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import java.text.ParseException;



public class DataFileLoaderFrame extends FileHandlerFrame {
    
  private static DataFileLoaderFrame instance;
  private static String defaultDataFile = "/users/staff/matthew/public_html/lectures/IS3/fsmvis/DATA/d100.csv";   
    
    
    private DataFileLoaderFrame(Viewer parent)
    {
	super(parent);
	
	setTitle("New Layout");
	
	chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
	
	filter  = new ExampleFileFilter();
	filter.addExtension("csv");
	filter.setDescription("Comma Separated Var files");
	chooser.addChoosableFileFilter(filter);
    }
    
    
    
    /**
     * Static public accessor for this singleton frame
     *
     */
    public static DataFileLoaderFrame getInstance(Viewer parent)
    {
	if ( instance == null )
	    instance = new DataFileLoaderFrame(parent);
	
	instance.setVisible(true);
	
	instance.fileText.setText("");

	return instance;
    }
    
    
    
    /**
     * Does the work of loading things.  implements the method in 
     * FileLoaderFrame.
     *
     */
    protected void handleFile()
    {
	String fileName = fileText.getText();
	
	try {
		    
	    DataLoader loader;
	    
	    //want to put a progress bar in here...
	    // hehe or maybe not :)

	    // load the default file, if one is not specified
	    if (fileName.length() == 0) {
		
		PropertiesHandler properties = 
		    new PropertiesHandler(this, "DataFileLoaderFrame");
		    
		//fileName = properties.getProperty("DefaultFile");
		fileName = defaultDataFile;

		
	    }	
	    
	    // nasty hack :)
	    File f = new File(fileName);
	    if ( !f.exists() || !f.canRead() )
		throw new NoPropertiesException(); 

	    System.out.println(fileName);	    
	    loader = new CSVLoader(parent, fileName);
	    
	    System.out.println("reading data");
	    
	    loader.readData();
	    
	    this.setVisible(false);

	    // then want to call the select layout algorithm
	    SelectAlgorithmFrame selector = 
	    	new SelectAlgorithmFrame(loader.getDataItemCollection(),
					 parent);
	    
	    this.dispose();
	    	    
	}
	catch ( FileNotFoundException fnfe) {
	    
	    DialogBox.errorMessage(this, "The file "+fileName+
				   " could not be found");
	    System.err.println("The file "+fileName+" could not be found");
	    fnfe.printStackTrace();
	}
	catch ( IOException ioe) {
	    
	    DialogBox.errorMessage(this, "An IO error occured reading from"+
				   " "+fileName);
	    System.err.println("An IO error occurred");
	    ioe.printStackTrace();
	}
	catch (ParseException pe) {
	    pe.printStackTrace();
	    DialogBox.errorMessage(this, "An error occured while trying to "+
		        "parse the input file. Either the data file or the"+
			" properties file was badly formatted");
	}
	catch (NoPropertiesException npe) {
	    npe.printStackTrace();
	    DialogBox.errorMessage(this, "No default file was found, please "+
				   "select one.");
	}
    }
    
}

