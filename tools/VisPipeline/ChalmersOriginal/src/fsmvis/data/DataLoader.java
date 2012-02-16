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
 * DataLoader
 * Interface to describe how a data loader should work.  This is any module 
 * which is responsible for loading data into the FSM tool from an external
 * source.
 *  
 */ 

package fsmvis.data;

import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;

public interface DataLoader
{


    /**
     * Reads from the data source and parses its contents to create a 
     * DataItemCollection
     */
    public void readData() throws IOException, ParseException;
    
    
    
    /**
     * Method to parse a data item, turns the String of fields in the data
     * item into a dataItem object.
     *
     * @param line The line containing the data for this item
     * @return The DataItem object that is created.
     */
    public DataItem parseDataItem(String line) throws ParseException;
    
    
    
    /**
     * Returns a DataItemCollection object containing the data in the input
     * source
     *
     * @return The DataItemCollection containing all the data.
     */
    public DataItemCollection getDataItemCollection();

    
    
    /**
     * gets the field names in the data source
     *
     * @return The field names in the data
     */
    public ArrayList getFields();


    
    /**
     * Gets the types that correspond to the field names,
     *
     * @return The types of each field name in the data
     */
    public ArrayList getTypes();
    
}
