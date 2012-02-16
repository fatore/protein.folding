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

package topics.util;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import textprocessing.corpus.Corpus;
import textprocessing.corpus.CorpusFactory;
import textprocessing.util.filter.ZIPFilter;
import topics.model.TopicProjectionModel;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.util.PropertiesManager;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class OpenDialog extends visualizationbasics.util.OpenDialog {

    public static boolean checkCorpus(TopicProjectionModel model,
            Component parent) throws IOException {
        if (OpenDialog.dialog == null) {
            OpenDialog.dialog = new javax.swing.JFileChooser();
        }

        _filename = null;

        if (model != null && model.getCorpus() == null) {
            dialog.resetChoosableFileFilters();
            dialog.setAcceptAllFileFilterUsed(false);
            dialog.setFileFilter(new ZIPFilter());
            dialog.setMultiSelectionEnabled(false);
            dialog.setSelectedFile(new File(""));
            dialog.setDialogTitle("Open Corpus");

            PropertiesManager m = PropertiesManager.getInstance(TopicConstants.PROPFILENAME);
            dialog.setCurrentDirectory(new File(m.getProperty("ZIP.DIR")));

            int result = dialog.showOpenDialog(parent);
            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                String filename = dialog.getSelectedFile().getAbsolutePath();
                m.setProperty("ZIP.DIR", dialog.getSelectedFile().getParent());

                String inputValue = (String) JOptionPane.showInputDialog(null,
                        "Define the number of grams:", "Defining the Number of Grams",
                        JOptionPane.QUESTION_MESSAGE, null, null, "1");

                if (inputValue == null) {
                    return false;
                }

                Corpus corpus = CorpusFactory.getInstance(filename, Integer.parseInt(inputValue));

                //checking how many documents on the graph are in the corpus
                int count = 0;
                int valid = 0;
                for (AbstractInstance ai : model.getInstances()) {
                    int id = ai.getId();
                    if (corpus.getIds().contains(id)) {
                        count++;
                    }

                    valid++;
                }

                if (count != valid) {
                    String message = "Only " + count + " corresponding files were found in the corpus!\n" +
                            "The projection has " + valid + " files. Would like to proceed?";
                    int answer = JOptionPane.showOptionDialog(dialog, message, "Openning Warning",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

                    if (answer == JOptionPane.NO_OPTION) {
                        return false;
                    }
                }

                model.setCorpus(corpus);

                return true;
            } else {
                return false;
            }
        }
        return true;
    }

}
