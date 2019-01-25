<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><i18nTag:localization message="change_password"/></title>
    <link href="<c:url value='/css/operations/updatePassword.css'/>" rel="stylesheet">
</head>

<body>

<div style="text-align: right; margin: 0 auto; border: 3px solid #00905d;width:400px;padding:10px;">

    <div class="error">
        <p style="color: #EF3B3A;"><i18nTag:localization message="${error}"/></p>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/user/passwordUpdate.jsp">
        <i18nTag:localization message="old_password"/>:<br>
        <input type="password" name="oldPassword" value=""><br>
        <i18nTag:localization message="new_password"/>:<br>
        <input type="password" name="newPassword" value=""><br>
        <br>
        <hr>

        <div class="submit">
            <button type="submit" class="btn-link"><i18nTag:localization message="submit"/></button>
        </div>
    </form>
</div>

</body>
</html>