package multithreads;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ParallelSearchTest {

    @Test
    public void when() {
        String[] inputExts = {"java"};
        ParallelSearch searcher = new ParallelSearch(
           "D:\\projects\\job4j", "ArrayList", Arrays.asList(inputExts));
        searcher.init();
        assertThat(searcher.result().size(), greaterThan(0));
    }
}
