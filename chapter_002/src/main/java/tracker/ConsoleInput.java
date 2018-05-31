package tracker;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.io.Console;
import java.lang.reflect.Method;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
