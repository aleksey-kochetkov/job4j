package services;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SimpleLinkedTest {

    private SimpleLinked<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinked<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseDelete() {
        assertThat(list.delete(), is(3));
        assertEquals(2, list.getSize());
    }
}
