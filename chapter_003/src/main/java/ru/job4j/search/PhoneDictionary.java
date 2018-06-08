package ru.job4j.search;

import java.util.List;
import java.util.ArrayList;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, которые содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person p : this.persons) {
            if (p.getName().contains(key)
                || p.getSurname().contains(key)
                || p.getPhone().contains(key)
                || p.getAddress().contains(key)) {
                result.add(p);
            }
        }
        return result;
    }
}
