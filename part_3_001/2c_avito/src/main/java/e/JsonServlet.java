package e;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonServlet extends HttpServlet {
    private final BusinessLogic logic =
                                   new BusinessLogic(new HbRepository());
    @Override
    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        JsonGenerator generator = new JsonFactory().createGenerator(
                                             response.getOutputStream());
        generator.writeStartArray();
        for (Model m : this.logic.findModelsByMarkId(
                                       request.getParameter("markId"))) {
            generator.writeStartObject();
            generator.writeNumberField("id", m.getId());
            generator.writeStringField("name", m.getName());
            generator.writeEndObject();
        }
        generator.writeEndArray();
        generator.flush();
    }

    @Override
    public void destroy() {
        this.logic.closeRepository();
    }
}
