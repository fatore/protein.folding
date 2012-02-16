
package vispipeline.component.method;

import java.awt.Color;
import java.awt.Graphics2D;
import vispipeline.component.parameter.Parameter;
import vispipeline.component.*;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Method;
import vispipeline.component.parameter.OutputParameter;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class OutputMethod extends ComponentMethod {

    public OutputMethod(ComponentProxy comp, Method method) throws IOException {
        super(comp, method);

        if (method.getParameterTypes().length == 0) {
            Class type = method.getReturnType();
            parameter = new OutputParameter(this, type);

            //define the size of the component
            this.size = Parameter.SIZE + 2 * Parameter.SPACE;
        } else {
            throw new IOException(comp.toString() + ": an output method cannot " +
                    "have parameters.");
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(position.x, position.y, size,
                Parameter.SIZE + (Parameter.SIZE / 2), Parameter.SIZE, Parameter.SIZE);

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(position.x, position.y, size,
                Parameter.SIZE + (Parameter.SIZE / 2), Parameter.SIZE, Parameter.SIZE);

        parameter.draw(g2);
    }

    @Override
    public Parameter isInside(Point pos) {
        if (parameter.isInside(pos)) {
            return parameter;
        }

        return null;
    }

    @Override
    public void setPosition(Point position) {
        if (position != null) {
            this.position = position;

            //define the position of each parameter
            parameter.setPosition(new Point(Parameter.SPACE + position.x,
                    position.y + Parameter.SIZE));
        }
    }

    public OutputParameter getOutputParameter() {
        return parameter;
    }

    private OutputParameter parameter;
}
