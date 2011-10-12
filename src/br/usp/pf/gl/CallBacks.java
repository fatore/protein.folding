package br.usp.pf.gl;

import br.usp.pf.core.PFTriangle;
import br.usp.pf.core.PFVertex;
import br.usp.pf.projections.Loader;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;

/**
 *
 * @author Fatore
 */
public class CallBacks extends GLJPanelInteractive {

    private Loader l;
    private PFTriangle[] triangles;
    private int shadeModel;

    // load mode -> 1 = smooth, 0 = normal
    public CallBacks(GLCapabilities glcaps, String filename, int shadeModel) throws Exception {
        super(glcaps);

        this.shadeModel = shadeModel;

        l = new Loader();
        if (l.loadVertices(filename) < 1) {
            System.exit(1);
        }

        int loadMode = 0;
        if (shadeModel == GL.GL_SMOOTH) {
            loadMode = 1;
        }

        //executa a triangulizacao dos pos pontos
        this.triangles = l.createTriangles(loadMode);

    }

    //calcula a normal da face
    //inicializacao sistema
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        gl.glShadeModel(shadeModel);
        gl.glEnable(GL.GL_NORMALIZE);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);

        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.6f * 128.0f);

        setMaterial(drawable);
        lighting(drawable);

    }

    //funcao de desenho
    public void display(GLAutoDrawable drawable) {
        if (shadeModel == GL.GL_SMOOTH) {
            displaySmooth(drawable);
        } else {
            displayFlat(drawable);
        }
    }

    public void displayFlat(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        for (PFTriangle t : triangles) {
            gl.glBegin(GL.GL_TRIANGLES);
            float[] nv = t.getNormalVector();
            gl.glNormal3f(-nv[0], -nv[1], -nv[2]);
            for (PFVertex v : t.getVertices()) {
                gl.glVertex3f(v.getX(), v.getEnergy(), v.getY());
                //gl.glVertex3f(v.getX(), 0, v.getY());
            }
            gl.glEnd();
        }

        gl.glFlush();
    }

    public void displaySmooth(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        for (PFTriangle t : triangles) {
            gl.glBegin(GL.GL_TRIANGLES);
            for (PFVertex v : t.getVertices()) {
                float[] nv = v.getNormalVector();
                gl.glNormal3f(-nv[0], -nv[1], -nv[2]);
                gl.glVertex3f(v.getX(), v.getEnergy(), v.getY());
                //gl.glVertex3f(v.getX(), 0, v.getY());
            }
            gl.glEnd();
        }

        gl.glFlush();
    }

    private void lighting(GLAutoDrawable glad) {
        GL gl = glad.getGL();

        float[] luzAmbiente = {0.5f, 0.5f, 0.5f, 1.0f};
        float[] luzDifusa = new float[]{0.75f, 0.75f, 0.75f, 1.0f};
        float[] luzEspecular = new float[]{0.7f, 0.7f, 0.7f, 1.0f};
        float[] posicaoLuz = new float[]{
            GLJPanelInteractive.WINDOW_SIZE * 5.0f,
            GLJPanelInteractive.WINDOW_SIZE * 5.0f,
            GLJPanelInteractive.WINDOW_SIZE * 5.0f,
            1.0f
        };

        // Define os parametros da luz 0
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, luzDifusa, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, luzEspecular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posicaoLuz, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);


    }

    private void setMaterial(GLAutoDrawable glad) {
        GL gl = glad.getGL();

        //float mat[] = v.getColor();
        float[] mat = new float[]{0.0f, 0.0f, 1.0f, 1.0f};

        mat[2] -= 0.0;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat, 0);

        mat[2] -= 0.15;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat, 0);

        mat[2] -= 0.15;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat, 0);


    }

    private void setMaterial(GLAutoDrawable glad, PFVertex v) {
        GL gl = glad.getGL();

        //float mat[] = v.getColor();
        float[] mat = new float[]{
            v.getColor()[0],
            v.getColor()[1],
            v.getColor()[2],
            1.0f
        };

        mat[2] -= 0.0;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat, 0);

        mat[2] -= 0.15;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat, 0);

        mat[2] -= 0.15;
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat, 0);

    }

    //funcao de redesenhamento
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    //funcao para mudanca de display
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
