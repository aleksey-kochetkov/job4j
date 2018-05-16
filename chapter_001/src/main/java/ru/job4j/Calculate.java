package ru.job4j;

/**
 * Это комментарий к классу Calculate. Этот класс создан для учебного примера.
 * @author Aleksey Kochetkov
 */
public class Calculate {

  /**
   * Echoes the input value three times.
   * @param value input string
   * @return resulting string
   */
  public String echo(String value) {
    return String.format("%s %s %s", value, value, value);
  }

  /**
   * Main.
   * @param args args
   */
  public static void main(String[] args) {
    Calculate calculate = new Calculate();
    System.out.println(calculate.echo("Aah!"));
  }
}
