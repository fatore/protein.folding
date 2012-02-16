/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.view;

import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Output",
name = "Value output",
description = "Display in a window a given velue.")
public class OutputComp implements AbstractComponent {

    public void execute() {
        if (value != null) {
            Output.display(title, value.toString());
        }
    }

    public void input(@Param(name = "value") Integer value) {
        this.value = value;
    }

    public void input(@Param(name = "value") Double value) {
        this.value = value;
    }

    public void input(@Param(name = "value") Float value) {
        this.value = value;
    }

    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new OutputParamView(this);
        }

        return paramview;
    }

    public void reset() {
        value = null;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public static final long serialVersionUID = 1L;
    private String title = "";
    private transient Object value;   
    private transient OutputParamView paramview;
}
