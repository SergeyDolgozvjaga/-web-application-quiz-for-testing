<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tag/localization.tld" prefix="i18nTag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><i18nTag:localization message="admin_create_new"/></title>
    <link href="<c:url value='/css/admin/profile/createNewAdmin.css'/>" rel="stylesheet">
</head>

<body>
<div style="text-align: right; margin: 0 auto; border: 3px solid #0000FF;width:400px;padding:10px;">

    <div class="error">
        <p><i18nTag:localization message="${error}"/></p>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/admin/createNewAdmin.jsp">
        <i18nTag:localization message="user_id"/>:<br>
        <input type="text" name="userID" value="" required><br>
        <i18nTag:localization message="user_login"/>:<br>
        <input type="text" name="login" value="" required><br>
        <hr>

        <div class="submit">
            <button type="submit" class="btn-link"><i18nTag:localization message="submit"/></button>
        </div>

    </form>
</div>

<br>
</body>
</html>
