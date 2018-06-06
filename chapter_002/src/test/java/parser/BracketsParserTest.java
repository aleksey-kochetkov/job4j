package parser;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BracketsParserTest {

    @Test
    public void whenFirstSampleThenValid() {
        String sample = "{{}}[]";
        BracketsParser parser = new BracketsParser();
        List<BracketsPair> result = null;
        try {
            result = parser.parse(sample);
        } catch (ParseException exception) {
            fail();
        }
        List<BracketsPair> expect = new ArrayList<>();
        BracketsPair pair = new BracketsPair('{', '}');
        pair.setOpenPosition(0);
        pair.setClosePosition(3);
        expect.add(pair);
        pair = new BracketsPair('{', '}');
        pair.setOpenPosition(1);
        pair.setClosePosition(2);
        expect.add(pair);
        pair = new BracketsPair('[', ']');
        pair.setOpenPosition(4);
        pair.setClosePosition(5);
        expect.add(pair);
        assertThat(result.toArray(), is(expect.toArray()));
    }

    @Test
    public void whenSecondSampleThenValid() {
        String sample = "[{}{}]";
        BracketsParser parser = new BracketsParser();
        List<BracketsPair> result = null;
        try {
            result = parser.parse(sample);
        } catch (ParseException exception) {
            fail();
        }
        List<BracketsPair> expect = new ArrayList<>();
        BracketsPair pair = new BracketsPair('[', ']');
        pair.setOpenPosition(0);
        pair.setClosePosition(5);
        expect.add(pair);
        pair = new BracketsPair('{', '}');
        pair.setOpenPosition(1);
        pair.setClosePosition(2);
        expect.add(pair);
        pair = new BracketsPair('{', '}');
        pair.setOpenPosition(3);
        pair.setClosePosition(4);
        expect.add(pair);
        assertThat(result.toArray(), is(expect.toArray()));
    }

    @Test(expected = ParseException.class)
    public void whenThirdSampleThenInvalid() throws ParseException {
        String sample = "{[}]";
        BracketsParser parser = new BracketsParser();
        parser.parse(sample);
        fail();
    }

    @Test(expected = ParseException.class)
    public void whenSpecialSampleThenInvalid() throws ParseException {
        String sample = "{[]";
        BracketsParser parser = new BracketsParser();
        parser.parse(sample);
        fail();
    }

    @Test(expected = ParseException.class)
    public void whenFourthSampleThenInvalid() throws ParseException {
        String sample = "a[b{c}d)e]";
        BracketsParser parser = new BracketsParser();
        parser.parse(sample);
        fail();
    }

    @Test
    public void whenMyFourthSampleThenValid() {
        String sample = "a[b{c}(d)e]";
        BracketsParser parser = new BracketsParser();
        List<BracketsPair> result = null;
        try {
            result = parser.parse(sample);
        } catch (ParseException exception) {
            fail();
        }
        List<BracketsPair> expect = new ArrayList<>();
        BracketsPair pair = new BracketsPair('[', ']');
        pair.setOpenPosition(1);
        pair.setClosePosition(10);
        expect.add(pair);
        pair = new BracketsPair('{', '}');
        pair.setOpenPosition(3);
        pair.setClosePosition(5);
        expect.add(pair);
        pair = new BracketsPair('(', ')');
        pair.setOpenPosition(6);
        pair.setClosePosition(8);
        expect.add(pair);
        assertThat(result.toArray(), is(expect.toArray()));
    }

    @Test
    public void whenCustomBracketsThenValid() {
        String sample = "a/[b{c\\}(d)e]";
        BracketsParser parser = new BracketsParser("/", "\\");
        List<BracketsPair> result = null;
        try {
            result = parser.parse(sample);
        } catch (ParseException exception) {
            fail();
        }
        List<BracketsPair> expect = new ArrayList<>();
        BracketsPair pair = new BracketsPair('/', '\\');
        pair.setOpenPosition(1);
        pair.setClosePosition(6);
        expect.add(pair);
        assertThat(result.toArray(), is(expect.toArray()));
    }
}
