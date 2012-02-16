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
 * of the original code is Roberto Pinho <robertopinho@yahoo.com.br>, 
 * Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package textprocessing.corpus.zip;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import textprocessing.corpus.Corpus;
import textprocessing.processing.Ngram;
import visualizationbasics.util.Util;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ZipCorpus extends Corpus {

    /**
     * Creates a new instance of ZipCorpora
     * @param url
     * @param nrGrams
     */
    public ZipCorpus(String url, int nrGrams) {
        super(url, nrGrams);

        this.idtourl = new HashMap<Integer, String>();

        this.run();

        //the inverted corpus must be on the same place of corpus
        String invCorpFilename = this.url.substring(0, this.url.length() - 4) + ".inv";
        this.invCorpus = new InvertedZipCorpus(this, nrGrams, invCorpFilename);
    }

    @Override
    public String getViewContent(int id) throws IOException {
        return this.getFullContent(id);
    }

    @Override
    public String getSearchContent(int id) throws IOException {
        return this.getFullContent(id);
    }

    @Override
    public String getFullContent(int id) throws IOException {
        ZipFile zip = null;
        try {
            zip = new ZipFile(this.url);

            ZipEntry entry = zip.getEntry(idToUrl(id));

            if (entry != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(zip.getInputStream(entry),
                        Corpus.getEncoding().toString()));

                String line;
                StringBuffer text = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    text.append(line + "\r\n");
                }

                return text.toString();
            }
        } catch (IOException e) {
            throw new IOException("File " + idToUrl(id) + " does not exist.");
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    @Override
    public String getFilteredContent(int id) throws IOException {
        return this.getFullContent(id);
    }

    @Override
    public ArrayList<Ngram> getCorpusNgrams() throws IOException {
        return this.invCorpus.getCorpusNgrams();
    }

    @Override
    public ArrayList<Ngram> getNgrams(int id) throws IOException {
        return this.invCorpus.getNgrams(id);
    }

    public String idToUrl(int id) {
        return idtourl.get(id);
    }

    @Override
    protected void run() {
        this.createIndex();
        this.createCdata();
    }

    private void createCdata() {
        if (this.url != null && this.ids != null) {
            //Capturing the initials
            ArrayList<String> initials_aux = new ArrayList<String>();
            for (int i = 0; i < this.ids.size(); i++) {
                String filename = idToUrl(ids.get(i));

                int begin = filename.lastIndexOf("/");
                if (begin > -1) {
                    filename = filename.substring(begin + 1);
                }

                String ini = filename;
                if (filename.length() > 2) {
                    ini = filename.substring(0, 2);
                }

                //Create the initials with two letters
                if (!initials_aux.contains(ini)) {
                    initials_aux.add(ini);
                }
            }

            String[] initials = new String[initials_aux.size()];
            for (int i = 0; i < initials_aux.size(); i++) {
                initials[i] = initials_aux.get(i);
            }

            Arrays.sort(initials);

            //Creating the cdata
            if (initials.length > 1) {
                this.cdata = new float[this.ids.size()];

                for (int i = 0; i < this.ids.size(); i++) {
                    this.cdata[i] = -1;
                    for (int j = 0; j < initials.length; j++) {

                        //Taking out the part of the file that correspond to the directory
                        String filename = idToUrl(ids.get(i));

                        //int begin=filename.lastIndexOf(System.getProperty("file.separator"));
                        int begin = filename.lastIndexOf("/");
                        if (begin > -1) {
                            filename = filename.substring(begin + 1);
                        }

                        //Given the cdata number
                        if (filename.startsWith(initials[j])) {
                            this.cdata[i] = j;
                        }
                    }
                }
            } else {
                this.cdata = new float[this.ids.size()];
                Arrays.fill(this.cdata, 0.0f);
            }
        }
    }

    private void createIndex() {
        if (this.url != null) {
            this.ids = new ArrayList<Integer>();

            HashSet<Integer> uniques = new HashSet<Integer>();

            ZipFile zip = null;

            try {
                zip = new ZipFile(this.url);

                Enumeration entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();

                    if (!entry.isDirectory()) {
                        ZipEntry entry2 = zip.getEntry(entry.getName());

                        if (entry2 != null) {
                            String filename = entry2.getName();
                            int id = Util.convertToInt(filename);

                            this.ids.add(id);
                            uniques.add(id);
                            this.idtourl.put(id, filename);
                        }
                    }
                }

                if (this.ids.size() != uniques.size()) {
                    throw new IOException("There are some non-unique ids: " +
                            this.ids.size() + " != " + uniques.size());
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (zip != null) {
                    try {
                        zip.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ZipCorpus corpus = new ZipCorpus("D:\\Meus documentos\\FERNANDO\\Corpora\\cbr-ilp-ir.zip", 1);

        for (int id : corpus.getIds()) {
            try {
                System.out.println(corpus.getFullContent(id));
                System.out.println("##############");
            } catch (IOException ex) {
                Logger.getLogger(ZipCorpus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    private HashMap<Integer, String> idtourl;
    private InvertedZipCorpus invCorpus;
}
