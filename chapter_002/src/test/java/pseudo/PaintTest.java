package pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void resetOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(out.toString(), is(new StringBuilder().
                append("  +").append(System.lineSeparator()).
                append(" + +").append(System.lineSeparator()).
                append("+++++").toString()
        ));
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(out.toString(), is(new StringBuilder().
                append("+---+").append(System.lineSeparator()).
                append("|   |").append(System.lineSeparator()).
                append("+---+").toString()
        ));
    }
}
