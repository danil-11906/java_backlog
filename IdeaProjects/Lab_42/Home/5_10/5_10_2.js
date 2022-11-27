function Calculator() {
    this.a = 0;
    this.b = 0;
    this.read = function() {
        this.a = parseInt(prompt('Первое число', '0'));
        this.b = parseInt(prompt('Второе число', '0'));
    };
    this.sum = function() {
        return this.a + this.b;
    };
    this.mul = function() {
        return this.a * this.b;
    };
}

let calculator = new Calculator();
calculator.read();

alert( calculator.sum() );
alert( calculator.mul() );