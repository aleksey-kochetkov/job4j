package services;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SimpleHashSetTest {

    @Test
    public void whenAdd() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        assertTrue(set.add(0));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNull() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(null);
    }

    @Test
    public void whenAddSameThenFalse() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        assertFalse(set.add(0));
    }

    @Test
    public void whenContains() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        assertTrue(set.contains(0));
    }

    @Test
    public void whenRemove() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        assertTrue(set.remove(0));
    }

    @Test
    public void whenRemoveAbsentThenFalse() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        set.add(1);
        set.add(2);
        assertFalse(set.remove(3));
    }

    @Test
    public void whenResize() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        assertTrue(set.add(1));
    }
}
