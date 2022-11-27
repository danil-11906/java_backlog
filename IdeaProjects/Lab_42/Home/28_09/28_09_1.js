var obj = {
    className: 'menu'
}

function addClass(obj, newBlock) {
    if (obj.className.indexOf(" " + newBlock + " ") !== -1) {
        return 1;
    }
    else {
        obj.className = obj.className + " " + newBlock;
    }
}


function deleteClass(obj, oldBlock){
    obj.className = " " + obj.className + " ";
    var start = obj.className.indexOf(" " + oldBlock + " ") + 1;
    var end = start + oldBlock.length - 1;
    obj.className = obj.className.substring(1,start-1) + obj.className.substring(end);
}
addClass(obj, 'block-new');
addClass(obj, 'blue');
addClass(obj, 'block');
alert( obj.className );
deleteClass(obj, 'blue');
alert(obj.className);