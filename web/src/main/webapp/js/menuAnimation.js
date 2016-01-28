
$(document).ready(function () {
    // управление анимацией меню
    $('.slideable ul').hide(); // сворачиваем все вложенные списки
    $('.slideable a').bind('click',function () {
        $(this).siblings('ul').slideToggle(function() { // разворачиваем список по клику
            // сворачиваем предыдущий список
            $(".slideable ul").not(this).slideUp();
        });
    });


});


