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
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[] {"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("One", "Description one"));
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[] {"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerHasOneItem() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "Description one");
        tracker.add(one);
        Item two = new Item("Two", "Description two");
        tracker.add(two);
        Input input = new StubInput(new String[] {"3", one.getId(), "6"});
        new StartUI(input, tracker).init();
        assertEquals(1, tracker.getAll().size());
    }

    @Test
    public void whenShowAll() {
        Input input = new StubInput(new String[] {"1", "6"});
        Tracker tracker = new Tracker();
        Item one = new Item("One", "Description one");
        tracker.add(one);
        Item two = new Item("Two", "Description two");
        tracker.add(two);
        new StartUI(input, tracker).init();
        StringBuilder expect = new StringBuilder("One Description one ").
                append(one.getId()).append(System.lineSeparator()).
                append("Two Description two ").append(two.getId());
        assertTrue(this.out.toString().contains(expect));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "Description one");
        tracker.add(one);
        Item two = new Item("Two", "Description two");
        tracker.add(two);
        Input input = new StubInput(new String[] {"4", one.getId(), "6"});
        new StartUI(input, tracker).init();
        StringBuilder expect = new StringBuilder("One Description one ").
                append(one.getId());
        assertTrue(this.out.toString().contains(expect));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "Description one");
        tracker.add(one);
        Item two = new Item("Two", "Description two");
        tracker.add(two);
        Item three = new Item("One", "Description three");
        tracker.add(three);
        Input input = new StubInput(new String[] {"5", "One", "6"});
        new StartUI(input, tracker).init();
        StringBuilder expect = new StringBuilder("One Description one ").
                append(one.getId()).append(System.lineSeparator()).
                append("One Description three ").append(three.getId());
        assertTrue(this.out.toString().contains(expect));
    }
}
