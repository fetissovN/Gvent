
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

var map, infoWindow;
var choiceBoxEnabled = false;
var newEvent = null;
var createEventBool = false;
var closeChoiceBool = false;

function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.758570, lng: -73.985077},
        zoom: 17
    });
    // var trafficLayer = new google.maps.TrafficLayer(); SHOWS TRAFFIC ON THE MAP
    // trafficLayer.setMap(map); SHOWS TRAFFIC ON THE MAP
    infoWindow = new google.maps.InfoWindow;

    google.maps.event.addListener(map, 'click', function(event) {
        newEvent = new Event(null,null,null,null,event.latLng.lat(),event.latLng.lng());
        if (choiceBoxEnabled == false){
            choiceBoxEnabled = true;
            showChoiceBox();
            placeMarker(event.latLng);
        }

        if (createEventBool){
            createEventBool = false;
        }else if (closeChoiceBool){
            closeChoiceBool = false;
        }
        showOverlays();
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
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

setMarkersFromDb();

function setMarkersFromDb() {
    var method = "all";
    var obj = {"name":method};
    console.log(obj);
    var request = JSON.stringify(obj);
    console.log(request);
    $.ajax({
        type: 'GET',
        url: '/api/getAll'+request,
        // contentType: "application/json",
        // data: request,
        success: function(data){
            alert(data);
        },
        error: function () {
            alert('fail');
        }
    });
}

function setAllMap(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function setAllMapDB(map) {
    for (var i = 0; i < markersDB.length; i++) {
        markersDB[i].setMap(map);
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
    console.log(marker);
    markers.push(marker);
    return marker;
}


function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

function showChoiceBox() {
    var box = $('.mapClickChoice');
    box.show();
    var btn_add = $('.createEvent');
    var btn_cancel = $('.cancelEvent');
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
                closeChoiceBox(false);
                document.location.href = '/login';
            }else if (data == 1){
                closeChoiceBox(false);
                createEventBool = true;
            }else if (data == 0 ){
                createEventBool = false;
            }
        },
        error: function () {
            createEventBool = false;
        }
    });
}

function closeChoiceBox(delLastMarker) {
    var inp_name = $('.nameIn');
    var inp_desc = $('.descIn');
    inp_name.val('');
    inp_desc.val('');
    var box = $('.mapClickChoice');
    box.hide();
    closeChoiceBool = true;
    clearOverlays();
    if (delLastMarker){
        deleteLastMarker();
    }
    showOverlays();
    choiceBoxEnabled = false;
}
