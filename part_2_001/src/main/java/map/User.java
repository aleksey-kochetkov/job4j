package map;

import java.util.Calendar;

public class User {
    private String name;
    private Calendar birthday;
    private int children;

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    @Override
    public String toString() {
        return "User{" + "name=\"" + name + '"'
                + ", birthday={" + birthday.get(Calendar.YEAR) + ','
                + birthday.get(Calendar.MONTH) + ','
                + birthday.get(Calendar.DATE) + '}'
                + ", children=" + children + '}';
    }
}
