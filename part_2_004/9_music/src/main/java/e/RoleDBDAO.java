package e;

import org.apache.commons.dbcp2.ConnectionFactory;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDBDAO implements RoleDAO {
    private final DBUnitOfWork work;

    public RoleDBDAO(UnitOfWork work) {
        this.work = (DBUnitOfWork) work;
    }

    @Override
    public Role findRoleByCode(String code) {
        Role result;
        try (Connection connection = this.work.getConnection();
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
    public List<Role> findAllRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = this.work.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                                                 "SELECT * FROM role")) {
            while (rs.next()) {
                result.add(this.newRole(rs));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    private Role newRole(ResultSet rs) throws SQLException {
        return new Role(rs.getString("code"), rs.getString("descript"));
    }
}
