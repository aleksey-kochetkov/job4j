package tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

public class StartUITest {
    private final PrintStream outSave = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void resetOutput() {
        System.setOut(this.outSave);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            int previousSize = tracker.getAll().size();
            Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
            new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
            assertThat(tracker.getAll().get(previousSize).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        }
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() throws SQLException {
        // создаём Tracker
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            //Напрямую добавляем заявку
            Item item = tracker.add(new Item("One", "Description one"));
            //создаём StubInput с последовательностью действий
            Input input = new StubInput(new String[]{
                    "2", Integer.toString(item.getId()), "test name", "desc", "6"});
            // создаём StartUI и вызываем метод init()
            new StartUI(input, tracker).init();
            // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
            assertThat(tracker.findById(item.getId()).getName(), is("test name"));
        }
    }

    @Test
    public void whenDeleteThenTrackerHasOneItem() throws SQLException {
        try (Tracker tracker = new Tracker(
                RollbackConnection.create(StartUI.getConnection()))) {
            int previousSize = tracker.getAll().size();
            Item one = new Item("One", "Description one");
            tracker.add(one);
            Item two = new Item("Two", "Description two");
            tracker.add(two);
            Input input = new StubInput(new String[]{
                    "3", Integer.toString(one.getId()), "6"});
            new StartUI(input, tracker).init();
            assertEquals(previousSize + 1, tracker.getAll().size());
        }
    }

    @Test
    public void whenShowAll() throws SQLException {
        Input input = new StubInput(new String[] {"1", "6"});
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            Item one = new Item("March", "Description one");
            tracker.add(one);
            Item two = new Item("April", "Description two");
            tracker.add(two);
            new StartUI(input, tracker).init();
            StringBuilder expect = new StringBuilder("March Description one ").
                    append(one.getId()).append(System.lineSeparator()).
                    append("April Description two ").append(two.getId());
            assertTrue(this.out.toString().contains(expect));
        }
    }

    @Test
    public void whenFindById() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            Item one = new Item("May", "Description one");
            tracker.add(one);
            Item two = new Item("June", "Description two");
            tracker.add(two);
            Input input = new StubInput(new String[]{
                    "4", Integer.toString(one.getId()), "6"});
            new StartUI(input, tracker).init();
            StringBuilder expect = new StringBuilder("May Description one ")
                                                    .append(one.getId());
            assertTrue(this.out.toString().contains(expect));
        }
    }

    @Test
    public void whenFindByName() throws SQLException {
        try (Tracker tracker = new Tracker(
                   RollbackConnection.create(StartUI.getConnection()))) {
            tracker.deleteAll();
            Item one = new Item("July", "Description one");
            tracker.add(one);
            Item two = new Item("Two", "Description two");
            tracker.add(two);
            Item three = new Item("July", "Description three");
            tracker.add(three);
            Input input = new StubInput(new String[]{"5", "July", "6"});
            new StartUI(input, tracker).init();
            StringBuilder expect = new StringBuilder("July Description one ")
                .append(one.getId()).append(System.lineSeparator())
                .append("July Description three ").append(three.getId());
            assertTrue(this.out.toString().contains(expect));
        }
    }
}
