<%--
  Created by IntelliJ IDEA.
  User: Alsu
  Date: 21.11.2024
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить питомца</title>
    <script>
        function toggleBreedField() {
            const species = document.querySelector('input[name="species"]:checked').value;
            const breedField = document.getElementById('breedField');

            if (species === 'собака') {
                breedField.style.display = 'block';
            } else {
                breedField.style.display = 'none';
            }
        }
    </script>
</head>
<body>
<h1>Добавить питомца</h1>

<form action="addPet" method="post">
    <label>
        <input type="radio" name="species" value="собака" onclick="toggleBreedField()" required>
        Собака
    </label>
    <label>
        <input type="radio" name="species" value="кошка" onclick="toggleBreedField()" required>
        Кошка
    </label>
    <br><br>

    Имя: <input type="text" name="name" required><br>
    Дата рождения: <input type="date" name="birthDate" required><br>


    <div id="breedField" style="display: none;">
        <p>Порода:
            <select name="breedId">
                <c:forEach var="breed" items="${breeds}">
                    <option value="${breed.id}">${breed.name}</option>
                </c:forEach>
            </select>
        </p>
    </div>

    <button type="submit">Добавить питомца</button>
</form>

<br>
<p><a href="clientProfile">Вернуться в профиль клиента</a></p>
</body>
</html>