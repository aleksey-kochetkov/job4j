package parser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

/**
 * Разбор скобок
 * @author Aleksey Kochetkov
 */
public class BracketsParser {
    private String openSet;
    private String closeSet;

    /**
     * Набор скобок по умолчанию.
     */
    public BracketsParser() {
        this.openSet = "([{<";
        this.closeSet = ")]}>";
    }

    /**
     * Произвольные пары символов.
     * @param openSet строка открывающих скобок
     * @param closeSet строка соответствующих закрывающих скобок
     */
    public BracketsParser(String openSet, String closeSet) {
        this.openSet = openSet;
        this.closeSet = closeSet;
    }

    public List<BracketsPair> parse(String input) throws ParseException {
        Deque<BracketsPair> stack = new LinkedList<>();
        List<BracketsPair> list = new ArrayList<>();
        for (int index = 0; index < input.length(); index++) {
            char c = input.charAt(index);
            int i = this.openSet.indexOf(c);
            if (i > -1) {
                BracketsPair pair = new BracketsPair(this.openSet.charAt(i), this.closeSet.charAt(i));
                pair.setOpenPosition(index);
                stack.push(pair);
                list.add(pair);
            } else {
                i = this.closeSet.indexOf(c);
                if (i > -1) {
                    if (stack.isEmpty() || stack.peek().getCloseChar() != c) {
                        throw new ParseException("Invalid string", i);
                    }
                    stack.pop().setClosePosition(index);
                }
            }
        }
        if (!stack.isEmpty()) {
            throw new ParseException("Invalid string", input.length() - 1);
        }
        return list;
    }
}
