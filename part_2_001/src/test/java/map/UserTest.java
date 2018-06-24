package map;

import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserTest {
    @Test
    public void whenConstructor() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1985, Calendar.JULY, 1);
        User user = new User("One", birthday, 0);
        assertThat(user.toString(),
              is("User{name=\"One\", birthday={1985,6,1}, children=0}"));
    }
}
