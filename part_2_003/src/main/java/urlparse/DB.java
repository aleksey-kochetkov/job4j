package urlparse;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.InputStream;
import java.util.Scanner;
import java.util.List;
import java.util.Properties;
import java.sql.DatabaseMetaData;

public class DB implements AutoCloseable {
    private Properties properties;
    private Connection connection;

    public DB(Properties properties) {
        this.properties = properties;
    }

    private String getJdbcUrl() {
        return String.format("jdbc:%s:%s",
                this.properties.getProperty("jdbc.driver"),
                this.properties.getProperty("jdbc.url"));
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(getJdbcUrl(),
                    this.properties.getProperty("jdbc.username"),
                    this.properties.getProperty("jdbc.password"));
            if (!this.tableExists()) {
                this.runCreateSQL();
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private boolean tableExists() throws SQLException {
        DatabaseMetaData meta = this.connection.getMetaData();
        ResultSet rs = meta.getTables(null, null, null, new String[] {"TABLE"});
        return rs.next();
    }

    private void runCreateSQL() throws SQLException {
        InputStream in = this.getClass().getResourceAsStream("/create.sql");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter(";");
            try (Statement statement = this.connection.createStatement()) {
                while (scanner.hasNext()) {
                    statement.executeUpdate(scanner.next());
                }
            }
        }
    }

    public Timestamp getMaxTimestamp() {
        Timestamp result;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT max(dt) FROM position");
            rs.next();
            result = rs.getTimestamp(1);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    /**
     * Общая транзакция не введена потому что в базе стоит проверка
     * на уникальность (descript, dt), и общая транзакция откатит весь
     * блок. А так выбрасывается только лишний INSERT.
     *
     * @param positions список вакансий
     */
    public void insert(List<Position> positions) {
        try (PreparedStatement prepared = this.connection.prepareStatement(
                "INSERT INTO position (descript, dt) VALUES (?, ?)")) {
            for (Position position : positions) {
                prepared.setString(1, position.getDescription());
                prepared.setTimestamp(2, new Timestamp(position.getDate().getTime()));
                prepared.executeUpdate();
            }
        } catch (SQLException exception) {
            if (!"23505".equals(exception.getSQLState())) {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
