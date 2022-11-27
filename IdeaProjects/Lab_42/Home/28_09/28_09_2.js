arr = [ 1, 2, 3, 4, 5 ];
function  getSumsArray(arr) {
    arr = arr.reverse();
    arr[arr.length] = 0;
    arr = arr.reverse();
    for (var i = 1; i < arr.length; i++) {
        arr[i] += arr[i-1];
    }
    arr.shift();
    return arr;
}
alert(getSumsArray(arr));