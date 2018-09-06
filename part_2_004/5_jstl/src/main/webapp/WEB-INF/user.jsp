<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <table>
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" name="name" id="name" value="${user.name}"></td>
            </tr>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login" value="${user.login}"></td>
                <td><label for="password">Password:</label></td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" name="email" id="email" value="${user.email}"></td>
            </tr>
            <tr>
                <td><label for="role">Роль:</label></td>
                <td>
                    <c:choose>
                        <c:when test="${operator.role.code == 'root'}">
                    <select name="role" id="role">
                        </c:when>
                        <c:otherwise>
                    <input type="hidden" name="role" value="${roleCode}">
                    <select name="role" id="role" disabled>
                        </c:otherwise>
                    </c:choose>
                        <c:forEach items="${roles}" var="role">
                            <c:choose>
                                <c:when test="${role.code.equals(roleCode)}">
                                    <option value="${role.code}" selected>${role.description}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${role.code}">${role.description}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit" name="action" value="${pageContext.request.getParameter("action")}">
                        Сохранить
                    </button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
