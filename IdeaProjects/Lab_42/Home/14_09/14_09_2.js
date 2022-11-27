const number = parseInt(prompt('Введите число'));
alert(factorial(number))
function factorial(n){
    var result = 0, a=n;
    while(n > 0){
        result += n--;
    }
    if (a===0){result=0;}
    if (a<0){return alert('Вы ввели неправильное число')}
    else{return result;}
}
