package e;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Конфигурирование контекста через
 * 1. spring-context.xml.   <---
 * 2. annotations.
 * 3. (java code) @Configuration class.
 */
public class UserStorageTest {
    @Test
    public void whenCreateThenSize() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage users = context.getBean(UserStorage.class);
        users.create(new User());
        assertEquals(1, users.size());
    }
}
