<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 31.10.2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Добавить информацию о питомце</title>
</head>
<body>
<h1>Добавить информацию о питомце</h1>

<form action="addPet" method="post">
    Имя: <input type="text" name="name" required><br>
    Дата рождения: <input type="date" name="birthDate" required><br>
    Вид: ${sessionScope.species}<br>
    <input type="hidden" name="species" value="${sessionScope.species}">

    <c:if test="${sessionScope.species == 'собака'}">
        <p>Порода:
            <select name="breedId" required>
                <c:forEach var="breed" items="${breeds}">
                    <option value="${breed.id}">${breed.name}</option>
                </c:forEach>
            </select>
        </p>
    </c:if>

    <button type="submit">Добавить питомца</button>
</form>

<br>
<p><a href="clientProfile">Вернуться в профиль клиента</a></p>
</body>
</html>
