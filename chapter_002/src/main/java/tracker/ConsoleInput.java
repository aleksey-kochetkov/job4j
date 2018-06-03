package tracker;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.io.Console;
import java.lang.reflect.Method;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in, "cp866");

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
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
