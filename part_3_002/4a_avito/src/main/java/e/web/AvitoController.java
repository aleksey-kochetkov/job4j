package e.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import java.security.Principal;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import e.Entry;
import e.domain.User;
import e.domain.Model;
import e.domain.Ad;
import e.service.BusinessLogic;

@Controller
public class AvitoController {
    private final BusinessLogic logic;

    public AvitoController(BusinessLogic logic) {
        this.logic = logic;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(String mark, String period, String notClosed,
                                   Principal principal, ModelMap model) {
        if (principal != null) {
            model.addAttribute("operator",
                        this.logic.findUserByLogin(principal.getName()));
        }
        model.addAttribute("marks", this.logic.findAllMarks());
        Optional<Integer> markId = this.optionalOf(mark);
        markId.ifPresent(id -> model.addAttribute("markId", id));
        model.addAttribute("period", period);
        model.addAttribute("notClosed", notClosed);
        model.addAttribute("rows",
                          this.logic.findAds(markId, period, notClosed));
        return "index";
    }

    @RequestMapping(value = {"/edit", "/create"}, method = RequestMethod.GET)
    public String authFilter(String id, Principal principal, ModelMap model) {
        String result;
        if (principal == null && id == null) {
            result = "forward:auth?path=edit";
        } else {
            result = this.editPage(id, null, principal, model);
        }
        return result;
    }

    private String editPage(String id, Ad ad, Principal principal, ModelMap model) {
        if (id != null) {
            ad = this.logic.findAdById(id);
            model.addAttribute("ad", ad);
            this.addAttributeModels(ad, model);
            this.checkAttributeView(ad, principal, model);
        }
        if (principal != null) {
            model.addAttribute("operator",
                        this.logic.findUserByLogin(principal.getName()));
        }
        model.addAttribute("marks", this.logic.findAllMarks());
        model.addAttribute("carBodies", this.logic.findAllCarBodies());
        model.addAttribute("engines", this.logic.findAllEngines());
        model.addAttribute("transmissions",
                                      this.logic.findAllTransmissions());
        return "edit";
    }

    @RequestMapping (value = "/edit", method = RequestMethod.POST)
    public String editPage(@RequestParam Map<String, Object> params,
      @RequestParam MultipartFile file, Principal principal, ModelMap model) {
        try {
            if (!file.isEmpty()) {
                params.put("image", file.getBytes());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Ad ad;
        String id = (String) params.get("id");
        User operator = this.logic.findUserByLogin(principal.getName());
        if (id == null) {
            ad = this.logic.createAd(operator, params);
        } else {
            ad = this.logic.updateAd(operator, id, params);
        }
        model.addAttribute("ad", ad);
        this.addAttributeModels(ad, model);
        model.addAttribute("view", true);
        return this.editPage(null, ad, principal, model);
    }

    private void addAttributeModels(Ad ad, ModelMap model) {
        model.addAttribute("models", this.logic.findModelsByMarkId(
                                       ad.getModel().getMark().getId()));
    }

    private void checkAttributeView(Ad ad,
                                   Principal principal, ModelMap model) {
        if (principal == null || !principal.getName().equals(
                                              ad.getUser().getLogin())) {
            model.addAttribute("view", true);
        }
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public List<Entry> jsonService(String markId) {
        List<Entry> result = new ArrayList<>();
        for (Model m : this.logic.findModelsByMarkId(markId)) {
            result.add(new Entry(m.getId(), m.getName()));
        }
        return result;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authPage(String path, String error, ModelMap model) {
        model.addAttribute("path", path);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "auth";
    }

    private Optional<Integer> optionalOf(String value) {
        return value == null || value.length() == 0 ? Optional.empty()
                                   : Optional.of(Integer.valueOf(value));
    }
    @RequestMapping(value = "/car", method = RequestMethod.GET)
    @ResponseBody
    public byte[] imageResponse(String id, String name) throws IOException {
        byte[] result;
        if (id != null) {
            result =
               this.logic.findImageById(Integer.parseInt(id)).getBytes();
        } else {
            result = this.writeByName(name);
        }
        return result;
    }

    private byte[] writeByName(String name) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = this.getClass().getResourceAsStream(
                     String.format("/%s", name));
        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
        }
        return out.toByteArray();
    }
}
