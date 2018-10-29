package e;

import java.util.Date;
import java.util.Calendar;

public class Period {
    private Date begin;
    private Date end;

    public Period decode(String value) {
        if ("d".equals(value)) {
            this.calcBegin(Calendar.DATE, -1);
        } else if ("3d".equals(value)) {
            this.calcBegin(Calendar.DATE, -3);
        } else if ("m".equals(value)) {
            this.calcBegin(Calendar.MONTH, -1);
        } else {
            this.end = null;
            this.begin = null;
        }
        return this;
    }

    private void calcBegin(int field, int amount) {
        this.end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.end);
        calendar.add(field, amount);
        this.begin = calendar.getTime();
    }

    public Date getBegin() {
        return this.begin;
    }

    public Date getEnd() {
        return this.end;
    }
}
