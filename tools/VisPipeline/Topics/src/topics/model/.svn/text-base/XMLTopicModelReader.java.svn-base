/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package topics.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class XMLTopicModelReader extends DefaultHandler {

    public void read(TopicProjectionModel model, String filename) throws IOException {
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
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equalsIgnoreCase(SCALAR)) {
            String name = attributes.getValue(NAME);
            String value = attributes.getValue(VALUE);

            if (name != null && value != null) {
                Scalar s = model.addScalar(name);
                tmpinstance.setScalarValue(s, Float.parseFloat(value));
            }
        } else if (qName.equalsIgnoreCase(INSTANCE)) {
            String id = attributes.getValue(ID);

            if (Util.isParsableToInt(id)) {
                tmpinstance = new TopicProjectionInstance(model, Integer.parseInt(id));
                tmpinstance.setLabel(Integer.toString(tmpinstance.getId()));
            } else {
                tmpinstance = new TopicProjectionInstance(model, Util.convertToInt(id));
                tmpinstance.setLabel(Integer.toString(tmpinstance.getId()));
            }
        } else if (qName.equalsIgnoreCase(X_COORDINATE)) {
            String value = attributes.getValue(VALUE);
            if (value != null) {
                tmpinstance.setX(Float.parseFloat(value));
            }
        } else if (qName.equalsIgnoreCase(Y_COORDINATE)) {
            String value = attributes.getValue(VALUE);
            if (value != null) {
                tmpinstance.setY(Float.parseFloat(value));
            }
        } else if (qName.equalsIgnoreCase(LABEL)) {
            String value = attributes.getValue(VALUE);

            if (value != null) {
                tmpinstance.setLabel(value);
            }
        }
    }

    private TopicProjectionInstance tmpinstance;
    private TopicProjectionModel model;
    private static final String INSTANCE = "instance";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String X_COORDINATE = "x-coordinate";
    private static final String Y_COORDINATE = "y-coordinate";
    private static final String LABEL = "label";
    private static final String VALUE = "value";
    private static final String SCALAR = "scalar";
}
