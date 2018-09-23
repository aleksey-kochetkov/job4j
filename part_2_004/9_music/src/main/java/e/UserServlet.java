package e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final SimpleDateFormat FMT =
                                 new SimpleDateFormat("dd.MM.YYY HH:mm");
//    public static final String DEF = "user";
    private final BusinessLogic logic =
        new BusinessLogic(new DBUnitOfWorkFactory(), new DBDAOFactory());
    private final ActionDispatcher dispatcher = new ActionDispatcher();

    @Override
    public void init() {
        this.dispatcher.load("search", this::listPage);
        this.dispatcher.load("create", this::createPage);
        this.dispatcher.load("createSave", this::create);
        this.dispatcher.load("edit", this::editPage);
        this.dispatcher.load("editSave", this::edit);
        this.dispatcher.load("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        this.indexPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        dispatcher.dispatch(request.getParameter("action"),
                                                      request, response);
    }

    private void indexPage(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/head.jsp")
                                             .include(request, response);
        request.getRequestDispatcher("/WEB-INF/index.jsp")
                                             .include(request, response);
    }

    private void listPage(HttpServletRequest request,
                                          HttpServletResponse response) {
        request.setAttribute("users",
          this.logic.findUsersByAnyField(request.getParameter("input")));
        try {
            request.getRequestDispatcher("/WEB-INF/head.jsp")
                                             .include(request, response);
            request.getRequestDispatcher("/WEB-INF/list.jsp")
                                             .include(request, response);
        } catch (ServletException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void createPage(HttpServletRequest request,
                            HttpServletResponse response) {
        request.setAttribute("musicTypes",
                                         this.logic.findAllMusicTypes());
        request.setAttribute("roles", this.logic.findAllRoles());
        request.setAttribute("roleCode", "USER    ");
        try {
            request.getRequestDispatcher("/WEB-INF/head.jsp")
                    .include(request, response);
            request.getRequestDispatcher("/WEB-INF/user.jsp")
                    .include(request, response);
        } catch (ServletException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void create(HttpServletRequest request,
                                          HttpServletResponse response) {
        this.logic.createUser(
                    (User) request.getSession().getAttribute("operator"),
                                           request.getParameter("login"),
          request.getParameter("password"), request.getParameter("name"),
           request.getParameter("address"), request.getParameter("role"),
                                    request.getParameterValues("music"));
        this.listPage(request, response);
    }


    private void editPage(HttpServletRequest request,
                                          HttpServletResponse response) {
        User user =
               this.logic.findUserByLogin(request.getParameter("login"));
        request.setAttribute("edit", "true");
        request.setAttribute("user", user);
        request.setAttribute("musicTypes",
                                         this.logic.findAllMusicTypes());
        request.setAttribute("roles", this.logic.findAllRoles());
        request.setAttribute("roleCode", user.getRole().getCode());
        try {
            request.getRequestDispatcher("/WEB-INF/head.jsp")
                    .include(request, response);
            request.getRequestDispatcher("/WEB-INF/user.jsp")
                    .include(request, response);
        } catch (ServletException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void edit(HttpServletRequest request,
                                          HttpServletResponse response) {
        this.logic.updateUser(
                    (User) request.getSession().getAttribute("operator"),
                                           request.getParameter("login"),
          request.getParameter("password"), request.getParameter("name"),
           request.getParameter("address"), request.getParameter("role"),
                                    request.getParameterValues("music"));
        this.listPage(request, response);
    }

    private void delete(HttpServletRequest request,
                                          HttpServletResponse response) {
        this.logic.deleteUser(
                    (User) request.getSession().getAttribute("operator"),
                                          request.getParameter("login"));
        this.listPage(request, response);
    }
}

class ActionDispatcher {
    private final
         Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>>
                                                    map = new HashMap<>();

    public void load(String action,
          BiConsumer<HttpServletRequest, HttpServletResponse> consumer) {
        this.map.put(action, consumer);
    }

    public void dispatch(String action,
              HttpServletRequest request, HttpServletResponse response) {
        this.map.get(action).accept(request, response);
    }
}