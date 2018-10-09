<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Автомобили</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    img {
        height: 155px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Автомобили</a>
    </div>
    <div class="navbar-form navbar-right" action="" method="post">
      <a class="nav-link" href="/2c_avito/auth?path=/avito">
        <c:choose>
          <c:when test="${empty operator}">Вход</c:when>
          <c:otherwise>${operator.name}</c:otherwise>
        </c:choose>
      </a>
      <button class="btn btn-default" type="submit" onclick="create()">
        Подать объявление
      </button>
    </div>
  </div>
</nav>

<div class="container">
  <table>
    <c:forEach items="${rows}" var="row">
    <tr>
      <c:forEach items="${row}" var="ad">
      <td>
        <a style="color: inherit" href="edit?id=${ad.id}">
        <c:if test="${!empty ad}">
          <img src="car?id=${ad.id}">
          <b><div class="text-primary">${ad.model.mark.name}
                                       ${ad.model.name}, ${ad.year}</div>
          <div>${ad.price}₽</div></b>
          <div>${ad.km}км</div>
          <div class="text-muted">${ad.formattedCreateDt}</div>
        </c:if>
        </a>
      </td>
      </c:forEach>
    </tr>
    </c:forEach>
  </table>
</div>

</body>
<script>
  function create() {
    location.assign("edit");
  }
</script>
</html>