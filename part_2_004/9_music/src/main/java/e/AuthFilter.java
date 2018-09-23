package e;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                             ServletResponse response, FilterChain chain)
                                   throws ServletException, IOException {
        if (((HttpServletRequest) request).getSession().getAttribute("operator") == null) {
            request.getRequestDispatcher("/auth").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
