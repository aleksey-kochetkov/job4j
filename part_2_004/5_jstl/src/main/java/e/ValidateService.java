package e;

import java.util.List;

public final class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = DBStore.getInstance().init();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private ValidateService() {
    }

    public void addUser(User user, User operator) {
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        if (user.getName() == null) {
            throw new IllegalArgumentException();
        }
        this.store.addUser(user);
    }

    public void updateUser(User user, User operator) {
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        if (this.store.findUserById(user.getId()) == null
                     || user.getName() == null || user.getLogin() == null
                                            || user.getEmail() == null) {
            throw new IllegalArgumentException();
        }
        this.store.updateUser(user);
    }

    public void deleteUser(User user, User operator) {
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        if (this.store.findUserById(user.getId()) == null) {
            throw new IllegalArgumentException();
        }
        this.store.deleteUser(user);
    }

    public List<User> findAllUsers() {
        return this.store.findAllUsers();
    }

    public User findUserById(int id) {
        User result = this.store.findUserById(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public User findUserByLogin(String login) {
        User result = this.store.findUserByLogin(login);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public boolean isCredential(String login, String password) {
        User user = this.store.findUserByLogin(login);
        return user != null && password.equals(user.getPassword());
    }

    public List<Role> findAllRoles() {
        return this.store.findAllRoles();
    }

    public Role findRoleByCode(String code) {
        Role result = this.store.findRoleByCode(code);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public void closeStore() {
        try {
             this.store.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private boolean checkAccess(User user, User operator) {
        return "root".equals(operator.getRole().getCode())
            || operator.getLogin().equals(user.getLogin())
            || operator.getId() == user.getId();
    }
}
