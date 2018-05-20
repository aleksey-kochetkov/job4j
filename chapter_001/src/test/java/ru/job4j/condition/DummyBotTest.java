package ru.job4j.condition;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

/**
 * @author Aleksey Kochetkov
 */
public class DummyBotTest {

    @Test
    public void whenGreetBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Привет, Бот!"), is("Привет, умник!"));
    }

    @Test
    public void whenBueBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Пока!"), is("До скорой встречи!"));
    }

    @Test
    public void whenUnknownBot() {
        DummyBot bot = new DummyBot();
        assertThat(bot.answer("Сколько будет 2 + 2?"),
                  is("Это ставит меня в тупик. Задайте другой вопрос."));
    }
}
