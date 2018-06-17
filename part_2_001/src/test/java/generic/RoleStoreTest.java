package generic;

import org.junit.Test;

public class RoleStoreTest {
    @Test
    public void whenConstructors() {
        new RoleStore().add(new Role("one"));
    }
}
