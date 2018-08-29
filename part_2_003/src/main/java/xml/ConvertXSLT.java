package xml;

import java.io.File;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;

public class ConvertXSLT {
    private File source;
    private File result;
    private String stylesheet;

    public ConvertXSLT(File source, File result, String stylesheet) {
        this.source = source;
        this.result = result;
        this.stylesheet = stylesheet;
    }

    public void convert() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(
                       this.getClass().getResourceAsStream(this.stylesheet)));
        transformer.transform(new StreamSource(this.source), new StreamResult(this.result));
    }
}
