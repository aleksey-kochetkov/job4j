package service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.Reader;
import java.io.Writer;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class AbuseServiceTest {

    @Test
    public void whenSimple() throws IOException {
        String[] input = {"АПОЖ", "ОЙД", "НОС"};
        String result;
        try (Reader in = new CharArrayReader(
                       "Апожалуйста пройдёмте на носках!".toCharArray());
             Writer out = new CharArrayWriter()) {
            new AbuseService().dropAbuses(in, out, input);
            out.flush();
            result = out.toString();
        }
        assertEquals(result, "алуйста прёмте на ках!");
    }

    @Test
    public void whenFantasy() throws IOException {
        String[] input = {"К", "АК", "РАК", "АРАК", "МАРАК"};
        String result;
        try (Reader in = new CharArrayReader(
                        "марак0арак1рак2ак3к4к5к6к7к8к9к".toCharArray());
             Writer out = new CharArrayWriter()) {
            new AbuseService().dropAbuses(in, out, input);
            out.flush();
            result = out.toString();
        }
        assertEquals(result, "0123456789");
    }
}
