var arr = [];
function text () {
    var number = parseInt(prompt('Введите число'));
    var i = 0;
    while ((!isNaN(number))||(number===null)||(number==="")){
        arr[i] = number;
        i++;
        number = parseInt(prompt('Введите число'));
    }
    return "Ответ....";
}
function  sum (arr) {
    var sum = 0;
    for (var i = 0; i < arr.length;i++) {
        sum += arr[i];
    }
    return sum;
}
alert(text());
alert(sum(arr))
