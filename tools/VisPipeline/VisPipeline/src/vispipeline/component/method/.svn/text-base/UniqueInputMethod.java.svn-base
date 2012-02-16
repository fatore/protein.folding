/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.method;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import vispipeline.component.ComponentProxy;
import vispipeline.component.parameter.Parameter;
import vispipeline.component.parameter.UniqueInputParameter;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class UniqueInputMethod extends InputMethod {

    public UniqueInputMethod(ComponentProxy comp, Method method) throws IOException {
        super(comp, method);

        if (method.getParameterTypes().length > 0) {
            //getting the method parameters
            Class[] paramTypes = method.getParameterTypes();
            Annotation[][] annotations = method.getParameterAnnotations();

            for (int i = 0; i < paramTypes.length; i++) {
                String name = "";

                //getting the parameters name, if it is provided by an annotation
                for (int j = 0; j < annotations[i].length; j++) {
                    if (annotations[i][j] instanceof vispipelinebasics.annotations.Param) {
                        name = ((vispipelinebasics.annotations.Param) annotations[i][j]).name();
                        break;
                    }
                }

                this.parameters.add(new UniqueInputParameter(this, paramTypes[i], name));
            }

            //define the size of the component
            this.size = parameters.size() * Parameter.SIZE + (parameters.size() + 1) * Parameter.SPACE;
        } else {
            throw new IOException(comp.toString() + ": an unique input method " +
                    "should have at least one input parameter.");
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (parameters.size() > 0) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRoundRect(position.x, position.y, size,
                    Parameter.SIZE + (Parameter.SIZE / 2), Parameter.SIZE, Parameter.SIZE);

            g2.setColor(Color.BLACK);
            g2.drawRoundRect(position.x, position.y, size,
                    Parameter.SIZE + (Parameter.SIZE / 2), Parameter.SIZE, Parameter.SIZE);

            for (int i = parameters.size() - 1; i >= 0; i--) {
                parameters.get(i).draw(g2);
            }
        }
    }

}
