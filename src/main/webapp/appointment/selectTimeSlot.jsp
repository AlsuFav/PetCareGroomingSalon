<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 30.10.2024
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Выбор времени</title>
</head>
<body>
<h2>Выберите время записи</h2>

<form method="post" action="selectTimeSlot">
    <c:forEach var="date" items="${availableSlots}">
        <h3>${date.key}</h3>
            <c:forEach var="slot" items="${date.value}">
                <input type="radio" name="timeSlotId" value="${slot.id}" required>
                    ${slot.getTime()} (Грумер: ${slot.groomer.firstName}) <br>
            </c:forEach>
    </c:forEach>

    <br>

    <button type="submit">Далее</button>
</form>

<c:if test="${empty availableSlots}">
    <p>К сожалению, нет свободных мест для записи :(.</p>
    <p><a href="clientProfile">Перейти в профиль</a></p>
</c:if>

</body>
</html>