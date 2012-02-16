/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import vispipeline.component.method.InputMethod;
import vispipeline.component.method.MultipleInputMethod;
import vispipeline.component.method.OutputMethod;
import vispipeline.component.method.UniqueInputMethod;
import vispipeline.component.parameter.Parameter;
import vispipeline.pipeline.Pipeline;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ComponentProxy implements Serializable {

    public enum State {

        TOEXECUTE, EXECUTING, EXECUTED
    }

    public ComponentProxy(AbstractComponent component) throws IOException {
        this.component = component;
        this.width = 0;
        this.height = (int) (Parameter.SIZE * 4);
        this.position = new Point();
        this.selected = false;
        this.state = State.TOEXECUTE;

        this.input = new ArrayList<InputMethod>();
        this.output = new ArrayList<OutputMethod>();

        Method[] methods = getComponentToExecute().getClass().getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(ComponentProxy.INPUT)) {
                this.input.add(new UniqueInputMethod(this, methods[i]));
            } else if (methods[i].getName().equals(ComponentProxy.ATTACH)) {
                this.input.add(new MultipleInputMethod(this, methods[i]));
            } else if (methods[i].getName().startsWith(ComponentProxy.OUTPUT)) {
                this.output.add(new OutputMethod(this, methods[i]));
            }
        }


        if (this.input.size() > 0 || this.output.size() > 0) {
            //setting the width of the component
            int inputsize = Parameter.SPACE;
            for (int i = 0; i < input.size(); i++) {
                inputsize += Parameter.SPACE + this.input.get(i).getSize();
            }

            this.width = Math.max(inputsize, this.width);

            //setting the width of the component
            int outputsize = Parameter.SPACE;
            for (int i = 0; i < this.output.size(); i++) {
                outputsize += Parameter.SPACE + this.output.get(i).getSize();
            }

            this.width = Math.max(outputsize, this.width);

            setPosition(position);
        } else {
            throw new IOException(this.toString() + ": a component should have " +
                    "at least one input/ouput method.");
        }
    }

    public AbstractParametersView getParametersEditor() {
        return component.getParametersEditor();
    }

    @Override
    public String toString() {
        return component.getClass().getAnnotation(VisComponent.class).name();
    }

    public String description() {
        return component.getClass().getAnnotation(VisComponent.class).description();
    }

    public String howToCite() {
        return component.getClass().getAnnotation(VisComponent.class).howtocite();
    }

    public void draw(Graphics2D g2) {
        //if the name of the component is larger than the its width, increase it
        FontMetrics metrics = g2.getFontMetrics();
        width = Math.max(width, metrics.stringWidth(toString()) + Parameter.SPACE * 2);

        //draw the component
        g2.setColor(new Color(0.8f, 0.8f, 1.0f));
        g2.fillRoundRect(position.x, position.y, width, height, Parameter.SIZE, Parameter.SIZE);

        if (selected) {
            g2.setStroke(new BasicStroke(3.0f));
            g2.setColor(Color.RED);
            g2.drawRoundRect(position.x, position.y, width, height, Parameter.SIZE, Parameter.SIZE);
            g2.setStroke(new BasicStroke(1.0f));
        } else {
            if (state == State.EXECUTED) {
                g2.setStroke(new BasicStroke(3.0f));
                g2.setColor(Color.BLUE);
                g2.drawRoundRect(position.x, position.y, width, height, Parameter.SIZE, Parameter.SIZE);
                g2.setStroke(new BasicStroke(1.0f));
            } else if (state == State.EXECUTING) {
                g2.setStroke(new BasicStroke(3.0f));
                g2.setColor(Color.YELLOW);
                g2.drawRoundRect(position.x, position.y, width, height, Parameter.SIZE, Parameter.SIZE);
                g2.setStroke(new BasicStroke(1.0f));
            } else {
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(position.x, position.y, width, height, Parameter.SIZE, Parameter.SIZE);
            }
        }

        //draw the component name
        g2.setColor(Color.BLACK);
        g2.drawString(toString(), position.x + Parameter.SPACE,
                position.y + (Parameter.SIZE * 2.5f));

        //draw the inputs
        if (getInput() != null) {
            for (int i = getInput().size() - 1; i >= 0; i--) {
                getInput().get(i).draw(g2);
            }
        }

        //draw the outputs
        if (getOutput() != null) {
            for (int i = getOutput().size() - 1; i >= 0; i--) {
                getOutput().get(i).draw(g2);
            }
        }
    }

    public boolean isInside(Point pos) {
        if ((pos.x >= position.x && pos.y >= position.y) &&
                (pos.x <= position.x + width && pos.y <= position.y + height)) {
            return true;
        }

        return false;
    }

    public boolean isInside(Point pos1, Point pos2) {
        int x_aux = Math.min(pos1.x, pos2.x);
        int width_aux = Math.abs(pos2.x - pos1.x);

        int y_aux = Math.min(pos1.y, pos2.y);
        int height_aux = Math.abs(pos2.y - pos1.y);

        if (((position.x >= x_aux) && (position.x - x_aux < width_aux)) &&
                ((position.y >= y_aux) && (position.y - y_aux < height_aux))) {
            return true;
        }

        return false;
    }

    public void setPosition(Point position) {
        if (position != null) {
            this.position = position;

            //define the position of the inputs
            if (getInput() != null) {
                int x = Parameter.SPACE + position.x;
                for (int i = 0; i < getInput().size(); i++) {
                    getInput().get(i).setPosition(new Point(x, position.y - Parameter.SIZE / 2));
                    x = x + getInput().get(i).getSize() + Parameter.SPACE;
                }
            }

            //define the position of the output
            if (getOutput() != null) {
                int x = Parameter.SPACE + position.x;
                for (int i = 0; i < getOutput().size(); i++) {
                    getOutput().get(i).setPosition(new Point(x, position.y + Parameter.SIZE * 3));
                    x = x + getOutput().get(i).getSize() + Parameter.SPACE;
                }
            }
        }
    }

    public ArrayList<OutputMethod> getOutput() {
        return output;
    }

    public ArrayList<InputMethod> getInput() {
        return input;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public AbstractComponent getComponentToExecute() {
        return component;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void reset() {
        component.reset();
        state = State.TOEXECUTE;

        for (InputMethod im : input) {
            im.reset();
        }
    }

    public static final long serialVersionUID = 1L;
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String ATTACH = "attach";
    private AbstractComponent component;
    private ArrayList<InputMethod> input;
    private ArrayList<OutputMethod> output;
    private int width;
    private int height;
    private Point position;
    private boolean selected;
    private Pipeline pipeline;
    private State state;
}
