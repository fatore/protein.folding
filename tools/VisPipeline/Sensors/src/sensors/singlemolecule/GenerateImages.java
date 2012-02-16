/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.singlemolecule;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 *
 * @author paulovich
 */
public class GenerateImages {

    public static void execute(AbstractMatrix matrix, String filename) throws IOException {
        int width = (int) (matrix.getDimensions() * 1.5f);
        int height = (int) (width * 0.5f);

        ZipOutputStream zout = null;

        try {

            float[][] matrix_aux = matrix.toMatrix();

            //normalizing
            float max = Float.NEGATIVE_INFINITY;
            float min = Float.POSITIVE_INFINITY;
            for (int i = 0; i < matrix_aux.length; i++) {
                for (int j = 0; j < matrix_aux[i].length; j++) {
                    if (matrix_aux[i][j] > max) {
                        max = matrix_aux[i][j];
                    }

                    if (matrix_aux[i][j] < min) {
                        min = matrix_aux[i][j];
                    }
                }
            }

            for (int i = 0; i < matrix_aux.length; i++) {
                for (int j = 0; j < matrix_aux[i].length; j++) {
                    matrix_aux[i][j] = (matrix_aux[i][j] - min) / (max - min);
                }
            }

            int hmin = 10;
            int hmax = width - 10;
            int hspacing = (hmax - hmin) / (matrix.getDimensions() - 1);

            int vmax = height - 10;
            int vmin = 10;
            int vspace = vmax - vmin;

            //creating the zip file
            FileOutputStream dest = new FileOutputStream(filename);
            zout = new ZipOutputStream(new BufferedOutputStream(dest));

            //creating an image for each row
            for (int i = 0; i < matrix_aux.length; i++) {
                //creating the image
                BufferedImage image = new BufferedImage(2 * hmin + hspacing * matrix_aux[i].length, height, BufferedImage.TYPE_INT_RGB);

                //getting the draw context
                Graphics2D g2Buffer = image.createGraphics();
                g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2Buffer.setStroke(new BasicStroke(2.0f));

                //drawing the background
                g2Buffer.setColor(Color.WHITE);
                g2Buffer.fillRect(0, 0, image.getWidth(), image.getHeight());

                g2Buffer.setColor(Color.BLACK);
                g2Buffer.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);

                for (int j = 0; j < matrix_aux[i].length - 1; j++) {
                    int x1 = hmin + (j * hspacing);
                    int x2 = hmin + ((j + 1) * hspacing);

                    int y1 = (int) (vmin + (vspace - (vspace * matrix_aux[i][j])));
                    int y2 = (int) (vmin + (vspace - (vspace * matrix_aux[i][j + 1])));

                    g2Buffer.drawLine(x1, y1, x2, y2);
                }

                g2Buffer.setColor(Color.RED);
                g2Buffer.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                g2Buffer.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 120));
                java.awt.FontMetrics metrics = g2Buffer.getFontMetrics(g2Buffer.getFont());
                g2Buffer.drawString(matrix.getLabel(i), 10, (int) (metrics.getAscent() * 0.75) + 5);

                //adding the image to the zip file
                ZipEntry entry = new ZipEntry(matrix.getLabel(i) + ".png");
                zout.putNextEntry(entry);
                ImageIO.write(image, "png", new BufferedOutputStream(zout));
                zout.closeEntry();
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (zout != null) {
                zout.flush();
                zout.finish();
                zout.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filename = "D:/My Dropbox/artigos/Case-Prudente/single-molecule/data/SMD-MB/SMD_MB_1molecule_a.data";
        AbstractMatrix matrix = MatrixFactory.getInstance(filename);

        DataTransformation.removeNoise(matrix, 1, 20);
        matrix.save(filename.substring(0, filename.lastIndexOf(".")) + "-clean.data");
        
        GenerateImages.execute(matrix, filename.substring(0, filename.lastIndexOf(".")) + "-images.zip");
    }
}
