package e;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    List<User> findAllUsers();
    User findUserByLogin(String login);
    void updateUser(User user);
    void deleteUser(User user);
}
