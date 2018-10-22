package e;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StorageTest {
    @Test
    public void whenCreate() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
        Storage storage = context.getBean(MemoryStorage.class);
        storage.createUser(new User("one"));
        storage.createUser(new User("two"));
        assertEquals(2, storage.countUsers());
    }
}
