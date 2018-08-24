package xml;

import java.io.File;

public class UI {
    private final Config config = new Config("jdbc:sqlite:magnit");
    private final File xmlFile = new File("magnit.xml");
    private final File transformedFile = new File("transformed.xml");
    private final String stylesheet = "/stylesheet.xsl";

    public static void main(String[] args) {
        new UI().run();
    }

    private void run() {
        new StoreSQL(this.config).generate(18);
        new StoreXML(this.config, this.xmlFile).save();
        new ConvertXSLT(this.xmlFile, this.transformedFile, this.stylesheet).convert();
        new ParseSAX(this.transformedFile).run();
    }
}
