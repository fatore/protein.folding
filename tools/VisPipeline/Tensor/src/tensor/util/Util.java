/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import projection.model.Scalar;
import tensor.model.FiberInstance;
import tensor.model.FiberModel;
import visualizationbasics.model.AbstractInstance;



/**
 *
 * @author jpocom
 */

public class Util {

    //cdata;year
    //filename1.txt;1.3;0.70
    //filename2.txt;4.0;0.06
    //filename3.txt;6.7;0.40
    //filename4.txt;3.0;0.12
    //filename5.txt;8.9;0.11
    public static void importScalars(FiberModel model, String filename) throws IOException {
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
            HashMap<Integer, FiberInstance> index = new HashMap<Integer, FiberInstance>();
            for (AbstractInstance pi : model.getInstances()) {
                index.put(pi.getId(), (FiberInstance)pi);
            }

            //reading the scalars
            while ((line = in.readLine()) != null) {
                linenumber++;
                ArrayList<Float> values = new ArrayList<Float>();

                //ignore comments
                if (line.trim().length() > 0 && line.lastIndexOf('#') == -1) {
                    StringTokenizer t = new StringTokenizer(line, ";", false);

                    //Capturing the id
                    int id = Integer.parseInt(t.nextToken().trim());

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
                    FiberInstance pi = index.get(id);

                    if (pi != null) {
                        for (int i = 0; i < scalars.size(); i++) {
                            Scalar s = model.addScalar(scalars.get(i));
                            pi.setScalarValue(s, values.get(i));
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
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void exportScalars(FiberModel model, String filename) throws IOException {
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
            for (AbstractInstance p : model.getInstances()) {
                FiberInstance pi = (FiberInstance)p;
                out.write(pi.getId() + ";");

                for (int i = 0; i < model.getScalars().size(); i++) {
                    float scalar = pi.getScalarValue(model.getScalars().get(i));
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
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
