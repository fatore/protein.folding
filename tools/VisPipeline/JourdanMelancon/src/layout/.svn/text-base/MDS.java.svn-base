/*
 * Created on 17 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * MDS.java
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
package layout;


import java.util.Iterator;
import java.util.Random;

import structure.DataSet;
import structure.GeometricalDataSet;
import util.StressMeasurement;

/**
 * The top level class for MDS computation. All implementations of algorithms will extend this abstract class.
 * </br>Commented on 17 oct. 2005
 * @author Fabien
 */
public abstract class MDS {

	/**
	 * The set that contains the dataset and the coordinates associated to each element of the dataset.
	 */
	protected GeometricalDataSet geoDataSet;
	/**
	 * Max Width of the representation space.
	 */
	protected double maxWidth;
	/**
	 * Max height of the representation space.
	 */
	protected double maxHeight;
	/**
	 * The stress value of the configuration.
	 */
	protected double stress;
	/**
	 * Since similarity is between 0 and 1 the final distance between elements will be between 0 and 1.
	 * For the finale representation the coordinates will be recompute for a givent scale factor.
	 * The scaleFactor will be computed according to maxWidth and maxHeight.
	 */
	protected double scaleFactor=1;
	/**
	 * A random value for the initial positions
	 */
	protected Random random = new Random();

	/**
	 * Constructor require a dataset and sizes of the representation space.
	 * @param d The DataSet that contains elements and a way to compute similarity
	 * @param w The width of the representation space
	 * @param h The height of the representation space
	 * 
	 * </br><i>Cretated on 19 dec. 2005</i>
	 */
	public MDS(DataSet d,double w,double h)
	{
		geoDataSet=new GeometricalDataSet(d);
		maxWidth=w;
		maxHeight=h;
	}

	/**
	 * Return the stress of a configuration <b>Before the scaling step !!!</b>
	 * @return the stress value between 0 and 1 : 0 euclidean distances fit perfectely with similarities
	 * </br><i>Created on 19 dec. 2005</i>
	 */
	public double getStress() {return stress;}

	/**
	 * Compute positions.
	 * It calls method from MDSLayout which first fill the High Dimensional Distance Matrix
	 */
	public void computePositions() {
			doComputation();
			stress=StressMeasurement.compute(geoDataSet);
			this.scale();
	}	
	
	/**
	 * This compute position method will generally called by kernel based algorithms such ones by chalmers et al.
	 * 
	 * </br><i>Created on 19 dec. 2005</i>
	 */
	public void computePositionsWithoutScaling() {
		doComputation();
		stress=StressMeasurement.compute(geoDataSet);
}	
	/**
 	* Main part of Position computation
 	*It used both when HDDMatrix is computed or given.
 	*/
	protected void doComputation() {}
	
	/**
	 * Method to get the x coordinate associated to an object.
	 * @param o The object
	 * @return The x coordinate
	 * </br><i>Created on 19 dec. 2005</i>
	 */
	public double getX(Object o) {return geoDataSet.getX(o);}
	
	/**
	 * Method to get the y coordinate associated to an object.
	 * @param o The object
	 * @return The x coordinate
	 * </br><i>Created on 19 dec. 2005</i>
	 */
	public double getY(Object o){return geoDataSet.getY(o);}
	
	/**
	 * This method is used to fit the mds into the representation space
	 * 
	 * </br><i>Created on 19 dec. 2005</i>
	 */
	public void scale()
	{
		double minX=Double.MAX_VALUE;
		double maxX=Double.MIN_VALUE;
		double minY=Double.MAX_VALUE;
		double maxY=Double.MIN_VALUE;
		
		Iterator iterator=geoDataSet.getDataset().getCollection().iterator();
		while(iterator.hasNext())
		{
			Object element=iterator.next();
			double x=geoDataSet.getX(element);
			double y=geoDataSet.getY(element);
			if (x>maxX)maxX=x;
			if (y>maxY)maxY=y;
			if (x<minX)minX=x;
			if (y<minY)minY=y;
		}
		
		scaleFactor=Math.min(1/(Math.abs(maxX-minX)/maxWidth),1/(Math.abs(maxY-minY)/maxHeight));
		iterator=geoDataSet.getDataset().getCollection().iterator();
		while(iterator.hasNext())
		{
			Object element=iterator.next();
			double x=geoDataSet.getX(element);
			double y=geoDataSet.getY(element);
			geoDataSet.setX(element,scaleFactor*(x-minX));
			geoDataSet.setY(element,scaleFactor*(y-minY));
		}
	}
	/**
	 * To access the GeometricalDataset
	 * @return the Geometrical Dataset
	 * </br><i>Created on 20 dec. 2005</i>
	 */
	public GeometricalDataSet getGeoDataSet() {return geoDataSet;}
	/**
	 * Provide a new GeometricalDataSet
	 * @param geoDataSet The new GeometricalDataset
	 * </br><i>Created on 20 dec. 2005</i>
	 */
	public void setGeoDataSet(GeometricalDataSet geoDataSet) {this.geoDataSet = geoDataSet;}
}
