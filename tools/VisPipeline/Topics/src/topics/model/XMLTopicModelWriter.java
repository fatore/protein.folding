/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics.model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projection.model.ProjectionInstance;
import visualizationbasics.model.AbstractInstance;
import projection.model.Scalar;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class XMLTopicModelWriter {

    public void write(TopicProjectionModel model, String filename) throws IOException {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "ISO-8859-1"));

            //writting the header
            out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\r\n");
            out.write("<projection>\r\n");

            //writting the instances
            out.write("<!--   instances   -->\r\n");

            ArrayList<AbstractInstance> instances = model.getInstances();

            for (AbstractInstance ai : instances) {
                ProjectionInstance pi = (ProjectionInstance) ai;

                out.write("<instance id=\"");
                out.write(Integer.toString(pi.getId()));
                out.write("\">\r\n");

                out.write("<x-coordinate value=\"");
                out.write(Float.toString(pi.getX()));
                out.write("\"/>\r\n");

                out.write("<y-coordinate value=\"");
                out.write(Float.toString(pi.getY()));
                out.write("\"/>\r\n");

                out.write("<label value=\"");
                out.write(convert(deConvert(encodeToValidChars(pi.toString()))));
                out.write("\"/>\r\n");

                out.write("<scalars>\r\n");
                for (Scalar s : model.getScalars()) {
                    out.write("<scalar name=\"");
                    out.write(s.getName().replaceAll("\"", "'"));
                    out.write("\" value=\"");
                    out.write(Float.toString(pi.getScalarValue(s)));
                    out.write("\"/>\r\n");
                }
                out.write("</scalars>\r\n");

                out.write("</instance>\r\n");
            }

            out.write("</projection>\r\n");

        } catch (FileNotFoundException e) {
            throw new IOException("File \"" + filename + "\" was not found!");
        } catch (IOException e) {
            throw new IOException("Problems reading the file \"" + filename + "\"");
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(XMLTopicModelWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static String convert(String value) {
        if (value != null) {
            value = value.replaceAll("&", "&amp;");
            value = value.replaceAll("<", "&lt;");
            value = value.replaceAll(">", "&gt;");
            value = value.replaceAll("\"", "&quot;");
            value = value.replaceAll("\'", "&#39;");
        } else {
            return "";
        }
        return value;
    }

    public static String deConvert(String value) {
        if (value != null) {
            value = value.replaceAll("&amp;", "&");
            value = value.replaceAll("&lt;", "<");
            value = value.replaceAll("&gt;", ">");
            value = value.replaceAll("&quot;", "\"");
            value = value.replaceAll("&#39;", "\'");
        } else {
            return "";
        }
        return value;
    }

    public static String encodeToValidChars(String pData) {
        StringBuffer encodedData = new StringBuffer();

        for (int i = 0; i < pData.length(); i++) {
            char ch = pData.charAt(i);
            int chVal = (int) ch;

            if (chVal >= 32 && chVal <= 255) {
                encodedData.append((char) chVal);
            } else {
                encodedData.append(" ");
            }
        }

        return encodedData.toString();
    }

}
