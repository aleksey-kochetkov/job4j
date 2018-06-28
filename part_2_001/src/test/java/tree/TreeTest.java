package tree;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Aleksey Kochetkov
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when2ElFindNotExistsThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterate() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(4, 8);
        tree.add(1, 9);
        int i = 0;
        for (Integer value : tree) {
            assertThat(value, is(++i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveIterateThenNoSuchElementException() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyWhileIterateThenException() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        for (Integer value : tree) {
            tree.add(3, 4);
        }
    }

    @Test
    public void whenAddDoubleChildrenThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(1, 2));
    }

    @Test
    public void whenAddWithAbsentParentThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(3, 4));
    }
}