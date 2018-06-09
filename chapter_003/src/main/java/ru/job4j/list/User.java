package ru.job4j.list;

public class User implements Comparable<User> {
    private static int nextId = 0;
    private int id;
    private String name;
    private String city;
    private int age = -1;

    public User(String name, String city) {
        this.id = nextId++;
        this.name = name;
        this.city = city;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int compareTo(User other) {
        return this.age - other.age;
    }
}
