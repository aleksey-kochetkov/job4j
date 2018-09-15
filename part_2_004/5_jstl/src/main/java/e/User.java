package e;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private Date createDate;
    private String password;
    private Role role;
    private City city;

    public static class Builder {
        private int id;
        private String name;
        private String login;
        private String email;
        private Date createDate;
        private String password;
        private Role role;
        private City city;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withCity(City city) {
            this.city = city;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.login = builder.login;
        this.email = builder.email;
        this.createDate = builder.createDate;
        this.password = builder.password;
        this.role = builder.role;
        this.city = builder.city;
    }

    public User(int id, String name, String login, String email,
                                 String password, Role role, City city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
        this.password = password;
        this.role = role;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public City getCity() {
        return this.city;
    }
}
