package profession;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class TeacherTest {

    @Test
    public void whenNewThenTestName() {
        Teacher teacher = new Teacher("Marivanna");
        String expect = "Marivanna";
        assertThat(teacher.getName(), is(expect));
    }

    /**
     * Просто пройти созданный код.
     */
    @Test
    public void whenNewThenTestMethodsAreCallable() {
        Teacher teacher = new Teacher("Marivanna");
        teacher.teach(new Student("Waldemar"));
    }
}
