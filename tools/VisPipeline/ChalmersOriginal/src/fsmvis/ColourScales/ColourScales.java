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
package fsmvis.ColourScales;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Color;
import java.lang.reflect.Field;

/** Class managing a set of colour scales.
	
	Colour scales should be implemented as classes in the
	ColourScales package having a static member named 'rgb'
	which is an array of java.awt.Color.
	
	Once implemented, they can be registered in the static 
	initialiser in this class.
	
	Use getScale() to get the Colors for a named scale.
	Use getNames() to get the names of registered scales.
 */

public class ColourScales
{
	static Hashtable scales = new Hashtable();
	
	static void registerScale(String name,Color[] rgb)
	{
		scales.put(name,rgb);
	}

	static void registerScale(String name)
	{
		try {
			Class cl = Class.forName("ColourScales." + name);

			Field f = cl.getField("rgb");
			Color[] c = (Color[]) f.get(null);

			scales.put(name,c);
		} catch (Exception e)
		{
		}
	}
	
	static public Color[] getScale(String name)
	{
		return (Color[]) scales.get(name);
	}
	
	static Vector enumerationToVector(Enumeration e)
	{
		Vector v = new Vector();
		
		while (e.hasMoreElements())
			v.add(e.nextElement());
		
		return v;
	}
	
	static public Vector getNames()
	{
		return enumerationToVector(scales.keys());
	} 

	static
	{
		registerScale("BTC",BTC.rgb);
		registerScale("BTY",BTY.rgb);
		registerScale("Gray",Gray.rgb);
		registerScale("HeatedObject",HeatedObject.rgb);
		registerScale("LOCS",LOCS.rgb);
		registerScale("LinGray",LinGray.rgb);
		registerScale("Magenta",Magenta.rgb);
		registerScale("OCS",OCS.rgb);
		registerScale("Rainbow",Rainbow.rgb);

// The following is nice but it doesn't create dependancies on the class
// files for each of these color scales so they don't get compiled automatically

/*		registerScale("BTC");
		registerScale("BTY");
		registerScale("Gray");
		registerScale("HeatedObject");
		registerScale("LOCS");
		registerScale("LinGray");
		registerScale("Magenta");
		registerScale("OCS");
		registerScale("Rainbow");
 */
	}
}
