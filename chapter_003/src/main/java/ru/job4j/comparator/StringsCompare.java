package ru.job4j.comparator;

import java.util.Comparator;
import java.util.List;

public class StringsCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result;
        int i = 0;
        do {
            if (left.length() == i || right.length() == i) {
                result = left.length() - right.length();
                break;
            }
            result = left.charAt(i) - right.charAt(i);
            i++;
        } while (result == 0);
        return result;
    }
}