class User {
    constructor(name, surname) {
        this.name = name;
        this.surname = surname;
    }
    getInfo() {
        return this.name + " " + this.surname;
    }
}

class Student extends User {
    constructor (name, surname, year){
        super(name, surname);
        this.year = year;
    }
    getCourse() {
        let now = new Date(),
            nowYear = now.getFullYear();
        return nowYear - this.year;
    }
    getInfo() {
        return super.getInfo() + " студент " + this.getCourse() + " го курса ";
    }
}


var student = new Student('Иван', 'Иванов', 2017);
console.log(student.name); //выведет 'Иван'
console.log(student.surname); //выведет 'Иванов'
console.log(student.getInfo()); //выведет 'Иван Иванов - студент 3 курса'
console.log(student.year); //выведет 2017
console.log(student.getCourse()); //выведет 3 - третий курс, так как текущий год