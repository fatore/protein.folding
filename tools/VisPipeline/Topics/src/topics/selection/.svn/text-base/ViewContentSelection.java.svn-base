/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics.selection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import visualizationbasics.view.selection.AbstractSelection;
import topics.model.TopicProjectionModel;
import topics.util.OpenDialog;
import topics.view.MultipleFileView;
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
            TopicProjectionModel model = (TopicProjectionModel) viewer.getModel();

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
