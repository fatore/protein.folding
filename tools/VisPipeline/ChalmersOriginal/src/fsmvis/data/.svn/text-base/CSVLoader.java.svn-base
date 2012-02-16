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
 * CSVLoader
 * abstract class to load in a Collection of DataItems from a csv file
 * File is assumed to have field names on the first line followed
 * by types on the second line.
 *  
 */
package fsmvis.data;

import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.utils.MonitorableTask;
import fsmvis.gui.Viewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CSVLoader implements DataLoader, MonitorableTask {

    public static Map CSV_TYPES;
    protected Viewer parent;
    protected DataItemCollection dataItemColl;
    protected int numDataItems;
    protected String fileName;
    protected PropertiesHandler properties;
    protected ArrayList fields;
    protected ArrayList types;
    protected BufferedReader csvFile;
    protected double[] sumOfVals;
    protected double[] sumOfSquares;

    //    protected IDF 		 idfInstance;   // GR
    //    protected static ArrayList	 idfValue;      // GR
    protected SimpleDateFormat dateF;
    protected String delim;
    protected String undef;
    //allows  monitoring of progress
    protected int taskLength;
    protected int progress;
    public final static String DOUBLE_NAME = "DoubleName";
    public final static String INTEGER_NAME = "IntegerName";
    public final static String STRING_NAME = "StringName";
    public final static String DATE_NAME = "DateName";
    public final static String DATE_FORMAT = "DateFormat";
    public final static String UNDEFINED_FORMAT = "UndefinedFormat";
    public final static String DELIMITER_FORMAT = "Delimiter";
    public final static String DEFAULT_DOUBLE = "REAL";
    public final static String DEFAULT_INTEGER = "INT";
    public final static String DEFAULT_STRING = "STRING";
    public final static String DEFAULT_DATE = "DATE";
    public final static String DEFAULT_UNDEFINED = "";
    public final static String DEFAULT_DELIMITER = ",";
    public final static String DEFAULT_DATE_FORMAT = "yyyy'-'MM'-'d";
    /**
     * Constructor for CSVLoader, takes a csv filename as param
     * @param fileName The csv file to be loaded
     */
    public CSVLoader(Viewer parent, String fileName) throws
            FileNotFoundException,
            IOException //NoPropertiesException
    {
        this.fileName = fileName;
        this.parent = parent;

        //setup some default values for reading data
        CSV_TYPES = new HashMap();
        CSV_TYPES.put(DEFAULT_STRING, new Integer(DataItemCollection.STRING));
        CSV_TYPES.put(DEFAULT_DATE, new Integer(DataItemCollection.DATE));
        CSV_TYPES.put(DEFAULT_INTEGER, new Integer(DataItemCollection.INTEGER));
        CSV_TYPES.put(DEFAULT_DOUBLE, new Integer(DataItemCollection.DOUBLE));

        dateF = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        delim = DEFAULT_DELIMITER;
        undef = DEFAULT_UNDEFINED;

        int start = 0;//Math.max(0, fileName.lastIndexOf('/')+1);
        int end = fileName.length();
        if (fileName.lastIndexOf('.') != -1) {
            end = fileName.lastIndexOf('.');
        }

        String propFile = fileName.substring(start, end);

        try {

            properties = new PropertiesHandler(propFile);

            initProperties();
        } catch (NoPropertiesException npe) {
            System.err.println("Could not find any properties information " +
                    "for " + fileName);
        //DialogBox.errorMessage(parent, "No property file found for "+
        //			   fileName+", attempting to use defaults");
        }

        FileInputStream fStream = new FileInputStream(fileName);
        taskLength = fStream.available();

        System.out.println("length = " + fStream.available());
        fStream.close();

        csvFile = new BufferedReader(new FileReader(fileName));

        fields = new ArrayList();
        types = new ArrayList();

        dataItemColl = new DataItemCollection();

        progress = 0;
    }

    /**
     * initialises the properties associated with this loader
     *
     */
    protected void initProperties() {
        if (properties.getProperty(STRING_NAME) != null) {
            CSV_TYPES.put(properties.getProperty(STRING_NAME), new Integer(0));
        }

        if (properties.getProperty(DATE_NAME) != null) {
            CSV_TYPES.put(properties.getProperty(DATE_NAME), new Integer(1));
        }

        if (properties.getProperty(INTEGER_NAME) != null) {
            CSV_TYPES.put(properties.getProperty(INTEGER_NAME), new Integer(2));
        }

        if (properties.getProperty(DOUBLE_NAME) != null) {
            CSV_TYPES.put(properties.getProperty(DOUBLE_NAME), new Integer(3));
        }

        if (properties.getProperty(DATE_FORMAT) != null) {
            dateF = new SimpleDateFormat(properties.getProperty(DATE_FORMAT));
        }

        if (properties.getProperty(UNDEFINED_FORMAT) != null) {
            undef = properties.getProperty(UNDEFINED_FORMAT);
        }

        if (properties.getProperty(DELIMITER_FORMAT) != null) {
            delim = properties.getProperty(DELIMITER_FORMAT);
        }
    }

    /**
     * Reads the field names from the first line of the csv file
     * Assumes the input pointer is ready at the start of the file
     */
    public void readFields() throws IOException {

        //Read in headings
        String line = csvFile.readLine();

        doneWork(line.length() + 1);

        StringTokenizer tok = new StringTokenizer(line, delim);

        while (tok.hasMoreTokens()) {

            fields.add(tok.nextToken());
        }

        fields.trimToSize();
    //	initIDF();  // GR
    }

    /**
     * Reads the corresponding types from the second line of csv file
     * Assumes the input pointer is at the start of the second line of the 
     * csv file.
     */
    public void readTypes() throws IOException, ParseException {
        // read types line
        String line = csvFile.readLine();

        doneWork(line.length() + 1);

        StringTokenizer tok = new StringTokenizer(line, delim);

        while (tok.hasMoreTokens()) {

            Integer type = (Integer) CSV_TYPES.get(tok.nextToken());

            //if this type was not found, throw a parseException
            if (type == null) {
                throw new ParseException("", 0);
            }

            types.add(type);
        }

        types.trimToSize();
    }

    /**
     * Adds fields.size() number of IDF object instances to the idf ArrayList
     * in order to tally the idf values.
     * 
     * GR
     *
     */
//      public void initIDF()
//      {
// 		idfValue = new ArrayList();
// 		for (int i = 0; i < fields.size(); i++)
// 		{
// 			idfInstance = new IDF();
// 			idfValue.add(i, idfInstance);
// 		}
//      }
// 
//      public static ArrayList getIDF()
//      {
// 		return idfValue;
//      }
    /**
     * Reads from the data source and parses its contents to create a 
     * DataItemCollection
     */
    public void readData() throws IOException, ParseException {
        /*
        This took 17381ms with reading whole file initially then parsing


        Incremental parsing took 1412ms!!!!!!!!!!! why would anyone do it
        the other way!!!!!!!
         */

        long start = System.currentTimeMillis();

        //if the file is not ready to be read, throw an IOException
        if (!csvFile.ready()) {
            throw new IOException();
        }

        readFields();
        readTypes();

        dataItemColl.setFields(getFields());
        dataItemColl.setTypes(getTypes());

        // the three values used for normalizing the data
        sumOfVals = new double[fields.size()];
        sumOfSquares = new double[fields.size()];

        String line = csvFile.readLine();

        while (line != null) {

            doneWork(line.length() + 1);

            // parse the data item, calcs normalization data at same time
            DataItem item = parseDataItem(line);

            dataItemColl.addItem(item);

            // read in the next line of the csv file
            line = csvFile.readLine();

        }

        // finished with the file, close it
        csvFile.close();

        //trim the dataItemColl to size, prolly overkill but just making sure
        dataItemColl.getDataItems().trimToSize();

        long readEnd = System.currentTimeMillis();

        System.out.println("took " + (readEnd - start) + "ms to read the file");

        // Store the number of items in each instance of the IDF class.GR

// 	for (int i = 0; i < idfValue.size(); i++)
// 	{
// 		((IDF)idfValue.get(i)).setNumObjects(dataItemColl.getSize());
// 	}

        //normalize the data
        dataItemColl.setNormalizeData(sumOfVals, sumOfSquares);

    }

    /**
     * Method to parse a data item, turns the String of fields in the data
     * item into a dataItem object.  Also does work on normalization data - 
     * incremements the sumOfVals and the sumOfSquares arrays
     *
     * @param line The line containing the data for this item
     * @return The DataItem object that is created.
     */
    public DataItem parseDataItem(String line) throws ParseException {


        ArrayList values = new ArrayList();

        StringTokenizer tok = new StringTokenizer(line, delim, true);

        String lastTok;

        int colNum = 0;

        while (tok.hasMoreTokens()) {

            String val = tok.nextToken();

            lastTok = val;

            // if this value is a delimiter, record a null
            if (val.equals(delim)) {

                values.add(null);
            } // if this value is not a delimiter, continue
            else {

                // take the real delimiter
                if (tok.hasMoreTokens()) {
                    lastTok = tok.nextToken();
                }

                if (val.equals(undef)) {

                    values.add(null);
                } else {

                    int colType = ((Integer) types.get(colNum)).intValue();

                    int type = ((Integer) types.get(colNum)).intValue();

                    switch (type) {

                        case DataItemCollection.STRING: // String
                            values.add(val);

                            // Accumulate the idf values for use with k-means. GR

// 			((IDF)idfValue.get(colNum)).add(val);

                            break;

                        case DataItemCollection.DATE: // Date
                            Date d = dateF.parse(val);

                            values.add(d);
                            sumOfVals[colNum] += (double) d.getTime();
                            sumOfSquares[colNum] += (double) d.getTime() *
                                    (double) d.getTime();
                            break;

                        case DataItemCollection.INTEGER: // Integer
                            int intVal = Integer.parseInt(val);
                            values.add(Integer.valueOf(val));
                            sumOfVals[colNum] += (double) intVal;
                            sumOfSquares[colNum] += (double) (intVal * intVal);
                            break;

                        case DataItemCollection.DOUBLE: // Double
                            double dblVal = Double.parseDouble(val);
                            values.add(Double.valueOf(val));
                            sumOfVals[colNum] += dblVal;
                            sumOfSquares[colNum] += dblVal * dblVal;
                            break;

                        default:
                            System.err.println("An unknown type has been found");
                            System.exit(0);
                    }
                }
            }
            //deal with special case of null value at end of line
            if (!tok.hasMoreTokens() && lastTok.equals(delim)) {
                values.add(null);
            }

            colNum++;
        }


        DataItem dataItem = new DataItem(values.toArray());

        return dataItem;
    }

    /**
     * Returns a DataItemCollection object containing the data in the csv
     * file
     * @return The DataItemCollection containing all the data.
     */
    public DataItemCollection getDataItemCollection() {
        return dataItemColl;
    }

    /**
     * gets the field names in the csv file
     * @return The field names in the csv
     */
    public ArrayList getFields() {
        return fields;
    }

    /**
     * Gets the types that correspond to the field names,
     * @return The types of each field name in the csv
     */
    public ArrayList getTypes() {
        return types;
    }

    // ----------------------------------
    // Methods defined by monitorable task interface
    /**
     * Returns the length of this current task,  this is defined to be some 
     * kind of abstract work unit.  For example a 1000 line text file might 
     * return a task length of 1000, and would consider reading a line of
     * the file as doing a unit of work
     *
     * @return The length of the task in abstract work units
     */
    public int getLengthOfTask() {
        return taskLength;
    }

    /**
     * Returns the current progress through this task.  This is the number of 
     * units that have been completed
     * 
     * @return The current progress through the task
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Returns whether or not the current task has been completed
     *
     * @return Whether or not the task has finished
     */
    public boolean isFinished() {
        return getLengthOfTask() == getProgress();
    }

    /**
     * A method that is called when ever an item of work has been completed
     *
     */
    public void doneWork() {
        progress++;
    }

    /**
     * A method that is called when ever a number of work items have been 
     * completed
     * 
     * @param units The number of work units that where comleted
     */
    public void doneWork(int units) {
        progress += units;
    }

}
