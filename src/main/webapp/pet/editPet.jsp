<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 31.10.2024
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Редактировать питомца</title>
</head>
<body>
<h1>Редактировать питомца</h1>

<form action="editPet" method="post">
    <input type="hidden" name="petId" value="${pet.id}">

    Имя: <input type="text" name="name" value="${pet.name}" required><br>
    Дата рождения: <input type="date" name="birthDate" value="${pet.birthDate}" required><br>
    Вид: <input type="text" name="species" value="${pet.species}" readonly><br>

    <c:if test="${pet.species == 'собака'}">
        Порода:
            <select name="breedId">
                <c:forEach var="breed" items="${breeds}">
                    <option value="${breed.id}"
                            <c:if test="${pet.breed != null && pet.breed.id == breed.id}">selected</c:if>>
                            ${breed.name}
                    </option>
                </c:forEach>
            </select><br>
    </c:if>

    <button type="submit">Сохранить</button>
</form>
</body>
</html>
