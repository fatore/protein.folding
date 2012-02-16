/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vispipeline.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 * Load components from the \components directory.
 * @author Fernando Viera Paulovich
 */
public class ComponentsLoader {

    public ComponentsLoader() {
        root = new DefaultMutableTreeNode("Components");

        //create the component directory if it does not exist
        File compdir_aux = new File(compdir);
        if (!compdir_aux.exists()) {
            compdir_aux.mkdir();
        }

        //create the dlls directory if it does not exist
        File dll_aux = new File(dlldir);
        if (!dll_aux.exists()) {
            dll_aux.mkdir();
        }

        jars = new TreeSet<File>(new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {
                //compare two *.jar files based on their names
                return f1.getName().compareTo(f2.getName());
            }
        });

        dlls = new TreeSet<File>(new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {
                //compare two *.dll files based on their names
                return f1.getName().compareTo(f2.getName());
            }
        });

        dirs = new TreeSet<File>(new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {
                //compare two directories based on their names
                return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
            }
        });
    }

    /**
     * Load all components and create a components tree. Return the root node
     * of this tree.
     * @return The root component node.
     * @throws java.io.IOException
     */
    public DefaultMutableTreeNode execute() throws IOException {
        //get all directories (components) on the "components" directory
        ArrayList<File> aux_dirs = new ArrayList<File>();
        File[] filenames = (new File(compdir)).listFiles();
        for (int i = 0; i < filenames.length; i++) {
            if (filenames[i].isDirectory()) {
                aux_dirs.add(filenames[i]);
            }
        }

        //adding the first level *.jars
        for (int i = 0; i < aux_dirs.size(); i++) {
            ArrayList<File> jars_aux = getJars(aux_dirs.get(i), false);
            for (File f : jars_aux) {
                jars.add(f);
            }
        }

        //for each directory (component), store its libraries (*.jars and *.dlls),
        //avoiding repeated libraries, and the directories
        for (int i = 0; i < aux_dirs.size(); i++) {
            ArrayList<File> jars_aux = getJars(aux_dirs.get(i), true);
            ArrayList<File> dlls_aux = getDlls(aux_dirs.get(i));
            ArrayList<File> dirs_aux = getDirectories(aux_dirs.get(i));

            //adding the remaining *.jars
            for (File f : jars_aux) {
                if (!jars.contains(f)) {
                    jars.add(f);
                } else {
                    File floor = jars.floor(f);

                    if (floor != null && floor.getName().equals(f.getName()) &&
                            !floor.getAbsolutePath().equals(f.getAbsolutePath())) {

                        //if the files have different sizes
                        if (floor.length() != f.length()) {
                            //keep the newest one
                            if (f.lastModified() > floor.lastModified()) {
                                jars.remove(floor);
                                jars.add(f);
                            } else {
                                File old = new File(f.getAbsolutePath() + "-not-included");
                                old.delete();

                                f.renameTo(new File(f.getAbsolutePath() + "-not-included"));
                            }
                        } else {
                            File old = new File(f.getAbsolutePath() + "-not-included");
                            old.delete();

                            f.renameTo(new File(f.getAbsolutePath() + "-not-included"));
                        }
                    }
                }
            }

            //adding the *.dlls
            for (File f : dlls_aux) {
                if (!dlls.contains(f)) {
                    dlls.add(f);
                } else {
                    File floor = dlls.floor(f);

                    if (floor != null && floor.getName().equals(f.getName())) {
                        //if the files have different sizes
                        if (floor.length() != f.length()) {

                            //keep the newest one
                            if (f.lastModified() > floor.lastModified()) {
                                File old = new File(floor.getAbsolutePath() + "-not-included");
                                old.delete();

                                dlls.remove(floor);
                                floor.renameTo(new File(floor.getAbsolutePath() + "-not-included"));
                                dlls.add(f);
                            }
                        }
                    }
                }
            }

            //adding the directories
            dirs.add(aux_dirs.get(i));
            for (File f : dirs_aux) {
                dirs.add(f);
            }
        }

        //copying all *.dll files to the libraries directory
        for (File dllfile : dlls) {
            File newdll = new File(dlldir + "/" + dllfile.getName());

            if (!newdll.exists()) {
                dllfile.renameTo(newdll);
            }
        }

        //put the *.jar files on the path
        for (File jarfile : jars) {
            PathLoader.load(jarfile);
        }

        //put the directories on the path
        for (File dir : dirs) {
            PathLoader.load(dir);
        }

        //get the components names that are defined using annotations
        ArrayList<String> compclasses = getComponentClasses(aux_dirs);

        //for each component class
        for (String cc : compclasses) {
            try {
                //get the annotations
                Class klass = Class.forName(cc);
                Annotation[] annotations = klass.getAnnotations();
                Class[] interfaces = klass.getInterfaces();

                for (int i = 0; i < interfaces.length; i++) {
                    if (interfaces[i] == AbstractComponent.class) {
                        for (Annotation an : annotations) {
                            //if the annotation is a component
                            if (an instanceof vispipelinebasics.annotations.VisComponent) {
                                //create the tree path to add the component as a node
                                String hierarchy = ((vispipelinebasics.annotations.VisComponent) an).hierarchy();
                                String name = ((vispipelinebasics.annotations.VisComponent) an).name();
                                DefaultMutableTreeNode node = getNodeToAddComponent(hierarchy);

                                //check if the component is well defined
                                try {
//                                    System.out.println(klass);
//                                    System.out.println(klass.getProtectionDomain().getCodeSource().getLocation().toString());

                                    Object instance = klass.newInstance();
                                    ComponentProxy tmp = new ComponentProxy((AbstractComponent) instance);
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(ComponentTransfer.class.getName()).log(Level.SEVERE,
                                            "Error component: " + klass.getName(), ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(ComponentTransfer.class.getName()).log(Level.SEVERE,
                                            "Error component: " + klass.getName(), ex);
                                }

                                //add the component
                                ComponentTransfer transf = ComponentTransfer.getInstance(klass, name);
                                node.add(new DefaultMutableTreeNode(transf));
                            }
                        }

                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ComponentsLoader.class.getName()).log(Level.SEVERE,
                        "Error component: " + cc, ex);
            }
        }

        return root;
    }

    /**
     * Return the node which is the place to add the component.
     * @param path An string indicating the path on the tree (names separated by dot).
     * @return The node to add the component.
     */
    private DefaultMutableTreeNode getNodeToAddComponent(String path) {
        DefaultMutableTreeNode node = root;
        Enumeration<DefaultMutableTreeNode> children = root.children();
        StringTokenizer tokenizer = new StringTokenizer(path, ".");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            boolean found = false;

            if (children != null) {
                while (children.hasMoreElements()) {
                    DefaultMutableTreeNode next = children.nextElement();

                    if (next.toString().toLowerCase().trim().equals(token.toLowerCase().trim())) {
                        children = next.children();
                        node = next;
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                DefaultMutableTreeNode newnode = new DefaultMutableTreeNode(token);
                children = newnode.children();

                node.add(newnode);
                node = newnode;
            }
        }

        return node;
    }

    /**
     * Return the classes names which are components inside the a list of
     * directories.
     * @param aux_dirs The list of directories.
     * @return The classes names.
     * @throws java.io.IOException
     */
    private ArrayList<String> getComponentClasses(ArrayList<File> dirs) throws IOException {
        ArrayList<String> compclasses = new ArrayList<String>();

        //for each directory
        for (int i = 0; i < dirs.size(); i++) {
            //get the *.jar files on the directory (does not check the sub-directories)
            ArrayList<File> jars_aux = getJars(dirs.get(i), false);

            //for each *.jar file
            for (File jarfile : jars_aux) {
                ArrayList<String> classesNames = getClassesNames(jarfile);

                for (String name : classesNames) {
                    try {
                        Class klass = Class.forName(name);
                        Annotation[] annotations = klass.getAnnotations();

                        for (Annotation an : annotations) {
                            if (an instanceof vispipelinebasics.annotations.VisComponent) {
                                compclasses.add(name);
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ComponentsLoader.class.getName()).log(Level.SEVERE,
                                "Error jar: " + jarfile.getName(), ex);
                    } catch (NoClassDefFoundError ex) {
                        Logger.getLogger(ComponentsLoader.class.getName()).log(Level.SEVERE,
                                "Error jar: " + jarfile.getName(), ex);
                    }
                }
            }
        }

        return compclasses;
    }

    /**
     * Get all *.jar files inside a directory and its sub-directories if request.
     * @param dir The directory.
     * @param recursive If true, check inside the subdirectories. If false, check
     * only inside the diretory.
     * @return The *.jar files.
     */
    private ArrayList<File> getJars(File dir, boolean recursive) {
        ArrayList<File> jars_aux = new ArrayList<File>();

        File[] filenames = dir.listFiles();
        for (int j = 0; j < filenames.length; j++) {
            if (!filenames[j].isDirectory()) {
                if (filenames[j].getName().endsWith(".jar")) {
                    jars_aux.add(filenames[j]);
                }
            } else {
                if (recursive) {
                    ArrayList<File> subjars = getJars(filenames[j], true);
                    jars_aux.addAll(subjars);
                }
            }
        }

        return jars_aux;
    }

    /**
     * Get all *.dll files inside a directory (and its sub-directories).
     * @param dir The directory.
     * @return The *.dll files.
     */
    private ArrayList<File> getDlls(File dir) {
        ArrayList<File> dlls_aux = new ArrayList<File>();

        File[] filenames = dir.listFiles();
        for (int j = 0; j < filenames.length; j++) {
            if (!filenames[j].isDirectory()) {
                if (filenames[j].getName().endsWith(".dll") ||
                        filenames[j].getName().endsWith(".dylib") ||
                        filenames[j].getName().endsWith(".so")) {
                    dlls_aux.add(filenames[j]);
                }
            } else {
                ArrayList<File> subdlls = getDlls(filenames[j]);
                dlls_aux.addAll(subdlls);
            }
        }

        return dlls_aux;
    }

    /**
     * Get all directories inside a directory and its sub-directories.
     * @param dir The directory.
     * @return The directories.
     */
    private ArrayList<File> getDirectories(File dir) {
        ArrayList<File> dirs_aux = new ArrayList<File>();

        File[] filenames = dir.listFiles();
        for (int j = 0; j < filenames.length; j++) {
            if (filenames[j].isDirectory()) {
                dirs_aux.add(filenames[j]);

                ArrayList<File> subdirs = getDirectories(filenames[j]);
                dirs_aux.addAll(subdirs);
            }
        }

        return dirs_aux;
    }

    /**
     * Get all classes inside a *.jar 
     * @param jar The *.jar 
     * @return The classes names.
     * @throws java.io.IOException
     */
    private ArrayList<String> getClassesNames(File jar) throws IOException {
        ArrayList<String> classes = new ArrayList<String>();

        JarInputStream jarfile = new JarInputStream(new FileInputStream(jar));
        JarEntry jarEntry = null;

        while ((jarEntry = jarfile.getNextJarEntry()) != null) {
            String name = jarEntry.getName();

            if (name.endsWith(".class") && name.indexOf('$') == -1) {
                name = name.replaceAll(".class", "").replaceAll("/", ".");
                classes.add(name);
            }
        }

        return classes;
    }
    
    public static final String compdir = "components";
    public static final String dlldir = "dlls";
    private TreeSet<File> jars;
    private TreeSet<File> dlls;
    private TreeSet<File> dirs;
    private DefaultMutableTreeNode root;
}
