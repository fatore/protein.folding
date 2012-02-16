/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipelineexamples;

import javax.swing.JOptionPane;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Example.Input",
name = "Input",
description = "This is an input component.")
public class InputComponent implements AbstractComponent {

    public void execute() {
        String result = JOptionPane.showInputDialog("input");
        input = Integer.parseInt(result);
    }

    public Integer output() {
        return input;
    }

    public AbstractParametersView getParametersEditor() {
        return null;
    }

    public void reset() {
    }

    public static final long serialVersionUID = 1L;
    private transient int input;
}
