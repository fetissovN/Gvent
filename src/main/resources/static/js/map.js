
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
    boundaries:null
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
        placeMarker(event.latLng);
        showOverlays();
    });
    $('.refresh').on('click', function() {
        isLoaded = false;
        currentPositionWithZoom.latLng = map.center;
        currentPositionWithZoom.boundaries = map.getBounds();
        deleteArrayMarkersAndMarkersDB();
        run();
    });
    var btn_add = $('.createEvent_createBtn');
    var btn_cancel = $('.createEvent_cancelBtn');
    btn_add.on('click', createEvent);
    btn_cancel.on('click', function (){
        closeChoiceBox(true);
    });
    $(document).on('click', '.participate', function() {
        var btn = $('.participate');
        participate(btn.attr('data-id'));

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

    for (var i = 0; i < arr.length; i++) {
        var content = arr[i].description;
        var title = arr[i].name;
        var pos = {
            lat: +arr[i].latitude,
            lng: +arr[i].longitude
        };
        var id = arr[i].id;
        placeMarker(pos, content, title, id);
    }
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

function placeMarker(location, contentInfo, title, id) {
    var customBox = createCustomInfoWindow(contentInfo, id);
   console.log(customBox);
   console.log(customBox.html());
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

function createCustomInfoWindow(content,id) {
    var box = $('<div></div>');
    box.addClass('customInfo');
    var insideBox = $('<div></div>').addClass('custom');
    var button = $('<button></button>').text('Participate');
    button.addClass('participate');
    button.attr('data-id',id);
    // button.click(function () {
    //    participate(id);
    // });
    var desc = $('<p></p>').text(content);
    // desc.textContent = content;
    insideBox.append(desc);
    insideBox.append(button);
    box.append(insideBox);
    return box;
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
    console.log(idInt);
    $.ajax({
        type: 'GET',
        url: '/api/addParticipant/'+idInt,
        contentType: "application/json",
        // data: JSON.stringify({"id",id}),
        success: function(data){
            if('auth' in data){
                document.location.href = '/login';
            }
            if('event' in data){
                var btn = $('.participate');
                btn.text("You are in");
                btn.prop('disabled', true);
                console.log(data.event);
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
        url: '/api/getAll/absolute',
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
    var request = JSON.stringify(currentPositionWithZoom);
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
                }
                isLoaded = true;
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