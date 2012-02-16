/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipelineexamples;

import java.io.IOException;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Example.Techniques.Aritmethic",
name = "Sum",
description = "This is a sum component (A + B).")
public class SumComponent implements AbstractComponent {

    public void execute() throws IOException {
        sum = a + b;
    }

    public void input(@Param(name = "A") Integer a, @Param(name = "B") Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer output() {
        return sum;
    }

    public AbstractParametersView getParametersEditor() {
        return null;
    }

    public void reset() {
    }

    public static final long serialVersionUID = 1L;
    private transient int sum;
    private transient int a;
    private transient int b;
}
