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
                </ul>
            </div>

            <div class="headNav-mapActions-wrapped">
                <div class="search"> <i class="material-icons">search</i> </div>
                <div class="user-settings"> <i class="material-icons">settings</i> </div>
            </div>
        </div>
        <!-- Header_navigation_menu END -->

        <!-- Card section -->
        <div class="dashboard-wrapper">


            <!--******************-->
            <div class="cardEvent_chat_wrapper">
                <div class="cardEvent_chat">
                    <div class="chatHead_title">
                        <p class="chatHead_eventAddress"> 810 East 7th Street, Charlotte, NC 28202 </p>
                        <div class="chatHead_menu">
                            <div class="event-options"> <i class="material-icons">more_vert</i> </div>
                            <div class="closeBtn"> <i class="material-icons">close</i> </div>
                        </div>
                    </div>
                    <div class="bodyWrapper">
                    <#--dropdown menu-->
                        <div class="chat-menu-wrapper">
                            <ul class="chat-menu-list">
                                <li>
                                    <div class="deleteEvent">
                                        <i class="material-icons">delete_forever</i>
                                        Delete Event
                                    </div>
                                </li>
                            </ul>
                        </div>
                    <#--dropdown menu END-->
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
            <!--********************-->


            <div class="user-info-wrapper">
                <div class="user-info-picture"></div>
                <div class="user-info-summary">
                    <div class="user-info-menu"> </div>
                    <div class="user-info-name">
                        <p> Dennis Kiparyn </p>
                    </div>
                </div>
            </div>

            <div class="dashboard-space">
                <div class="dashboard-space-events">
                    <div class="card_m"  draggable="true">
                        <p class="card_m-name">8100 East 7th Street, Charlotte, NC 28202</p>
                        <p class="descr">Description</p>
                    </div>
                </div>
                <div class="dashboard-space-feed">
                    <div class="feed-card"> <p>Test Feed :)</p> </div>
                </div>
            </div>
        </div>
        <!-- Card section END -->


    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="js/jquery.cookie.js"> </script>
    <script src="js/eventCard.js"> </script>
    </body>
</html>