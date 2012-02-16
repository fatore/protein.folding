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
 * DialogBox
 *
 * Class which provides static methods to allow standard dialog boxes to
 * be displayed to the user
 *
 */
 
package fsmvis.gui;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.awt.Component;


public class DialogBox
{
    
    
    /** 
     * Send a standard swing message box to the user telling them of msg 
     *
     * @param parent The parent of this dialog
     * @param msg The message to appear in the dialogue
     */
    public static void informMessage(Component parent, String msg)
    {
	JOptionPane inform = 
	    new JOptionPane(msg,
			    JOptionPane.INFORMATION_MESSAGE,
			    JOptionPane.DEFAULT_OPTION);
	JDialog dialog = inform.createDialog(parent, "Message");
	dialog.show();
    }
    
    
    
    /** Send a standard Swing error message to the user informing them that
     * msg error has occured 
     *
     * @param parent The parent of this dialog
     * @param msg The message to display
     */
    public static void errorMessage(Component parent, String msg)
    {
	JOptionPane error = new JOptionPane(msg, 
					    JOptionPane.ERROR_MESSAGE,
					    JOptionPane.DEFAULT_OPTION);
	JDialog dialog = error.createDialog(parent, "Error");
	dialog.show();
    }                 
    

    
    /** 
     * Asks the user to confirm the message msg and offers them yes or no
     * options.  The result is returned to the caller. 
     * 
     * @param parent The parent of this dialog
     * @param msg The message to display
     * @return The users response
     */
    public static boolean confirmDialog(Component parent, String msg)
    {
	JOptionPane conf = new JOptionPane(msg, 
					   JOptionPane.QUESTION_MESSAGE,
					   JOptionPane.YES_NO_OPTION);
	
	JDialog dialog = conf.createDialog(parent, "Confirm");
	
	dialog.show();
	
	return (((Integer)conf.getValue()).intValue()
		== JOptionPane.YES_OPTION);
    }
                           
    
    
}
