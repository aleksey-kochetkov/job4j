package urlparse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PagePositions {
    private static final Pattern JSP = Pattern.compile("java *script", Pattern.CASE_INSENSITIVE);
    private static final Pattern JPT = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
    private boolean floorFound;

    public List<Position> parse(Document doc, Date floorDate) {
        Elements trs = doc.select("table.forumTable").first()
                                          .children().first().children();
        int i = 0;
        List<Position> positions = new ArrayList<>();
        for (Element tr : trs) {
// пропустить закреплённые сообщения форума
            if (i++ <= 3) {
                continue;
            }
            String description = tr.children().get(1).children().first().text();
            String dateStr = tr.children().get(5).text();
            Date date;
            try {
                date = new ForumDateFormat().parse(dateStr);
            } catch (ParseException exception) {
                throw new RuntimeException(exception);
            }
            if (floorDate.compareTo(date) > 0) {
                this.floorFound = true;
                break;
            }
            String tmp = JSP.matcher(description).replaceAll("");
            if (JPT.matcher(tmp).find()) {
                positions.add(new Position(description, date));
            }
        }
        return positions;
    }

    public boolean floorFound() {
        return this.floorFound;
    }
}
