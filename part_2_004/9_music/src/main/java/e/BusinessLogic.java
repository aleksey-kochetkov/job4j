package e;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BusinessLogic {
    private final Repository repository;

    public BusinessLogic(UnitOfWorkFactory unitOfWorkFactory,
                                                 DAOFactory daoFactory) {
        this.repository = new Repository(unitOfWorkFactory, daoFactory);
    }

    public void createUser(User operator, String login, String password,
              String name, String address, String role, String[] music) {
        User user =
               this.newUser(login, password, name, address, role, music);
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        this.repository.createUser(user);
    }

    public User findUserByLogin(String login) {
        User result = this.repository.findUserByLogin(login);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public List<User> findUsersByAnyField(String input) {
        List<User> result = new ArrayList<>();
        if (input == null || input.equals("")) {
            result = this.repository.findAllUsers();
        } else {
            User user = this.repository.findUserByLogin(input);
            if (user != null) {
                result.add(user);
            }
        }
        return result;
    }

    public void updateUser(User operator, String login, String password,
              String name, String address, String role, String[] music) {
        User user =
               this.newUser(login, password, name, address, role, music);
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        this.repository.updateUser(user);
    }

    public void deleteUser(User operator, String login) {
        User user = this.repository.findUserByLogin(login);
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        this.repository.deleteUser(user);
    }

    public boolean isCredential(String login, String password) {
        User user = this.repository.findUserByLogin(login);
        return user != null && password.equals(user.getPassword());
    }

    public List<Role> findAllRoles() {
        return this.repository.findAllRoles();
    }

    private boolean checkAccess(User user, User operator) {
        return "ADMIN   ".equals(operator.getRole().getCode())
                          || operator.getLogin().equals(user.getLogin());
    }

    public List<MusicType> findAllMusicTypes() {
        return this.repository.findAllMusicTypes();
    }

    private User newUser(String login, String password, String name,
                           String address, String role, String[] music) {
        List<MusicType> musicTypes = new ArrayList<>();
        for (String code : music) {
            musicTypes.add(this.repository.findMusicTypeByCode(code));
        }
        return new User(login, password, name,
                                             new Address(login, address),
                   this.repository.findRoleByCode(role), musicTypes);
    }
}
