package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        JsonObject object = mapper.readValue(reader, JsonObject.class);
        object = new JsonObject("Полокан", "Тахти", "Суомесса");
        mapper.writeValue(writer, object);
    }
}
