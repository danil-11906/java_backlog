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
    <link rel="stylesheet" href="/css/menu.css">
</head>
<body>

<div class="back">
    <header>
        <div class="logo">Анализы Z</div>
        <nav class="menu" id="my-menu">
            <li><a class="nav-menu" href="/profile">
                    <svg margin-top="1px" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                        <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                        <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                    </svg>
                    <span>МОЙ ПРОФИЛЬ</span>
                </a></li>
            <li><a class="nav-menu" href="/list">СПИСОК УСЛУГ</a></li>
            <li><a class="nav-menu" href="/doctors">ВРАЧИ</a></li>
            <li><a class="nav-menu" href="/users">ПОЛЬЗОВАТЕЛИ</a></li>
        </nav>
        <div class="bm">
            <a href="#" class="burger_menu" id="burger_menu">
                <svg width="30px" height="30px" viewBox="0 0 16 16" class="bi bi-list" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                </svg>
            </a>
        </div>
    </header>
</div>


<script>
    let refetch = () => {
        $.ajax({
            type: "GET",
            url: "/allservices",
            success: function(response) {
                // рисуем таблицу на основе ответа на запрос
                renderTable(response, $('#table'))
            },
            //тип данных, который мы отпралвяем
            dataType: "json",
            contentType: "application/json"
        })
    }
    // принимает пользователей в формате JSON и таблицу, которую нужно заполнить
    function renderTable(services, table) {

        let innerHtml = '<tr>\n' +
            '               <th>id</th>' +
            '               <th>Name</th>' +
            '               <th>Cost</th>' +
            '               <th>Date</th>' +
            '            </tr>';

        for (let i = 0; i < services.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + services[i]['id'] + '</td>';
            innerHtml += '  <td>' + services[i]['name'] + '</td>';
            innerHtml += '  <td>' + services[i]['cost'] + '</td>';
            innerHtml += '  <td>' + services[i]['date'] + '</td>';
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
            url: "/services", //урл запроса
            data: JSON.stringify(data),  // данные для отправки из JSON-объекта превращаем в JSON-строку
            // // что происходит, если запрос прошел успешнго?
            success: refetch,
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
<div>
    <table id="table">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>cost</th>
            <th>date</th>
        </tr>
        <#list servicesList as services>
            <tr>
                <td>${services.id}</td>
                <td>${services.name}</td>
                <td>${services.cost}</td>
                <td>${services.date}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>