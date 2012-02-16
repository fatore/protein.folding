
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class PrefuseTest {

    public static void main(String[] argv) {

        //grafo
        Graph graph = null;

        //ler dados e jogar em graph
        try {
            GraphMLReader gmlr = new GraphMLReader();
            graph = gmlr.readGraph("/home/fm/ic/data/grafos/min-100-prefuse.xml");
        } catch (DataIOException ex) {
            Logger.getLogger(PrefuseTest.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error loading graph, verify file path.\nExiting...");
            System.exit(1);
        }

        //cria uma abstracao para o grafo
        Visualization vis = new Visualization();
        vis.add("graph", graph);

        //configuracao da factory
        DefaultRendererFactory rf = new DefaultRendererFactory();

        ShapeRenderer sr = new ShapeRenderer(3);
        rf.setDefaultRenderer(sr);

        //LabelRenderer lr = new LabelRenderer("key");
        //lr.setRoundedCorner(8, 8);
        //rf.setDefaultRenderer(lr);

        //EdgeRenderer er = new EdgeRenderer();
        //er.setEdgeType(Constants.EDGE_TYPE_CURVE);
        //er.setArrowType(Constants.EDGE_ARROW_FORWARD);
        //er.setArrowHeadSize(0.75, 0.75);
        //er.setDefaultLineWidth(0.75);
        //rf.setDefaultEdgeRenderer(er);

        vis.setRendererFactory(rf);


        //configuracoes de cores
        ActionList color = new ActionList();

        int[] pallete = ColorLib.getInterpolatedPalette(ColorLib.color(Color.red), ColorLib.color(Color.black));

        // use black for node
        ColorAction nodeColor = new ColorAction("graph.nodes",VisualItem.FILLCOLOR, ColorLib.gray(0));
        //DataColorAction nodeColor = new DataColorAction("graph.nodes", "inc", Constants.NUMERICAL, VisualItem.FILLCOLOR, pallete);
        ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
        ColorAction textbox = new ColorAction("graph.nodes", VisualItem.STROKECOLOR, ColorLib.gray(0));
        // use light grey for edges
        ColorAction edges = new ColorAction("graph.edges",VisualItem.STROKECOLOR, ColorLib.gray(0));
        //DataColorAction edges = new DataColorAction("graph.edges", "weight", Constants.NUMERICAL, VisualItem.STROKECOLOR, pallete);
        //DataColorAction arrow = new DataColorAction("graph.edges", "weight", Constants.NUMERICAL, VisualItem.FILLCOLOR, pallete);


        color.add(nodeColor);
        color.add(text);
        //color.add(textbox);
        color.add(edges);
        //color.add(arrow);

        //definir acoes do layout (animacao)
        ActionList layout = new ActionList(Activity.INFINITY);
        //ActionList layout = new ActionList(5000);

        ForceDirectedLayout fl = new ForceDirectedLayout("graph");
        RepaintAction ra = new RepaintAction();

        layout.add(fl);
        layout.add(ra);

        //adicionar as acoes a visualizacao
        vis.putAction("color", color);
        vis.putAction("layout", layout);

        Display display = new Display(vis);

        display.setSize(1024, 860);



        //cria um frame para o display rodar
        display.addControlListener(new DragControl());
        display.addControlListener(new PanControl());
        display.addControlListener(new ZoomControl());
        display.addControlListener(new WheelZoomControl());

        JFrame frame = new JFrame("Prefuse Test");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.pack();
        frame.setVisible(true);

        //rodar as listas de acoes
        vis.run("color");
        vis.run("layout");

    }
}
