<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Калькулятор услуг</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:menu/>
<div class="element">
    <form action="/calculator" method="post">
        <div class="container">
            <c:forEach items="${servicesForJsp}" var="services">
                <input type="checkbox" name="${services.name}">
                <label>${services.name}</label>
                <br>
            </c:forEach>
            <button class="button" type="submit">Send</button>
        </div>
    </form>
</div>
</body>
</html>