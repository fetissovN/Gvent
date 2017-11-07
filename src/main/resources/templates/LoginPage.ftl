<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/LoginPage.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
    <meta charset="utf-8">
    <title>Knot | login</title>
    <link rel="icon" href="logo.png">
</head>

<body>


<div class="wrapper_headTitle">
    <span class="super">k</span>not
    <span class="str_logIn">| Log In</span>
    <span class="str_signUp">| Sign Up</span>
</div>


<div class="signMenu">
    <ul>
        <li class="btn_logIn"> <a href="#"> Log In </a> </li>
        <li class="btn_signUp"> <a href="#"> Sign Up </a> </li>
        <li> <a href="mailto:support@knot.com"> Support </a> </li>
    </ul>
</div>

<!--***********************************************-->

<div class="formBlock_logIn">
    <form action="/action_page.php">
        <input type="text" name="login" value="" placeholder="User Name">
        <input type="password" name="password" value="" placeholder="Password">
        <input type="submit" value="Login">
    </form>
</div>

<!--***********************************************-->

<div class="formBlock_signUp">
    <form action="/action_page.php">
        <input type="text" name="FirstName" value="" placeholder="First Name">
        <input type="text" name="LastName" value="" placeholder="Last Name">
        <input type="text" name="login" value="" placeholder="Email">
        <input type="text" name="login" value="" placeholder="User Name">
        <input type="password" name="password" value="" placeholder="Password">
        <input type="password" name="password" value="" placeholder="Confirm Password">
        <input type="submit" value="SignUp">
    </form>
</div>

<!--***********************************************-->


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/LoginPage.js"> </script>
</body>

</html>