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
 * MouseHandler
 *
 * Handles mouse interactions for the ScatterPanel component, all the
 * results of interactions are passed onto the scatterpanel.  The class
 * also remembers state information.
 *
 */

package fsmvis.gui; 

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;



public class MouseHandler extends MouseInputAdapter
{

    protected ScatterPanel parent;
    
    protected Point        down;
    
    protected Rectangle    rect;
    

    /**
     * constructor:
     *
     * @param parent
     */
    public MouseHandler( ScatterPanel parent )
    {
	this.parent = parent;
	
	down = new Point();
	
	rect = new Rectangle();
    }
    
    
    
    /**
     * Method that is called whenever the mouse is dragged, that is, has a
     * button held down and is moved
     *
     * @param me
     */
    public void mouseDragged( MouseEvent me )
    {
	//first button pressed, SELECTING
	if ( (me.getModifiers() & InputEvent.BUTTON1_MASK)
	     == InputEvent.BUTTON1_MASK ) {
	    
	    parent.setCursor(Cursor.getPredefinedCursor(1));
	    
	    Graphics g = parent.getGraphics();
	    g.setXORMode(Color.white);
	    	    
	    //remove the previous rect, if its not empty
	    if ( !( rect.width == 0 && rect.height == 0 )) 
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	    

	    //check mouse draggin is within range of viewer
	    int realX = me.getX();
	    int realY = me.getY();
	    if ( me.getX() < 0)
		realX = 0;				
	    if ( me.getX() > parent.getSize().width )
		realX = parent.getSize().width;
	    if ( me.getY() < 0)
		realY = 0;
	    if (me.getY() > parent.getSize().height )
		realY = parent.getSize().height;
	    
	    rect.width = Math.abs(realX - down.x);
	    rect.height = Math.abs(realY - down.y);
	    rect.x = down.x;
	    rect.y = down.y;
	    
	    if (realX < rect.x && realY <= rect.y) {
		rect.x = realX;
		rect.y = realY;
	    } else if (realX >= rect.x && realY < rect.y)
		rect.y = realY;
	    else if (realX < rect.x && realY > rect.y)
		rect.x = realX;
	    
	    	    
	    g.drawRect(rect.x, rect.y, rect.width, rect.height);
	    parent.setRect(rect);

	}
	//middle button held down, ZOOMING
    
	else if ( (me.getModifiers() & InputEvent.BUTTON3_MASK)
	     == InputEvent.BUTTON3_MASK ) {
	     
	    // if the zoom level is greater than 1.0 then applying scaling 
	    // to the factor used for panning
	    double factor = 500;
    
	    double delta = ((double)(down.y - me.getY())) / factor;
		
	    
	    double newScaleX = parent.getScaleX() + delta;
	    double newScaleY = parent.getScaleY() + delta;
	    if (newScaleX < 0.01) newScaleX = 0.01;
	    if (newScaleY < 0.01) newScaleY = 0.01;
	    
	    parent.setScale( newScaleX, newScaleY );
	    
	    parent.update();
	}
	
	
	// right mouse button held down, PANNING

	else if ( (me.getModifiers() & InputEvent.BUTTON2_MASK)
		  == InputEvent.BUTTON2_MASK ) {
	    
	    parent.setCursor(Cursor.getPredefinedCursor( Cursor.MOVE_CURSOR ));
	    
	    //choose the change in scale value, make it relative to 
	    // the current scale level, so pans slower at greate zoom
	    double xFactor = 400.0;
	    double yFactor = 400.0;
	    if ( parent.getScaleX() > 1.0 )
		xFactor *= parent.getScaleX();
	    if ( parent.getScaleY() > 1.0 )
		yFactor *= parent.getScaleY();
	    
	    double deltaX = ((double)down.x - me.getX()) / xFactor;

	    double deltaY = ((double)down.y - me.getY()) / yFactor;
	    
	    parent.setOffsetX( parent.getOffsetX() + deltaX );
	    parent.setOffsetY( parent.getOffsetY() + deltaY );
	    
	    parent.update();
	}
    }
    
    
    
    /**
     *
     *
     *
     * @param me
     */
    public void mousePressed( MouseEvent me )
    {
	//record coords mouse pressed at
	down.x = me.getX();
	down.y = me.getY();
	
	
	// SELECTING, init the rectangle
	if ( (me.getModifiers() & InputEvent.BUTTON1_MASK)
	     == InputEvent.BUTTON1_MASK ) {
	
	    rect.x      = down.x;
	    rect.y      = down.y;
	    rect.width  = 0;
	    rect.height = 0;

	}
	//middle button pressed down, ZOOMING
	else if ( (me.getModifiers() & InputEvent.BUTTON3_MASK)
	     == InputEvent.BUTTON3_MASK ) {
	    
	    
	}
	
    }
    
    
    public void mouseReleased( MouseEvent me ) 
    {
	parent.setCursor(Cursor.getPredefinedCursor(0));
	
	
	// Button 1 held down

	if ( (me.getModifiers() & InputEvent.BUTTON1_MASK)
	     == InputEvent.BUTTON1_MASK ) {
	    
	    Graphics g = parent.getGraphics();
	    g.setXORMode(Color.white);
	    
	    //remove the previous rect, unless it never moved
	    if ( !( rect.width == 0 && rect.height == 0 ))
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	    
	    // SHIFT modifier	    
	    if ( (me.getModifiers() & InputEvent.SHIFT_MASK)
		 == InputEvent.SHIFT_MASK )
		parent.addToSelection(rect);
	    
	    // CONTROL modifier
	    else if ((me.getModifiers() & InputEvent.CTRL_MASK)
			 == InputEvent.CTRL_MASK)
		parent.removeFromSelection(rect);
	    
	    // no modifier
	    else 
		parent.handleSelection(rect);

	    
	    

	    rect = new Rectangle();
	}
    }

    
    public void mouseClicked( MouseEvent me )
    {
	if ( (me.getModifiers() & InputEvent.BUTTON1_MASK)
	     == InputEvent.BUTTON1_MASK ) {
	
	}
    }
    
}
