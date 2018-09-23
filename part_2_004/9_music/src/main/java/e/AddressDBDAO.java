package e;

import org.apache.commons.dbcp2.ConnectionFactory;

import java.sql.*;

public class AddressDBDAO implements AddressDAO {
    private final DBUnitOfWork work;

    public AddressDBDAO(UnitOfWork work) {
        this.work = (DBUnitOfWork) work;
    }

    @Override
    public void createAddress(Address address) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                                  "INSERT INTO address VALUES (?, ?)")) {
            prepared.setString(1, address.getUserLogin());
            prepared.setString(2, address.getAddress());
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateAddress(Address address) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                "UPDATE address SET address = ? WHERE user_login = ?")) {
            prepared.setString(1, address.getAddress());
            prepared.setString(2, address.getUserLogin());
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteAddress(Address address) {
        Connection connection = this.work.getConnection();
        try (PreparedStatement prepared = connection.prepareStatement(
                           "DELETE FROM address WHERE user_login = ?")) {
            prepared.setString(1, address.getUserLogin());
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
