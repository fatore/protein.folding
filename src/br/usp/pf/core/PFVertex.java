/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.core;

import br.usp.pf.util.PFColor;

import java.awt.Color;

/**
 *
 * @author Fatore
 */
class SmoothVertex {

    /**
	 */
    float x;
    /**
	 */
    float y;
    /**
	 */
    float energy;
}

public class PFVertex {

    /**
	 */
    private int id;
    /**
	 */
    private float x;
    /**
	 */
    private float y;
    /**
	 */
    private float energy;
    /**
	 */
    private float[] color;
    /**
	 */
    private SmoothVertex smoothVertex;
    /**
	 */
    float[] normalVector;

    public PFVertex(int id, float x, float y, float energy) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.energy = energy;
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

    //retorna um float[4] contendo as informaçoes para composição do material, deve-se passar a energia do root
    /**
	 * @param rootEnergy
	 */
    public void setColor(float rootEnergy) {
        Color c = PFColor.calculateColor(this.energy, rootEnergy);

        float[] r = new float[4];
        c.getColorComponents(r);

        this.color = r;
    }

    private void divideByf(float f) {
        this.x = this.x / f;
        this.y = this.y / f;
        this.energy = this.energy / f;
    }

    public void setSmoothVertex(PFVertex vts[]) {

        smoothVertex = new SmoothVertex();

        //soma os vizinhos
        for (PFVertex v : vts) {
            smoothVertex.x += v.getX();
            smoothVertex.y += v.getY();
            smoothVertex.energy += v.getEnergy();
        }

        //soma o proprio vertice
        smoothVertex.x += this.getX();
        smoothVertex.y += this.getY();
        smoothVertex.energy += this.getEnergy();

        //tira a media, div = numeros de vizinhos + o proprio vertice
        int div = vts.length + 1;
        smoothVertex.x /= div;
        smoothVertex.y /= div;
        smoothVertex.energy /= div;
    }

    public void smooth() {
        this.x = smoothVertex.x;
        this.y = smoothVertex.y;
        this.energy = smoothVertex.energy;
    }

    /**
	 * @param normals
	 */
    public void setNormalVector(float[][] normals) {
        float[] vn = new float[3];
        for (float[] n : normals) {
            vn[0] += n[0];
            vn[1] += n[1];
            vn[2] += n[2];
        }
        float div = normals.length;
        vn[0] /= div;
        vn[1] /= div;
        vn[2] /= div;

        this.normalVector = vn;
    }

    /**
	 * @return
	 */
    public float[] getNormalVector() {
        return this.normalVector;
    }

    /**
	 * @return
	 */
    public float getEnergy() {
        return energy;
    }

    /**
	 * @return
	 */
    public int getId() {
        return id;
    }

    /**
	 * @return
	 */
    public float getX() {
        return x;
    }

    /**
	 * @return
	 */
    public float getY() {
        return y;
    }

    /**
	 * @return
	 */
    public float[] getColor() {
        return color;
    }
}
