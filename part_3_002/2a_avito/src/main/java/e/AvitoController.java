package e;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

@Controller
public class AvitoController {
    private final BusinessLogic logic;
    @Autowired
    private User operator;

    public AvitoController(BusinessLogic logic) {
        this.logic = logic;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(String mark, String period, String notClosed,
                                                        ModelMap model) {
        model.addAttribute("operator", this.operator);
        model.addAttribute("marks", this.logic.findAllMarks());
        Optional<Integer> markId = this.optionalOf(mark);
        markId.ifPresent(id -> model.addAttribute("markId", id));
        model.addAttribute("period", period);
        model.addAttribute("notClosed", notClosed);
        model.addAttribute("rows",
                          this.logic.findAds(markId, period, notClosed));
        return "index";
    }

    private Optional<Integer> optionalOf(String value) {
        return value == null || value.length() == 0 ? Optional.empty()
                                   : Optional.of(Integer.valueOf(value));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String authFilter(String id, ModelMap model) {
        String result;
        if (this.operator.getLogin() == null && id == null) {
            result = "forward:auth?path=edit";
        } else {
            result = this.editPage(id, null, model);
        }
        return result;
    }

    private String editPage(String id, Ad ad, ModelMap model) {
        if (id != null) {
            ad = this.logic.findAdById(id);
            model.addAttribute("ad", ad);
            this.addAttributeModels(ad, model);
            this.checkAttributeView(ad, model);
        }
        model.addAttribute("operator", this.operator);
        model.addAttribute("marks", this.logic.findAllMarks());
        model.addAttribute("carBodies", this.logic.findAllCarBodies());
        model.addAttribute("engines", this.logic.findAllEngines());
        model.addAttribute("transmissions",
                                      this.logic.findAllTransmissions());
        return "edit";
    }

    @RequestMapping (value = "/edit", method = RequestMethod.POST)
    public String editPage(@RequestParam Map<String, Object> params,
                      @RequestParam MultipartFile file, ModelMap model) {
        try {
            if (!file.isEmpty()) {
                params.put("image", file.getBytes());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Ad ad;
        String id = (String) params.get("id");
        if (id == null) {
          ad = this.logic.createAd(this.operator, params);
        } else {
          ad = this.logic.updateAd(this.operator, id, params);
        }
        model.addAttribute("ad", ad);
        this.addAttributeModels(ad, model);
        model.addAttribute("view", true);
        return this.editPage(null, ad, model);
    }

    private void addAttributeModels(Ad ad, ModelMap model) {
        model.addAttribute("models", this.logic.findModelsByMarkId(
                                       ad.getModel().getMark().getId()));
    }

    private void checkAttributeView(Ad ad, ModelMap model) {
        if (this.operator.getLogin() == null
          || !this.operator.getLogin().equals(ad.getUser().getLogin())) {
            model.addAttribute("view", true);
        }
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public @ResponseBody List<Entry> jsonService(String markId) {
        List<Entry> result = new ArrayList<>();
        for (Model m : this.logic.findModelsByMarkId(markId)) {
            result.add(new Entry(m.getId(), m.getName()));
        }
        return result;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authPage(String path, ModelMap model) {
        model.addAttribute("path", path);
        return "auth";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authPage(String path, String login, String password,
                                                        ModelMap model) {
        String result;
        if (this.logic.isCredential(login, password)) {
            User operator = this.logic.findUserByLogin(login);
            this.operator.setLogin(operator.getLogin());
            this.operator.setName(operator.getName());
            if (path == null) {
                path = "index";
            }
            result = "redirect:" + path;
        } else {
            model.addAttribute("error", "Ошибка авторизации");
            model.addAttribute("path", path);
            result = null;
        }
        return result;
    }
}
