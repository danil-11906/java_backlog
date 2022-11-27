<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отзывы</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:menu/>
<style>
    .box_review {
        width: 150px;
        padding: 10px;
        border-radius: 20%;
        border-color: #4CAF50;
        border-style: solid;
        border-width: 4px;
    }
</style>
<form action="/reviews" method="post">
    <label><b>Review</b></label>
    <input type="text" name="text" placeholder="Review">
    <button class="button" type="submit">Отправить</button>
    <div class="element">
        <c:forEach items="${reviewsForJsp}" var="reviews">
            <div class="box_review">
                <p><b>${reviews.id}</b></p>
                <p>${reviews.text}</p>
            </div>
            <br>
        </c:forEach>
    </div>
</form>
</body>
</html>