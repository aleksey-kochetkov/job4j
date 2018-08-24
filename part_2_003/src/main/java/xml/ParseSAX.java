package xml;

import java.io.File;
import java.io.IOException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ParseSAX extends DefaultHandler {
    private final File source;
    private int sum;

    public ParseSAX(File source) {
        this.source = source;
    }

    public void run() {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(this.source, this);
            System.out.format("sum:%d%n", this.sum);
        } catch (ParserConfigurationException
                 | SAXException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("entry".equals(qName)) {
            this.sum += Integer.parseInt(attributes.getValue("field"));
        }
    }
}
