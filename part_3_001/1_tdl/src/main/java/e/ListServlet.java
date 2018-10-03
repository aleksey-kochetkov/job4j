package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class ListServlet extends HttpServlet {
    private final BusinessLogic logic = new BusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<Item> items =
                   this.logic.findAllItems(request.getParameter("done"));
        try (JsonGenerator generator = new JsonFactory()
                          .createGenerator(response.getOutputStream())) {
            generator.writeStartArray();
            for (Item i : items) {
                    generator.writeStartObject();
                generator.writeNumberField("id", i.getId());
                generator.writeStringField("descript", i.getDescript());
                generator.writeStringField("created", i.getCreated().toString());
                generator.writeBooleanField("done", i.isDone());
                generator.writeEndObject();
            }
            generator.writeEndArray();
        }
    }

    @Override
    public void destroy() {
        this.logic.close();
    }
}
