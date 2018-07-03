package dom;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TextUITest {
    private Processor processor;
    private PrintStream outSave;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void before() {
        this.outSave = System.out;
        System.setOut(new PrintStream(this.out));
    }

    @Test
    public void whenSample() {
        String[] input = {"1", "LKODL", "a", "b", "0.49", "2",
                          "1", "LKODL", "a", "b", "0.50", "3",
                          "1", "LKODL", "a", "b", "0.51", "2",
                          "1", "LKODL", "d", "b", "0.51", "2",
                          "1", "LKODL", "a", "a", "0.51", "2",
                          "1", "LKODL", "a", "a", "0.52", "1",
                          "1", "LKODL", "a", "a", "0.51", "2",
                          "1", "LKODL", "a", "b", "0.49", "3",
                          "2", "LKODL", "3"};
        new TextUI(new StubInput(input)).start();
        System.setOut(this.outSave);
        Scanner scanner = new Scanner(this.out.toString());
        scanner.useDelimiter(System.lineSeparator());
        String result;
        do {
            result = scanner.next();
        } while (!"--- Просмотр биржевого стакана ---".equals(result));
        assertThat(scanner.next(), is("Продажа Цена   Покупка"));
        assertThat(scanner.next(), is("      0   0.52       1"));
        assertThat(scanner.next(), is("      0   0.51       4"));
        assertThat(scanner.next(), is("      3   0.50       0"));
        assertThat(scanner.next(), is("      5   0.49       0"));
    }
}
