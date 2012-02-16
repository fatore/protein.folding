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
 * ScatterPanel
 *
 */
 
package fsmvis.gui; 

import fsmvis.data.DataItemCollection;
import fsmvis.data.DataItem;
import fsmvis.engine.LayoutModel;
import fsmvis.engine.SpringModel;
import fsmvis.engine.NeighbourAndSampleModel;
import fsmvis.engine.EndCriteria;
import fsmvis.engine.Coordinate;
import fsmvis.engine.TooManyIterationsException;
import fsmvis.utils.PropertiesHandler;
import fsmvis.utils.NoPropertiesException;
import fsmvis.ColourScales.ColourScales;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import java.io.*;

public class ScatterPanel extends JPanel implements Runnable,
					     Selectable,
					     SelectionChangedListener
{

    protected          LayoutModel       model;
    protected          Viewer            parent;
    protected          int               dataSize;
    protected          Thread            me;
    protected volatile boolean           running     = true;
    protected volatile boolean           drawTrails  = true;
    protected volatile boolean           paused      = true;
    protected volatile boolean           animating   = true;
    protected volatile long              delay       = 0;
    protected          int               width;
    protected          int               height;
    
    protected          double            scaleX      = 1.0;
    protected          double            scaleY      = 1.0;
    
    protected          double            offsetX     = 0.0;
    protected          double            offsetY     = 0.0;
    
    protected          Point             origin;
    
    protected          ArrayList         trails;
    protected          PropertiesHandler properties;
    protected          SelectionHandler  selected; 
    protected          Set               selection;
    
    protected          MouseHandler      mouse;
    
    protected          Color[]           colourScheme;
    protected          int               colourField;
    protected          int               selectedCol;

    public static      double            DATA_DOT_SIZE  = 6.0;
    public static      double            TRAIL_DOT_SIZE = 6.0;
    public static      int               TRAIL_LENGTH   = 10;
    public static      Color             TRAIL_COLOR    = Color.cyan;
    public static      Color             DES_TRAIL_COL  = Color.lightGray;
    public static      Color             DESELECTED_DOT = Color.gray;
    public static      Color             BACKGROUND     =
	                                               new Color(100,100,100);
    
    public static      long              MAX_DELAY      = 800;
    
    // selection rectangle details
    protected Rectangle rec;

    protected boolean notFirst = false;
    // protected long totalTime=0;
    
    public ScatterPanel(LayoutModel model, Viewer parent)
    {
	this.model  = model;
	this.parent = parent;

	dataSize = model.getDataItemCollection().getSize();
	
	setPreferredSize( new Dimension( 400,400 ));
	

	try {
	 
	    properties = new PropertiesHandler(this, "ScatterPanel");
	
	    initProperties();
	}
	catch(NoPropertiesException npe) {
	 
	    System.err.println("Couldn't load the properties for "+
			       "ScatterPanel");
	    npe.printStackTrace();
	}
       	
	selected = parent.getSelectionHandler();

	selected.addSelectableObject(this);

	//array list of selected indices
	selection = new HashSet();
	
	//init trails and selected items
	trails = new ArrayList();

	for (int i = 0 ; i < dataSize ; i++) {
	    trails.add(new ArrayList());
	    selection.add(new Integer(i));
	}	
	
	mouse = new MouseHandler(this);
	
	origin = new Point();
	
	addMouseListener(mouse);
        addMouseMotionListener(mouse);
	
	this.setEnabled(false);
	
	me = new Thread(this);
	me.start();
    
    }
    
       public ScatterPanel(LayoutModel model)
    {
	this.model  = model;

	dataSize = model.getDataItemCollection().getSize();
	
	setPreferredSize( new Dimension( 350,350 ));
       	origin = new Point();
	me = new Thread(this);
	me.start();
    }
 
    
    /**
     * Initialises the properties associated with this object
     *
     *
     */
    protected void initProperties()
    {
	DATA_DOT_SIZE = Double.parseDouble(properties.
                                           getProperty("DataDotSize"));
	TRAIL_DOT_SIZE = Double.parseDouble(properties.
                                           getProperty("TrailDotSize"));
	TRAIL_LENGTH = Integer.parseInt(properties.
					   getProperty("TrailLength"));
	
    }
    
    /**
     * Pauses the layout model
     */
    public void pause()
    {
	paused = true;
    }
    
    
    
    /**
     * starts the layout model
     */
    public void start()
    {
       	paused = false;
    }
    

    
    /**
     * run method defined by runnable interface
     */
    public void run()
    {
	while (running) {
	    // perform one iteration of layout model if not paused
	    long time1=0;
	    if (parent.getInfoPanel() != null)
		time1 = parent.getInfoPanel().getExactTime();
	    if (!paused) {
		
		try {			    
		    model.doIteration();

		    if ( animating ) {

			updateTrails();

			update();
		    }

		    parent.getScatterControlPanel().setIterations(
						   model.getNumIterations());
		}
		catch (TooManyIterationsException te) {
		    
		    parent.getMenuPanel().setAnimateState(true);
		
		    System.err.println("max number of iterations");
		    //te.printStackTrace();
		    //notFirst = true; 
		    //try {FileOutputStream out;
		    //PrintWriter pw;
		    //out = new FileOutputStream("totalTimes.txt", true);
		    //pw = new PrintWriter(out,true);
		    //pw.println(totalTime);
		    //out.close();} catch (IOException e){};

		    //parent.end();
		}
	    }
	    //long time2 = parent.getInfoPanel().getExactTime();
	    //totalTime+=(time2-time1);


	}    
    }
    
    

    //-------------------------
    // Standard paint method and supporting functions
    //--------------------------
    

    /**
     * Method that should be called whenever an screen update is required, 
     * 
     *
     */
    public void update()
    {
	repaint();
    }
    
    
        
    /**
     * Overrides the standard paint method, displays all the dots onto the 
     * screen
     *
     */
    public void paint(Graphics g)
    {
	
	width  = this.getWidth();
	height = this.getHeight();
	
	
	g.setColor( BACKGROUND );
	g.fillRect(0, 0, width, height);
	
	if ( animating ) {
	
	    //get the origin.
	    origin.x = width / 2;
	    origin.y = height / 2;
	    
	    int numItems = dataSize;
	    
	    //use the items list, if its not null
	    List items = model.getDisplayableItems();
	    if ( items != null )
		numItems = items.size();
	    
	    
	    //draw the trails first
	    for (int i = 0 ; i < numItems ; i++ ) {
		
		int k = i;
		
		if ( items != null )
		    k = ((Integer)items.get(i)).intValue();
		
		if ( ! selected.getState(k) )
		    drawTrail(g, k, DES_TRAIL_COL);
	    }
	    
	    for (int i = 0 ; i < numItems ; i++ ) {
		
		int k = i;
		
		if ( items != null )
		    k = ((Integer)items.get(i)).intValue();
		
		if ( selected.getState(k) )
		    drawTrail(g, k, getDotColour(k));
	    }
	    
	    //then draw data points
	    for (int i = 0 ; i < numItems ; i++ ) {
		
		int k = i;
		
		if ( items != null )
		    k = ((Integer)items.get(i)).intValue();
		
		if ( ! selected.getState(k) )
		    drawDot(g, k, DESELECTED_DOT);
	    }
	    
	    for (int i = 0 ; i < numItems ; i++ ) {
		
		int k = i;
		
		if ( items != null )
		    k = ((Integer)items.get(i)).intValue();
		
		if ( selected.getState(k) )
		    drawDot(g, k, getDotColour(k));
	    }
	}
	
    }
    
    
    
    
    /**
     * Draws a dot located at index index in the data item collection, with 
     * the colour col.  Draw all of this onto the graphics g.
     *
     */
    protected void drawDot(Graphics g, int index, Color col)
    {
	
	Coordinate c = model.getPosition(index);
	
	double x = calcPosX( c.getX() );
	double y = calcPosY( c.getY() );
	
	//draw data point
	g.setColor(col);
	
	g.fillRect( (int)( x - (DATA_DOT_SIZE / 2)), 
		    (int)( y - (DATA_DOT_SIZE / 2)),
		    (int)DATA_DOT_SIZE, 
		    (int)DATA_DOT_SIZE);

    }

    
    /**
     * Draws a trail on the data item with index index. and colour col
     * 
     * @param g
     * @param index
     * @param col
     */
    protected void drawTrail(Graphics g, int index, Color colour)
    {
	Color col = new Color(colour.getRed(), 
			      colour.getGreen(), 
			      colour.getBlue());
	Coordinate lastPt = new Coordinate();

	// if there are new points in the data set, create new trails
	while ( index > trails.size() - 1 )
	    trails.add(new ArrayList());

	int limit = Math.min(((ArrayList)trails.get(index)).size(), 
			     TRAIL_LENGTH);
	
	for (int k = 0 ; k < limit ; k++) {
	    
	    
	    Coordinate trailPt = (Coordinate)((ArrayList)trails.
					      get(index)).get(k);
	    
	    double x = calcPosX(trailPt.getX());
	    double y = calcPosY(trailPt.getY());
	    
	    if ((int)x != (int)calcPosX(lastPt.getX()) &&
		(int)y != (int)calcPosY(lastPt.getY())) {
		
		g.setColor(col);
		
		if (drawTrails) {
		    g.fillRect( (int)( x - (TRAIL_DOT_SIZE / 2)), 
				(int)( y - (TRAIL_DOT_SIZE / 2)), 
				(int)TRAIL_DOT_SIZE, 
				(int)TRAIL_DOT_SIZE);
		}
	    }
	    //make colour darker every 3 iterations
	    col = fadeToBackground(col);
	    
	    lastPt = trailPt;
	}
	
    }
    
    

    /**
     * Method to convert the colour passed in to a version of itself more 
     * similar to the background colour
     *
     * @param col The colour to fade
     * @return The resultant colour
     */
    private Color fadeToBackground( Color col )
    {
	int red = (int)( (((double)col.getRed() * 3.0) + 
			 (double)BACKGROUND.getRed() ) / 4.0);
	int green = (int)( (((double)col.getGreen() * 3.0) + 
			   (double)BACKGROUND.getGreen() ) / 4.0);
	int blue = (int)( (((double)col.getBlue() * 3.0) + 
			  (double)BACKGROUND.getBlue() ) / 4.0); 
	
	return new Color(red, green, blue);
    }
    
    

    
    /**
     * calculates the x position that this object should be at within
     * the current JPanel and layoutbounds of the layoutModel
     *
     */
    public double calcPosX(double x)
    {
	return origin.x + ((x + offsetX) * scaleX * width);
    }
    
    
    
    /**
     * calculates the y position that this object should be at within
     * the current JPanel and layoutbounds of the layoutModel
     *
     */
    public double calcPosY(double y)
    {
	return origin.y + ((y + offsetY) * scaleY * height);
    }
    
    

    /**
     * Returns the dot with index current drawing colour, gets this from the 
     * ColourScales class by looking up this dots colour relative to the current
     * colourScheme and colourField.
     *
     * @param index The index of the dot to retrieve colour for
     * @return The colour that this dot should be
     */
    public Color getDotColour( int index )
    {
	
	double max = convToDouble( parent.getModel().getDataItemCollection().
				   getMaximum(colourField));
	double min = convToDouble( parent.getModel().getDataItemCollection().
				   getMinimum(colourField));
	
	double val = convToDouble( parent.getModel().getDataItemCollection().
			     getDataItem(index).getValues()[colourField]);
	
	
	int i = (int)( ((val-min)/(max-min))*(colourScheme.length-40)) +20;
	
	return colourScheme[ i ];
    }
    
    
    
    /**
     * private method to convert an object which is known to be of type Integer
     * Double or Date then into a double primitive
     *
     * @param o The object
     * @return The corresponding double value
     */
    private double convToDouble( Object o ) 
    {
	
	if ( o instanceof Integer )
	    return (double)((Integer)o).intValue();
	
	else if ( o instanceof Double )
	    return ((Double)o).doubleValue();
	
	else if ( o instanceof Date )
	    return (double)((Date)o).getTime();
	
	// keeps compiler happy
	return 0.0;
	
    }
    
    
    //--------------------------------
    // Methods to setup data when the layout model changes
    //----------------------------

        
    /**
     * called when the layout model that is being used has changed
     *
     * @param model The new layout model to use
     */
    public void setModel ( LayoutModel model )
    {
	this.model = model;
	
	dataSize = model.getDataItemCollection().getSize();
	
	selected = parent.getSelectionHandler();
	selected.addSelectionChangedListener(this);
	selected.addSelectableObject(this);

	//init trails and selected items, set all to selected
	trails = new ArrayList();
	selection = new HashSet();
		
	for (int i = 0 ; i < dataSize ; i++) {
	    trails.add(new ArrayList());
	    selection.add(new Integer(i));
	}
	
	//stop the current thread if there is one
	if ( me != null ) {
	    paused  = true;

	    running = false;	
	 

	    try {
		me.join();
	    }
	    catch( InterruptedException ie) {
		System.out.println("had problems stopping the thread");
		ie.printStackTrace();
	    }
	}		
	// then restart it
	running = true;
	me = new Thread(this);

	//reset the iterations counter and enable the start button
	parent.getScatterControlPanel().enable();
	parent.getScatterControlPanel().setIterations(
						  model.getNumIterations());	
	//reset the zoom and pan
	reset();
	
	this.setEnabled(true);
    }

    


    
    /** 
     * Sets whether or not the scatter panel should draw trails on the 
     * data items
     *
     * @param drawTrails Sets the state of draw trails
     */
    public void setDrawTrails(boolean drawTrails)
    {
	this.drawTrails = drawTrails;

	update();
    }
    
    
    public void newThread()
    {
        running = true;
        me = new Thread(this);
        me.start();
    }

    public void threadRunning(boolean r)
    {
        running = r;
    }

    public void setAnimate(boolean animating)
    {
	this.animating = animating;

	if ( !animating ) {
	    removeMouseListener(mouse);
	    removeMouseMotionListener(mouse);
	}
	else {
	    addMouseListener(mouse);
	    addMouseMotionListener(mouse);
	
	    reset();
	}
	    
	update();
    }
    

    
    /**
     * updates the contents of the trails information.
     *
     *
     */
    public void updateTrails()
    {
	int numItems = dataSize;
	    
	//use the items list, if its not null
	List items = model.getDisplayableItems();
	if ( items != null )
	    numItems = items.size();
	    
	//draw the trails first
	for (int i = 0 ; i < numItems ; i++ ) {
	    
	    int k = i;
	    
	    if ( items != null )
		k = ((Integer)items.get(i)).intValue();
	    
	    Coordinate c = model.getPosition(k);
	    ((ArrayList)trails.get(k)).add(0, new Coordinate(c.getX(), 
							     c.getY()));
	    
	    if ( ((ArrayList)trails.get(k)).size() > TRAIL_LENGTH )
		((ArrayList)trails.get(k)).remove(
				    ((ArrayList)trails.get(k)).size()-1);
	}
    }
    
    

    //--------------------------
    // Methods to handle rectangle selection from the mouse handler
    //-----------------------
    
    
    /**
     * Sets the currently selected indices of the scatter panel to be the 
     * indices of the data items held within rect
     *
     * @param rect The rectangle to look in
     */
    public void handleSelection(Rectangle rect)
    {
	selection = new HashSet( getRectContents( rect ) );
	selected.updateSelection();
    }
    

    
    /**
     * Adds all the data items which are within the on screen rectangle rect
     * to the currently selected indices that this object holds
     *
     * @param rect The rectange of data items
     */
    public void addToSelection( Rectangle rect )
    {
	selection.addAll( getRectContents( rect ) );
			 
	selected.updateSelection();
    }
    
    

    /**
     * Removes all the data items which are within the on screen rectangle rect
     * from the currently selected indices that this object holds
     *
     * @param rect The rectange of data items
     */
    public void removeFromSelection( Rectangle rect )
    {
	selection.removeAll( getRectContents( rect ) );
    
	selected.updateSelection();
    }

    
    
    /**
     * Returns the indices of the data items that are contained inside the 
     * on screen rectangle rect.
     *
     * @param rect The rectangle
     * @return The collection of indices inside this rectangle
     */
    protected Collection getRectContents( Rectangle rect )
    {
	// adjust the selection rectangle so that it is relative to the on 
	// screen dots
	rect.x      -= (int)(DATA_DOT_SIZE / 2.0); 
	rect.y      -= (int)(DATA_DOT_SIZE / 2.0); 
		
	if ( rect.height == 0 && rect.width == 0 ) {
	    rect.height = (int)DATA_DOT_SIZE; 
	    rect.width  = (int)DATA_DOT_SIZE;
	}
	else {
	    
	    rect.height += 2 * (int)DATA_DOT_SIZE; 
	    rect.width  += 2 * (int)DATA_DOT_SIZE;
	}

	ArrayList contents = new ArrayList();

        int numItems = dataSize;
	    
	//use the items list, if its not null
	List items = model.getDisplayableItems();
	if ( items != null )
	    numItems = items.size();
	    
	    
	//find the on screen positions that are within this rect
	for (int i = 0 ; i < numItems ; i++ ) {
	    
	    int k = i;
	    
	    if ( items != null )
		k = ((Integer)items.get(i)).intValue();
	    
	    Coordinate c = model.getPosition(k);
	    
            if ( rect.contains( calcPosX( c.getX() ), 
			        calcPosY( c.getY() )) ) {
		
		contents.add(new Integer(k));
	    }
	    
        }
	
	return contents;
    }
    
    
    
    //--------------------
    // Standard accessors for variables that can be modified
    //----------------

    
    /**
     *
     *
     *
     */
    public void setColourField( String field )
    {
	this.colourField = parent.getModel().getDataItemCollection().
	                                     getFields().indexOf( field );
	update();
    }
 
    

    /**
     * Sets the colour scheme for the scatter panel to use
     *
     *
     */
    public void setColourScheme( String colour )
    {	
	this.colourScheme = ColourScales.getScale( colour );
    
	update();
    }
    
    
    
    /**
     * Sets the selected column value, this is used for the tooltips to give a 
     * value
     * 
     * @param colName The name of the column which is selected
     */
    public void setSelectedColumn( String colName )
    {
	selectedCol = parent.getModel().getDataItemCollection().
	                                       getFields().indexOf( colName );
    }
    
    

    /**
     * Returns the current delay between iterations
     * 
     * @return The delay bewtween iterations
     */
    public long getDelay()
    {
	return delay;
    }
    
    
    
    /**
     * Sets the delay between calls to do runIteration
     *
     * @param delay The new delay value
     */
    public void setDelay( long delay )
    {
	this.delay = delay;
    }


    
    /**
     * resets the scale and offset values to their original values.  This 
     * should be called whenever a new layout is being started or someone 
     * has scrolled or zoomed away from all the data
     */
    public void reset()
    {
	scaleX  = 1.0;  scaleY  = 1.0;
	offsetX = 0.0;  offsetY = 0.0;
	
	update();
    }
    


    /**
     * Sets the scale to be the new value scale
     *
     * @param scale The new scale value
     */
    public void setScale( double scaleX, double scaleY )
    {
	this.scaleX = scaleX;
	this.scaleY = scaleY;
    }
    
    
    
    /**
     * Sets the x scale to be the new value scale
     *
     * @param scaleX The new x scale value
     */
    public void setScaleX( double scaleX )
    {
	this.scaleX = scaleX;
    }

    
    /**
     * Sets the y scale to be the new value scale
     *
     * @param scaleY The new y scale value
     */
    public void setScaleY( double scaleY )
    {
	this.scaleY = scaleY;
    }
    
    
    
    /**
     * Returns the current value of scale in the x direction
     * 
     * @return The scale value
     */ 
    public double getScaleX()
    {
	return scaleX;
    }

    

    /**
     * Returns the current value of scale in the y direction
     * 
     * @return The scale value
     */ 
    public double getScaleY()
    {
	return scaleY;
    }

    
    
    /**
     * Returns the x offset value
     *
     * @return The x offset
     */
    public double getOffsetX()
    {
	return offsetX;
    }
    
    
    
    /**
     * Returns the y offset value
     *
     * @return The y offset
     */
    public double getOffsetY()
    {
	return offsetY;
    }
    


    /**
     * sets the x offset value
     *
     * @return The x offset
     */
    public void setOffsetX( double offsetX )
    {
	this.offsetX = offsetX;
    }


    
    /**
     * sets the y offset value
     *
     * @return The y offset
     */
    public void setOffsetY( double offsetY )
    {
	this.offsetY = offsetY;
    }


    
    /**
     * Returns the Point at the centre of the display, the origin
     *
     * @return The origin
     */
    public Point getOrigin()
    {
	return origin;
    }
    

    
    // -----------------------
    // Methods required by selectable interface
    // -----------------
    

    /**
     * Method that will be called the selection is changed in the selection 
     * handler
     *
     * @param select The selection handler that created the selection changed
     */
    public void selectionChanged( SelectionHandler select )
    {
	update();
    }



    /**
     * Returns the selection of this object
     *
     * @return The selection
     */
    public Collection getSelection()
    {
	return selection;
    }
    

    
    /**
     * Returns the indices of the deselected items in this object
     * This method may return null if the getSelection method is being used
     * instead.  
     *
     * @return The deselection
     */
    public Collection getDeselection()
    {
	return null;
    }



    /**
     * Sets this selectable object to select all its items
     *
     */
    public void selectAll()
    {
	selection = new HashSet();
	for ( int i = 0 ; i < model.getDataItemCollection().getSize() ; i++ )
	    selection.add(new Integer(i));
    }
    

    
    /**
     * Sets this selectable item to select none of it items
     *
     */
    public void selectNone()
    {
	selection.clear();
    }



    // over ride methods in JComponent to display tool tips
    
    /**
     * Sets the tool tip text to be displayed to be relative to the data element
     * under the cursor
     *
     * @param event The mouse event that caused this tool tip to be displayed
     * @return The tool tip text to display
     */
    public String getToolTipText(MouseEvent event)
    {
	
	//find index of item under mouse cursor
	ArrayList indices = new ArrayList(getRectContents(
					    new Rectangle(event.getX(), 
							  event.getY(), 0, 0 )) );
	
	
	if ( indices.size() == 0 ) 
	    return null;
	
	// get the value in selectedCol of the data collection
	
	Object val = parent.getModel().getDataItemCollection().getDataItem( 
              ((Integer)indices.get(0)).intValue() ).getValues()[selectedCol];
	
	if ( val == null )
	
	    return "no data";
	
	else
	    
	    return val.toString();
	
    }
    

    
    /**
     * Sets the location that the tool tip should be displayed at
     *
     * @param event The mouse event that caused this to be called
     * @return The coords of the tool tip
     */
    public Point getToolTipLocation(MouseEvent event)
    {
	
	return new Point(event.getX()+10, event.getY());
    }

    

    /** 
     *  Stores the last drawn rectangle
     *
     * @param r The rectangle
     */
    public void setRect(Rectangle r) {
	rec = r;
    }


    
}
