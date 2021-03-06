package e;

import java.util.List;

public interface Store extends AutoCloseable {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> findAllUsers();
    User findUserById(int id);
    User findUserByLogin(String login);
    void addRole(Role role);
    List<Role> findAllRoles();
    Role findRoleByCode(String code);
    Country findCountryByCode(String code);
    List<Country> findAllCountries();
    City findCityByCode(String code);
    List<City> findCitiesByCountryCode(String countryCode);
}
