
var userCookieChecked = false;
var eventsTakePartTriggerLoaded = false;
var eventsCreatorTriggerLoaded= false;
var eventArrParticipant = [];
var eventArrCreator = [];

function getUserAjax() {
    $.ajax({
        type: 'GET',
        url: '/api/user/getUserInfo',
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('user' in data){
                var user = data.user;
                if (checkCookieValid(user)){
                    console.log("validCookie");
                }else {
                    console.log("newCookie");
                    setSTDCookie(user);
                    console.log("setted");
                }
                userCookieChecked = true;
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getMarkersFromDbPrivate(id) {
    // var request = JSON.stringify({"user":id});
    $.ajax({
        type: 'GET',
        url: '/api/user/getUsersEvents/',
        data:'userId='+id,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('events' in data){
                console.log(data);
                for(var i=0;i<data.events.length;i++){
                    eventArrCreator.push(data.events[i]);
                }
                eventsCreatorTriggerLoaded= true;
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function removeEvent(id) {
    $.ajax({
        type: 'DELETE',
        url: '/api/event/removeEvent/'+id,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('fail' in data){
                console.log('no such id');
            }
            if('deleted' in data){
                var cardToDelete = $('.card_m[data-id='+id+']');
                cardToDelete.remove();
                closeChatWindow();
                // var parent = cardToDelete.parent();
                // parent.remove();
                // $('.refresh').click();

            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getEventsFromDbTakePartIn(id) {
    $.ajax({
        type: 'GET',
        url: '/api/user/getUsersEventsTakePart/',
        data: "userId="+id,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('events' in data){
                for(var i=0;i<data.events.length;i++){
                    eventArrParticipant.push(data.events[i]);
                }
                eventsTakePartTriggerLoaded = true;
                console.log(data);
            }
        },
        error: function () {
            alert('fail');
        }
    });
}


function setSTDCookie(userJson) {
    $.cookie('userId',userJson.id,{
        expires: 5,
        path: '/'
    });
    $.cookie('userNickname',userJson.username,{
        expires: 5,
        path: '/'
    });
    $.cookie('enabled',userJson.enabled,{
        expires: 5,
        path: '/'
    });
    $.cookie('firstName',userJson.firstName,{
        expires: 5,
        path: '/'
    });
    $.cookie('lastName',userJson.lastName,{
        expires: 5,
        path: '/'
    });
}

function deletSTDCookie() {
    $.cookie('userId',null);
    $.cookie('userNickname',null);
    $.cookie('enabled',null);
    $.cookie('firstName',null);
    $.cookie('lastName',null);
}

function checkCookieValid(userJson) {
    var id = $.cookie('userId');
    var nickname = $.cookie('userNickname');
    var enabled = $.cookie('enabled');
    var firstName = $.cookie('firstName');
    var lastName = $.cookie('lastName');
    if (id!=null && nickname!=null && enabled!= null && firstName!=null && lastName!=null){
        return userJson.id == id;
    }
    return false;
}

function createCards() {
    console.log(eventArrParticipant);
    console.log(eventArrCreator);
    var container = $('.dashboard-space-events');
    for(var i = 0;i<eventArrCreator.length;i++){
        // var del = $('<div></div>');
        // del.addClass('deleteEvent');
        // del.attr('data-id',eventArrCreator[i].id);
        var card = $('<div></div>');
        var name = $('<p></p>');
        var descr = $('<p></p>');
        descr.addClass('descr');
        descr.text(eventArrCreator[i].description);
        name.addClass('card_m-name');
        name.text(eventArrCreator[i].name);
        card.addClass('card_m');
        card.addClass('creator');
        card.addClass('card-visible');
        card.attr('draggable','true');
        card.attr('data-id', eventArrCreator[i].id);
        // card.append(del);
        card.append(name);
        card.append(descr);
        container.append(card);
    }
    for(var i = 0;i<eventArrParticipant.length;i++){
        var card = $('<div></div>');
        var name = $('<p></p>');
        var descr = $('<p></p>');
        descr.addClass('descr');
        descr.text(eventArrParticipant[i].description);
        name.addClass('card_m-name');
        name.text(eventArrParticipant[i].name);
        card.addClass('card_m');
        card.addClass('card-visible');
        card.attr('draggable','true');
        card.attr('data-id', eventArrParticipant[i].id);
        // card.append(del);
        card.append(name);
        card.append(descr);
        container.append(card);
    }
}
function closeChatWindow() {
    $('.cardEvent_chat_wrapper').hide();
    $('.chat-menu-wrapper').hide();
}

function setFirstLastName() {
    var div = $('.user-info-name');
    var firstName = $.cookie('firstName');
    var lastName = $.cookie('lastName');
    console.log(firstName);
    console.log(lastName);
    if (firstName != null && lastName != null) {
        div.text(firstName + ' ' + lastName);
    }
}
function run() {
    var timerLoadTP = setInterval(function () {
        if (userCookieChecked){
            console.log('run');
            var id = $.cookie('userId');
            getEventsFromDbTakePartIn(id);
            userCookieChecked = false;
            clearInterval(timerLoadTP);
        }
    },300);
    var timerLoadC = setInterval(function () {
        console.log('wait for creator events');
        if (eventsTakePartTriggerLoaded){
            var id = $.cookie('userId');
            getMarkersFromDbPrivate(id);
            clearInterval(timerLoadC);
        }
    },500);

    var timerAllLoaded = setInterval(function () {
        console.log('check all true');
        if (eventsCreatorTriggerLoaded && eventsTakePartTriggerLoaded){
            console.log('are true');
            createCards();
            clearInterval(timerAllLoaded);
        }
    },500);
}

$(document).on('click','.deleteEvent',function () {
    var b = this.getAttribute('data-id');
    removeEvent(b);
});

getUserAjax();
run();

//set first/last name on the dashboard from cookie
setFirstLastName();


// show/hide SEND button --------------
$("input").keyup(function () {
    if ($(this).val()) {
        $(".chatBody_btn_send").show();
    }
    else {
        $(".chatBody_btn_send").hide();
    }
});

$(".closeBtn").click(closeChatWindow);
// CLOSE button END-------------

$(document).on('click', '.card_m', function () {
    console.log(this);
    var id = this.getAttribute('data-id');
    var pName = $(this).find('.card_m-name');
    var name = pName.text();
    console.log(name);
    console.log(id);
    var eventDelDiv = $('.deleteEvent');
    eventDelDiv.attr('data-id', id);
    // var instantCard = null;
    // for (var i = 0; i < eventArrCreator.length; i++) {
    //     if (eventArrCreator[i].id == id) {
    //         instantCard = eventArrCreator[i];
    //     }
    // }
    // console.log(instantCard);

    $('.chatHead_eventAddress').text(name);
    $(".cardEvent_chat_wrapper").show();
});



///////////// dashboard chat dropdown menu ////////////
$(document).ready(function() {
    $('.event-options, .chat-menu-list, .chat-menu-list li').on('click', function(){
        $('.chat-menu-wrapper').toggle();
    });
});
//////////// dashboard chat dropdown menu END ///////////