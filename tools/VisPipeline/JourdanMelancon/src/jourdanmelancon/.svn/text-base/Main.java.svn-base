/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jourdanmelancon;

import appl.TestFrame;
import java.util.Random;
import java.util.Vector;
import layout.JourdanMelanconSampling;
import structure.DataSet;
import util.SimilarityEngine;

/**
 *
 * @author Fernando V. Paulovic
 */
public class Main {

    static double width = 400;
    static double height = 400;
    static int range = 10000;
    static int nbElements = 500;
    static Random random = new Random();

    public static void main(String[] args) throws CloneNotSupportedException {
        //Create the collection for the dataset
        Vector data = new Vector();
        //Fill the dataset with objects, her for the example we use a Double Object
        for (int i = 0; i < nbElements; i++) {
            Double obj = new Double(random.nextInt(range));
//            while (data.contains(obj)) {
//                obj = new Double(random.nextInt(range));
//            }
            data.add(new Double(random.nextInt(range)));
        }
        //Create the dataset with the collection and the way to compute similarity
        //measure. It is achieved by creating a new Dataset class which implements
        //the getSimilarity method
        MySimilarity sim = new MySimilarity();
        DataSet dataset = new DataSet(data, sim);
        //Creation of a ChalmersLinear Object
        //ChalmersLinear mds=new ChalmersLinear(dataset,width,height);
        //ChalmersSampling mds=new ChalmersSampling(dataset,width,height);
        JourdanMelanconSampling mds = new JourdanMelanconSampling(dataset, width, height);
        //Call the computation method
        mds.computePositions();
        //SWING view of the result
        //Through the chalmers object you can easily access coordinates of one
        //of the object
        TestFrame frame = new TestFrame(width, height, mds, dataset);
    }

    protected static class MySimilarity extends SimilarityEngine {

        public double getSimilarity(Object a, Object b) {
            return Math.abs((((Double) a).doubleValue()
                    - ((Double) b).doubleValue()));
        }

    }

//    public static void main(String[] args) throws CloneNotSupportedException, IOException {
////        String filename = "D:\\pixel_watershed\\data\\NEW_paperOnlyGabor3x3NoStand.data";
////        String filename = "D:\\My Documents\\FERNANDO\\Dados\\mammals-5000-normalized.data";
//        String filename = "D:\\My Documents\\FERNANDO\\Dados\\iris-std.data";
//        Matrix matrix = MatrixFactory.getInstance(filename);
//
//        //creating the data
//        ArrayList data = new ArrayList();
//        for (int i = 0; i < matrix.getRowCount(); i++) {
//            data.add(matrix.getRow(i).toArray());
//        }
//
//        //Create the dataset with the collection and the way to compute similarity
//        //measure. It is achieved by creating a new Dataset class which implements
//        //the getSimilarity method
//        MySimilarity sim = new MySimilarity();
//        DataSet dataset = new DataSet(data, sim);
//
//        //Creation of a ChalmersLinear Object
//        ChalmersLinear mds = new ChalmersLinear(dataset, width / 2, height / 2);
//        //ChalmersSampling mds = new ChalmersSampling(dataset, width, height);
//        //JourdanMelanconSampling mds = new JourdanMelanconSampling(dataset, 10000, 10000);
//        //Call the computation method
//        mds.computePositions();
//
//        BufferedWriter out = null;
//        try {
//            out = new BufferedWriter(new FileWriter(filename + "-HM.prj"));
//
//            out.write("DY\r\n");
//            out.write(matrix.getRowCount() + "\r\n");
//            out.write("2\r\n");
//            out.write("x;y\r\n");
//
//            Iterator iterator = dataset.getCollection().iterator();
//            int i = 0;
//            while (iterator.hasNext()) {
//                Object element = iterator.next();
//                int x = (int) mds.getX(element);
//                int y = (int) mds.getY(element);
//                out.write(matrix.getRow(i).getId() + ";" + x +
//                        ";" + y + ";" + matrix.getRow(i).getKlass() + "\r\n");
//                i++;
//            }
//        } catch (IOException e) {
//            throw new IOException(e.getMessage());
//        } finally {
//            //fechar o arquivo
//            if (out != null) {
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//
//    protected static class MySimilarity extends SimilarityEngine {
//
//        public double getSimilarity(Object a, Object b) {
//            Dissimilarity sim = new Euclidean();
//            DenseVector va = new DenseVector((float[]) a);
//            DenseVector vb = new DenseVector((float[]) b);
//            return sim.calculate(va, vb);
//        }
//
//    }
//    static double width = 10;
//    static double height = 10;
}
