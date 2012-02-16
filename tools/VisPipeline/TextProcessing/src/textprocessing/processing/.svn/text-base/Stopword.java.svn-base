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
 * Contributor(s): 
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package textprocessing.processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import textprocessing.util.TextProcessingConstants;
import visualizationbasics.util.PropertiesManager;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class Stopword {

    private Stopword() {
        this.stopwords = new ArrayList<String>();
    }

    public static Stopword getInstance() throws IOException {
        if (instance == null) {
            PropertiesManager spm = PropertiesManager.getInstance(TextProcessingConstants.PROPFILENAME);
            String filename = spm.getProperty("SPW.FILE");

            instance = new Stopword();

            if (Stopword.class.getClassLoader().getResource(filename) != null) {
                try {
                    File file = new File(Stopword.class.getClassLoader().getResource(filename).toURI());

                    if (!file.isDirectory() && file.exists()) {
                        instance.readStopwordList(file);
                    } else {
                        Logger.getLogger(Stopword.class.getName()).log(Level.SEVERE,
                                "ERROR: stopwords file not found: " + filename);
                    }
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Stopword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return instance;
    }

    public void changeStopwordList(String filename) throws IOException {
        this.stopwords = new ArrayList<String>();

        File file = new File(filename);
        if (!file.isDirectory() && file.exists()) {
            readStopwordList(file);
        } else {
            Logger.getLogger(Stopword.class.getName()).log(Level.SEVERE,
                    "ERROR: stopwords file not found: " + filename);
        }
    }

    public List<String> getStopwordList() {
        return stopwords;
    }

    public void addStopwords(List<String> stopwords) {
        for (String stopword : stopwords) {
            if (!this.stopwords.contains(stopword.toLowerCase())) {
                this.stopwords.add(stopword.toLowerCase());
            }
        }
        Collections.sort(this.stopwords);
    }

    public void removeStopword(String stopword) {
        this.stopwords.remove(stopword);
    }

    public void saveStopwordsList(String filename) throws IOException {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new FileWriter(filename));

            for (String stopword : stopwords) {
                out.write(stopword);
                out.write("\n");
            }
        } catch (IOException ex) {
            throw new IOException("Problems saving \"" + filename + "\" file!");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public boolean isStopWord(String word) {
        return (Collections.binarySearch(stopwords, word) >= 0);
    }

    private void readStopwordList(File file) throws IOException {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = in.readLine()) != null && line.trim().length() > 0) {
                stopwords.add(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File \"" + file.getAbsolutePath() + "\" was not found!");
        } catch (IOException e) {
            throw new IOException("Problems reading the file \"" + file.getAbsolutePath() + "\"");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        Collections.sort(stopwords);
    }

    private ArrayList<String> stopwords;
    private static Stopword instance;
}
