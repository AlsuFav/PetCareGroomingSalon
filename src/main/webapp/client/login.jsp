<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h2>Вход</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br><br>

    <button type="submit">Войти</button>
</form>

<c:if test="${param.error == 'empty'}">
    <p style="color:red;">Пожалуйста, заполните все поля.</p>
</c:if>

<c:if test="${param.error == 'invalid'}">
    <p style="color:red;">Неверный email или пароль. Попробуйте еще раз.</p>
</c:if>


</body>
</html>

