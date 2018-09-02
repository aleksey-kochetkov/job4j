<%@ page import="e.ValidateService" %>
<%@ page import="e.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/servlet" method="post">
<%  User user;
    try {
        user = ValidateService.getInstance().findById(
                           Integer.parseInt(request.getParameter("id")));
    } catch (NumberFormatException exception) {
        user = null;
    }%>
        <input type="hidden" name="id" value="<%=user.getId()%>">
        <table>
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" name="name" id="name" value="<%=user.getName()%>"></td>
            </tr>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value="<%=user.getLogin()%>"></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" name="email" id="email" value="<%=user.getEmail()%>"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit" name="action" value="update">Сохранить</button></td>
            </tr>
        </table>
    </form>
</body>
</html>
