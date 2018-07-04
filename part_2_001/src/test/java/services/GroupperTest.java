package services;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class GroupperTest {

    @Test
    public void whenFirstTest() {
        Groupper groupper = new Groupper();
        String[] input = {"длиннобезгруппы", "кот", "котоматрица", "котопёс",
                          "ктотматрац", "кто", "ктотопёс",
                          "октопус", "октопёсик", "окто",
                          "восьмиджаварь", "восьминог"};
        String result = groupper.apply(input);
        assertThat(result, is("восьмиджаварь"));
    }

    @Test
    public void whenTheRusultIsFirstInTheInput() {
        Groupper groupper = new Groupper();
        String[] input = {"экстримпрограммист", "экслюбовница",
                "диван", "девальвациядевальвация", "дивчина",
                "длиннобезгруппы", "кот", "котоматрица", "котопёс"};
        String result = groupper.apply(input);
        assertThat(result, is("экстримпрограммист"));
    }
}
