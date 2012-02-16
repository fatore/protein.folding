/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.model.ProjectionInstance;
import projection.model.ProjectionModel;
import visualizationbasics.model.AbstractInstance;
import projection.model.Scalar;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ProjectionUtil {

    public static AbstractMatrix modelToMatrix(ProjectionModel model, Scalar scalar) {
        DenseMatrix matrix = new DenseMatrix();

        ArrayList<String> attr = new ArrayList<String>();
        attr.add("x");
        attr.add("y");

        matrix.setAttributes(attr);

        ArrayList<AbstractInstance> instances = model.getInstances();

        for (int i = 0; i < instances.size(); i++) {
            ProjectionInstance ins = (ProjectionInstance) instances.get(i);

            float[] vect = new float[2];
            vect[0] = ins.getX();
            vect[1] = ins.getY();
            matrix.addRow(new DenseVector(vect, ins.getId(), ins.getScalarValue(scalar)));
        }

        return matrix;
    }

    //cdata;year
    //filename1.txt;1.3;0.70
    //filename2.txt;4.0;0.06
    //filename3.txt;6.7;0.40
    //filename4.txt;3.0;0.12
    //filename5.txt;8.9;0.11
    public static void importScalars(ProjectionModel model, String filename) throws IOException {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new java.io.FileReader(filename));
            ArrayList<String> scalars = new ArrayList<String>();

            //Capturing the scalar names
            int linenumber = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                linenumber++;

                //ignore comments
                if (line.trim().length() > 0 && line.lastIndexOf('#') == -1) {
                    StringTokenizer t = new StringTokenizer(line, ";");

                    while (t.hasMoreTokens()) {
                        scalars.add(t.nextToken().trim());
                    }

                    break;
                }
            }

            //index for the instances
            HashMap<Integer, AbstractInstance> index = new HashMap<Integer, AbstractInstance>();
            for (AbstractInstance pi : model.getInstances()) {
                index.put(pi.getId(), pi);
            }

            //reading the scalars            
            while ((line = in.readLine()) != null) {
                linenumber++;
                ArrayList<Float> values = new ArrayList<Float>();

                //ignore comments
                if (line.trim().length() > 0 && line.lastIndexOf('#') == -1) {
                    StringTokenizer t = new StringTokenizer(line, ";", false);

                    //Capturing the filename
                    String fname = t.nextToken().trim();

                    //Capturing the scalar values
                    while (t.hasMoreTokens()) {
                        float value = Float.parseFloat(t.nextToken().trim());
                        values.add(value);
                    }

                    //checking the data
                    if (scalars.size() != values.size()) {
                        throw new IOException("The number of values for one scalar " +
                                "does not match with the number of declared scalars.\r\n" +
                                "Check line " + linenumber + " of the file.");
                    }

                    //Adding the scalar values to the instance
                    AbstractInstance pi = index.get(Integer.parseInt(fname));

                    if (pi != null) {
                        for (int i = 0; i < scalars.size(); i++) {
                            Scalar s = model.addScalar(scalars.get(i));
                            ((ProjectionInstance) pi).setScalarValue(s, values.get(i));
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectionUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void exportScalars(ProjectionModel model, String filename) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));

            //writing the scalar names
            for (int i = 0; i < model.getScalars().size(); i++) {
                out.write(model.getScalars().get(i).getName().replaceAll(";", "_"));
                if (i < model.getScalars().size() - 1) {
                    out.write(";");
                }
            }

            out.write("\r\n");

            //writing the scalar values
            for (AbstractInstance pi : model.getInstances()) {
                out.write(pi.getId() + ";");

                for (int i = 0; i < model.getScalars().size(); i++) {
                    float scalar = ((ProjectionInstance) pi).getScalarValue(model.getScalars().get(i));
                    out.write(Float.toString(scalar).replaceAll(";", "_"));
                    if (i < model.getScalars().size() - 1) {
                        out.write(";");
                    }
                }

                out.write("\r\n");
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectionUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Configures the system log.
     * @param console If true, the console log is activated, false it is not.
     * @param file If true, the file log is activated, false it is not.
     * @throws java.io.IOException
     */
    public static void log(boolean console, boolean file) throws IOException {
        String filename = "log%g.txt";
        int limit = 10000000; // 10 Mb

        int numLogFiles = 5;

        if (file) {
            FileHandler handler = new FileHandler(filename, limit, numLogFiles, true);
            handler.setFormatter(new SimpleFormatter());
//          handler.setFormatter(new XMLFormatter());
            handler.setLevel(Level.ALL);
            Logger.getLogger("").addHandler(handler);

            Logger.getLogger("Util").log(Level.INFO, "Adding the file logging.");
        } else {
            Logger.getLogger("Util").log(Level.INFO, "Removing the file logging.");

            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();

            for (int i = 0; i < handlers.length; i++) {
                if (handlers[i] instanceof FileHandler) {
                    rootLogger.removeHandler(handlers[i]);
                }
            }
        }

        //disable console log
        if (console) {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();

            for (int i = 0; i < handlers.length; i++) {
                if (handlers[i] instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handlers[i]);
                }
            }

            rootLogger.addHandler(new ConsoleHandler());

            Logger.getLogger("Util").log(Level.INFO, "Adding console logging.");
        } else {
            Logger.getLogger("Util").log(Level.INFO, "Removing console logging.");

            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();

            for (int i = 0; i < handlers.length; i++) {
                if (handlers[i] instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handlers[i]);
                }
            }
        }
    }
//    public static int showDialog(AbstractComponent comp) {
//        int result = WizardDialog.SUCESS;
//        AbstractParametersView parameditor = comp.getParametersEditor();
//        if (parameditor != null) {
//            parameditor.reset();
//            WizardDialog dialog = WizardDialog.getInstance(null, parameditor);
//            result = dialog.display();
//        }
//
//        return result;
//    }
}
