/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.dense.DenseMatrix;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;
import visualizationbasics.color.ColorTable;

/**
 *
 * @author Fernando
 */
public class GenerateImageFromProjection {

    public void execute(String filename, int width, int height) throws IOException {
        //reading projection
        DenseMatrix projection = new DenseMatrix();
        projection.load(filename);

        //normalize the projection
        normalize(projection, width, height);
        normalizeScalar(projection);

        //draw to a file
        BufferedImage image = new BufferedImage(width + 10, height + 10, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width + 10, height + 10);

        for (int i = 0; i < projection.getRowCount(); i++) {
            drawPoint(image, projection.getRow(i));
        }

        ImageIO.write(image, "png", new File(filename + ".png"));
    }

    private void normalizeScalar(AbstractMatrix projection) {
        float max = Float.NEGATIVE_INFINITY;
        float min = Float.POSITIVE_INFINITY;

        for (int i = 0; i < projection.getRowCount(); i++) {
            float scalar = projection.getRow(i).getKlass();

            if (scalar > max) {
                max = scalar;
            }

            if (scalar < min) {
                min = scalar;
            }
        }

        for (int i = 0; i < projection.getRowCount(); i++) {
            float scalar = projection.getRow(i).getKlass();

            if (max > min) {
                projection.getRow(i).setKlass((scalar - min) / (max - min));
            } else {
                projection.getRow(i).setKlass(0);
            }
        }

    }

    private void normalize(AbstractMatrix projection, int width, int height) {
        float maxx = Float.NEGATIVE_INFINITY;
        float minx = Float.POSITIVE_INFINITY;
        float maxy = Float.NEGATIVE_INFINITY;
        float miny = Float.POSITIVE_INFINITY;

        for (int i = 0; i < projection.getRowCount(); i++) {
            float[] array = projection.getRow(i).toArray();

            if (maxx < array[0]) {
                maxx = array[0];
            }

            if (minx > array[0]) {
                minx = array[0];
            }

            if (maxy < array[1]) {
                maxy = array[1];
            }

            if (miny > array[1]) {
                miny = array[1];
            }
        }

        float begin = 10.0f;
        float endy = height;
        float endx = width;

//        if (maxy > maxx) {
//            endy = Math.min(width, height) - begin;
//
//            if (maxy != miny) {
//                endx = ((maxx - minx) * endy) / (maxy - miny);
//            } else {
//                endx = ((maxx - minx) * endy);
//            }
//        } else {
//            endx = Math.min(width, height) - begin;
//
//            if (maxx != minx) {
//                endy = ((maxy - miny) * endx) / (maxx - minx);
//            } else {
//                endy = ((maxy - miny) * endx);
//            }
//        }
//
//        System.out.println(endx + " - "+ endy);

        for (int i = 0; i < projection.getRowCount(); i++) {
            float[] array = projection.getRow(i).getValues();

            if (maxx != minx) {
                array[0] = (((array[0] - minx) / (maxx - minx))
                        * (endx - begin)) + begin;
            } else {
                array[0] = begin;
            }

            if (maxy != miny) {
                array[1] = (((array[1] - miny) / (maxy - miny))
                        * (endy - begin)) + begin;
            } else {
                array[1] = begin;
            }
        }
    }

    private void drawPoint(BufferedImage image, AbstractVector point) {
        float[] array = point.toArray();
        float x = array[0];
        float y = array[1];

        int rgbcolor = ctable.getColor(point.getKlass()).getRGB();
        
        image.setRGB((int) x - 1, (int) y - 1, rgbcolor);
        image.setRGB((int) x, (int) y - 1, rgbcolor);
        image.setRGB((int) x + 1, (int) y - 1, rgbcolor);
        image.setRGB((int) x - 1, (int) y, rgbcolor);
        image.setRGB((int) x, (int) y, rgbcolor);
        image.setRGB((int) x + 1, (int) y, rgbcolor);
        image.setRGB((int) x - 1, (int) y + 1, rgbcolor);
        image.setRGB((int) x, (int) y + 1, rgbcolor);
        image.setRGB((int) x + 1, (int) y + 1, rgbcolor);

//        float alpha = 0.005f;
//        simulateAlpha(image, alpha, (int) x - 1, (int) y - 1, rgbcolor);
//        simulateAlpha(image, alpha, (int) x, (int) y - 1, rgbcolor);
//        simulateAlpha(image, alpha, (int) x + 1, (int) y - 1, rgbcolor);
//        simulateAlpha(image, alpha, (int) x - 1, (int) y, rgbcolor);
//        simulateAlpha(image, alpha, (int) x, (int) y, rgbcolor);
//        simulateAlpha(image, alpha, (int) x + 1, (int) y, rgbcolor);
//        simulateAlpha(image, alpha, (int) x - 1, (int) y + 1, rgbcolor);
//        simulateAlpha(image, alpha, (int) x, (int) y + 1, rgbcolor);
//        simulateAlpha(image, alpha, (int) x + 1, (int) y + 1, rgbcolor);
    }

    protected void simulateAlpha(BufferedImage image, float alpha, int x, int y, int rgb) {
        //C = (alpha * (A-B)) + B
        int oldrgb = image.getRGB(x, y);
        int oldr = (oldrgb >> 16) & 0xFF;
        int oldg = (oldrgb >> 8) & 0xFF;
        int oldb = oldrgb & 0xFF;

        int newr = (int) ((alpha * (((rgb >> 16) & 0xFF) - oldr)) + oldr);
        int newg = (int) ((alpha * (((rgb >> 8) & 0xFF) - oldg)) + oldg);
        int newb = (int) ((alpha * ((rgb & 0xFF) - oldb)) + oldb);

        int newrgb = newb | (newg << 8) | (newr << 16);
        image.setRGB(x, y, newrgb);
    }

    public static void main(String[] args) throws IOException {
        String dirname = "/home/paulovich/Dropbox/dados/";
        File dir = new File(dirname);

        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            String file = files[i];

            if (file.endsWith(".prj")) {
                System.out.println(file);

                GenerateImageFromProjection gen = new GenerateImageFromProjection();
                gen.execute(dirname + file, 1000, 1000);
            }
        }
    }

    private ColorTable ctable = new ColorTable(ColorScaleType.CATEGORY_SCALE);
}
