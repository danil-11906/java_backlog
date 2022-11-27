var burger_menu = document.getElementById("burger_menu");

burger_menu.onclick = function() {
    var x = document.getElementById('my-menu');

    if (x.className === "menu") x.className += " responsive";
    else x.className = "menu";
};