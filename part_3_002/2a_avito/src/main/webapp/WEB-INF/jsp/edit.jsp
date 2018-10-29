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
 /* The switch - the box around the slider */
    .switch {
      position: relative;
      display: inline-block;
      width: 60px;
      height: 34px;
    }
/* Hide default HTML checkbox */
    .switch input {display:none;}
/* The slider */
    .slider {
      position: absolute;
      cursor: pointer;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background-color: #ccc;
      -webkit-transition: .4s;
      transition: .4s;
    }
    .slider:before {
      position: absolute;
      content: "";
      height: 26px;
      width: 26px;
      left: 4px;
      bottom: 4px;
      background-color: white;
      -webkit-transition: .4s;
      transition: .4s;
    }
    input:checked + .slider {
      background-color: #2196F3;
    }
    input:focus + .slider {
      box-shadow: 0 0 1px #2196F3;
    }
    input:checked + .slider:before {
      -webkit-transform: translateX(26px);
      -ms-transform: translateX(26px);
      transform: translateX(26px);
    }
/* Rounded sliders */
    .slider.round {
      border-radius: 34px;
    }
    .slider.round:before {
      border-radius: 50%;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Подать объявление</a>
    </div>
    <div class="navbar-form navbar-right" action="" method="post">
      <a class="nav-link" href="#">${operator.name}</a>
    </div>
  </div>
</nav>

<div class="container">
  <form class="form-horizontal" id="mainForm" name="mainForm" method="POST" enctype="multipart/form-data">
    <c:if test="${!empty ad}">
      <input type="hidden" name="id" value="${ad.id}">
    </c:if>
    <div class="form-group">
      <label class="control-label col-sm-2" for="mark">Марка</label>
      <div class="col-sm-8">
        <select class="form-control" id="mark" name="mark"
                                    <c:if test="${view}">disabled</c:if>>
          <option disabled selected>—</option>
          <c:forEach items="${marks}" var="mark">
          <option value="${mark.id}"
              <c:if test="${ad.model.mark.id == mark.id}">selected</c:if>
                                                   >${mark.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="model">Модель</label>
      <div class="col-sm-8">
        <select class="form-control" id="model" name="model"
                                    <c:if test="${view}">disabled</c:if>>
          <option disabled selected>—</option>
          <c:forEach items="${models}" var="model">
            <option value="${model.id}"
              <c:if test="${ad.model.id == model.id}">selected</c:if>
                                                  >${model.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="year">Год</label>
      <div class="col-sm-8">
        <select class="form-control" id="year" name="year"
                                    <c:if test="${view}">disabled</c:if>>
          <option disabled selected>—</option>
          <option
            <c:if test="${ad.year == 2011}">selected</c:if>>2011</option>
          <option
            <c:if test="${ad.year == 2010}">selected</c:if>>2010</option>
          <option
            <c:if test="${ad.year == 2009}">selected</c:if>>2009</option>
          <option
            <c:if test="${ad.year == 2008}">selected</c:if>>2008</option>
          <option
            <c:if test="${ad.year == 2007}">selected</c:if>>2007</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="km">Пробег</label>
      <div class="col-sm-7">
        <input class="form-control" type="text" id="km" name="km"
                   value="${ad.km}" <c:if test="${view}">disabled</c:if>>
      </div>
      <div class="col-sm-1">км</div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="carBody">Кузов</label>
      <div class="col-sm-8">
        <select class="form-control" id="carBody" name="carBody"
                                    <c:if test="${view}">disabled</c:if>>
          <option disabled selected>—</option>
          <c:forEach items="${carBodies}" var="carBody">
            <option value="${carBody.id}"
              <c:if test="${ad.carBody.id == carBody.id}">selected</c:if>
                                            >${carBody.descript}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="engine">Двигатель</label>
      <div class="col-sm-8">
        <div class="btn-group" data-toggle="buttons">
          <c:forEach items="${engines}" var="engine">
            <label class="btn btn-default
              <c:choose>
              <c:when test="${ad.engine.id == engine.id}">active</c:when>
              <c:when test="${view}">disabled</c:when>
              </c:choose>">
              <c:if test="${!view}">
              <input type="radio" name="engine" value="${engine.id}"
                <c:if test="${ad.engine.id == engine.id}">checked</c:if>>
              </c:if>${engine.descript}
            </label>
          </c:forEach>
        </div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="transmission">
        Коробка передач
      </label>
      <div class="col-sm-8">
        <div class="btn-group" data-toggle="buttons">
          <c:forEach items="${transmissions}" var="transmission">
            <label class="btn btn-default
              <c:choose>
              <c:when test="${ad.transmission.id == transmission.id}">active</c:when>
              <c:when test="${view}">disabled</c:when>
              </c:choose>">
              <c:if test="${!view}">
              <input type="radio" name="transmission"
                                               value="${transmission.id}"
                  <c:if test="${ad.transmission.id == transmission.id}">
                    checked
                  </c:if>>
              </c:if>${transmission.descript}
            </label>
          </c:forEach>
        </div>
      </div>
    </div>
    <hr>
    <div class="form-group">
      <label class="control-label col-sm-2" for="price">Цена</label>
      <div class="col-sm-7">
        <input class="form-control" type="text" id="price" name="price"
                 value="${ad.price}"<c:if test="${view}">disabled</c:if>>
      </div>
      <div class="col-sm-1">₽</div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">Фотография</label>
      <div class="col-sm-8">
        <label for="file">
          <img
             <c:choose>
             <c:when test="${empty ad.id}">src="unselected.png"</c:when>
             <c:otherwise>src="car?id=${ad.id}"</c:otherwise>
             </c:choose>
            style="pointer-events: none; -webkit-user-select: none;
                   -khtml-user-select: none; -moz-user-select: none;
                   -o-user-select: none; user-select: none;" id="image">
          <c:if test="${empty view}">
          <input style='visibility: hidden; width: 0; height: 0'
                                        type="file" id="file" name="file"
                                           onchange="onFileChange(this)">
          </c:if>
        </label>
      </div>
    </div>
    <c:if test="${!empty ad}">
    <div class="form-group">
      <label class="control-label col-sm-2">Продано</label>
      <div class="col-sm-10">
        <!-- Rounded switch -->
        <label class="switch">
          <input type="checkbox" name="closed"
                                 <c:if test="${ad.closed}">checked</c:if>
                                    <c:if test="${view}">disabled</c:if>>
          <span class="slider round"></span>
        </label>
      </div>
    </div>
    </c:if>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-default" type="button" id="save"
                                     name="save" onclick="onSaveClick()"
                                   <c:if test="${view}">disabled</c:if>>
          Сохранить
        </button>
      </div>
    </div>
  </form>
</div>

<script>
  function onMarkChangeCallback(data, status) {
    var s = "<option disabled selected>—</option>";
    for(var i = 0; i < data.length; i++) {
      s += "<option value=\"" + data[i].id + "\">" + data[i].name
                                                           + "</option>";
    }
    $("#model").html(s);
  }
  function onMarkChange(element) {
    $.ajax({
      type: "POST",
      url: "json",
      data: {action: "findModelsByMarkId", markId: element.value},
      success: onMarkChangeCallback
    });
  }
  function onFileChange(f) {
    var reader = new FileReader();
    reader.readAsDataURL(f.files[0]);
    reader.onloadend = function(event) {
      var img = document.getElementById("image");
      img.src = event.target.result;
    };
  }
  function validate(mark, model, year, km, carBody, engine, transmission,
                                                           price, file) {
    var result = true;
    if (!mark) {
      result = false;
      alert("Необходимо указать марку");
    }
    if (!model) {
      result = false;
      alert("Необходимо указать модель");
    }
    if (!year) {
      result = false;
      alert("Необходимо указать год");
    }
    if (!km) {
      result = false;
      alert("Необходимо указать пробег");
    }
    if (!carBody) {
      result = false;
      alert("Необходимо указать кузов");
    }
    if (!engine) {
      result = false;
      alert("Необходимо указать двигатель");
    }
    if (!transmission) {
      result = false;
      alert("Необходимо указать коробку передач");
    }
    if (!price) {
      result = false;
      alert("Необходимо указать цену");
    }
    <c:if test="${empty ad}">
    if (!file) {
      result = false;
      alert("Необходимо разместить изображение");
    }
    </c:if>
    return result;
  }
  function onSaveClick() {
    var mark = $("#mark").val();
    var model = $("#model").val();
    var year = $("#year").val();
    var km = $("#km").val();
    var carBody = $("#carBody").val();
    var engine = $("[name=engine]:checked").val();
    var transmission = $("[name=transmission]:checked").val();
    var price = $("#price").val();
    var file = $("#file").val();
    if (validate(mark, model, year, km, carBody, engine, transmission,
                                                          price, file)) {
      $("#mainForm").submit();
    }
  }
  $("#mark").attr("onchange", "onMarkChange(this)");
</script>
</body>
</html>