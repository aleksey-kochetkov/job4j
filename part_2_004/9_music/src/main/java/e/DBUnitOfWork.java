package e;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUnitOfWork implements UnitOfWork {
    private Connection connection;

    DBUnitOfWork(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void beginWork() {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void commitWork() {
        try {
            this.connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    Connection getConnection() {
        return this.connection;
    }
}
