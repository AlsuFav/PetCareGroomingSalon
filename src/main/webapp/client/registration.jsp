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
<form action="${pageContext.request.contextPath}/register" method="POST">
    <!-- Имя -->
    <div>
        <label for="firstName">Имя:</label>
        <input type="text" id="firstName" name="firstName" value="${param.firstName}" required>
        <c:if test="${not empty errorFirstName}">
            <p style="color: red;">${errorFirstName}</p>
        </c:if>
    </div>

    <!-- Фамилия -->
    <div>
        <label for="lastName">Фамилия:</label>
        <input type="text" id="lastName" name="lastName" value="${param.lastName}">
        <c:if test="${not empty errorLastName}">
            <p style="color: red;">${errorLastName}</p>
        </c:if>
    </div>

    <!-- Телефон -->
    <div>
        <label for="phone">Телефон:</label>
        <input type="text" id="phone" name="phone" value="${param.phone}">
        <c:if test="${not empty errorPhone}">
            <p style="color: red;">${errorPhone}</p>
        </c:if>
    </div>

    <!-- Email -->
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${param.email}" required>
        <c:if test="${not empty errorEmail}">
            <p style="color: red;">${errorEmail}</p>
        </c:if>
    </div>

    <!-- Пароль -->
    <div>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
        <c:if test="${not empty errorPassword}">
            <p style="color: red;">${errorPassword}</p>
        </c:if>
    </div>

    <!-- Подтверждение пароля -->
    <div>
        <label for="confirmPassword">Подтвердите пароль:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <c:if test="${not empty errorConfirmPassword}">
            <p style="color: red;">${errorConfirmPassword}</p>
        </c:if>
    </div>

    <button type="submit">Зарегистрироваться</button>
</form>

<p>Уже зарегистрированы? <a href="login.jsp">Войдите</a>.</p>
</body>
</html>