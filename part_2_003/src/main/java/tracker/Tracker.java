package tracker;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Система ведения заявок.
 *
 * @author Aleksey Kochetkov
 */
public class Tracker implements AutoCloseable {
    private static final Random RANDOM = new Random();
    private List<Item> items = new ArrayList<>();
    private Connection connection;

    public Tracker(Connection connection) {
        this.connection = connection;
    }

    public Item add(Item item) {
        try (PreparedStatement prepared = this.connection.prepareStatement(
                       "INSERT INTO item (name, descript) VALUES (?, ?)",
                                      Statement.RETURN_GENERATED_KEYS)) {
            prepared.setString(1, item.getName());
            prepared.setString(2, item.getDescription());
            prepared.executeUpdate();
            ResultSet keys = prepared.getGeneratedKeys();
            keys.next();
            item.setId(keys.getInt(1));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return item;
    }

    public void replace(int id, Item item) {
        try (PreparedStatement prepared = this.connection.prepareStatement(
                "UPDATE item SET name = ?, descript = ? WHERE id = ?")) {
            prepared.setString(1, item.getName());
            prepared.setString(2, item.getDescription());
            prepared.setInt(3, id);
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void delete(int id) {
        try (PreparedStatement prepared = this.connection.prepareStatement(
                                      "DELETE FROM item WHERE id = ?")) {
            prepared.setInt(1, id);
            prepared.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    void deleteAll() {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DELETE FROM item");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                                                   "SELECT * FROM item");
            while (resultSet.next()) {
                result.add(new Item(resultSet.getInt("id"),
                                             resultSet.getString("name"),
                                       resultSet.getString("descript")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(
                                    "SELECT * FROM item WHERE id = ?")) {
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                result = new Item(resultSet.getInt("id"),
                                             resultSet.getString("name"),
                                        resultSet.getString("descript"));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(
                                  "SELECT * FROM item WHERE name = ?")) {
            prepared.setString(1, name);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                result.add(new Item(resultSet.getInt("id"),
                                             resultSet.getString("name"),
                                       resultSet.getString("descript")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
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
