package e;

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
    public static final SimpleDateFormat FMT =
                                 new SimpleDateFormat("dd.MM.YYY HH:mm");
    public static final String DEF = "user";
    private final ValidateService logic = ValidateService.getInstance();
    private final ActionDispatcher dispatcher = new ActionDispatcher();

    @Override
    public void init() {
        this.dispatcher.load("create", this.logic::addUser);
        this.dispatcher.load("edit", this.logic::updateUser);
        this.dispatcher.load("deleteUser", this.logic::deleteUser);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        this.forward(request.getParameter("action"), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = this.getValue(request.getParameter("id"), Integer.MIN_VALUE);
        String roleCode = request.getParameter("role");
        dispatcher.dispatch(request.getParameter("action"),
                               new User(id, request.getParameter("name"),
                                           request.getParameter("login"),
         request.getParameter("email"), request.getParameter("password"),
          roleCode == null ? null : this.logic.findRoleByCode(roleCode)),
                   (User) request.getSession().getAttribute("operator"));
        this.forward("list", request, response);
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

    private void forward(String action, HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
        String path;
        if ("create".equals(action)) {
            path = "/WEB-INF/user.jsp";
            request.setAttribute("title", "Создание");
            request.setAttribute("roles", this.logic.findAllRoles());
            request.setAttribute("roleCode", Role.DEF);
        } else if ("edit".equals(action)) {
            path = "/WEB-INF/user.jsp";
            request.setAttribute("title", "Редактирование");
            User user = this.logic.findUserById(
                getValue(request.getParameter("id"), Integer.MIN_VALUE));
            request.setAttribute("user", user);
            request.setAttribute("roles", this.logic.findAllRoles());
            request.setAttribute("roleCode", user.getRole().getCode());
        } else {
            path = "/WEB-INF/list.jsp";
            request.setAttribute("users", this.logic.findAllUsers());
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
}
