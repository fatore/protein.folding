/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizationbasics.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PropertiesManager {

    /** Creates a new instance of PropertiesManager */
    private PropertiesManager(String filename) throws IOException {
        this.filename = filename;

        if (getClass().getClassLoader().getResource(filename) != null) {
            try {
                File file = new File(getClass().getClassLoader().getResource(filename).toURI());

                if (file.exists()) {
                    this.properties = new Properties();
                    FileInputStream fis = new FileInputStream(file);
                    this.properties.load(fis);
                    fis.close();
                } else {
                    throw new FileNotFoundException("File \'" + filename + "\' does not exist.");
                }
            } catch (URISyntaxException ex) {
                Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.properties = new Properties();
        }
    }

    public static PropertiesManager getInstance(String filename) throws IOException {
        return new PropertiesManager(filename);
    }

    public String getProperty(String id) {
        if (properties == null) {
            return "";
        } else {
            if (properties.containsKey(id)) {
                return properties.getProperty(id);
            } else {
                return "";
            }
        }
    }

    public void setProperty(String id, String value) {
        if (properties == null) {
            properties = new Properties();
        }

        properties.setProperty(id, value);

        try {
            if (getClass().getClassLoader().getResource(filename) != null) {
                File file = new File(getClass().getClassLoader().getResource(filename).toURI());
                FileOutputStream out = new FileOutputStream(file);
                properties.store(out, "Recording the properties...");
                out.flush();
                out.close();
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Properties properties;
    private String filename;
}
