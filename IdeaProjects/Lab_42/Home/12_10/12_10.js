var date;
function arr (obj) {
    if (Array.isArray(obj)) {
        obj.forEach(arr);
    } else {
        alert(obj + " здесь");
    }
}
function checkPresence(x) {
    if (typeof (x) =='string') {
        alert (x + " здесь")
    }
    if (typeof(x) == 'number') {
        alert( "Сегодня на уроке присутствуют " + x + " человека");
    }
    if (typeof (x) == 'boolean') {
        if (x === true) {
            alert("Сегодня все присутствуют на уроке");
        }
        if (x === false) {
            alert("Никого нет :(");
        }
    }
    if (x === date) {
        x = new Date();
        alert(x);
    }
    if (toString.call(x)==='[object Array]') {
        arr(x);
    }
}

checkPresence("Пафнутий");
checkPresence(["Пафнутий", "Ипполит"]);
checkPresence(["Пафнутий", "Ипполит", ["Люба", "Клава"]]);
checkPresence(24);
checkPresence(date);
checkPresence (true);
checkPresence (false);
