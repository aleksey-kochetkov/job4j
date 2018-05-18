package ru.job4j.converter;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    @Test
    public void when2EuroThen140() {
        Converter converter = new Converter();
        assertThat(converter.euroToRuble(2), is(140D));
    }

    @Test
    public void when3DollarThen180() {
        Converter converter = new Converter();
        assertThat(converter.dollarToRuble(3), is(180D));
    }
}
