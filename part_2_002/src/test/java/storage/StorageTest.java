package storage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StorageTest {

    @Test
    public void whenAdd() {
        UserStorage storage = new UserStorage();
        storage.add(new User(0, 0));
        storage.add(new User(0, 100));
        assertEquals(1, storage.size());
    }

    @Test
    public void whenFindById() {
        UserStorage storage = new UserStorage();
        User zero = new User(0, 100);
        storage.add(zero);
        assertEquals(100, storage.findById(0).getAmount());
    }

    @Test
    public void whenUpdate() {
        UserStorage storage = new UserStorage();
        User zero = new User(0, 0);
        storage.add(zero);
        storage.update(new User(0, 100));
        assertEquals(100, storage.findById(0).getAmount());
    }

    @Test
    public void whenDelete() {
        UserStorage storage = new UserStorage();
        storage.add(new User(0, 0));
        storage.delete(new User(0, 100));
        assertEquals(0, storage.size());
    }

    @Test
    public void whenTransfer() {
        UserStorage storage = new UserStorage();
        storage.add(new User(0, 0));
        storage.add(new User(1, 100));
        storage.transfer(1, 0, 30);
        assertEquals(30, storage.findById(0).getAmount());
    }
}
