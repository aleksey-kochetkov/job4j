package ru.job4j.loop;

public class Board {
    public String paint(int width, int height) {
      StringBuilder screen = new StringBuilder();
      final String LN = System.lineSeparator();
      for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
              if ((y + x) % 2 == 0) {
                  screen.append("X");
              } else {
                  screen.append(" ");
              }
          }
          screen.append(LN);
      }
      return screen.toString();
    }
}
