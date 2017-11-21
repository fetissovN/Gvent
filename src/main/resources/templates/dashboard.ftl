<!doctype html>
<html lang="en">

    <head>

	</head>
	
    <body>
    <#include "bar.ftl">


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

                <ul class="chatHead_menu">
                    <li> &#8285 </li>
                    <li class="closeBtn"> &#10005 </li>
                </ul>

            </div>

            <div class="bodyWrapper">
                <div class="chatBody_userList"> users </div>
                <div class="chatBody_chatWindow">

                    <div class="chatBody_displayer">
                    </div>

                    <div class="chatBody_typer">
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
    <script src="js/eventCard.js"> </script>
    </body>
</html>