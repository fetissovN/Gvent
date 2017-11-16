<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/loginPage.css">
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
    <#--<#if logout>-->
    <#--<div class="info">Uve been logged in</div>-->
    <#--</#if>-->
    <#if error>
    <div class="danger">Invalid user or password</div>
    </#if>
    <div class="formBlock_logIn">
        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <input type="text" name="username" value="" placeholder="User Name">
            <input type="password" name="password" value="" placeholder="Password">
            <input type="submit" value="Login">
        </form>
    </div>

    <!--***********************************************-->
    <#import "/spring.ftl" as spring/>
    <div class="formBlock_signUp">

        <form action="/login/registration" method="post">
        <@spring.bind path= "newUser" />
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
        <@spring.formInput "newUser.username" />
            <#--<input type="text" name="username" value="" placeholder="User Name">-->
            <#--<input type="text" name="firstName" value="" placeholder="First Name">-->
            <#--<input type="text" name="lastName" value="" placeholder="Last Name">-->
            <#--<input type="text" name="email" value="" placeholder="Email" required>-->
            <#--<input type="password" name="password" value="" placeholder="Password" required>-->
            <#--<input type="password" name="passwordConfirm" value="" placeholder="Confirm Password" required>-->
            <input type="submit" value="SignUp">
        </form>
    </div>
    <#--<div class="formBlock_signUp">-->
        <#--<form action="/login/registration" name="newUser" method="post">-->
            <#--<input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">-->
            <#--<input type="text" name="username" value="" placeholder="User Name">-->
            <#--<input type="text" name="firstName" value="" placeholder="First Name">-->
            <#--<input type="text" name="lastName" value="" placeholder="Last Name">-->
            <#--<input type="text" name="email" value="" placeholder="Email" required>-->
            <#--<input type="password" name="password" value="" placeholder="Password" required>-->
            <#--<input type="password" name="passwordConfirm" value="" placeholder="Confirm Password" required>-->
            <#--<input type="submit" value="SignUp">-->
        <#--</form>-->
    <#--</div>-->

    <!--***********************************************-->


    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="/js/loginPage.js"> </script>
</body>

</html>