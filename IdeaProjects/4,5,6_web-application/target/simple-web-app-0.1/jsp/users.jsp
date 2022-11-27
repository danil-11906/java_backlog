<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="models.User" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>FROM JSP USERS</h1>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>SURNAME</th>
        </tr>
        <c:forEach items="${usersForJsp}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
            </tr>
        </c:forEach>
<%--        <%--%>
<%--            List<User> users = (List<User>) request.getAttribute("usersForJsp");--%>
<%--            for ( int i = 0; i < users.size(); i++) {--%>
<%--            %>--%>
<%--        <tr>--%>
<%--            <td> <%=users.get(i).getId()%></td>--%>
<%--            <td> <%=users.get(i).getFirstName()%></td>--%>
<%--            <td> <%=users.get(i).getLastName()%></td>--%>
<%--            <td> <%=users.get(i).getAge()%></td>--%>
<%--        </tr>--%>
<%--        <%}%>--%>
    </table>
</div>
</body>
</html>
