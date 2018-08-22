package tracker;

import java.util.List;

public class ValidateInput implements Input {
    private final Input input;

    ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int result;
        do {
            try {
                result = this.input.ask(question, range);
                break;
            } catch (MenuOutException exception) {
                System.out.println("Значение не из меню, повторите");
            } catch (NumberFormatException exception) {
                System.out.println("Ввод некорректен, повторите");
            }
        } while (true);
        return result;
    }
}
