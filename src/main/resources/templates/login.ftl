<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>

        <!-- jQuery -->
        <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300" rel='stylesheet'>
        <meta charset="utf-8">
        <title>Knot</title>
        <link rel="icon" href="/images/logo.png">
    </head>

    <body>
        <#--<div th:if="${param}">-->
            <#--Invalid username and password.-->
        <#--</div>-->
        <#--<div th:if="${logout}">-->
            <#--You have been logged out.-->
        <#--</div>-->
        <#if logout>
        <div class="info">Uve been logged in</div>
        </#if>
        <#if error>
        <div class="danger">Invalid user or password</div>
        </#if>
        <#--th:action="@{/login/}"-->
        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
    </body>
</html>