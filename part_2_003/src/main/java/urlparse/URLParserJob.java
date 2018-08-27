package urlparse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class URLParserJob implements Job {
    private List<Position> positions = new ArrayList<>();
    private Date floorDate;
    private final Properties properties = URLParser.loadProperties();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try (DB db = new DB(this.properties)) {
            db.connect();
            Timestamp timestamp = db.getMaxTimestamp();
            this.setFloorDate(timestamp == null ? null : new Date(timestamp.getTime()));
            this.parsePositions();
            db.insert(this.positions);
        }
    }

    private static Properties loadProperties() {
        Properties result = new Properties();
        try {
            result.load(new FileReader(URLParser.configuration));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    private void setFloorDate(Date floorDate) {
        if (floorDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            this.floorDate = calendar.getTime();
        } else {
            this.floorDate = floorDate;
        }
    }

    private void parsePositions() {
        for (int i = 1; i < 100; i++) {
//        Authenticator.setDefault(
//                new Authenticator() {
//                    public PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(
//                                "kas65", "????????".toCharArray());
//                    }
//                }
//        );
//        Document doc = Jsoup.connect("http://sql.ru")
//                                      .proxy("tu.komus.net", 3128).post();
            Document doc;
            try {
                String url = String.format("http://sql.ru/forum/job-offers/%d", i);
                doc = Jsoup.connect(url).get();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            PagePositions pagePositions = new PagePositions();
            this.positions.addAll(pagePositions.parse(doc, this.floorDate));
            if (pagePositions.floorFound()) {
                break;
            }
        }
    }
}
