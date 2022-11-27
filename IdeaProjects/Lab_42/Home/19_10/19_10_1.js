class Worker {
    constructor(name, surname, rate, days) {
        this._name = name;
        this._surname = surname;
        this._rate = rate;
        this._days = days;
    }

    getName() {
        return this._name;
    }

    getSurname() {
        return this._surname;
    }

    rate = function(num) {
        if (!arguments.length) {
            return this._rate;
        }
        else {
            this._rate = num;
        }
    };

    days = function(num) {
        if (!arguments.length) {
            return this._days;
        }
        if ((num >= 10) && (num <= 31)) {
            this._days = num;
        }
    };

    salary = function(num) {
        if (!arguments.length) {
            return this._rate * this._days;
        }
        else {
            return this._rate * num;
        }
    };
}

var worker = new Worker('Иван', 'Иванов', 10, 31);

console.log(worker.getName()); //выведет 'Иван'
console.log(worker.getSurname()); //выведет 'Иванов'
console.log(worker.rate()); //выведет 10
console.log(worker.days()); //выведет 31
console.log(worker.salary()); //выведет 310 - то есть 10*31
//Теперь используем сеттер:
worker.rate(20); //зададим ставку
worker.days(10); //зададим количество дней (проверка: сумма рабочих дней должна быть не меньше 10 и не больше 31)
worker.salary(10); //задаем зарплату (не изменяя количество рабочих дней)
console.log(worker.salary()); //выведет 200 - то есть 20*10