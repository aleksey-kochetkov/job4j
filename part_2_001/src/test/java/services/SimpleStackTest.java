package services;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleStackTest {

    @Test
    public void whenPush() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("one");
        stack.push("two");
        assertThat(stack.pop(), is("two"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExcessiveRemove() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.pop();
    }
}
