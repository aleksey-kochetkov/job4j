package tracker;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws SQLException {
        try (Tracker tracker = new Tracker(
                RollbackConnection.create(StartUI.getConnection()))) {
            Item item = new Item("Jan", "Description one");
            tracker.add(item);
            assertThat(tracker.findByName("Jan").get(0), is(item));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            Item oldItem = new Item("Old item", "Old description");
            tracker.add(oldItem);
            Item newItem = new Item("New item", "New description");
            String expect = "New item";
            newItem.setId(oldItem.getId());
            tracker.replace(oldItem.getId(), newItem);
            assertThat(tracker.findById(oldItem.getId()).getName(), is(expect));
        }
    }

    @Test
    public void whenDeleteSingleThenEmpty() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            int previousSize = tracker.getAll().size();
            Item item = new Item("Single", "Single description");
            tracker.add(item);
            tracker.delete(item.getId());
            assertEquals(previousSize, tracker.getAll().size());
        }
    }

    @Test
    public void whenDeleteThenSecondBecomesFirst() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            Item one = new Item("One", "One description");
            int previousSize = tracker.getAll().size();
            tracker.add(one);
            Item two = new Item("Two", "Two description");
            tracker.add(two);
            tracker.delete(one.getId());
            assertThat(tracker.getAll().get(previousSize), is(two));
        }
    }

    @Test
    public void whenFindByNameThenTwoFound() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            Item one = new Item("Feb", "One description");
            tracker.add(one);
            Item two = new Item("Two", "Two description");
            tracker.add(two);
            Item another = new Item("Feb", "Another description");
            tracker.add(another);
            List<Item> expect = Arrays.asList(one, another);
            assertThat(tracker.findByName("Feb"), is(expect));
        }
    }

    @Test
    public void whenNotAddedAndGetAllThenEmpty() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            tracker.deleteAll();
            assertEquals(0, tracker.getAll().size());
        }
    }

    @Test
    public void whenNotAddedAndFindByIdThenNull() {
        try (Tracker tracker = new Tracker(StartUI.getConnection())) {
            tracker.deleteAll();
            assertNull(tracker.findById(123));
        }
    }
}
