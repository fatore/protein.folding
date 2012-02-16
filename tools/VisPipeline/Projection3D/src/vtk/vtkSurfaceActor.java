/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vtk;

import java.awt.Color;
import java.util.ArrayList;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Administrador
 */
public class vtkSurfaceActor extends vtkActor {

    public vtkSurfaceActor(String name) {
        super();
        this.name = name;
        this.instances = null;
    }

    public ArrayList<AbstractInstance> getInstances() {
        return instances;
    }

    public void setInstances(ArrayList<AbstractInstance> insts) {
        this.instances = insts;
    }

    public void setColor(Color c) {
        this.GetProperty().SetColor(c.getRed()/255., c.getGreen()/255., c.getBlue()/255.);
    }

    public Color getColor() {
        double color[] = this.GetProperty().GetColor();
        return new Color((float)color[0], (float)color[1], (float)color[2]);
    }

    @Override
    public String toString(){
        return name;
    }

    private ArrayList<AbstractInstance> instances;
    private String name;
}
