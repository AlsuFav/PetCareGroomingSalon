<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 31.10.2024
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Профиль питомца</title>
</head>
<body>
<h1>Профиль питомца: ${pet.name}</h1>

<p>Имя: ${pet.name}</p>
<p>Дата рождения: ${pet.birthDate}</p>
<p>Вид: ${pet.species}</p>
<c:if test="${pet.species == 'собака'}">
    <p>Порода: ${pet.breed.name}</p>
</c:if>

<br>
<a href="editPet?petId=${pet.id}">Редактировать</a>

<br>
<form action="deletePet" method="post" style="display:inline;">
    <input type="hidden" name="petId" value="${pet.id}">
    <button type="submit" onclick="return confirm('Вы уверены, что хотите удалить этого питомца?')">Удалить</button>
</form>

<br>
<c:if test="${param.error == 'cancel_appointments_first'}">
    <p style="color:red;">Невозможно удалить питомца. Сначала отмените все предстоящие записи.</p>
</c:if>

<br>
<p><a href="clientProfile">Вернуться в профиль клиента</a></p>

</body>
</html>