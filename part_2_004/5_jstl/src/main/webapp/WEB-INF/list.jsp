<%@ page import="java.util.List" %>
<%@ page import="e.User" %>
<%@ page import="e.UserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Список</title>
</head>
<body><div class="container-fluid">
    <table class="table">
        <thead>
            <tr>
                <th>Id</th>
                <th>Имя</th>
                <th>Логин</th>
                <th>Email</th>
                <th>Создан</th>
                <th>Роль</th>
            </tr>
        </thead>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <jsp:useBean id="user" type="e.User"/>
            <td>${UserServlet.FMT.format(user.getCreateDate())}</td>
            <td>${user.role.description}</td>
            <td>
                <c:if test="${operator.role.code == 'root' || operator.login == user.login}">
                    <form action="${pageContext.request.contextPath}/servlet" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <button class="btn btn-default" type="submit" name="action" value="edit">Редактировать</button>
                    </form>
                </c:if>
            </td>
            <td>
                <c:if test="${operator.role.code == 'root' || operator.login == user.login}">
                    <form action="${pageContext.request.contextPath}/servlet" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <button class="btn btn-default" type="submit" name="action" value="delete">Удалить</button>
                    </form>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td></td><td></td><td></td><td></td><td></td><td></td>
            <td>
                <c:if test="${operator.role.code == 'root'}">
                    <form action="${pageContext.request.contextPath}/servlet" method="get">
                        <button class="btn btn-default" type="submit" name="action" value="create">Новый</button>
                    </form>
                </c:if>
            </td><td></td>
        </tr>
    </table>
</div></body>
</html>
