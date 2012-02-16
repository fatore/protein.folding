/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.view.selection;

import visualizationbasics.view.selection.AbstractSelection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import visualizationbasics.model.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import projection.model.ProjectionModel;
import visualizationbasics.util.OpenDialog;
import projection.util.ProjectionConstants;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.filter.DATAandBINMultipleFilter;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class CovarianceSelection extends AbstractSelection {

    public CovarianceSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (selinst.size() > 0) {
            if (matrix == null) {
                try {
                    PropertiesManager spm = PropertiesManager.getInstance(ProjectionConstants.PROPFILENAME);
                    int result = OpenDialog.showOpenDialog(spm, new DATAandBINMultipleFilter(), viewer);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            String filename = OpenDialog.getFilename();
                            matrix = MatrixFactory.getInstance(filename);
                        } catch (IOException ex) {
                            Logger.getLogger(CovarianceSelection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CovarianceSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (matrix != null) {
                float[][] points = new float[selinst.size()][];
                //store all selected ids
                HashSet<Integer> selids = new HashSet<Integer>();
                for (int i = 0; i < selinst.size(); i++) {
                    AbstractInstance pi = selinst.get(i);
                    selids.add(pi.getId());
                    int index = matrix.getIds().indexOf(pi.getId());
                    //int index = pi.getId();
                    points[i] = matrix.getRow(index).toArray();
                }


                //Extracting the mean of the columns
                float[] mean = new float[matrix.getDimensions()];
                Arrays.fill(mean, 0.0f);

                for (int i = 0; i < points.length; i++) {
                    //calculating
                    for (int j = 0; j < points[i].length; j++) {
                        mean[j] += points[i][j];
                    }
                }

                for (int i = 0; i < mean.length; i++) {
                    mean[i] /= points.length;
                }

                //extracting the mean
                for (int i = 0; i < points.length; i++) {
                    for (int j = 0; j < points[i].length; j++) {
                        points[i][j] -= mean[j];
                    }
                }


                float[][] cov = new float[matrix.getDimensions() - 1][];
                for (int i = 0; i < cov.length; i++) {
                    cov[i] = new float[i + 1];
                }

                float max = Float.MIN_VALUE;
                float min = Float.MAX_VALUE;
                for (int i = 0; i < cov.length; i++) {
                    for (int j = 0; j < cov[i].length; j++) {
                        cov[i][j] = covariance(points, i + 1, j);
                        if (cov[i][j] > max) {
                            max = cov[i][j];
                        }
                        if (cov[i][j] < min) {
                            min = cov[i][j];
                        }
                    }
                }

                System.out.println("Max: " + max);
                System.out.println("Min: " + min);

                //normalization
                DecimalFormat fmt = new DecimalFormat("0.######");
                for (int i = 0; i < cov.length; i++) {
                    for (int j = 0; j < cov[i].length; j++) {
                        cov[i][j] = (cov[i][j] - min) / (max - min);
                        System.out.print(fmt.format(cov[i][j]) + " ");
                    }
                    System.out.println();
                }

                CovarianceView view = new CovarianceView();
                view.display(cov);
            }

            if (viewer.getModel() != null) {
                viewer.getModel().setSelectedInstances(selinst);
                viewer.getModel().notifyObservers();
            }
        }
    }

    private float covariance(float[][] points, int a, int b) {
        float covariance = 0.0f;
        for (int i = 0; i < points.length; i++) {
            covariance += points[i][a] * points[i][b];
        }
        covariance /= points.length;
        return covariance;
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/table/RowInsertBefore16.gif"));
    }

    @Override
    public String toString() {
        return "Covariance Selection";
    }

    class CovarianceView extends ApplicationFrame {

        public CovarianceView() {
            super("Covariance View");
        }

        public void display(float[][] matrix) {
            this.setSize(500, 500);
            this.view = new ViewPanel();
            this.view.setPreferredSize(new Dimension(500, 300));
            setContentPane(this.view);
            pack();

            this.covMatrix = matrix;

            RefineryUtilities.centerFrameOnScreen(this);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        private void draw(BufferedImage image) {
            Graphics2D g2 = (Graphics2D) image.getGraphics();
            int x0 = 10;
            int y0 = 10;
            int dist = 20;

            //triangular matrix
            for (int i = 0; i < this.covMatrix.length; i++) {
                for (int j = 0; j < this.covMatrix[i].length; j++) {
                    g2.setColor(((ProjectionModel) viewer.getModel()).getColorTable().getColor(this.covMatrix[i][j]));
                    g2.fillRect(x0 + j * dist, y0 + (i + 1) * dist, dist, dist);
                    g2.fillRect(x0 + (i + 1) * dist, y0 + j * dist, dist, dist);
                    //g2.fillRect(y0 + (i + 1) * dist, x0 + j * dist, dist, dist);
                }
            }

            //diagonal
            g2.setColor(((ProjectionModel) viewer.getModel()).getColorTable().getColor(0.0f));
            for (int i = 0; i <= this.covMatrix.length; i++) {
                int j = i;
                g2.fillRect(x0 + j * dist, y0 + i * dist, dist, dist);
            }

            //lines
            g2.setColor(Color.BLACK);
            //g2.drawLine(x0, y0, x0 + this.covMatrix.length*dist, y0);
            for (int i = 0; i < this.covMatrix.length; i++) {
                g2.drawLine(x0, y0 + (i + 1) * dist, x0 + (this.covMatrix.length + 1) * dist, y0 + (i + 1) * dist);
                g2.drawLine(x0 + (i + 1) * dist, y0, x0 + (i + 1) * dist, y0 + (this.covMatrix.length + 1) * dist);
            }
        }

        @Override
        public void windowClosing(final WindowEvent evt) {
            if (evt.getWindow() == this) {
                dispose();

            }
        }

        public class ViewPanel extends JPanel {

            @Override
            public void paintComponent(java.awt.Graphics g) {
                super.paintComponents(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

                if (image == null) {
                    Dimension size = this.getSize();
                    image = new BufferedImage(size.width + 10, size.height + 10,
                            BufferedImage.TYPE_INT_RGB);

                    System.out.println(size.width);
                    System.out.println(size.height);
                    java.awt.Graphics2D g2Buffer = image.createGraphics();
                    g2Buffer.setColor(Color.WHITE);
                    g2Buffer.fillRect(0, 0, size.width + 10, size.height + 10);


                    g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);


                    draw(image);

                    g2Buffer.dispose();
                }

                if (image != null) {
                    g2.drawImage(image, 0, 0, null);
                }
            }

            private BufferedImage image = null;
        }

        private ViewPanel view = null;
        private float[][] covMatrix = null;
    }

    private AbstractMatrix matrix;
}
