<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выбор питомца и услуги</title>
</head>
<body>
<h2>Выберите питомца и услугу</h2>

<form method="post" action="selectAppointmentOptions">
    <label for="petId">Питомец:</label>
    <select name="petId" id="petId" required>
        <c:forEach var="pet" items="${pets}">
            <option value="${pet.id}">${pet.name} (${pet.species})</option>
        </c:forEach>
    </select>

    <label for="serviceId">Услуга:</label>
    <select name="serviceId" id="serviceId" required>
        <c:forEach var="service" items="${services}">
            <option value="${service.id}">${service.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Далее</button>
</form>
</body>
</html>