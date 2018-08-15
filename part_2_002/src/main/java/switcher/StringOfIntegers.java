package switcher;

public class StringOfIntegers {
    private StringBuilder data = new StringBuilder();

    public void append(int i) {
        this.data.append(Integer.toString(i));
    }

    public String get() {
        return this.data.toString();
    }
}
