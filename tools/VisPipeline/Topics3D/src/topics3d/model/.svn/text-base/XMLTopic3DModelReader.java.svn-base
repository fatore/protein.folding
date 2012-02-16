/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics3d.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import projection.model.Scalar;
import visualizationbasics.util.Util;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class XMLTopic3DModelReader extends DefaultHandler {

    public void read(TopicProjection3DModel model, String filename) throws IOException {
        this.model = model;

        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            InputSource in = new InputSource(new InputStreamReader(new FileInputStream(filename), "ISO-8859-1"));
            SAXParser sp = spf.newSAXParser();
            sp.parse(in, this);
        } catch (SAXException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase(INSTANCE)) {
            if (Util.isParsableToInt(tempid)) {
                tmpinstance = new TopicProjection3DInstance(model, Integer.parseInt(tempid), tempx, tempy, tempz);
            } else {
                tmpinstance = new TopicProjection3DInstance(model, Util.convertToInt(tempid), tempx, tempz, tempz);
            }
            tmpinstance.setLabel(templabel);
            for (Map.Entry<String, String> e: tempscalars.entrySet()) {
                Scalar s = model.addScalar(e.getKey());
                tmpinstance.setScalarValue(s, Float.parseFloat(e.getValue()));
            }
            model.addInstance(tmpinstance);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equalsIgnoreCase(SCALAR)) {
            String name = attributes.getValue(NAME);
            String value = attributes.getValue(VALUE);

            if (name != null && value != null) {
                tempscalars.put(name, value);
            }
        } else if (qName.equalsIgnoreCase(INSTANCE)) {
            tempid = attributes.getValue(ID);

        } else if (qName.equalsIgnoreCase(X_COORDINATE)) {
            String value = attributes.getValue(VALUE);
            if (value != null) {
                tempx = Float.parseFloat(value);
            }
        } else if (qName.equalsIgnoreCase(Y_COORDINATE)) {
            String value = attributes.getValue(VALUE);
            if (value != null) {
                tempy = Float.parseFloat(value);
            }
        } else if (qName.equalsIgnoreCase(Z_COORDINATE)) {
            String value = attributes.getValue(VALUE);
            if (value != null) {
                tempz = Float.parseFloat(value);
            }
        } else if (qName.equalsIgnoreCase(LABEL)) {
            String value = attributes.getValue(VALUE);

            if (value != null) {
                templabel = value;
            }
        }
    }

    private Map<String, String> tempscalars = new HashMap<String, String>();
    private String tempid = "";
    private String templabel = "";
    private float tempx = 0.f;
    private float tempy = 0.f;
    private float tempz = 0.f;
    private TopicProjection3DInstance tmpinstance;
    private TopicProjection3DModel model;
    private static final String INSTANCE = "instance";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String X_COORDINATE = "x-coordinate";
    private static final String Y_COORDINATE = "y-coordinate";
    private static final String Z_COORDINATE = "z-coordinate";
    private static final String LABEL = "label";
    private static final String VALUE = "value";
    private static final String SCALAR = "scalar";
}
