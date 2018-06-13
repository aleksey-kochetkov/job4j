package ru.job4j.reference;

import java.util.List;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

/**
 * Справочник подразделений (сортировка).
 * @author Aleksey Kochetkov
 */
public class Reference {
    public static final char SEPARATOR = '\\';
    Map<String, NavigableSet<String>> departments = new HashMap<>();

    /**
     * Задать входные данные, массив.
     * @param departments коды подразделений
     */
    public void addAll(String[] departments) {
        for (String d : departments) {
            String cur = d;
            String sup;
            do {
                int i = cur.lastIndexOf(SEPARATOR);
                if (i > -1) {
                    sup = cur.substring(0, i);
                } else {
                    sup = null;
                }
                this.departments.putIfAbsent(sup, new TreeSet<>());
                this.departments.get(sup).add(cur);
                cur = sup;
            } while (sup != null);
        }
    }

    /**
     * Получить массив всех подразделений, отсортированный в естественном порядке с учётом иерархии.
     * Так как в задаче указана ограниченная глубина влженности
     * (департамент-служба-отдел), то во избежание рекурсии проход
     * иерархии организован без универсальности.
     * @param descending сортировка результата по убыванию
     * @return отсортированный массив подразделений
     */
    public String[] toArray(boolean descending) {
        List<String> result = new ArrayList<>();
        NavigableSet<String> supers = this.departments.get(null);
        if (descending) {
            supers = supers.descendingSet();
        }
        for (String sup : supers) {
            result.add(sup);
            NavigableSet<String> services = this.departments.get(sup);
            if (services != null) {
                if (descending) {
                    services = services.descendingSet();
                }
                for (String service : services) {
                    result.add(service);
                    NavigableSet<String> divisions = this.departments.get(service);
                    if (divisions != null) {
                        if (descending) {
                            divisions = divisions.descendingSet();
                        }
                        result.addAll(divisions);
                    }
                }
            }
        }
        return result.toArray(new String[0]);
    }
}
