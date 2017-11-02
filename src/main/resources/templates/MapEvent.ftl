<!--<!doctype html>-->
<html>
    <head>
	    <#--<link rel="stylesheet" type="text/css" href="/css/1.css">-->
		<link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet" rel='stylesheet'>
		<#--<title>Knot</title>-->
		<#--<link rel="icon" href="/images/logo.png">-->
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
        <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 80%;
        }
        
        </style>
    </head>
    
    <body>
        <#include "Bar.ftl">

    <#--<!-- Header_navigation_menu &ndash;&gt;-->
		<#--<div id="header">-->
			<#--<div class="logo">-->
				<#--<a href="Main.ftl"> <img src="/images/logo.png" alt="logo" style="width:30px;height:30px;"> </a>-->
				<#--<span class="super">k</span>not-->
            <#--</div>-->
            <#---->
			<#--<ul class="nav">-->
				<#--<li> <a href="/map">2nd page</a> </li>-->
				<#--<li> <a href="">3rd page</a> </li>-->
				<#--<li> <a href="">4th page</a> </li>-->
				<#--<li> <span id="text" title="Help text should be here." style="cursor:help;"> Need help?</span> </li>-->
				<#--<li> <a href="#searchbox"> Search </a> </li>-->
				<#--<!-- <li> <div class="user"> K </div> </li> &ndash;&gt;-->
			<#--</ul>			 -->
		<#--</div>-->
		<#---->
		<#--<div id="searchbox"> -->
			<#--<input type="search" id="search" placeholder="Search Knot.com">-->
        <#--</div>-->
        <#--<!-- Header_navigation_menu END &ndash;&gt;-->
        <#---->
        <!-- GOOGLE MAPS BLOCK -->
        <div id="map"></div>
        <!-- GOOGLE MAPS BLOCK END -->

    <script src="/js/1.js"> </script>

    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBbDY4iinrNtE_w231GN3mwj3OCt2Lz8kI&callback=initMap"> //GOOGLE MAPS API KEY
    </script>

    </body>
</html>