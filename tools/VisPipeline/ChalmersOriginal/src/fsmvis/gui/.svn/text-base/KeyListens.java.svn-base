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
 * KeyListens
 *
 * Monitors what the user types for the number of K-means iterations
 * and suppresses non integer values.
 *
 */
 

package fsmvis.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.NumberFormatException;

public class KeyListens extends KeyAdapter
{

	private KMeansSettingsFrame kSettingsFrame;
	private int stringLen = 0;

	public KeyListens(KMeansSettingsFrame kSettingsFrame)
	{
		this.kSettingsFrame = kSettingsFrame;
	}

	public void keyPressed(KeyEvent e)
	{
		String sTemp = null;
		sTemp = kSettingsFrame.getIterations();
		stringLen = sTemp.length();
	}

	public void keyReleased(KeyEvent e) 
	{

		// Only allow integers to be typed into the K-means iterations text field.

		kSettingsFrame.setIterations(format(kSettingsFrame.getIterations()));
	}

	private String format(String sIn)
	{
		try
		{
			Integer iTemp = null;
			iTemp = new Integer(sIn);
			return sIn;
		}
		catch(NumberFormatException er)
		{
			// If a non integer character has been appended then remove it.
			
			String s = sIn;
			s.trim();

			if (s.length() >= stringLen)
			{
				stringLen = sIn.length() - 1;
				return format(s.substring(0, s.length() - 1));
			}
			else if (s.length() < stringLen)
			{
				kSettingsFrame.setIterations("1");
				return "1";
			}
			return "1";
		}
	}
}
