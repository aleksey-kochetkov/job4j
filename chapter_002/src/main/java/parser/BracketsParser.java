package parser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.ParseException;

/**
 * Разбор скобок
 * @author Aleksey Kochetkov
 */
public class BracketsParser {
    /**
     * Набор скобок для поиска.
     */
    private List<BracketsPair> pairs = new ArrayList<>();

    /**
     * Набор скобок по умолчанию.
     */
    public BracketsParser() {
        this.pairs.add(new BracketsPair('(', ')'));
        this.pairs.add(new BracketsPair('[', ']'));
        this.pairs.add(new BracketsPair('{', '}'));
        this.pairs.add(new BracketsPair('<', '>'));
    }

    /**
     * Произвольные пары символов.
     * @param pairs массив пар скобок
     */
    public BracketsParser(BracketsPair[] pairs) {
        this.addAll(pairs);
    }

    /**
     * Дополнить существующий набор скобок.
     * @param pair пара скобок
     */
    public void add(BracketsPair pair) {
        this.pairs.add(pair);
    }

    /**
     * Дополнить существующий набор скобок.
     * @param pairs массив пар скобок
     */
    public void addAll(BracketsPair[] pairs) {
        this.pairs.addAll(Arrays.asList(pairs));
    }

    private int indexOfOpen(char c) {
        int result = -1;
        for (int i = 0; i < this.pairs.size(); i++) {
            if (this.pairs.get(i).getOpenChar() == c) {
                result = i;
                break;
            }
        }
        return result;
    }

    private int indexOfClose(char c) {
        int result = -1;
        for (int i = 0; i < this.pairs.size(); i++) {
            if (this.pairs.get(i).getCloseChar() == c) {
                result = i;
                break;
            }
        }
        return result;
    }

    public List<BracketsPair> parse(String input) throws ParseException {
        Deque<BracketsPair> stack = new LinkedList<>();
        List<BracketsPair> list = new ArrayList<>();
        for (int index = 0; index < input.length(); index++) {
            char c = input.charAt(index);
            int i = this.indexOfOpen(c);
            if (i > -1) {
                BracketsPair pair = new BracketsPair(this.pairs.get(i));
                pair.setOpenPosition(index);
                stack.push(pair);
                list.add(pair);
            } else {
                i = this.indexOfClose(c);
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
