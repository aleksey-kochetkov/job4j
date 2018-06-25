package services;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void whenIterateThenLastElement() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        set.add(1);
        int result = 0;
        for (Integer i : set) {
            result = i;
        }
        assertEquals(1, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveIterate() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationWhileIterate() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(0);
        set.add(1);
        for (Integer i : set) {
            set.add(2);
        }
    }
}
