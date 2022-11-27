<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:menu/>
<form action="/bag" method="post">
    <div class="element">
        <c:forEach items="${serviceUser}" var="services">
            <div>
                <p>${services.name}</p>
            </div>
        </c:forEach>
        <button class="button" type="submit">Send</button>
    </div>
</form>
</body>
</html>

