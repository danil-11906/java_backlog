function makeBuffer() {
    var text = '';
    function buffer(word) {
        if(arguments.length === 0) {
            return text;
        }
        text+=word;
    }
    buffer.remove = function(letter) {
        var start = text.indexOf(letter);
        var end = start + letter.length-1;
        text = text.substring(0,start-1) + text.substring(end + 1);
    }
    return buffer;
}
var buffer = makeBuffer();
buffer('Замыкания');
buffer(' Использовать');
buffer(' Нужно!');
alert( buffer() );
buffer.remove('Использовать');
alert( buffer() );