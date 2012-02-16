/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.pipeline;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import vispipeline.component.ComponentProxy;
import vispipeline.component.method.InputMethod;
import vispipeline.component.method.OutputMethod;
import vispipeline.component.method.UniqueInputMethod;
import vispipeline.component.parameter.InputParameter;
import vispipeline.component.parameter.MultipleInputParameter;
import vispipeline.component.parameter.UniqueInputParameter;
import vispipeline.component.parameter.OutputParameter;
import vispipeline.component.parameter.Parameter;

/**
 *
 * @author Fernando Veira Paulovich
 */
public class Pipeline implements Serializable {

    public Pipeline() {
        this.components = new ArrayList<ComponentProxy>();
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Sans Serif", Font.PLAIN, 10));

        for (int i = components.size() - 1; i >= 0; i--) {
            components.get(i).draw(g2);
        }
    }

    public void addComponent(ComponentProxy compproxy) {
        if (compproxy != null) {
            components.add(compproxy);
            compproxy.setPipeline(this);
        }
    }

    public boolean removeComponent(ComponentProxy compproxy) {
        if (compproxy != null) {
            compproxy.setPipeline(null);
        }

        return components.remove(compproxy);
    }

    public ComponentProxy isInsideComponent(Point pos) {
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).isInside(pos)) {
                return components.get(i);
            }
        }

        return null;
    }

    public Parameter isInsideParameter(Point pos) {
        Parameter param = null;

        for (int i = 0; i < components.size(); i++) {
            for (int j = 0; j < components.get(i).getInput().size(); j++) {
                param = components.get(i).getInput().get(j).isInside(pos);

                if (param != null) {
                    return param;
                }
            }

            for (int j = 0; j < components.get(i).getOutput().size(); j++) {
                param = components.get(i).getOutput().get(j).isInside(pos);

                if (param != null) {
                    return param;
                }
            }
        }

        return param;
    }

    public boolean canBeLinked(Parameter param1, Parameter param2) {
        InputParameter input = (InputParameter) ((param1 instanceof UniqueInputParameter)
                ? param1 : (param2 instanceof UniqueInputParameter)
                ? param2 : (param1 instanceof MultipleInputParameter)
                ? param1 : (param2 instanceof MultipleInputParameter) ? param2 : null);
        Parameter output = (OutputParameter) ((param1 instanceof OutputParameter)
                ? param1 : (param2 instanceof OutputParameter) ? param2 : null);

        //the parameters functions should be different, one should be
        //InputParameter and the other one OutputParameter
        if (input == null || output == null) {
            return false;
        }

        //the parameters types should be the same
        if (param1.getType() != param2.getType()) {
            return false;
        }

        //the param2 and param1 of the same ComponentProxy cannot be linked
        if (param1.getParentMethod().getParentComponent() ==
                param2.getParentMethod().getParentComponent()) {
            return false;
        }

        //one ouput can only be linked one time to a multiple input
        if (input instanceof MultipleInputParameter) {
            if (((MultipleInputParameter) input).getOutputParameters().contains(output)) {
                return false;
            }
        }

        //an unique input can only be linked one time
        if (input instanceof UniqueInputParameter) {
            if (((UniqueInputParameter) input).getOutputParameter() != null) {
                return false;
            }
        }

        //only one UniqueInputMethod can be called in one ComponentProxy at once
        if (input instanceof UniqueInputParameter) {
            ComponentProxy comp = input.getParentMethod().getParentComponent();
            ArrayList<InputMethod> inputs = comp.getInput();

            for (InputMethod in : inputs) {
                if (in instanceof UniqueInputMethod && in != input.getParentMethod()) {
                    if (((UniqueInputParameter) input).getOutputParameter() != null) {
                        return false;
                    }
                }
            }
        }

        //getting all links between input and output parameters (the first
        //element is the input and the second the output)
        ArrayList<Parameter[]> links = new ArrayList<Parameter[]>();

        for (int i = 0; i < components.size(); i++) {
            ArrayList<OutputMethod> outputs = components.get(i).getOutput();

            for (int j = 0; j < outputs.size(); j++) {
                ArrayList<InputParameter> inparam = outputs.get(j).
                        getOutputParameter().getInputParameters();

                //create the links, pairs of InputParamater and OutputParameter
                for (int n = 0; n < inparam.size(); n++) {
                    Parameter[] link = new Parameter[2];
                    link[0] = inparam.get(n);
                    link[1] = outputs.get(j).getOutputParameter();
                    links.add(link);
                }
            }
        }

        //we should avoid cyclic calling
        DirectedGraph<ComponentProxy, DefaultEdge> g =
                new DefaultDirectedGraph<ComponentProxy, DefaultEdge>(DefaultEdge.class);
        for (int i = 0; i < components.size(); i++) {
            g.addVertex(components.get(i));
        }

        for (int i = 0; i < links.size(); i++) {
            Parameter[] par = links.get(i);

            if (par[0] != null && par[1] != null) {
                g.addEdge(par[1].getParentMethod().getParentComponent(),
                        par[0].getParentMethod().getParentComponent());
            }
        }

        g.addEdge(output.getParentMethod().getParentComponent(),
                input.getParentMethod().getParentComponent());

        CycleDetector<ComponentProxy, DefaultEdge> detector =
                new CycleDetector<ComponentProxy, DefaultEdge>(g);
        if (detector.detectCycles()) {
            return false;
        }

        return true;
    }

    public void save(String filename) throws IOException {
        reset();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(this);
        out.flush();
        out.close();
    }

    public static Pipeline load(String filename) throws IOException {
        Pipeline pipeline = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            pipeline = (Pipeline) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            throw new IOException("Error reading the file: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Error reading the file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new IOException("Error reading the file. Class not found: " + e.getMessage());
        }

        return pipeline;
    }

    public ArrayList<ComponentProxy> getComponents() {
        return components;
    }

    public void deleteSelectedComponents() {
        //removing the components
        for (int i = components.size() - 1; i >= 0; i--) {
            if (components.get(i).isSelected()) {
                ComponentProxy removed = components.remove(i);
                removed.setPipeline(null);

                //removing the output links
                ArrayList<OutputMethod> output = removed.getOutput();
                for (int j = 0; j < output.size(); j++) {
                    OutputParameter out = output.get(j).getOutputParameter();
                    out.removeAllInputParameter();
                }

                //removing the input links
                ArrayList<InputMethod> input = removed.getInput();
                for (int j = 0; j < input.size(); j++) {
                    InputMethod inmeth = input.get(j);

                    for (int k = 0; k < inmeth.getParameters().size(); k++) {
                        InputParameter in = (InputParameter) inmeth.getParameters().get(k);

                        if (in instanceof UniqueInputParameter) {
                            if (((UniqueInputParameter) in).getOutputParameter() != null) {
                                ((UniqueInputParameter) in).getOutputParameter().removeInputParameter(in);
                            }
                        } else if (in instanceof MultipleInputParameter) {
                            ArrayList<OutputParameter> outparams = ((MultipleInputParameter) in).getOutputParameters();

                            for (OutputParameter op : outparams) {
                                op.removeInputParameter(in);
                            }
                        }
                    }
                }
            }
        }
    }

    public void reset() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).reset();
        }
    }

    public boolean isExecuteWizard() {
        return executewizard;
    }

    public void setExecuteWizard(boolean executewizard) {
        this.executewizard = executewizard;
    }

    public static final long serialVersionUID = 1L;
    private ArrayList<ComponentProxy> components;
    private boolean executewizard;
}
