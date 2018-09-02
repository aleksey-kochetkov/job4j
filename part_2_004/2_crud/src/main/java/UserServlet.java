import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("UserServlet");
    private final ValidateService logic = ValidateService.getInstance();
    private final ActionDispatcher dispatcher = new ActionDispatcher();

    @Override
    public void init() {
        this.dispatcher.load("add", this.logic::add);
        this.dispatcher.load("update", this.logic::update);
        this.dispatcher.load("delete", this.logic::delete);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        for (User u : this.logic.findAll()) {
            out.format("%d %s%n", u.getId(), u.getName());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                   throws ServletException, IOException {
        int id = this.getValue(request.getParameter("id"), Integer.MIN_VALUE);
        this.logic.delete(new User(id, request.getParameter("name")));
        response.sendRedirect(String.format("%s%s",
                    request.getContextPath(), request.getServletPath()));
    }

    private int getValue(String string, int def) {
        int result;
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            result = def;
        }
        return result;
    }
}
