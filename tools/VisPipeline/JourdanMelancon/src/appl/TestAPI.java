/*
 * Created on 18 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * TestAPI.java
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

import java.util.Random;
import java.util.Vector;

import layout.ChalmersSampling;
import structure.DataSet;
import util.SimilarityEngine;

/**
 * This class is a sample API to show how MDS API can be used.
 * The DataSet is made of a set of Double Objects.
 * Similarity is only the normalised absolute value of the difference between double values.</br>
 * Note that we expect to get a drawing showing aligned points.
 * 
 * </br>Commented on 18 oct. 2005
 * @author Fabien
 */
public class TestAPI {
	static double width=400;
	static double height=400;
	static int range=10000;
	static int nbElements=500;
	static Random random=new Random();
	
	public static void main(String[] args) {
		//Create the collection for the dataset
		Vector data=new Vector();
		//Fill the dataset with objects, her for the example we use a Double Object
		for(int i=0;i<nbElements;i++)
		{
			Double obj=new Double(random.nextInt(range));
			while(data.contains(obj))
				obj=new Double(random.nextInt(range));
			data.add(new Double(random.nextInt(range)));
		}	
		//Create the dataset with the collection and the way to compute similarity measure
		//It is achieved by creating a new Dataset class which implements the getSimilarity method
		MySimilarity sim=new MySimilarity();
		DataSet dataset=new DataSet(data,sim);
		
		//Creation of a ChalmersLinear Object
		//ChalmersLinear mds=new ChalmersLinear(dataset,width,height);
		ChalmersSampling mds=new ChalmersSampling(dataset,width,height);
		//JourdanMelanconSampling mds=new JourdanMelanconSampling(dataset,width,height);
		//Call the computation method
		//long begin=new Date().getTime();
		mds.computePositions();
		//long end=new Date().getTime();
		//System.err.println("Temps "+(end-begin));
		
		//SWING view of the result
		//Through the chalmers object you can easily access coordinates of one of the object
		TestFrame frame=new TestFrame(width,height,mds,dataset);
	}
	protected static class MySimilarity extends SimilarityEngine
	{
		public double getSimilarity(Object a, Object b) {
			return Math.abs((((Double)a).doubleValue()-((Double)b).doubleValue())/(TestAPI.range));
		}
	}
}
