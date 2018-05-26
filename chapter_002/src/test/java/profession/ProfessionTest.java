package profession;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ProfessionTest {
    @Test
    public void whenNewThenTestName() {
        Profession profession = new Profession("Person");
        String expect = "Person";
        assertThat(profession.getName(), is(expect));
    }
}
