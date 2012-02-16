/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import java.util.ArrayList;
import vispipeline.component.method.MultipleInputMethod;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class MultipleInputParameter extends InputParameter {

    public MultipleInputParameter(MultipleInputMethod method, Class type, String name) {
        super(method, type, name);

        this.outputs = new ArrayList<OutputParameter>();
    }

    public ArrayList<Object> getObjects() {
        if (objects == null) {
            objects = new ArrayList<Object>();
        }

        return objects;
    }

    public void addObject(Object object) {
        if (objects == null) {
            objects = new ArrayList<Object>();
        }

        objects.add(object);
    }

    public boolean isFilled() {
        return (objects != null && objects.size() == outputs.size());
    }

    public void reset() {
        objects = new ArrayList<Object>();
    }

    public ArrayList<OutputParameter> getOutputParameters() {       
        return outputs;
    }

    public void addOutputParameter(OutputParameter output) {
        outputs.add(output);
    }

    public void removeOutputParameter(OutputParameter output) {
        outputs.remove(output);
    }

    private transient ArrayList<Object> objects;
    private ArrayList<OutputParameter> outputs;
}
