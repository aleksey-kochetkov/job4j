package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class AvitoServlet extends HttpServlet {
    private final BusinessLogic logic = new BusinessLogic(
                                                     new HbRepository());

    @Override
    protected void doGet(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        this.avitoPage(request, response);
    }

    private void avitoPage(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        this.getServletContext().setAttribute("path",
                                               request.getServletPath());
        this.getServletContext().setAttribute("rows",
                                                this.logic.findAllAds());
        request.getRequestDispatcher("/WEB-INF/avito.jsp")
                                             .include(request, response);
    }
}
