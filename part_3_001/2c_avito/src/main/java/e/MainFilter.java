package e;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

public class MainFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response, FilterChain chain)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);
    }
}