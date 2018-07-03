package dom;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner in = new Scanner(System.in);

    @Override
    public String prompt(String text) {
        System.out.print(text);
        return this.in.next();
    }
}
