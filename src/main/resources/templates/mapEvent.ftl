<!doctype html>
<html>
    <head>
	    <link rel="stylesheet" type="text/css" href="/css/main.css">
		<link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </head>
    
    <body>
    <div class="overlay-back"></div>
        <div class ="mapContentBox">
        <#include "bar.ftl">
            <div class="refresh_wrapper">
                <button class="refresh">refresh</button>
            </div>
                <div class="createEventWindow_wrapper">
                    <div class="createEventWindow">
                        <div class="createEventWindow_header">
                            <input class="nameIn" placeholder="Add title..."/>
                            <button class="createEvent_cancelBtn">&#10005</button>
                        </div>

                        <div class="createEventWindow_body">
                            <input class="descIn" placeholder="Add description..."/>
                            <button class="createEvent_createBtn">CREATE</button>
                        </div>
                    </div>
                </div>
            <#--<div class="mapClickChoice">-->
                <#--<input class="nameIn" placeholder="name"/>-->
                <#--<input class="descIn" placeholder="description"/>-->
                <#--<button class="createEvent">Create new one</button>-->
                <#--<button class="cancelEvent">Cancel</button>-->
            <#--</div>-->
            <div id="map"></div>
        </div>


    <script src="/js/map.js"> </script>
    <script src="js/jquery.cookie.js"> </script>
    <script src="js/mapEvent.js"> </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbDY4iinrNtE_w231GN3mwj3OCt2Lz8kI&callback=initMap"> //GOOGLE MAPS API KEY
    </script>

    </body>
</html>