/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import vispipeline.component.method.UniqueInputMethod;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class UniqueInputParameter extends InputParameter {

    public UniqueInputParameter(UniqueInputMethod method, Class type, String name) {
        super(method, type, name);
    }

    public boolean isFilled() {
        return (object != null);
    }

    public void reset() {
        object = null;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public OutputParameter getOutputParameter() {
        return output;
    }

    public void setOutputParameter(OutputParameter output) {
        this.output = output;
    }

    private transient Object object;
    private OutputParameter output;
}
