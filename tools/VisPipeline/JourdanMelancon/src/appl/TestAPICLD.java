package appl;

import java.util.*;
import java.util.Vector;
import java.lang.Exception;
import java.io.*;


import layout.*;
import structure.DataSet;
import util.*;


/**
 * This class is a sample API to show how MDS API can be used.
 * The DataSet is made of a set of Double Objects.
 * Similarity is only the normalised absolute value of the difference between double values.</br>
 * Note that we expect to get a drawing showing aligned points.
 * 
 * </br>Commented on 18 oct. 2005
 * @author Fabien
 */
public class TestAPICLD {
	static double width=400;
	static double height=400;
	//static int range=10000;
	//static int nbElements=500;
	static Random random=new Random();
	
	public static void main(String[] args) {
	    //Create the collection for the dataset
	    Vector data = new Vector();
	    try 
		{
		    data=getCLDDataFromFile("C:/Recherche/Donnees/Multimedia/aqua.txt");
		} 
	    catch(Exception e) 
		{
		    System.err.println(e.toString());
		}
	    //Create the dataset with the collection and the way to compute similarity measure
	    //It is achieved by creating a new Dataset class which implements the getSimilarity method
	    Double max = getMax(data);
	    System.out.println("Max = " + max);
	    CLDSimilarity sim=new CLDSimilarity(max);
	    DataSet dataset=new DataSet(data,sim);

	    
	    //Creation of a ChalmersLinear Object
	    //ChalmersLinear mds=new ChalmersLinear(dataset,width,height);
	    //mds.setOptimisation();
	    //ChalmersSampling mds=new ChalmersSampling(dataset,width,height);
	    JourdanMelanconSampling mds=new JourdanMelanconSampling(dataset,width,height);
	    
	    //Call the computation method
	    long begin=new Date().getTime();
	    mds.computePositions();
	    long end=new Date().getTime();
//	   System.err.println("Temps "+(end-begin));
	   System.err.println("Stress " + mds.getStress());
	   System.out.println(mds.getGeoDataSet().toString());
	    //SWING view of the result
	    //Through the chalmers object you can easily access coordinates of one of the object
	    TestFrame frame=new TestFrame(width,height,mds,dataset);
	}
    
    protected static class CLDSimilarity extends SimilarityEngine
    {
    	Double _max;
    	
    	CLDSimilarity(Double max) 
    	{
    		_max = max;
    	}
    		@SuppressWarnings("unchecked")
			public double getSimilarity(Object a, Object b) {
    			  Vector clda = (Vector) a;
    			  Vector cldb = (Vector) b;
    			  Double r1 = 0.0, r2 = 0.0, r3 = 0.0;
    			    
    			  for(int i=0; i<6; i++)
    			     r1 += Math.pow((Double) clda.get(i) - (Double)cldb.get(i), 2.0);
    			    
    			  for(int i=6; i<9; i++)
     			     r2 += Math.pow((Double) clda.get(i) - (Double)cldb.get(i), 2.0);
    			  
    			  for(int i=9; i<11; i++)
     			     r3 += Math.pow((Double) clda.get(i) - (Double)cldb.get(i), 2.0);
    			  
    			  double sum = Math.sqrt(r1) + Math.sqrt(r2) + Math.sqrt(r3);
    			  return sum / _max; 
    		}
    }
    
    public static Double getMax(Vector v)
    {
    	CLDSimilarity sim = new CLDSimilarity(1.0);
    	Double max = 1.0;
    	for(int i = 0; i < v.size(); i++)
    	{
    		Object v1 = v.get(i);
    		for(int j = i; j < v.size(); j++)
    		{
    			Object v2 = v.get(j);
    			if(sim.getSimilarity(v1,v2) > max)
    			{
    				max = sim.getSimilarity(v1,v2);
    			}
    		}
    	}
    	return max;
    }
    
    public static Vector getCLDDataFromFile(String filename) throws IOException
    {
	FileReader file = new FileReader(filename);
	StreamTokenizer input = new StreamTokenizer(file);
	input.eolIsSignificant(true);
	int token;
	Vector result = new Vector<Vector<Double>>();
	Vector cld = new Vector<Double>();
	while( (token = input.nextToken()) != StreamTokenizer.TT_EOF)
	    {
		if(token == StreamTokenizer.TT_EOL)
		    {
			result.add(cld);
			cld = new Vector<Double>();
		    }
		if(token == StreamTokenizer.TT_NUMBER)
		    {
			cld.add((Double) input.nval);
		    }
	    } 	 
	file.close();
	System.out.println("Number of descriptors " + result.size() );
	return result;
    }
}
