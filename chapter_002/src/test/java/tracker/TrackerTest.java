package tracker;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class TrackerTest {
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
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("One", "Description one");
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item oldItem = new Item("Old item", "Old description");
        tracker.add(oldItem);
        Item newItem = new Item("New item", "New description");
        String expect = "New item";
        newItem.setId(oldItem.getId());
        tracker.replace(oldItem.getId(), newItem);
        assertThat(tracker.findById(oldItem.getId()).getName(), is(expect));
    }

    @Test
    public void whenDeleteSingleThenEmpty() {
        Tracker tracker = new Tracker();
        Item item = new Item("Single", "Single description");
        tracker.add(item);
        tracker.delete(item.getId());
        assertEquals(0, tracker.getAll().length);
    }

    @Test
    public void whenDeleteThenSecondBecomesFirst() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "One description");
        tracker.add(one);
        Item two = new Item("Two", "Two description");
        tracker.add(two);
        tracker.delete(one.getId());
        assertThat(tracker.getAll()[0], is(two));
    }

    @Test
    public void whenFindByNameThenTwoFound() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "One description");
        tracker.add(one);
        Item two = new Item("Two", "Two description");
        tracker.add(two);
        Item another = new Item("One", "Another description");
        tracker.add(another);
        Item[] expect = {one, another};
        assertThat(tracker.findByName("One"), is(expect));
    }

    @Test
    public void whenNotAddedAndGetAllThenEmpty() {
        Tracker tracker = new Tracker();
        assertEquals(0, tracker.getAll().length);
    }

    @Test
    public void whenNotAddedAndFindByIdThenNull() {
        Tracker tracker = new Tracker();
        assertNull(tracker.findById("123"));
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
