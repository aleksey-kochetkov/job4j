package tracker;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.io.Console;
import java.lang.reflect.Method;

public class ConsoleInput implements Input {
    private Scanner scanner = this.getScanner();

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * В Windows cmd есть проблема с кодировкой.
     * Специфическая бработка этого случая.
     * @return new Scanner object
     */
    private Scanner getScanner() {
        Scanner result = null;
        try {
            Class<Console> clazz = Console.class;
            Method method = clazz.getDeclaredMethod("encoding");
            method.setAccessible(true);
            String encoding = (String) method.invoke(null);
            if ("cp866".equals(encoding)) {
                result = new Scanner(System.in, "cp866");
            }
        } catch (NoSuchMethodException | IllegalAccessException
                 | InvocationTargetException exception) {
        }
        if (result == null) {
            result = new Scanner(System.in);
        }
        return result;
    }
}
