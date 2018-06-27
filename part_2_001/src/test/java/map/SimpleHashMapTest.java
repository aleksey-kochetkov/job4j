package map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleHashMapTest {

    @Test
    public void whenInsert() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first value");
        map.insert(2, "second value");
        assertEquals("second value", map.get(2));
    }

    @Test
    public void whenRemove() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("one", "first value");
        assertTrue(map.remove("one"));
        assertNull(map.get("one"));
    }

    @Test
    public void whenRemoveNonexistentKey() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first value");
        map.insert(2, "second value");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenRemoveNonexistentKeyAndBasketIsEmpty() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first value");
        map.insert(2, "second value");
        map.insert(3, "third value");
        assertFalse(map.remove(4));
    }

    @Test
    public void whenSameHashCodeThenNoInsert() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first value");
        map.insert(3, "second value");
        assertNull(map.get(3));
    }

    @Test
    public void whenIterate() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "zero value");
        map.insert(1, "first value");
        map.insert(2, "second value");
        Integer result = null;
        for (Integer key : map) {
            result = key;
        }
        assertThat(result, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveIterate() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "zero value");
        map.insert(1, "first value");
        map.insert(2, "second value");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyWhileIterateThenConcurrentModEx() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "zero value");
        map.insert(1, "first value");
        map.insert(2, "second value");
        for (Integer key : map) {
            map.remove(1);
        }
    }
}
