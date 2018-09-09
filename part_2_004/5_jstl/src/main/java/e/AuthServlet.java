package e;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class AuthServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth.jsp")
                                             .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        if (this.logic.isCredential(request.getParameter("login"),
                                     request.getParameter("password"))) {
            User operator = this.logic.findUserByLogin(request.getParameter("login"));
            request.getSession().setAttribute("operator", operator);
            response.sendRedirect(
                  String.format("%s/servlet", request.getContextPath()));
        } else {
            request.setAttribute("error", "Ошибка авторизации");
            this.doGet(request, response);
        }
    }
}
