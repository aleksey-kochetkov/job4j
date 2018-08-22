package tracker;

import java.util.Scanner;
import java.util.List;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exists = false;
        for (int i : range) {
            if (key == i) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}
