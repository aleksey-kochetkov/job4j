<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty error}">
    <div style="background-color: red;">Ошибка авторизации</div>
</c:if>
<form action="${pageContext.request.contextPath}/auth" method="post">
    <table>
        <caption>Авторизуйтесь</caption>
        <tr>
            <td><label for="login">login</label></td>
            <td><input type="text" id="login" name="login"></td>
        </tr>
        <tr>
            <td><label for="password">password</label></td>
            <td><input type="password" id="password" name="password"></td>
    </table>
    <input type="submit" value="Вход">
</form>
</body>
</html>
