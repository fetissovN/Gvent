<!doctype html>
<html>
    <head>
	    <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Knot</title>
        <link rel="icon" href="/images/logo.png">

        <!-- jQuery -->
    <#--<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>-->
        <script
                src="https://code.jquery.com/jquery-3.0.0.min.js"
                integrity="sha256-JmvOoLtYsmqlsWxa7mDSLMwa6dZ9rrIdtrrVYRnDRH0="
                crossorigin="anonymous">
        </script>
    </head>

    <body>
    <div class="overlay-back"></div>
    <div class ="mapContentBox">

        <!-- Header_navigation_menu -->
        <div class="headNavBox-wrapper">
            <div class="headNavBox-logo">
                <a href="/"> <img src="images/logo.png" alt="logo" style="width:30px;height:30px;"> </a>
                <span class="super">k</span>not
            </div>
            <div class="navigation-wrapper">
                <ul class="nav">
                    <li> <a href="/">Dashboard</a> </li>
                    <li> <a href="/map">MapEvent</a> </li>
                    <li> <a href="">3rd page</a> </li>
                </ul>
            </div>

            <div class="headNav-mapActions-wrapped">
                <div class="refresh"> <i class="material-icons">refresh</i> </div>
                <div class="my-location"> <i class="material-icons">my_location</i> </div>
                <div class="search"> <i class="material-icons">search</i> </div>
            </div>
        </div>
        <!-- Header_navigation_menu END -->


        <div class="createEventWindow_wrapper">
            <div class="createEventWindow">
                <div class="createEventWindow_header">
                    <input class="nameIn" placeholder="Add title..."/>
                    <div class="createEvent_cancelBtn"> <i class="material-icons">close</i> </div>
                </div>

                <div class="createEventWindow_body">
                    <input class="descIn" placeholder="Add description..."/>
                    <div class="createEvent_createBtn"> <span>CREATE</span> </div>
                </div>
            </div>
        </div>
        <div id="map"></div>
    </div>
    <script src="/js/map.js"> </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbDY4iinrNtE_w231GN3mwj3OCt2Lz8kI&callback=initMap"> //GOOGLE MAPS API KEY
    </script>

    </body>
</html>