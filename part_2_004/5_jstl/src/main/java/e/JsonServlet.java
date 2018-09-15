package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class JsonServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser parser =
                           jsonFactory.createParser(request.getReader());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(parser);
        List<City> cities = this.logic.findCitiesByCountryCode(
                                   rootNode.get("countryCode").asText());
        mapper.writeValue(response.getWriter(), cities);
    }
}
