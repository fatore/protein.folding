/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.view.selection;

import visualizationbasics.view.selection.AbstractSelection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import projection.model.ProjectionInstance;
import visualizationbasics.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.YIntervalRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import projection.model.ProjectionModel;
import visualizationbasics.util.OpenDialog;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import projection.view.ProjectionFrame;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.filter.DATAandBINMultipleFilter;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class RangeSelection extends AbstractSelection {

    public RangeSelection(ModelViewer viewer) {
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
                            Logger.getLogger(RangeSelection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(RangeSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (matrix != null) {
                MinMax[] selranges = new MinMax[matrix.getDimensions()];
                MinMax[] dataranges = new MinMax[matrix.getDimensions()];
                for (int i = 0; i < selranges.length; i++) {
                    selranges[i] = new MinMax();
                    dataranges[i] = new MinMax();
                }

                //store all selected ids
                HashSet<Integer> selids = new HashSet<Integer>();
                for (AbstractInstance pi : selinst) {
                    selids.add(pi.getId());
                }

                //getting the max and min of each attribute
                for (int i = 0; i < matrix.getRowCount(); i++) {
                    AbstractVector row = matrix.getRow(i);

                    float[] array = row.toArray();

                    //storing the min and max values of the selection
                    if (selids.contains(row.getId())) {
                        for (int j = 0; j < array.length; j++) {
                            selranges[j].store(array[j]);
                        }
                    }

                    //storing the min and max values of the data
                    for (int j = 0; j < array.length; j++) {
                        dataranges[j].store(array[j]);
                    }
                }

                if (viewer.getModel() != null) {
                    Scalar rangescalar = ((ProjectionModel) viewer.getModel()).addScalar("range");

                    ArrayList<AbstractInstance> instances = viewer.getModel().getInstances();

                    for (int i = 0; i < instances.size(); i++) {
                        ((ProjectionInstance) instances.get(i)).setScalarValue(rangescalar,
                                calculateScalar(matrix.getRow(i), selranges));
                    }

                    if (viewer instanceof ProjectionFrame) {
                        ((ProjectionFrame) viewer).updateScalars(rangescalar);
                    }
                }

                RangeView view = new RangeView();
                view.display(selranges, dataranges);
            }

            if (viewer.getModel() != null) {
                viewer.getModel().setSelectedInstances(selinst);
                viewer.getModel().notifyObservers();
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/table/ColumnDelete16.gif"));
    }

    @Override
    public String toString() {
        return "Range Selection";
    }

    private float calculateScalar(AbstractVector v, MinMax[] ranges) {
        float[] array = v.toArray();
        float scalar = 0.0f;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > ranges[i].max || array[i] < ranges[i].min) {
                return 0.0f;
            } else {
                scalar += Math.abs(array[i] - ((ranges[i].max + ranges[i].min) / 2));
            }

        }

        return -scalar;
    }

    public class MinMax {

        public MinMax() {
            this.min = Float.MAX_VALUE;
            this.max = Float.MIN_VALUE;
        }

        public void store(float value) {
            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }
        }

        public float min() {
            return min;
        }

        public float max() {
            return max;
        }

        @Override
        public String toString() {
            return "(" + min + "," + max + ")";
        }

        private float min;
        private float max;
    }

    class RangeView extends ApplicationFrame {

        public RangeView() {
            super("Range View");
        }

        public void display(MinMax[] selranges, MinMax[] dataranges) {
            JPanel panel = new ChartPanel(createChart(createDataset(selranges, dataranges)));
            panel.setPreferredSize(new Dimension(500, 300));
            setContentPane(panel);

            pack();
            RefineryUtilities.centerFrameOnScreen(this);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        private JFreeChart createChart(IntervalXYDataset intervalxydataset) {
            JFreeChart jfreechart = ChartFactory.createScatterPlot("", "Attribute",
                    "Range", intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
            jfreechart.setBackgroundPaint(Color.white);
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            xyplot.setBackgroundPaint(Color.lightGray);
            xyplot.setDomainGridlinePaint(Color.white);
            xyplot.setRangeGridlinePaint(Color.white);
            xyplot.setAxisOffset(new RectangleInsets(10, 10, 10, 10));
            xyplot.setRenderer(new YIntervalRenderer());
            return jfreechart;
        }

        private IntervalXYDataset createDataset(MinMax[] selranges, MinMax[] dataranges) {
            YIntervalSeriesCollection seriescollection = new YIntervalSeriesCollection();

            YIntervalSeries selseries = new YIntervalSeries("Selection");
            for (int i = 0; i < selranges.length; i++) {
                selseries.add(i + 1, 0,
                        ((selranges[i].min - dataranges[i].min) / (dataranges[i].max - dataranges[i].min)),
                        ((selranges[i].max - dataranges[i].min) / (dataranges[i].max - dataranges[i].min)));
            }

            seriescollection.addSeries(selseries);

            YIntervalSeries dataseries = new YIntervalSeries("Data set");
            for (int i = 0; i < dataranges.length; i++) {
                dataseries.add(i + 1, 0, 0, 1);
            }

            seriescollection.addSeries(dataseries);

            return seriescollection;
        }

        @Override
        public void windowClosing(final WindowEvent evt) {
            if (evt.getWindow() == this) {
                dispose();

            }
        }

    }

    private AbstractMatrix matrix;
}
