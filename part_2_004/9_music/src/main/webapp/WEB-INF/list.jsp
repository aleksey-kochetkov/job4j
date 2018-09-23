<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid">
    <table class="table">
        <thead>
            <tr>
                <th>Логин</th>
                <th>Имя</th>
                <th>Адрес</th>
                <th>Жанр</th>
                <th>Роль</th>
                <th>
                    <c:if test="${operator.role.code == 'ADMIN   '}">
                    <form method="post">
                        <button class="btn btn-default" type="submit" name="action" value="create">
                            <i class="glyphicon glyphicon-plus"></i>
                        </button>
                    </form>
                    </c:if>
                </th>
            </tr>
        </thead>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.address.address}</td>
            <td>
                <c:forEach items="${user.musicTypes}" var="musicType">
                ${musicType.description}
                </c:forEach>
            </td>
            <td>${user.role.description}</td>
            <td class="btn-group"
             ><c:if test="${operator.role.code == 'ADMIN   ' || operator.login == user.login}"
             ><form method="post"
             ><input type="hidden" name="login" value="${user.login}"
             ><button class="btn btn-default" type="submit" name="action" value="edit"
             ><i class="glyphicon glyphicon-edit"></i></button></c:if
             ><c:if test="${operator.role.code == 'ADMIN   ' || operator.login == user.login}"
             ><button class="btn btn-default" type="submit" name="action" value="delete"
             ><i class="glyphicon glyphicon-remove"></i></button></form></c:if
           ></td>
        </tr>
        </c:forEach>
    </table>
</div></body>
</html>

