package map;

import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private String name;
    private Calendar birthday;
    private int children;

    public static void main(String[] args) {
        User a = new User("One", new Calendar.Builder().setFields(
                      Calendar.YEAR, 1985, Calendar.MONTH, Calendar.JUNE,
                                          Calendar.DATE, 25).build(), 0);
        User b = new User("One", new Calendar.Builder().setFields(
                      Calendar.YEAR, 1985, Calendar.MONTH, Calendar.JUNE,
                                          Calendar.DATE, 25).build(), 0);
        Map<User, Object> map = new HashMap<>();
        map.put(a, new Object());
        map.put(b, new Object());
        System.out.format("%s%n", map.toString());
        System.out.format("%s %s%n", Integer.toHexString(System.identityHashCode(a)),
                        Integer.toHexString(System.identityHashCode(b)));
        System.out.format("%b%n", a.equals(b));
    }

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.birthday.hashCode();
        result = 31 * result + Integer.hashCode(this.children);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User) o;
        if (!(this.name == null ? u.name == null : this.name.equals(u.name))) {
            return false;
        }
        if (!(this.birthday == null ? u.birthday == null : this.birthday.equals(u.birthday))) {
            return false;
        }
        return this.children == u.children;
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
