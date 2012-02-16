/*
	File:	DoubleSlider.java
	Author:	Jonathan Paisley, paisleyj@dcs.gla.ac.uk

	Copyright 2000, Jonathan Paisley
	
	Please feel free to use and/or modify this code. If you do so,
	however, please leave the original copyright notice intact and
	let me know of any useful changes so that I can merge them in to
	the original sources.
 */
package fsmvis.gui; 



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** Basic double-ended slider for Swing.
	@author Jonathan Paisley, paisleyj@dcs.gla.ac.uk
	@version 0.3
 */

public class DoubleSlider extends JPanel 
{
	/** Orientations */
	public static final int HORIZONTAL = 0, VERTICAL = 1;

	/** Size of the (square) buttons */
	private static final int buttonSize = 16;

	ArrowButton	minButton,maxButton;
	
	/** Component for the track between the two buttons */
	JComponent track;
	
	/** Current orientation (HORIZONTAL or VERTICAL) */
	int orientation;

	/** Values representing absolute bounds of slider */
	double maxValue,minValue;
	
	/** Values representing current selected bounds */
	double currentMaxValue,currentMinValue;
	
	/** Values representing current hilited bounds */
	double hilitedMaxValue,hilitedMinValue;

	/** True if the range indicated by hilitedMaxValue and
		hilitedMinValue is to be shown */
	boolean showHilitedRange;
	
	/** True if a drag is currently in progress */
	boolean trackingDrag = false;

	static Color midToLightGray = mixColors(Color.lightGray,Color.gray);

	/** Default colour for the track */
	Color trackColor = new Color(216,215,153);

	/** List of interested DoubleSliderAdjustmentListeners */
	java.util.List	listeners;

	/** Last time a DoubleSliderAdjustmentListeners was notified (ms)*/
	long lastNotifyTime = 0;
	
	/** Duration in milliseconds between notifications */
	long notifyInterval = 0;


//---------------------------------------------------------------------------
// PUBLIC CONSTRUCTORS
//---------------------------------------------------------------------------

	/** Construct a default DoubleSlider with minimum 0.0 and maximum 100.0,
		oriented HORIZONTALly */
	public DoubleSlider()
	{
		this(HORIZONTAL,0.0,100.0);
	}

	/** Construct a DoubleSlider with given minimum, maximum and orientation
		@param orientation HORIZONTAL or VERTICAL
		@param minValue The minimum value
		@param maxValue The maximum value
	 */
	public DoubleSlider(int orientation,double minValue,double maxValue)
	{
		
		this.orientation = orientation;
		this.minValue = this.currentMinValue = this.hilitedMinValue = minValue;
		this.maxValue = this.currentMaxValue = this.hilitedMaxValue = maxValue;
	
		showHilitedRange = false;

		listeners = new LinkedList();
	
		EventsHandler eh = new EventsHandler();
		setLayout(eh);

		minButton = new ArrowButton(
			orientation==HORIZONTAL ? ArrowButton.RIGHT : ArrowButton.DOWN);
		maxButton = new ArrowButton(
			orientation==HORIZONTAL ? ArrowButton.LEFT : ArrowButton.UP);

		track = new Tracker();
		
		add(track);
		add(minButton);
		add(maxButton);
		
		minButton.addMouseMotionListener(eh);
		minButton.addMouseListener(eh);

		maxButton.addMouseMotionListener(eh);
		maxButton.addMouseListener(eh);
		
		track.addMouseMotionListener(eh);
		track.addMouseListener(eh);
		
		setBackground(Color.lightGray);
		layoutMyButtons();
	}


//---------------------------------------------------------------------------
// PUBLIC ACCESSORS
//---------------------------------------------------------------------------

	/** Check whether the user is currently dragging the slider.
		@return true if the user is dragging the slider
	 */
	public boolean isTrackingDrag()
	{
		return trackingDrag;
	}

	/** Add a DoubleSliderAdjustmentListener to listen for adjustment events 
		@param i the listener to add
	 */
	public void addAdjustmentListener(DoubleSliderAdjustmentListener i)
	{
		listeners.add(i);
	}

	/** Remove the given DoubleSliderAdjustmentListener from the list of
		event listeners
		@param i the listener to remove
	 */
	public void removeAdjustmentListener(DoubleSliderAdjustmentListener i)
	{
		listeners.remove(i);
	}

	/** Set the time interval between consecutive DoubleSliderAdjustmentListener
		notifications whilst tracking a drag.
		
		@param i the new interval in milliseconds
	 */
	
	public void setTrackNotificationThrottle(long i)
	{
		notifyInterval = i;
	}

	/** Get the current hilited minimum value 
		@return minimum hilited value
	 */
	public double getHilitedMinimum()
	{
		return hilitedMinValue;
	}

	/** Get the current hilited maximum value 
		@return maximum hilited value
	 */
	public double getHilitedMaximum()
	{
		return hilitedMaxValue;
	}

	/** Get the current selected maximum value 
		@return maximum selected value
	 */
	public double getSelectedMinimum()
	{
		return currentMinValue;
	}

	/** Get the current selected maximum value 
		@return maximum selected value
	 */
	public double getSelectedMaximum()
	{
		return currentMaxValue;
	}

	/** Get the current absolute minimum value 
		@return minimum absolute value
	 */
	public double getAbsoluteMinimum()
	{
		return minValue;
	}

	/** Get the current absolute maximum value 
		@return maximum absolute value
	 */
	public double getAbsoluteMaximum()
	{
		return maxValue;
	}

	/** Set the minimum hilited value
		@param min the new minimum hilited value
	 */
	public void setHilitedMinimum(double min)
	{
		hilitedMinValue = min;
		
		if (hilitedMaxValue<hilitedMinValue)
			hilitedMaxValue = hilitedMinValue;

		sanitiseLimits();
		layoutMyButtons();
	}

	/** Set the maximum hilited value
		@param max the new maximum hilited value
	 */
	public void setHilitedMaximum(double max)
	{
		hilitedMaxValue = max;
		
		if (hilitedMinValue>hilitedMaxValue)
			hilitedMinValue = hilitedMaxValue;
		
		sanitiseLimits();
		layoutMyButtons();
	}

	/** Set the hilited range
		@param min the new minimum hilited value
		@param max the new maximum hilited value
	 */
	public void setHilitedRange(double min,double max)
	{
		hilitedMinValue = min;
		hilitedMaxValue = max;

		if (hilitedMaxValue<hilitedMinValue)
			hilitedMaxValue = hilitedMinValue =
						(hilitedMaxValue+hilitedMinValue)/2.0;

		sanitiseLimits();
		layoutMyButtons();
	}

	/** Determine whether or not the hilited range is currently marked
		@return true if it is currently being marked
	 */
	public boolean getHiliteVisible()
	{
		return showHilitedRange;		
	}
	
	/** Set the marking status of the hilited range
		@param visible the new marking status
	 */
	public void setHiliteVisible(boolean visible)
	{
		showHilitedRange = visible;
		repaint();
	}

	/** Set the minimum selected value
		@param min the new minimum selected value
	 */
	public void setSelectedMinimum(double min)
	{
		currentMinValue = min;

		if (currentMaxValue<currentMinValue)
			currentMaxValue = currentMinValue;

		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the maximum selected value
		@param max the new maximum selected value
	 */
	public void setSelectedMaximum(double max)
	{
		currentMaxValue = max;
		
		if (currentMinValue>currentMaxValue)
			currentMinValue = currentMaxValue;
		
		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the selected range
		@param min the new minimum selected value
		@param max the new maximum selected value
	 */
	public void setSelectedRange(double min,double max)
	{
		currentMinValue = min;
		currentMaxValue = max;

		if (currentMaxValue<currentMinValue)
			currentMaxValue = currentMinValue = 
						(currentMaxValue+currentMinValue)/2.0;

		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the minimum possible value
		@param min the new minimum possible value
	 */
	public void setAbsoluteMinimum(double min)
	{
		minValue = min;

		if (maxValue<minValue)
			maxValue = minValue;

		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the maximum possible value
		@param max the new maximum possible value
	 */
	public void setAbsoluteMaximum(double max)
	{
		maxValue = max;

		if (minValue>maxValue)
			minValue = maxValue;

		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the maximum possible range
		@param min the new minimum possible value
		@param max the new maximum possible value
	 */
	public void setAbsoluteRange(double min,double max)
	{
		minValue = min;
		maxValue = max;

		if (maxValue<minValue)
			maxValue = minValue = (maxValue+minValue)/2.0;

		sanitiseLimits();
		layoutAndNotify();
	}

	/** Set the maximum possible range and selected range all together
		@param absMin the new minimum possible value
		@param absMax the new maximum possible value
		@param selectedMin the new minimum selected value
		@param selectedMax the new maximum selected value
	 */
	public void setValues(	double absMin,		double absMax,
							double selectedMin,	double selectedMax)
	{
		minValue = absMin;
		maxValue = absMax;
		currentMinValue = selectedMin;
		currentMaxValue = selectedMax;
		
		if (maxValue<minValue)
			maxValue = minValue = (maxValue+minValue)/2.0;

		sanitiseLimits();
		layoutAndNotify();		
	}

	/** Get the current color for the track
		@return The current track color
	 */
	public Color getTrackColor()
	{
		return trackColor;
	}

	/** Set the current color for the track
		@param c the new color
	 */
	public void setTrackColor(Color c)
	{
		trackColor = c;
	}


	/** Get the average of two colours
		@param a Color to mix
		@param b Color to mix
		@return (a+b)/2
	 */
	private static Color mixColors(Color a,Color b)
	{
		return new Color(
			(a.getRed() + b.getRed())/2,
			(a.getGreen() + b.getGreen())/2,
			(a.getBlue() + b.getBlue())/2);
			
	}
	
	/** Return the given value pinned to the range 0.0-1.0
		@param x Value to pin
		@return min(max(x,1.0),0.0)
	 */
	private static float pinToUnity(double x)
	{
		if (x>1.0F)
			return 1.0F;
		if (x<0.0F)
			return 0.0F;
		return (float) x;
	}
	
	/** Return the given Color after scaling by the given factor.
		@param a Color to scale
		@param factor Scale factor
		@return a*factor
	 */	
	private static Color scaleColor(Color a,double factor)
	{
		return new Color(
			(float) pinToUnity((a.getRed()/255.0) * factor),
			(float) pinToUnity((a.getGreen()/255.0) * factor),
			(float) pinToUnity((a.getBlue()/255.0) * factor));
			
	}

	/** The middle track component */
	private class Tracker extends JComponent
	{
		/** Draw the track, possibly highlighting a range somewhere */
		public void paint(Graphics g)
		{
			g.setColor(trackColor);
			g.fill3DRect(0,0,getWidth(),getHeight(),true);

			if (showHilitedRange)
			{
				g.setColor(scaleColor(trackColor,1.1));
				
				int start,size;
				
				start = valueToPixel(hilitedMinValue) - valueToPixel(currentMinValue); 
				size = valueToPixel(hilitedMaxValue) - valueToPixel(hilitedMinValue) + 1;
				
				if (start<1) {
					size -= (1-start);
					start = 1;
				}
				
				switch (orientation)
				{
					case HORIZONTAL:
						g.fillRect(start,1,size,getHeight()-2);
						break;
					case VERTICAL:
						g.fillRect(1,start,getWidth()-2,size);
						break;
				}
			}
		}
	} // end of Tracker
	
	/** The arrow button component */
	private class ArrowButton extends JComponent
	{
		/** Possible orientation */
		public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
		
		/** Current orientation */
		private int facing;
		
		/** Construct a new ArrowButton facing in the given direction */
		ArrowButton(int facing)
		{
			this.facing = facing;
		}
	
		/** Draw a triangle centered around the given point of the given size
			@param g The Graphics context
			@param x X coordinate of center
			@param y Y coordinate of center
			@param size the number of pixels wide/high
		 */
		public void paintTriangle(Graphics g,int x,int y,int size)
		{
			for (int i=0;i<size;i++)
			{
				switch (facing)
				{
					case UP:
						g.drawLine(x-i,y-size/2+i,x+i,y-size/2+i);
						break;
					case DOWN:
						g.drawLine(x-i,y+size/2-i,x+i,y+size/2-i);
						break;
					case LEFT:
						g.drawLine(x-size/2+i,y-i,x-size/2+i,y+i);
						break;
					case RIGHT:
						g.drawLine(x+size/2-i,y-i,x+size/2-i,y+i);
						break;
				}
			}
		}
	
		/** Draw the ArrowButton component, with its arrow */
		public void paint(Graphics g)
		{
			g.setColor(Color.lightGray);
			g.fill3DRect(0,0,getWidth(),getHeight(),true);			

			g.setColor(Color.black);
			paintTriangle(g,getWidth()/2,getHeight()/2,
						Math.min(getWidth(),getHeight())/2-3);
		}
	} // end of ArrowButton


	/** Notify registered listeners that a change has occurred */
	private void notifyListeners()
	{
		if (trackingDrag)
		{
			long timeNow = System.currentTimeMillis();
			if ((timeNow-lastNotifyTime)>=notifyInterval)
				lastNotifyTime = timeNow;
			else return;
		}

		Iterator itr = listeners.iterator();
		
		while (itr.hasNext())
		{
			((DoubleSliderAdjustmentListener)itr.next()).	
				adjustmentValueChanged(this);
		}
	}

	/** Ensure that the given value is within the absolute maximum and
		minimum values.
		@return min(max(limit,maxValue),minValue)
	 */
	private double pinLimits(double limit)
	{
		if (limit>maxValue)
			limit = maxValue;
		if (limit<minValue)
			limit = minValue;
		return limit;
	}

	/** Ensure that the current selected and highlighted limits are sane */
	private void sanitiseLimits()
	{
		currentMaxValue = pinLimits(currentMaxValue);
		currentMinValue = pinLimits(currentMinValue);
		hilitedMaxValue = pinLimits(hilitedMaxValue);
		hilitedMinValue = pinLimits(hilitedMinValue);
	}

	/** Update the locations of the components and tell any listeners */
	private void layoutAndNotify()
	{
		layoutMyButtons();
		notifyListeners();
	}


	/** Coordinates of last mouse coordinates during a drag */
	private int	lastX,lastY;	

	/** Simple nested class to hide the public Listener and LayoutManager
		events
	 */
	private class EventsHandler
		implements	LayoutManager, 
					MouseMotionListener, MouseListener
	{

		/** Handle a mouse press at the beginning of a drag */
		public void mousePressed(MouseEvent e) 
		{
			Component c = (Component) e.getSource();
			lastX = e.getX() + c.getX();
			lastY = e.getY() + c.getY();

			trackingDrag = true;
			lastNotifyTime = System.currentTimeMillis();
		}

		/** Handle a mouse release at the end of a drag */
		public void mouseReleased(MouseEvent e) 
		{
			trackingDrag = false;
			notifyListeners();
		}

		/** Handle user moving mouse */
		public void mouseDragged(MouseEvent e)
		{
			if (maxValue!=minValue)
			{
				Component c = (Component) e.getSource();

				int	newX = e.getX() + c.getX();
				int	newY = e.getY() + c.getY();
				int offset;

				offset = orientation==HORIZONTAL ? newX - lastX : newY - lastY;

				if (c==minButton)
					offset = updateMinimum(offset);
				else if (c==maxButton)
					offset = updateMaximum(offset);
				else if (c==track)
					offset = updateMinimumAndMaximum(offset);

				if (orientation==HORIZONTAL)
					lastX = newX - offset;
				else lastY = newY - offset;

				layoutAndNotify();
			}
		}

		/** Not used */
		public void mouseClicked(MouseEvent e) 
		{
		}

		/** Not used */
		public void mouseEntered(MouseEvent e) 
		{
		}

		/** Not used */
		public void mouseExited(MouseEvent e) 
		{
		}

		/** Not used */
		public void mouseMoved(MouseEvent e)
		{
		}

		/** Not used */
		public void addLayoutComponent(String name, Component comp) 
		{
		} 

		/** Relayout the components following (eg) a parent resize */
		public void layoutContainer(Container parent) 
		{
			layoutMyButtons();
		}

		/** Get the minimum size of this component */
		public Dimension minimumLayoutSize(Container parent) 
		{
			return preferredLayoutSize(parent);
		}

		/** Get the preferred size of this component */
		public Dimension preferredLayoutSize(Container parent) 
		{
			if (orientation==HORIZONTAL)
				return new Dimension(buttonSize*4,buttonSize);
			return new Dimension(buttonSize,buttonSize*4);
		}

		/** Not used */
		public void removeLayoutComponent(Component comp)
		{
		}

	} // end of EventsHandler
 
	/** Figure out the number of pixels in the track
		@return The number of pixels in the track
	 */
 	private int getTrackSize()
	{
		return (orientation==HORIZONTAL ? getWidth() : getHeight()) 
			- buttonSize*2;
	}
 
 	/** Convert a pixel difference to a value difference */
 	private double pixelToValue(int pixel)
	{
		if (getTrackSize()==0)
			return 0.0;
		return pixel * (maxValue-minValue) / 
					getTrackSize();
	}
 
 	/** Convert a value difference to a value pixel */
 	private int valueToPixel(double value)
	{
		if (maxValue==minValue)
			return 0;
		return (int) (0.5 + value * getTrackSize() / 
						(maxValue-minValue));
	}
 
 	/** Given a mouse drag of offset on the minimum button
		update the bounds and return a compensation if
		they've moved too far.
	 */
 	private int updateMinimum(int offset)
	{
		double newMinValue;
		
		newMinValue = currentMinValue + pixelToValue(offset);
		
		if (newMinValue<minValue)
		{
			// Chap has gone off end.
			currentMinValue = minValue;
			offset = valueToPixel(newMinValue - currentMinValue);
 		} else if (newMinValue>currentMaxValue)
		{ 
			currentMinValue = currentMaxValue;
			offset = valueToPixel(newMinValue - currentMaxValue);
 		} else
		{
			currentMinValue = newMinValue;
			offset = 0;
		}
		return offset;
	}
 
 	/** Given a mouse drag of offset on the maximum button
		update the bounds and return a compensation if
		they've moved too far.
	 */
 	private int updateMaximum(int offset)
	{
		double newMaxValue;
		
		newMaxValue = currentMaxValue + pixelToValue(offset);
		
		if (newMaxValue>maxValue)
		{
			// Chap has gone off end.
			currentMaxValue = maxValue;
			offset = valueToPixel(newMaxValue - currentMaxValue);
 		} else if (newMaxValue<currentMinValue)
		{ 
			currentMaxValue = currentMinValue;
			offset = valueToPixel(newMaxValue - currentMinValue);
 		} else
		{
			currentMaxValue = newMaxValue;
			offset = 0;
		}
		return offset;
	}
 
 	/** Given a mouse drag of offset on the track
		update the bounds and return a compensation if
		they've moved too far.
	 */
 	private int updateMinimumAndMaximum(int offset)
	{
		double newMaxValue = currentMaxValue + pixelToValue(offset);
		double newMinValue = currentMinValue + pixelToValue(offset);
		
		if (newMaxValue>maxValue)
		{
			currentMinValue += maxValue - currentMaxValue;
			currentMaxValue = maxValue;
			offset = valueToPixel(newMaxValue - currentMaxValue);
		} else if (newMinValue<minValue)
		{
			currentMaxValue -= currentMinValue - minValue;
			currentMinValue = minValue;
			offset = valueToPixel(newMinValue - currentMinValue);
		} else
		{
			currentMaxValue = newMaxValue;
			currentMinValue = newMinValue;
			offset = 0;
		}

		return offset;
	}
 
	/** Set the bounds of our components based on the values */
	private void layoutMyButtons()
	{
		int width = getWidth();
		int height = getHeight();
		
		int minOffset = valueToPixel(currentMinValue - minValue);
		int maxOffset = valueToPixel(maxValue - currentMaxValue);

		switch (orientation)
		{
			case HORIZONTAL:
				minButton.setBounds(minOffset,0,
						buttonSize,buttonSize);

				track.setBounds(minOffset+buttonSize,0,
						width-maxOffset-buttonSize*2-minOffset,buttonSize);

				maxButton.setBounds(width-maxOffset-buttonSize,0,
						buttonSize,buttonSize);
				break;
			
			case VERTICAL:
				minButton.setBounds(0,minOffset,
						buttonSize,buttonSize);

				track.setBounds(0,minOffset+buttonSize,
						buttonSize,height-maxOffset-buttonSize*2-minOffset);

				maxButton.setBounds(0,height-maxOffset-buttonSize,
						buttonSize,buttonSize);
				break;
			
		}

	} // end of layoutMyButtons
 
} // end of DoubleSlider
