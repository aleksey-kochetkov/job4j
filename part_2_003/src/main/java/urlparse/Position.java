package urlparse;

import java.util.Date;

public class Position {
    private String description;
    private Date date;

    public Position(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("Position{description=%s, date=%s}",
                                 this.description, this.date.toString());
    }
}
