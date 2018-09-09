package e;

import java.util.List;

public interface Validate {
    void addUser(User user, User operator);
    void updateUser(User user, User operator);
    void deleteUser(User user, User operator);
    List<User> findAllUsers();
    User findUserById(int id);
    User findUserByLogin(String login);
    boolean isCredential(String login, String password);
    List<Role> findAllRoles();
    Role findRoleByCode(String code);
    void closeStore();
}
