/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.gl;

/**
 *
 * @author Fatore
 */
public class Vertex {

    private int id;
    private float x;
    private float y;
    private float energy;

    private void divideByf(float f) {
        this.x = this.x / f;
        this.y = this.y / f;
        this.energy = this.energy / f;
    }

    //normaliza o vetor no intervalo [-1,1]
    public void normalize(float[][] exValues) {
        x = ((x - exValues[0][0]) / (exValues[1][0] - exValues[0][0]));
        x = (x - 0.5f) * 2;

        y = ((y - exValues[0][1]) / (exValues[1][1] - exValues[0][1]));
        y = (y - 0.5f) * 2;

        energy = ((energy - exValues[0][2]) / (exValues[1][2] - exValues[0][2]));
        energy = (energy - 0.5f) * 2;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getEnergy() {
        return energy;
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static void main(String args[]) {
    }
}
