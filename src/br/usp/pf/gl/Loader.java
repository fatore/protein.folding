/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.gl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.util.Delaunay;

/**
 *
 * @author Fatore
 */
public class Loader {

    private Vertex[] vertices;
    private int verticesCounter;
    private float[][] exValues;

    public Vertex[] getVertices() {
        return vertices;
    }

    public int getVerticesCounter() {
        return verticesCounter;
    }

    public float[][] getExValues() {
        return exValues;
    }

    public Vertex getVertex(int index) {
        return vertices[index];
    }

    //le os vertices do arquivo
    public int loadVertices(String inputFile) throws Exception {

        BufferedReader in = null;

        in = new BufferedReader(new FileReader(inputFile));

        String curLine = null;

        StringTokenizer token = null;

        //descarta primeira linha, DY
        in.readLine();

        //guarda o numero de estados, vertices
        curLine = in.readLine();
        token = new StringTokenizer(curLine);
        this.verticesCounter = Integer.parseInt(token.nextToken());

        //descarta linha sobre numero de colunas
        in.readLine();

        //descarta linha sobre informacao sobre colunas
        in.readLine();

        //armazena todos os vertices em um array
        vertices = new Vertex[verticesCounter];

        //preenche o array
        int i = 0;
        while ((curLine = in.readLine()) != null) {
            token = new StringTokenizer(curLine, ";");

            Vertex v = new Vertex();

            v.setId(Integer.parseInt(token.nextToken()));
            v.setX(Float.parseFloat(token.nextToken()));
            v.setY(Float.parseFloat(token.nextToken()));
            v.setEnergy(Float.parseFloat(token.nextToken()));

            vertices[i++] = v;
        }

        in.close();

        return verticesCounter;
    }

    //retorna um vetor contendo dois vetores que representam valores min e max
    private void calculateMinMax() {

        float min_x = vertices[0].getX();
        float min_y = vertices[0].getY();
        float min_e = vertices[0].getEnergy();

        float max_x = vertices[0].getX();
        float max_y = vertices[0].getY();
        float max_e = vertices[0].getEnergy();

        for (Vertex _v : vertices) {
            if (_v.getX() < min_x) {
                min_x = _v.getX();
            }
            if (_v.getY() < min_y) {
                min_y = _v.getY();
            }
            if (_v.getEnergy() < min_e) {
                min_e = _v.getEnergy();
            }

            if (_v.getX() > max_x) {
                max_x = _v.getX();
            }
            if (_v.getY() > max_y) {
                max_y = _v.getY();
            }
            if (_v.getEnergy() > max_e) {
                max_e = _v.getEnergy();
            }
        }

        float[] min = {min_x, min_y, min_e};
        float[] max = {max_x, max_y, max_e};

        float[][] r = {min, max};

        this.exValues = r;
    }

    //retorna um vetor de triagulos 
    public Triangle[] createTriangles() {

        calculateMinMax();
        //normalizar todos vertices
        for (Vertex v : vertices) {
            v.normalize(exValues);
        }

        float[][] points = new float[verticesCounter][];
        for (int i = 0; i < verticesCounter; i++) {
            points[i] = new float[2];
            points[i][0] = vertices[i].getX();
            points[i][1] = vertices[i].getY();
        }

        long start = System.currentTimeMillis();

        //Creating the Delaunay triangulation
        float[] dtpoints = new float[points.length * 2];

        for (int i = 0; i < dtpoints.length; i += 2) {
            dtpoints[i] = points[i / 2][0];
            dtpoints[i + 1] = points[i / 2][1];
        }

        int[] ed = Delaunay.triangulate(dtpoints);

        Triangle[] triangles = new Triangle[(int) ((ed.length / 3))];

        int index = 0;
        for (int i = 0; i < ed.length; i++) {
            Triangle t = new Triangle(
                    this.vertices[(ed[i++] - 3)],
                    this.vertices[(ed[i++] - 3)],
                    this.vertices[(ed[i] - 3)]);

            t.calculateNormalVector();

            triangles[index++] = t;
        }

        long finish = System.currentTimeMillis();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Delaunay time: {0}s", (finish - start) / 1000.0f);

        return triangles;
    }

    public static void main(String args[]) throws Exception {
        Loader l = new Loader();
        l.loadVertices("../data/projections//inicial_2.prj");
    }
}
