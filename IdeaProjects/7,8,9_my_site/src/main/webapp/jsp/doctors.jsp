<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Врачи</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<t:menu/>
<style>
    .about {
        display: inline-block;
    }
    .info {
        float: right;
        margin-top: 10px;
        margin-left: 20px;
    }
    img {
        height: 15%;
    }
    .photo {
        float: left;
        /*margin-left: 150px;*/
        margin-top: 10px;
        padding-bottom: 10px;
        padding-left: 10px;
    }
</style>
<div class="element">
    <c:forEach items="${DocsForJsp}" var="doctors">
        <div class="about">
            <div class="photo">
                <img src="http://localhost:8080/uploaded/files?id=${doctors.id}">
            </div>
        <div class="info">
            <p><b>Номер:</b> ${doctors.id}</p>
            <p><b>Имя:</b> ${doctors.name}</p>
            <p><b>Фамилия:</b> ${doctors.secondName}</p>
            <p><b>Отчество:</b> ${doctors.lastName}</p>
            <p><b>Стаж:</b> ${doctors.position}</p>
            <p><b>Опыт:</b> ${doctors.exp}</p>
        </div>
        </div>
        <br>
    </c:forEach>
</div>
</body>
</html>
