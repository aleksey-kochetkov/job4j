package xml;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.StringWriter;
import java.io.PrintWriter;

public class UI {
    static {
        System.setProperty("log4j.configuration", "file:log4j.configuration");
    }
    private static Logger logger = Logger.getLogger(StoreSQL.class);
    private final Config config = new Config("jdbc:sqlite:magnit");
    private final File xmlFile = new File("magnit.xml");
    private final File transformedFile = new File("transformed.xml");
    private final String stylesheet = "/stylesheet.xsl";

    public static void main(String[] args) {
        new UI().run();
    }

    private void run() {
        try {
            new StoreSQL(this.config).generate(19);
            new StoreXML(this.config, this.xmlFile).save();
            new ConvertXSLT(this.xmlFile, this.transformedFile, this.stylesheet).convert();
            new ParseSAX(this.transformedFile).run();
        } catch (Exception exception) {
            StringWriter out = new StringWriter();
            exception.printStackTrace(new PrintWriter(out));
            logger.error(out);
            throw new RuntimeException(exception);
        }
    }
}
