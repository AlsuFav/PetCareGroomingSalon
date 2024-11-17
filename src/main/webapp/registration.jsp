<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 07.11.2024
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="register" method="POST">
    <div>
        <label for="firstName">Имя:</label>
        <input type="text" id="firstName" name="firstName" required>
    </div>
    <div>
        <label for="lastName">Фамилия:</label>
        <input type="text" id="lastName" name="lastName">
    </div>
    <div>
        <label for="phone">Телефон:</label>
        <input type="text" id="phone" name="phone">
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>
    <div>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <label for="confirmPassword">Подтвердите пароль:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <button type="submit">Зарегистрироваться</button>
</form>

<!-- Отображение ошибок -->
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<p>Уже зарегистрированы? <a href="login.jsp">Войдите</a>.</p>
</body>
</html>