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



/** Interface for adjustment listeners for DoubleSlider */
public interface DoubleSliderAdjustmentListener
{
	/** The given DoubleSlider's value has changed
		@param which The DoubleSlider
	 */
	void adjustmentValueChanged(DoubleSlider which);
}

