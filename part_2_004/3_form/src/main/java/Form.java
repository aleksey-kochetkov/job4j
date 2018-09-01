public class Form {
    public static final String FORM =
      "<!DOCTYPE html>\n"
    + "  <head>\n"
    + "    <meta charset=\"UTF-8\">\n"
    + "    <title>Создание/изменение</title>\n"
    + "  </head>\n"
    + "  <body>\n"
    + "    <form action=\"%s%s\" method=\"post\">\n"
    + "      <input type=\"hidden\" %s>"
    + "      <table>\n"
    + "        <tr>\n"
    + "          <td>Name:</td>\n"
    + "          <td><input type=\"text\" name=\"name\" value=\"%s\"></td>\n"
    + "        </tr>\n"
    + "        <tr>\n"
    + "          <td>Login:</td>\n"
    + "          <td><input type=\"text\" name=\"login\" value=\"%s\"></td>\n"
    + "        </tr>"
    + "        <tr>\n"
    + "          <td>Email:</td>\n"
    + "          <td><input type=\"text\" name=\"email\" value=\"%s\"></td>\n"
    + "        </tr>\n"
    + "        <tr>\n"
    + "          <td></td>\n"
    + "          <td><input type=\"submit\" value=\"Сохранить\"></td>\n"
    + "        </tr>\n"
    + "      </table></form></body></html>";
}
