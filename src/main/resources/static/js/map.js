
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

var map, infoWindow;
var newEvent = null;
var currentPositionWithZoom = {
    latLng:null,
    zoom:17
};

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.758570, lng: -73.985077},
        zoom: 17
    });
    // var trafficLayer = new google.maps.TrafficLayer(); SHOWS TRAFFIC ON THE MAP
    // trafficLayer.setMap(map); SHOWS TRAFFIC ON THE MAP
    infoWindow = new google.maps.InfoWindow;

    google.maps.event.addListener(map, 'rightclick', function(event) {
        newEvent = new Event(null,null,null,null,event.latLng.lat(),event.latLng.lng());
        showChoiceBox();
        console.log(event.latLng);
        placeMarker(event.latLng);
        showOverlays();
    });
    google.maps.event.addListener($('.refresh'), 'click', function(event) {
        alert('add listener');
        console.log(event.zoom);
        currentPositionWithZoom.latLng = event.latLng;
        currentPositionWithZoom.zoom = event.zoom;
        console.log(currentPositionWithZoom);
        run(event.latLng,event.zoom);
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
            console.log(currentPositionWithZoom);
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

function setAllMap(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function setAllMarkerDBLocation(arr) {
    console.log(arr);
    for (var i = 0; i < arr.length; i++) {
        var pos = {
            lat: +arr[i].latitude,
            lng: +arr[i].longitude
        };
        placeMarker(pos);
    }
}

// Removes the overlays from the map, but keeps them in the array.
function clearOverlays() {
    for (var i = 0; i < markers.length; i++){
        markers[i].setMap(null);
    }
}

function deleteLastMarker() {
    markers.pop();
}

// Shows any overlays currently in the array.
function showOverlays() {
    setAllMap(map);
}

function placeMarker(location) {
    var marker = new google.maps.Marker({
        position: location,
    });
    markers.push(marker);
    return marker;
}

function showChoiceBox() {
    var box = $('.createEventWindow_wrapper');
    console.log(box);
    box.show();
    var btn_add = $('.createEvent_createBtn');
    var btn_cancel = $('.createEvent_cancelBtn');
    btn_add.on('click', createEvent);
    btn_cancel.on('click', function (){
        closeChoiceBox(true);
    });
}

function createEvent() {
    var inp_name = $('.nameIn');
    var inp_desc = $('.descIn');

    newEvent.name = inp_name.val();
    newEvent.description = inp_desc.val();
    var d = JSON.stringify(newEvent);
    $.ajax({
        type: 'POST',
        url: '/api/createEvent',
        contentType: "application/json",
        data: d,
        success: function(data){
            if (data == "authFail"){
                document.location.href = '/login';
            }else if (data == 1){
                closeChoiceBox();
            }else if (data == 0 ){
            }
        },
        error: function () {
        }
    });
}

function getMarkersFromDb() {
    $.ajax({
        type: 'GET',
        url: '/api/getAll/absolute',
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('events' in data){
                var arr = data.events;
                for(var i=0;i<arr.length;i++){
                    markersDB.push(arr[i]);
                    isLoaded = true;
                }
            }
        },
        error: function () {
            alert('fail');
        }
    });
}

function getMarkersFromDbWithBoundaries() {

    var request = JSON.stringify(currentPositionWithZoom);
    console.log(request);
    $.ajax({
        type: 'POST',
        url: '/api/getAll',
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
                    isLoaded = true;
                }
            }
        },
        error: function () {
            alert('fail');
        }
    });
}


function getMarkersFromDbPrivate(id) {
    var request = JSON.stringify({"user":id});
    $.ajax({
        type: 'GET',
        url: '/api/getUsersEvents/'+request,
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
        url: '/api/removeEvent/'+request,
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

function closeChoiceBox(delLastMarker) {
    var inp_name = $('.nameIn');
    var inp_desc = $('.descIn');
    inp_name.val('');
    inp_desc.val('');
    var box = $('.createEventWindow_wrapper');
    box.hide();
    clearOverlays();
    if (delLastMarker){
        deleteLastMarker();
    }
    showOverlays();
}

function run() {
    //Map is already shown
    //1) getting Events from server to markersDB[] variable
    getMarkersFromDbWithBoundaries();
    // getMarkersFromDb();

    //2) checking trigger(isLoaded) that markersBD[] is not empty
    var timer = setInterval(function() {
        console.log("not loaded");
        if (isLoaded){
            console.log("loaded");
            //3) convert markersDB[] to markers[] with valid location
            setAllMarkerDBLocation(markersDB);
            //4) put map to all markers[] with delay 500ms, after that they will be shown to user
            setTimeout(showOverlays,500);
            clearInterval(timer);
            // isLoaded = false;
        }
    }, 50);
}