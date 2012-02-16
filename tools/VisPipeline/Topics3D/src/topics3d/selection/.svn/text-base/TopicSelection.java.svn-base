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

package topics3d.selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import visualizationbasics.view.selection.AbstractSelection;
import topics3d.AbstractTopic3DCreator;
import topics3d.TopicFactory;
import topics3d.Topic3D;
import topics3d.model.TopicProjection3DModel;
import topics3d.util.OpenDialog;
import topics3d.view.TopicProjection3DFrame;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class TopicSelection extends AbstractSelection {

    public TopicSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (selinst.size() > 0) {
            try {
                TopicProjection3DModel model = (TopicProjection3DModel) viewer.getModel();

                if (OpenDialog.checkCorpus(model, viewer)) {
                    AbstractTopic3DCreator creator = TopicFactory.getInstance(model, topictype);
                    Topic3D topic = creator.createTopic(TopicProjection3DModel.convertInstances(selinst));
                    model.addTopic(topic);

                    ((TopicProjection3DFrame) viewer).updateScalars(creator.getScalar());
                }
            } catch (IOException ex) {
                Logger.getLogger(TopicSelection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Information16.gif"));
    }

    @Override
    public String toString() {
        return "Create topic";
    }

    private TopicFactory.TopicType topictype = TopicFactory.TopicType.COVARIANCE;
}
