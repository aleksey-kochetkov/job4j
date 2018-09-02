<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/servlet" method="post">
        <table>
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><input type="text" name="login" id="login"></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit" name="action" value="add">Сохранить</button></td>
            </tr>
        </table>
    </form>
</body>
</html>
