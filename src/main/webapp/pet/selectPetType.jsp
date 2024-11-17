<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 31.10.2024
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Выбор вида питомца</title>
</head>
<body>
<h1>Выберите вид питомца</h1>

<form action="selectPetType" method="post">
    <label>
        <input type="radio" name="species" value="собака" required>
        Собака
    </label>
    <label>
        <input type="radio" name="species" value="кошка" required>
        Кошка
    </label>
    <br><br>
    <button type="submit">Продолжить</button>
</form>

<br>
<p><a href="clientProfile">Вернуться в профиль клиента</a></p>
</body>
</html>