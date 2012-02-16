
package vispipeline.component.method;

import vispipeline.component.parameter.Parameter;
import vispipeline.component.*;
import java.awt.Point;
import java.lang.reflect.Method;
import java.util.ArrayList;
import vispipeline.component.parameter.InputParameter;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class InputMethod extends ComponentMethod {

    public InputMethod(ComponentProxy comp, Method method) {
        super(comp, method);

        this.parameters = new ArrayList<InputParameter>();
    }

    @Override
    public Parameter isInside(Point pos) {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).isInside(pos)) {
                return parameters.get(i);
            }
        }

        return null;
    }

    @Override
    public void setPosition(Point position) {
        if (position != null) {
            this.position = position;

            //define the position of each parameter
            for (int i = 0; i < parameters.size(); i++) {
                int x = ((i + 1) * Parameter.SPACE + i * Parameter.SIZE) + position.x;
                int y = position.y - Parameter.SIZE / 2;
                parameters.get(i).setPosition(new Point(x, y));
            }
        }
    }

    public ArrayList<InputParameter> getParameters() {
        return parameters;
    }

    public void reset() {
        for (InputParameter ip : parameters) {
            ip.reset();
        }
    }

    public boolean isFilled() {
        for (InputParameter param : parameters) {
            if (!param.isFilled()) {
                return false;
            }
        }

        return true;
    }

    protected ArrayList<InputParameter> parameters;
}
