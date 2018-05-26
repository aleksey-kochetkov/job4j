package profession;

public class Doctor extends Profession {

    public Doctor(String name) {
        super(name);
    }

    public Diagnosis heal(Patient patient) {
        return new Diagnosis();
    }
}
