package e;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@PowerMockIgnore("javax.management.*")
public class UserServletTest {

    @Test
    public void whenAddAsRoot() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        User operator = validate.findUserByLogin("root");
        UserServlet servlet = new UserServlet();
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("action")).thenReturn("create");
        when(request.getParameter("name")).thenReturn("Принцесса");
        when(request.getSession().getAttribute("operator")).thenReturn(operator);
        HttpServletResponse response = mock(HttpServletResponse.class);
        servlet.init();
        servlet.doPost(request, response);
        assertThat(validate.findUserById(1).getName(), is("Принцесса"));
    }

    @Test
    public void whenUpdateAsRoot() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        User user = new User(-1, "Принцесса", "принцесса", "принцесса@ya.ru", "*",
          validate.findRoleByCode("user"), validate.findCityByCode("7495"));
        User operator = validate.findUserByLogin("root");
        validate.addUser(user, operator);
        UserServlet servlet = new UserServlet();
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("action")).thenReturn("edit");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Лев");
        when(request.getParameter("login")).thenReturn("лев");
        when(request.getParameter("email")).thenReturn("лев@ya.ru");
        when(request.getSession().getAttribute("operator")).thenReturn(operator);
        HttpServletResponse response = mock(HttpServletResponse.class);
        servlet.init();
        servlet.doPost(request, response);
        assertThat(validate.findUserById(1).getName(), is("Лев"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDeleteAsRoot() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        User user = new User(-1, "Принцесса", "принцесса", "принцесса@ya.ru", "*",
          validate.findRoleByCode("user"), validate.findCityByCode("7495"));
        User operator = validate.findUserByLogin("root");
        validate.addUser(user, operator);
        UserServlet servlet = new UserServlet();
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession().getAttribute("operator")).thenReturn(operator);
        HttpServletResponse response = mock(HttpServletResponse.class);
        servlet.init();
        servlet.doPost(request, response);
        validate.findUserById(1);
    }
}
