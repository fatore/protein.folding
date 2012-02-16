/*
 * Created on 2 nov. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * JourdanMelanconSampling.java
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import structure.DataSet;

/**
 *	This algorithm is an O(NlogN) version of MDS. See <a href="http://www.lirmm.fr/~fjourdan/Ressources/Articles/JourdanMelanconIV04.ps" target="blank">[Jourdan and Melancon 2004]</a> for more details.
 * </br>Commented on 3 nov. 2005
 * @author Fabien
 */
public class JourdanMelanconSampling extends ChalmersSampling {

	protected int nbOfReferenceNodes=6;
	protected HashMap referenceNodesAndOrderedSets=new HashMap();
	
	public JourdanMelanconSampling(DataSet d,double w,double h) {super(d,w,h);}
	
	protected void buildOrderedArrays()
	{
		ArrayList referenceNodes=sampleOfSamples(nbOfReferenceNodes);
		Iterator allReferenceNodes=referenceNodes.iterator();
		while(allReferenceNodes.hasNext())
		{
			Object referenceNode=allReferenceNodes.next();
			//Build the ordered list corresponding to this node.
			HDNodeComparator comparator=new HDNodeComparator(referenceNode);
			TreeSet set=new TreeSet(comparator);
			Iterator allSamples=samples.iterator();
			while(allSamples.hasNext())
			{
				Object node=allSamples.next();
				if(!node.equals(referenceNode))
				{
					set.add(node);
				}
			}
			referenceNodesAndOrderedSets.put(referenceNode,set);
			Iterator allElementsInSet=set.iterator();
		}		
	}
	
/**
 * This method is extended to add the pre-treatment phase.
 * It consists in ordering kernel nodes according to a constant number of reference nodes.
 */
	protected void initParameters()
	{
		super.initParameters();
		buildOrderedArrays();
	}
	
	/**
	 * This method extends the findHDClosestParent from chalmersSampling.
	 * Indeed the complexity improvement comes from this methods which provides a parent in NlogN
	 */
		protected Object findHDClosestParent(Object node)
		{
		Object parent=null;
		Iterator allReferences=referenceNodesAndOrderedSets.keySet().iterator();
		ArrayList newReferences=new ArrayList();
		double bestValue2=Double.MAX_VALUE;
		while(allReferences.hasNext())
		{
			Object ref=allReferences.next();
			TreeSet set=(TreeSet)referenceNodesAndOrderedSets.get(ref);
			Object[] nodeArray=((TreeSet)referenceNodesAndOrderedSets.get(ref)).toArray();
			double d=geoDataSet.getSimilarity(node,ref);
			
			Object c=getClosestTo(nodeArray,d,ref);
			double cDist=geoDataSet.getSimilarity(c,node);
			if (cDist<bestValue2)
			{
				parent=c;
				bestValue2=cDist;
			}
		}
		return parent;
	}
	
	
	/**
	 * Internal method used by findHDClosestParent.
	 * @param nodeArray
	 * @param d
	 * @param ref
	 * @return The closest parent in an ordered set
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	private Object getClosestTo(Object[] nodeArray,double d,Object ref)
		{
			Object r=null;
			int debut=0;
			int fin=nodeArray.length-1;
			int middle=(debut+fin)/2;
			double distMiddle=0;
			while(debut<fin)
			{
				double distDebut=geoDataSet.getSimilarity(nodeArray[debut],ref);
				double distFin=geoDataSet.getSimilarity(nodeArray[fin],ref);
				if ((d-distDebut)<(d-distFin))
					r=nodeArray[debut];
				else
					r=nodeArray[fin];
				distMiddle=geoDataSet.getSimilarity(nodeArray[middle],ref);
				if (d<distMiddle)
				{
					fin=middle;
					middle=(debut+fin)/2;
				}
				else
				{
					debut=middle+1;
					middle=(debut+fin)/2;
				}
			}
			return r;		
		}
		
	
	public class HDNodeComparator implements Comparator
	{
		protected Object reference;
		
		public HDNodeComparator(Object ref)
		{super();reference=ref;}
		//Never return 0 !!!!!
		//If we return 0 the treeset will sctatch objects with same HDDdistance values.
		public int compare(Object node1,Object node2)
		{
			int result=0;
			if (geoDataSet.getSimilarity(reference,node1)>geoDataSet.getSimilarity(reference,node2)) result=1;
			else result=-1;
			return result;
		}
	}
	
	
}
