<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../css/profile/login.css" rel="stylesheet">

    <title><i18nTag:localization message="login"/></title>
    <jsp:include page="header.jsp"></jsp:include>
</head>

<body>
<br>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="login" accept-charset="UTF-8">
            <p style="color: #EF3B3A;"><i18nTag:localization message="${error}"/></p>
            <i18nTag:localization message="login"/>:<br>
            <input type="text" name="login" placeholder="<i18nTag:localization message="user_login"/>" value="" required><br>
            <i18nTag:localization message="password"/>:<br>
            <input type="password" name="password" placeholder="<i18nTag:localization message="password"/>" value="" required><br>
            <i18nTag:localization message="submit"/>:<br>
            <button type="submit"><i18nTag:localization message="login"/></button>
            <p class="message"><i18nTag:localization message="not_registered?"/>
                <a href="${pageContext.request.contextPath}/registration.jsp"><i18nTag:localization message="register_now"/></a></p>
        </form>
    </div>
</div>
<br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
