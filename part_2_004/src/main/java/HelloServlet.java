import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger("HelloServlet");

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        writer.append("Hello, World!");
        writer.flush();
    }
}
