/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import topics.view.MultipleFileView;
import visualizationbasics.view.selection.AbstractSelection;
import topics3d.model.TopicProjection3DModel;
import topics3d.util.OpenDialog;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ViewContentSelection extends AbstractSelection {

    public ViewContentSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        try {
            TopicProjection3DModel model = (TopicProjection3DModel) viewer.getModel();

            if (OpenDialog.checkCorpus(model, viewer)) {
                MultipleFileView.getInstance(viewer).display(selinst, model.getCorpus());
            }
        } catch (IOException ex) {
            Logger.getLogger(ViewContentSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Copy16.gif"));
    }

    @Override
    public String toString() {
        return "View Content";
    }

}
