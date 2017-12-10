
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
function Event(id,userId,name,desc,lat,lng){
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.description = desc;
    this.latitude = lat;
    this.longitude = lng;
}

var markers = [];
var markersDB = [];
var markersDBPrivate = [];

var isLoaded = false;
var triggerMapLoaded = false;

var map, infoWindow;
var newEvent = null;
var currentPositionWithZoom = {
    latLng:null,
    boundaries:null
};
var zoom = null;

function initMap() {
    console.log('init');
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.758570, lng: -73.985077},
        zoom: 17
    });
    // var trafficLayer = new google.maps.TrafficLayer(); SHOWS TRAFFIC ON THE MAP
    // trafficLayer.setMap(map); SHOWS TRAFFIC ON THE MAP
    infoWindow = new google.maps.InfoWindow;

    google.maps.event.addListener(map, 'rightclick', function(event) {
        newEvent = new Event(null,null,null,null,event.latLng.lat(),event.latLng.lng());
        var box = $('.createEventWindow_wrapper');
        if (box.is(":visible")){
            return;
        }else {
            showChoiceBox();
            placeMarker(event.latLng);
            showOverlays();
        }
    });
    $('.refresh').on('click', function() {
        isLoaded = false;
        currentPositionWithZoom.latLng = map.center;
        currentPositionWithZoom.boundaries = map.getBounds();
        zoom = map.getZoom();
        deleteArrayMarkersAndMarkersDB();
        run();
    });
    var btn_add = $('.createEvent_createBtn');
    var btn_cancel = $('.createEvent_cancelBtn');
    var btn_private = $('.showPrivateBtn');
    var btn_take_part_in= $('.showIAmParticipateInBtn');
    btn_add.on('click', createEvent);
    btn_cancel.on('click', function (){
        closeChoiceBox(true);
    });
    btn_private.on('click',function () {
        clearOverlays();
        deleteArrayMarkers();
        checkMapLoaded();
        setAllMarkersDBPrivateLocation();
    });
    btn_take_part_in.on('click', function () {
        clearOverlays();
        deleteArrayMarkers();
        checkMapLoaded();
        setAllMarkerDBTakePartLocation();
    });
    $(document).on('click', '.participate', function() {
        var b = this.getAttribute('data-id');
        participate(b);
    });
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('You are here!');
            infoWindow.open(map);
            map.setCenter(pos);

            currentPositionWithZoom.latLng = pos;
            currentPositionWithZoom.boundaries = map.getBounds();
            zoom = map.getZoom();
            run();
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

function checkMapLoaded() {
    triggerMapLoaded = false;
    var timer = setInterval(function () {
        if (!triggerMapLoaded){
            if (currentPositionWithZoom.latLng != null && currentPositionWithZoom.boundaries != null){
                // console.log('not null');
                triggerMapLoaded = true;
                clearInterval(timer);
            }else {
                // console.log('map not loaded yet');
                navigator.geolocation.getCurrentPosition(function(position) {
                    var pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };
                    currentPositionWithZoom.latLng = map.setCenter(pos);
                    currentPositionWithZoom.boundaries = map.getBounds();
                });
            }
        }
    },1000);


}

function setAllMap(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function setAllMarkerDBLocation() {
    convertFromMarkersDBToMarkers(false);
    setTimeout(showOverlays,500);
}

function setAllMarkerDBTakePartLocation() {
    convertFromMarkersDBToMarkers(false, true);
    setTimeout(showOverlays,500);
}

function setAllMarkersDBPrivateLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var currLat = position.coords.latitude;
            var currLng = position.coords.longitude;
            var latPrev = currentPositionWithZoom.latLng.lat;
            var lngPrev = currentPositionWithZoom.latLng.lng;
            if (Math.abs(currLat-latPrev) < 0.00001 && Math.abs(currLng-lngPrev) < 0.00001
                    && zoom == map.getZoom()){
                console.log('same location');
                console.log(markers);
                convertFromMarkersDBToMarkers(true);
                zoom = map.getZoom();
                setTimeout(showOverlays,500);
            }else {
                console.log('was refreshed');
                deleteArrayMarkersAndMarkersDB();
                getMarkersFromDbWithBoundaries();
                //2) checking trigger(isLoaded) that markersBD[] is not empty
                var timer = setInterval(function() {
                    console.log("not loaded");
                    if (isLoaded){
                        console.log("loaded");
                        convertFromMarkersDBToMarkers(true);
                        console.log('converted');
                        setTimeout(showOverlays,500);
                        // showOverlays();
                        console.log('show');
                        clearInterval(timer);
                        isLoaded = false;
                    }
                }, 50);
                zoom = map.getZoom();
            }
        })
    }else {
        //TO DO show error message, that navigation not avaliable
    }
}

function convertFromMarkersDBToMarkers(owner, takesPart) {
    for (var i = 0; i < markersDB.length; i++) {
        if (takesPart){
            if (checkIsParticipant(markersDB[i].participants)){
                placeSingleMarkerFromMarkerDB(markersDB[i]);
            }
        }else {
            if (!owner){
                placeSingleMarkerFromMarkerDB(markersDB[i]);
            }else {
                if (checkCookieOwner(markersDB[i].userId)){
                    placeSingleMarkerFromMarkerDB(markersDB[i]);
                }
            }
        }
    }
}

function placeSingleMarkerFromMarkerDB(marDB) {
    var content = marDB.description;
    var title = marDB.name;
    var pos = {
        lat: +marDB.latitude,
        lng: +marDB.longitude
    };
    var idEvent = marDB.id;
    var userId = marDB.userId;
    var participants = marDB.participants;
    placeMarker(pos, content, title, idEvent, userId, participants);
}

// Removes the overlays from the map, but keeps them in the array.
function clearOverlays() {
    for (var i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
}

function deleteArrayMarkersAndMarkersDB() {
    clearOverlays();
    markers = [];
    markersDB = [];
}

function deleteArrayMarkers() {
    clearOverlays();
    markers = [];
}

function deleteLastMarker() {
    markers.pop();
}

// Shows any overlays currently in the array.
function showOverlays() {
    setAllMap(map);
}

function placeMarker(location, contentInfo, title, idEvent, userId, participants) {
    var customBox = createCustomInfoWindow(contentInfo, idEvent, userId, participants);
    var infowindow = new google.maps.InfoWindow({
        content: customBox.html()
    });
    var marker = new google.maps.Marker({
        position: location,
        title: title
    });
    marker.addListener('click', function() {
        infowindow.open(map, marker);
    });
    markers.push(marker);
    return marker;
}

function showChoiceBox() {
    var box = $('.createEventWindow_wrapper');
    box.show();
}

function closeChoiceBox(delLastMarker) {
    var inp_name = $('.nameIn');
    var inp_desc = $('.descIn');
    inp_name.val('');
    inp_desc.val('');
    var box = $('.createEventWindow_wrapper');
    box.hide();
    if (delLastMarker){
        clearOverlays();
        deleteLastMarker();
        showOverlays();
    }

}

function createCustomInfoWindow(content,id, userId, participants) {
    var box = $('<div></div>');
    box.addClass('customInfo');
    var insideBox = $('<div></div>').addClass('custom');
    var p = $('<p></p>').addClass('size');
    if(participants != null){
        p.text(participants.length+': participants');
    }
    var button = $('<button></button>').text('Participate');
    button.addClass('participate');
    button.attr('data-id',id);
    if (checkCookieOwner(userId)){
        button.text("You are Creator");
        button.prop('disabled', true);
    }
    if (checkIsParticipant(participants)){
        button.text("You are participant");
        button.prop('disabled', true);
    }
    var desc = $('<p></p>').text('description: '+content);
    // desc.textContent = content;
    insideBox.append(desc);
    insideBox.append(p);
    insideBox.append(button);
    box.append(insideBox);
    return box;
}


function checkCookieOwner(idUserOfEvent) {
    var id = $.cookie('userId');
    return id == idUserOfEvent;
}

function checkIsParticipant(participantsArr) {
    var id = $.cookie('userId');
    return jQuery.inArray(+id, participantsArr) !== -1;
}

function createEvent() {
    var inp_name = $('.nameIn');
    var inp_desc = $('.descIn');

    newEvent.name = inp_name.val();
    newEvent.description = inp_desc.val();
    var d = JSON.stringify(newEvent);
    $.ajax({
        type: 'POST',
        url: '/api/event/createEvent',
        contentType: "application/json",
        data: d,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('event' in data){
                closeChoiceBox(false);
                markersDB.push(data.event);
                deleteArrayMarkers();
                setAllMarkerDBLocation(markersDB);
                showOverlays();
            }
            if ('invalid' in data){

            }
            if ('error' in data){
                console.log(data);
            }
        },
        error: function () {
        }
    });
}

function participate(id) {
    var idInt = +id;
    $.ajax({
        type: 'GET',
        url: '/api/event/addParticipant/'+idInt,
        contentType: "application/json",
        // data: JSON.stringify({"id",id}),
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('event' in data){
                var btn = $('.participate[data-id='+idInt+']');
                console.log(btn);
                btn.text("You are in");
                btn.prop('disabled', true);
            }
            if ('invalid' in data){
                console.log(data.invalid);
            }
            if ('error' in data){
                console.log(data.error);
            }
        },
        error: function () {
        }
    });
}

function getMarkersFromDb() {
    $.ajax({
        type: 'GET',
        url: '/api/event/getAll/absolute',
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('events' in data){
                var arr = data.events;
                for(var i=0;i<arr.length;i++){
                    markersDB.push(arr[i]);
                }
                isLoaded = true;
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getMarkersFromDbWithBoundaries() {
    console.log(triggerMapLoaded);
    checkMapLoaded();
    var timer = setInterval(function () {
        if (triggerMapLoaded){
            console.log('go to db');
            var request = JSON.stringify(currentPositionWithZoom);
            $.ajax({
                type: 'POST',
                url: '/api/event/getAll',
                contentType: "application/json",
                data: request,
                success: function(data){
                    if('auth' in data){
                        document.location.href = '/login';
                    }
                    if('events' in data){
                        var arr = data.events;
                        for(var i=0;i<arr.length;i++){
                            markersDB.push(arr[i]);
                        }
                        isLoaded = true;
                    }
                },
                error: function () {
                    // document.location.href = '/map';
                    console.log('some error');
                }
            });
            clearInterval(timer);
        }

    },1000);

}


function getMarkersFromDbPrivate(id) {
    var request = JSON.stringify({"user":id});
    $.ajax({
        type: 'GET',
        url: '/api/user/getUsersEvents/'+request,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('events' in data){
                console.log(data);
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function removeEvent(id) {
    var request = JSON.stringify({"event":id});
    $.ajax({
        type: 'DELETE',
        url: '/api/event/removeEvent/'+request,
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('deleted' in data){
                console.log(data);
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function run(privateTrigger) {
    console.log('run');
    //Map is already shown
    //1) getting Events from server to markersDB[] variable
    getMarkersFromDbWithBoundaries();
    //2) checking trigger(isLoaded) that markersBD[] is not empty
    var timer = setInterval(function() {
        console.log("not loaded");
        if (isLoaded){
            console.log("loaded");
            //3) convert markersDB[] to markers[] with valid location
            setAllMarkerDBLocation();
            //4) put map to all markers[] with delay 500ms, after that they will be shown to user
            setTimeout(showOverlays,500);
            clearInterval(timer);
            isLoaded = false;
        }
    }, 50);
}
