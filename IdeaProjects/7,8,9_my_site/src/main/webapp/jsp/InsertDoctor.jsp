<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/sign.css">
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>
<t:admin/>
<form action="/insert" method="post" enctype="multipart/form-data">
    <div class="container">
        <h1>Insert</h1>
        <hr>
        <label><b>Name</b></label>
        <input type="text" name="name" placeholder="Name">
        <label><b>Surname</b></label>
        <input type="text" name="secondName" placeholder="Surname">
        <label><b>Last Name</b></label>
        <input type="text" name="lastName" placeholder="Last Name">
        <label><b>Position</b></label>
        <input type="text" name="position" placeholder="Position">
        <label><b>Experience</b></label>
        <input type="text" name="exp" placeholder="Exp">
        <hr>
        <input type="file" name="file">
        <input class="button" type="submit" value="File upload">
    </div>
</form>
</body>
</html>