/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.gl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import visualizer.graph.Edge;
import visualizer.util.Delaunay;
import visualizer.util.Pair;
import br.usp.pf.core.PFTriangle;
import br.usp.pf.core.PFVertex;

/**
 *
 * @author Fatore
 */
public class CopyOfLoader {

    private PFVertex[] vertices;
    private int verticesCounter;
    //[0][x] -> minimos de x, y e energia
    //[1][x] -> maximos de x, y e energia
    private float[][] exValues;

    //ler os vertices do arquivo
    public int loadVertices(String inputFile) throws Exception {

        BufferedReader in = null;

        in = new BufferedReader(new FileReader(inputFile));

        String curLine = null;

        StringTokenizer token = null;

        //descartar primeira linha, DY
        in.readLine();

        //guardar o numero de estados, vertices
        curLine = in.readLine();
        token = new StringTokenizer(curLine);
        this.verticesCounter = Integer.parseInt(token.nextToken());

        //descartar linha sobre numero de colunas
        in.readLine();

        //descartar linha sobre informacao sobre colunas
        in.readLine();

        //armazena todos os vertices em um array
        vertices = new PFVertex[verticesCounter];

        //preencher o array
        int i = 0;
        while ((curLine = in.readLine()) != null) {
            token = new StringTokenizer(curLine, ";");

            PFVertex v = new PFVertex(
                    Integer.parseInt(token.nextToken()),
                    Float.parseFloat(token.nextToken()),
                    Float.parseFloat(token.nextToken()),
                    Float.parseFloat(token.nextToken()));

            vertices[i++] = v;
        }

        in.close();

        //calcular valores maximos e minimos
        calculateMinMax();
        //calcula a cor para cada vertice
        calculateColors();

        return verticesCounter;
    }

    //return a triangles array
    public PFTriangle[] createTriangles(int mode) {

        //normalize all vertices
        for (PFVertex v : vertices) {
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

        PFTriangle[] triangles = new PFTriangle[(int) ((ed.length / 3))];

        int index = 0;
        for (int i = 0; i < ed.length; i++) {
            PFTriangle t = new PFTriangle(
                    this.vertices[(ed[i++] - 3)],
                    this.vertices[(ed[i++] - 3)],
                    this.vertices[(ed[i] - 3)]);

            t.calculateNormalVector();

            triangles[index++] = t;
        }

        //smooth mode
        if (mode == 1) {
            //repeat as many times as necessary
            smoothVertices(points, ed);
            //calculate the normal vector for each vertice
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
            calculateVerticesNormals(triangles);
        }

        long finish = System.currentTimeMillis();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Delaunay time: {0}s", (finish - start) / 1000.0f);

        return triangles;
    }

    private void calculateVerticesNormals(PFTriangle[] triangles) {
        //run over all vertices searching for related triangles
        for (PFVertex v: vertices) {
            ArrayList<float[]> vnvs = new ArrayList<float[]>();
            for (PFTriangle t : triangles) {
                for (PFVertex tv : t.getVertices()) {
                    if (v.getId() == tv.getId()) {
                        vnvs.add(t.getNormalVector());
                    }
                }
            }
            float[][] verticeNormal = new float[vnvs.size()][];
            vnvs.toArray(verticeNormal);
            v.setNormalVector(verticeNormal);
        }
    }





    //retorna um vetor contendo dois vetores que representam valores min e max
    private void calculateMinMax() {

        float min_x = vertices[0].getX();
        float min_y = vertices[0].getY();
        float min_e = vertices[0].getEnergy();

        float max_x = vertices[0].getX();
        float max_y = vertices[0].getY();
        float max_e = vertices[0].getEnergy();

        for (PFVertex _v : vertices) {
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

    //calcula a cor para cada vertice
    private void calculateColors() {
        for (PFVertex v : vertices) {
            v.setColor(this.exValues[0][2]);
        }
    }

    //suaviza todos os vertices carregados
    private void smoothVertices(float[][] points,int[] ed) {
        //construo um vetor de vertices para passar para setsmooth
        ArrayList<ArrayList<Pair>> neigh_aux = new ArrayList<ArrayList<Pair>>();

        for (int i = 0; i < points.length; i++) {
            neigh_aux.add(new ArrayList<Pair>());
        }

        for (int i = 0; i < ed.length; i++) {
            long v1 = ed[i++] - 3;
            long v2 = ed[i++] - 3;
            long v3 = ed[i] - 3;

            neigh_aux.get((int) v1).add(new Pair((int) v2, Edge.NO_SIZE));
            neigh_aux.get((int) v1).add(new Pair((int) v3, Edge.NO_SIZE));
            neigh_aux.get((int) v2).add(new Pair((int) v3, Edge.NO_SIZE));
        }

        Pair[][] neighborhood = new Pair[points.length][];

        for (int i = 0; i < neigh_aux.size(); i++) {
            neighborhood[i] = new Pair[neigh_aux.get(i).size()];

            for (int j = 0; j < neigh_aux.get(i).size(); j++) {
                neighborhood[i][j] = neigh_aux.get(i).get(j);
            }
        }

        for (int i = 0; i < neighborhood.length; i++) {
            PFVertex[] vts = new PFVertex[neighborhood[i].length];
            for (int j = 0; j < neighborhood[i].length; j++) {
                vts[j] = this.vertices[neighborhood[i][j].index];
            }
            this.vertices[i].setSmoothVertex(vts);
        }

        

        for (PFVertex v : vertices) {
            v.smooth();
        }
    }

    

    public PFVertex[] getVertices() {
        return vertices;
    }

    public int getVerticesCounter() {
        return verticesCounter;
    }

    public float[][] getExValues() {
        return exValues;
    }

    public PFVertex getVertex(int index) {
        return vertices[index];
    }

    public static void main(String args[]) throws Exception {
        CopyOfLoader l = new CopyOfLoader();
        l.loadVertices("projection.prj");
    }
}