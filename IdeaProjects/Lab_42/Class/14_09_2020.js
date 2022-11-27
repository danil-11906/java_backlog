var a = "У нас во дворе-подворье погода размокропогодилась"
function f(a){
    while (a==true){
        return(alert(a.indexOf('во')))
        delete a.indexOf('во')
    }
}
alert(f(a))

