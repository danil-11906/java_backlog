<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Услуги</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:menu/>
    <div class="element">
        <c:forEach items="${servicesForJsp}" var="services">
        <div>
            <p><b>Название:</b> ${services.name}</p>
            <p><b>Стоимость:</b> ${services.cost}</p>
            <p><b>Время проведения анализа:</b> ${services.date}</p>
            <br>
        </div>
        </c:forEach>
    </div>
</body>
</html>
