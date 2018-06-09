package ru.job4j.list;

public class User {
    private static int nextId = 0;
    private int id;
    private String name;
    private String city;

    public User(String name, String city) {
        this.id = nextId++;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
