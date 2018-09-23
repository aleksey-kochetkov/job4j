package e;

import java.util.List;

public interface RoleDAO {
    Role findRoleByCode(String code);
    List<Role> findAllRoles();
}
