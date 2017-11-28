
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/loginPage.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
    <meta charset="utf-8">
    <title>Knot | login</title>
    <link rel="icon" href="images/logo.png">
</head>

<body>

<!-- 1234567890 -->

<div class="loginPage-wrapper">
    <div class="title-wrapper">
        <div class="headTitle">
            <span class="super">k</span>not
            <span class="str_logIn">| Log In</span>
        </div>
        <div class="signMenu">
            <ul>
                <li class="btn_logIn"> <a href="/login"> Log In </a> </li>
                <li class="btn_signUp"> <a href="/login/registration"> Sign Up </a> </li>
                <li> <a href="mailto:support@knot.com"> Support </a> </li>
            </ul>
        </div>
    </div>

    <div class="warning-wrapper">
    <#if error>
        <div class="danger">Invalid user or password</div>
    </#if>
    </div>

    <div class="form-wrapper">
        <div class="formBlock_logIn">
            <form method="post">
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                <input type="text" name="username" value="" placeholder="User Name">
                <input type="password" name="password" value="" placeholder="Password">
                <input type="submit" value="Login">
            </form>
            <div class="createAnAccount"> <a href="/login/registration"> Create an Account </a> </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="/js/loginPage.js"> </script>
</body>

</html>