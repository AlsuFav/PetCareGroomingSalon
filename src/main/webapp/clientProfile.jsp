<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Client Profile</title>
</head>
<body>
<h1>Добро пожаловать, ${client.firstName}!</h1>


<h2>Ваши питомцы:</h2>
<c:if test="${not empty pets}">
    <c:forEach var="pet" items="${pets}">
        ${pet.name} <br>
    </c:forEach>
</c:if>


<c:if test="${empty pets}">
    <p>Список питомцев пуст</p>
</c:if>

<br>

<p><a href="/addPet">Добавить питомца</a></p>

<br>


<h2>Предстоящие записи:</h2>
<c:if test="${not empty upcomingAppointments}">
    <c:forEach var="appointment" items="${upcomingAppointments}">
            Питомец: ${appointment.pet.name} <br>
            Услуга: ${appointment.service.name} <br>
            Дата: ${appointment.getDateAndTime()} <br>
            Грумер: ${appointment.groomer.firstName} <br>
            Цена: ${appointment.price}₽ <br>
    </c:forEach>
</c:if>

<c:if test="${empty upcomingAppointments}">
    <p>Нет предстоящих записей.</p>
</c:if>


<br>

<p><a href="selectAppointmentOptions">Записаться на груминг</a></p>

</body>
</html>