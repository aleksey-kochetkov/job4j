package tracker;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ValidateInputTest {
    private final PrintStream outSave = System.out;
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.outSave);
    }

    @Test
    public void whenInvalidInput() {
        String[] input = {"dfj", "6"};
        ValidateInput validator = new ValidateInput(new StubInput(input));
        validator.ask("Enter : ", Arrays.asList(1));
        String expect = String.format("Ввод некорректен, повторите%n");
        assertThat(this.mem.toString(), is(expect));
    }
}
