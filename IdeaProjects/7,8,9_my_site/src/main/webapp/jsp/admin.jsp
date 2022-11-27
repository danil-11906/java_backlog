<%@ page import="models.User" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="/css/profile.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:admin/>
<h1>PROFILE</h1>
<div>
    <table>
        <tr>
            <th>NAME</th>
            <th>SURNAME</th>
            <th>LOGIN</th>
        </tr>
        <%User result = (User) request.getAttribute("userForJsp");%>
        <tr>
            <td><%=result.getName()%></td>
            <td><%=result.getSurname()%></td>
            <td><%=result.getLogin()%></td>
        </tr>
    </table>
</div>
<form action="/admin" method="post">
    <button class="button" type="submit">Выйти из профиля</button>
</form>
</body>
</html>