package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ImageServlet extends HttpServlet {
    private final BusinessLogic logic = new BusinessLogic(
                                                     new HbRepository());

    @Override
    protected void doGet(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        String id = request.getParameter("id");
        OutputStream out = response.getOutputStream();
        if (id != null) {
            this.writeById(out, Integer.parseInt(id));
        } else {
            this.writeByName(out, request.getParameter("name"));
        }
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        this.logic.closeRepository();
    }

    private void writeById(OutputStream out, int id) throws IOException {
        out.write(this.logic.findImageById(id).getBytes());
    }

    private void writeByName(OutputStream out, String name)
                                                     throws IOException {
        InputStream in = this.getClass().getResourceAsStream(
                     String.format("/%s", name));
        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
        }
    }
}
