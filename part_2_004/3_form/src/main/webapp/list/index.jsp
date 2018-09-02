<%@ page import="e.ValidateService" %>
<%@ page import="e.User" %>
<%@ page import="e.UserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Имя</th>
                <th>Логин</th>
                <th>Email</th>
                <th>Создан</th>
            </tr>
        </thead>
        <% for (User u : ValidateService.getInstance().findAll()) { %>
        <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getName()%></td>
            <td><%=u.getLogin()%></td>
            <td><%=u.getEmail()%></td>
            <td><%=UserServlet.FMT.format(u.getCreateDate())%></td>
            <td>
                <form action="<%=request.getContextPath()%>/edit" method="get">
                    <input type="hidden" name="id" value="<%=u.getId()%>">
                    <input type="submit" value="Редактировать">
                </form>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/servlet" method="post">
                    <input type="hidden" name="id" value="<%=u.getId()%>">
                    <button type="submit" name="action" value="delete">Удалить</button>
                </form>
            </td>
        </tr>
        <% } %>
        <tr>
            <td></td><td></td><td></td><td></td><td></td>
            <td>
                <form action="<%=request.getContextPath()%>/create" method="get">
                    <input type="submit" value="Новый">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
