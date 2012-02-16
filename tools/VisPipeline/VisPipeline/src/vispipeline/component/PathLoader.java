/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Add *.jar files or directories to the classpath.
 * @author Fernando Vieira Paulovich
 */
public class PathLoader {

    /**
     * Add a *.jar file or a directory to the classpath.
     * @param jarfile The *.jar file or the directory.
     * @throws java.io.IOException
     */
    public static void load(File file) throws IOException {
        try {
            URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Class sysclass = URLClassLoader.class;
            Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{file.toURI().toURL()});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PathLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PathLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PathLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PathLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PathLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
