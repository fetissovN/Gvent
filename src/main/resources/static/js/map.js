
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
function Event(lat,lng,name,desc){
    this.latitute = lat;
    this.longitute = lng;
    this.name = name;
    this.description = desc;
}


var map, infoWindow;
var newEvent = null;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.758570, lng: -73.985077},
        zoom: 17
    });
    // var trafficLayer = new google.maps.TrafficLayer(); SHOWS TRAFFIC ON THE MAP
    // trafficLayer.setMap(map); SHOWS TRAFFIC ON THE MAP
    infoWindow = new google.maps.InfoWindow;

    google.maps.event.addListener(map, 'click', function(event) {
        newEvent = new Event(event.latLng.lat(),event.latLng.lng(),"name","desc");
        showChoiceBox();

        placeMarker(event.latLng);

    });

    function placeMarker(location) {
        var marker = new google.maps.Marker({
            position: location,
            map: map
        });
    }
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

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

function showChoiceBox() {
    console.log(newEvent);
    var box = $('.mapClickChoice');
    box.show();
    var btn_add = $('.createEvent');
    var btn_cancel = $('.cancelEvent');
    btn_add.on('click', createEvent);
    btn_cancel.on('click', closeChoiceBox);
}

function createEvent() {
    console.log(newEvent);
    var d = JSON.stringify();
    console.log(d);
    $.ajax({
        type: 'GET',
        url: '/api/createEvent',
        contentType: "application/json; charset=utf-8",
        data: d,
        success: function(data){
            alert(data);
        },
        error: function () {
            alert("fail!");
        }
    });
}

function closeChoiceBox() {
    alert("closed");
    var box = $('.mapClickChoice');
    box.hide();
}

console.log("!map.js");