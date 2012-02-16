/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component.parameter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import vispipeline.component.method.OutputMethod;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class OutputParameter extends Parameter {

    public OutputParameter(OutputMethod method, Class type) {
        super(method, type);

        this.inputparam = new ArrayList<InputParameter>();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        //draw the link between the input parameters
        for (int i = 0; i < inputparam.size(); i++) {
            InputParameter input = inputparam.get(i);

            g2.setStroke(new BasicStroke(3.0f));
            g2.setColor(Color.RED);

            float p1x = input.getPosition().x + (Parameter.SIZE / 2);
            float p1y = input.getPosition().y + (Parameter.SIZE / 2);
            float p2x = this.getPosition().x + (Parameter.SIZE / 2);
            float p2y = this.getPosition().y + (Parameter.SIZE / 2);

            //draw a spline curve
            CubicCurve2D curve = new CubicCurve2D.Float(p1x, p1y,
                    p1x, p1y + (p2y - p1y) / 2,
                    p2x, p1y + (p2y - p1y) / 2,
                    p2x, p2y);
            g2.draw(curve);

            g2.setStroke(new BasicStroke(1.0f));
        }
    }

    public void addInputParameter(InputParameter input) {
        if (input != null && !inputparam.contains(input)) {
            inputparam.add(input);

            if (input instanceof UniqueInputParameter) {
                ((UniqueInputParameter) input).setOutputParameter(this);
            } else if (input instanceof MultipleInputParameter) {
                ((MultipleInputParameter) input).addOutputParameter(this);
            }
        }
    }

    public void removeInputParameter(InputParameter input) {
        if (input != null) {
            inputparam.remove(input);

            if (input instanceof UniqueInputParameter) {
                ((UniqueInputParameter) input).setOutputParameter(null);
            } else if (input instanceof MultipleInputParameter) {
                ((MultipleInputParameter) input).removeOutputParameter(this);
            }
        }
    }

    public void removeAllInputParameter() {
        for (int i = 0; i < inputparam.size(); i++) {
            if (inputparam.get(i) instanceof UniqueInputParameter) {
                ((UniqueInputParameter) inputparam.get(i)).setOutputParameter(null);
            } else if (inputparam.get(i) instanceof MultipleInputParameter) {
                ((MultipleInputParameter) inputparam.get(i)).removeOutputParameter(this);
            }
        }

        inputparam.clear();
    }

    public ArrayList<InputParameter> getInputParameters() {
        return inputparam;
    }

    @Override
    public String toString() {
        return type.getCanonicalName();
    }

    private ArrayList<InputParameter> inputparam;
}
