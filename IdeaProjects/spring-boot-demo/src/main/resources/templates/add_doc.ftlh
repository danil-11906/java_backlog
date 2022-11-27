<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/css/menu.css">
    <link rel="stylesheet" href="/css/sign.css">
    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
<#--спринг бут -->
<#--    миеросервисная архитектура-->
<#--    обеспечение безопасности -->
<#--    спринг мвс-->
<#--    рест-->
<#--    орм кибернейт джпа sql-->
<#--    спринг клауд-->
<#--    и теория 1 сема-->
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
        </header>
    </div>

</head>
<body>

<script>
    function sendFile() {
        let formData = new FormData();
        let files = ($('#file'))[0]['files'];
        [].forEach.call(files, function (file, i, files) {
            formData.append("file", file);
        });

        $.ajax({
            type: "POST",
            url: "/files",
            data: formData,
            processData: false,
            contentType: false
        })
            .done(function (response){
                let fileUrl = 'http://localhost/files' + response;
                $('#photo').append('<img src = "' + fileUrl + '"/>');
            })
            .fail(function () {
                alert('Error')
            });
    }
</script>

<form action="/addDoc" method="post">
    <div class="container">
        <h1>Add Doc</h1>
        <hr>
        <label><b>Doc name</b></label>
        <input type="text" name="name" placeholder="Name">
        <label><b>Doc surname</b></label>
        <input type="text" name="secondname" placeholder="SecondName">
        <label><b>Doc lastName</b></label>
        <input type="text" name="lastname" placeholder="LastName">
        <label><b>Doc position</b></label>
        <input type="text" name="position" placeholder="position">
        <label><b>Doc exp</b></label>
        <input type="text" name="exp" placeholder="exp">
        <input type="file" id="file" name="file" placeholder="Имя файла..."/>
        <input type="hidden" id="file_hidden">
        <hr>
        <button class="button" type="submit" onclick="sendFile()">Send</button>
        <div class="filename"></div>
    </div>
</form>
<div>
    <div id="photo">
    </div>
</div>
</body>
</html>