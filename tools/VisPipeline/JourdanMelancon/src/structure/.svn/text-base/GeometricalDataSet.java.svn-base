/*
 * Created on 17 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * GeometricalDataSet.java
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
package structure;

import java.util.HashMap;
import java.util.Iterator;

import util.Vector2D;

/**
 * Turn a DataSet into a geometrical DataSet.
 * That is each element is associated two 2D coordinates.
 * </br>Commented on 17 oct. 2005
 * @author Fabien
 */
public class GeometricalDataSet {
	DataSet dataset;
	
	HashMap geometricalElements=new HashMap();
	Object[] dataSetArray;
	
	public GeometricalDataSet(DataSet d)
	{
		dataset=d;
		createMap();
		dataSetArray=dataset.getCollection().toArray();
	}
	
	protected void createMap()
	{
		Iterator iterator=dataset.getCollection().iterator();
		while(iterator.hasNext())
		{
			Object o=iterator.next();
			geometricalElements.put(o,new Coordinate());
		}
	}
	
	public Object getItem(int i)
	{
		return dataSetArray[i];
	}
	
	public int size()
	{
		return dataset.getCollection().size();
	}
	
	public double getX(Object o)
	{
		return ((Coordinate)geometricalElements.get(o)).getX();
	}
	public double getY(Object o)
	{
		return ((Coordinate)geometricalElements.get(o)).getY();
	}
	public void setX(Object o,double x)
	{
		((Coordinate)geometricalElements.get(o)).setX(x);
	}
	public void setY(Object o,double y)
	{
		((Coordinate)geometricalElements.get(o)).setY(y);
	}
	
	
	public double getSimilarity(Object o1,Object o2)
	{
		return dataset.getSimilarity(o1,o2);
	}	
	public double getEuclideanDistance(Object o1,Object o2)
	{
		double distance=Math.sqrt((getX(o2)-getX(o1))*(getX(o2)-getX(o1))+(getY(o2)-getY(o1))*(getY(o2)-getY(o1))  );
		//return Vector2D.sub(new Vector2D(getX(o1),getY(o1)),new Vector2D(getX(o2),getY(o2))).norm();
		return distance;
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return The unit direction between this two nodes
	 */
		public Vector2D unitVector2D(Object source, Object target)
		{
			double x=0;
			double y=0;
			if(getEuclideanDistance(source,target)!=0)
			{
				x=(getX(target)-getX(source))/(getEuclideanDistance(source,target));
				y=(getY(target)-getY(source))/(getEuclideanDistance(source,target));
			}
			return new Vector2D(x,y);
		}
	
	
	protected class Coordinate{
		protected double x=Double.MIN_VALUE;
		protected double y=Double.MIN_VALUE;
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
	}
	
	public DataSet getDataset() {return dataset;}
}
