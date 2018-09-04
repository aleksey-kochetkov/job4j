<%@ page import="java.util.List" %>
<%@ page import="e.User" %>
<%@ page import="e.UserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <jsp:useBean id="user" type="e.User"/>
            <% String createDate = UserServlet.FMT.format(user.getCreateDate()); %>
            <td><c:out value="${createDate}"/></td>
            <td>
                <form action="${pageContext.request.contextPath}/servlet" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" name="action" value="edit">Редактировать</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/servlet" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" name="action" value="delete">Удалить</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td></td><td></td><td></td><td></td><td></td>
            <td>
                <form action="${pageContext.request.contextPath}/servlet" method="get">
                    <button type="submit" name="action" value="create">Новый</button>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
