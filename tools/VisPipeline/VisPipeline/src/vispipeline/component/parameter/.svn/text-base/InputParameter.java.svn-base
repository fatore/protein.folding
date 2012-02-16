/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import vispipeline.component.method.InputMethod;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class InputParameter extends Parameter {

    public InputParameter(InputMethod method, Class type, String name) {
        super(method, type);
        this.name = name;
    }

    public abstract boolean isFilled();

    public abstract void reset();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " : " + type.getCanonicalName();
    }

    private String name;
}
