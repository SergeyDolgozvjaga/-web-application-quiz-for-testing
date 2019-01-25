<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="update_profile"/></title>

    <link href="<c:url value='/css/operations/profileUpdate.css'/>" rel="stylesheet">

</head>

<body>
<br>
<div style="text-align: right; margin: 0 auto; border: 3px solid #00905d;width:400px;padding:10px;">

    <div class="profile-update">
        <p style="color: #EF3B3A;"><i18nTag:localization message="${error}"/></p>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/user/profileUpdate.jsp">
        <i18nTag:localization message="login"/>:<br>
        <input type="text" name="login" placeholder="" value="${loggedUser.login}"><br>
        <i18nTag:localization message="user_name"/>:<br>
        <input type="text" name="userName" value="${loggedUser.userName}"><br>
        <i18nTag:localization message="age"/>:<br>
        <input type="text" name="age" value="${loggedUser.age}"><br>
        <i18nTag:localization message="email"/>:<br>
        <input type="text" name="email" value="${loggedUser.email}"><br>
        <br>
        <hr>

        <div class="submit">
            <button type="submit" class="btn-link"><i18nTag:localization message="submit"/></button>
        </div>
    </form>
</div>
<br>

<a class="button" href="${pageContext.request.contextPath}/user/passwordUpdate.jsp">
    <i18nTag:localization message="change_password"/></a>
<a class="button" href="${pageContext.request.contextPath}/user/profileDelete.jsp">
    <i18nTag:localization message="delete_profile"/></a>
<br>

</body>
</html>


