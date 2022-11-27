function getTeachers() {
    $.get("http://localhost/usersNode", function (data) {
        let html = '';

        for (let i = 0; i < data.length; i++) {
            html += '<tr>' +
                '<td>' + data[i].id + '</td>' +
                '<td>' + data[i].name + '</td>' +
                '<td>' + data[i].surname + '</td>' +
                '<td>' + data[i].email + '</td>';
        }

        $('#header_table').after(html);
    })
}

function getDoctors() {
    $.get("http://localhost/docNode", function (data) {
        let html = '';

        for (let i = 0; i < data.length; i++) {
            html += '<tr>' +
                '<td>' + '<img id="image" src="http://localhost/files/' + data[i].image + '">' + '</td>' +
                '<td>' + data[i].name + '</td>' +
                '<td>' + data[i].secondname + '</td>' +
                '<td>' + data[i].lastname + '</td>' +
                '<td>' + data[i].position + '</td>' +
                '<td>' + data[i].exp + '</td>';
        }

        $('#header_table').after(html);
    })
}