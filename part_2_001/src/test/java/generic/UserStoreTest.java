package generic;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {

    @Test
    public void whenAdd() {
        UserStore store = new UserStore();
        User one = new User("one");
        store.add(one);
        User two = new User("two");
        store.add(two);
        assertThat(store.findById("one"), is(one));
        assertThat(store.findById("two"), is(two));
    }

    @Test
    public void whenDelete() {
        UserStore store = new UserStore();
        User one = new User("one");
        store.add(one);
        User two = new User("two");
        store.add(two);
        assertTrue(store.delete("two"));
        assertNull(store.findById("two"));
    }

    @Test
    public void whenDeleteOnEmpty() {
        UserStore store = new UserStore();
        assertFalse(store.delete("two"));
    }

    @Test
    public void whenReplace() {
        UserStore store = new UserStore();
        User one = new User("one");
        store.add(one);
        User two = new User("two");
        store.add(two);
        User three = new User("three");
        assertTrue(store.replace("two", three));
        assertNull(store.findById("two"));
    }

    @Test
    public void whenReplaceOnEmpty() {
        UserStore store = new UserStore();
        User three = new User("three");
        assertFalse(store.replace("two", three));
    }
}
