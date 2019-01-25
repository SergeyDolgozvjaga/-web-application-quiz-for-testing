<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="user_registration"/></title>

    <link href="../../css/profile/registration.css" rel="stylesheet">
</head>

<body>

<div class="registration-page">
    <div class="form">
        <form class="register-form" method="post" action="registration" accept-charset="UTF-8">
            <p style="color: #EF3B3A;"><i18nTag:localization message="${error}"/></p>
            <i18nTag:localization message="register_form"/>:<br>
            <i18nTag:localization message="login"/>:<br>
            <input type="text" name="login" placeholder="<i18nTag:localization message="user_login"/>" value="" required/><br>
            <i18nTag:localization message="password"/>:<br>
            <input type="password" name="password" placeholder="<i18nTag:localization message="password"/>" value="" required/><br>
            <i18nTag:localization message="user_name"/>:<br>
            <input type="text" name="userName" placeholder="<i18nTag:localization message="user_name"/>" value="" required/><br>
            <i18nTag:localization message="age"/>:<br>
            <input type="text" name="age" placeholder="<i18nTag:localization message="age"/>" value="" required/><br>
            <i18nTag:localization message="email"/>:<br>
            <input type="text" name="email" placeholder="<i18nTag:localization message="email"/>" name="email" value="" required/><br>
            <i18nTag:localization message="registration_send_data"/>:<br>
            <button type="submit"><i18nTag:localization message="submit"/></button><br>
            <p class="message"><i18nTag:localization message="already_registered?"/>
                <a href="${pageContext.request.contextPath}/login.jsp"><i18nTag:localization message="login"/></a></p>
        </form>
    </div>
</div>

<br>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
