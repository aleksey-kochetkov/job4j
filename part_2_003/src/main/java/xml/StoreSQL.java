package xml;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreSQL {
    private Config config;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int n) throws SQLException {
        try (Connection connection = DriverManager.getConnection(this.config.getUrl())) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS entry");
                statement.executeUpdate("CREATE TABLE entry ( "
                        + "  field INTEGER "
                        + ")");
            }
            try (PreparedStatement prepared =
                        connection.prepareStatement("INSERT INTO entry VALUES (?)")) {
                connection.setAutoCommit(false);
                for (int i = 0; i < n; i++) {
                    prepared.setInt(1, i);
                    prepared.executeUpdate();
                }
                connection.setAutoCommit(true);
            }
        }
    }
}
