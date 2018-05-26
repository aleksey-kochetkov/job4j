package profession;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class EngineerTest {

    @Test
    public void whenNewThenTestName() {
        Engineer engineer = new Engineer("Lopez");
        String expect = "Lopez";
        assertThat(engineer.getName(), is(expect));
    }

    /**
     * Просто пройти созданный код.
     */
    @Test
    public void whenNewThenTestMethodsAreCallable() {
        Engineer engineer = new Engineer("Lopez");
        engineer.build(new House());
    }
}
