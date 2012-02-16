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
name = "Multiply",
        description="This is a multiply component (A * B).")
public class MultiplyComponent implements AbstractComponent {

    public void execute() throws IOException {
        mult = a * b;
    }

    public void input(@Param(name = "A") Integer a, @Param(name = "B") Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer output() {
        return mult;
    }

    public AbstractParametersView getParametersEditor() {
        return null;
    }


    public void reset() {
    }

    public static final long serialVersionUID = 1L;
    private transient int mult;
    private transient int a;
    private transient int b;
}
