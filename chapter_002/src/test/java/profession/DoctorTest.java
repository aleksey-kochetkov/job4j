package profession;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class DoctorTest {

    @Test
    public void whenNewThenTestName() {
        Doctor doctor = new Doctor("House");
        String expect = "House";
        assertThat(doctor.getName(), is(expect));
    }

    /**
     * Просто пройти созданный код.
     */
    @Test
    public void whenNewThenTestMethodsAreCallable() {
        Doctor doctor = new Doctor("House");
        Patient patient = new Patient("");
        doctor.heal(patient);
    }
}
