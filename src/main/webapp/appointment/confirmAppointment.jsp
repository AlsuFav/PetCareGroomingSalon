<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подтверждение записи</title>
</head>
<body>
<h2>Подтверждение записи</h2>

<p>Питомец: ${selectedPet.name}</p>
<p>Услуга: ${selectedService.name}</p>
<p>Время записи: ${selectedTimeSlot.getDateAndTime()}</p>
<p>Стоимость: ${price}₽ </p>

<form method="post" action="confirmAppointment">
    <input type="hidden" name="price" value="${price}">
    <button type="submit">Подтвердить запись</button>
</form>
</body>
</html>
