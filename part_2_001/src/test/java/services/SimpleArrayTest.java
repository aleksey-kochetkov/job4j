package services;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    @Test
    public void whenAdd() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.add("one");
        assertThat(iterable.get(0), is("one"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOutOfBoundsThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(1);
        iterable.add("one");
        iterable.add("two");
    }

    @Test
    public void whenSet() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.add("one");
        iterable.set(0, "two");
        assertThat(iterable.get(0), is("two"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOutOfBoundsThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.set(0, "one");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetNegativeThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.set(-8, "one");
    }

    @Test
    public void whenRemove() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.add("one");
        iterable.add("two");
        iterable.remove(0);
        assertThat(iterable.get(0), is("two"));
    }

    @Test
    public void whenRemoveLast() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.add("one");
        iterable.add("two");
        iterable.remove(1);
        iterable.add("three");
        assertThat(iterable.get(1), is("three"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveOutOfBoundsThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.remove(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveNegativeThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.remove(-8);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBoundsThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.get(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetNegativeThenIndexOutOfBoundsException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.get(-8);
    }

    @Test
    public void whenIterate() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.add("one");
        iterable.add("two");
        String result = null;
        for (String s : iterable) {
            result = s;
        }
        assertThat(result, is("two"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveNextThenNoSuchElementException() {
        SimpleArray<String> iterable = new SimpleArray<>(16);
        iterable.iterator().next();
    }
}
