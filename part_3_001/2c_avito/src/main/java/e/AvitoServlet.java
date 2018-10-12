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
        this.avitoPage(request, response);
    }

    private void avitoPage(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        this.getServletContext().setAttribute("path",
                                               request.getServletPath());
        request.setAttribute("marks", this.logic.findAllMarks());
        Integer markId = this.valueOf(request.getParameter("mark"));
        if (markId != null) {
            request.setAttribute("markId", markId);
        }
        request.setAttribute("period", request.getParameter("period"));
        request.setAttribute("notClosed",
                                      request.getParameter("notClosed"));
        request.setAttribute("rows",
             this.logic.findAds(markId, request.getParameter("period"),
                                     request.getParameter("notClosed")));
        request.getRequestDispatcher("/WEB-INF/avito.jsp")
                                             .include(request, response);
    }

    private Integer valueOf(String value) {
        return value == null || value.length() == 0 ? null
                                                : Integer.valueOf(value);
    }
}
