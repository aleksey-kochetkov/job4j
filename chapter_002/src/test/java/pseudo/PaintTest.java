package pseudo;

import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream outSave = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(out.toString(), is(new StringBuilder().
                append("+---+").append(System.lineSeparator()).
                append("|   |").append(System.lineSeparator()).
                append("+---+").toString()
        ));
        System.setOut(outSave);
    }
}
