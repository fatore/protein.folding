package sensors.proteinfoldingold;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import nu.xom.*;

/**
 *
 * @author fm
 */
public class ExperimentOutput {

    public void writeXML(ExperimentConformations ec, ExperimentConformationTransitions ect, String targetFile) {
    }

    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception {

        ExperimentConformationTransitions ect = new ExperimentConformationTransitions();

        //caminho padrao para os dados do experimento
        String filePath = "/home/fm/ic/data/Resultados/minimos.dat";

        //caminho alternativo pode ser passado como primeiro parametro
        if (args.length > 0) {
            filePath = args[0];
        }

        ect.read(filePath);

        //ect.print();

        Element root = new Element("graphml");
        Element graph = new Element("graph");

        Attribute edgedefault = new Attribute("edgedefault", "undirected");
        graph.addAttribute(edgedefault);

        root.appendChild(graph);

        Element key = new Element("key");

        Attribute at = new Attribute("id", "key");
        key.addAttribute(at);

        at = new Attribute("for", "node");
        key.addAttribute(at);

        at = new Attribute("attr.name", "key");
        key.addAttribute(at);

        at = new Attribute("attr.type", "int");
        key.addAttribute(at);

        graph.appendChild(key);

        //==================================

        key = new Element("key");

        at = new Attribute("id", "weight");
        key.addAttribute(at);

        at = new Attribute("for", "edge");
        key.addAttribute(at);

        at = new Attribute("attr.name", "weight");
        key.addAttribute(at);

        at = new Attribute("attr.type", "int");
        key.addAttribute(at);

        graph.appendChild(key);

        //==================================

        key = new Element("key");

        at = new Attribute("id", "inc");
        key.addAttribute(at);

        at = new Attribute("for", "node");
        key.addAttribute(at);

        at = new Attribute("attr.name", "inc");
        key.addAttribute(at);

        at = new Attribute("attr.type", "int");
        key.addAttribute(at);

        graph.appendChild(key);

        //==================================

        for (Node x : ect.ec.distinctConformations.values()) {

            //if (x.incidence > 0) {
                Element node = new Element("node");
                Attribute id = new Attribute("id", Integer.toString(x.key));
                node.addAttribute(id);
                graph.appendChild(node);

                Element data = new Element("data");
                Attribute d = new Attribute("key", "key");
                data.addAttribute(d);
                node.appendChild(data);

                data.appendChild(Integer.toString(x.key));

                data = new Element("data");
                d = new Attribute("key", "inc");
                data.addAttribute(d);
                node.appendChild(data);

                data.appendChild(Integer.toString(x.incidence));

            //}
        }

        for (Edge x : ect.transitions.values()) {
            if (x.weight > 10) {
                Element edge = new Element("edge");
                graph.appendChild(edge);

                Attribute source = new Attribute("source", Integer.toString(x.source));
                edge.addAttribute(source);
                Attribute target = new Attribute("target", Integer.toString(x.target));
                edge.addAttribute(target);

                Element data = new Element("data");
                Attribute d = new Attribute("key", "weight");
                data.addAttribute(d);
                edge.appendChild(data);

                data.appendChild(Integer.toString(x.weight));
            }
        }

        Document doc = new Document(root);
        //format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("/home/fm/ic/data/grafos/min-10-prefuse.xml")), doc);

    }
}
