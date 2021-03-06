package tracker;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("One", "Description one");
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
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
        assertEquals(0, tracker.getAll().size());
    }

    @Test
    public void whenDeleteThenSecondBecomesFirst() {
        Tracker tracker = new Tracker();
        Item one = new Item("One", "One description");
        tracker.add(one);
        Item two = new Item("Two", "Two description");
        tracker.add(two);
        tracker.delete(one.getId());
        assertThat(tracker.getAll().get(0), is(two));
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
        List<Item> expect = Arrays.asList(one, another);
        assertThat(tracker.findByName("One"), is(expect));
    }

    @Test
    public void whenNotAddedAndGetAllThenEmpty() {
        Tracker tracker = new Tracker();
        assertEquals(0, tracker.getAll().size());
    }

    @Test
    public void whenNotAddedAndFindByIdThenNull() {
        Tracker tracker = new Tracker();
        assertNull(tracker.findById("123"));
    }
}
