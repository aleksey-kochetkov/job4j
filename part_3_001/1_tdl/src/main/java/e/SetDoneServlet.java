package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class SetDoneServlet extends HttpServlet {
    private BusinessLogic logic = new BusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Item item = this.logic.setItemDone(
               request.getParameter("id"), request.getParameter("done"));
        try (JsonGenerator generator = new JsonFactory()
                          .createGenerator(response.getOutputStream())) {
            generator.writeStartObject();
            generator.writeNumberField("id", item.getId());
            generator.writeStringField("descript", item.getDescript());
            generator.writeStringField("created", item.getCreated().toString());
            generator.writeBooleanField("done", item.isDone());
            generator.writeEndObject();
        }
    }
}
