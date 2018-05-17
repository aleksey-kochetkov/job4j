package ru.job4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

/**
 * Test.
 * @author Aleksey Kochetkov
 * @since 0.1
 */
public class CalculateTest {
	
	/**
	 * Test echo.
	 */
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
        String input = "Petr Arsentev";
		String expect = "Echo, echo, echo: Petr Arsentev";
		Calculate calculate = new Calculate();
		String result = calculate.echo(input);
		assertThat(result, is(expect));
	}
}