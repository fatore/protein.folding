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
 * ProgressBarFrame
 * 
 */
 
package fsmvis.gui;

import fsmvis.utils.MonitorableTask;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;



public class ProgressBarFrame extends JFrame implements ActionListener,
							Runnable
{
    protected MonitorableTask task;
    protected String          msg;
    protected JProgressBar    progressBar;
    protected Timer           timer;
    protected Thread          me;
    
    public ProgressBarFrame(MonitorableTask task, String msg)
    {
	this.task = task;
	this.msg  = msg;
	
	setTitle("Progress");
    
	setSize(300, 80);
	
	JPanel bar = new JPanel();
	bar.setBorder( new TitledBorder( LineBorder.createGrayLineBorder(),
					 msg));

	progressBar = new JProgressBar(0, task.getLengthOfTask());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
	bar.add(progressBar);
	
	this.getContentPane().setLayout(new BorderLayout());
	
	this.getContentPane().add(bar);
	
	
	addWindowListener(new WindowHandler(this));
	
	this.pack();
	this.show();
	
	progressBar.setValue(progressBar.getMinimum());
	
	try {
	    Thread.sleep(2000);
	}
	catch ( InterruptedException ie) {
	    ie.printStackTrace();
	}
	
	me = new Thread(this);
	
	me.start();
    }

    public void run()
    {
	
	
	while (!task.isFinished()) {
	    try {
		
		progressBar.setValue(task.getProgress());
		
		Thread.sleep(0);
	    }
	    catch ( InterruptedException ie) {
		ie.printStackTrace();
	    }
	}
	
	progressBar.setValue(progressBar.getMaximum());
	
    }
    


    
    public void actionPerformed(ActionEvent e) 
    {
	
	Toolkit.getDefaultToolkit().beep();
	
	progressBar.setValue(task.getProgress());
	if (task.isFinished()) {
	    timer.stop();
	    progressBar.setValue(progressBar.getMaximum());
	    dispatchEvent(new WindowEvent(this,
					  WindowEvent.WINDOW_CLOSING));
	}
	
    }
}
