import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class UserUpdateServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("UserServlet");
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        int id = Integer.MIN_VALUE;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException exception) {
        }
        User user = this.logic.findById(id);
        out.append(String.format(Form.FORM, request.getContextPath(),
          request.getServletPath(), String.format("name=\"id\" value=\"%d\"",
                                                           user.getId()),
          user.getName(), user.getLogin(), user.getEmail()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.MIN_VALUE;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException exception) {
        }
        this.logic.update(new User(id,
          request.getParameter("name"),
          request.getParameter("login"), request.getParameter("email")));
        response.sendRedirect(String.format("%s/list",
                                              request.getContextPath()));
    }
}
