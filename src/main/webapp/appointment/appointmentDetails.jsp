<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 31.10.2024
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Информация о записи</title>
</head>
<body>
<h1>Детали записи</h1>

<p>Дата: ${appointment.getDateAndTime()}</p>
<p>Питомец: ${appointment.pet.name}</p>
<p>Услуга: ${appointment.service.name}</p>
<p>Грумер: ${appointment.groomer.firstName}</p>
<p>Цена: ${appointment.price}₽</p>

<form action="cancelAppointment" method="post">
    <input type="hidden" name="appointmentId" value="${appointment.id}">
    <button type="submit">Отменить запись</button>
</form>

<p><a href="clientProfile">Вернуться в профиль</a></p>

</body>
</html>