<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Музыкальная площадка</title>
</head>
<body><div class="container-fluid">
<c:if test="${!empty error}">
    <div class="alert alert-danger">Ошибка авторизации</div>
</c:if>
<h2>Авторизуйтесь</h2>
<form class="form-horizontal" action="${pageContext.request.contextPath}/auth" method="post">
    <div class="form-group">
        <label class="control-label col-sm-2" for="login">login</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" id="login" name="login">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">password</label>
        <div class="col-sm-10">
            <input class="form-control" type="password" id="password" name="password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-default" type="submit" value="Вход">
        </div>
    </div>
</form>
</div></body>
</html>
