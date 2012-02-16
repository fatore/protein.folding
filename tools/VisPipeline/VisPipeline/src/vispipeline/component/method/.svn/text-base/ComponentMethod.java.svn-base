/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.method;

import vispipeline.component.parameter.Parameter;
import vispipeline.component.*;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class ComponentMethod implements Serializable {

    public ComponentMethod(ComponentProxy comp, Method method) {
        this.comp = comp;
        this.method = method;
        this.methodname = method.toString();
        this.position = new Point();
    }

    public abstract void draw(Graphics2D g2);

    public int getSize() {
        return this.size;
    }

    public Method getMethod() {
        if (method == null) {
            Method[] methods = comp.getComponentToExecute().getClass().getMethods();

            for (int i = 0; i < methods.length; i++) {
                if (methods[i].toString().equals(methodname)) {
                    method = methods[i];
                    break;
                }
            }
        }

        return method;
    }

    public abstract Parameter isInside(Point pos);

    public ComponentProxy getParentComponent() {
        return comp;
    }

    public abstract void setPosition(Point position);

    public static final long serialVersionUID = 1L;
    protected int size;
    protected Point position;
    protected ComponentProxy comp;
    private String methodname;
    private transient Method method;
}
