package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BinarySearchTreeTest {

    @Test
    public void whenAdd() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(8);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.add(9);
        tree.add(9);
        tree.add(10);
        int[] result = {1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 9, 9, 10};
        int i = 0;
        for (Integer element : tree) {
            assertEquals(result[i++], element.intValue());
        }
    }

    @Test
    public void whenEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (Integer element : tree) {
            fail();
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExcessiveIterateThenNoSuchElementException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(4);
        Iterator<Integer> iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}
