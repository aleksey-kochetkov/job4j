package e;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UserDBDAO implements UserDAO {
    private final DBUnitOfWork work;

    public UserDBDAO(UnitOfWork work) {
        this.work = (DBUnitOfWork) work;
    }

    @Override
    public void createUser(User user) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                               "INSERT INTO uzer VALUES (?, ?, ?, ?)")) {
            prepared.setString(1, user.getLogin());
            prepared.setString(2, user.getPassword());
            prepared.setString(3, user.getName());
            prepared.setString(4, user.getRole().getCode());
            prepared.executeUpdate();
            this.createUserMusicType(connection, user);
        } catch (SQLException exception) {
             throw new RuntimeException(exception);
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> result = new ArrayList<>();
        Connection connection = this.work.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                         "SELECT uzer.*, address.address, role.descript "
                       + "FROM uzer, address, role "
                       + "WHERE address.user_login = uzer.login "
                       + "AND role.code = uzer.role_code");
            while (rs.next()) {
                result.add(this.newUser(connection, rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        User result = null;
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                         "SELECT uzer.*, address.address, role.descript "
                       + "FROM uzer, address, role "
                       + "WHERE address.user_login = uzer.login "
                       + "AND role.code = uzer.role_code "
                       + "AND uzer.login = ?")) {
            prepared.setString(1, login);
            ResultSet rs = prepared.executeQuery();
            if (rs.next()) {
                result = this.newUser(connection, rs);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public void updateUser(User user) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                                                    this.getSql(user))) {
            int i = 1;
            if (user.getPassword().length() > 0) {
                prepared.setString(i++, user.getPassword());
            }
            prepared.setString(i++, user.getName());
            prepared.setString(i++, user.getRole().getCode());
            prepared.setString(i++, user.getLogin());
            prepared.executeUpdate();
            this.deleteUserMusicTypes(connection, user);
            this.createUserMusicType(connection, user);
        } catch (SQLException exception) {
             throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteUser(User user) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                                   "DELETE FROM uzer WHERE login = ?")) {
            this.deleteUserMusicTypes(connection, user);
            prepared.setString(1, user.getLogin());
            prepared.executeUpdate();
        } catch (SQLException exception) {
             throw new RuntimeException(exception);
        }
    }

    private void createUserMusicType(Connection connection, User user)
                                                    throws SQLException {
        if (user.getMusicTypes().size() > 0) {
            try (PreparedStatement prepared =
                                             connection.prepareStatement(
                          "INSERT INTO user_music_type VALUES (?, ?)")) {
                for (MusicType m : user.getMusicTypes()) {
                    prepared.setString(1, user.getLogin());
                    prepared.setString(2, m.getCode());
                    prepared.executeUpdate();
                }
            }
        }
    }

    private void deleteUserMusicTypes(Connection connection, User user)
                                                    throws SQLException {
        try (PreparedStatement prepared = connection.prepareStatement(
                   "DELETE FROM user_music_type WHERE user_login = ?")) {
            prepared.setString(1, user.getLogin());
            prepared.executeUpdate();
        }
    }

    private User newUser(Connection connection, ResultSet rs)
                                                    throws SQLException {
        try (PreparedStatement prepared = connection.prepareStatement(
               "SELECT music_type.* "
             + "FROM user_music_type, music_type "
             + "WHERE music_type.code = user_music_type.music_type_code "
             + "AND user_login = ?")) {
            prepared.setString(1, rs.getString("login"));
            ResultSet resultSet = prepared.executeQuery();
            List<MusicType> musicTypes = new ArrayList<>();
            while (resultSet.next()) {
                musicTypes.add(new MusicType(resultSet.getString("code"),
                                       resultSet.getString("descript")));
            }
            return new User(rs.getString("login"),
                          rs.getString("password"), rs.getString("name"),
                                       new Address(rs.getString("login"),
                                                rs.getString("address")),
                                      new Role(rs.getString("role_code"),
                                  rs.getString("descript")), musicTypes);
        }
    }

    private String getSql(User user) {
        String result;
        if (user.getPassword().length() > 0) {
            result = "UPDATE uzer SET (password, name, role_code) = "
                                           + "(?, ?, ?) WHERE login = ?";
        } else {
            result = "UPDATE uzer SET (name, role_code) = "
                                              + "(?, ?) WHERE login = ?";
        }
        return result;
    }
}
