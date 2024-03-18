<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        table, th, td{
            border: 1px solid black;
        }
        .green {color: green}
        .red {color: red}
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <table>
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach var="meal" items="${meallist}">
            <tr class="${meal.excess ? 'red' : 'green'}">
                <td>${meal.dateTimeNormalized}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>