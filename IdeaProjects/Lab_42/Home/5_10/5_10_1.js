var calculator = {
    read() {
        this.a = parseInt(prompt('Первое число','0'));
        this.b = parseInt(prompt('Второе число','0'));
    },
    sum() {
        return this.a + this.b;
    },
    mul() {
        return this.a * this.b;
    }
};

calculator.read();
alert( calculator.sum() );
alert( calculator.mul() );