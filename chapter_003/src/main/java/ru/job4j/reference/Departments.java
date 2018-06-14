package ru.job4j.reference;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Departments {
    public static final char SEPARATOR = '\\';
    private List<String> departments;

    public Departments(List<String> departments) {
        this.departments = departments;
    }

    public List<String> sortAscending() {
        Collections.sort(this.departments);
        return this.departments;
    }

    public List<String> sortDescending() {
        this.departments.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                String[] parts1 = o1.split(Pattern.quote(Character.toString(SEPARATOR)));
                String[] parts2 = o2.split(Pattern.quote(Character.toString(SEPARATOR)));
                int i = 0;
                do {
                    result = parts2[i].compareTo(parts1[i]);
                    if (result == 0 && (parts1.length == i + 1 || parts2.length == i + 1)) {
                        result = parts1.length - parts2.length;
                        break;
                    }
                    i++;
                } while (result == 0);
                return result;
            }
        });
        return this.departments;
    }
}
