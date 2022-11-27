<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
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
<script>
    $.ajax ({
        function (response) {
            // рисуем таблицу на основе ответа на запрос
            renderTable(response, $('#table'))
        },
        //тип данных, который мы отпралвяем
        dataType: "json",
        contentType: "application/json"
    })
    // принимает пользователей в формате JSON и таблицу, которую нужно заполнить
    function renderTable(service, table) {
        let innerHtml = '<tr>\n' +
            '               <th>Name</th>' +
            '               <th>Cost</th>' +
            '               <th>Date</th>' +
            '            </tr>';

        for (let i = 0; i < service.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + service[i]['name'] + '</td>';
            innerHtml += '  <td>' + service[i]['cost'] + '</td>';
            innerHtml += '  <td>' + service[i]['date'] + '</td>';
            innerHtml += '</tr>';
        }

        table.html(innerHtml);
    }

    function sendUser(name, cost, date) {

        let data = {
            "name": name,
            "cost": cost,
            "date": date
        };

        $.ajax({
            type: "POST", // метод запроса
            url: "/update", //урл запроса
            data: JSON.stringify(data), // данные для отправки из JSON-объекта превращаем в JSON-строку
            // что происходит, если запрос прошел успешнго?
            success: function (response) {
                // рисуем таблицу на основе ответа на запрос
                renderTable(response, $('#table'))
            },
            //тип данных, который мы отпралвяем
            dataType: "json",
            contentType: "application/json"
        });
    }
</script>
<div class="container">
    <h1>Insert</h1>
    <hr>
    <label><b>Name</b></label>
    <input type="text" id="name" placeholder="Name">
    <label><b>Cost</b></label>
    <input type="text" id="cost" placeholder="Cost">
    <label><b>Time</b></label>
    <input type="text" id="date" placeholder="Time">
    <hr>
    <button class="button" onclick="sendUser($('#name').val(),
                              $('#cost').val(),
                              $('#date').val())">Добавить</button>
</div>
</body>
<div>
    <table id="table">

    </table>
</div>
</body>
</html>