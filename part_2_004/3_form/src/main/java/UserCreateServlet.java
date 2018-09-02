import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class UserCreateServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("UserServlet");
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        out.append(String.format(Form.FORM, request.getContextPath(),
          request.getServletPath(), "",
          "", "", ""));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.logic.add(new User(Integer.MIN_VALUE,
          request.getParameter("name"),
          request.getParameter("login"), request.getParameter("email")));
        response.sendRedirect(String.format("%s/list",
                                              request.getContextPath()));
    }
}
