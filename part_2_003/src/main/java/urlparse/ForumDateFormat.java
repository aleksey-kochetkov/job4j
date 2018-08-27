package urlparse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

public class ForumDateFormat {
    private static final SimpleDateFormat F = new SimpleDateFormat("dd MMM yy, HH:mm");
    private static final SimpleDateFormat TF = new SimpleDateFormat("сегодня, HH:mm");
    private static final SimpleDateFormat YF = new SimpleDateFormat("вчера, HH:mm");
    private static final Calendar T = Calendar.getInstance();
    private static final Calendar Y = Calendar.getInstance();
    static {
        Y.add(Calendar.DATE, -1);
    }

    public Date parse(String value) throws ParseException {
        Date result;
        try {
            result = F.parse(value);
        } catch (ParseException e0) {
            try {
                result = parseHuman(value, TF, T);
            } catch (ParseException e1) {
                result = parseHuman(value, YF, Y);
            }
        }
        return result;
    }

    private Date parseHuman(String value, SimpleDateFormat format,
                               Calendar calendar) throws ParseException {
        Calendar tmp = Calendar.getInstance();
        tmp.setTime(format.parse(value));
        tmp.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        tmp.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        tmp.set(Calendar.DATE, calendar.get(Calendar.DATE));
        return tmp.getTime();
    }
}
