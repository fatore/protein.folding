/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Dump {

    private static FileWriter writer;

    static {
        try {
            writer = new FileWriter(new File("dump.txt"), true);
        } catch (IOException ex) {
            Logger.getLogger(Dump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dump(String msg) {
        try {
            if (writer != null) {
                writer.write(msg + "\r\n");
                writer.flush();
            }

            System.out.println(msg);
        } catch (IOException ex) {
            Logger.getLogger(Dump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setFilename(String filename) {
        try {
            if (writer != null) {
                writer.close();
            }
            
            writer = new FileWriter(new File(filename), true);
        } catch (IOException ex) {
            Logger.getLogger(Dump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
