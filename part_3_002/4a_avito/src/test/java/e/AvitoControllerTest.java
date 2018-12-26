package e;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.security.Principal;
import java.io.IOException;
import org.springframework.ui.ModelMap;
import e.domain.User;
import e.domain.Mark;
import e.domain.Model;
import e.domain.CarBody;
import e.domain.Engine;
import e.domain.Transmission;
import e.domain.Ad;
import e.domain.Image;
import e.service.BusinessLogic;
import e.web.AvitoController;
import e.model.StubPrincipal;
import e.model.StubMultipartFile;

public class AvitoControllerTest {
    private BusinessLogic logic = mock(BusinessLogic.class);
    private AvitoController controller;
    private Principal principal = new StubPrincipal();

    @Before
    public void before() {
        this.logic = mock(BusinessLogic.class);
        when(this.logic.findUserByLogin(anyString())).thenReturn(
                                  new User("login", "Name", "password"));
        this.controller = new AvitoController(this.logic);
        List<Mark> marks = Arrays.asList(
                                  new Mark(1), new Mark(2), new Mark(3));
        when(this.logic.findAllMarks()).thenReturn(marks);
        List<Model> models = Arrays.asList(
                                       new Model(1, marks.get(1), "One"),
                                      new Model(2, marks.get(1), "Two"));
        when(this.logic.findModelsByMarkId(anyInt())).thenReturn(models);
        when(this.logic.findModelsByMarkId(anyString())).thenReturn(models);
        List<CarBody> carBodies = Arrays.asList(new CarBody(1));
        when(this.logic.findAllCarBodies()).thenReturn(carBodies);
        List<Engine> engines = Arrays.asList(new Engine(1));
        when(this.logic.findAllEngines()).thenReturn(engines);
        List<Transmission> transmissions = Arrays.asList(
                                                     new Transmission());
        when(this.logic.findAllTransmissions())
                                              .thenReturn(transmissions);
    }

    @Test
    public void whenIndexPageThenViewAndAttributes() {
        Ad[][] expect = {{new Ad(), new Ad(), new Ad()}};
        when(this.logic.findAds(any(), anyString(), anyString()))
                                                     .thenReturn(expect);
        ModelMap modelMap = new ModelMap();
        String view = this.controller.indexPage(
                         null, "m", "on", this.principal, modelMap);
        assertEquals("index", view);
        assertEquals("login",
                           ((User) modelMap.get("operator")).getLogin());
        assertEquals(3, ((List<Mark>) modelMap.get("marks")).size());
        assertEquals("m", modelMap.get("period"));
        assertEquals(1, ((Ad[][]) modelMap.get("rows")).length);
    }

    @Test
    public void whenAuthFilterNoPrincipalNoIdThenLogin() {
        ModelMap modelMap = new ModelMap();
        String view = this.controller.authFilter(null, null, modelMap);
        assertTrue(view.startsWith("forward:auth"));
    }

    @Test
    public void whenAuthFilterThenViewAndAttributes() {
        Ad ad = new Ad.Builder(new User(), new Model(new Mark(2)),
            new CarBody(), new Engine(), new Transmission(), 2002, 20020)
                                                      .withId(3).build();
        when(this.logic.findAdById(anyString())).thenReturn(ad);
        ModelMap modelMap = new ModelMap();
        String view = this.controller.authFilter(
                                          "2", this.principal, modelMap);
        assertEquals("edit", view);
        assertEquals(3, ((Ad) modelMap.get("ad")).getId());
        assertEquals(2, ((List<Model>) modelMap.get("models")).size());
        assertTrue((boolean) modelMap.get("view"));
        assertEquals("login",
                           ((User) modelMap.get("operator")).getLogin());
        assertEquals(3, ((List<Mark>) modelMap.get("marks")).size());
        assertEquals(1, ((List<CarBody>) modelMap.get("carBodies")).size());
        assertEquals(1, ((List<Engine>) modelMap.get("engines")).size());
        assertEquals(1, ((List<Transmission>) modelMap.get("transmissions")).size());
    }

    @Test
    public void whenEditPageThenViewAndAttributes() {
        Ad ad = new Ad.Builder(new User(), new Model(new Mark(2)),
            new CarBody(), new Engine(), new Transmission(), 2002, 20020)
                                                      .withId(3).build();
        Map<String, Object> params = new HashMap<>();
        when(this.logic.createAd(any(), any())).thenReturn(ad);
        ModelMap modelMap = new ModelMap();
        String view = this.controller.editPage(params,
                      new StubMultipartFile(), this.principal, modelMap);
        assertEquals("edit", view);
        assertThat(params.get("image"), is(new byte[] {1, 2, 3}));
        assertThat(modelMap.get("ad"), is(ad));
        assertEquals(2, ((List<Model>) modelMap.get("models")).size());
        assertTrue((boolean) modelMap.get("view"));
        assertEquals("login",
                           ((User) modelMap.get("operator")).getLogin());
        assertEquals(1,
                     ((List<CarBody>) modelMap.get("carBodies")).size());
        assertEquals(1, ((List<Engine>) modelMap.get("engines")).size());
        assertEquals(1,
            ((List<Transmission>) modelMap.get("transmissions")).size());
    }

    @Test
    public void whenJsonServiceThenList() {
        List<Entry> result = this.controller.jsonService("42");
        List<Entry> expect = Arrays.asList(
                               new Entry(1, "One"), new Entry(2, "Two"));
        assertThat(result, is(expect));
    }

    @Test
    public void whenAuthPageThenViewAndAttributes() {
        ModelMap modelMap = new ModelMap();
        String view = this.controller.authPage(
                                              "path", "error", modelMap);
        assertEquals("auth", view);
        assertEquals("path", modelMap.get("path"));
        assertEquals("error", modelMap.get("error"));
    }

    @Test
    public void whenImageResponseByIdThenByteArray() throws IOException {
        byte[] bytes = {1, 2, 3};
        Image image = new Image(bytes);
        when(this.logic.findImageById(anyInt())).thenReturn(image);
        byte[] result = this.controller.imageResponse("6", null);
        assertThat(result, is(bytes));
    }

    @Test
    public void whenImageResponseByNameThenByteArray()
                                                     throws IOException {
        byte[] expect = {-119, 80, 78};
        byte[] image =
                   this.controller.imageResponse(null, "unselected.png");
        byte[] result = new byte[3];
        System.arraycopy(image, 0, result, 0, 3);
        assertThat(result, is(expect));
    }
}
