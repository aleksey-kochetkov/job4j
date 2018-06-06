package coffeemachine;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CoffeemachineTest {

    @Test
    public void when35Of50ThenTenFive() {
        Coffeemachine coffeemachine = new Coffeemachine();
        int[] expect = {10, 5};
        assertThat(coffeemachine.changes(50, 35), is(expect));
    }

    @Test
    public void when37Of100Then6TensTwoOne() {
        Coffeemachine coffeemachine = new Coffeemachine();
        int[] expect = {10, 10, 10, 10, 10, 10, 2, 1};
        assertThat(coffeemachine.changes(100, 37), is(expect));
    }
}
