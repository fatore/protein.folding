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
 *
 * MonitorableTask
 *  
 * Defines an interface for a task which can be monitored by some kind of
 * Progress bar or progress monitor
 *
 */ 

package fsmvis.utils;




public interface MonitorableTask
{

    /**
     * Returns the length of this current task,  this is defined to be some 
     * kind of abstract work unit.  For example a 1000 line text file might 
     * return a task length of 1000, and would consider reading a line of
     * the file as doing a unit of work
     *
     * @return The length of the task in abstract work units
     */
    public int getLengthOfTask();
    
    
    
    /**
     * Returns the current progress through this task.  This is the number of 
     * units that have been completed
     * 
     * @return The current progress through the task
     */
    public int getProgress();
    
    
    

    /**
     * Returns whether or not the current task has been completed
     *
     * @return Whether or not the task has finished
     */
    public boolean isFinished();

    

    /**
     * A method that is called when ever an item of work has been completed
     *
     */
    public void doneWork();

    
    
    /**
     * A method that is called when ever a number of work items have been 
     * completed
     * 
     * @param units The number of work units that where comleted
     */
    public void doneWork(int units);

}
