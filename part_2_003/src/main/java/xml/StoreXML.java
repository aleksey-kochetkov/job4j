package xml;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

public class StoreXML {
    private Config source;
    private File target;
    private XmlRoot xmlRoot = new XmlRoot();

    public StoreXML(Config source, File target) {
        this.target = target;
        this.source = source;
    }

    private void selectFromDB() {
        try (Connection connection = DriverManager.getConnection(this.source.getUrl())) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                this.xmlRoot.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void save() {
        selectFromDB();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlRoot.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this.xmlRoot, this.target);
        } catch (JAXBException exception) {
            throw new RuntimeException(exception);
        }
    }

    @XmlRootElement(name = "entries")
    static class XmlRoot {
        @XmlElement(name = "entry")
        List<Entry> entries = new ArrayList<>();

        void add(Entry entry) {
            this.entries.add(entry);
        }
    }

    @XmlRootElement
    static class Entry {
        @XmlElement
        private int field;

        Entry() {
        }

        Entry(int field) {
            this.field = field;
        }
    }
}
