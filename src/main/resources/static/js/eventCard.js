// show/hide SEND button --------------
$("input").keyup(function () {
    if ($(this).val()) {
        $(".chatBody_btn_send").show();
    }
    else {
        $(".chatBody_btn_send").hide();
    }
});
// show/hide SEND button END ---------


// CLOSE button-----------------

$("div.cardEvent_chat_wrapper").hide();

$(".closeBtn").click(function(){
    $(".cardEvent_chat_wrapper").hide();
});
// CLOSE button END-------------

$(".cardZip_m").click(function(){
    $(".cardEvent_chat_wrapper").show();
});



console.log("test");










