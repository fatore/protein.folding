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
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>, 
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package ncd;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import ncd.NcdDistanceMatrixFactory.CompressorType;
import textprocessing.corpus.Corpus;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class NormalCompressDistance {

    public NormalCompressDistance(CompressorType comptype, Corpus corpus) {
        this.comptype = comptype;
        this.corpus = corpus;
        this.ids = this.corpus.getIds();
    }

    public float calculateNewNCD(int id1, int id2) {
        float ncd = this.calculateNCD(id1, id2);
        float cxx = this.calculateNCD(id1, id2);
        float cyy = this.calculateNCD(id1, id2);

        return (ncd - ((cxx + cyy) / 2));
    }

    public float calculateNCD(int id1, int id2) {
        long cx = this.calculateC(id1);
        long cy = this.calculateC(id2);
        long cxy = this.calculateCombinedC(id1, id2);
        return (cxy - Math.min(cx, cy)) / (float) (Math.max(cx, cy));
    }

    protected long calculateC(int id) {
        try {
            ByteArrayOutputStream dest = new ByteArrayOutputStream();
            //zip.DeflaterOutputStream out=null;
            OutputStream out = null;

            if (this.comptype == CompressorType.BZIP2) {
                out = this.compressUsingZip(dest);
            } else {
                out = this.compressUsingGzip(dest);
            }

            String tmp = "";
            tmp += this.corpus.getFullContent(id);
            out.write(tmp.getBytes(), 0, tmp.length());

            //out.finish();
            out.flush();
            out.close();
            return (long) dest.size();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    protected long calculateCombinedC(int id1, int id2) {
        try {
            ByteArrayOutputStream dest = new ByteArrayOutputStream();
            //zip.DeflaterOutputStream out=null;
            OutputStream out = null;

            if (this.comptype == CompressorType.BZIP2) {
                out = this.compressUsingZip(dest);
            } else {
                out = this.compressUsingGzip(dest);
            }

            String tmp = this.corpus.getFullContent(id1) + " " +
                    this.corpus.getFullContent(id2);
            out.write(tmp.getBytes(), 0, tmp.length());

            //out.finish();
            out.flush();
            out.close();

            return dest.size();

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    protected void calculateAllC() {
        try {
            for (Integer id : this.ids) {
                ByteArrayOutputStream dest = new ByteArrayOutputStream();
                //zip.DeflaterOutputStream out=null;
                OutputStream out = null;

                if (this.comptype == CompressorType.BZIP2) {
                    out = this.compressUsingZip(dest);
                } else {
                    out = this.compressUsingGzip(dest);
                }

                String tmp = this.corpus.getFullContent(id);
                out.write(tmp.getBytes(), 0, tmp.length());

                //out.finish();
                out.flush();
                out.close();

                this.c.add((long) dest.size());
            }
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected OutputStream compressUsingGzip(ByteArrayOutputStream dest) throws IOException {
        return new GZIPOutputStream(new BufferedOutputStream(dest));
    }

    protected OutputStream compressUsingZip(ByteArrayOutputStream dest) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        out.setMethod(ZipOutputStream.DEFLATED);
        ZipEntry entry = new ZipEntry("");
        out.putNextEntry(entry);
        return out;
    }

    private Corpus corpus;
    private ArrayList<Integer> ids = new ArrayList<Integer>();
    private CompressorType comptype = CompressorType.BZIP2;
    private ArrayList<Long> c = new ArrayList<Long>();
}
