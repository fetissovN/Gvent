$(document).ready(function(){
    $(".btn_logIn").click(function(){
        $(".formBlock_logIn").show();
        $(".str_logIn").show();
    });
    $(".btn_logIn").click(function(){
        $(".formBlock_signUp").hide();
        $(".str_signUp").hide();
    });
});

$(document).ready(function(){
    $(".btn_signUp").click(function(){
        $(".formBlock_signUp").show();
        $(".str_signUp").show();
    });
    $(".btn_signUp").click(function(){
        $(".formBlock_logIn").hide();
        $(".str_logIn").hide();
    });
});