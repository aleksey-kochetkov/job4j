package e;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public final class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore().init();

    public static Store getInstance() {
        return INSTANCE;
    }


    private DBStore() {
    }

    public DBStore init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
        SOURCE.setUrl("jdbc:postgresql:servlet");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        return this;
    }

    @Override
    public void close() throws SQLException {
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection
          .prepareStatement(
                   "INSERT INTO uzer (name, login, email, password, role_code, city_code) "
                                           + "VALUES (?, ?, ?, ?, ?, ?)")) {
            this.setAndExecute(0, prepared, user);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection
                                  .prepareStatement(this.getSql(user))) {
            this.setAndExecute(user.getPassword().length() > 0 ? 1 : 2,
                                                         prepared, user);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection
          .prepareStatement("DELETE FROM uzer WHERE id = ?")) {
            prepared.setInt(1, user.getId());
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM uzer");
            while (rs.next()) {
                result.add(this.newUser(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public User findUserById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                                    "SELECT * FROM uzer WHERE id = ?")) {
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            result = this.newUser(rs);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                                 "SELECT * FROM uzer WHERE login = ?")) {
            prepared.setString(1, login);
            ResultSet rs = prepared.executeQuery();
            if (rs.next()) {
                result = this.newUser(rs);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public void addRole(Role role) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM role");
            while (rs.next()) {
                result.add(this.newRole(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public Role findRoleByCode(String code) {
        Role result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                                  "SELECT * FROM role WHERE code = ?")) {
            prepared.setString(1, code);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            result = this.newRole(rs);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public Country findCountryByCode(String code) {
        Country result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                               "SELECT * FROM country WHERE code = ?")) {
            prepared.setString(1, code);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            result = this.newCountry(rs);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public City findCityByCode(String code) {
        City result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                                  "SELECT * FROM city WHERE code = ?")) {
            prepared.setString(1, code);
            ResultSet rs = prepared.executeQuery();
            rs.next();
            result = this.newCity(rs);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public List<Country> findAllCountries() {
        List<Country> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM country");
            while (rs.next()) {
                result.add(this.newCountry(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public List<City> findCitiesByCountryCode(String countryCode) {
        List<City> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement prepared = connection.prepareStatement(
                           "SELECT * FROM city WHERE country_code = ?")) {
            prepared.setString(1, countryCode);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                result.add(this.newCity(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    private User newUser(ResultSet rs) throws SQLException {
        return new User.Builder().withId(rs.getInt("id"))
          .withName(rs.getString("name")).withLogin(rs.getString("login"))
          .withEmail(rs.getString("email"))
          .withCreateDate(new Date(rs.getTimestamp("create_dt").getTime()))
          .withPassword(rs.getString("password"))
          .withRole(this.findRoleByCode(rs.getString("role_code")))
          .withCity(this.findCityByCode(rs.getString("city_code"))).build();
    }

    private String getSql(User user) {
        String result;
        if (user.getPassword().length() > 0) {
            result = "UPDATE uzer SET (name, login, email, password, role_code, city_code) = "
                                        + "(?, ?, ?, ?, ?, ?) WHERE id = ?";
        } else {
            result = "UPDATE uzer SET (name, login, email, role_code, city_code) = "
                                           + "(?, ?, ?, ?, ?) WHERE id = ?";
        }
        return result;
    }

    private void setAndExecute(int context, PreparedStatement prepared,
                                         User user) throws SQLException {
        int i = 1;
        prepared.setString(i++, user.getName());
        prepared.setString(i++, user.getLogin());
        prepared.setString(i++, user.getEmail());
        if (context != 2) {
            prepared.setString(i++, user.getPassword());
        }
        prepared.setString(i++, user.getRole().getCode());
        prepared.setString(i++, user.getCity().getCode());
        if (context != 0) {
            prepared.setInt(i++, user.getId());
        }
        prepared.executeUpdate();
    }

    private Role newRole(ResultSet rs) throws SQLException {
        return new Role(rs.getString("code"), rs.getString("descript"));
    }

    private Country newCountry(ResultSet rs) throws SQLException {
        return new Country(rs.getString("code"), rs.getString("name"));
    }

    private City newCity(ResultSet rs) throws SQLException {
        return new City(rs.getString("code"), rs.getString("name"),
                   this.findCountryByCode(rs.getString("country_code")));
    }
}
