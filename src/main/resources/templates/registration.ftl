
<#include "regBar.ftl">

    <!--***********************************************-->
    <#import "/spring.ftl" as spring/>
<div class="wrapper_headTitle">
    <span class="super">k</span>not
    <span class="str_signUp">| Sign Up</span>
</div>
    <div class="formBlock_signUp">

        <form action="/login/registration" method="post">
        <@spring.bind path= "newUser" />
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">

        <#if errorNicknameExist ??>
            <div class="danger">${errorNicknameExist}</div>
        </#if>
        <@spring.formInput "newUser.username","placeholder='User Name'" />
        <@spring.showErrors "newUser.username","error" />

        <@spring.formInput "newUser.firstName","placeholder='First Name'" />
        <@spring.showErrors "newUser.firstName","error" />

        <@spring.formInput "newUser.lastName","placeholder='Last Name'" />
        <@spring.showErrors "newUser.lastName","error" />

        <#if errorEmailExist ??>
            <div class="danger">${errorEmailExist}</div>
        </#if>
        <@spring.formInput "newUser.email","placeholder='Email'" />
        <@spring.showErrors "newUser.email","error" />

        <@spring.formInput "newUser.age","placeholder='Age',class='age'" />
        <@spring.showErrors "newUser.age","error" />

        <@spring.formInput "newUser.gender","placeholder='Gender',class='age'" />
        <@spring.showErrors "newUser.gender","error" />

        <@spring.formInput "newUser.password","placeholder='Password'" />
        <@spring.showErrors "newUser.password","error" />

        <@spring.formInput "newUser.passwordCheck","placeholder='Confirm Password'" />
        <@spring.showErrors "newUser.passwordCheck","error" />
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