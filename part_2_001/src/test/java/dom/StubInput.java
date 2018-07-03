package dom;

public class StubInput implements Input {
    private String[] values;
    private int next;

    StubInput(String[] values) {
        this.values = values;
    }

    @Override
    public String prompt(String text) {
        return this.values[this.next++];
    }
}
