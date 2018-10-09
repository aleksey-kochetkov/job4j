package e;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;

public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request,
                         HttpServletResponse response, FilterChain chain)
                                   throws ServletException, IOException {
        if (request.getSession().getAttribute("operator") == null
                                 && request.getParameter("id") == null) {
            this.getServletContext().setAttribute("path",
                                               request.getServletPath());
            request.getRequestDispatcher("/auth")
                                             .forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
