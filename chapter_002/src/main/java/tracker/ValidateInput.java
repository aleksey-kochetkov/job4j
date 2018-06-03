package tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        int result;
        do {
            try {
                result = super.ask(question, range);
                break;
            } catch (MenuOutException exception) {
                System.out.println("Значение не из меню, повторите");
            } catch (NumberFormatException exception) {
                System.out.println("Ввод некорректен, повоторите");
            }
        } while (true);
        return result;
    }
}
