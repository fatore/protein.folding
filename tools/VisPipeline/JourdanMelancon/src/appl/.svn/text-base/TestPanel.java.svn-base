/*
 * Created on 18 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * TestPanel.java
 * 
 * 						 Copyright Fabien Jourdan
 						 Fabien.Jourdan@toulouse.inra.fr
 						 
						 This software is a computer program whose purpose is to [describe
						 functionalities and technical features of your software].

						 This software is governed by the CeCILL  license under French law and
						 abiding by the rules of distribution of free software.  You can  use, 
						 modify and/ or redistribute the software under the terms of the CeCILL
						 license as circulated by CEA, CNRS and INRIA at the following URL
						 "http://www.cecill.info". 

						 As a counterpart to the access to the source code and  rights to copy,
						 modify and redistribute granted by the license, users are provided only
						 with a limited warranty  and the software's author,  the holder of the
						 economic rights,  and the successive licensors  have only  limited
						 liability. 

						 In this respect, the user's attention is drawn to the risks associated
						 with loading,  using,  modifying and/or developing or reproducing the
						 software by the user in light of its specific status of free software,
						 that may mean  that it is complicated to manipulate,  and  that  also
						 therefore means  that it is reserved for developers  and  experienced
						 professionals having in-depth computer knowledge. Users are therefore
						 encouraged to load and test the software's suitability as regards their
						 requirements in conditions enabling the security of their systems and/or 
						 data to be ensured and,  more generally, to use and operate it in the 
						 same conditions as regards security. 

						 The fact that you are presently reading this means that you have had
						 knowledge of the CeCILL license and that you accept its terms.
 */
package appl;



import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import layout.MDS;
import structure.DataSet;

/**
 * A panel to draw the result of the computation.
 * </br>Commented on 18 oct. 2005
 * @author Fabien
 */
public class TestPanel extends JPanel{
	MDS mds;
	DataSet dataset;
	public TestPanel(MDS m, DataSet d,int w,int h)
	{
		this.setSize(w,h);
		mds=m;
		//mds.scale();
		dataset=d;
	}
	public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  g.setColor(Color.black);
		  Iterator iterator=dataset.getCollection().iterator();
		  while(iterator.hasNext())
		  {
		  	Object element=iterator.next();
		  	int x=(int)mds.getX(element);
		  	int y=(int)mds.getY(element);
		  	g.drawRect(x,y,3,3);
		  }
		}
	
}
