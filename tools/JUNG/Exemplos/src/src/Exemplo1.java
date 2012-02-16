
import edu.uci.ics.jung.samples.PluggableRendererDemo;
import javax.swing.JFrame;

public class Exemplo1 extends javax.swing.JApplet {

    public static void main(String[] args) {

        //AddNodeDemo and = new AddNodeDemo();
        //AnimatingAddNodeDemo and = new AnimatingAddNodeDemo();
        //AnnotationsDemo and = new AnnotationsDemo();
        //BalloonLayoutDemo and = new BalloonLayoutDemo();
        //ClusteringDemo and = new ClusteringDemo();
        //EdgeLabelDemo and = new EdgeLabelDemo();
        //GraphEditorDemo and = new GraphEditorDemo();
        //ImageEdgeLabelDemo and = new ImageEdgeLabelDemo();
        //L2RTreeLayoutDemo and = new L2RTreeLayoutDemo();
        //LensDemo and = new LensDemo();
        //LensVertexImageShaperDemo and = new LensVertexImageShaperDemo();
        //RadialTreeLensDemo and = new RadialTreeLensDemo();
        //SatelliteViewDemo and = new SatelliteViewDemo();
        //ShortestPathDemo and = new ShortestPathDemo();
        //ShowLayouts and = new ShowLayouts();
        //SubLayoutDemo and = new SubLayoutDemo();
        //TreeCollapseDemo and = new TreeCollapseDemo();
        //TwoModelDemo and = new TwoModelDemo();
        //VertexCollapseDemo and = new VertexCollapseDemo();
        //VertexCollapseDemoWithLayouts and = new VertexCollapseDemoWithLayouts();
        //VertexImageShaperDemo and = new VertexImageShaperDemo();
        //VertexLabelAsShapeDemo and = new VertexLabelAsShapeDemo();
        //WorldMapGraphDemo and = new WorldMapGraphDemo();
        //MultiViewDemo and = new MultiViewDemo();
        //importante
        //MinimumSpanningTreeDemo and = new MinimumSpanningTreeDemo();
        //estudo
        PluggableRendererDemo and = new PluggableRendererDemo();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(and);

        and.init();
        and.start();
        frame.pack();
        frame.setVisible(true);
    }
}
