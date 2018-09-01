import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("UserServlet");
    private final ValidateService logic = ValidateService.getInstance();
    private final SimpleDateFormat format =
                                 new SimpleDateFormat("dd.MM.YYY HH:mm");

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        out.append("<!DOCTYPE html><head><meta charset=\"UTF-8\"><title>Список</title></head>"
          + "<body><table>");
        for (User u : this.logic.findAll()) {
            out.append(String.format("<tr><td>%d</td>"
              + "<td>&nbsp;%s</td><td>%s</td><td>%s</td><td>%s</td>"
              + "<td><form action=\"%s/edit\" method=\"get\">"
              + "<input type=\"hidden\" name=\"id\" value=\"%d\">"
              + "<input type=\"submit\" value=\"Редактировать\">"
              + "</form></td><td>"
              + "<form action=\"%s/list\" method=\"post\">"
              + "<input type=\"hidden\" name=\"id\" value=\"%d\">"
              + "<input type=\"submit\" value=\"Удалить\">"
              + "</form></td></tr>",
              u.getId(), u.getName(), u.getLogin(), u.getEmail(),
              this.format.format(u.getCreateDate()),
              request.getContextPath(), u.getId(),
              request.getContextPath(), u.getId()));
        }
        out.append("</table></body></html>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                   throws ServletException, IOException {
        int id = Integer.MIN_VALUE;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException exception) {
        }
        this.logic.delete(new User(id, request.getParameter("name")));
    }
}
