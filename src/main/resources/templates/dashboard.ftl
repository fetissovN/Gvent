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
    <#--<#include "bar.ftl">-->

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
                <#--<div class="refresh"> <i class="material-icons">refresh</i> </div>-->
                <#--<div class="my-location"> <i class="material-icons">my_location</i> </div>-->
                <#--<div class="search"> <i class="material-icons">search</i> </div>-->
            </div>
        </div>
        <!-- Header_navigation_menu END -->

    <!-- Card section -->
    <div class="card_m"  draggable="true"> <h6>Message: Kate</h6>
        LOL, or lol, is an acronym for laugh(ing) out loud or lots of laughs and a popular element of Internet slang.
        It was first used almost exclusively on Usenet, but has since become widespread in other forms of computer-mediated communication and even face-to-face communication.
        It is one of many initialisms for expressing bodily reactions, in particular laughter, as text, including initialisms for more emphatic expressions of laughter such as LMAO ("laugh(ing) my ass off")
        and ROFL (or its older form ROTFL; "roll(ing) on the floor laughing"). Other unrelated expansions include the now mostly obsolete "lots of luck" or "lots of love" used in letter-writing.
    </div>

    <div class="cardZip_m"  draggable="true"> <h6>8100 East 7th Streer, Charlotte, NC 28202</h6>
        <p>Chat</p>
    </div>

    <div class="card_m"  draggable="true"> <h6>Message: Nick</h6>
        LOL, or lol, is an acronym for laugh(ing) out loud or lots of laughs and a popular element of Internet slang.
        It was first used almost exclusively on Usenet, but has since become widespread in other forms of computer-mediated communication and even face-to-face communication.
        It is one of many initialisms for expressing bodily reactions, in particular laughter, as text, including initialisms for more emphatic expressions of laughter such as LMAO ("laugh(ing) my ass off")
        and ROFL (or its older form ROTFL; "roll(ing) on the floor laughing"). Other unrelated expansions include the now mostly obsolete "lots of luck" or "lots of love" used in letter-writing.
    </div>

    <div class="card_m"  draggable="true"> <h6>John send you a picture</h6>
        <img src="images/image.jpeg" alt="img" draggable="false" style="width:75%;height:75%;">
    </div>
    <!-- Card section END -->

    <#--******************-->
    <div class="cardEvent_chat_wrapper">
        <div class="cardEvent_chat">

            <div class="chatHead_title">
                <p class="chatHead_eventAddress"> 810 East 7th Streer, Charlotte, NC 28202 </p>

                <div class="chatHead_menu">
                    <div class="event-options"> <i class="material-icons">more_vert</i> </div>
                    <div class="closeBtn"> <i class="material-icons">close</i> </div>
                </div>

            </div>

            <div class="bodyWrapper">
                <div class="chatBody_userList"> users </div>
                <div class="chatBody_chatWindow">

                    <div class="chatBody_displayer">
                    </div>

                    <div class="chatBody_typer">
                        <form>
                            <input class="chatBody_field_input" type="text" name="sendAMessage" placeholder="Send a message">
                            <input class="chatBody_btn_send" type="submit" value="&#10148">
                            <!-- <form action="/action_page.php"> -->
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <#--********************-->
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="js/jquery.cookie.js"> </script>
    <script src="js/eventCard.js"> </script>
    </body>
</html>