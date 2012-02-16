/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipelineexamples;

import javax.swing.JOptionPane;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Example.Output",
name = "Output",
description = "This is an output component.")
public class OutputComponent implements AbstractComponent {

    public void execute() {
        JOptionPane.showMessageDialog(null, "result: " + Integer.toString(a));
    }

    public void input(@Param(name = "A") Integer a) {
        this.a = a;
    }

    public AbstractParametersView getParametersEditor() {
        return null;
    }

    public void reset() {
    }

    public static final long serialVersionUID = 1L;
    private transient int a;
}
