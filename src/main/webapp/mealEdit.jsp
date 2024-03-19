<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Edit meal</h2>
    <form method="post" action="meals?action=mealslist" name="addMeal">
        <section>
            <label for="mealId"></label>
            <input id="mealId" type="hidden"  name="mealId" value="<c:out value="${meal.id}"/>">
        </section>
        <section>
            <label for="dateTime">Date/Time: </label>
            <input id="dateTime" type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}" />">
        </section>
        <section>
            <label for="description">Description: </label>
            <input id="description" type="text" name="description" value="<c:out value="${meal.description}"/>">
        </section>
        <section>
            <label for="calories">Calories: </label>
            <input id="calories" type="text" name="calories" value="<c:out value="${meal.calories}"/>">
        </section>
        <input type="submit" value="Submit">
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</body>
</html>