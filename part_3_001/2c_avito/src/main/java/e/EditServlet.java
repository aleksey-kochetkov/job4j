package e;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;

public class EditServlet extends HttpServlet {
    private final BusinessLogic logic = new BusinessLogic(
                                                     new HbRepository());
    private final DiskFileItemFactory factory =
                                               new DiskFileItemFactory();

    @Override
    public void init() {
        this.factory.setRepository((File) this.getServletContext()
                         .getAttribute("javax.servlet.context.tempdir"));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String id = request.getParameter("id");
        if (id != null) {
            Ad ad = this.logic.findAdById(id);
            request.setAttribute("ad", ad);
            this.setAttributeModels(request, ad);
            this.checkAttributeView(request, ad);
        }
        request.setAttribute("marks", this.logic.findAllMarks());
        request.setAttribute("carBodies", this.logic.findAllCarBodies());
        request.setAttribute("engines", this.logic.findAllEngines());
        request.setAttribute("transmissions",
                                      this.logic.findAllTransmissions());
        request.getRequestDispatcher("/WEB-INF/edit.jsp")
                                             .include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                                            HttpServletResponse response)
                                   throws ServletException, IOException {
        Map<String, Object> parameters = this.getParameters(request);
        Ad ad;
        String id = (String) parameters.get("id");
        if (id == null) {
          ad = this.logic.createAd(
                    (User) request.getSession().getAttribute("operator"),
                                                             parameters);
        } else {
          ad = this.logic.updateAd(
                    (User) request.getSession().getAttribute("operator"),
                                                         id, parameters);
        }
        request.setAttribute("ad", ad);
        this.setAttributeModels(request, ad);
        request.setAttribute("view", true);
        this.doGet(request, response);
    }

    @Override
    public void destroy() {
        this.logic.closeRepository();
    }

    private Map<String, Object> getParameters(
                                            HttpServletRequest request) {
        Map<String, Object> result;
        if (ServletFileUpload.isMultipartContent(request)) {
            return this.processMultipartContent(request);
        } else {
            throw new IllegalStateException();
        }
    }
    private Map<String, Object> processMultipartContent(
                                            HttpServletRequest request) {
        try {
            Map<String, Object> result = new HashMap<>();
            ServletFileUpload upload =
                                     new ServletFileUpload(this.factory);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem i : items) {
                if (i.isFormField()) {
                    result.put(i.getFieldName(), i.getString());
                } else {
                    byte[] bytes = i.get();
                    if (bytes.length > 0) {
                        result.put("image", bytes);
                    }
                }
            }
            return result;
        } catch (FileUploadException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setAttributeModels(HttpServletRequest request, Ad ad) {
        request.setAttribute("models", this.logic.findModelsByMarkId(
                                       ad.getModel().getMark().getId()));
    }

    private void checkAttributeView(HttpServletRequest request, Ad ad) {
        HttpSession session = request.getSession();
        User operator = (User) session.getAttribute("operator");
        if (operator == null
               || !operator.getLogin().equals(ad.getUser().getLogin())) {
            request.setAttribute("view", true);
        }
    }
}
