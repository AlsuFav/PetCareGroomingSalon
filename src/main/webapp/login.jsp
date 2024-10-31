<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h2>Вход</h2>

<form action="login" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br><br>

    <button type="submit">Войти</button>
</form>

<%
    String error = request.getParameter("error");
    if ("empty".equals(error)) {
%>
<p style="color: red;">Пожалуйста, заполните все поля.</p>
<%
} else if ("invalid".equals(error)) {
%>
<p style="color: red;">Неверный email или пароль. Попробуйте еще раз.</p>
<%
    }
%>
</body>
</html>

