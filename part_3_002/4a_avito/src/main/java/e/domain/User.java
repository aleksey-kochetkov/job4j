package e.domain;

public class User {
    private String login;
    private String password;
    private String name;

    public User() {
    }

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User) o;
        return (u.login == null ? this.login == null
                                            : u.login.equals(this.login))
            && (u.password == null ? this.password == null
                                      : u.password.equals(this.password))
            && (u.name == null ? this.name == null
                                             : u.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        int result = this.login == null ? 0 : this.login.hashCode();
        result += 31 * result + (this.password == null ? 0
                                             : this.password.hashCode());
        result += 31 * result + (this.name == null ? 0
                                                 : this.name.hashCode());
        return result;
    }
}
