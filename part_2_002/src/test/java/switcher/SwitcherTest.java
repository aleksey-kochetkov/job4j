package switcher;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SwitcherTest {
    @Test
    public void whenRun() {
        Switcher switcher = new Switcher();
        switcher.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        switcher.stop();
        String expect = "111111111122222222221111111111";
        assertThat(switcher.getResult().substring(0, 30), is(expect));
    }
}
