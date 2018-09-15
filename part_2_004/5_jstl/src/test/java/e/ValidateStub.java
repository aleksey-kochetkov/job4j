package e;

import java.util.List;

public final class ValidateStub implements Validate {
    private final Store store = new StubStore().init();

    @Override
    public void addUser(User user, User operator) {
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        if (user.getName() == null) {
            throw new IllegalArgumentException();
        }
        this.store.addUser(user);
    }

    @Override
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

    @Override
    public void deleteUser(User user, User operator) {
        if (!this.checkAccess(user, operator)) {
            throw new IllegalArgumentException();
        }
        if (this.store.findUserById(user.getId()) == null) {
            throw new IllegalArgumentException();
        }
        this.store.deleteUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return this.store.findAllUsers();
    }

    @Override
    public User findUserById(int id) {
        User result = this.store.findUserById(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        User result = this.store.findUserByLogin(login);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public boolean isCredential(String login, String password) {
        User user = this.store.findUserByLogin(login);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public List<Role> findAllRoles() {
        return this.store.findAllRoles();
    }

    @Override
    public Role findRoleByCode(String code) {
        Role result = this.store.findRoleByCode(code);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<Country> findAllCountries() {
        return this.store.findAllCountries();
    }

    @Override
    public City findCityByCode(String code) {
        City result = this.store.findCityByCode(code);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<City> findCitiesByCountryCode(String countryCode) {
        return this.store.findCitiesByCountryCode(countryCode);
    }

    @Override
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
