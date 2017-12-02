
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/loginPage.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
    <meta charset="utf-8">
    <title>Knot | login</title>
    <link rel="icon" href="images/logo.png">
</head>

<body>
<#import "/spring.ftl" as spring/>

<div class="loginPage-wrapper">
    <div class="title-wrapper">
        <div class="headTitle">
            <span class="super">k</span>not
            <span class="str_logIn">| Sign Up</span>
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
    <#if errorNicknameExist ??>
        <div class="danger">${errorNicknameExist}</div>
    </#if>
    <#if errorEmailExist ??>
        <div class="danger">${errorEmailExist}</div>
    </#if>
    </div>

    <div class="form-wrapper">
        <div class="formBlock_signUp">
            <form action="/login/registration" method="post">
            <@spring.bind path= "newUser" />
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">

            <@spring.formInput "newUser.username","placeholder='User Name'" />
                <#--<div class="error">-->
                    <@spring.showErrors "newUser.username","error" />
                <#--</div>-->
            <@spring.formInput "newUser.firstName","placeholder='First Name'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.firstName","error" />
                <#--</div>-->

            <@spring.formInput "newUser.lastName","placeholder='Last Name'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.lastName","error" />
                <#--</div>-->

            <@spring.formInput "newUser.email","placeholder='Email'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.email","error" />
                <#--</div>-->

            <@spring.formInput "newUser.age","placeholder='Age',class='age'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.age","error" />
                <#--</div>-->


            <@spring.formInput "newUser.gender","placeholder='Gender',class='age'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.gender","error" />
                <#--</div>-->

            <@spring.formInput "newUser.password","placeholder='Password'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.password","error" />
                <#--</div>-->

            <@spring.formInput "newUser.passwordCheck","placeholder='Confirm Password'" />
                <#--<div class="danger">-->
                    <@spring.showErrors "newUser.passwordCheck","error" />
                <#--</div>-->
            <input type="submit" value="SignUp">
            </form>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="/js/loginPage.js"> </script>
</body>

</html>