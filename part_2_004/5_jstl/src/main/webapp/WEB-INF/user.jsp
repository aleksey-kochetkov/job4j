<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>
        <c:choose>
        <c:when test="${edit}">Редактирование</c:when>
        <c:otherwise>Создание</c:otherwise>
        </c:choose>
    </title>
</head>
<body><div class="container-fluid">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="action" value="${pageContext.request.getParameter("action")}">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="name" name="name" id="name" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Страна:</label>
            <div class="col-sm-10">
                <select class="form-control" id="country" name="country">
                <c:if test="${empty countryCode}">
                    <option selected disabled>Страна</option>
                </c:if>
                <c:forEach items="${countries}" var="country">
                    <c:choose>
                        <c:when test="${country.code.equals(countryCode)}">
                            <option value="${country.code}" selected>${country.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${country.code}">${country.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="role">Нас. пункт:</label>
            <div class="col-sm-10">
                <select class="form-control" id="city" name="city">
                <c:if test="${empty cityCode}">
                    <option selected disabled>Город</option>
                </c:if>
                <c:forEach items="${cities}" var="city">
                    <c:choose>
                        <c:when test="${city.code.equals(cityCode)}">
                            <option value="${city.code}" selected>${city.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${city.code}">${city.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-5">
                <input class="form-control" type="text" id="login" name="login" id="login" value="${user.login}">
            </div>
            <label class="control-label col-sm-1" for="password">Password:</label>
            <div class="col-sm-4">
                <input class="form-control" type="password" id="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="email" name="email" value="${user.email}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="role">Роль:</label>
            <div class="col-sm-10">
                <c:choose>
                    <c:when test="${operator.role.code == 'root'}">
                <select class="form-control" id="role" name="role">
                    </c:when>
                    <c:otherwise>
                <input type="hidden" id="role" name="role" value="${roleCode}">
                <select class="form-control" name="role" id="role" disabled>
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

    function callback(data, status) {
        var s = "<option selected disabled>Город</option>";
        for(var i = 0; i < data.length; i++) {
            s += "<option value=\"" + data[i].code + "\">" + data[i].name
                                                           + "</option>";
        }
        $("#city").html(s);
    }

    function onCountryChange(element) {
        $.ajax({
            type: "POST",
            url: "json",
            dataType: "json",
            data: JSON.stringify({countryCode: element.value}),
            success: callback
        });
    }

    function validate(name, city, login, password, email) {
        var result = true;
        if (!name) {
            result = false;
            alert("Необходимо ввести имя");
        }
        if (!city) {
            result = false;
            alert("Необходимо ввести населённый пункт");
        }
        if (!login) {
            result = false;
            alert("Необходимо ввести логин");
        }
        if (!password) {
            result = false;
            alert("Необходимо ввести пароль");
        }
        if (!email) {
            result = false;
            alert("Необходимо ввести email");
        }
        return result;
    }

    function process() {
        var name = $("#name").val();
        var city = $("#city").val();
        var login = $("#login").val();
        var password = '${edit}' || $("#password").val();
        var email = $("#email").val();
        if (validate(name, city, login, password, email)) {
            $("form").submit();
        }
    }

    $("button").attr("onclick", "process()");
    $("#country").attr("onchange", "onCountryChange(this)");
</script>
</body>
</html>
