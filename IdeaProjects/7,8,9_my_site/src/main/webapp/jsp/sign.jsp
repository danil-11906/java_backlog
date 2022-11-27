<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Authorization</title>
    <link rel="stylesheet" href="/css/sign.css">
</head>
<body>
<t:menu/>
<form action="/signIn" method="post">
    <div class="container">
        <h1>Authorization</h1>
        <p>Please fill out this form to log in to your account.</p>
        <hr>
        <label><b>Login</b></label>
        <input type="text" name="login" placeholder="Login">
        <label><b>Password</b></label>
        <input type="password" name="password" placeholder="Password">
        <hr>
        <button class="button" type="submit">Send</button>
    </div>
    <div class="container signin">
        <p>You don't have account? <a href="/signUp">Registration</a>.</p>
    </div>
</form>
</body>
</html>