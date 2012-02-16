/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.pipeline;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import vispipeline.component.ComponentProxy;
import vispipeline.component.method.InputMethod;
import vispipeline.component.method.MultipleInputMethod;
import vispipeline.component.method.OutputMethod;
import vispipeline.component.method.UniqueInputMethod;
import vispipeline.component.parameter.InputParameter;
import vispipeline.component.parameter.MultipleInputParameter;
import vispipeline.component.parameter.UniqueInputParameter;
import vispipeline.component.parameter.OutputParameter;
import vispipeline.view.VisPipeline;
import vispipeline.view.VisPipeline.PipelineDrawPanel;
import vispipeline.view.WizardDialog;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.view.MessageDialog;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ExecuteEngine {

    public ExecuteEngine(PipelineDrawPanel drawpanel, Pipeline pipeline) {
        this.drawpanel = drawpanel;
        this.pipeline = pipeline;
        this.threadpoolsize = 5;
    }

    public void start(boolean wizard) throws IOException {
        executor = Executors.newFixedThreadPool(threadpoolsize);

        //reset the pipeline
        pipeline.reset();
        pipeline.setExecuteWizard(wizard);
        drawpanel.repaint();

        //find the components which does not have input parameters
        ArrayList<ComponentProxy> noInputComponents = getNoInputComponents();

        for (int i = 0; i < noInputComponents.size(); i++) {
            //and execute it
            Executor ex = new Executor(noInputComponents.get(i));
            ex.execute();
        }
    }

    public void stop() {
        if (executor != null && !executor.isTerminated()) {
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private ArrayList<ComponentProxy> getNoInputComponents() {
        ArrayList<ComponentProxy> noninput = new ArrayList<ComponentProxy>();

        ArrayList<ComponentProxy> components = pipeline.getComponents();
        for (int i = 0; i < components.size(); i++) {
            ArrayList<InputMethod> inmeth = components.get(i).getInput();
            boolean empty = true;

            for (int j = 0; j < inmeth.size(); j++) {
                if (inmeth.get(j).getParameters().size() > 0) {
                    empty = false;
                    break;
                }
            }

            if (empty) {
                noninput.add(components.get(i));
            }
        }

        return noninput;
    }

    public int getThreadPoolSize() {
        return threadpoolsize;
    }

    public void setThreadPoolSize(int threadpoolsize) {
        this.threadpoolsize = threadpoolsize;
    }

    public class Executor implements Runnable {

        public Executor(ComponentProxy comp) {
            this.comp = comp;
        }

        @Override
        public void run() {
            try {
                comp.setState(ComponentProxy.State.EXECUTING);

                if (drawpanel != null) {
                    drawpanel.repaint();
                }

                //get all input methods
                ArrayList<InputMethod> inputs = comp.getInput();

                for (InputMethod in : inputs) {
                    //call all input methods that are filled
                    if (in.isFilled()) {
                        if (in instanceof UniqueInputMethod) {
                            //creating the parameters list
                            Object[] param = new Object[in.getParameters().size()];
                            for (int i = 0; i < in.getParameters().size(); i++) {
                                param[i] = ((UniqueInputParameter) in.getParameters().get(i)).getObject();
                            }

                            //calling the method
                            in.getMethod().invoke(comp.getComponentToExecute(), param);
                        } else if (in instanceof MultipleInputMethod) {
                            //get the objects associated to the only parameter of
                            //the MultipleInputMethod
                            ArrayList<Object> objs =
                                    ((MultipleInputParameter) in.getParameters().get(0)).getObjects();

                            //for each object
                            for (Object o : objs) {
                                //call the method
                                in.getMethod().invoke(comp.getComponentToExecute(), o);
                            }
                        }
                    }
                }

                int result = WizardDialog.SUCESS;

                if (pipeline.isExecuteWizard()) {
                    AbstractParametersView parameditor = comp.getParametersEditor();

                    if (parameditor != null) {
                        parameditor.reset();

                        WizardDialog dialog = WizardDialog.getInstance(drawpanel.getVisFramework(),
                                parameditor);
                        result = dialog.display();
                    }
                }

                if (result == WizardDialog.SUCESS) {
                    md = MessageDialog.show(drawpanel.getVisFramework(),
                            "Executing: \"" + comp.toString().trim() + "\" component...");

                    try {
                        long start = System.currentTimeMillis();
                        comp.getComponentToExecute().execute();
                        long finish = System.currentTimeMillis();

                        VisPipeline.ConsoleOutput.append("[EXECUTED]: " + comp.toString() +
                                " - execution time " + ((finish - start) / 1000.0f) + "s",
                                Color.BLACK, false, false, true);

                        if (comp.howToCite().trim().length() > 0) {
                            VisPipeline.ConsoleOutput.append("[" + comp.toString() + "] >> " +
                                    comp.howToCite(), Color.BLUE, true, false, false);
                        }

                        if (drawpanel != null) {
                            drawpanel.repaint();
                        }

                        //define that all output methods are available
                        ArrayList<OutputMethod> outputs = comp.getOutput();
                        for (int i = 0; i < outputs.size(); i++) {
                            available(outputs.get(i).getOutputParameter());
                        }

                        comp.setState(ComponentProxy.State.EXECUTED);
                    } catch (IOException ex) {
                        exception = ex;
                    }
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ExecuteEngine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ExecuteEngine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ExecuteEngine.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (md != null) {
                    md.close();
                }
            }
        }

        private void available(OutputParameter output) throws InvocationTargetException,
                IllegalArgumentException, IllegalAccessException, IOException {
            //get the value of this output
            Method method = output.getParentMethod().getMethod();
            Object result = method.invoke(output.getParentMethod().getParentComponent().getComponentToExecute());

            //pass this value to all input parameters that are interested in
            for (int i = 0; i < output.getInputParameters().size(); i++) {
                InputParameter inparam = output.getInputParameters().get(i);

                if (inparam instanceof UniqueInputParameter) {
                    ((UniqueInputParameter) inparam).setObject(result);
                } else if (inparam instanceof MultipleInputParameter) {
                    ((MultipleInputParameter) inparam).addObject(result);
                }

                //call the input method associated to it
                ComponentProxy cproxy = inparam.getParentMethod().getParentComponent();

                //check if the component can be executed, if it is true execute it
                if (canExecute(cproxy)) {
                    Executor ex = new Executor(cproxy);
                    ex.execute();
                }
            }
        }

        private boolean canExecute(ComponentProxy comp) {
            ArrayList<InputMethod> inputs = comp.getInput();

            for (InputMethod in : inputs) {
                ArrayList<InputParameter> parameters = in.getParameters();

                for (InputParameter param : parameters) {
                    if (param instanceof UniqueInputParameter) {
                        if (((UniqueInputParameter) param).getOutputParameter() != null &&
                                !in.isFilled()) {
                            return false;
                        }
                    } else if (param instanceof MultipleInputParameter) {
                        if (((MultipleInputParameter) param).getOutputParameters().size() > 0 &&
                                !in.isFilled()) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        public void execute() throws IOException {
            if (!executor.isShutdown()) {
                executor.execute(this);
            }

            if (exception != null) {
                throw exception;
            }
        }

        private IOException exception;
        private MessageDialog md;
        private ComponentProxy comp;
    }

    private Pipeline pipeline;
    private PipelineDrawPanel drawpanel;
    private ExecutorService executor;
    private int threadpoolsize;
}
