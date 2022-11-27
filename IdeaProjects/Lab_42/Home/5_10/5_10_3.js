function sum(arr) {
    return arr.reduce(function(a, b) {
        return a + b;
    });
}
alert( sum([1, 2, 3]) );

function sumArgs() {
    arguments.reduce = [].reduce;
    return arguments.reduce(function(a, b) {
        return a + b;
    });
}

alert( sumArgs(1,2,3) );