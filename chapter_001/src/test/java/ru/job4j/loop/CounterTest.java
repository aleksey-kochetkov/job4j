package ru.job4j.loop;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 *@author Aleksey Kochetkov
 */
public class CounterTest {

    @Test
    public void when1To10Then30() {
        Counter counter = new Counter();
        assertEquals(30, counter.add(1, 10));
    }
}
