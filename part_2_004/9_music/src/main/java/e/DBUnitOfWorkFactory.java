package e;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUnitOfWorkFactory implements UnitOfWorkFactory {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
        SOURCE.setUrl("jdbc:postgresql:music");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    @Override
    public UnitOfWork getUnitOfWork() {
        try {
            return new DBUnitOfWork(SOURCE.getConnection());
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
