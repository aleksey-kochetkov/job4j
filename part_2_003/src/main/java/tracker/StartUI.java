package tracker;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Aleksey Kochetkov
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных
     * @param tracker хранилище заявок
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int key;
        do {
            menu.show();
            key = this.input.ask("Введите пункт меню : ", menu.getRange());
            menu.select(key);
        } while (key != menu.exitIndex);
    }

    /**
     * Запуск программы.
     * @param args args
     */
    public static void main(String[] args) {
        Connection connection = getConnection();
        try (Tracker tracker = new Tracker(connection)) {
            new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
        }
    }

    static Connection getConnection() {
        Connection result = null;
        try (Scanner scanner =
          new Scanner(StartUI.class.getResourceAsStream("/db.conf"), "utf-8")) {
            scanner.useDelimiter(";");
            String url = scanner.next().trim();
            String user = scanner.next().trim();
            String password = scanner.next().trim();
            result = connect(url, user, password);
            if (result == null) {
                createDatabase(url, user, password);
                result = connect(url, user, password);
                executeDBSql(scanner, result);
            }
        }
        return result;
    }

    private static Connection connect(String url, String user, String password) {
        Connection result = null;
        try {
            result = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            if ("3D000".equals(exception.getSQLState())) {
                result = null;
            } else {
                throw new RuntimeException(exception);
            }
        }
        return result;
    }

    private static void createDatabase(String url, String user, String password) {
        int index = url.lastIndexOf(':') + 1;
        String urlPath = url.substring(0, index);
        String database = url.substring(index);
        try (Connection connection = DriverManager.getConnection(urlPath, user, password)) {
            connection.createStatement().executeUpdate(
                          String.format("CREATE DATABASE %s", database));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static void executeDBSql(Scanner scanner, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            while (scanner.hasNext()) {
                statement.executeUpdate(scanner.next());
            }
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
