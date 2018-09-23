<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid">
    <form class="form-horizontal" id="mainForm" method="post">
        <input type="hidden" name="action" value="${pageContext.request.getParameter("action")}Save">
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Логин</label>
            <div class="col-sm-5">
                <c:choose>
                <c:when test="${edit}">${user.login}
                    <input type="hidden" id="login" name="login" value="${user.login}">
                </c:when>
                <c:otherwise>
                    <input class="form-control" type="text" id="login"
                           name="login" id="login" value="${user.login}">
                </c:otherwise>
                </c:choose>
            </div>
            <label class="control-label col-sm-1" for="password">Пароль</label>
            <div class="col-sm-4">
                <input class="form-control" type="password" id="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Имя</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="name" name="name" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="address">Адрес</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="address"
                          name="address" value="${user.address.address}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="music">Жанр</label>
            <div class="col-sm-10">
                <select multiple class="form-control" id="music" name="music">
                <c:forEach items="${musicTypes}" var="musicType">
                    <c:choose>
                        <c:when test="${user.hasMusicTypeCode(musicType.code)}">
                            <option value="${musicType.code}" selected>${musicType.description}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${musicType.code}">${musicType.description}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="role">Роль</label>
            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${operator.role.code == 'ADMIN   '}">
                <select class="form-control" id="role" name="role">
                    </c:when>
                    <c:otherwise>
                <input type="hidden" id="role" name="role" value="${roleCode}">
                <select class="form-control" name="role" id="role" disabled>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${roles}" var="role">
                    <c:choose>
                        <c:when test="${role.code == roleCode}">
                            <option value="${role.code}" selected>${role.description}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${role.code}">${role.description}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="button">
                    Сохранить
                </button>
            </div>
        </div>
    </form>
</div>
<script>

    function validate(login, password, name, address) {
        var result = true;
        if (!login) {
            result = false;
            alert("Необходимо ввести логин");
        }
        if (!password) {
            result = false;
            alert("Необходимо ввести пароль");
        }
        if (!name) {
            result = false;
            alert("Необходимо ввести имя");
        }
        if (!address) {
            result = false;
            alert("Необходимо ввести адрес");
        }
        return result;
    }

    function process() {
        var login = $("#login").val();
        var password = '${edit}' || $("#password").val();
        var name = $("#name").val();
        var address = $("#address").val();
        if (validate(login, password, name, address)) {
            $("#mainForm").submit();
        }
    }

    $("button").attr("onclick", "process()");
    $("#country").attr("onchange", "onCountryChange(this)");
</script>
</body>
</html>
